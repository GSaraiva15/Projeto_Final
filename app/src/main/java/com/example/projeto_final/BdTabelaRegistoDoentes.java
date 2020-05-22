package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaRegistoDoentes implements BaseColumns {

    public static final String NOME_TABELA_DOENTE = "Noentes";
    public static final String NOME_DOENTE = "Nome";
    public static final String NASCIMENTO_DOENTE ="Data_Nascimento";
    public static final String TELEMOVEL_DOENTE = "Telemovel_";
    public static final String CONCELHO_DOENTE ="Concelho";
    public static final String SEXO_DOENTE = "Sexo";
    public static final String CRONICO_DOENTE ="Cronico";
    public static final String ESTADO_PACIENTE ="Estado";


    private SQLiteDatabase db;
    public BdTabelaRegistoDoentes(SQLiteDatabase db){
        this.db = db;
    }
    public void criar(){
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA_DOENTE +"("+
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NOME_DOENTE + " TEXT NOT NULL,"+
                        NASCIMENTO_DOENTE + "TEXT NOT NULL,"+
                        TELEMOVEL_DOENTE + "TEXT NOT NULL,"+
                        CONCELHO_DOENTE + "TEXT NOT NULL,"+
                        SEXO_DOENTE +  "TEXT NOT NULL,"+
                        CRONICO_DOENTE +"TEXT NOT NULL,"+
                        ESTADO_PACIENTE + "TEXT NOT NULL"+
                        ")"
        );
    }
    public long insert (ContentValues values){
        return db.insert(NOME_TABELA_DOENTE,null,values);
    }
    /**
     * Query the given table, returning a {@link Cursor} over the result set.
     *
     *
     * @param columns A list of which columns to return. Passing null will
     *            return all columns, which is discouraged to prevent reading
     *            data from storage that isn't going to be used.
     * @param selection A filter declaring which rows to return, formatted as an
     *            SQL WHERE clause (excluding the WHERE itself). Passing null
     *            will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be
     *         replaced by the values from selectionArgs, in order that they
     *         appear in the selection. The values will be bound as Strings.
     * @param groupBy A filter declaring how to group rows, formatted as an SQL
     *            GROUP BY clause (excluding the GROUP BY itself). Passing null
     *            will cause the rows to not be grouped.
     * @param having A filter declare which row groups to include in the cursor,
     *            if row grouping is being used, formatted as an SQL HAVING
     *            clause (excluding the HAVING itself). Passing null will cause
     *            all row groups to be included, and is required when row
     *            grouping is not being used.
     * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
     *            (excluding the ORDER BY itself). Passing null will use the
     *            default sort order, which may be unordered.
     * @return A {@link Cursor} object, which is positioned before the first entry. Note that
     * {@link Cursor}s are not synchronized, see the documentation for more details.
     * @see Cursor
     */
    public Cursor query(String[] columns, String selection,String[] selectionArgs,String groupBy,String having, String orderBy){
        return db.query(NOME_TABELA_DOENTE,columns,selection,selectionArgs,groupBy,having,orderBy);
    }
    /**
     * Convenience method for updating rows in the database.
     *
     * @param values a map from column names to new column values. null is a
     *            valid value that will be translated to NULL.
     * @param whereClause the optional WHERE clause to apply when updating.
     *            Passing null will update all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     * @return the number of rows affected
     */
    public int update(ContentValues values,String whereClause,String[] whereArgs){
        return db.update(NOME_TABELA_DOENTE,values,whereClause,whereArgs);
    }

    /**
     * Convenience method for deleting rows in the database.
     *
     * @param whereClause the optional WHERE clause to apply when deleting.
     *            Passing null will delete all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     */
    public int delete(String whereClause,String[] whereArgs){
        return db.delete(NOME_TABELA_DOENTE,whereClause,whereArgs);
    }
}
