package com.example.retrofitconnection.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitconnection.adapter.CursoAdapter;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Curso;
import com.example.retrofitconnection.repository.ResultEventCurso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.retrofitconnection.R.*;

public class UpdateCourseActivity extends AppCompatActivity {

    private Curso curso;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_update_course);

        curso = (Curso) getIntent().getSerializableExtra(CursoAdapter.ITEM_ID_EXTRA);

        Button btEnviar = findViewById(id.bt_enviar);
        Button btCancelar = findViewById(id.bt_cancelar);
        Button btExcluir = findViewById(id.bt_excluir);
        editText = findViewById(id.ed_curso_name);
        editText.setText(curso.getName());

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString().trim();

                if(!curso.getName().equalsIgnoreCase(text)){
                    curso.setName(text);
                    updateCourse(curso.getId(), curso, new ResultEventCurso() {
                        @Override
                        public void onResult(List<Curso> cursos) {
                            finish();
                        }
                        @Override
                        public void onFail(String mensagem) {
                            Toast.makeText(UpdateCourseActivity.this, "Falha!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirCurso(curso.getId(), new ResultEventCurso() {
                    @Override
                    public void onResult(List<Curso> cursos) {
                       finish();
                    }

                    @Override
                    public void onFail(String mensagem) {
                        Toast.makeText(UpdateCourseActivity.this, "Falha!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateCourse(int id, Curso curso, final ResultEventCurso resultEventCurso) {
        Call<Curso> call = new RetrofitConfig().getCursoService().update(id, curso);

        call.enqueue(new Callback<Curso>() {
            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {
                if(response.isSuccessful()){
                    Curso curso = response.body();
                    Toast.makeText(UpdateCourseActivity.this, "Curso atualizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(UpdateCourseActivity.this, "Falha no sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {
                Toast.makeText(UpdateCourseActivity.this, "Falha na atualização!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void excluirCurso(int id, ResultEventCurso resultEventCurso){
        Call<Curso> call = new RetrofitConfig().getCursoService().delete(id);

        call.enqueue(new Callback<Curso>() {
            @Override
            public void onResponse(Call<Curso> call, Response<Curso> response) {
                if(response.isSuccessful()){
                    Curso curso = response.body();
                    Toast.makeText(UpdateCourseActivity.this, "Curso excluído com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(UpdateCourseActivity.this, "Falha no sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Curso> call, Throwable t) {
                Toast.makeText(UpdateCourseActivity.this, "Falha na exclusão!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}