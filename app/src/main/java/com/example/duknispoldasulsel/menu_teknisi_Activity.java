package com.example.duknispoldasulsel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu_teknisi_Activity extends AppCompatActivity {

    private Button  btn_riwayatLaporan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_teknisi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_riwayatLaporan = findViewById(R.id.btn_riwayatLaporanTeknisi);

        btn_riwayatLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_teknisi_Activity.this, ListRiwayatLaporanActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_teknisi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_logout :
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.lonceng :
                Intent intent = new Intent(menu_teknisi_Activity.this,NotofikasiActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);

    }
}
