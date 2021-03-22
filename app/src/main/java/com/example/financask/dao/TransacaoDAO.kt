package com.example.financask.dao

import com.example.financask.model.Transacao

class TransacaoDAO {

    val transacoes: MutableList<Transacao> =  mutableListOf()

    fun adicionar(transacao: Transacao){
        transacoes.add(transacao)
    }

    fun alterar(transacao: Transacao, posicao: Int){
        transacoes[posicao] = transacao
    }

    fun remover (posicao: Int){
        transacoes.removeAt(posicao)
    }
}