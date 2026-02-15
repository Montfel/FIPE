package com.montfel.fipe.vehicledetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.fipe.data.model.SearchData
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsUiState
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun VehicleDetailsRoute(
    searchData: SearchData,
    viewModel: VehicleDetailsViewModel = koinViewModel { parametersOf(searchData) }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    VehicleDetailsScreen(
        uiState = uiState,
        onEvent = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VehicleDetailsScreen(
    uiState: VehicleDetailsUiState,
    onEvent: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do veículo") }
            )
        },
        modifier = Modifier.navigationBarsPadding()
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            uiState.vehicleInfo?.let {
                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Valor")
                        Text(it.price)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Tipo do veículo")
                        Text(it.vehicleType.toString())
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Marca")
                        Text(it.brand)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Modelo")
                        Text(it.model)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Código FIPE")
                        Text(it.fipeCode)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Ano modelo")
                        Text(it.yearModel.toString())
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Combustível")
                        Text(it.fuel)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Mês de referência")
                        Text(it.referenceMonth)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Data da consulta")
                        Text(it.consultDate)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun VehicleDetailsScreenPreview() {
    VehicleDetailsScreen(
        uiState = VehicleDetailsUiState(),
        onEvent = {}
    )
}
