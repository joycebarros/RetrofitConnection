package com.example.retrofitconnection.service;

import com.example.retrofitconnection.model.Departamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DepartamentoService {

    @GET("departament")
    Call<List<Departamento>> getAllDepartamentos();

    @GET("departament")
    Call<Departamento> getDepartamento(@Query("id") int id);

    @DELETE("departament")
    Call<Departamento> delete(@Query("id") int id);

    @POST("departament")
    Call<Departamento> create(@Body Departamento departamento);
}
