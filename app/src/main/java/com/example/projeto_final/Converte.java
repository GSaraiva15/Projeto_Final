package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {
    public static ContentValues doenteToContentValues(Doentes doentes) {
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaRegistoDoentes.NOME_DOENTE, doentes.getNome_doente());
        valores.put(BdTabelaRegistoDoentes.NASCIMENTO_DOENTE, doentes.getNascimento_doente());
        valores.put(BdTabelaRegistoDoentes.TELEMOVEL_DOENTE, doentes.getTelemovel_doente());
        valores.put(BdTabelaRegistoDoentes.CONCELHO_DOENTE, doentes.getConcelho_doente());
        valores.put(BdTabelaRegistoDoentes.SEXO_DOENTE, doentes.getSexo_doente());
        valores.put(BdTabelaRegistoDoentes.CRONICO_DOENTE, doentes.getCronico_doente());
        valores.put(BdTabelaRegistoDoentes.ESTADO_DOENTE, doentes.getEstado_doente());
        return valores;
    }

    public static Doentes contentValuesToDoentes(ContentValues valores) {
        Doentes doentes = new Doentes();
        doentes.setId(valores.getAsLong(BdTabelaRegistoDoentes._ID));
        doentes.setNome_doente(valores.getAsString(BdTabelaRegistoDoentes.NOME_DOENTE));
        doentes.setTelemovel_doente(valores.getAsString(BdTabelaRegistoDoentes.TELEMOVEL_DOENTE));
        doentes.setConcelho_doente(valores.getAsString(BdTabelaRegistoDoentes.CONCELHO_DOENTE));
        doentes.setSexo_doente(valores.getAsString(BdTabelaRegistoDoentes.SEXO_DOENTE));
        doentes.setCronico_doente(valores.getAsString(BdTabelaRegistoDoentes.CRONICO_DOENTE));
        doentes.setEstado_doente(valores.getAsString(BdTabelaRegistoDoentes.ESTADO_DOENTE));
        return doentes;
    }
    public static Doentes cursorToPaciente(Cursor cursor){
        Doentes doentes = new Doentes();
        doentes.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaRegistoDoentes._ID)));
        doentes.setNome_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.NOME_DOENTE)));
        doentes.setNascimento_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.NASCIMENTO_DOENTE)));
        doentes.setTelemovel_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.TELEMOVEL_DOENTE)));
        doentes.setConcelho_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.CONCELHO_DOENTE)));
        doentes.setSexo_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.SEXO_DOENTE)));
        doentes.setCronico_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.CRONICO_DOENTE)));
        doentes.setEstado_doente(cursor.getString(cursor.getColumnIndex(BdTabelaRegistoDoentes.ESTADO_DOENTE)));
        return doentes;
    }
}