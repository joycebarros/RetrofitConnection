package com.example.retrofitconnection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.model.Alocacao;

import java.util.List;

public class AlocacaoAdapter extends RecyclerView.Adapter<AlocacaoAdapter.AlocacaoHolder>{
    private Context context;
    private List<Alocacao> alocacaos;
    private final LayoutInflater minflater;

    public AlocacaoAdapter(Context context, List<Alocacao> alocacaos) {
        this.context = context;
        this.alocacaos = alocacaos;
        this.minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AlocacaoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.activity_alocacao, parent, false);
        return new AlocacaoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlocacaoHolder holder, int position) {
        Alocacao alocacao = alocacaos.get(position);
        holder.textView.setText(alocacao.getDayOfWeek());
    }

    @Override
    public int getItemCount() {
        return alocacaos.size();
    }

    public class AlocacaoHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public AlocacaoHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_alocacao);
            textView = itemView.findViewById(R.id.tv_alocacao_nome);
        }
    }
}
