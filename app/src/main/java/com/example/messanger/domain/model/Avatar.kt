package com.example.messanger.domain.model

import java.time.LocalDateTime

data class Avatar(
    val id: Int = 1,
    val originalName: String = "",
    val url: String="",
    val createdAt: String=""
) {
    val fileExtension: String
        get() = originalName.substringAfterLast('.', "")
    
    val isImage: Boolean
        get() = fileExtension in listOf("jpg", "jpeg", "png", "gif", "webp")
}