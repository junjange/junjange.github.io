package junjange.dev.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

private const val WORD_JOINER = '⁠'

/**
 * 한글이 글자 단위로 줄바꿈되지 않도록 띄어쓰기 사이의 글자를 WORD JOINER(U+2060)로 묶는다.
 * 줄바꿈은 공백에서만 일어난다.
 */
fun String.keepWords(): String =
    buildString(length * 2) {
        this@keepWords.forEachIndexed { index, char ->
            if (index > 0 && !char.isWhitespace() && !this@keepWords[index - 1].isWhitespace()) {
                append(WORD_JOINER)
            }
            append(char)
        }
    }

fun String.keepWords(accents: List<Pair<String, Color>>): AnnotatedString {
    val colorAt = arrayOfNulls<Color>(length)
    accents.forEach { (word, color) ->
        val start = indexOf(word)
        if (start >= 0) {
            for (i in start until start + word.length) colorAt[i] = color
        }
    }

    return buildAnnotatedString {
        this@keepWords.forEachIndexed { index, char ->
            if (index > 0 && !char.isWhitespace() && !this@keepWords[index - 1].isWhitespace()) {
                append(WORD_JOINER)
            }
            val color = colorAt[index]
            if (color != null) {
                withStyle(SpanStyle(color = color)) { append(char) }
            } else {
                append(char)
            }
        }
    }
}
