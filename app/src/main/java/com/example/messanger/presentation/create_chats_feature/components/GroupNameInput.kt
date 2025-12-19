package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupNameInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text("Название группы") },
        placeholder = { Text("Введите название группы") },
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}