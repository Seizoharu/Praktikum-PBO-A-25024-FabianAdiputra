package id.ac.univpancasila.perpustakaan.denda;

public final class AturanDendaFactory {

    private AturanDendaFactory() {
    }

    public static AturanDenda untuk(String jenisAnggota) {
        if (jenisAnggota == null) {
            throw new IllegalArgumentException("Jenis anggota tidak boleh null.");
        }

        return switch (jenisAnggota.trim().toLowerCase()) {
            case "mahasiswa" -> new DendaMahasiswa();
            case "dosen" -> new DendaDosen();
            case "kehormatan", "bebas" -> new DendaBebas();
            case "alumni" -> new DendaAlumni();
            default -> throw new IllegalArgumentException(
                    "Jenis anggota tidak dikenal: " + jenisAnggota);
        };
    }
}
