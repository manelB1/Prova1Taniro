package com.example.minhaprova.BancoDeDados

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.minhaprova.Livro
import com.example.minhaprova.Modelos.LivroConsts

class LivroDBOpener(context: Context): SQLiteOpenHelper(context,
    LivroConsts.DATABASE_NAME, null,
    LivroConsts.DATA_BASE_VERSION
) {

    val TAG = "sql"
    val SQL_CREATE_TABLE =
        "CREATE TABLE ${LivroConsts.LivroEntry.TABLE_NAME}" +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${LivroConsts.LivroEntry.NOME} TEXT," +
                " ${LivroConsts.LivroEntry.AUTOR} TEXT," +
                " ${LivroConsts.LivroEntry.NOTA} REAL," +
                " ${LivroConsts.LivroEntry.ANO} INTEGER" +
                ")"
    val SQL_DROP_TABLE =
        "DROP TABLE ${LivroConsts.LivroEntry.TABLE_NAME}"


    override fun onCreate(db: SQLiteDatabase?) {
        Log.i(TAG, "Banco de dados criado")
        if (db != null) {
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE)
            db.execSQL(SQL_CREATE_TABLE)
        }
    }

    fun insertLivro(livro: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {

            var values = ContentValues()
            values.put(LivroConsts.LivroEntry.NOME, livro.nome)
            values.put(LivroConsts.LivroEntry.AUTOR, livro.autor)
            values.put(LivroConsts.LivroEntry.NOTA, livro.nota)
            values.put(LivroConsts.LivroEntry.ANO, livro.ano)

            banco.insert(LivroConsts.LivroEntry.TABLE_NAME, null, values)

        } finally {
            banco.close()
        }
    }

    fun updateLivro(livro: Livro) {
        var banco: SQLiteDatabase = writableDatabase
        try {
            var values = ContentValues()
            values.put(LivroConsts.LivroEntry.NOME, livro.nome)
            values.put(LivroConsts.LivroEntry.AUTOR, livro.autor)
            values.put(LivroConsts.LivroEntry.NOTA, livro.nota)
            values.put(LivroConsts.LivroEntry.ANO, livro.ano)

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${livro.id}")
            banco.update(LivroConsts.LivroEntry.TABLE_NAME, values, selection, whereArgs)

        } finally {
            banco.close()
        }
    }
     fun findById(id: Int): Livro {
        var banco: SQLiteDatabase = readableDatabase
        try {

            var selection = "${BaseColumns._ID} = ?"
            var whereArgs = arrayOf("${id}")
            val cursor: Cursor = banco.query(
                LivroConsts.LivroEntry.TABLE_NAME,
                null,
                selection,
                whereArgs,
                null,
                null,
                null,
                null
            )
             cursor.moveToNext()

            var livro = Livro()
            livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            livro.nome = cursor.getString(cursor.getColumnIndex(LivroConsts.LivroEntry.NOME))
            livro.autor = cursor.getString(cursor.getColumnIndex(LivroConsts.LivroEntry.AUTOR))
            livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroConsts.LivroEntry.NOTA))
            livro.ano = cursor.getInt(cursor.getColumnIndex(LivroConsts.LivroEntry.ANO))
            return livro

        } finally {
            banco.close()
        }
    }
    fun findAll(): ArrayList<Livro> {
        var banco: SQLiteDatabase = readableDatabase
        try {

            val cursor: Cursor = banco.query(
                LivroConsts.LivroEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
        var lista = ArrayList<Livro>()

            while (cursor.moveToNext()) {
                var livro = Livro()

                livro.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
                livro.nome = cursor.getString(cursor.getColumnIndex(LivroConsts.LivroEntry.NOME))
                livro.autor =
                    cursor.getString(cursor.getColumnIndex(LivroConsts.LivroEntry.AUTOR))
                livro.ano = cursor.getInt(cursor.getColumnIndex(LivroConsts.LivroEntry.ANO))
                livro.nota = cursor.getFloat(cursor.getColumnIndex(LivroConsts.LivroEntry.NOTA))

                lista.add(livro)
            }
            return lista

        } finally {
            banco.close()
        }
    }
}
