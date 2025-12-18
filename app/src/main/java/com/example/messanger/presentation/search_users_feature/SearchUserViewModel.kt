package com.example.messanger.presentation.search_users_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messanger.presentation.search_users_feature.model.UserItemUi
import com.example.messanger.presentation.search_users_feature.model.UsersScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
class SearchUserViewModel (

): ViewModel() {
    private val _state = MutableStateFlow(UsersScreenState())
    val state: StateFlow<UsersScreenState> = _state.asStateFlow()

    private var searchJob: Job? = null
    private val searchQueryFlow = MutableStateFlow("")

    init {
        // Настройка debounce для поиска
        setupSearchDebounce()
        // Загрузка всех пользователей при инициализации
        loadAllUsers()
    }

    private fun setupSearchDebounce() {
        searchQueryFlow
            .debounce(500) // Ждем 500ms после последнего ввода
            .distinctUntilChanged()
            .filter { query ->
                query.isNotEmpty() && query.length >= 2 // Ищем только если >= 2 символов
            }
            .onEach { query ->
                searchUsers(query)
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        searchQueryFlow.value = query

        if (query.isEmpty()) {
            // Если поиск очищен, показываем всех пользователей
            _state.value = _state.value.copy(filteredUsers = _state.value.users)
            return
        }

        // При начале ввода показываем локальный поиск
        if (query.length < 2) {
            val filtered = _state.value.users.filter { user ->
                user.name.contains(query, ignoreCase = true)
            }
            _state.value = _state.value.copy(filteredUsers = filtered)
        }
    }

    private fun loadAllUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                // Имитация загрузки данных
                delay(1000)

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

    private fun searchUsers(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                // Имитация поиска на сервере
                delay(800)

                // Здесь должен быть реальный API вызов
                // val searchResult = userRepository.searchUsers(query)

                // Для примера фильтруем локально
                val searchResult = _state.value.users.filter { user ->
                    user.name.contains(query, ignoreCase = true)

                }

                _state.value = _state.value.copy(
                    filteredUsers = searchResult,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Ошибка поиска"
                )
            }
        }
    }

    fun onUserClick(userId: String) {
        // Обработка клика на пользователя
        // Например, навигация в чат
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    private fun generateMockUsers(): List<UserItemUi> {
        return listOf(
            UserItemUi(
                id = "1",
                name = "Алексей Петров",
                avatarUrl = null,
                isOnline = true
            ),
            UserItemUi(
                id = "2",
                name = "Мария Иванова",
                avatarUrl = "https://example.com/avatar2.jpg",
                isOnline = false,
                lastSeen = "2 минуты назад"
            ),
            UserItemUi(
                id = "3",
                name = "Иван Сидоров",
                avatarUrl = null,
                isOnline = true
            ),
            UserItemUi(
                id = "4",
                name = "Екатерина Волкова",
                avatarUrl = null,
                isOnline = false,
                lastSeen = "5 часов назад"
            ),
            UserItemUi(
                id = "5",
                name = "Дмитрий Козлов",
                avatarUrl = "https://example.com/avatar5.jpg",
                isOnline = true
            ),
            UserItemUi(
                id = "6",
                name = "Анна Смирнова",
                avatarUrl = null,
                isOnline = false,
                lastSeen = "вчера"
            ),
            UserItemUi(
                id = "7",
                name = "Сергей Николаев",
                avatarUrl = null,
                isOnline = true
            ),
            UserItemUi(
                id = "8",
                name = "Ольга Михайлова",
                avatarUrl = "https://example.com/avatar8.jpg",
                isOnline = false,
                lastSeen = "только что"
            ),
            UserItemUi(
                id = "9",
                name = "Павел Федоров",
                avatarUrl = null,
                isOnline = true
            ),
            UserItemUi(
                id = "10",
                name = "Наталья Захарова",
                avatarUrl = null,
                isOnline = false,
                lastSeen = "30 минут назад"
            )
        )
    }
}