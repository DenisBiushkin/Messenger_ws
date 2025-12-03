package com.example.messanger.presentation.profile_feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange

import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Phone

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.messanger.data.network.dto.AvatarDto
import com.example.messanger.data.network.dto.UserDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserInfoCard(
    user: UserDto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Информация о профиле",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // ID пользователя
            InfoRow(
                icon = Icons.Default.Email,
                title = "ID пользователя",
                value = user.id.toString()
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Телефон
            InfoRow(
                icon = Icons.Default.Phone,
                title = "Телефон",
                value = user.phone
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Дата регистрации
            InfoRow(
                icon = Icons.Default.DateRange,
                title = "Зарегистрирован",
                value = formatDate(user.created_at)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Количество аватаров
            InfoRow(
                icon = Icons.Default.AccountCircle,
                title = "Аватаров",
                value = user.avatars.size.toString()
            )
        }
    }
}