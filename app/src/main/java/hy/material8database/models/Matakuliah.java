package hy.material8database.models;

public class Matakuliah {
    public String kode_matakuliah;
    public String nama;
    public int sks;
    public String kode_jurusan;


    public Matakuliah(String kode_jurusan, String kode_matakuliah, String nama, int sks) {
        this.kode_jurusan = kode_jurusan;
        this.kode_matakuliah = kode_matakuliah;
        this.nama = nama;
        this.sks = sks;
    }
}