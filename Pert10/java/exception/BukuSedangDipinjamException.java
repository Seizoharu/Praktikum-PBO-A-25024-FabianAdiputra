package id.ac.univpancasila.perpustakaan.exception;

/**
 * Carries the ISBN and title of a book that is already on loan.
 */
public class BukuSedangDipinjamException extends PerpustakaanException {

    private final String isbn;
    private final String judul;

    public BukuSedangDipinjamException(String isbn, String judul) {
        super("Buku \"" + judul + "\" dengan ISBN " + isbn + " sedang dipinjam.");
        this.isbn = isbn;
        this.judul = judul;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getJudul() {
        return judul;
    }
}
