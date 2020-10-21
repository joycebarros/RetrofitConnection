package com.example.retrofitconnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.model.Professor;
import com.example.retrofitconnection.repository.ResultEventInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProfessorAdapter professorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        professorAdapter = new ProfessorAdapter(this, new ArrayList<Professor>());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Caso vc queira um Grid user o GridLayoutManager
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(professorAdapter);

       // createProfessor();

        getAllProfessors(new ResultEventInterface() {
            @Override
            public void onResult(List<Professor> professors) {
                //Quando houver resultado, mostre os valores na tela!

                professorAdapter = new ProfessorAdapter(MainActivity.this, professors);
                recyclerView.setAdapter(professorAdapter);
            }

            @Override
            public void onFail(String message) {
                // Quando houver falha, Exiba uma mensagem de erro!
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void createProfessor() {
        Departamento departamento = new Departamento(271, "");
        Professor p1 = new Professor("Joyce Barros", "044555666", departamento);

        Call<Professor> call = new RetrofitConfig().getProfessorService().create(p1);

        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {

                if (response.isSuccessful()) {
                    Professor professor = response.body();
                    Toast.makeText(MainActivity.this, "Sucesso ao criar o professor!!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erro no sucesso", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha ao criar o Professsor!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAllProfessors(final ResultEventInterface resultEventInterface) {
        Call<List<Professor>> call = new RetrofitConfig().getProfessorService().getAllProfessors();

        call.enqueue(new Callback<List<Professor>>() {
            @Override
            public void onResponse(Call<List<Professor>> call, Response<List<Professor>> response) {
                List<Professor> professorsList = response.body();

                resultEventInterface.onResult(professorsList);
            }

            @Override
            public void onFailure(Call<List<Professor>> call, Throwable t) {
                resultEventInterface.onFail("Falha na requisição!!!");
            }
        });
    }
}