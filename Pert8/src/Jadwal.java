public class Jadwal {
    private final String hari;
    private final String jamMulai;
    private final String jamSelesai;
    private final String ruang;
    private final String mataKuliah;

    public Jadwal(String hari, String jamMulai, String jamSelesai, String mataKuliah, String ruang) {
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.ruang = ruang;
        this.mataKuliah = mataKuliah;
    }

    public String getHari()       { return hari; }
    public String getJamMulai()   { return jamMulai; }
    public String getJamSelesai() { return jamSelesai; }
    public String getRuang()      { return ruang; }
    public String getMataKuliah()      { return mataKuliah; }

    @Override public String toString() {
        return hari + " | " + jamMulai + "-" + jamSelesai + " | " + mataKuliah + " (" + ruang + ")";
    }
}