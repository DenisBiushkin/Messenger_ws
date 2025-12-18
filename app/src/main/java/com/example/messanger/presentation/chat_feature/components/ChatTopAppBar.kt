package com.example.messanger.presentation.chat_feature.components

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.messanger.presentation.chat_feature.model.ChatUserUi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material.icons.filled.MoreVert

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar(
    chatUserUi: ChatUserUi,
    isOnline: Boolean = false,
    lastSeen: String? = null,
    primaryColor: Color = Color(0xFF7E57C2),
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { /* Переход в профиль */ }
            ) {
                // Аватар
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(chatUserUi.getAvatarColor()),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chatUserUi.avatarInitial,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = chatUserUi.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (isOnline) {
                        Text(
                            text = "онлайн",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF34C759),
                            maxLines = 1
                        )
                    } else if (lastSeen != null) {
                        Text(
                            text = "был(а) $lastSeen",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF888888),
                            maxLines = 1
                        )
                    }
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Назад",
                    tint = primaryColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = primaryColor,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    )
}