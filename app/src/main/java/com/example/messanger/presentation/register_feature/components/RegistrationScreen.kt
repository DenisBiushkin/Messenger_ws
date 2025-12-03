package com.example.messanger.presentation.register_feature.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messanger.presentation.register_feature.models.RegistrationEvent
import com.example.messanger.presentation.register_feature.models.RegisterVMState
import com.example.messanger.presentation.register_feature.util.PhoneVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    state: RegisterVMState,
    onEvent: (RegistrationEvent) -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Поле для имени
        NameField(
            name = state.name,
            onNameChange = { onEvent(RegistrationEvent.NameChanged(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для телефона
        PhoneField(
            phone = state.phone,
            onPhoneChange = { onEvent(RegistrationEvent.PhoneChanged(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для пароля
        PasswordField(
            password = state.password,
            onPasswordChange = { onEvent(RegistrationEvent.PasswordChanged(it)) },
            passwordVisible = state.passwordVisible,
            onPasswordVisibilityToggle = { onEvent(RegistrationEvent.TogglePasswordVisibility) },
            label = "Пароль",
            placeholder = "Введите ваш пароль"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для подтверждения пароля
        PasswordField(
            password = state.passwordConfirmation,
            onPasswordChange = { onEvent(RegistrationEvent.PasswordConfirmationChanged(it)) },
            passwordVisible = state.confirmPasswordVisible,
            onPasswordVisibilityToggle = { onEvent(RegistrationEvent.ToggleConfirmPasswordVisibility) },
            label = "Подтверждение пароля",
            placeholder = "Повторите ваш пароль"
        )

        // Отображение ошибки
        state.errorMessage?.let { errorMessage ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка регистрации
        RegistrationButton(
            isLoading = state.isLoading,
            onRegisterClick = { onEvent(RegistrationEvent.Register) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Ссылки для навигации
        NavigationLinksRegistration(
            onNavigateToLogin = onNavigateToLogin
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NameField(
    name: String,
    onNameChange: (String) -> Unit
) {

    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = "Имя",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Введите ваше имя")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Name",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhoneField(
    phone: String,
    onPhoneChange: (String) -> Unit
) {
    OutlinedTextField(
        value = phone,
        onValueChange = onPhoneChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = "Телефон",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Введите ваш телефон")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit,
    label: String,
    placeholder: String
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(placeholder)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        trailingIcon = {
            IconButton(onClick = onPasswordVisibilityToggle) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Lock else Icons.Default.Build,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
private fun RegistrationButton(
    isLoading: Boolean,
    onRegisterClick: () -> Unit
) {
    Button(
        onClick = onRegisterClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.onPrimary,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = "Зарегистрироваться",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun NavigationLinksRegistration(
    onNavigateToLogin: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ссылка "Уже есть аккаунт?"
        Text(
            text = "Уже есть аккаунт?",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )

        TextButton(
            onClick = onNavigateToLogin
        ) {
            Text(
                text = "Войти",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
