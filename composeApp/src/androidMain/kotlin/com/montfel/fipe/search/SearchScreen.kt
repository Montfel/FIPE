package com.montfel.fipe.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montfel.fipe.domain.model.VehicleType
import com.montfel.fipe.shared.resources.Res
import com.montfel.fipe.shared.resources.brand
import com.montfel.fipe.shared.resources.consult_vehicle
import com.montfel.fipe.shared.resources.fipe_code_not_found
import com.montfel.fipe.shared.resources.fipe_code_placeholder
import com.montfel.fipe.shared.resources.ic_arrow_left
import com.montfel.fipe.shared.resources.ic_car
import com.montfel.fipe.shared.resources.ic_chevron_right
import com.montfel.fipe.shared.resources.model
import com.montfel.fipe.shared.resources.month_reference
import com.montfel.fipe.shared.resources.search_by_fipe
import com.montfel.fipe.shared.resources.search_by_vehicle
import com.montfel.fipe.shared.resources.vehicle_type
import com.montfel.fipe.shared.resources.year_model
import com.montfel.fipe.theme.getFont
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem
import com.montfel.fipe.ui.search.SearchUiState
import com.montfel.fipe.ui.theme.Colors.color4
import com.montfel.fipe.ui.theme.Colors.color7
import com.montfel.fipe.ui.theme.Colors.color8
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    isByFipe: Boolean,
    uiState: SearchUiState,
    onEvent: (SearchEvent) -> Unit
) {
    Scaffold(
        containerColor = Color(color7),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onEvent(SearchEvent.OnNavigateBack) }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    val title =
                        if (isByFipe) Res.string.search_by_fipe else Res.string.search_by_vehicle
                    Text(
                        text = stringResource(title),
                        fontFamily = getFont(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            Button(
                enabled = uiState.shouldEnableButton,
                onClick = { onEvent(SearchEvent.OnVehicleSearch) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(color4),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = stringResource(Res.string.consult_vehicle),
                    fontFamily = getFont()
                )
            }
        },
        modifier = Modifier.navigationBarsPadding()
    ) { paddingValues ->
        val vehicleTypes = persistentListOf(
            FormDataItem("Carro", VehicleType.CAR.code),
            FormDataItem("Moto", VehicleType.MOTORCYCLE.code),
            FormDataItem("Caminhão", VehicleType.TRUCK.code)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            FormCard(
                isEnabled = true,
                icon = Res.drawable.ic_car,
                value = uiState.selectedReference?.label,
                formData = FormData(
                    title = stringResource(Res.string.month_reference),
                    items = uiState.referenceTable.map {
                        FormDataItem(
                            label = it.date,
                            value = it.code
                        )
                    }.toPersistentList(),
                    onItemClick = { onEvent(SearchEvent.OnReferenceSelected(it)) }
                ),
                onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }
            )

            FormCard(
                isEnabled = true,
                icon = Res.drawable.ic_car,
                value = uiState.selectedVehicleType?.label,
                formData = FormData(
                    title = stringResource(Res.string.vehicle_type),
                    items = vehicleTypes,
                    onItemClick = { onEvent(SearchEvent.OnVehicleTypeSelected(it)) }
                ),
                onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }
            )

            if (isByFipe) {
                TextField(
                    enabled = uiState.shouldEnableFipeCode,
                    value = uiState.selectedFipeCode.orEmpty(),
                    onValueChange = { onEvent(SearchEvent.OnFipeCodeChanged(it)) },
                    placeholder = {
                        Text(stringResource(Res.string.fipe_code_placeholder))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    visualTransformation = FipeCodeVisualTransformation(),
                    isError = uiState.hasFipeCodeError,
                    supportingText = {
                        if (uiState.hasFipeCodeError) {
                            Text(stringResource(Res.string.fipe_code_not_found))
                        }
                    }
                )
            } else {
                FormCard(
                    isEnabled = uiState.shouldEnableBrand,
                    icon = Res.drawable.ic_car,
                    value = uiState.selectedBrand?.label,
                    formData = FormData(
                        title = stringResource(Res.string.brand),
                        items = uiState.brands.map {
                            FormDataItem(
                                label = it.name,
                                value = it.code
                            )
                        }.toPersistentList(),
                        onItemClick = { onEvent(SearchEvent.OnBrandSelected(it)) }
                    ),
                    onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }
                )

                FormCard(
                    isEnabled = uiState.shouldEnableModel,
                    icon = Res.drawable.ic_car,
                    value = uiState.selectedModel?.label,
                    formData = FormData(
                        title = stringResource(Res.string.model),
                        items = uiState.models?.models?.map {
                            FormDataItem(
                                label = it.name,
                                value = it.code
                            )
                        }.orEmpty().toPersistentList(),
                        onItemClick = { onEvent(SearchEvent.OnModelSelected(it)) }
                    ),
                    onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }
                )
            }

            FormCard(
                isEnabled = uiState.shouldEnableYearModel,
                icon = Res.drawable.ic_car,
                value = uiState.selectedYearModel?.label,
                formData = FormData(
                    title = stringResource(Res.string.year_model),
                    items = uiState.yearModels.map {
                        FormDataItem(
                            label = it.name,
                            value = it.code
                        )
                    }.toPersistentList(),
                    onItemClick = { onEvent(SearchEvent.OnYearModelSelected(it)) }
                ),
                onClick = { onEvent(SearchEvent.OnNavigateToForm(it)) }
            )
        }
    }
}

@Composable
fun FormCard(
    isEnabled: Boolean,
    icon: DrawableResource,
    value: String?,
    formData: FormData,
    onClick: (formData: FormData) -> Unit,
) {
    Card(
        enabled = isEnabled,
        onClick = { onClick(formData) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            disabledContainerColor = Color(color8)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(color4).copy(alpha = 0.1f))
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = Color(color4)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = formData.title,
                        fontSize = 12.sp,
                        fontFamily = getFont()
                    )

                    value?.let {
                        Text(
                            text = value,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = getFont()
                        )
                    }
                }
            }

            Icon(
                painter = painterResource(Res.drawable.ic_chevron_right),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun SearchScreenIsNotByFipePreview() {
    SearchScreen(
        isByFipe = false,
        uiState = SearchUiState(
            selectedReference = FormDataItem(label = "fevereiro/2026", value = "330"),
            selectedVehicleType = FormDataItem(label = "Carro", value = VehicleType.CAR.code),
            selectedBrand = FormDataItem(label = "Fiat", value = "fiat"),
            selectedModel = FormDataItem(label = "Uno", value = "uno"),
            selectedYearModel = FormDataItem(label = "2022", value = "2022"),
        ),
        onEvent = {}
    )
}

@Preview
@Composable
private fun SearchScreenIsByFipePreview() {
    SearchScreen(
        isByFipe = true,
        uiState = SearchUiState(
            selectedReference = FormDataItem(label = "fevereiro/2026", value = "330"),
            selectedVehicleType = FormDataItem(label = "Carro", value = VehicleType.CAR.code),
            selectedYearModel = FormDataItem(label = "2022", value = "2022"),
        ),
        onEvent = {}
    )
}
