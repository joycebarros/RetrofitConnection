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
import com.example.retrofitconnection.model.Curso;
import com.example.retrofitconnection.view.UpdateCourseActivity;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoHolder> {

    private Context context;
    private final LayoutInflater mInflater;
    private List<Curso> cursos;
    public final static String ITEM_ID_EXTRA = "CursoID";

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

        ImageView imageView;
        TextView textView;
        ConstraintLayout parentLayout;

        public CursoHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_curso);
            textView = itemView.findViewById(R.id.tv_curso_nome);
            parentLayout = itemView.findViewById(R.id.parent_container);

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateCourseActivity.class);
                    Curso curso =  cursos.get(getAdapterPosition());
                    intent.putExtra(ITEM_ID_EXTRA, curso);
                    context.startActivity(intent);
                }
            });
        }
    }
}
