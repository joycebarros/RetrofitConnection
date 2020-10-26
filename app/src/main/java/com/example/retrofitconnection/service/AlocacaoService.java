package com.example.retrofitconnection.service;

import com.example.retrofitconnection.model.Alocacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlocacaoService {

    @GET("allocation")
    Call<List<Alocacao>> getAllAlocacao();
}
