package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {
    public static ContentValues doenteToContentValues(Doentes doentes) {
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaDoentes.NOME_DOENTE, doentes.getNome_doente());
        valores.put(BdTabelaDoentes.NASCIMENTO_DOENTE, doentes.getNascimento_doente());
        valores.put(BdTabelaDoentes.TELEMOVEL_DOENTE, doentes.getTelemovel_doente());
        valores.put(BdTabelaDoentes.CONCELHO_DOENTE, doentes.getConcelho_doente());
        valores.put(BdTabelaDoentes.SEXO_DOENTE, doentes.getSexo_doente());
        valores.put(BdTabelaDoentes.CRONICO_DOENTE, doentes.getCronico_doente());
        valores.put(BdTabelaDoentes.ESTADO_DOENTE, doentes.getEstado_doente());
        return valores;
    }

    public static Doentes contentValuesToDoentes(ContentValues valores) {
        Doentes doentes = new Doentes();
        doentes.setId(valores.getAsLong(BdTabelaDoentes._ID));
        doentes.setNome_doente(valores.getAsString(BdTabelaDoentes.NOME_DOENTE));
        doentes.setTelemovel_doente(valores.getAsString(BdTabelaDoentes.TELEMOVEL_DOENTE));
        doentes.setConcelho_doente(valores.getAsString(BdTabelaDoentes.CONCELHO_DOENTE));
        doentes.setSexo_doente(valores.getAsString(BdTabelaDoentes.SEXO_DOENTE));
        doentes.setCronico_doente(valores.getAsString(BdTabelaDoentes.CRONICO_DOENTE));
        doentes.setEstado_doente(valores.getAsString(BdTabelaDoentes.ESTADO_DOENTE));
        return doentes;
    }
    public static Doentes cursorToPaciente(Cursor cursor){
        Doentes doentes = new Doentes();
        doentes.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaDoentes._ID)));
        doentes.setNome_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.NOME_DOENTE)));
        doentes.setNascimento_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.NASCIMENTO_DOENTE)));
        doentes.setTelemovel_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.TELEMOVEL_DOENTE)));
        doentes.setConcelho_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.CONCELHO_DOENTE)));
        doentes.setSexo_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.SEXO_DOENTE)));
        doentes.setCronico_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.CRONICO_DOENTE)));
        doentes.setEstado_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.ESTADO_DOENTE)));
        return doentes;
    }
}