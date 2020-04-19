package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void inserirDoente (View view){

        Intent intent = new Intent(this,DisplayInserirDoentes.class);
    }
    public void verEstatisticas (View view){

        Intent intent2 = new Intent(this,DisplayVerEstatisticas.class);
    }
}
