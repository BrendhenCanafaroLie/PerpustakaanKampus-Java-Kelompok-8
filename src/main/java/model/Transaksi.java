package model;

public class Transaksi {
    public Buku buku;
    public String mahasiswa;
    public int hariIdx;

    public Transaksi(Buku buku, String mahasiswa, int hariIdx) {
        this.buku = buku;
        this.mahasiswa = mahasiswa;
        this.hariIdx = hariIdx;
    }
}