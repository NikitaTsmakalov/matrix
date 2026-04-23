# Миграция части UI с XML на Compose

Этот документ описывает, что было сделал в проекте, зачем это делалось и почему такой подход важен для дальнейшего развития приложения с моей точки зрения.

## Что именно было переписано на Compose

В рамках текущего этапа на Compose вынесены не все экраны, а конкретные UI-блоки в уже существующих XML-экранах, но в дальнейшем в основном продукте новые экраны можно писать сразу на компоузе:

- `Home`:
    - кластер FAB-кнопок (`HomeFabCluster`) в `NewHomeDetailFragment`
    - кнопки "создать чат" и "открыть список пространств"
    - бейдж непрочитанных для Spaces
- `Timeline`:
    - чип "Jump to first unread" (`JumpToReadMarkerChip`) в `TimelineFragment`
- `Spaces`:
    - пустое состояние списка пространств (`SpaceListEmptyState`) в `SpaceListFragment`

Новые Compose-компоненты:

- `vector/src/main/java/im/vector/app/features/home/compose/HomeFabCluster.kt`
- `vector/src/main/java/im/vector/app/features/home/room/detail/compose/JumpToReadMarkerChip.kt`
- `vector/src/main/java/im/vector/app/features/spaces/compose/SpaceListEmptyState.kt`

## Как Compose внедрен в проект, который изначально на XML, впринципе тут неособо сложно

Внедрение сделал без полного переписывания экранов:

1. В XML-макеты добавлены `ComposeView` контейнеры:
    - `fragment_new_home_detail.xml`
    - `fragment_timeline.xml`
    - `fragment_space_list.xml`
2. Во фрагментах Compose подключается через `setContent { ... }`.
3. Используется `ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed`, чтобы композиция корректно уничтожалась вместе с жизненным циклом View и не создавала утечек.
4. Существующая архитектура сохранена. Compose-компоненты получают только необходимые данные и колбэки.


Почему это правильно и полезно для продукта в дальнейшем, помимо того что XML капец как устарел и писать на нём медленно:

- **Минимальный риск**: бизнес-логика и экранные сценарии не ломаются, меняется только слой UI.
- **Лучше тестируемость UI**: появляется нативный Compose UI Testing для новых блоков.
- **Плавная миграция**: можем переводить экран за экраном, без остановки текущей разработки.

## Какие тесты добавлены

### Unit-тесты

- `vector/src/test/java/im/vector/app/features/home/compose/HomeFabClusterTest.kt`
    - проверка форматирования бейджа (`badgeText`)
    - проверка кейса с нулевым количеством непрочитанных

### Android UI-тесты (Compose)

- `vector/src/androidTest/java/im/vector/app/features/home/compose/HomeFabClusterUiTest.kt`
    - проверка кликов по FAB и вызова колбэков
- `vector/src/androidTest/java/im/vector/app/features/home/room/detail/compose/JumpToReadMarkerChipTest.kt`
    - проверка скрытого и видимого состояний чипа
- `vector/src/androidTest/java/im/vector/app/features/spaces/compose/SpaceListEmptyStateTest.kt`
    - проверка локализованного контента пустого состояния

## Почему Compose сейчас более актуален, чем XML

- Новые библиотеки и API в первую очередь развиваются вокруг Compose.
- Меньше шаблонного кода и выше скорость итераций.
- Проще поддерживать единый стиль компонентов и дизайн-систему.
- Легче масштабировать, когда компоненты сделаны декларативно и переиспользуемо.



Чтобы полноценно прогонять тесты, нужно запускать проект на `JDK 21`.

## Приложение фото - коммит базовое ТЗ

![photo1](../vector/src/main/res/drawable/photo1.jpg)

![photo2](../vector/src/main/res/drawable/photo2.jpg)
