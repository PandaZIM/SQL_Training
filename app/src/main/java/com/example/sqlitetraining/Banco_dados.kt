package com.example.sqlitetraining

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedReaderContract {
    object FeedEntry : BaseColumns {

         const val TABLE_NAME = "tb_clientes"

         const val COLUMN_NAME = "Nome"
         const val COLUMN_CODE = "Codigo"
         const val COLUMN_CELLPHONE = "Telefone"
         const val COLUMN_EMAIL = "Email"

    }
}

private const val SQL_CREATE_ENTRIES = "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "${FeedReaderContract.FeedEntry.COLUMN_CODE} INTEGER PRIMARY KEY, " +
        "${FeedReaderContract.FeedEntry.COLUMN_NAME} TEXT, " +
        "${FeedReaderContract.FeedEntry.COLUMN_CELLPHONE} TEXT, " +
        "${FeedReaderContract.FeedEntry.COLUMN_EMAIL} TEXT "

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME

class Banco_dados(context: Context) : SQLiteOpenHelper(context, database_name, null, database_version) {


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(SQL_CREATE_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)

    }

    companion object {

        const val database_version = 1
        const val database_name = "bd_clientes"


    }

    val dbHelper = Banco_dados(context)


    fun add_cliente(cliente: Cliente){

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {

            put(FeedReaderContract.FeedEntry.COLUMN_NAME, cliente.Nome)
            put(FeedReaderContract.FeedEntry.COLUMN_CELLPHONE, cliente.Telefone)
            put(FeedReaderContract.FeedEntry.COLUMN_EMAIL, cliente.email)

        }

        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
        db.close()

    }


}
