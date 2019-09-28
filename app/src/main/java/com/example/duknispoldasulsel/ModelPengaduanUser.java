package com.example.duknispoldasulsel;

public class ModelPengaduanUser {
    private String id;
    private String nama;
    private String saker;
    private String isipengaduan;
    private String gambar;

    public ModelPengaduanUser(String id, String nama, String saker, String isipengaduan, String gambar) {
        this.id = id;
        this.nama = nama;
        this.saker = saker;
        this.isipengaduan = isipengaduan;
        this.gambar = gambar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSaker() {
        return saker;
    }

    public void setSaker(String saker) {
        this.saker = saker;
    }

    public String getIsipengaduan() {
        return isipengaduan;
    }

    public void setIsipengaduan(String isipengaduan) {
        this.isipengaduan = isipengaduan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}

