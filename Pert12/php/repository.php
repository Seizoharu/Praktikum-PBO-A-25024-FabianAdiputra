<?php
declare(strict_types=1);

namespace Perpustakaan;

use Perpustakaan\Model\Buku;

/**
 * Sesi 12 — Repository dan Strategy (PHP).
 * Bandingkan dengan versi Java; strukturnya nyaris identik.
 */

// ══ REPOSITORY ═══════════════════════════════════════════════
interface BookRepository
{
    public function simpan(Buku $buku): void;
    public function cariByIsbn(string $isbn): ?Buku;

    /** @return Buku[] */
    public function cariSemua(): array;

    public function kurangiStok(Buku $buku): void;
}

final class InMemoryBookRepository implements BookRepository
{
    /** @var array<string,Buku> */
    private array $data = [];

    public function simpan(Buku $buku): void
    {
        $this->data[$buku->getNomorInduk()] = $buku;
    }

    public function cariByIsbn(string $isbn): ?Buku
    {
        return $this->data[trim($isbn)] ?? null;
    }

    public function cariSemua(): array
    {
        return array_values($this->data);
    }

    public function kurangiStok(Buku $buku): void
    {
        $isbn = $buku->getNomorInduk();
        if (!isset($this->data[$isbn])) {
            throw new \InvalidArgumentException("Buku tidak ditemukan: {$isbn}");
        }
        $this->data[$isbn]->kurangiStok();
    }
}

// ══ STRATEGY ═════════════════════════════════════════════════
interface AturanDenda
{
    public function hitung(int $hariTerlambat): float;
    public function nama(): string;
}

final class DendaMahasiswa implements AturanDenda
{
    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * 1000;
    }

    public function nama(): string
    {
        return 'Mahasiswa';
    }
}

final class DendaDosen implements AturanDenda
{
    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * 500;
    }

    public function nama(): string
    {
        return 'Dosen';
    }
}

final class DendaBebas implements AturanDenda
{
    public function hitung(int $hariTerlambat): float
    {
        return 0;
    }

    public function nama(): string
    {
        return 'Bebas';
    }
}

final class DendaAlumni implements AturanDenda
{
    public function hitung(int $hariTerlambat): float
    {
        return max(0, $hariTerlambat) * 750;
    }

    public function nama(): string
    {
        return 'Alumni';
    }
}

final class AturanDendaFactory
{
    public static function untuk(string $jenisAnggota): AturanDenda
    {
        return match (strtolower(trim($jenisAnggota))) {
            'mahasiswa' => new DendaMahasiswa(),
            'dosen' => new DendaDosen(),
            'kehormatan', 'bebas' => new DendaBebas(),
            'alumni' => new DendaAlumni(),
            default => throw new \InvalidArgumentException(
                "Jenis anggota tidak dikenal: {$jenisAnggota}"
            ),
        };
    }
}

// ══ LAYANAN ══════════════════════════════════════════════════
final class LibraryService
{
    public function __construct(
        private BookRepository $repo,
        private AturanDenda $aturanDenda,
    ) {
    }

    public function tambahBuku(Buku $buku): void
    {
        $this->repo->simpan($buku);
    }

    public function cariBuku(string $isbn): ?Buku
    {
        return $this->repo->cariByIsbn($isbn);
    }

    /** @return Buku[] */
    public function semuaBuku(): array
    {
        return $this->repo->cariSemua();
    }

    public function pinjam(Buku $buku): void
    {
        $this->repo->kurangiStok($buku);
    }

    public function hitungDenda(int $hariTerlambat): float
    {
        return $this->aturanDenda->hitung($hariTerlambat);
    }

    public function namaAturanDenda(): string
    {
        return $this->aturanDenda->nama();
    }
}
