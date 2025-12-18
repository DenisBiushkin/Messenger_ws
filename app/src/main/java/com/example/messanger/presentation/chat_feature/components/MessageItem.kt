package com.example.messanger.presentation.chat_feature.components



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messanger.presentation.chat_feature.model.ChatTheme
import com.example.messanger.presentation.chat_feature.model.MessageUi
import com.example.messanger.util.ChatThemes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MessageItem(
    messageUi: MessageUi,
    currentUserId: String,
    theme: ChatTheme = ChatThemes.PurpleTheme,
    modifier: Modifier = Modifier
) {
    val isMyMessage = messageUi.isMine
    val configuration = LocalConfiguration.current
    val maxWidth = configuration.screenWidthDp.dp * theme.maxMessageWidthPercent

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalAlignment = if (isMyMessage) Alignment.End else Alignment.Start
    ) {
        // Имя отправителя
        if (!isMyMessage) {
            Text(
                text = messageUi.senderName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = theme.senderNameColor,
                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
            )
        }

        // Сообщение
        Box(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .clip(RoundedCornerShape(theme.messageCornerRadius))
                .background(
                    color = messageUi.getMessageColor(theme)
                )
                .shadow(
                    elevation = theme.messageElevation,
                    shape = RoundedCornerShape(theme.messageCornerRadius),
                    spotColor = theme.primaryColor.copy(alpha = 0.3f)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = messageUi.text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 15.sp,
                    lineHeight = 21.sp
                ),
                color = messageUi.getTextColor(theme)
            )
        }

        // Время и статус прочтения
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 2.dp)
        ) {
            if (isMyMessage && messageUi.isRead) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "Прочитано",
                    tint = theme.primaryColor,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }

            Text(
                text = formatTimestamp(messageUi.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = theme.timestampColor,
                modifier = Modifier.padding(start = if (isMyMessage) 0.dp else 4.dp)
            )
        }
    }
}
private fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}