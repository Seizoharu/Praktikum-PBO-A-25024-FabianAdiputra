/**
 * BAHAN LANGKAH 5 — versi yang TIDAK memakai polimorfisme.
 *
 * Jalankan dulu apa adanya, amati keluarannya.
 * Lalu refaktor menjadi polimorfik dan simpan sebagai AntiPatternRefaktor.java.
 * JANGAN hapus berkas ini — keduanya diperlukan saat demo.
 *
 * Pertanyaan pemandu:
 *   1. Berapa baris yang harus disunting untuk menambah satu bangun datar baru?
 *   2. Apa yang terjadi jika seseorang lupa menambahkan satu cabang else-if?
 *   3. Di mana pengetahuan "cara menghitung luas lingkaran" SEHARUSNYA berada?
 */
public class AntiPattern {

    static class LingkaranData {
        final double r;
        LingkaranData(double r) {
            this.r = r;
        }
    }

    static class PersegiData {
        final double sisi;
        PersegiData(double sisi) {
            this.sisi = sisi;
        }
    }

    static class SegitigaData {
        final double alas;
        final double tinggi;
        SegitigaData(double alas, double tinggi) {
            this.alas = alas;
            this.tinggi = tinggi;
        }
    }

    /** Setiap bangun datar baru memaksa method ini disunting. */
    static double hitungLuas(Object bangun) {
        if (bangun instanceof LingkaranData) {
            LingkaranData l = (LingkaranData) bangun;
            return Math.PI * l.r * l.r;
        } else if (bangun instanceof PersegiData) {
            PersegiData p = (PersegiData) bangun;
            return p.sisi * p.sisi;
        } else if (bangun instanceof SegitigaData) {
            SegitigaData s = (SegitigaData) bangun;
            return 0.5 * s.alas * s.tinggi;
        }
        throw new IllegalArgumentException("Bangun tidak dikenal: " + bangun);
    }

    public static void main(String[] args) {
        Object[] daftar = {
            new LingkaranData(7),
            new PersegiData(5),
            new SegitigaData(4, 3)
        };

        double total = 0;
        for (Object o : daftar) total += hitungLuas(o);
        System.out.printf("Total luas (cara anti-pattern): %.2f%n", total);

        System.out.println();
        System.out.println("Tugas Langkah 5: ubah menjadi polimorfik.");
        System.out.println("Catat di penelusuran.md: berapa baris yang disunting");
        System.out.println("untuk menambah tipe baru pada masing-masing versi?");
    }
}
