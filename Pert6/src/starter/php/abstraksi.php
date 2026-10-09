<?php
declare(strict_types=1);

// INTERFACE
interface Movable
{
    public function bergerak(): void;
    public function kecepatanMaksimum(): float;
}

interface Fuelable
{
    public function isiBahanBakar(float $jumlah): void;
    public function kapasitasTangki(): float;
    public function tipeBahanBakar(): TipeBahanBakar;
}

// Nilai enum direpresentasikan sebagai singleton agar kompatibel dengan PHP 8.0.
final class TipeBahanBakar
{
    private static ?self $bensin = null;
    private static ?self $solar = null;
    private static ?self $listrik = null;

    private function __construct(
        private string $nilai,
        private string $namaLabel,
        private float $harga
    ) {}

    public static function bensin(): self
    {
        return self::$bensin ?? (self::$bensin = new self('bensin', 'Bensin', 12000));
    }

    public static function solar(): self
    {
        return self::$solar ?? (self::$solar = new self('solar', 'Solar', 10500));
    }

    public static function listrik(): self
    {
        return self::$listrik ?? (self::$listrik = new self('listrik', 'Listrik', 2500));
    }

    /** @return self[] */
    public static function cases(): array
    {
        return [self::bensin(), self::solar(), self::listrik()];
    }

    public function value(): string
    {
        return $this->nilai;
    }

    public function label(): string
    {
        return $this->namaLabel;
    }

    public function hargaPerSatuan(): float
    {
        return $this->harga;
    }

    public function biayaPengisian(float $jumlah): float
    {
        return $jumlah * $this->hargaPerSatuan();
    }

    public function ramahLingkungan(): bool
    {
        return $this === self::listrik();
    }
}

// TRAIT
trait Loggable
{
    public function log(string $pesan): void
    {
        $waktu = date('H:i:s');
        $namaKelas = (new ReflectionClass($this))->getShortName();
        printf("[%s] %s: %s\n", $waktu, $namaKelas, $pesan);
    }
}

// ABSTRACT CLASS
abstract class Kendaraan
{
    protected string $merek;
    protected int $tahun;

    public function __construct(
        string $merek,
        int $tahun,
    ) {
        $this->merek = $merek;
        $this->tahun = $tahun;
    }

    public function umur(int $tahunSekarang): int
    {
        return max(0, $tahunSekarang - $this->tahun);
    }

    abstract public function jumlahRoda(): int;

    public function __toString(): string
    {
        return sprintf('%s (%d, %d roda)', $this->merek, $this->tahun, $this->jumlahRoda());
    }
}

// KELAS MOBIL
final class Mobil extends Kendaraan implements Movable, Fuelable
{
    use Loggable;

    private float $isiTangki = 0.0;
    private float $kapasitas;

    public function __construct(
        string $merek,
        int $tahun,
        float $kapasitas
    ) {
        parent::__construct($merek, $tahun);
        $this->kapasitas = $kapasitas;
    }

    public function jumlahRoda(): int { return 4; }

    public function bergerak(): void
    {
        echo $this->merek, ' sedang melaju', PHP_EOL;
    }

    public function kecepatanMaksimum(): float { return 180.0; }

    public function isiBahanBakar(float $jumlah): void
    {
        if ($jumlah < 0) {
            throw new InvalidArgumentException('Jumlah bahan bakar tidak boleh negatif.');
        }
        $this->isiTangki = min($this->kapasitas, $this->isiTangki + $jumlah);
    }

    public function kapasitasTangki(): float { return $this->kapasitas; }
    public function tipeBahanBakar(): TipeBahanBakar { return TipeBahanBakar::bensin(); }
    public function getIsiTangki(): float { return $this->isiTangki; }
}

// KELAS SEPEDA: Movable, tetapi bukan Fuelable
final class Sepeda extends Kendaraan implements Movable
{
    public function jumlahRoda(): int { return 2; }

    public function bergerak(): void
    {
        echo $this->merek, ' sedang dikayuh', PHP_EOL;
    }

    public function kecepatanMaksimum(): float { return 30.0; }
}

// KELAS PESANAN: menggunakan trait yang sama, bukan turunan Kendaraan
class Pesanan
{
    use Loggable;

    private string $namaPelanggan;
    private Kendaraan $kendaraan;

    public function __construct(
        string $namaPelanggan,
        Kendaraan $kendaraan,
    ) {
        $this->namaPelanggan = $namaPelanggan;
        $this->kendaraan = $kendaraan;
    }

    public function __toString(): string
    {
        return sprintf('Pesanan %s: %s', $this->namaPelanggan, $this->kendaraan);
    }
}
