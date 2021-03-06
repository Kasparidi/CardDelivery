### DESCRIPTION

Форма заказа карты

![img.png](src/test/resources/img.png)

Требования к содержимому полей:
* Город - один из административных центров субъектов РФ
* Дата - не ранее трёх дней с текущей даты
* Поле Фамилия и имя - разрешены только русские буквы, дефисы и пробелы
* Поле телефон - только цифры (11 цифр), символ + (на первом месте)
* Флажок согласия должен быть выставлен  

Важно: состояние загрузки не должно длиться более 15 секунд.  
После успешной отправки формы (завершения бронирования) появится всплывающее окно об успешном завершении бронирования:

![img_1.png](src/test/resources/img_1.png)

### [TASK](https://github.com/netology-code/aqa-homeworks/tree/master/selenide)

Автоматизированное тестирование формы  
Тестируемая функциональность: отправка формы.

А также проверить отправку, использую следующие условия:
1. Ввод 2 букв в поле город, после чего выбор нужного города из выпадающего списка.
1. Выбор даты на неделю вперёд (начиная от текущей даты) через инструмент календаря

### COMPLEXITY

Дату и время нельзя хардкодить.
Логика в тесте возможна.

### TOOLS

Java, JUnit5, Gradle, Selenide

[![Build status](https://ci.appveyor.com/api/projects/status/jjpc87uq6dv0haps?svg=true)](https://ci.appveyor.com/project/Kasparidi/carddelivery) CI AppVeyor

### CONCLUSION

[Доступен ввод без фамилии в поле "Фамилия и имя" при отправке заполненной формы](https://github.com/Kasparidi/CardDelivery/issues/1)
