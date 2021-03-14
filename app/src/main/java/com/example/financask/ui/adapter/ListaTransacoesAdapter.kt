package com.example.financask.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.BaseAdapter
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.transacao_item.view.*

import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.extension.limitarEmAte
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.extension.formatarParaBrasileiro

class ListaTransacoesAdapter(
    private val transacoes: List<Transacao>, private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 16

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent,false)

        val transacao = transacoes[position]

        adicionarValor(transacao, viewCriada)
        adicionarIcone(transacao, viewCriada)
        adicionarCategoria(viewCriada, transacao)
        adicionarData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionarValor(transacao: Transacao, viewCriada: View) {
        val cor: Int = corPor(transacao.tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formatarParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }

    private fun adicionarIcone(transacao: Transacao, viewCriada: View) {
         var icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionarCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitarEmAte(limiteDaCategoria)
    }

    private fun adicionarData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()
    }
}