package com.example.controlefinanceiro2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.controlefinanceiro2.controle.ContabilidadeControle
import com.example.controlefinanceiro2.databinding.ActivityMainBinding
import com.example.controlefinanceiro2.databinding.ActivityResumoBinding
import com.example.controlefinanceiro2.modelo.Tipo

class ResumoActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityResumoBinding
    lateinit var contabilidadeControle: ContabilidadeControle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResumoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contabilidadeControle = ContabilidadeControle(baseContext)

        atualizaDadosMensag()

        registrarEventos()
    }

    private fun registrarEventos(){
        binding.btnVoltar.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.btnVoltar.id -> voltar()
        }
    }

    private fun atualizaDadosMensag(){
        setValorEntrada(contabilidadeControle.somaEntradasSaidas(Tipo.ENTRADA.toString()))
        setValorGastos(contabilidadeControle.somaEntradasSaidas(Tipo.SAIDA.toString()))
        setValorDinDisponivel(contabilidadeControle.somaDinDisponivel())
    }

    private fun voltar(){
        finish()
    }

    private fun setValorEntrada(valor: Double){
        val valorFormatado = String.format("R$ %.2f", valor)
        binding.valorEntradasTxt.text = "R$ $valorFormatado"
    }

    private fun setValorGastos(valor: Double){
        val valorFormatado = String.format("R$ %.2f", valor)
        binding.valorGastosTxt.text = "R$ $valorFormatado"
    }

    private fun setValorDinDisponivel(valor: Double){
        val valorFormatado = String.format("R$ %.2f", valor)
        binding.valorDinDisponivelTxt.text = "R$ $valorFormatado"
    }


}