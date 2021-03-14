package com.example.financask.extension

fun String.limitarEmAte(caracteres: Int) : String {

    if(this.length > caracteres){
        val primeiroCaracter = 0
        return "${this.substring(primeiroCaracter, caracteres)}..."
    }
    return this
}