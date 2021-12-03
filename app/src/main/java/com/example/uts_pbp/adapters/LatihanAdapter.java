package com.example.uts_pbp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uts_pbp.AddEditActivity;
import com.example.uts_pbp.LatihanActivity;
import com.example.uts_pbp.MainActivity;
import com.example.uts_pbp.R;
import com.example.uts_pbp.model.Latihan;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LatihanAdapter extends RecyclerView.Adapter<LatihanAdapter.ViewHolder>
        implements Filterable {

    private List<Latihan> latihanList, filteredLatihanList;
    private Context context;

    public LatihanAdapter(List<Latihan> latihanList, Context context) {
        this.latihanList = latihanList;
        filteredLatihanList = new ArrayList<>(latihanList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_latihan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Latihan latihan = filteredLatihanList.get(position);

        holder.tvHuruf.setText(latihan.getAlfabet());
        holder.tvKata.setText(latihan.getNama());

        Glide.with(context)
                .load(latihan.getUrlgambar())
                .placeholder(R.drawable.tanpagambar)
                .into(holder.ivGambar);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data latihan ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (context instanceof LatihanActivity)
                                    ((LatihanActivity) context).deleteProduk(latihan.getId());
                            }
                        })
                        .show();
            }
        });

        holder.cvProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditActivity.class);
                i.putExtra("id", latihan.getId());

                if (context instanceof LatihanActivity)
                    ((LatihanActivity) context).startActivityForResult(i, LatihanActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredLatihanList.size();
    }


    public void setLatihanList(List<Latihan> latihanList) {
        this.latihanList = latihanList;
        filteredLatihanList = new ArrayList<>(latihanList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Latihan> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(latihanList);
                } else {
                    for (Latihan latihan : latihanList) {
                        if (latihan.getNama().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(latihan);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredLatihanList.clear();
                filteredLatihanList.addAll((List<Latihan>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHuruf, tvKata;
        ImageView ivGambar;
        ImageButton btnDelete;
        CardView cvProduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHuruf = itemView.findViewById(R.id.tv_huruf);
            tvKata = itemView.findViewById(R.id.tv_kata);
            ivGambar = itemView.findViewById(R.id.iv_gambar);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            cvProduk = itemView.findViewById(R.id.cv_produk);
        }
    }
}
