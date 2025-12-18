package com.example.messanger.presentation.chat_feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Send

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.messanger.presentation.chat_feature.model.ChatTheme
import com.example.messanger.util.ChatThemes


@Composable
fun ChatBottomBar(
    currentInput: String,
    onInputChange: (String) -> Unit,
    onSendMessage: (String) -> Unit,
    onAttachClick: () -> Unit = {},
    theme: ChatTheme = ChatThemes.PurpleTheme,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .navigationBarsPadding()
            .imePadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onAttachClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = "Прикрепить файл",
                tint = theme.primaryColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .border(
                    1.dp,
                    theme.primaryColor.copy(alpha = 0.3f),
                    RoundedCornerShape(24.dp)
                )
        ) {
            BasicTextField(
                value = currentInput,
                onValueChange = onInputChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF333333)
                ),
                maxLines = 4,
                cursorBrush = SolidColor(theme.primaryColor),
                decorationBox = { innerTextField ->
                    Box {
                        if (currentInput.isEmpty()) {
                            Text(
                                text = "Сообщение...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF888888)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }

        IconButton(
            onClick = {
                if (currentInput.isNotBlank()) {
                    onSendMessage(currentInput)
                }
            },
            modifier = Modifier.size(48.dp),
            enabled = currentInput.isNotBlank()
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        if (currentInput.isNotBlank()) {
                            theme.primaryColor
                        } else {
                            Color(0xFFE0E0E0)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Отправить",
                    tint = if (currentInput.isNotBlank()) {
                        Color.White
                    } else {
                        Color(0xFF9E9E9E)
                    },
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}