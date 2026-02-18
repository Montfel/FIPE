package com.montfel.fipe.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.montfel.fipe.shared.resources.Res
import com.montfel.fipe.shared.resources.inter_bold
import com.montfel.fipe.shared.resources.inter_extrabold
import com.montfel.fipe.shared.resources.inter_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun getFont() = FontFamily(
    Font(Res.font.inter_semibold, FontWeight.SemiBold),
    Font(Res.font.inter_bold, FontWeight.Bold),
    Font(Res.font.inter_extrabold, FontWeight.ExtraBold)
)
