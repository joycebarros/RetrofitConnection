package com.example.retrofitconnection.service;

import com.example.retrofitconnection.model.Professor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfessorService {

    @GET("professor")
    Call<List<Professor>> getAllProfessors();

    @GET("professor")
    Call<Professor> getProfessor(@Query("id") int id);

    @DELETE("professor")
    Call<Professor> delete(@Query("id") int id);

    @POST("professor")
    Call<Professor> create(@Body Professor professor);

}
