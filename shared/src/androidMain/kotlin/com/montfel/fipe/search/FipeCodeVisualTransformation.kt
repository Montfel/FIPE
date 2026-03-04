package com.montfel.fipe.search

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class FipeCodeVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val fipeCodeMask = text.text.mapIndexed { index, char ->
            when (index) {
                5 -> "${char}-"
                else -> char
            }
        }.joinToString("")

        val fipeCodeOffsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset > 5) offset.inc() else offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset > 5) offset.dec() else offset
            }
        }

        return TransformedText(AnnotatedString(fipeCodeMask), fipeCodeOffsetMapping)
    }
}
