package com.lucasn.filmesfavoritos.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lucasn.filmesfavoritos.viewmodel.FilmeViewModel

@Composable
fun EstatisticasScreen(modifier: Modifier = Modifier, viewModel: FilmeViewModel) {
    val total = viewModel.filmes.size
    val assistidos = viewModel.filmes.count { it.assistido }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Estatísticas", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        Text("$assistidos de $total filmes assistidos", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = { if (total == 0) 0f else assistidos.toFloat() / total },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}
