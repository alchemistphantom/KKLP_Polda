package com.example.duknispoldasulsel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListRiwayatLaporanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter_list_riwayat adapter_list_riwayat;
    private ArrayList<Model_riwayat> riwayatArrayLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_riwayat_laporan);

        addData();

        recyclerView = findViewById(R.id.rv_list_riwayat);
        adapter_list_riwayat = new Adapter_list_riwayat(riwayatArrayLists);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListRiwayatLaporanActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_list_riwayat);
    }

    private void addData() {

        riwayatArrayLists = new ArrayList<>();
        riwayatArrayLists.add(new Model_riwayat("Laporan ke 1", "Tes 1"));
        riwayatArrayLists.add(new Model_riwayat("Laporan ke 2", "Tes 2"));
        riwayatArrayLists.add(new Model_riwayat("Laporan ke 3", "Tes 3"));
    }
}

