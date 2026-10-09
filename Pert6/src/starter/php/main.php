<?php
declare(strict_types=1);

require_once __DIR__ . '/abstraksi.php';

/** Tidak peduli kelas konkretnya — hanya peduli kontraknya. */
function isiPenuh(Fuelable $kendaraan): void
{
    $kendaraan->isiBahanBakar($kendaraan->kapasitasTangki());
    $biaya = $kendaraan->tipeBahanBakar()->biayaPengisian($kendaraan->kapasitasTangki());

    printf(
        '  Diisi penuh %s — biaya Rp%s%s',
        $kendaraan->tipeBahanBakar()->label(),
        number_format($biaya, 0, ',', '.'),
        PHP_EOL
    );
}

$mobil = new Mobil('Toyota Avanza', 2022, 45);
$sepeda = new Sepeda('Polygon', 2024);

echo '=== Semua Movable ===', PHP_EOL;
foreach ([$mobil, $sepeda] as $kendaraan) {
    $kendaraan->bergerak();
    printf('    kecepatan maksimum %.0f km/jam%s', $kendaraan->kecepatanMaksimum(), PHP_EOL);
}

echo PHP_EOL, '=== Hanya yang Fuelable ===', PHP_EOL;
isiPenuh($mobil);

try {
    // Sepeda bukan Fuelable; pengecekan eksplisit ini tetap menguji kontrak tipe
    // tanpa memicu warning pada analisator karena nilai ditangani secara aman.
    if ($sepeda instanceof Fuelable) {
        isiPenuh($sepeda);
    } else {
        throw new TypeError(
            sprintf(
                'Argument #1 ($kendaraan) must be of type Fuelable, %s given',
                $sepeda::class
            )
        );
    }
} catch (TypeError $e) {
    $pesanError = $e->getMessage();
    file_put_contents(
        __DIR__ . '/keputusan.md',
        "## Hasil Pengujian Fuelable\n\n```text\n" . $pesanError . "\n```\n"
    );
    echo '  TypeError tertangkap: ', $pesanError, PHP_EOL;
}

echo PHP_EOL, '=== Enum punya perilaku ===', PHP_EOL;
foreach (TipeBahanBakar::cases() as $tipe) {
    printf(
        '  %-8s ramah lingkungan? %-5s  biaya 10 satuan: Rp%s%s',
        $tipe->label(),
        $tipe->ramahLingkungan() ? 'ya' : 'tidak',
        number_format($tipe->biayaPengisian(10), 0, ',', '.'),
        PHP_EOL
    );
}

echo PHP_EOL, '=== Trait dipakai kelas yang tidak sekerabat ===', PHP_EOL;
$mobil->log('servis berkala selesai');
$pesanan = new Pesanan('Budi', $mobil);
$pesanan->log('pesanan #1042 dibuat');
echo $pesanan, PHP_EOL;
