package com.lucasn.filmesfavoritos.userinterface

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.lucasn.filmesfavoritos.model.Rota
import com.lucasn.filmesfavoritos.viewmodel.FilmeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: FilmeViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rota.Splash) {

        composable<Rota.Splash> {
            SplashScreen(
                aoTerminar = {
                    val destino = if (viewModel.estaLogado) Rota.ListaFilmes else Rota.Login
                    navController.navigate(destino) {
                        popUpTo(Rota.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Rota.Login> {
            LoginScreen(
                aoLogar = {
                    viewModel.fazerLogin()
                    navController.navigate(Rota.ListaFilmes) {
                        popUpTo(Rota.Login) { inclusive = true }
                    }
                }
            )
        }

        composable<Rota.ListaFilmes> {
            PainelPrincipal(navController, viewModel, AbaPrincipal.FILMES)
        }

        composable<Rota.Estatisticas> {
            PainelPrincipal(navController, viewModel, AbaPrincipal.ESTATISTICAS)
        }

        composable<Rota.Perfil> {
            PainelPrincipal(navController, viewModel, AbaPrincipal.PERFIL)
        }

        composable<Rota.DetalheFilme> { backStackEntry ->
            val rota: Rota.DetalheFilme = backStackEntry.toRoute()
            DetalheFilmeScreen(
                filme = viewModel.buscarFilme(rota.filmeId),
                aoVoltar = { navController.popBackStack() },
                aoAlternarAssistido = { viewModel.alternarAssistido(rota.filmeId) }
            )
        }
    }
}
