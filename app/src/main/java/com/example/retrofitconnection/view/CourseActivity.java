package com.example.retrofitconnection.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import com.example.retrofitconnection.R;
import com.example.retrofitconnection.adapter.CursoAdapter;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Curso;
import com.example.retrofitconnection.repository.ResultEventCurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CursoAdapter cursoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        recyclerView = findViewById(R.id.rv_cursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllCursos(new ResultEventCurso() {
            @Override
            public void onResult(List<Curso> cursos) {
                cursoAdapter = new CursoAdapter(CourseActivity.this, cursos);
                recyclerView.setAdapter(cursoAdapter);
            }

            @Override
            public void onFail(String mensagem) {
                Toast.makeText(CourseActivity.this, mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllCursos(final ResultEventCurso resultEventCurso){
        Call<List<Curso>> call = new RetrofitConfig().getCursoService().getAllCurso();
        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                List<Curso> cursoLista = response.body();
                resultEventCurso.onResult(cursoLista);
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                resultEventCurso.onFail("Falha na requisição!");
            }
        });
    }
}