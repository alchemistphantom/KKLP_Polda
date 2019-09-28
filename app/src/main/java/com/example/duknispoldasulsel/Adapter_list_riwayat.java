package com.example.duknispoldasulsel;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_list_riwayat extends RecyclerView.Adapter<Adapter_list_riwayat.ViewHolder> {

    private ArrayList<Model_riwayat> datalist;

    public Adapter_list_riwayat(ArrayList<Model_riwayat> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public Adapter_list_riwayat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.content_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_list_riwayat.ViewHolder viewHolder, int i) {

        viewHolder.tv_laporan.setText(datalist.get(i).getLaporan());
        viewHolder.et_nama_laporan.setText(datalist.get(i).getNama_laporan());
    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_laporan;
        private EditText et_nama_laporan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_laporan = itemView.findViewById(R.id.tv_laporan);
            et_nama_laporan = itemView.findViewById(R.id.et_nama_laporan);
        }
    }
}
