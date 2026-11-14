package id.ac.univpancasila.perpustakaan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Sesi 11 — unit test pertama Anda.
 *
 * Letakkan berkas ini di src/test/java/id/ac/univpancasila/perpustakaan/
 * Jalankan dengan: mvn test
 *
 * Konvensi Maven: hanya berkas di src/test/java yang bernama *Test
 * yang akan dijalankan sebagai pengujian.
 */
class RepositoryTest {

    /** Objek sederhana untuk diuji. */
    static final class Buku {
        private final String isbn;
        private final String judul;

        Buku(String isbn, String judul) {
            this.isbn = isbn;
            this.judul = judul;
        }

        String isbn() { return isbn; }
        String judul() { return judul; }
    }

    private Repository<Buku> repo;

    @BeforeEach
    void siapkan() {
        // Dijalankan ulang sebelum SETIAP pengujian, sehingga tiap uji
        // berangkat dari keadaan yang bersih dan tidak saling memengaruhi.
        repo = new Repository<>(Buku::isbn);
    }

    @Test
    @DisplayName("Item yang disimpan dapat ditemukan kembali")
    void simpanLaluTemukan() {
        repo.simpan(new Buku("978-1", "PBO"));

        var hasil = repo.cariById("978-1");
        assertTrue(hasil.isPresent());
        assertEquals("PBO", hasil.get().judul());
    }

    @Test
    @DisplayName("Mencari id yang tidak ada mengembalikan Optional kosong")
    void cariYangTidakAda() {
        assertTrue(repo.cariById("978-999").isEmpty());
    }

    @Test
    @DisplayName("Item yang dihapus tidak dapat ditemukan lagi")
    void hapusItem() {
        repo.simpan(new Buku("978-1", "PBO"));

        assertTrue(repo.hapus("978-1"));
        assertTrue(repo.cariById("978-1").isEmpty());
    }

    @Test
    @DisplayName("Repository baru berisi nol item")
    void repositoryKosong() {
        assertEquals(0, repo.jumlah());
        assertTrue(repo.cariSemua().isEmpty());
    }

    @Test
    @DisplayName("Koleksi yang dikembalikan cariSemua tidak dapat diubah dari luar")
    void koleksiTidakBocor() {
        repo.simpan(new Buku("978-1", "PBO"));

        var daftar = repo.cariSemua();
        assertThrows(UnsupportedOperationException.class, () -> daftar.add(new Buku("978-2", "Java")));
    }

    @Test
    @DisplayName("Simpan dengan id yang sama mengganti item lama")
    void simpanDenganIdYangSamaMenggantiNilaiLama() {
        repo.simpan(new Buku("978-1", "PBO"));
        repo.simpan(new Buku("978-1", "Java"));

        assertEquals(1, repo.jumlah());
        assertEquals("Java", repo.cariById("978-1").orElseThrow().judul());
    }

    @Test
    @DisplayName("Menghapus id yang tidak ada mengembalikan false")
    void hapusYangTidakAda() {
        assertFalse(repo.hapus("978-999"));
    }

    @Test
    @DisplayName("cariSemua mengembalikan salinan yang tidak terhubung ke data internal")
    void cariSemuaBukanAliasMap() {
        repo.simpan(new Buku("978-1", "PBO"));

        var hasil = repo.cariSemua();
        assertThrows(UnsupportedOperationException.class, () -> hasil.add(new Buku("978-2", "Java")));
        assertEquals(1, repo.jumlah());
        assertTrue(repo.cariById("978-2").isEmpty());
    }
}
