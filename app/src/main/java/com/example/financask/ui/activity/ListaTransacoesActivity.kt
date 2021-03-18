package com.example.financask.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.math.BigDecimal
import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.ui.ResumoView
import com.example.financask.model.Transacao
import androidx.appcompat.app.AppCompatActivity
import com.example.financask.delegate.TransacaoDelegate
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import com.example.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configurarResumo()

        configurarLista()

        lista_transacoes_adiciona_receita.setOnClickListener {
           AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
               .configurarDialog( object : TransacaoDelegate{
                   override fun delegate(transacao: Transacao) {
                       atualizarTransacoes(transacao)
                       lista_transacoes_adiciona_menu.close(true)
                   }
               })
        }
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


