package com.example.retrofitconnection.service;

import androidx.room.Query;

import com.example.retrofitconnection.model.Curso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CursoService {

    @GET("course")
    Call<List<Curso>> getAllCurso();

}
