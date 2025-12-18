package com.example.messanger.presentation.profile_feature.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ExitToApp

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messanger.data.network.dto.user.AvatarDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale







@Composable
fun AvatarCarousel(
    avatars: List<AvatarDto>,
    onAddAvatar: () -> Unit,
    onAvatarClick: (AvatarDto) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Мои аватары",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(avatars) { avatar ->
                AvatarItem(
                    avatar = avatar,
                    onClick = { onAvatarClick(avatar) },
                    modifier = Modifier.size(80.dp)
                )
            }
            
            item {
                AddAvatarButton(
                    onClick = onAddAvatar,
                    modifier = Modifier.size(80.dp)
                )
            }
        }
    }
}
@Composable
fun LogoutButton(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onLogout,
        modifier = modifier.height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Icon(
            imageVector = Icons.Default.ExitToApp,
            contentDescription = "Logout",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Выйти из аккаунта",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}





fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy 'в' HH:mm", Locale("ru"))
        val date = inputFormat.parse(dateString) ?: Date()
        outputFormat.format(date)
    } catch (e: Exception) {
        dateString
    }
}

private fun Modifier.offsetY(y: Float) = this.offset(y = y.dp)