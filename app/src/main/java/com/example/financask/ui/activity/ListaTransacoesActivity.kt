package com.example.financask.ui.activity

import android.os.Bundle
import java.math.BigDecimal
import com.example.financask.R
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configurarLista(transacoes)
    }

    private fun configurarLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo() = listOf(
        Transacao(valor = BigDecimal(20.5),categoria = "Almoço de final de semana",tipo = Tipo.DESPESA),
        Transacao(valor = BigDecimal(100), categoria = "Economia", tipo = Tipo.RECEITA),
        Transacao(valor = BigDecimal(250), tipo = Tipo.DESPESA),
        Transacao(valor = BigDecimal(750), categoria = "Premio", tipo = Tipo.RECEITA),
    )
}