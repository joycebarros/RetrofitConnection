package com.example.retrofitconnection.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitconnection.R;
import com.example.retrofitconnection.adapter.DepartamentoAdapter;
import com.example.retrofitconnection.config.RetrofitConfig;
import com.example.retrofitconnection.model.Departamento;
import com.example.retrofitconnection.repository.ResultEventDepartamentoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDepartamentoActivity extends AppCompatActivity {

    private Departamento departamento;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_departamento);

        departamento = (Departamento) getIntent().getSerializableExtra(DepartamentoAdapter.ITEM_ID_EXTRA);

        Button btEnviar = findViewById(R.id.bt_enviar);
        Button btCancelar = findViewById(R.id.bt_cancelar);
        Button btExcluir = findViewById(R.id.bt_excluir);
        editText = findViewById(R.id.ed_departamento_name);
        editText.setText(departamento.getName());

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString().trim();

                if(!departamento.getName().equalsIgnoreCase(text)){
                    departamento.setName(text);
                    updateDepartamento(departamento.getId(), departamento, new ResultEventDepartamentoInterface() {
                        @Override
                        public void onResult(List<Departamento> departamentos) {
                            finish();
                        }
                        @Override
                        public void onFail(String mensagem) {
                            Toast.makeText(UpdateDepartamentoActivity.this, "Falha!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirDepartamento(departamento.getId(), new ResultEventDepartamentoInterface() {
                    @Override
                    public void onResult(List<Departamento> departamentos) {
                        finish();
                    }

                    @Override
                    public void onFail(String mensagem) {
                        Toast.makeText(UpdateDepartamentoActivity.this, "Falha!", Toast.LENGTH_LONG).show();
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

    private void updateDepartamento(int id, Departamento departamento, final ResultEventDepartamentoInterface resultEventDepartamentoInterface) {
        Call<Departamento> call = new RetrofitConfig().getDepartamentoService().update(id, departamento);

        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Departamento departamento1 = response.body();
                    Toast.makeText(UpdateDepartamentoActivity.this, "Departamento atualizado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(UpdateDepartamentoActivity.this, "Falha no sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Toast.makeText(UpdateDepartamentoActivity.this, "Falha na atualização!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void excluirDepartamento(int id, ResultEventDepartamentoInterface resultEventDepartamentoInterface){
        Call<Departamento> call = new RetrofitConfig().getDepartamentoService().delete(id);

        call.enqueue(new Callback<Departamento>() {
            @Override
            public void onResponse(Call<Departamento> call, Response<Departamento> response) {
                if(response.isSuccessful()){
                    Departamento departamento = response.body();
                    Toast.makeText(UpdateDepartamentoActivity.this, "Departamento excluído com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(UpdateDepartamentoActivity.this, "Falha no sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Departamento> call, Throwable t) {
                Toast.makeText(UpdateDepartamentoActivity.this, "Falha na exclusão!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}