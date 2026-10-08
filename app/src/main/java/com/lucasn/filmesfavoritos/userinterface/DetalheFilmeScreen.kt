package com.lucasn.filmesfavoritos.userinterface

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasn.filmesfavoritos.model.Filme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheFilmeScreen(filme: Filme?, aoVoltar: () -> Unit, aoAlternarAssistido: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(filme?.titulo ?: "Filme", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = aoVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        if (filme == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Filme não encontrado.")
            }
            return@Scaffold
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(filme.emoji, fontSize = 64.sp)
            Spacer(Modifier.height(16.dp))
            Text(
                filme.titulo,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text("${filme.ano} · ${filme.genero} · Nota ${filme.nota}/10", color = MaterialTheme.colorScheme.secondary)
            Spacer(Modifier.height(24.dp))
            Text(filme.sinopse, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            Spacer(Modifier.height(32.dp))
            Button(onClick = aoAlternarAssistido) {
                Text(if (filme.assistido) "Desmarcar como assistido" else "Marcar como assistido")
            }
        }
    }
}
