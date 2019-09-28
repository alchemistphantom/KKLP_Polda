
package com.example.duknispoldasulsel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class menu_user_Activity extends AppCompatActivity {

    private Button btn_laporanMasalah, btn_riwayatLaporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView judul = findViewById(R.id.judul);
        judul.setText("Selamat Datang User : "+getIntent().getStringExtra("username"));
        btn_laporanMasalah = findViewById(R.id.btn_laporanMasalah);
        btn_riwayatLaporan = findViewById(R.id.btn_riwayatLaporanUser);

        btn_laporanMasalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_user_Activity.this, LaporanMasalahUserActivity.class);
                intent.putExtra("username1",getIntent().getStringExtra("username"));
                startActivity(intent);

            }
        });

        btn_riwayatLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_user_Activity.this, ListRiwayatLaporanActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuoption, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout :
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
