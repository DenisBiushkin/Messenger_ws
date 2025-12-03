package com.example.messanger.presentation.register_feature.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }

        if (digits.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        // Убираем первую цифру если это 8 или 7
        val phoneDigits = if (digits[0] == '8' || digits[0] == '7') {
            digits.drop(1)
        } else {
            digits
        }

        val formatted = buildString {
            append("+7")

            if (phoneDigits.isNotEmpty()) {
                append(" (")
                append(phoneDigits.take(3))
                append(")")

                if (phoneDigits.length > 3) {
                    append(" ")
                    append(phoneDigits.substring(3, minOf(6, phoneDigits.length)))

                    if (phoneDigits.length > 6) {
                        append("-")
                        append(phoneDigits.substring(6, minOf(8, phoneDigits.length)))

                        if (phoneDigits.length > 8) {
                            append("-")
                            append(phoneDigits.substring(8, minOf(10, phoneDigits.length)))
                        }
                    }
                }
            }
        }

        // Простой и безопасный OffsetMapping
        return TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // Преобразуем позицию в оригинальном тексте
                    // Ограничиваем максимальным значением
                    return minOf(
                        calculateTransformedOffset(offset, text.text, formatted),
                        formatted.length
                    )
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // Преобразуем позицию в отформатированном тексте
                    // Ограничиваем максимальным значением
                    return minOf(
                        calculateOriginalOffset(offset, text.text, formatted),
                        text.text.length
                    )
                }

                private fun calculateTransformedOffset(
                    originalOffset: Int,
                    originalText: String,
                    formattedText: String
                ): Int {
                    if (originalOffset == 0) return 0

                    // Считаем сколько цифр было введено до этой позиции
                    val digitsCount = originalText
                        .take(originalOffset)
                        .count { it.isDigit() }

                    // Для российского номера
                    return when (digitsCount) {
                        0 -> 0
                        1 -> 2  // после "+7"
                        2 -> 4  // в скобках
                        3 -> 5  // в скобках
                        4 -> 6  // в скобках
                        5 -> 8  // после пробела
                        6 -> 9  // после пробела
                        7 -> 10 // после пробела
                        8 -> 12 // после первого дефиса
                        9 -> 13 // после первого дефиса
                        10 -> 15 // после второго дефиса
                        11 -> 16 // после второго дефиса
                        else -> formattedText.length
                    }.coerceIn(0, formattedText.length)
                }

                private fun calculateOriginalOffset(
                    transformedOffset: Int,
                    originalText: String,
                    formattedText: String
                ): Int {
                    if (transformedOffset == 0) return 0

                    // Определяем в какой секции отформатированного текста находимся
                    val section = when {
                        transformedOffset <= 2 -> 0  // "+7"
                        transformedOffset <= 6 -> 1  // "(XXX)"
                        transformedOffset <= 10 -> 2 // " XXX"
                        transformedOffset <= 13 -> 3 // "-XX"
                        else -> 4                   // "-XX"
                    }

                    // Количество цифр в этой секции
                    val digitsInSection = when (section) {
                        0 -> 0  // "+7" - нет цифр
                        1 -> minOf(3, transformedOffset - 2) // в скобках
                        2 -> 3 + minOf(3, transformedOffset - 6) // после пробела
                        3 -> 6 + minOf(2, transformedOffset - 10) // после первого дефиса
                        4 -> 8 + minOf(2, transformedOffset - 13) // после второго дефиса
                        else -> 0
                    }

                    // Находим позицию в оригинальном тексте для этого количества цифр
                    var digitCount = 0
                    for (i in originalText.indices) {
                        if (originalText[i].isDigit()) {
                            digitCount++
                            if (digitCount == digitsInSection) {
                                return i + 1
                            }
                        }
                    }

                    return originalText.length
                }
            }
        )
    }
}