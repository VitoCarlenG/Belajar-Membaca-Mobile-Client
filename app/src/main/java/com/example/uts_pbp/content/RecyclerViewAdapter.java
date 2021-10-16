package com.example.uts_pbp.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts_pbp.databinding.RecyclerViewBinding;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    private Context context;
    private ArrayList<DataAlphabet> dataAlphabetsList;
    RecyclerViewBinding binding;

    public RecyclerViewAdapter(Context context, ArrayList<DataAlphabet> dataAlphabetsList) {
        this.context = context;
        this.dataAlphabetsList = dataAlphabetsList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RecyclerViewBinding.inflate(inflater,
                parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.viewHolder holder, int position) {
        DataAlphabet dataAlphabet = dataAlphabetsList.get(position);
        holder.binding.setAlpha(dataAlphabet);
    }

    @Override
    public int getItemCount() {
        return dataAlphabetsList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RecyclerViewBinding binding;

        public viewHolder(@NonNull RecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

