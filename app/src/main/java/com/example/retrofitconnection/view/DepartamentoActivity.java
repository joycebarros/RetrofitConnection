package com.example.retrofitconnection.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

       // departamentoAdapter = new DepartamentoAdapter(this, new ArrayList<Departamento>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // recyclerView.setAdapter(departamentoAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllDepartamentos(new ResultEventDepartamentoInterface() {
            @Override
            public void onResult(List<Departamento> departamentos) {
                List<Departamento> dList;
                if(departamentos.isEmpty()){
                    dList = dbInstance.departamentoDAO().getAll();
                }else{
                    dList = departamentos;
                }

                departamentoAdapter = new DepartamentoAdapter(DepartamentoActivity.this, dList);
                recyclerView.setAdapter(departamentoAdapter);
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(DepartamentoActivity.this, message, Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(DepartamentoActivity.this, CreateDepartamentoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
