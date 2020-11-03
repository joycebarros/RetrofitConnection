package com.example.retrofitconnection.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Curso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCourseActivity extends AppCompatActivity {

    private Curso curso;
    private EditText editText;
    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_curso);

        Button btEnviar = findViewById(R.id.bt_enviar);
        Button btCancelar = findViewById(R.id.bt_cancelar);
        editText = findViewById(R.id.ed_curso_name);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCurso(curso);
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCourseActivity.this, CourseActivity.class);
                startActivity(intent);
            }
        });
    }


    private void createCurso(Curso pcurso) {
        pcurso = new Curso();
        String text = editText.getText().toString().trim();
        pcurso.setName(text);
        Call<Curso> call = new RetrofitConfig().getCursoService().create(pcurso);

        intent = new Intent(CreateCourseActivity.this, CourseActivity.class);

        call.enqueue(new Callback<Curso>() {
            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {
                if(response.isSuccessful()){
                    Curso curso = response.body();
                    curso.toString();
                    Toast.makeText(CreateCourseActivity.this, "Sucesso ao criar o curso", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }else {
                    Toast.makeText(CreateCourseActivity.this, "Erro no Sucesso", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {
                Toast.makeText(CreateCourseActivity.this, "Falha ao criar o curso", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }
}
