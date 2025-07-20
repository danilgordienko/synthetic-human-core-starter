# synthetic-human-core-starter

**synthetic-human-core-starter** — это Spring Boot стартер, предоставляющий готовые решения для управления, мониторинга и аудита команд, обрабатываемых синтетическими "андроидами".

---

## Описание стартера

### 1) Обработка команд  
Используйте `CommandHandler` для централизованной обработки команд.

### 2) Аудит вызовов  
Просто добавьте аннотацию `@WeylandWatchingYou` к нужному методу.

### 3) Метрики  
`AndroidMetrics` автоматически регистрирует метрики:

| Метрика                      | Описание                                  |
|-----------------------------|-------------------------------------------|
| `android.task.queue.size`   | Текущий размер очереди команд             |
| `android.commands.executed` | Количество команд, исполненных по авторам (tag: author) |

---

## Сборка стартера

```bash
mvn clean install
```

## Подключение
Добавьте зависимость в ваш pom.xml:

```
<dependency>
    <groupId>ru.danilgordienko</groupId>
    <artifactId>synthetic-human-core-starter</artifactId>
    <version>4.0.0</version>
</dependency>
