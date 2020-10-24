package com.example.retrofitconnection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.model.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoHolder> {

    private Context context;
    private final LayoutInflater mInflater;
    private List<Curso> cursos;

    public CursoAdapter(Context context, List<Curso> cursos) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public CursoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_curso, parent, false);
        return new CursoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder.textView.setText(curso.getName());
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public class CursoHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public CursoHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_curso_nome);
        }
    }
}
