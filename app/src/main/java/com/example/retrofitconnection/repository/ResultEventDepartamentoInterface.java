package com.example.retrofitconnection.repository;

import com.example.retrofitconnection.model.Departamento;

import java.util.List;

public interface ResultEventDepartamentoInterface {

    void onResult(List<Departamento> departamentos);
    void onFail (String message);
}
