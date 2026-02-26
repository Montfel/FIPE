package com.montfel.fipe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.montfel.fipe.form.FormScreen
import com.montfel.fipe.home.HomeRoute
import com.montfel.fipe.search.SearchRoute
import com.montfel.fipe.ui.model.FormDataItem
import com.montfel.fipe.ui.model.FormDataType
import com.montfel.fipe.vehicledetails.VehicleDetailsRoute

@Composable
internal fun NavigationGraph() {
    val backStack = rememberNavBackStack(Screen.Home)
    val resultContainer = retain { ResultContainer() }

    NavDisplay(
        backStack = backStack,
        onBack = dropUnlessResumed(block = backStack::removeLastOrNull),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Screen.Home> {
                HomeRoute(
                    onNavigateToSearch = { isByFipe ->
                        backStack.add(Screen.Search(isByFipe = isByFipe))
                    }
                )
            }
            entry<Screen.Search> { key ->
                val formDataItem = resultContainer.getFormDataItem()
                val formDataType = resultContainer.getFormDataType()
                resultContainer.clear()

                SearchRoute(
                    isByFipe = key.isByFipe,
                    formDataItem = formDataItem,
                    formDataType = formDataType,
                    onNavigateToForm = { formData ->
                        backStack.add(Screen.Form(formData = formData))
                    },
                    onNavigateToVehicleDetails = { searchRequest ->
                        backStack.add(Screen.VehicleDetails(searchRequest))
                    },
                    onNavigateBack = dropUnlessResumed(block = backStack::removeLastOrNull),
                )
            }
            entry<Screen.Form> { key ->
                FormScreen(
                    formData = key.formData,
                    onNavigateBack = {
                        resultContainer.setFormDataItem(it?.first)
                        resultContainer.setFormDataType(it?.second)
                        backStack.removeLastOrNull()
                    }
                )
            }
            entry<Screen.VehicleDetails> { key ->
                VehicleDetailsRoute(
                    searchRequest = key.searchRequest,
                    onNavigateBack = dropUnlessResumed(block = backStack::removeLastOrNull),
                )
            }
        },
    )
}

class ResultContainer {
    private var formDataItem: FormDataItem? = null
    private var formDataType: FormDataType? = null

    fun setFormDataItem(data: FormDataItem?) {
        this.formDataItem = data
    }

    fun getFormDataItem(): FormDataItem? = formDataItem

    fun setFormDataType(data: FormDataType?) {
        this.formDataType = data
    }

    fun getFormDataType(): FormDataType? = formDataType

    fun clear() {
        formDataItem = null
        formDataType = null
    }
}
