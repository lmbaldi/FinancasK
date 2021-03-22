package com.example.financask.dao

import com.example.financask.model.Transacao

class TransacaoDAO {

    val transacoes: List<Transacao> = Companion.transacoes

    companion object{
       private val transacoes: MutableList<Transacao> =  mutableListOf()
    }


    fun adicionar(transacao: Transacao){
        Companion.transacoes.add(transacao)
    }

    fun alterar(transacao: Transacao, posicao: Int){
        Companion.transacoes[posicao] = transacao
    }

    fun remover (posicao: Int){
        Companion.transacoes.removeAt(posicao)
    }
}