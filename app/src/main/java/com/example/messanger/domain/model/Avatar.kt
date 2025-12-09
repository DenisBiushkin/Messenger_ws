package com.example.messanger.domain.model

import java.time.LocalDateTime

data class Avatar(
    val id: Int,
    val originalName: String,
    val url: String,
    val createdAt: LocalDateTime
) {
    val fileExtension: String
        get() = originalName.substringAfterLast('.', "")
    
    val isImage: Boolean
        get() = fileExtension in listOf("jpg", "jpeg", "png", "gif", "webp")
}