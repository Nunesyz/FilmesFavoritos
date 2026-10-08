package com.lucasn.filmesfavoritos.model

data class Filme(
    val id: Int,
    val titulo: String,
    val ano: Int,
    val genero: String,
    val emoji: String,
    val nota: Int,
    val sinopse: String,
    val assistido: Boolean = false
)
