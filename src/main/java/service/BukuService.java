package service;

import helper.InputHelper;
import java.util.ArrayList;
import model.Buku;

public class BukuService {

    private static final int LANTAI = 2, RAK = 3, POSISI = 4;

    // Array 3D: lokasi buku [lantai][rak][posisi]
    private final String[][][] lokasi = new String[LANTAI][RAK][POSISI];

    // ArrayList: koleksi buku yang dinamis
    private final ArrayList<Buku> daftarBuku = new ArrayList<>();

    private final KategoriService kategoriService;
    private int nextId = 1;

    public BukuService(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
    }

    // Menu 2: tambah buku (ArrayList + Array 3D)
    public void tambah() {
        String judul = InputHelper.bacaString("Judul buku: ");
        if (judul.isEmpty()) {
            System.out.println("Judul tidak boleh kosong.");
            return;
        }

        kategoriService.tampil();
        int k = InputHelper.bacaInt("Pilih kategori (nomor): ");
        if (k < 1 || k > kategoriService.jumlah()) {
            System.out.println("Kategori tidak valid.");
            return;
        }

        // Cari slot kosong pertama pada array 3D
        for (int l = 0; l < LANTAI; l++) {
            for (int r = 0; r < RAK; r++) {
                for (int p = 0; p < POSISI; p++) {
                    if (lokasi[l][r][p] == null) {
                        Buku b = new Buku(nextId++, judul, k - 1, l, r, p);
                        daftarBuku.add(b);
                        lokasi[l][r][p] = judul;
                        System.out.println("Buku ditambahkan (ID " + b.id + ") di Lantai "
                                + (l + 1) + ", Rak " + (r + 1) + ", Posisi " + (p + 1));
                        return;
                    }
                }
            }
        }
        System.out.println("Rak penuh, buku tidak dapat ditambahkan.");
    }

    // Menu 3: tampilkan semua buku (ArrayList)
    public void tampil() {
        System.out.println("\n--- Daftar Buku ---");
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }
        for (Buku b : daftarBuku) {
            System.out.println("ID " + b.id + " | " + b.judul + " | " + kategoriService.getNama(b.kategoriIdx)
                    + " | L" + (b.lantai + 1) + "-R" + (b.rak + 1) + "-P" + (b.posisi + 1)
                    + " | " + (b.dipinjam ? "Dipinjam" : "Tersedia"));
        }
    }

    // Menu 4: hapus buku (ArrayList + Array 3D)
    public void hapus() {
        int id = InputHelper.bacaInt("ID buku yang dihapus: ");
        for (int i = 0; i < daftarBuku.size(); i++) {
            Buku b = daftarBuku.get(i);
            if (b.id == id) {
                if (b.dipinjam) {
                    System.out.println("Buku sedang dipinjam, tidak dapat dihapus.");
                    return;
                }
                lokasi[b.lantai][b.rak][b.posisi] = null;
                daftarBuku.remove(i);
                System.out.println("Buku \"" + b.judul + "\" dihapus.");
                return;
            }
        }
        System.out.println("Buku dengan ID tersebut tidak ditemukan.");
    }

    // Menu 8: peta lokasi buku (Array 3D)
    public void tampilLokasi() {
        System.out.println("\n--- Peta Lokasi Buku ---");
        for (int l = 0; l < LANTAI; l++) {
            System.out.println("Lantai " + (l + 1));
            for (int r = 0; r < RAK; r++) {
                System.out.print("  Rak " + (r + 1) + ": ");
                for (int p = 0; p < POSISI; p++) {
                    String isi = (lokasi[l][r][p] == null) ? "-" : lokasi[l][r][p];
                    System.out.print("[" + isi + "] ");
                }
                System.out.println();
            }
        }
    }

    // Dipakai proses peminjaman: tampilkan buku yang tersedia, kembalikan true jika ada
    public boolean tampilTersedia() {
        System.out.println("Buku tersedia:");
        boolean ada = false;
        for (Buku b : daftarBuku) {
            if (!b.dipinjam) {
                System.out.println("  ID " + b.id + " | " + b.judul);
                ada = true;
            }
        }
        return ada;
    }

    // Dipakai proses peminjaman: cari buku tersedia berdasarkan ID, null jika tidak ada
    public Buku cariTersedia(int id) {
        for (Buku b : daftarBuku) {
            if (b.id == id && !b.dipinjam) {
                return b;
            }
        }
        return null;
    }
}