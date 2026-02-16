package com.montfel.fipe.vehicledetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montfel.fipe.R
import com.montfel.fipe.data.model.VehicleInfo
import com.montfel.fipe.theme.font
import com.montfel.fipe.ui.vehicledetails.VehicleDetailsUiState
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun VehicleDetailsScreen(
    uiState: VehicleDetailsUiState,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Detalhes do veículo",
                        fontFamily = font
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        modifier = Modifier.navigationBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            uiState.vehicleInfo?.let {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it.brand,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF0F172A),
                        fontFamily = font
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = it.model,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color(0xFF64748B),
                        fontFamily = font
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = it.price,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 48.sp,
                        color = Color(0xFF0F172A),
                        fontFamily = font,
                        letterSpacing = -(2.4).sp
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                val items = persistentListOf(
                    Triple(R.drawable.ic_calendar, "Ano modelo", it.yearModel.toString()),
                    Triple(R.drawable.ic_fuel, "Combustível", it.fuel),
                    Triple(R.drawable.ic_123, "Código Fipe", it.fipeCode),
                    Triple(R.drawable.ic_car, "Tipo do veículo", it.vehicleType.toString()),
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            border = BorderStroke(width = 1.dp, color = Color(0xFFF1F5F9)),
                            modifier = Modifier.aspectRatio(1.3f)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(Color(0x101E3A8A))
                                ) {
                                    Icon(
                                        painter = painterResource(it.first),
                                        contentDescription = null,
                                        tint = Color(0xFF1E293B)
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Text(
                                    text = it.second,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = font,
                                    fontSize = 12.sp,
                                    color = Color(0xFF94A3B8)
                                )

                                Text(
                                    text = it.third,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    fontFamily = font,
                                    color = Color(0xFF0F172A)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun VehicleDetailsScreenPreview() {
    VehicleDetailsScreen(
        uiState = VehicleDetailsUiState(
            vehicleInfo = VehicleInfo(
                brand = "Fiat",
                model = "Uno",
                price = "R$ 30.000,00",
                vehicleType = 1,
                fipeCode = "123456",
                yearModel = 2020,
                fuel = "Gasolina",
                referenceMonth = "Janeiro de 2023",
                consultDate = "20/02/2023",
                authentication = "123",
                fuelAcronym = "F"
            )
        ),
        onNavigateBack = {}
    )
}
