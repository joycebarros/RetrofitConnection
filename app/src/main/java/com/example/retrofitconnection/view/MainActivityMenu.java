package com.example.retrofitconnection.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.retrofitconnection.R;

public class MainActivityMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button b1 = findViewById(R.id.bt_professor);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMenu.this, ProfessorActivity.class);
                startActivity(intent);
            }
        });

        Button b2 = findViewById(R.id.bt_departamento);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMenu.this, DepartamentoActivity.class);
                startActivity(intent);
            }
        });

        Button b3 = findViewById(R.id.bt_curso);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMenu.this, CourseActivity.class);
                startActivity(intent);
            }
        });
    }
}