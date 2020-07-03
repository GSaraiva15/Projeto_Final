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

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name : columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }
        return tableString;
    }

    @Test
    public void consegueAbrirBaseDados() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdProjFinal = openHelper.getReadableDatabase();
        assertTrue(bdProjFinal.isOpen());
        getTableAsString(bdProjFinal, "Concelhos");
        bdProjFinal.close();
    }

    private long insereDoente(BdTabelaDoentes tabelaDoentes, Doentes doentes) {
        long id = tabelaDoentes.insert(Converte.doenteToContentValues(doentes));
        assertNotEquals(-1, id);
        return id;
    }

    private long insereDoente(BdTabelaDoentes tabelaDoentes, String Nome, String Data_Nascimento, String Telemovel, Integer Concelho, String Sexo, String Cronico, String Estado,String dataEstado) {
        Doentes doentes = new Doentes();
        doentes.setNome_doente(Nome);
        doentes.setNascimento_doente(Data_Nascimento);
        doentes.setTelemovel_doente(Telemovel);
        doentes.setId_concelho(Concelho);
        doentes.setSexo_doente(Sexo);
        doentes.setCronico_doente(Cronico);
        doentes.setEstado_doente(Estado);
        doentes.setData_estado(dataEstado);
        return insereDoente(tabelaDoentes, doentes);
    }

    @Test
    public void concegueInserirDoente() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        getTableAsString(db, "Concelhos");
        Cursor cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        Cursor cursor1 = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_=?", new String[]{"Seia"}, null, null, null);
        Integer id_conselho = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_conselho = cursor1.getInt(cursor1.getColumnIndex("_id"));
        insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_conselho, "Masculino", "Não,", "Recuperado","25/06/2020");
        cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();
        db.close();
    }

    @Test
    public void consegueAlterarDoentes() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdDoente = openHelper.getWritableDatabase();

        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(bdDoente);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(bdDoente);

        Doentes doentes = new Doentes();
        doentes.setNome_doente("Gonçalo");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        Integer id_conselho = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_ = ?", new String[]{"Seia"}, null, null, null).getColumnIndex("_id");
        doentes.setId_concelho(id_conselho);
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        doentes.setData_estado("25/06/2020");
        long id = insereDoente(tabelaDoentes, doentes);

        doentes.setNome_doente("Gonçalo Saraiva");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        id_conselho = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_ = ?", new String[]{"Seia"}, null, null, null).getColumnIndex("_id");
        doentes.setId_concelho(id_conselho);
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        doentes.setData_estado("25/06/2020");
        int registosAlterados = tabelaDoentes.update(Converte.doenteToContentValues(doentes), BdTabelaDoentes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAlterados);
        bdDoente.close();
    }

    @Test
    public void consegueApagarDoentes() {
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);

        Cursor cursor1 = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_=?", new String[]{"Seia"}, null, null, null);
        Integer id_conselho = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_conselho = cursor1.getInt(cursor1.getColumnIndex("_id"));
        long id = insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_conselho, "Masculino", "Não,", "Recuperado","25/06/2020");
        int registosApagados = tabelaDoentes.delete(BdTabelaDoentes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosApagados);
        db.close();
    }

    @Test
    public void consegueLerDoente() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);

        Cursor cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        Cursor cursor1 = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_=?", new String[]{"Seia"}, null, null, null);
        Integer id_conselho = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_conselho = cursor1.getInt(cursor1.getColumnIndex("_id"));
        insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_conselho, "Masculino", "Não,", "Recuperado","25/06/2020");
        cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();
        db.close();
    }

    private long insereTeste(SQLiteDatabase db, String dataTestes, String Resultado_teste) {
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);

        Integer id_conselho = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_ = ?", new String[]{"Seia"}, null, null, null).getColumnIndex("_id");
        long idTeste = insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_conselho, "Masculino", "Não,", "Recuperado","25/06/2020");

        Testes testes = new Testes();
        testes.setData_testes(dataTestes);
        testes.setResultado_testes(Resultado_teste);
        testes.setId(idTeste);

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);
        long id = tabelaTestes.insert(Converte.testesToContentValues(testes));
        assertNotEquals(-1, id);

        return id;
    }

    @Test
    public void consegueInserirTeste() {
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdTestes = openHelper.getWritableDatabase();
        insereTeste(bdTestes, "13/06/2020", "Infetado");

        bdTestes.close();
    }

    @Test
    public void conseguelerTeste() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdTestes = openHelper.getWritableDatabase();
        BdTabelaTestes tabelaTestes = new BdTabelaTestes(bdTestes);

        Cursor cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereTeste(bdTestes, "13/06/2020", "Infetado");
        cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdTestes.close();
    }

    @Test
    public void consegueAlterarTeste() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdTestes = openHelper.getWritableDatabase();

        long idTestes = insereTeste(bdTestes, "13/06/2020", "Infetado");

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(bdTestes);

        Cursor cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, BdTabelaTestes.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(idTestes)}, null, null, null);
        assertEquals(1, cursor.getCount());

        assertTrue(cursor.moveToNext());
        Testes testes = Converte.cursorToTestes(cursor);
        cursor.close();

        assertEquals("13/06/2020", testes.getData_testes());

        testes.setData_testes("14/06/2020");


        int registosAfetados = tabelaTestes.update(Converte.testesToContentValues(testes), BdTabelaTestes.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(testes.getId())});
        assertEquals(1, registosAfetados);

        bdTestes.close();
    }

    @Test
    public void consegueEliminarTestes() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdTestes = openHelper.getWritableDatabase();

        long id = insereTeste(bdTestes, "13/06/2020", "Infetado");

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(bdTestes);
        int registosEliminados = tabelaTestes.delete(BdTabelaTestes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdTestes.close();
    }
}
