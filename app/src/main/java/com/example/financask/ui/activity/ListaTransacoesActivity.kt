package com.example.financask.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.math.BigDecimal
import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.ui.ResumoView
import com.example.financask.model.Transacao
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configurarResumo(transacoes)

        configurarLista(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {
           val view: View = window.decorView
            val viewCriada = LayoutInflater.from(this)
                .inflate(R.layout.form_transacao, view as ViewGroup, false)

            val ano =2021
            val mes = 3
            val dia = 16
            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())
            viewCriada.form_transacao_data.setOnClickListener{
                DatePickerDialog(this,
                    { view, year, month, dayOfMonth ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(year, month, dayOfMonth)
                        viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                    }, ano, mes, dia).show()
            }

            val adapter = ArrayAdapter
                .createFromResource(
                    this, R.array.categorias_de_receita,
                    android.R.layout.simple_spinner_dropdown_item
                )

            viewCriada.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
               .setTitle(R.string.adiciona_receita)
               .setView(viewCriada)
               .setPositiveButton("Adicionar", null)
               .setNegativeButton("Cancelar", null)
               .show()
        }
    }

    private fun configurarResumo(transacoes: List<Transacao>) {
        //pega view da activity
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualizar()
    }

    private fun configurarLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo() = listOf(
        Transacao(valor = BigDecimal(20.5),categoria = "Almoço de final de semana",tipo = Tipo.DESPESA),
        Transacao(valor = BigDecimal(100), categoria = "Economia", tipo = Tipo.RECEITA),
        Transacao(valor = BigDecimal(950), tipo = Tipo.DESPESA),
        Transacao(valor = BigDecimal(750), categoria = "Premio", tipo = Tipo.RECEITA),
    )
}

private fun LayoutInflater.inflate(formTransacao: Int) {

}
