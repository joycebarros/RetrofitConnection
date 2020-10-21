package com.example.retrofitconnection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitconnection.model.Departamento;

import java.util.List;

public class DepartamentoAdapter extends RecyclerView.Adapter<DepartamentoAdapter.DepartamentoHolder> {

    private List<Departamento> departamentos;
    private final LayoutInflater mInflater;

    public DepartamentoAdapter(Context context, List<Departamento> departamentos) {
        this.departamentos = departamentos;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DepartamentoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.item_departamento_view, parent, false);

        return new DepartamentoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartamentoHolder holder, int position) {

        holder.textView.setText(departamentos.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return departamentos.size();
    }

    public class DepartamentoHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public DepartamentoHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_departamento);
            textView = itemView.findViewById(R.id.departamento_name);
        }
    }
}
