package com.montfel.fipe.search

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class FipeCodeVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 7) text.text.substring(0..6) else text.text
        var out = ""
        
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i == 5) out += "-"
        }

        val fipeOffsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 5) return offset
                if (offset <= 7) return offset + 1
                return 8
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 6) return offset
                if (offset <= 8) return offset - 1
                return 7
            }
        }

        return TransformedText(AnnotatedString(out), fipeOffsetMapping)
    }
}
