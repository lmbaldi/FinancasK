package com.example.financask.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.ResumoView
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import com.example.financask.ui.dialog.AdicionaTransacaoDialog
import com.example.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    private val viewDaActivity by lazy {
        window.decorView
    }

    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

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
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            .configurarDialog(tipo) { transacaoCriada ->
                adicionar(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            }
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
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idDoMenu = item?.itemId
        if(idDoMenu == 1){
            val adapterMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDaTransacao = adapterMenuInfo.position
            remover(posicaoDaTransacao)
        }
        return super.onContextItemSelected(item)
    }

    private fun remover(posicao: Int) {
        transacoes.removeAt(posicao)
        atualizarTransacoes()
    }

    private fun chamarDialogDeAlteracao(transacao: Transacao,position: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
            .configurarDialog(transacao) { transacaoAlterada ->
                 alterar( transacaoAlterada, position)
            }
    }

    private fun alterar(transacao: Transacao, position: Int ) {
        transacoes[position] = transacao
        atualizarTransacoes()
    }
}


