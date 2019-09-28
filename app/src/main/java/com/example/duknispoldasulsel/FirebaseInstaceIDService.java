package com.example.duknispoldasulsel;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstaceIDService extends FirebaseInstanceIdService {
    private static final String TAG = FirebaseInstaceIDService.class.getSimpleName();
    public static final String TAG_ID       = "id";
    int success;
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_SUCCESS = "success";
    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
       // Register(token);
        simpan_update(token);
        Log.d("Token : ",token);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void simpan_update(final String id_ruang) {
            Log.d("Simpan",id_ruang);
    }

}
