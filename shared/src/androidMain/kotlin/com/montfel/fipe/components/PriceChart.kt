package com.montfel.fipe.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.montfel.fipe.domain.model.VehicleInfo
import com.montfel.fipe.vehicledetails.toBRL
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import kotlin.math.roundToInt

@Composable
fun PriceChart(
    infos: List<VehicleInfo> = emptyList(),
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    val reversedInfos = infos.asReversed()
    val months = reversedInfos.map { it.referenceMonth.substring(0, 3) }
    val prices = reversedInfos.mapNotNull { it.price }
    val lowestRangePrice = prices.min().div(1000).roundToInt().times(1000).toDouble()

    LaunchedEffect(reversedInfos) {
        if (prices.isNotEmpty()) {
            modelProducer.runTransaction {
                lineSeries {
                    series(
                        x = prices.indices.map { it },
                        y = prices
                    )
                }
            }
        }
    }

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                rangeProvider = CartesianLayerRangeProvider.fixed(
                    minY = lowestRangePrice
                )
            ),
            startAxis = VerticalAxis.rememberStart(
                valueFormatter = { _, value, _ ->
                    value.toBRL()
                }
            ),
            bottomAxis = HorizontalAxis.rememberBottom(
                valueFormatter = { _, value, _ ->
                    val index = value.toInt()
                    if (index >= 0 && index < months.size) {
                        months[index]
                    } else {
                        ""
                    }
                }
            ),
        ),
        modelProducer = modelProducer,
    )
}
