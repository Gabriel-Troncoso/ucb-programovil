package org.ucb.appp1.catalog.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.catalog.screen.composable.CharacterCard
import org.ucb.appp1.catalog.screen.viewmodel.CatalogEffect
import org.ucb.appp1.catalog.screen.viewmodel.CatalogEvent
import org.ucb.appp1.catalog.screen.viewmodel.CatalogViewModel

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val errorMessage = state.errorMessage

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CatalogEffect.ShowMessage -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F3F8))
            .safeContentPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
                Text(
                    text = "Star Wars Characters",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Characters found: ${state.totalCount}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            when {
                state.isLoading -> LoadingContent()
                errorMessage != null -> ErrorContent(
                    message = errorMessage,
                    onRetry = { viewModel.onEvent(CatalogEvent.Retry) }
                )
                else -> LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 28.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(
                        items = state.people,
                        key = { _, person -> person.id }
                    ) { index, person ->
                        CharacterCard(person)

                        if (index == state.people.lastIndex && state.nextPage != null) {
                            LaunchedEffect(person.id) {
                                viewModel.onEvent(CatalogEvent.LoadNextPage)
                            }
                        }
                    }

                    if (state.isLoadingMore) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CircularProgressIndicator()
            Text("Loading characters...")
        }
    }
}

@Composable
private fun ErrorContent(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Could not load characters",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(text = message)
            Button(onClick = onRetry) { Text("Retry") }
        }
    }
}
