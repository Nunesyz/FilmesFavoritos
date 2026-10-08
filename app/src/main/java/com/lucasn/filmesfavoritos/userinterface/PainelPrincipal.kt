package com.lucasn.filmesfavoritos.userinterface

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.lucasn.filmesfavoritos.model.Rota
import com.lucasn.filmesfavoritos.viewmodel.FilmeViewModel

enum class AbaPrincipal { FILMES, ESTATISTICAS, PERFIL }

@Composable
fun PainelPrincipal(
    navController: NavHostController,
    viewModel: FilmeViewModel,
    abaAtual: AbaPrincipal
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.FILMES,
                    onClick = { navegarParaAba(navController, Rota.ListaFilmes) },
                    icon = { Icon(Icons.Filled.Movie, contentDescription = "Filmes") },
                    label = { Text("Filmes") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.ESTATISTICAS,
                    onClick = { navegarParaAba(navController, Rota.Estatisticas) },
                    icon = { Icon(Icons.Filled.BarChart, contentDescription = "Estatísticas") },
                    label = { Text("Estatísticas") }
                )
                NavigationBarItem(
                    selected = abaAtual == AbaPrincipal.PERFIL,
                    onClick = { navegarParaAba(navController, Rota.Perfil) },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { paddingValues ->
        when (abaAtual) {
            AbaPrincipal.FILMES -> ListaFilmesScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                aoAbrirDetalhe = { id -> navController.navigate(Rota.DetalheFilme(id)) }
            )
            AbaPrincipal.ESTATISTICAS -> EstatisticasScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel
            )
            AbaPrincipal.PERFIL -> PerfilScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                aoSair = {
                    viewModel.fazerLogout()
                    navController.navigate(Rota.Login) {
                        popUpTo(Rota.ListaFilmes) { inclusive = true }
                    }
                }
            )
        }
    }
}

private fun navegarParaAba(navController: NavHostController, destino: Rota) {
    navController.navigate(destino) {
        popUpTo(Rota.ListaFilmes) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
