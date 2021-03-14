package com.example.financask.ui

import android.view.View
import java.math.BigDecimal
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import com.example.financask.extension.formatarParaBrasileiro

class ResumoView(private val view: View, private val transacoes: List<Transacao>) {

    fun adicionarReceita() {
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        view.resumo_card_receita.text = totalReceita.formatarParaBrasileiro()
    }

    fun adicionarDespesa() {
        var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }
        view.resumo_card_despesa.text = totalReceita.formatarParaBrasileiro()
    }
}