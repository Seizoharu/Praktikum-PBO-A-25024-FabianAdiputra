package id.ac.univpancasila.perpustakaan.denda;

public final class DendaMahasiswa implements AturanDenda {

    @Override
    public double hitung(int hariTerlambat) {
        return Math.max(0, hariTerlambat) * 1000.0;
    }

    @Override
    public String nama() {
        return "Mahasiswa";
    }
}
