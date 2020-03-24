##### *Development based on this specification*:

Приложение должно представлять собой web – сервис для управления всеми необходимыми данными, представляющими ресурсы конкретной организации: 

1.	Данные о платежах.
2.	Списки сотрудников и заказчиков.
3.	Списки заказов.
4.	Списки задач, наборы которых составляют заказы.
5.	Историю заработных плат каждого из сотрудников. 
6.	Информацию о планируемом и актуальном времени выполнения того или иного заказа.
7.	Списки контактных лиц заказчиков.
8.	Информацию о номерах телефонах и электронных почт сотрудников и контактных лиц. 

Полное описание всех данных, которые необходимо хранить в БД:

1.	Компании. У каждой компании обязательные поля: 
•	Название
•	Регион (из выпадающего списка регионов РФ)
•	ИНН
•	Адрес
•	Контактное лицо (должна быть возможность создать до 5ти конт лиц к одной компании, у каждого конт лица можно занести до 3х телефонов и почтовых адресов)
•	Степень надежности (возможны 3 варианта - Низкий, Средний, Высокий)
•	Название Группы в WhatsApp
•	Тип компании (Наше, Заказчик, Остальные)

2.	Платежи по объектам. У каждого платежа обязательные поля: 
•	Дата
•	Наименование компании (из списка компаний)
•	Наименование нашей компании (из списка компаний с типов “Наше”) 
•	Название объекта
•	Операция (от -9,99 млрд руб до 9,99 млрд руб)
•	Безнал (да/нет)
•	Необязательное поле: Комментарий
 
3.	Сотрудники. У каждого сотрудника обязательные поля: 
•	ФИО
•	Регион (из выпадающего списка регионов РФ)
•	Номер телефона (можно занести до 3х телефонов)
•	Электронный почтовый адрес (можно занести до 3х почтовых адресов)
•	Необязательное поле: Адрес

4.	Платежи сотрудников. У каждого платежа обязательные поля: 
•	Дата
•	ФИО (из списка сотрудников)
•	Контрагент (либо выбирается компания из списка компаний, либо сотрудник из списка сотрудников)
•	Операция (от -9,99 млрд руб до 9,99 млрд руб)
•	Тип платежа (Нал/Безнал/Начисление/Списание)
•	Необязательное поле: Комментарий

5.	Зарплаты. У каждой записи обязательные поля: 
•	ФИО (из списка сотрудников)
•	Дата начала
•	Дата окончания 
•	Оклад

6.	Объекты. У каждого объекта обязательные поля: 
•	Заказчик (из списка компаний)
•	Название 
•	Оплата по безналу (да/нет)
•	Договор нужен (да/нет) 
•	Договор есть (да/нет) 
•	Плановая дата начала
•	Фактическая дата начала
•	Плановая дата завершения
•	Фактическая дата завершения
•	Сумма 
•	Тип (Проект/Индивидуальная Документация/Смета/Юр. услуги)
•	Порядок оплаты (два или три числа, сумма которых равна 100%, например 40-60, или 30-40-30 или 100)
•	Кол-во строк (только если тип=смета)
•	Группа (из выпадающего списка групп, на данный момент есть 001-005, потом будут 006, 007 и т.д.)
•	Менеджер по работе с клиентами (из списка сотрудников)
•	В работе (да/нет)

7.	Планируемое время. У каждой записи обязательные поля: 
•	Название объекта (из списка объектов)
•	ФИО (из списка сотрудников)
•	Планируемое время

8.	Фактическое время. У каждой записи обязательные поля: 
•	Название объекта (из списка объектов)
•	ФИО (из списка сотрудников)
•	Дата
•	Фактическое время
•	Учетное время

9.	Задачи. У каждой задачи обязательные поля: 
•	Название объекта (из списка объектов)
•	Задача
•	Ответственное лицо  (из списка сотрудников)
•	Дата выполнения
•	Результат (Выполнено/Не выполнено/ Частично выполнено)
•	Необязательное поле: Комментарий ответственного
•	Необязательное поле: Кому РГ (выбирается заказчик или сотрудник и выбирается эл. почта, можно выбрать до 3х эл. почт)
•	Необязательное поле: Кому Менеджер (выбирается заказчик или сотрудник и выбирается эл. почта, можно выбрать до 3х эл. почт)

Роли пользователей приложения:

В приложении необходимо реализовать роли «Обычного пользователя» и Администратора. И тот и другой в системе являются сотрудниками. Администратор имеет все те права, что есть у обычного пользователя, но ко всему прочему имеет возможность редактировать данные  о других сотрудниках. 

Описание функционала приложения: 

* Если не указано, что функция доступна только для администратора, то доступна всем сотрудникам (пользователям)

Слева в меню основные пункты:

*	Личная информация: 
        *	Должна содержаться личная информация (ФИО),  телефон, почта и т.д.
        *	Возможность сменить пароль 
        *	Логин назначается администратором
*	Компании:
        *	Должен содержаться весь список
        *	С возможностью фильтра по:
                *	Региону
                *	Названию
                *	степени надежности
                *	типу компании
        *	С возможностью поиска по: 
                *	ИНН
                *	Адрес,
                *	Названию 
                *	Контактному лицу
*	Сотрудники:
        *	Должен содержаться весь список
        *	С возможностью фильтра по:
                *	Логину
        *	С возможностью поиска по: 
                *	Региону
                *	Адресу
                *	ФИО
        *	Должна быть возможность перейти глубже в сотрудника и увидеть все платежи сотрудника
        *	У администратора должна быть возможность корректировать данные по самому сотруднику: логин, номер телефона, электронная почта, зарплата
*	Объекты:
        *	Должен содержаться весь список
        *	С возможностью фильтра по:
                *	Названию
                *	Заказчику
                *	Оплата должна быть
                *	Оплата по безналу
                *	Тип
                *	Группа
                *	Договор нужен
                *	Договор есть
                *	Плановая дата начала 
                *	Фактическая дата начала
                *	Плановая дата завершения
                *	Фактическая дата завершения
                *	Сумма
                *	Порядок оплаты
                *	Кол-во строк
                *	Менеджер по работе с клиентами
                *	В работе
        *	Есть возможность перейти в список платежей по конкретному объекту, а также в планируемое, фактическое время по объекту и список задач по объекту.
*	Финансы:
        *	Можно выбрать дату и выводятся все платежи по сотрудникам и объектам с разделением на эту дату
*	Выход
