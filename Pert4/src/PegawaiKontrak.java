public class PegawaiKontrak extends Pegawai {

    private final int bulanKontrak;

    public PegawaiKontrak(String nip, String nama, double gajiPokok, int bulanKontrak) {
        super(nip, nama, gajiPokok);
        this.bulanKontrak = bulanKontrak;
    }

    // PegawaiKontrak tidak perlu override hitungGaji().
    // Method hitungGaji() dari Pegawai sudah mengembalikan gaji pokok.
    // Karena pegawai kontrak tidak mendapat tunjangan masa kerja,
    // tidak ada tambahan perhitungan yang diperlukan.

    @Override
    public String jenis() {
        return "KONTRAK";
    }

    public int getBulanKontrak() {
        return bulanKontrak;
    }
}
