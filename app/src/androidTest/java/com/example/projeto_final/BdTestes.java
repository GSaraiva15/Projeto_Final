package com.example.projeto_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BdTestes {
    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }
    @Before
    public void apagaBaseDados() {
        getTargetContext().deleteDatabase(BdInserirDoenteOpenHelper.NOME_BD_PROJ_FINAL);
    }

    @Test
    public void consegueAbrirBaseDados() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdInserirDoenteOpenHelper openHelper = new BdInserirDoenteOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getReadableDatabase();
        assertTrue(bdLivros.isOpen());
        bdLivros.close();
    }


}
