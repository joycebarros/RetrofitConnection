package com.example.retrofitconnection.config;

import androidx.room.TypeConverter;

import com.example.retrofitconnection.model.Curso;
import com.example.retrofitconnection.model.Professor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Converters2 {

    private static ObjectMapper objectMapper;

    @TypeConverter
    public static String listToJson2(Professor value) {
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
    public static Professor jsonToList2(String value) {
        objectMapper = new ObjectMapper();
        Professor arr = new Professor();
        try {
            arr = objectMapper.readValue(value, Professor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
        //Converter uma string em um tipo
    }

    @TypeConverter
    public static String listToJson3(Curso value) {
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
    public static Curso jsonToList3(String value) {
        objectMapper = new ObjectMapper();
        Curso arr = new Curso();
        try {
            arr = objectMapper.readValue(value, Curso.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
        //Converter uma string em um tipo
    }
}
