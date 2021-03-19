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
import com.example.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    private var viewDaActivity: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        viewDaActivity = window.decorView

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
        AdicionaTransacaoDialog(viewDaActivity as ViewGroup, this)
            .configurarDialog(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    adicionar(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun adicionar(transacao: Transacao) {
        transacoes.add(transacao)
        atualizarTransacoes()
    }

    private fun atualizarTransacoes() {
        configurarLista()
        configurarResumo()
    }

    private fun configurarResumo() {
        //pega view da activity
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualizar()
    }

    private fun configurarLista() {
        val listaTransacoesAdater = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview){
            adapter = listaTransacoesAdater
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamarDialogDeAlteracao(transacao, position)
            }
        }
    }

    private fun chamarDialogDeAlteracao(transacao: Transacao,position: Int) {
        AlteraTransacaoDialog(viewDaActivity as ViewGroup, this)
            .configurarDialog(transacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    alterar( transacao, position)
                }

            })
    }

    private fun alterar(transacao: Transacao, position: Int ) {
        transacoes[position] = transacao
        atualizarTransacoes()
    }
}


