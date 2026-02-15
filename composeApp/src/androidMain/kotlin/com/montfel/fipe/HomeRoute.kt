package com.montfel.fipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.montfel.fipe.ui.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToSearch: () -> Unit
) {
    HomeScreen(
        onEvent = { event ->
            when (event) {
                HomeEvent.OnSearchClicked -> onNavigateToSearch()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consulta FIPE") }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onEvent(HomeEvent.OnSearchClicked) }
                ) {
                    Text("Consultar veículo")
                }
            }
        },
        modifier = Modifier.navigationBarsPadding()

    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onEvent = {})
}
