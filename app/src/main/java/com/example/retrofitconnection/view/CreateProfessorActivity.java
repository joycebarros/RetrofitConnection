package com.example.retrofitconnection.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.model.Professor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProfessorActivity extends Activity {

    private Professor professor;
    private EditText campoNome;
    private EditText campoCPF;
    private Spinner spinnerDepartamento;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_professor);

        Button btEnviar = findViewById(R.id.bt_enviar);
        Button btCancelar = findViewById(R.id.bt_cancelar);
        campoNome = findViewById(R.id.ed_professor_name);
        campoCPF = findViewById(R.id.ed_professsor_cpf);
        spinnerDepartamento = findViewById(R.id.spinner);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfessor(professor);
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProfessorActivity.this, ProfessorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createProfessor(Professor professor) {
        Departamento departamento = new Departamento(271, "");
        professor = new Professor();
        String nome = campoNome.getText().toString();
        String cpf = campoCPF.getText().toString();
        professor.setName(nome);
        professor.setCpf(cpf);

        Call<Professor> call = new RetrofitConfig().getProfessorService().create(professor);

        intent = new Intent(CreateProfessorActivity.this, ProfessorActivity.class);

        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {

                if (response.isSuccessful()) {
                    Professor professor = response.body();
                    Toast.makeText(CreateProfessorActivity.this, "Sucesso ao criar o professor!!!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateProfessorActivity.this, "Erro no sucesso", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Toast.makeText(CreateProfessorActivity.this, "Falha ao criar o Professsor!!!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}
