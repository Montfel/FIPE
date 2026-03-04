package com.montfel.fipe.form

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import com.montfel.fipe.shared.resources.Res
import com.montfel.fipe.shared.resources.ic_arrow_left
import com.montfel.fipe.shared.resources.ic_chevron_right
import com.montfel.fipe.shared.resources.ic_search
import com.montfel.fipe.shared.resources.search
import com.montfel.fipe.theme.getFont
import com.montfel.fipe.ui.model.FormData
import com.montfel.fipe.ui.model.FormDataItem
import com.montfel.fipe.ui.model.FormDataType
import com.montfel.fipe.ui.theme.Colors.color10
import com.montfel.fipe.ui.theme.Colors.color3
import com.montfel.fipe.ui.theme.Colors.color6
import com.montfel.fipe.ui.theme.Colors.color7
import com.montfel.fipe.ui.theme.Colors.color9
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FormScreen(
    formData: FormData,
    onNavigateBack: (Pair<FormDataItem, FormDataType>?) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val filteredItems = formData.items.filter {
        it.label.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        containerColor = Color(color7),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = dropUnlessResumed { onNavigateBack(null) }) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = formData.title,
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
        Column(modifier = Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(bottom = 24.dp)
            ) {
                TextField(
                    value = searchText,
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_search),
                            contentDescription = null,
                            tint = Color(color6)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(color3),
                        unfocusedContainerColor = Color(color3)
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.search),
                            color = Color(color6),
                            fontSize = 16.sp,
                            fontFamily = getFont()
                        )
                    },
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp),
                contentPadding = PaddingValues(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = filteredItems,
                    key = FormDataItem::value
                ) { item ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(width = 1.dp, color = Color(color9)),
                        onClick = dropUnlessResumed { onNavigateBack(item to formData.type) },
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 56.dp)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = item.label,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                fontFamily = getFont(),
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                painter = painterResource(Res.drawable.ic_chevron_right),
                                contentDescription = null,
                                tint = Color(color10)
                            )
                        }
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
            items = persistentListOf(
                FormDataItem("Marca 1", "1"),
                FormDataItem("Marca 2", "2"),
            ),
            type = FormDataType.BRAND
        ),
        onNavigateBack = {}
    )
}
