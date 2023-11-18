package com.example.controlefinanceiro2.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.controlefinanceiro2.dados.EsquemaDB
import com.example.controlefinanceiro2.dados.MeuBancoSQLite
import com.example.controlefinanceiro2.modelo.Contabilidade
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ContabilidadeRepository(var contexto: Context) {

    var banco:MeuBancoSQLite
    var conexao: SQLiteDatabase

    init {
        this.banco = MeuBancoSQLite(this.contexto)
        this.conexao = this.banco.writableDatabase
    }

    fun salvarContabilidade(contabilidade: Contabilidade):Boolean{
        val valores = ContentValues()

        valores.put(EsquemaDB.tabela_contabilidade.NOME_ATT, contabilidade.nome)
        valores.put(EsquemaDB.tabela_contabilidade.VALOR_ATT, contabilidade.valor)
        valores.put(EsquemaDB.tabela_contabilidade.TIPO_ATT, contabilidade.tipo)

        if (contabilidade != null){
            valores.put(EsquemaDB.tabela_contabilidade.DATAC_ATT, contabilidade.dataCadastro!!.toEpochDay())
        }

        return this.conexao.insert(EsquemaDB.tabela_contabilidade.NOME_TABELA, null, valores) > 0
    }

    fun somaEntradasSaidas(tipoEntradaSaida:String): Double {
        val query = "SELECT SUM(${EsquemaDB.tabela_contabilidade.VALOR_ATT}) " +
                    "FROM ${EsquemaDB.tabela_contabilidade.NOME_TABELA} " +
                    "WHERE ${EsquemaDB.tabela_contabilidade.TIPO_ATT} = ?"

        val tuplasBanco = this.conexao.rawQuery(query, arrayOf(tipoEntradaSaida))

        if (tuplasBanco.moveToFirst()) {
            return tuplasBanco.getDouble(0)
        }

        return 00.00
    }

}