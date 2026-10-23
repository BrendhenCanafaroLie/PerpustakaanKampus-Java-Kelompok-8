package service;

public class KategoriService {

    // Array 1D: daftar kategori buku
    private final String[] kategori = {"Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"};

    public int jumlah() {
        return kategori.length;
    }

    public String getNama(int idx) {
        return kategori[idx];
    }

    // Menu 1
    public void tampil() {
        System.out.println("\n--- Daftar Kategori ---");
        for (int i = 0; i < kategori.length; i++) {
            System.out.println((i + 1) + ". " + kategori[i]);
        }
    }
}