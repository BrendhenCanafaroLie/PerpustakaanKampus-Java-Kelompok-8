package service;

import java.util.Stack;
import model.Buku;
import model.Transaksi;

public class TransaksiService {

    private final String[] hari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};

    // Array 2D: jumlah peminjaman [kategori][hari]
    private final int[][] peminjaman;

    // Stack (LIFO): riwayat transaksi
    private final Stack<Transaksi> riwayat = new Stack<>();

    private final KategoriService kategoriService;

    public TransaksiService(KategoriService kategoriService) {
        this.kategoriService = kategoriService;
        this.peminjaman = new int[kategoriService.jumlah()][hari.length];
    }

    public void tampilHari() {
        for (int i = 0; i < hari.length; i++) {
            System.out.println((i + 1) + ". " + hari[i]);
        }
    }

    public boolean hariValid(int nomor) {
        return nomor >= 1 && nomor <= hari.length;
    }

    public String namaHari(int idx) {
        return hari[idx];
    }

    // Mencatat peminjaman: ubah status buku, tambah counter 2D, push ke Stack
    public void catat(Buku buku, String mahasiswa, int hariIdx) {
        buku.dipinjam = true;
        peminjaman[buku.kategoriIdx][hariIdx]++;
        riwayat.push(new Transaksi(buku, mahasiswa, hariIdx));
    }

    // Menu 7: statistik peminjaman (Array 2D)
    public void tampilStatistik() {
        System.out.println("\n--- Statistik Peminjaman (kategori x hari) ---");
        System.out.printf("%-20s", "Kategori");
        for (String h : hari) {
            System.out.printf("%-8s", h);
        }
        System.out.println();
        for (int i = 0; i < kategoriService.jumlah(); i++) {
            System.out.printf("%-20s", kategoriService.getNama(i));
            for (int j = 0; j < hari.length; j++) {
                System.out.printf("%-8d", peminjaman[i][j]);
            }
            System.out.println();
        }
    }

    // Menu 9: lihat transaksi terakhir (Stack - peek)
    public void lihatTerakhir() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        Transaksi t = riwayat.peek();
        System.out.println("Transaksi terakhir: " + t.mahasiswa + " meminjam \"" + t.buku.judul
                + "\" pada hari " + hari[t.hariIdx]);
        System.out.println("Total transaksi tercatat: " + riwayat.size());
    }

    // Menu 10: batalkan transaksi terakhir (Stack - pop)
    public void batalkan() {
        if (riwayat.isEmpty()) {
            System.out.println("Tidak ada transaksi untuk dibatalkan.");
            return;
        }
        Transaksi t = riwayat.pop();
        t.buku.dipinjam = false;
        peminjaman[t.buku.kategoriIdx][t.hariIdx]--;
        System.out.println("Transaksi dibatalkan: \"" + t.buku.judul + "\" oleh " + t.mahasiswa
                + " (buku kembali tersedia).");
    }
}