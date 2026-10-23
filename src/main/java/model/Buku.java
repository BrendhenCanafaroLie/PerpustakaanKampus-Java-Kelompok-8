package model;

public class Buku {
    public int id;
    public String judul;
    public int kategoriIdx;
    public int lantai, rak, posisi;
    public boolean dipinjam;

    public Buku(int id, String judul, int kategoriIdx, int lantai, int rak, int posisi) {
        this.id = id;
        this.judul = judul;
        this.kategoriIdx = kategoriIdx;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
        this.dipinjam = false;
    }
}