package com.example.retrofitconnection.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.view.UpdateDepartamentoActivity;

import java.util.List;

public class DepartamentoAdapter extends RecyclerView.Adapter<DepartamentoAdapter.DepartamentoHolder> {

    private Context context;
    private List<Departamento> departamentos;
    private final LayoutInflater mInflater;
    public final static String ITEM_ID_EXTRA = "DepartamentoID";

    public DepartamentoAdapter(Context context, List<Departamento> departamentos) {
        this.context = context;
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
        ConstraintLayout parentLayout;

        public DepartamentoHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_departamento);
            textView = itemView.findViewById(R.id.departamento_name);
            parentLayout = itemView.findViewById(R.id.parent_container);

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateDepartamentoActivity.class);
                    Departamento departamento =  departamentos.get(getAdapterPosition());
                    intent.putExtra(ITEM_ID_EXTRA, departamento);
                    context.startActivity(intent);
                }
            });
        }
    }
}
