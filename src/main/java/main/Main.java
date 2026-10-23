package main;

import helper.InputHelper;
import service.AntreanService;
import service.BukuService;
import service.KategoriService;
import service.PeminjamanService;
import service.TransaksiService;

public class Main {

    public static void main(String[] args) {
        KategoriService kategori = new KategoriService();
        BukuService buku = new BukuService(kategori);
        AntreanService antrean = new AntreanService();
        TransaksiService transaksi = new TransaksiService(kategori);
        PeminjamanService peminjaman = new PeminjamanService(buku, antrean, transaksi);

        int pilihan;
        do {
            tampilMenu();
            pilihan = InputHelper.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1: kategori.tampil(); break;
                case 2: buku.tambah(); break;
                case 3: buku.tampil(); break;
                case 4: buku.hapus(); break;
                case 5: antrean.tambah(); break;
                case 6: peminjaman.proses(); break;
                case 7: transaksi.tampilStatistik(); break;
                case 8: buku.tampilLokasi(); break;
                case 9: transaksi.lihatTerakhir(); break;
                case 10: transaksi.batalkan(); break;
                case 0: System.out.println("Terima kasih."); break;
                default: System.out.println("Menu tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void tampilMenu() {
        System.out.println("\n===== PERPUSTAKAAN KAMPUS =====");
        System.out.println("1. Lihat kategori");
        System.out.println("2. Tambah buku");
        System.out.println("3. Tampilkan buku");
        System.out.println("4. Hapus buku");
        System.out.println("5. Antre peminjam");
        System.out.println("6. Proses peminjaman");
        System.out.println("7. Statistik peminjaman");
        System.out.println("8. Peta lokasi buku");
        System.out.println("9. Lihat transaksi terakhir");
        System.out.println("10. Batalkan transaksi terakhir");
        System.out.println("0. Keluar");
    }
}