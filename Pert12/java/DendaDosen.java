package id.ac.univpancasila.perpustakaan.denda;

public final class DendaDosen implements AturanDenda {

    @Override
    public double hitung(int hariTerlambat) {
        return Math.max(0, hariTerlambat) * 500.0;
    }

    @Override
    public String nama() {
        return "Dosen";
    }
}
