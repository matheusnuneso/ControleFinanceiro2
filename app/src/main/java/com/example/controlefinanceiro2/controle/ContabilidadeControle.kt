package com.example.controlefinanceiro2.controle

import android.content.Context
import com.example.controlefinanceiro2.modelo.Contabilidade
import com.example.controlefinanceiro2.modelo.Tipo
import com.example.controlefinanceiro2.repository.ContabilidadeRepository

class ContabilidadeControle(var contexto: Context) {

    var repository: ContabilidadeRepository

    init {
        repository = ContabilidadeRepository(contexto)
    }

    fun salvaContabilidade(nome:String, valor:String, tipo:String, dataC:String):Boolean{

        if(nome != "" && valor != "" && tipo != "" && dataC != ""){

            val valorDouble = valor.toDouble()

            val cont = Contabilidade(nome, valorDouble, tipo, dataC)
            return repository.salvarContabilidade(cont)
        }

        return false
    }

    fun somaEntradasSaidas(tipoEntradaSaida:String): Double {

        val somaValores = this.repository.somaEntradasSaidas(tipoEntradaSaida)
        return somaValores

    }

    fun somaDinDisponivel(): Double {
        val somaEntradas = this.repository.somaEntradasSaidas(Tipo.ENTRADA.toString())
        val somaGastos = this.repository.somaEntradasSaidas(Tipo.SAIDA.toString())

        return somaEntradas - somaGastos
    }

}