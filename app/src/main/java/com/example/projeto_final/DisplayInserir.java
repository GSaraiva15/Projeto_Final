package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DisplayInserir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_inserir);
    }
    public void inserirDoente (View view){

        Intent intentInserirDoente = new Intent(this,DisplayInserirDoentes.class);
        startActivity(intentInserirDoente);
    }
    public void inserirTestes (View view){

        Intent intentInserirTestes = new Intent(this,DisplayInserirTestes.class);
        startActivity(intentInserirTestes);
    }
}
