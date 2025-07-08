# LoginComponent

LoginComponent создан на основе архитектуры Decompose для переиспользования между Android и iOS
платформами.

## Структура

- `LoginComponent` - интерфейс компонента
- `DefaultLoginComponent` - реализация компонента
- `LoginUiState` - состояние UI для логина

## Использование

### Создание компонента

```kotlin
val loginComponent = DefaultLoginComponent(
    componentContext = DefaultComponentContext(
        lifecycle = LifecycleRegistry()
    ),
    onLoginSuccess = { /* Обработка успешного входа */ },
    onNavigateToForgotPassword = { /* Навигация на восстановление пароля */ },
    onNavigateToNetworkSettings = { /* Навигация на настройки сети */ }
)
```

### Наблюдение за состоянием

```kotlin
// Простое наблюдение за состоянием
var state by remember { mutableStateOf(LoginUiState()) }

LaunchedEffect(loginComponent) {
    loginComponent.state.subscribe { newState ->
        state = newState
    }
}
```

### Методы компонента

- `updateLogin(login: String)` - обновление логина
- `updatePassword(password: String)` - обновление пароля
- `updateLoginIn(isLoginIn: Boolean)` - переключение запомнить вход
- `updateDebugMenuExpanded(expanded: Boolean)` - переключение debug меню
- `onClickLogin()` - обработка нажатия кнопки входа
- `onClickForgotPassword()` - обработка нажатия "Забыли пароль?"
- `onClickNetworkSettings()` - обработка нажатия настроек сети

## Преимущества

1. **Переиспользуемость** - один код для Android и iOS
2. **Lifecycle-aware** - корректно обрабатывает жизненный цикл
3. **Reactive** - автоматически обновляет UI при изменении состояния
4. **Тестируемость** - легко тестировать бизнес-логику отдельно от UI

## Интеграция с DI

В реальном приложении LoginComponent следует создавать через DI фреймворк (например, Koin):

```kotlin
// В модуле DI
factory { params ->
    DefaultLoginComponent(
        componentContext = params.get(),
        onLoginSuccess = params.get(),
        onNavigateToForgotPassword = params.get(),
        onNavigateToNetworkSettings = params.get()
    )
}
```