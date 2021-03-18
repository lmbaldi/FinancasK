package com.example.financask.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.R
import com.example.financask.delegate.TransacaoDelegate
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.ResumoView
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import com.example.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configurarResumo()
        configurarLista()
        configurarFab()
    }

    private fun configurarFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamarDialogDeAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamarDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamarDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            .configurarDialog(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizarTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun atualizarTransacoes(transacaoCriada: Transacao) {
        transacoes.add(transacaoCriada)
        configurarLista()
        configurarResumo()
    }

    private fun configurarResumo() {
        //pega view da activity
        val view: View = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualizar()
    }

    private fun configurarLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}


