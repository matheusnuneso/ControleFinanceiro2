package com.example.controlefinanceiro2.dados

class EsquemaDB {

    companion object{
        val NOME_BD:String = "bd_controle_financeiro"
        val VERSAO:Int = 1
    }

    object tabela_contabilidade{
        val NOME_TABELA = "contabilidade"
        val ID_ATT = "id"
        val NOME_ATT = "nome"
        val VALOR_ATT = "valor"
        val TIPO_ATT = "tipo"
        val DATAC_ATT = "data_cadastro"
        val CRIA_TABELA = "CREATE TABLE IF NOT EXISTS $NOME_TABELA " +
                "($ID_ATT INTEGER primary key autoincrement, " +
                "$NOME_ATT text, $VALOR_ATT text, $TIPO_ATT text, " +
                "$DATAC_ATT integer)"
    }
}