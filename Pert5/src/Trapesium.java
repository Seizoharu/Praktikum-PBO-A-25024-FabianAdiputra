public class Trapesium extends BangunDatar {
    private final double sisiAtas;
    private final double sisiBawah;
    private final double tinggi;
    private final double sisiKiri;
    private final double sisiKanan;

    public Trapesium(double sisiAtas, double sisiBawah, double tinggi, double sisiKiri, double sisiKanan) {
        super("Trapesium");
        if (!Double.isFinite(sisiAtas) || !Double.isFinite(sisiBawah)
                || !Double.isFinite(tinggi) || !Double.isFinite(sisiKiri)
                || !Double.isFinite(sisiKanan) || sisiAtas <= 0 || sisiBawah <= 0
                || tinggi <= 0 || sisiKiri <= 0 || sisiKanan <= 0) {
            throw new IllegalArgumentException("Semua ukuran trapesium harus bernilai positif dan terbatas.");
        }
        this.sisiAtas = sisiAtas;
        this.sisiBawah = sisiBawah;
        this.tinggi = tinggi;
        this.sisiKiri = sisiKiri;
        this.sisiKanan = sisiKanan;
    }

    @Override
    public double luas() {
        return 0.5 * (sisiAtas + sisiBawah) * tinggi;
    }

    @Override
    public double keliling() {
        return sisiAtas + sisiBawah + sisiKiri + sisiKanan;
    }
}
