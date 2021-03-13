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

class ListaTransacoesAdapter(transacoes: List<Transacao>, context: Context) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    private val limiteDaCategoria = 16

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.transacao_item, parent,false)

        val transacao = transacoes[position]

        if(transacao.tipo == Tipo.RECEITA){
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
        }else {
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context,R.color.despesa))
        }

        if(transacao.tipo == Tipo.RECEITA){
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }

        viewCriada.transacao_valor.text = transacao.valor.formatarParaBrasileiro()
        viewCriada.transacao_categoria.text = transacao.categoria.limitarEmAte(limiteDaCategoria)
        viewCriada.transacao_data.text = transacao.data.formataParaBrasileiro()

        return viewCriada
    }

}