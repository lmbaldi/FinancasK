package com.example.financask.ui

import android.view.View
import android.content.Context
import com.example.financask.R
import androidx.core.content.ContextCompat
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import com.example.financask.extension.formatarParaBrasileiro
import com.example.financask.model.Resumo
import java.math.BigDecimal

class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    //propertie criada para acessar o objeto para evitar o conflito de transacoes
    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun adicionarReceita() {
       val totalReceita = resumo.receita()
        with(view.resumo_card_receita){
            setTextColor(corReceita)
            text = totalReceita.formatarParaBrasileiro()
        }
    }

    fun adicionarDespesa() {
      val totalDespesa = resumo.despesa()
      with(view.resumo_card_despesa) {
        setTextColor(corDespesa)
        text = totalDespesa.formatarParaBrasileiro()
      }
    }

    fun adicionarTotal(){
        val total = resumo.total()
        val cor = corPor(total)
        with(view.resumo_card_total){
            setTextColor(cor)
            text = total.formatarParaBrasileiro()
        }
    }

    private fun corPor(valor: BigDecimal) : Int {
        if (valor >= BigDecimal.ZERO) {
            return  corReceita
        }
        return  corDespesa
    }
}