package com.example.retrofitconnection.config;

import com.example.retrofitconnection.service.CursoService;
import com.example.retrofitconnection.service.DepartamentoService;
import com.example.retrofitconnection.service.ProfessorService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://professor-allocation.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ProfessorService getProfessorService() {
        return retrofit.create(ProfessorService.class);
    }

    public DepartamentoService getDepartamentoService(){
        return retrofit.create(DepartamentoService.class);
    }

    public CursoService getCursoService(){
        return retrofit.create(CursoService.class);
    }
}
