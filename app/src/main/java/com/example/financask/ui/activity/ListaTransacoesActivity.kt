package com.example.financask.ui.activity

import android.os.Bundle
import android.view.View
import java.math.BigDecimal
import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.ui.ResumoView
import com.example.financask.model.Transacao
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configurarResumo(transacoes)

        configurarLista(transacoes)
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