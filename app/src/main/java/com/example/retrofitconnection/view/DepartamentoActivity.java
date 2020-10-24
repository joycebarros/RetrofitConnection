package com.example.retrofitconnection.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.adapter.DepartamentoAdapter;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.config.RoomConfig;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.repository.ResultEventDepartamentoInterface;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartamentoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DepartamentoAdapter departamentoAdapter;
    private RoomConfig dbInstance;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_departamento);

        dbInstance = RoomConfig.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView2);

        departamentoAdapter = new DepartamentoAdapter(this, new ArrayList<Departamento>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(departamentoAdapter);

        //createDepartamento();

        getAllDepartamentos(new ResultEventDepartamentoInterface() {
            @Override
            public void onResult(List<Departamento> departamentos) {
                List<Departamento> dList = dbInstance.departamentoDAO().getAll();

                departamentoAdapter = new DepartamentoAdapter(DepartamentoActivity.this, dList);
                recyclerView.setAdapter(departamentoAdapter);
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(DepartamentoActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void createDepartamento() {
        Departamento departamento = new Departamento("ABC");

        Call<Departamento> call = new RetrofitConfig().getDepartamentoService().create(departamento);

        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if (response.isSuccessful()) {
                    Departamento departamento1 = response.body();
                    Toast.makeText(DepartamentoActivity.this, "Sucesso ao criar o departamento!!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DepartamentoActivity.this, "Erro no sucesso", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Toast.makeText(DepartamentoActivity.this, "Falha ao criar o departamento", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAllDepartamentos(final ResultEventDepartamentoInterface resultEventDepartamentoInterface) {
        Call<List<Departamento>> call = new RetrofitConfig().getDepartamentoService().getAllDepartamentos();

        call.enqueue(new Callback<List<Departamento>>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                List<Departamento> departamentoList = response.body();

                dbInstance.departamentoDAO().insertAll(departamentoList);
                resultEventDepartamentoInterface.onResult(departamentoList);

            }

            @Override
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                resultEventDepartamentoInterface.onFail("Falha na requisição!!!");
            }
        });
    }
}
