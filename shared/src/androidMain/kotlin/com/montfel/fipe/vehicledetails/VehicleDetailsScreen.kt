package com.montfel.fipe.vehicledetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montfel.fipe.components.PriceChart
import com.montfel.fipe.domain.model.VehicleInfo
import com.montfel.fipe.domain.model.VehicleType
import com.montfel.fipe.shared.resources.Res
import com.montfel.fipe.shared.resources.car
import com.montfel.fipe.shared.resources.fipe_code
import com.montfel.fipe.shared.resources.fuel
import com.montfel.fipe.shared.resources.ic_123
import com.montfel.fipe.shared.resources.ic_arrow_left
import com.montfel.fipe.shared.resources.ic_calendar
import com.montfel.fipe.shared.resources.ic_calendar_next
import com.montfel.fipe.shared.resources.ic_car
import com.montfel.fipe.shared.resources.ic_fuel
import com.montfel.fipe.shared.resources.ic_trending_down
import com.montfel.fipe.shared.resources.ic_trending_up
import com.montfel.fipe.shared.resources.motorcycle
import com.montfel.fipe.shared.resources.price_history
import com.montfel.fipe.shared.resources.reference_month
import com.montfel.fipe.shared.resources.truck
import com.montfel.fipe.shared.resources.vehicle_details
import com.montfel.fipe.shared.resources.vehicle_type
import com.montfel.fipe.shared.resources.year_model
import com.montfel.fipe.theme.getFont
import com.montfel.fipe.ui.theme.Colors.color1
import com.montfel.fipe.ui.theme.Colors.color2
import com.montfel.fipe.ui.theme.Colors.color3
import com.montfel.fipe.ui.theme.Colors.color5
import com.montfel.fipe.ui.theme.Colors.color6
import com.montfel.fipe.ui.theme.Colors.color7
import com.montfel.fipe.ui.theme.Colors.green1
import com.montfel.fipe.ui.theme.Colors.green2
import com.montfel.fipe.ui.theme.Colors.red1
import com.montfel.fipe.ui.theme.Colors.red2
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsUiState
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun VehicleDetailsScreen(
    uiState: VehicleDetailsUiState,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        containerColor = Color(color7),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(Res.string.vehicle_details),
                        fontFamily = getFont(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        modifier = Modifier.navigationBarsPadding()
    ) { paddingValues ->
        uiState.vehiclesInfo?.firstOrNull()?.let { vehicleInfo ->
            val vehicleTypeName = stringResource(
                when (vehicleInfo.vehicleType) {
                    VehicleType.CAR -> Res.string.car
                    VehicleType.MOTORCYCLE -> Res.string.motorcycle
                    VehicleType.TRUCK -> Res.string.truck
                }
            )
            val items = persistentListOf(
                InfoGridItemData(
                    icon = Res.drawable.ic_calendar,
                    title = Res.string.reference_month,
                    value = vehicleInfo.referenceMonth
                ),
                InfoGridItemData(
                    icon = Res.drawable.ic_car,
                    title = Res.string.vehicle_type,
                    value = vehicleTypeName
                ),
                InfoGridItemData(
                    icon = Res.drawable.ic_calendar_next,
                    title = Res.string.year_model,
                    value = vehicleInfo.yearModel.toString()
                ),
                InfoGridItemData(
                    icon = Res.drawable.ic_fuel,
                    title = Res.string.fuel,
                    value = vehicleInfo.fuel
                ),
                InfoGridItemData(
                    icon = Res.drawable.ic_123,
                    title = Res.string.fipe_code,
                    value = vehicleInfo.fipeCode
                ),
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                modifier = Modifier.padding(paddingValues)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = vehicleInfo.brand,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color(color5),
                            fontFamily = getFont()
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = vehicleInfo.model,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            color = Color(color2),
                            fontFamily = getFont()
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        vehicleInfo.price?.let {
                            Text(
                                text = it.toBRL(),
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 48.sp,
                                color = Color(color5),
                                fontFamily = getFont(),
                                letterSpacing = -(2.4).sp
                            )
                        }
                    }
                }

                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    uiState.difference?.let { difference ->
                        val isPositive = difference.contains("+")

                        val (containerColor, contentColor, icon) = if (isPositive) {
                            Triple(green1, green2, Res.drawable.ic_trending_up)
                        } else {
                            Triple(red1, red2, Res.drawable.ic_trending_down)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(containerColor),
                                contentColor = Color(contentColor)
                            ),
                            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                            ) {
                                Icon(
                                    painterResource(icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                )

                                Text(
                                    text = difference,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 14.sp,
                                    fontFamily = getFont(),
                                )
                            }
                        }
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(width = 1.dp, color = Color(color3)),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = stringResource(Res.string.price_history),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                fontFamily = getFont(),
                                color = Color(color5),
                            )

                            PriceChart(infos = uiState.vehiclesInfo)
                        }
                    }
                }

                items(items) { item ->
                    InfoGridItem(item = item)
                }
            }
        }
    }
}

private data class InfoGridItemData(
    val icon: DrawableResource,
    val title: StringResource,
    val value: String
)

@Composable
private fun InfoGridItem(
    item: InfoGridItemData,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(width = 1.dp, color = Color(color3)),
        modifier = Modifier.aspectRatio(1.2f)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(color3))
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    tint = Color(color1)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(item.title),
                fontWeight = FontWeight.Medium,
                fontFamily = getFont(),
                fontSize = 12.sp,
                color = Color(color6)
            )

            Text(
                text = item.value,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = getFont(),
                color = Color(color5)
            )
        }
    }
}

@Preview
@Composable
private fun VehicleDetailsScreenPreview() {
    VehicleDetailsScreen(
        uiState = VehicleDetailsUiState(
            vehiclesInfo = persistentListOf(
                VehicleInfo(
                    brand = "Fiat",
                    model = "Uno",
                    price = 30_000.00,
                    vehicleType = VehicleType.CAR,
                    fipeCode = "123456",
                    yearModel = 2020,
                    fuel = "Gasolina",
                    referenceMonth = "Janeiro de 2023",
                    consultDate = "20/02/2023",
                    authentication = "123",
                    fuelAcronym = "F"
                )
            ),
            difference = "-1,5% no último mês"
        ),
        onNavigateBack = {}
    )
}


fun Double.toBRL(): String {
    val totalSats = (this * 100).toLong()
    val absoluteValue = if (totalSats < 0) -totalSats else totalSats

    val reais = absoluteValue / 100
    val centavos = absoluteValue % 100

    val centavosString = centavos.toString().padStart(2, '0')

    val reaisString = reais.toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()

    return "R$ $reaisString,$centavosString"
}
