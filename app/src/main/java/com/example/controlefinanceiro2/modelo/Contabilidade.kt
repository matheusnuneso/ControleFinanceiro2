package com.example.controlefinanceiro2.modelo

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Contabilidade(var nome:String, var valor:Double, var tipo:String) {
    private val formatadorData = DateTimeFormatter.ofPattern("dd/MM/uuuu")

    var dataCadastro: LocalDate? = null

    //cadastro
    constructor(nome: String,
                valor: Double,
                tipo: String,
                dataC: String):this(nome, valor, tipo) {
                    this.dataCadastro = LocalDate.parse(dataC, formatadorData)
                }

    //recupera
    constructor(nome: String,
                valor: Double,
                tipo: String,
                dataC: Long):this(nome, valor, tipo) {
        this.dataCadastro = LocalDate.ofEpochDay(dataC)
    }

    fun getDataCAsSTR():String{
        return formatadorData.format(this.dataCadastro)
    }
}