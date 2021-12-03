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
import com.example.uts_pbp.NoteActivity;
import com.example.uts_pbp.NoteAddEditActivity;
import com.example.uts_pbp.R;
import com.example.uts_pbp.model.Latihan;
import com.example.uts_pbp.model.Note;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>
        implements Filterable {

    private List<Note> noteList, filteredNoteList;
    private Context context;

    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        filteredNoteList = new ArrayList<>(noteList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = filteredNoteList.get(position);

        holder.tvHuruf.setText(note.getTitle());
        holder.tvKata.setText(note.getPesan());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data note ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (context instanceof NoteActivity)
                                    ((NoteActivity) context).deleteProduk(note.getId());
                            }
                        })
                        .show();
            }
        });

        holder.cvProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, NoteAddEditActivity.class);
                i.putExtra("id", note.getId());

                if (context instanceof NoteActivity)
                    ((NoteActivity) context).startActivityForResult(i, NoteActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredNoteList.size();
    }


    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        filteredNoteList = new ArrayList<>(noteList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Note> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(noteList);
                } else {
                    for (Note note : noteList) {
                        if (note.getTitle().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(note);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredNoteList.clear();
                filteredNoteList.addAll((List<Note>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHuruf, tvKata;
        ImageButton btnDelete;
        CardView cvProduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHuruf = itemView.findViewById(R.id.tv_huruf);
            tvKata = itemView.findViewById(R.id.tv_kata);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            cvProduk = itemView.findViewById(R.id.cv_produk);
        }
    }
}
