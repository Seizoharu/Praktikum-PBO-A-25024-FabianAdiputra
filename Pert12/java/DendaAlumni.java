package id.ac.univpancasila.perpustakaan.denda;

public final class DendaAlumni implements AturanDenda {

    @Override
    public double hitung(int hariTerlambat) {
        return Math.max(0, hariTerlambat) * 750.0;
    }

    @Override
    public String nama() {
        return "Alumni";
    }
}
