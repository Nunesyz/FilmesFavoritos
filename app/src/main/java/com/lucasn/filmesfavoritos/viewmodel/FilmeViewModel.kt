package com.lucasn.filmesfavoritos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lucasn.filmesfavoritos.model.Filme

class FilmeViewModel : ViewModel() {

    var estaLogado by mutableStateOf(false)
        private set

    val filmes = mutableStateListOf(
        Filme(1, "Interestelar", 2014, "Ficção científica", "🚀", 10, "Um grupo de astronautas viaja por um buraco de minhoca procurando um novo planeta para a humanidade."),
        Filme(2, "Cidade de Deus", 2002, "Drama", "📷", 9, "A história de Buscapé, que cresce na Cidade de Deus e vê o crime tomar conta do lugar."),
        Filme(3, "O Poderoso Chefão", 1972, "Crime", "🌹", 10, "Michael Corleone acaba assumindo os negócios da família mafiosa depois de um atentado contra o pai."),
        Filme(4, "A Viagem de Chihiro", 2001, "Animação", "🐉", 9, "Chihiro fica presa num mundo de espíritos e precisa trabalhar numa casa de banhos para salvar os pais."),
        Filme(5, "Parasita", 2019, "Suspense", "🏠", 9, "Uma família pobre consegue emprego na casa de uma família rica, mas as coisas saem do controle."),
        Filme(6, "Matrix", 1999, "Ficção científica", "💊", 9, "Neo descobre que o mundo em que vive é uma simulação criada por máquinas."),
        Filme(7, "De Volta para o Futuro", 1985, "Aventura", "⚡", 9, "Marty volta no tempo até 1955 e precisa fazer os pais dele se conhecerem."),
        Filme(8, "Batman: O Cavaleiro das Trevas", 2008, "Ação", "🦇", 10, "Batman enfrenta o Coringa, que quer espalhar o caos em Gotham."),
        Filme(9, "O Auto da Compadecida", 2000, "Comédia", "🌵", 10, "João Grilo e Chicó se metem em várias confusões no sertão da Paraíba."),
        Filme(10, "Whiplash", 2014, "Drama", "🥁", 8, "Um baterista jovem é pressionado ao extremo por um professor muito exigente."),
        Filme(11, "Divertida Mente", 2015, "Animação", "💭", 8, "As emoções de uma menina tentam ajudá-la durante a mudança para outra cidade."),
        Filme(12, "Duna: Parte Dois", 2024, "Ficção científica", "🏜️", 8, "Paul Atreides se junta aos Fremen e busca vingança contra os inimigos da família.")
    )

    fun fazerLogin() {
        estaLogado = true
    }

    fun fazerLogout() {
        estaLogado = false
    }

    fun alternarAssistido(filmeId: Int) {
        val index = filmes.indexOfFirst { it.id == filmeId }
        if (index != -1) {
            filmes[index] = filmes[index].copy(assistido = !filmes[index].assistido)
        }
    }

    fun removerFilme(filmeId: Int) {
        filmes.removeAll { it.id == filmeId }
    }

    fun buscarFilme(filmeId: Int): Filme? =
        filmes.find { it.id == filmeId }
}
