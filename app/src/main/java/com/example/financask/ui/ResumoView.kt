package com.example.financask.ui

import android.view.View
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import com.example.financask.extension.formatarParaBrasileiro
import com.example.financask.model.Resumo

class ResumoView(private val view: View, transacoes: List<Transacao>) {

    //propertie criada para acessar o objeto para evitar o conflito de transacoes
    private val resumo: Resumo = Resumo(transacoes)

    fun adicionarReceita() {
       val totalReceita = resumo.receita()
       view.resumo_card_receita.text = totalReceita.formatarParaBrasileiro()
    }

    fun adicionarDespesa() {
      val totalDespesa = resumo.despesa()
      view.resumo_card_despesa.text = totalDespesa.formatarParaBrasileiro()
    }

    fun adicionarTotal(){
        val total = resumo.total()
        view.resumo_card_total.text = total.formatarParaBrasileiro()
    }
}