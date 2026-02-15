package com.montfel.fipe.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montfel.fipe.ui.search.SearchUiState
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    uiState: SearchUiState,
    onEvent: (SearchEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search FIPE") }
            )
        }
    ) { paddingValues ->
        val vehicleTypes = persistentListOf(
            FormDataItem("Carro", "1"),
            FormDataItem("Moto", "2"),
            FormDataItem("Caminhão", "3")
        )
        val options = persistentListOf(
            FormData(
                title = "Selecione o tipo do veículo",
                label = uiState.selectedVehicleType?.label,
                items = vehicleTypes,
                onItemClick = { onEvent(SearchEvent.OnVehicleTypeSelected(it)) }
            ),
            FormData(
                title = "Selecione mês e ano de referência",
                label = uiState.selectedReference?.label,
                items = uiState.referenceTable.map {
                    FormDataItem(
                        label = it.date,
                        value = it.code.toString()
                    )
                },
                onItemClick = { onEvent(SearchEvent.OnReferenceSelected(it)) }
            ),
            FormData(
                title = "Selecione a marca",
                label = uiState.selectedBrand?.label,
                items = uiState.brands.map {
                    FormDataItem(
                        label = it.name,
                        value = it.code
                    )
                },
                onItemClick = { onEvent(SearchEvent.OnBrandSelected(it)) }
            ),
            FormData(
                title = "Selecione o modelo",
                label = uiState.selectedModel?.label,
                items = uiState.models?.models?.map {
                    FormDataItem(
                        label = it.name,
                        value = it.code.toString()
                    )
                }.orEmpty(),
                onItemClick = { onEvent(SearchEvent.OnModelSelected(it)) }
            ),
            FormData(
                title = "Selecione o ano modelo",
                label = uiState.selectedYearModel?.label,
                items = uiState.yearModels.map {
                    FormDataItem(
                        label = it.name,
                        value = it.code
                    )
                },
                onItemClick = { onEvent(SearchEvent.OnYearModelSelected(it)) }
            )
        )

        Column(modifier = Modifier.padding(paddingValues)) {
            options.forEach {
                Card(onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(it.label ?: it.title)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = { onEvent(SearchEvent.OnVehicleSearch) }
            ) {
                Text("Consultar")
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        uiState = SearchUiState(),
        onEvent = {}
    )
}
