public class AntiPatternRefaktor {


    interface Bangun {
        double luas();
    }

    static class LingkaranData implements Bangun {
        private final double r;

        LingkaranData(double r) {
            this.r = r;
        }

        @Override
        public double luas() {
            return Math.PI * r * r;
        }
    }

    static class PersegiData implements Bangun {
        private final double sisi;

        PersegiData(double sisi) {
            this.sisi = sisi;
        }

        @Override
        public double luas() {
            return sisi * sisi;
        }
    }

    static class SegitigaData implements Bangun {
        private final double alas;
        private final double tinggi;

        SegitigaData(double alas, double tinggi) {
            this.alas = alas;
            this.tinggi = tinggi;
        }

        @Override
        public double luas() {
            return 0.5 * alas * tinggi;
        }
    }

    public static void main(String[] args) {
        Bangun[] daftar = {
            new LingkaranData(7),
            new PersegiData(5),
            new SegitigaData(4, 3)
        };

        double total = 0;
        for (Bangun b : daftar) {
            total += b.luas();
        }
        System.out.printf("Total luas (cara polimorfik): %.2f%n", total);
    }
}