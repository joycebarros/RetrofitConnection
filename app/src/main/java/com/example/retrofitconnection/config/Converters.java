package com.example.retrofitconnection.config;

import androidx.room.TypeConverter;

import com.example.retrofitconnection.model.Departamento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Converters {

    private static ObjectMapper objectMapper;

    @TypeConverter
    public static String listToJson(Departamento value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
        // Converter um tipo em uma string
    }

    @TypeConverter
    public static Departamento jsonToList(String value) {
        objectMapper = new ObjectMapper();
        Departamento arr = new Departamento();
        try {
            arr = objectMapper.readValue(value, Departamento.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
        //Converter uma string em um tipo
    }
}
