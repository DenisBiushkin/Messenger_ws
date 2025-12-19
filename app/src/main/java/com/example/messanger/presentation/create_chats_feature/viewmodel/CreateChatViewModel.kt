package com.example.messanger.presentation.create_chats_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.presentation.create_chats_feature.model.CreateChatEffect
import com.example.messanger.presentation.create_chats_feature.model.CreateChatEvent
import com.example.messanger.presentation.create_chats_feature.model.CreateChatVMState
import com.example.messanger.presentation.create_chats_feature.model.UserForChatUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor(
    // private val userRepository: UserRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(CreateChatVMState())
    val state: StateFlow<CreateChatVMState> = _state.asStateFlow()
    
    private val _effects = MutableSharedFlow<CreateChatEffect>()
    val effects: SharedFlow<CreateChatEffect> = _effects.asSharedFlow()
    
    private var searchJob: Job? = null
    
    init {
        loadUsers()
    }
    
    fun onEvent(event: CreateChatEvent) {
        when (event) {
            is CreateChatEvent.SearchQueryChanged -> onSearchQueryChanged(event.query)
            is CreateChatEvent.UserSelected -> onUserSelected(event.userId)
            is CreateChatEvent.ChatNameChanged -> onChatNameChanged(event.name)
            CreateChatEvent.ToggleGroupChat -> toggleGroupChat()
            CreateChatEvent.CreateChat -> createChat()
            CreateChatEvent.LoadUsers -> loadUsers()
        }
    }
    
    private fun onSearchQueryChanged(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        
        if (query.isEmpty()) {
            _state.value = _state.value.copy(filteredUsers = _state.value.users)
            return
        }
        
        searchJob?.cancel()
        
        if (query.length >= 2) {
            searchJob = viewModelScope.launch {
                _state.value = _state.value.copy(isLoading = true)
                delay(300) // Debounce
                
                val filtered = _state.value.users.filter { user ->
                    user.name.contains(query, ignoreCase = true)
                }
                
                _state.value = _state.value.copy(
                    filteredUsers = filtered,
                    isLoading = false
                )
            }
        } else {
            val filtered = _state.value.users.filter { user ->
                user.name.contains(query, ignoreCase = true)
            }
            _state.value = _state.value.copy(filteredUsers = filtered)
        }
    }
    
    private fun onUserSelected(userId: String) {
        //текущее состояние
        val currentState = _state.value
        //Пользователь по id выбранного
        val user = currentState.users.find { it.id == userId } ?: return

        //отмечаем его как добавленного
        val updatedUsers = currentState.users.map {
            if (it.id == userId)
                it.copy(isSelected = !it.isSelected) else it
        }

        //в отфильтрованных тоже отмечаем
        val updatedFilteredUsers = currentState.filteredUsers.map {
            if (it.id == userId)
                it.copy(isSelected = !it.isSelected) else it
        }
        //Если участник выбран
        val selectedUsers = if (user.isSelected) {
            //что?
            currentState.selectedUsers - user
        } else {
            // Проверка для личного чата
            if (!currentState.isGroupChat && currentState.selectedUsers.size >= 1) {
                viewModelScope.launch {
                    _effects.emit(CreateChatEffect.Error("Для личного чата можно выбрать только одного пользователя"))
                }
                return
            }
            currentState.selectedUsers + user.copy(isSelected = true)
        }
        
        // Автоматически переключаем в групповой режим если выбрано >1 пользователя
        val isGroupChat = selectedUsers.size > 1
        //val isGroupChat = true

        val showNameInput = isGroupChat && currentState.chatName.isEmpty()
        
        _state.value = currentState.copy(
            users = updatedUsers,
            filteredUsers = updatedFilteredUsers,
            selectedUsers = selectedUsers,
            isGroupChat = isGroupChat,
            showNameInput = showNameInput
        )
    }
    
    private fun onChatNameChanged(name: String) {
        _state.value = _state.value.copy(chatName = name)
    }
    
    private fun toggleGroupChat() {
        val newIsGroupChat = !_state.value.isGroupChat
        val showNameInput = newIsGroupChat && _state.value.chatName.isEmpty()
        
        _state.value = _state.value.copy(
            isGroupChat = newIsGroupChat,
            showNameInput = showNameInput
        )
        
        // Если переключили с группового на личный и выбрано >1 пользователя
        if (!newIsGroupChat && _state.value.selectedUsers.size > 1) {
            viewModelScope.launch {
                _effects.emit(CreateChatEffect.Error("Для личного чата нужно оставить только одного пользователя"))
            }
        }
    }
    
    private fun createChat() {
        viewModelScope.launch {
            try {
                val currentState = _state.value
                
                // Валидация
                if (currentState.selectedUsers.isEmpty()) {
                    _effects.emit(CreateChatEffect.Error("Выберите хотя бы одного пользователя"))
                    return@launch
                }
                
                if (currentState.isGroupChat && currentState.chatName.isBlank()) {
                    _effects.emit(CreateChatEffect.Error("Введите название группы"))
                    return@launch
                }
                
                // Имитация создания чата
                delay(1000)
                
                val chatId = "chat_${System.currentTimeMillis()}"
                _effects.emit(CreateChatEffect.ChatCreated(chatId))
                
            } catch (e: Exception) {
                _effects.emit(CreateChatEffect.Error("Ошибка создания чата: ${e.message}"))
            }
        }
    }
    
    private fun loadUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            
            try {
                // Загрузка пользователей из репозитория
                delay(500) // Имитация загрузки
                
                val mockUsers = generateMockUsers()
                _state.value = _state.value.copy(
                    users = mockUsers,
                    filteredUsers = mockUsers,
                    isLoading = false
                )


            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Ошибка загрузки пользователей"
                )
            }
        }
    }
    
    private fun generateMockUsers(): List<UserForChatUi> {
        return listOf(
            UserForChatUi(id = "1", name = "Алексей Петров"),
            UserForChatUi(id = "2", name = "Мария Иванова"),
            UserForChatUi(id = "3", name = "Иван Сидоров"),
            UserForChatUi(id = "4", name = "Екатерина Волкова"),
            UserForChatUi(id = "5", name = "Дмитрий Козлов"),
            UserForChatUi(id = "6", name = "Анна Смирнова"),
            UserForChatUi(id = "7", name = "Сергей Николаев"),
            UserForChatUi(id = "8", name = "Ольга Михайлова"),
            UserForChatUi(id = "9", name = "Павел Федоров"),
            UserForChatUi(id = "10", name = "Наталья Захарова")
        )
    }
}