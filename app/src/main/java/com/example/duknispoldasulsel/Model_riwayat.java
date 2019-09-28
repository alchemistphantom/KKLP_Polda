package com.example.duknispoldasulsel;

import android.support.design.widget.TextInputLayout;

public class Model_riwayat {

    private String laporan;
    private String nama_laporan;

    public Model_riwayat(String laporan, String nama_laporan) {
        this.laporan = laporan;
        this.nama_laporan = nama_laporan;
    }

    public String getLaporan() {
        return laporan;
    }
    public void setLaporan(String laporan) {
        this.laporan = laporan;
    }
    public String getNama_laporan() {
        return nama_laporan;
    }
    public void setNama_laporan(String nama_laporan) {
        this.nama_laporan = nama_laporan;
    }
}
