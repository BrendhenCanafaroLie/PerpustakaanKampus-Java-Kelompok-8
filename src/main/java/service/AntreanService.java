package service;

import helper.InputHelper;
import java.util.LinkedList;
import java.util.Queue;

public class AntreanService {

    // Queue (FIFO): antrean mahasiswa peminjam
    private final Queue<String> antrean = new LinkedList<>();

    // Menu 5: antre peminjam
    public void tambah() {
        String nama = InputHelper.bacaString("Nama mahasiswa: ");
        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong.");
            return;
        }
        antrean.offer(nama);
        System.out.println(nama + " masuk antrean. Posisi: " + antrean.size());
        System.out.println("Antrean saat ini: " + antrean);
    }

    public boolean kosong() {
        return antrean.isEmpty();
    }

    // Melihat mahasiswa terdepan tanpa mengeluarkannya
    public String lihatDepan() {
        return antrean.peek();
    }

    // Mengeluarkan mahasiswa terdepan
    public String keluarkan() {
        return antrean.poll();
    }
}