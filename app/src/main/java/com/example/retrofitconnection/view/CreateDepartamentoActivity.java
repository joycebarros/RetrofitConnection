package com.example.retrofitconnection.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Departamento;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDepartamentoActivity extends AppCompatActivity {

    private Departamento departamento;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_departamento);

        Button btEnviar = findViewById(R.id.bt_enviar);
        Button btCancelar = findViewById(R.id.bt_cancelar);
        editText = findViewById(R.id.ed_departamento_name);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDepartamento(departamento);
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void createDepartamento(Departamento departamento) {
        departamento = new Departamento();
        String text = editText.getText().toString().trim();
        departamento.setName(text);
        Call<Departamento> call = new RetrofitConfig().getDepartamentoService().create(departamento);

        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Departamento departamento1 = response.body();
                    Toast.makeText(CreateDepartamentoActivity.this, "Sucesso ao criar o departamento", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(CreateDepartamentoActivity.this, "Erro no Sucesso", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Toast.makeText(CreateDepartamentoActivity.this, "Falha ao criar o departamento", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}