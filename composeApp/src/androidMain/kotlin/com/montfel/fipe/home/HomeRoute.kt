package com.montfel.fipe.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.montfel.fipe.theme.font

@Composable
internal fun HomeRoute(
    onNavigateToSearch: (isByFipe: Boolean) -> Unit
) {
    HomeScreen(
        onEvent = { event ->
            when (event) {
                HomeEvent.OnSearchByFipeClicked -> onNavigateToSearch(true)
                HomeEvent.OnSearchByVehicleClicked -> onNavigateToSearch(false)
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
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Busca Fipe",
                        fontFamily = font
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(paddingValues)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(width = 1.dp, color = Color(0xFFF1F5F9)),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "O que é a Tabela FIPE?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF0F172A),
                        fontFamily = font
                    )

                    Text(
                        text = "É a referência de preços de veículos no Brasil, atualizada mensalmente pela Fundação Instituto de Pesquisas Econômicas.",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color(0xFF64748B),
                        fontFamily = font
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Consulte o seu veículo",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0xFF1E293B),
                fontFamily = font
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(width = 1.dp, color = Color(0xFFF1F5F9)),
                onClick = { onEvent(HomeEvent.OnSearchByVehicleClicked) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color(0x101E3A8A))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_car),
                                contentDescription = null,
                                tint = Color(0xFF1E3A8A)
                            )
                        }

                        Text(
                            text = "Pelo veículo",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF0F172A),
                            fontFamily = font
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(width = 1.dp, color = Color(0xFFF1F5F9)),
                onClick = { onEvent(HomeEvent.OnSearchByFipeClicked) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color(0x101E3A8A))
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_123),
                                contentDescription = null,
                                tint = Color(0xFF1E3A8A)
                            )
                        }

                        Text(
                            text = "Pelo código FIPE",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color(0xFF0F172A),
                            fontFamily = font
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_chevron_right),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onEvent = {})
}
