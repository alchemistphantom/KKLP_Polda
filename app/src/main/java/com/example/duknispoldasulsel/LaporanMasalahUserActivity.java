package com.example.duknispoldasulsel;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/*import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;*/

public class LaporanMasalahUserActivity extends AppCompatActivity {

//    private DatabaseReference databaseReference;
    private TextView id,nama,satker;
     private EditText isi;
     private ImageView gambar;
     private Button lapor;
    public final int SELECT_FILE = 1;
    StorageTask storageTask;
    public StorageReference storageReference;
    Uri fileUri;
     private ModelPengaduanUser data;
    private static final String TAG = "LaporanMasalahUserActiv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_masalah_user);

        id = findViewById(R.id.txtid_user);
        nama = findViewById(R.id.txtnama_user);
        satker = findViewById(R.id.txt_satker);
        isi = findViewById(R.id.id_pengaduan);
        gambar = findViewById(R.id.iv_gambarSurat);
        lapor = findViewById(R.id.btnLapor);
        set_data(getIntent().getStringExtra("username1"));
        storageReference = FirebaseStorage.getInstance().getReference("uploads");

        lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });

    }


    void set_data(final String username){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");

        myRef.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                id.setText(String.valueOf(dataSnapshot.child("id").getValue()));
                nama.setText(String.valueOf(dataSnapshot.child("nama").getValue()));
                satker.setText(String.valueOf(dataSnapshot.child("satker").getValue()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"errrr  "+ databaseError.getMessage());

            }
        });
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }


    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_FILE && resultCode==RESULT_OK && data!= null && data.getData() !=null){
            fileUri = data.getData();
            Glide.with(this).load(fileUri).into(gambar);
        }
    }
    private String AmbilExtensifile(Uri fileUri){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(resolver.getType(fileUri));
    }

    public void Tambah_menu(View view) {
        if(storageTask!=null && storageTask.isInProgress()){

        }else{
            upload();
        }
    }
    private void upload(){

        if(fileUri!=null){
            final StorageReference storage = storageReference.child(System.currentTimeMillis()+"."+AmbilExtensifile(fileUri));
            storageTask =  storage.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d("Uri",uri.toString());
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("Pengaduan");
                                    data = new ModelPengaduanUser(id.getText().toString(),nama.getText().toString(),
                                            satker.getText().toString(),
                                            isi.getText().toString(),uri.toString());
//                                    String id = myRef.push().getKey();
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                    String tgl = sdf.format(new Date());
                                    myRef.child(getIntent().getStringExtra("username1")).child(tgl).setValue(data);
                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LaporanMasalahUserActivity.this, "Gagal"+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
        }else{
            Toast.makeText(this, "Tidak ada file yang dipilih", Toast.LENGTH_SHORT).show();
        }
    }
}
