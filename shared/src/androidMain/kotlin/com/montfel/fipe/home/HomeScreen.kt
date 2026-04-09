package com.montfel.fipe.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.lifecycle.compose.dropUnlessResumed
import com.montfel.fipe.domain.model.VehicleInfo
import com.montfel.fipe.domain.model.VehicleType
import com.montfel.fipe.shared.resources.Res
import com.montfel.fipe.shared.resources.app_name
import com.montfel.fipe.shared.resources.by_fipe
import com.montfel.fipe.shared.resources.by_vehicle
import com.montfel.fipe.shared.resources.consult_your_vehicle
import com.montfel.fipe.shared.resources.fipe_concept
import com.montfel.fipe.shared.resources.ic_123
import com.montfel.fipe.shared.resources.ic_car
import com.montfel.fipe.shared.resources.ic_chevron_right
import com.montfel.fipe.shared.resources.recent_searches
import com.montfel.fipe.shared.resources.what_is_fipe
import com.montfel.fipe.theme.getFont
import com.montfel.fipe.ui.home.HomeUiState
import com.montfel.fipe.ui.theme.Colors.color1
import com.montfel.fipe.ui.theme.Colors.color2
import com.montfel.fipe.ui.theme.Colors.color3
import com.montfel.fipe.ui.theme.Colors.color4
import com.montfel.fipe.ui.theme.Colors.color5
import com.montfel.fipe.ui.theme.Colors.color7
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        containerColor = Color(color7),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        fontFamily = getFont(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .padding(24.dp)
                .padding(paddingValues)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(width = 1.dp, color = Color(color3)),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.what_is_fipe),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(color5),
                        fontFamily = getFont()
                    )

                    Text(
                        text = stringResource(Res.string.fipe_concept),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color(color2),
                        fontFamily = getFont()
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = stringResource(Res.string.consult_your_vehicle),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(color1),
                    fontFamily = getFont()
                )

                HomeCard(
                    icon = Res.drawable.ic_car,
                    title = stringResource(Res.string.by_vehicle),
                    onClick = dropUnlessResumed { onEvent(HomeEvent.OnSearchByVehicleClicked) }
                )

                HomeCard(
                    icon = Res.drawable.ic_123,
                    title = stringResource(Res.string.by_fipe),
                    onClick = dropUnlessResumed { onEvent(HomeEvent.OnSearchByFipeClicked) }
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                uiState.recentSearches?.let { recentSearches ->
                    Text(
                        text = stringResource(Res.string.recent_searches),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color(color1),
                        fontFamily = getFont()
                    )

                    recentSearches.forEach {
                        HomeCard(
                            icon = Res.drawable.ic_123,
                            title = it.model,
                            description = it.brand,
                            onClick = dropUnlessResumed { onEvent(HomeEvent.OnSearchByFipeClicked) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeCard(
    icon: DrawableResource,
    title: String,
    description: String? = null,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(width = 1.dp, color = Color(color3)),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(color4).copy(alpha = 0.1f))
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = Color(color4)
                    )
                }

                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(color5),
                        fontFamily = getFont()
                    )

                    description?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color(color2),
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
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(
            recentSearches = listOf(
                VehicleInfo(
                    brand = "Chevrolet",
                    model = "Onix",
                    price = 2.3,
                    yearModel = 1980,
                    fuel = "animal",
                    fipeCode = "comprehensam",
                    referenceMonth = "definitionem",
                    authentication = "doming",
                    vehicleType = VehicleType.CAR,
                    fuelAcronym = "nostrum",
                    consultDate = "minim",
                ),
                VehicleInfo(
                    brand = "Chevrolet",
                    model = "Onix",
                    price = 2.3,
                    yearModel = 1980,
                    fuel = "animal",
                    fipeCode = "comprehensam",
                    referenceMonth = "definitionem",
                    authentication = "doming",
                    vehicleType = VehicleType.CAR,
                    fuelAcronym = "nostrum",
                    consultDate = "minim",
                )
            )
        ),
        onEvent = {}
    )
}
