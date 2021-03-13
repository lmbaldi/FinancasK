package com.example.financask.extension

fun String.limitarEmAte(caracteres: Int) : String {
    if(this.length > caracteres){
        return "${this.substring(0, caracteres)}..."
    }
    return this
}