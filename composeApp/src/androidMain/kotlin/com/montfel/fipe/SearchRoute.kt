package com.montfel.fipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun SearchRoute() {
    SearchScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search FIPE") }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {

                    }
                ) {
                    Text("Consultar veículo")
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}
