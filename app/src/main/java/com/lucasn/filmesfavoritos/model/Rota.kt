package com.lucasn.filmesfavoritos.model

import kotlinx.serialization.Serializable

sealed interface Rota {
    @Serializable
    data object Splash : Rota

    @Serializable
    data object Login : Rota

    @Serializable
    data object ListaFilmes : Rota

    @Serializable
    data object Estatisticas : Rota

    @Serializable
    data object Perfil : Rota

    @Serializable
    data class DetalheFilme(val filmeId: Int) : Rota
}
