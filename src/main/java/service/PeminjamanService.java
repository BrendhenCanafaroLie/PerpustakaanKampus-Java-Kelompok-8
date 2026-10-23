package service;

import helper.InputHelper;
import model.Buku;

public class PeminjamanService {

    private final BukuService bukuService;
    private final AntreanService antreanService;
    private final TransaksiService transaksiService;

    public PeminjamanService(BukuService bukuService, AntreanService antreanService,
                             TransaksiService transaksiService) {
        this.bukuService = bukuService;
        this.antreanService = antreanService;
        this.transaksiService = transaksiService;
    }

    // Menu 6: proses peminjaman (Queue + ArrayList + Array 2D + Stack)
    public void proses() {
        if (antreanService.kosong()) {
            System.out.println("Antrean kosong.");
            return;
        }
        String nama = antreanService.lihatDepan();
        System.out.println("Mahasiswa terdepan: " + nama);

        if (!bukuService.tampilTersedia()) {
            System.out.println("Tidak ada buku yang tersedia.");
            return;
        }

        int id = InputHelper.bacaInt("ID buku yang dipinjam: ");
        Buku dipilih = bukuService.cariTersedia(id);
        if (dipilih == null) {
            System.out.println("ID tidak valid atau buku sedang dipinjam.");
            return;
        }

        transaksiService.tampilHari();
        int h = InputHelper.bacaInt("Hari peminjaman (nomor): ");
        if (!transaksiService.hariValid(h)) {
            System.out.println("Hari tidak valid.");
            return;
        }

        // Semua validasi lolos: baru mahasiswa dikeluarkan dari antrean
        antreanService.keluarkan();
        transaksiService.catat(dipilih, nama, h - 1);
        System.out.println(nama + " berhasil meminjam \"" + dipilih.judul + "\" ("
                + transaksiService.namaHari(h - 1) + ").");
    }
}