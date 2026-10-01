public class PegawaiTetap extends Pegawai {

    /** Tunjangan masa kerja: 2% gaji pokok per tahun, maksimum 40%. */
    protected static final double TUNJANGAN_PER_TAHUN = 0.02;
    protected static final double TUNJANGAN_MAKSIMUM  = 0.40;

    private final int masaKerjaTahun;

    public PegawaiTetap(String nip, String nama, double gajiPokok, int masaKerjaTahun) {
        // Wajib menjadi pernyataan pertama.
        super(nip, nama, gajiPokok);

        this.masaKerjaTahun = masaKerjaTahun;
    }

    /**
     * Hitung gaji = gaji dasar induk + tunjangan masa kerja.
     */
    @Override
    public double hitungGaji() {
        double persentaseTunjangan = masaKerjaTahun * TUNJANGAN_PER_TAHUN;

        // Maksimum tunjangan adalah 40%.
        if (persentaseTunjangan > TUNJANGAN_MAKSIMUM) {
            persentaseTunjangan = TUNJANGAN_MAKSIMUM;
        }

        double gajiDasar = super.hitungGaji();
        double tunjangan = gajiDasar * persentaseTunjangan;

        return gajiDasar + tunjangan;
    }

    @Override
    public String jenis() {
        return "TETAP";
    }

    protected int getMasaKerjaTahun() {
        return masaKerjaTahun;
    }
}
