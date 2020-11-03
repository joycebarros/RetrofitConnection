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
import com.example.retrofitconnection.model.Professor;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorHolder> {

    private List<Professor> professores;
    private final LayoutInflater mInflater;

    public ProfessorAdapter(Context context, List<Professor> professores) {
        this.professores = professores;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProfessorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Cria referencia de Layout

        View itemView = mInflater.inflate(R.layout.layout_professor, parent, false);

        return new ProfessorHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorHolder holder, int position) {

        // Pega os dados da lista e joga na tela.
        holder.textView.setText(professores.get(position).getName());

    }

    @Override
    public int getItemCount() {
        // Retorna para o adaptador quantos itens tem na lista
        return professores.size();
    }

    public class ProfessorHolder extends RecyclerView.ViewHolder {
        //Gerenciador de itens/componenetes do XML. Exemplo: faz o link do TextView do XML com o do Java

        ImageView imageView;
        TextView textView;

        public ProfessorHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_professor);
            textView = itemView.findViewById(R.id.professor_name);
        }
    }
}
