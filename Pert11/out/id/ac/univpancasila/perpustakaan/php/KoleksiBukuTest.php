<?php
declare(strict_types=1);

namespace Perpustakaan\Tests;

use PHPUnit\Framework\TestCase;
use Perpustakaan\Model\Buku;
use Perpustakaan\Model\BukuTidakDitemukanException;
use Perpustakaan\Model\KoleksiBuku;

/**
 * Sesi 11 — unit test PHPUnit.
 *
 * Letakkan di tests/ dan pastikan autoload-dev pada composer.json sudah
 * memetakan Perpustakaan\Tests\ ke tests/.
 *
 * Jalankan: ./vendor/bin/phpunit tests
 */
final class KoleksiBukuTest extends TestCase
{
    private KoleksiBuku $koleksi;

    protected function setUp(): void
    {
        // Dijalankan ulang sebelum SETIAP pengujian.
        $this->koleksi = new KoleksiBuku();
    }

    public function testItemYangDisimpanDapatDitemukanKembali(): void
    {
        $buku = new Buku('978-1', 'PBO');
        $this->koleksi->simpan($buku);

        $this->assertSame('PBO', $this->koleksi->cariByIsbn('978-1')->judul);
    }

    public function testMencariIsbnYangTidakAdaMengembalikanNull(): void
    {
        $this->assertNull($this->koleksi->cariByIsbn('978-999'));
    }

    public function testKoleksiBaruBerisiNolItem(): void
    {
        $this->assertCount(0, $this->koleksi);
    }

    public function testFilterHanyaMengembalikanYangLolosPredikat(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO'));
        $this->koleksi->simpan(new Buku('978-2', 'PHP'));

        $hasil = $this->koleksi->filter(fn (Buku $b) => $b->judul === 'PBO');

        $this->assertCount(1, $hasil);
        $this->assertSame('PBO', $hasil->semua()[0]->judul);
    }

    public function testJumlahkanMenghitungTotalDenganBenar(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO'));
        $this->koleksi->simpan(new Buku('978-2', 'PHP'));
        $this->koleksi->simpan(new Buku('978-3', 'Java'));

        $total = $this->koleksi->jumlahkan(fn (Buku $b) => strlen($b->judul));

        $this->assertSame(10.0, $total);
    }

    public function testKelompokkanMengelompokkanBukuBerdasarkanHurufPertama(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO'));
        $this->koleksi->simpan(new Buku('978-2', 'PHP'));
        $this->koleksi->simpan(new Buku('978-3', 'Java'));

        $hasil = $this->koleksi->kelompokkan(fn (Buku $b) => substr($b->judul, 0, 1));

        $this->assertCount(2, $hasil);
        $this->assertCount(2, $hasil['P']);
        $this->assertCount(1, $hasil['J']);
    }

    public function testMapMengubahSetiapJudulMenjadiHurufKecil(): void
    {
        $this->koleksi->simpan(new Buku('978-1', 'PBO'));
        $this->koleksi->simpan(new Buku('978-2', 'PHP'));

        $hasil = $this->koleksi->map(fn (Buku $b) => strtolower($b->judul));

        $this->assertSame(['pbo', 'php'], $hasil);
    }

    public function testAmbilMelemparExceptionSaatBukuTidakDitemukan(): void
    {
        $this->expectException(BukuTidakDitemukanException::class);

        $this->koleksi->ambil('978-999');
    }
}
