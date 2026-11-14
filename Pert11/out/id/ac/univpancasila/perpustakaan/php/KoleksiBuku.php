<?php
declare(strict_types=1);

namespace Perpustakaan\Model;

use ArrayIterator;
use Countable;
use IteratorAggregate;

/**
 * Sesi 11 — PHP tidak punya generics.
 *
 * Padanannya ada tiga, dan biasanya dipakai bersama:
 *   1. Anotasi PHPDoc (@param Buku[], @return Buku[]) — dibaca PHPStan/Psalm dan IDE
 *   2. Typed collection class — kelas pembungkus yang MEMVALIDASI isinya (berkas ini)
 *   3. Union type untuk kasus sederhana
 *
 * Laravel memakai pendekatan kedua secara luas lewat kelas Collection.
 *
 * IteratorAggregate -> objek ini bisa dipakai langsung di foreach
 * Countable         -> objek ini bisa dipakai langsung di count()
 */
final class KoleksiBuku implements IteratorAggregate, Countable
{
    /** @var array<string,Buku> isbn => Buku */
    private array $items = [];

    /** @param Buku[] $buku */
    public function __construct(array $buku = [])
    {
        foreach ($buku as $b) {
            $this->simpan($b);   // lewat simpan() agar type hint-nya berlaku
        }
    }

    public function simpan(Buku $b): void
    {
        $this->items[$b->isbn] = $b;
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->items[$isbn] ?? null;
    }

    /** @return Buku[] */
    public function semua(): array { return array_values($this->items); }

    public function filter(callable $predikat): self
    {
        return new self(array_values(array_filter($this->items, $predikat)));
    }

    /** @return mixed[] */
    public function map(callable $f): array
    {
        return array_map($f, array_values($this->items));
    }

    public function jumlahkan(callable $f): float
    {
        return (float) array_reduce(
            array_values($this->items),
            fn (float $total, Buku $b) => $total + (float) $f($b),
            0.0
        );
    }

    /** @return array<string,Buku[]> */
    public function kelompokkan(callable $kunci): array
    {
        $hasil = [];

        foreach ($this->items as $buku) {
            $key = (string) $kunci($buku);
            $hasil[$key][] = $buku;
        }

        return $hasil;
    }

    /**
     * Metode bantu untuk skenario pengecualian.
     */
    public function ambil(string $isbn): Buku
    {
        $buku = $this->cariByIsbn($isbn);

        if ($buku === null) {
            throw new BukuTidakDitemukanException("Buku dengan ISBN {$isbn} tidak ditemukan.");
        }

        return $buku;
    }

    public function getIterator(): ArrayIterator
    {
        return new ArrayIterator(array_values($this->items));
    }

    public function count(): int { return count($this->items); }
}
