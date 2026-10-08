package com.lucasn.filmesfavoritos.userinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasn.filmesfavoritos.model.Filme
import com.lucasn.filmesfavoritos.viewmodel.FilmeViewModel

@Composable
fun ListaFilmesScreen(
    modifier: Modifier = Modifier,
    viewModel: FilmeViewModel,
    aoAbrirDetalhe: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Meus filmes favoritos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )

        if (viewModel.filmes.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Nenhum filme na lista ainda.", color = MaterialTheme.colorScheme.secondary)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.filmes, key = { it.id }) { filme ->
                    CardFilme(
                        filme = filme,
                        aoClicar = { aoAbrirDetalhe(filme.id) },
                        aoAlternar = { viewModel.alternarAssistido(filme.id) },
                        aoRemover = { viewModel.removerFilme(filme.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CardFilme(filme: Filme, aoClicar: () -> Unit, aoAlternar: () -> Unit, aoRemover: () -> Unit) {
    Card(
        onClick = aoClicar,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(filme.emoji, fontSize = 22.sp)
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    filme.titulo,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "${filme.ano} · ${filme.genero}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(onClick = aoAlternar) {
                Icon(
                    imageVector = if (filme.assistido) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                    contentDescription = if (filme.assistido) "Desmarcar como assistido" else "Marcar como assistido",
                    tint = if (filme.assistido) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(onClick = aoRemover) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remover filme",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
