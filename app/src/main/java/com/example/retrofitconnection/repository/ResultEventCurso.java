package com.example.retrofitconnection.repository;

import com.example.retrofitconnection.model.Curso;

import java.util.List;

public interface ResultEventCurso {

    public void onResult(List<Curso> cursos);

    public void onFail(String mensagem);
}
