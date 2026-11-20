package id.ac.univpancasila.perpustakaan.denda;

/**
 * Sesi 12 — STRATEGY PATTERN.
 *
 * Rantai if untuk jenis anggota (mahasiswa, dosen, kehormatan) diganti
 * dengan satu interface dan beberapa implementasi.
 *
 * Akibatnya: menambah jenis anggota baru = menambah SATU kelas,
 * tanpa menyunting satu baris pun kode yang sudah ada.
 * Itulah Open-Closed Principle yang bekerja.
 */
public interface AturanDenda {

    /** Hitung denda untuk sejumlah hari keterlambatan. Hari negatif dianggap nol. */
    double hitung(int hariTerlambat);

    /** Nama aturan, untuk ditampilkan pada laporan. */
    String nama();
}

