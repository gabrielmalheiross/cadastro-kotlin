package com.example.cadastrokotlin.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cadastrokotlin.data.model.Cliente

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "jesusapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "clientes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOME = "nome"
        private const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NOME TEXT, $COLUMN_EMAIL TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertCliente(cliente: Cliente) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, cliente.nome)
            put(COLUMN_EMAIL, cliente.email)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

}