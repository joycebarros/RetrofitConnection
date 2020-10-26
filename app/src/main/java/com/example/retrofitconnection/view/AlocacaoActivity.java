package com.example.retrofitconnection.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.adapter.AlocacaoAdapter;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.config.RoomConfig;
import com.example.retrofitconnection.model.Alocacao;
import com.example.retrofitconnection.repository.ResultEventAlocacao;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlocacaoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlocacaoAdapter alocacaoAdapter;
    private RoomConfig dbInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alocacao);

        dbInstance = RoomConfig.getInstance(this);

        recyclerView = findViewById(R.id.rv_alocacao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllAlocacao(new ResultEventAlocacao() {
            @Override
            public void onResult(List<Alocacao> alocacaos) {
                List<Alocacao> listaAlocacao = dbInstance.alocacaoDAO().getAll();
                alocacaoAdapter = new AlocacaoAdapter(AlocacaoActivity.this, listaAlocacao);
                recyclerView.setAdapter(alocacaoAdapter);

            }

            @Override
            public void onFail(String mensagem) {
                Toast.makeText(AlocacaoActivity.this, mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllAlocacao(final ResultEventAlocacao resultEventAlocacao){

        Call<List<Alocacao>> call = new RetrofitConfig().getAlocacaoService().getAllAlocacao();
        call.enqueue(new Callback<List<Alocacao>>() {
            @Override
            public void onResponse(Call<List<Alocacao>> call, Response<List<Alocacao>> response) {
                List<Alocacao> alocacaoLista = response.body();
                dbInstance.alocacaoDAO().insertAll(alocacaoLista);
                resultEventAlocacao.onResult(alocacaoLista);
            }

            @Override
            public void onFailure(Call<List<Alocacao>> call, Throwable t) {
                resultEventAlocacao.onFail("Falha na requisição!");
            }
        });

    }
}