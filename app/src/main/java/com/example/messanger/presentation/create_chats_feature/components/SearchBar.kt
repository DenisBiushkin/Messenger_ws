package com.example.messanger.presentation.create_chats_feature.components
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Поиск")
        },
        placeholder = { Text("Поиск пользователей...") },
        singleLine = true,
        shape = MaterialTheme.shapes.medium
    )
}
