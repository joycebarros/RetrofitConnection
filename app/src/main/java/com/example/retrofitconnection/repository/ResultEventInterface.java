package com.example.retrofitconnection.repository;

import com.example.retrofitconnection.model.Professor;

import java.util.List;

public interface ResultEventInterface {

    void onResult(List<Professor> professors);
    void onFail(String message);


}
