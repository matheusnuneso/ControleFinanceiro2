package com.example.controlefinanceiro2.dados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MeuBancoSQLite(var context: Context)
    :SQLiteOpenHelper(context, EsquemaDB.NOME_BD, null, EsquemaDB.VERSAO) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(EsquemaDB.tabela_contabilidade.CRIA_TABELA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}