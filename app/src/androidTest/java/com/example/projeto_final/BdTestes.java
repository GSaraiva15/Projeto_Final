package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BdTestes {
    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
    @Before
    public void apagaBaseDados() {
        getTargetContext().deleteDatabase(BdDoenteOpenHelper.NOME_BD_PROJ_FINAL);
    }

    @Test
    public void consegueAbrirBaseDados() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdProjFinal = openHelper.getReadableDatabase();
        assertTrue(bdProjFinal.isOpen());
        bdProjFinal.close();
    }

    private long insereDoente(BdTabelaDoentes tabelaDoentes, Doentes doentes){
        long id= tabelaDoentes.insert(Converte.doenteToContentValues(doentes));
        assertNotEquals(-1,id);
        return id;
    }

    private long insereDoente(BdTabelaDoentes tabelaDoentes, String Nome, String Data_Nascimento, String Telemovel, String Concelho, String Sexo, String Cronico, String Estado){
        Doentes doentes = new Doentes();
        doentes.setNome_doente(Nome);
        doentes.setNascimento_doente(Data_Nascimento);
        doentes.setTelemovel_doente(Telemovel);
        doentes.setConcelho_doente(Concelho);
        doentes.setSexo_doente(Sexo);
        doentes.setCronico_doente(Cronico);
        doentes.setEstado_doente(Estado);

        return insereDoente(tabelaDoentes,doentes);
    }
    @Test
    public void concegueInserirDoente(){
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdDoentes = openHelper.getWritableDatabase();

        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(bdDoentes);
        Cursor cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE,null,null,null,null,null);
        int registos = cursor.getCount();
        cursor.close();
        insereDoente(tabelaDoentes,"Gonçalo","15/02/2000","987654321","Seia","Masculino","Não,","Recuperado");
        cursor =tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE,null,null,null,null,null);
        assertEquals(registos+1,cursor.getCount());
        cursor.close();
        bdDoentes.close();
    }
    @Test
    public void consegueAlterarDoentes(){
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdDoente = openHelper.getWritableDatabase();

        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(bdDoente);

        Doentes doentes = new Doentes();
        doentes.setNome_doente("Gonçalo");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        doentes.setConcelho_doente("Seia");
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        long id = insereDoente(tabelaDoentes,doentes);

        doentes.setNome_doente("Gonçalo Saraiva");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        doentes.setConcelho_doente("Seia");
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        int registosAlterados = tabelaDoentes.update(Converte.doenteToContentValues(doentes),BdTabelaDoentes._ID+ "=?",new String[]{String.valueOf(id)});
        assertEquals(1, registosAlterados);
        bdDoente.close();
    }
    @Test
    public void consegueApagarDoentes(){
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdDoentes = openHelper.getWritableDatabase();
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(bdDoentes);

        long id = insereDoente(tabelaDoentes,"Gonçalo","15/02/2000","987654321","Seia","Masculino","Não,","Recuperado");
        int registosApagados = tabelaDoentes.delete(BdTabelaDoentes._ID+ "=?",new String[]{String.valueOf(id)});
    assertEquals(1,registosApagados);
    bdDoentes.close();

    }
}
