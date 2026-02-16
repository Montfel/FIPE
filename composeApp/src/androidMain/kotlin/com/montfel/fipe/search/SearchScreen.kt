package com.montfel.fipe.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montfel.fipe.R
import com.montfel.fipe.theme.font
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem
import com.montfel.fipe.ui.search.SearchUiState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    isByFipe: Boolean,
    uiState: SearchUiState,
    onEvent: (SearchEvent) -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onEvent(SearchEvent.OnNavigateBack) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Pesquisar pelo veículo",
                        fontFamily = font
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { onEvent(SearchEvent.OnVehicleSearch) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E3A8A),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Consultar veículo",
                    fontFamily = font
                )
            }
        },
        modifier = Modifier.navigationBarsPadding()
    ) { paddingValues ->
        val vehicleTypes = persistentListOf(
            FormDataItem("Carro", "1"),
            FormDataItem("Moto", "2"),
            FormDataItem("Caminhão", "3")
        )
        val fields = mutableListOf(
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
        ).apply {
            if (isByFipe) {
                add(
                    index = 2,
                    FormData(
                        title = "AAAA",
                        label = uiState.selectedBrand?.label,
                        items = uiState.brands.map {
                            FormDataItem(
                                label = it.name,
                                value = it.code
                            )
                        },
                        onItemClick = { onEvent(SearchEvent.OnBrandSelected(it)) }
                    )
                )
            } else {
                addAll(
                    index = 2,
                    listOf(
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
                    )
                )
            }
        }.toPersistentList()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            fields.forEach {
                Card(onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = it.label ?: it.title,
                            fontFamily = font
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenIsByFipePreview() {
    SearchScreen(
        isByFipe = true,
        uiState = SearchUiState(),
        onEvent = {}
    )
}

@Preview
@Composable
private fun SearchScreenIsNotByFipePreview() {
    SearchScreen(
        isByFipe = false,
        uiState = SearchUiState(),
        onEvent = {}
    )
}
