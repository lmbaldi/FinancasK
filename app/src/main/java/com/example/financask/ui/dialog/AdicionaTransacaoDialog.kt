package com.example.financask.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.util.*
import java.math.BigDecimal
import java.lang.NumberFormatException
import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.delegate.TransacaoDelegate
import com.example.financask.extension.converterParaCalendar
import com.example.financask.extension.formataParaBrasileiro

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {

    private val viewCriada = criarLayout()

    fun configurarDialog(transacaoDelegate: TransacaoDelegate) {

        configurarCampoData()

        configuarCampoCategoria()

        configurarFormulario(transacaoDelegate)
    }

    private fun configurarFormulario(transacaoDelegate: TransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(R.string.adiciona_receita)
            .setView(viewCriada)
            .setPositiveButton("Adicionar", DialogInterface.OnClickListener { _, _ ->

                val valorEmTetxto = viewCriada.form_transacao_valor.text.toString()
                val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()
                val valor = converteCampoValor(valorEmTetxto)
                val data = dataEmTexto.converterParaCalendar()
                val transacaoCriada = Transacao(
                    tipo = Tipo.RECEITA,
                    valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                transacaoDelegate.delegate(transacaoCriada)
            })
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun converteCampoValor(valorEmTetxto: String) : BigDecimal {
        return try {
            BigDecimal(valorEmTetxto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversao de valor", Toast.LENGTH_LONG)
                .show()
            BigDecimal.ZERO
        }
    }

    private fun configuarCampoCategoria() {
        val adapter = ArrayAdapter
            .createFromResource(
                context, R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item
            )
        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configurarCampoData() {

        val hoje = Calendar.getInstance()
        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())
        viewCriada.form_transacao_data.setOnClickListener {
            DatePickerDialog(
                context,
                { _, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                }, ano, mes, dia
            ).show()
        }
    }

    private fun criarLayout(): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.form_transacao, viewGroup, false)
    }
}