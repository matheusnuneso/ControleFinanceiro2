package com.example.controlefinanceiro2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.controlefinanceiro2.controle.ContabilidadeControle
import com.example.controlefinanceiro2.databinding.ActivityMainBinding
import com.example.controlefinanceiro2.modelo.Tipo

class MainActivity2 : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var contabilidadeControle: ContabilidadeControle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contabilidadeControle = ContabilidadeControle(baseContext)

        registrarEventos()
    }

    private fun registrarEventos(){
        binding.checkBoxEntrada.setOnClickListener(this)
        binding.checkBoxSaida.setOnClickListener(this)

        binding.btnSalvar.setOnClickListener(this)
        binding.btnResumo.setOnClickListener(this)
    }

    override fun onClick(botao: View) {
        when(botao.id){
            binding.checkBoxEntrada.id -> uncheckSaida()
            binding.checkBoxSaida.id -> uncheckEntrada()

            binding.btnSalvar.id -> salvarContabilidade()
            binding.btnResumo.id -> irParaResumo()
        }
    }

    private fun uncheckSaida(){
        binding.checkBoxSaida.isChecked = false
    }

    private fun uncheckEntrada(){
        binding.checkBoxEntrada.isChecked = false
    }

    private fun irParaResumo(){
        val trancisaoResumo = Intent(baseContext, ResumoActivity::class.java)

        startActivity(trancisaoResumo)
    }

    private fun salvarContabilidade(){

        val nome = binding.lblInputNome.text.toString()
        val valor = binding.lblInputValor.text.toString()
        val dataC = binding.lblInputData.text.toString()
        val tipo = getTipoSelecionado().toString()

        val sucesso = this.contabilidadeControle.salvaContabilidade(nome, valor, tipo, dataC)

        if (sucesso) {
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "algum campo não foi preenchido", Toast.LENGTH_LONG).show()
        }

        limpaCampos()

    }

    private fun limpaCampos(){
        binding.lblInputNome.setText("")
        binding.lblInputValor.setText("")
        binding.lblInputData.setText("")

        uncheckEntrada()
        uncheckSaida()
    }

    private fun getTipoSelecionado():Tipo?{
        if (binding.checkBoxEntrada.isChecked){
            return Tipo.ENTRADA
        }

        if (binding.checkBoxSaida.isChecked){
            return Tipo.SAIDA
        }

        return null
    }

}