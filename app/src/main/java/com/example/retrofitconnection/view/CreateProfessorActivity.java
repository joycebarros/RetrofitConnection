package com.example.retrofitconnection.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.config.RoomConfig;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.model.Professor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProfessorActivity extends Activity {

    private Professor professor;
    private EditText campoNome;
    private EditText campoCPF;
    private Departamento departamento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_professor);

        Button btEnviar = findViewById(R.id.bt_enviar);
        Button btCancelar = findViewById(R.id.bt_cancelar);
        campoNome = findViewById(R.id.ed_professor_name);
        campoCPF = findViewById(R.id.ed_professsor_cpf);
        final Spinner spinner = findViewById(R.id.spinner);

        List<Departamento> departamentosLista = RoomConfig.getInstance(this).departamentoDAO().getAll();
        ArrayAdapter<Departamento> adapterSpinner = new ArrayAdapter<Departamento>(CreateProfessorActivity.this, android.R.layout.simple_list_item_1, departamentosLista);

        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departamento= (Departamento) spinner.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                finish();
            }
        });
    }

    private void createProfessor(Professor professor) {

        professor = new Professor();
        String nome = campoNome.getText().toString();
        String cpf = campoCPF.getText().toString();
        professor.setName(nome);
        professor.setCpf(cpf);
        professor.setDepartament(departamento);

        Call<Professor> call = new RetrofitConfig().getProfessorService().create(professor);

        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {

                if (response.isSuccessful()) {
                    Professor professor = response.body();
                    Toast.makeText(CreateProfessorActivity.this, "Sucesso ao criar o professor!!!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CreateProfessorActivity.this, "Erro no sucesso", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Toast.makeText(CreateProfessorActivity.this, "Falha ao criar o Professsor!!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
