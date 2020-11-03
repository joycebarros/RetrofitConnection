package com.example.retrofitconnection.service;

import com.example.retrofitconnection.model.Curso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CursoService {

    @GET("course")
    Call<List<Curso>> getAllCurso();

    @POST("course")
    Call<Curso> create(@Body Curso curso);

    @DELETE("course/{id}")
    Call<Curso> delete(@Path("id") int id);

    @PUT("course/{id}")
    Call<Curso> update(@Path("id") int id, @Body Curso curso);

}
