package com.example.retrofitconnection.repository;

import com.example.retrofitconnection.model.Alocacao;

import java.util.List;

public interface ResultEventAlocacao {

    public void onResult(List<Alocacao> alocacaos);

    public void onFail(String mensagem);
}
