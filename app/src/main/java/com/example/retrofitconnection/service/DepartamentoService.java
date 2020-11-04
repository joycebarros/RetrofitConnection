package com.example.retrofitconnection.service;

import com.example.retrofitconnection.model.Departamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentoService {

    @GET("departament")
    Call<List<Departamento>> getAllDepartamentos();

    @GET("departament/{id}")
    Call<Departamento> getDepartamento(@Path("id") int id);

    @DELETE("departament/{id}")
    Call<Departamento> delete(@Path("id") int id);

    @POST("departament")
    Call<Departamento> create(@Body Departamento departamento);

    @PUT("departament/{id}")
    Call<Departamento> update(@Path("id") int id, @Body Departamento departamento);
}
