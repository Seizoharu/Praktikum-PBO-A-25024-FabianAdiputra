// Kelas Mahasiswa dengan constructor overloading, setter, dan getter

public class Mahasiswa {
  
    // Variabel instance
    private String nama;
    private String npm;
    private float nilai;

    // Constructor: dengan parameter nama dan npm
    public Mahasiswa(String nama, String npm, float nilai) {
        this.nama = nama;
        this.npm = npm;
        this.nilai = nilai;
    }

    // Getter dan Setter untuk nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter untuk npm
    public String getnpm() {
        return npm;
    }

    public void setnpm(String npm) {
        this.npm = npm;
    }

    public float getNilai(){
        return nilai;
    }

    public void setNilai(float nilai){
        this.nilai=nilai;
    }

    // Method untuk menampilkan informasi mahasiswa
    public String tampilkanInfo() {
        return npm + " " + nama + " | Nilai = " + nilai;
    }
}