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

        Intent intentInserir = new Intent(this,DisplayInserirDoentes.class);
        startActivity(intentInserir);
    }
    public void verEstatisticas (View view){

        Intent intentEstatistica = new Intent(this,DisplayVerEstatisticas.class);
        startActivity(intentEstatistica);
    }
    public void verMapa (View view){
        Intent intentMapa = new Intent(this,DisplayMapa.class);
        startActivity(intentMapa);
    }
}
