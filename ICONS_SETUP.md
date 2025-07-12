# Настройка иконок приложения

## Обзор

Из исходного файла `ic_app.png` (22.7KB) были автоматически сгенерированы иконки для всех
поддерживаемых платформ:

## Android

- **Расположение**: `composeApp/src/androidMain/res/mipmap-*/`
- **Файлы**: `ic_launcher.png` и `ic_launcher_round.png`
- **Размеры**:
    - mdpi: 48x48
    - hdpi: 72x72
    - xhdpi: 96x96
    - xxhdpi: 144x144
    - xxxhdpi: 192x192

## iOS

- **Расположение**: `iosApp/iosApp/Assets.xcassets/AppIcon.appiconset/`
- **Файлы**: `Icon-*.png`
- **Размеры**: 20x20, 29x29, 40x40, 60x60, 76x76, 83.5x83.5, 1024x1024
- **Обновлен**: `Contents.json` для правильного отображения иконок

## WASM/Web

- **Расположение**: `composeApp/src/wasmJsMain/resources/`
- **Файлы**:
    - `favicon-16x16.png`, `favicon-32x32.png`
    - `icon-192x192.png`, `icon-512x512.png`
    - `favicon.ico`
- **Обновлен**: `index.html` с ссылками на иконки
- **Создан**: `manifest.json` для PWA поддержки

## Автоматическая генерация

Использовался скрипт с ImageMagick для создания всех размеров из одного исходного файла:

- Android: обычные и круглые иконки
- iOS: полный набор для iPhone, iPad и App Store
- WASM: favicon и PWA иконки

## Следующие шаги

1. Протестировать иконки на всех платформах
2. При необходимости заменить исходный файл `ic_app.png` на финальную версию
3. Повторно запустить генерацию при изменении дизайна иконки