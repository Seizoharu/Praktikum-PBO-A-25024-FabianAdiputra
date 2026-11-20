package id.ac.univpancasila.perpustakaan.denda;

public final class DendaBebas implements AturanDenda {

    @Override
    public double hitung(int hariTerlambat) {
        return 0.0;
    }

    @Override
    public String nama() {
        return "Bebas";
    }
}
