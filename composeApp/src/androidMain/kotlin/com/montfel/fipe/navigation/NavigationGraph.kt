package com.montfel.fipe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.montfel.fipe.form.FormScreen
import com.montfel.fipe.home.HomeRoute
import com.montfel.fipe.search.SearchRoute
import com.montfel.fipe.vehicledetails.VehicleDetailsRoute

@Composable
internal fun NavigationGraph() {
    val backStack = rememberNavBackStack(Screen.Home)

    NavDisplay(
        backStack = backStack,
        onBack = backStack::removeLastOrNull,
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeRoute(
                    onNavigateToSearch = { backStack.add(Screen.Search) }
                )
            }
            entry<Screen.Search> {
                SearchRoute(
                    onNavigateToForm = { backStack.add(Screen.Form(formData = it)) },
                    onNavigateToVehicleDetails = { backStack.add(Screen.VehicleDetails(it)) },
                    onNavigateBack = backStack::removeLastOrNull
                )
            }
            entry<Screen.Form> {
                FormScreen(
                    formData = it.formData,
                    onNavigateBack = backStack::removeLastOrNull
                )
            }
            entry<Screen.VehicleDetails> {
                VehicleDetailsRoute(
                    searchData = it.searchData,
                    onNavigateBack = backStack::removeLastOrNull
                )
            }
        },
    )
}
