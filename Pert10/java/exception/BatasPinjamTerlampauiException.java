package id.ac.univpancasila.perpustakaan.exception;

/**
 * Carries the member ID and maximum loan limit that was exceeded.
 */
public class BatasPinjamTerlampauiException extends PerpustakaanException {

    private final String idAnggota;
    private final int batas;

    public BatasPinjamTerlampauiException(String idAnggota, int batas) {
        super("Anggota " + idAnggota + " telah mencapai batas pinjam " + batas + ".");
        this.idAnggota = idAnggota;
        this.batas = batas;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public int getBatas() {
        return batas;
    }
}
