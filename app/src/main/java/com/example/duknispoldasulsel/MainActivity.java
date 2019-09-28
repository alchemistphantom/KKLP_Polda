package com.example.duknispoldasulsel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    EditText et_username, et_password;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_username.getText().toString().isEmpty()){
                    et_username.setError("Masukkan Username anda");
                    et_username.requestFocus();
                }
                else if (et_password.getText().toString().isEmpty()){
                    et_password.setError("Masukkan Password anda");
                    et_password.requestFocus();
                }
               /* else if (et_username.getText().toString().equals("user") || et_password.getText().toString().equals("user")){
                    *//*Intent intent = new Intent(MainActivity.this, menu_user_Activity.class);
                    startActivity(intent);*//*
                }  else if (et_username.getText().toString().equals("teknisi") || et_password.getText().toString().equals("teknisi")){
                   *//* Intent intent = new Intent(MainActivity.this, menu_teknisi_Activity.class);
                    startActivity(intent);*//*
                }*/else {
                    cek_login(et_username.getText().toString(),et_password.getText().toString());
                   // Toast.makeText(MainActivity.this, "Username dan Password salah", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    void cek_login(final String username, final String pass){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");

        myRef.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String password = String.valueOf(dataSnapshot.child("password").getValue());
                Log.d(TAG,"pass  "+ password);

               if (pass.equals(password)){
                   Intent intent = new Intent(MainActivity.this, menu_user_Activity.class);
                   intent.putExtra("username",username);
                   startActivity(intent);
                   Toast.makeText(MainActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(MainActivity.this, "Username atau password Salah", Toast.LENGTH_SHORT).show();
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"errrr  "+ databaseError.getMessage());

            }
        });
    }

}
