public class Lingkaran extends BangunDatar {

    private final double jariJari;

    public Lingkaran(double jariJari) {
        super("Lingkaran");
        if (!Double.isFinite(jariJari) || jariJari <= 0) {
            throw new IllegalArgumentException("Jari-jari harus bernilai positif dan terbatas.");
        }
        this.jariJari = jariJari;
    }

    @Override
    public double luas() {
        return Math.PI * jariJari * jariJari;
    }

    @Override
    public double keliling() {
        return 2 * Math.PI * jariJari;
    }

    public double getJariJari() { 
        return jariJari; 
    }
}