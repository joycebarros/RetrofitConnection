package com.example.retrofitconnection.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.retrofitconnection.R;
import com.example.retrofitconnection.adapter.CursoAdapter;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.config.RoomConfig;
import com.example.retrofitconnection.model.Curso;
import com.example.retrofitconnection.repository.ResultEventCurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CursoAdapter cursoAdapter;
    private RoomConfig dbInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        dbInstance = RoomConfig.getInstance(this);

        recyclerView = findViewById(R.id.rv_cursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllCursos(new ResultEventCurso() {
            @Override
            public void onResult(List<Curso> cursos) {
                List<Curso> listaCurso = dbInstance.cursoDAO().getAll();

                cursoAdapter = new CursoAdapter(CourseActivity.this, listaCurso);
                recyclerView.setAdapter(cursoAdapter);
            }

            @Override
            public void onFail(String mensagem) {
                Toast.makeText(CourseActivity.this, mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barra_menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_icone:
                Intent intent = new Intent(CourseActivity.this, CreateCourseActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getAllCursos(final ResultEventCurso resultEventCurso){
        Call<List<Curso>> call = new RetrofitConfig().getCursoService().getAllCurso();
        call.enqueue(new Callback<List<Curso>>() {
            @Override
            public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
                List<Curso> cursoLista = response.body();
                dbInstance.cursoDAO().insertAll(cursoLista);
                resultEventCurso.onResult(cursoLista);
            }

            @Override
            public void onFailure(Call<List<Curso>> call, Throwable t) {
                resultEventCurso.onFail("Falha na requisição!");
            }
        });
    }
}