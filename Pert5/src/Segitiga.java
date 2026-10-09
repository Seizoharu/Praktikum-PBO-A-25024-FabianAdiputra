public class Segitiga extends BangunDatar {
    private final double sisiA;
    private final double sisiB;
    private final double sisiC;
    
    public Segitiga(double sisiA, double sisiB, double sisiC) {
        super("Segitiga");
        if (!Double.isFinite(sisiA) || !Double.isFinite(sisiB) || !Double.isFinite(sisiC)
                || sisiA <= 0 || sisiB <= 0 || sisiC <= 0) {
            throw new IllegalArgumentException("Setiap sisi segitiga harus bernilai positif dan terbatas.");
        }
        if (sisiA + sisiB <= sisiC || sisiA + sisiC <= sisiB || sisiB + sisiC <= sisiA) {
            throw new IllegalArgumentException("Kombinasi sisi tidak memenuhi syarat segitiga.");
        }
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    @Override 
    public double luas() {
        double semikeliling = keliling() / 2;
        return Math.sqrt(semikeliling * (semikeliling - sisiA)
                * (semikeliling - sisiB) * (semikeliling - sisiC));
    }

    @Override 
    public double keliling() {
        return sisiA + sisiB + sisiC;
    }
}