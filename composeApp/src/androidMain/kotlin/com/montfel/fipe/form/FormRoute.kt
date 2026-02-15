package com.montfel.fipe.form

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem

//
//@Composable
//fun FormRoute() {
////    FormScreen()
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FormScreen(
    formData: FormData,
    onNavigateBack: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val filteredItems = formData.items.filter {
        it.label.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(formData.title) }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
        ) {
            item {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            items(
                items = filteredItems,
                key = FormDataItem::value
            ) { item ->
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    onClick = {
                        formData.onItemClick(item)
                        onNavigateBack()
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(item.label)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FormScreenPreview() {
    FormScreen(
        formData = FormData(
            title = "Selecione a Marca",
            label = null,
            items = listOf(
                FormDataItem("Marca 1", "1"),
                FormDataItem("Marca 2", "2"),
            ),
            onItemClick = {}
        ),
        onNavigateBack = {}
    )
}
