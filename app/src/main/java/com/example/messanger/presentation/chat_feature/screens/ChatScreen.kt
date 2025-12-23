
import androidx.compose.runtime.Composable
import com.example.messanger.presentation.chat_feature.model.ChatUserUi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.messanger.presentation.chat_feature.components.ChatBottomBar
import com.example.messanger.presentation.chat_feature.components.ChatTopAppBar
import com.example.messanger.presentation.chat_feature.components.ErrorMessage
import com.example.messanger.presentation.chat_feature.components.LoadingScreen
import com.example.messanger.presentation.chat_feature.components.MessagesList
import com.example.messanger.presentation.chat_feature.model.ChatVMState

@Composable
fun ChatScreen(
    chatUserUi: ChatUserUi,
    uiState: ChatVMState,
    onSendMessage: (String) -> Unit,
    onInputChange: (String) -> Unit,
    onBackClick:()->Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier= Modifier.fillMaxSize(),
        topBar = {
            ChatTopAppBar(
                chatUserUi = chatUserUi,
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            ChatBottomBar(
                currentInput = uiState.currentUserInput,
                onInputChange = onInputChange,
                onSendMessage = { text ->
                    onSendMessage(text)
                    //onInputChange("")
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Переключение между загрузкой и списком сообщений
            if (uiState.isLoading) {
                LoadingScreen()
            } else {
                MessagesList(
                    messages = uiState.messageUis,
                    currentUserId = chatUserUi.id,
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            // Отображение ошибки, если есть
            uiState.errorMessage?.let { error ->
                ErrorMessage(
                    message = error,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}