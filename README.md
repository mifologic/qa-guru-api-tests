# Проект автоматизации тестирования API на сайте reqres.in

Проект выполнен в рамках курса по автоматизации школы <a href="https://qa.guru">QA.Guru</a>.

<img src="/images/reqres.png">

## Содержание:

* <a href="#link-технологии-и-инструменты">Технологии и инструменты</a>
* <a href="#link-реализованные-проверки">Реализованные проверки</a>
* <a href="#link-сборка-в-jenkins">Сборка в Jenkins</a>
* <a href="#link-запуск-из-инструменты-командной-строки">Запуск из командной строки</a>
* <a href="#link-allure-отчет">Allure отчет</a>
* <a href="#link-отчет-в-telegram">Отчет в Telegram</a>


## :link: Технологии и инструменты
В проекте используются:

<p align="center">
<img width="6%" src="/images/icons/Java.svg">
<img width="6%" src="/images/icons/Intelij_IDEA.svg">
<img width="6%" src="/images/icons/Gradle.svg">
<img width="6%" src="/images/icons/JUnit5.svg">
<img width="6%" src="/images/icons/Selenoid.svg">
<img width="6%" src="/images/icons/GitHub.svg">
<img width="6%" src="/images/icons/Allure_Report.svg">
<img width="6%" src="/images/icons/Telegram.svg">
</p>

Тесты написаны на Java с использованием фреймворков Selenide, JUnit 5 и Allure. Для сборки проекта используется Gradle.

## :link: Реализованные проверки

- [x] Проверка имени и фамилии пользователя
- [x] Проверка случая, когда пользователь не найден
- [x] Создание нового пользователя
- [x] Редактирование пользователя
- [x] Удаление пользователя 

## :link: Запуск из командной строки
Реализована возможность как локального, так и удалённого запуска.

Для запуска используется команда:
`gradle clean test`

В отдельный config-file вынесены настройки для запуска: адрес сайта, основной путь, одинаковый для всех тестов.

Пример файла с настройками можно посмотреть в src/test/resources/config, файл exampleCredentials.properties.

## :link: Сборка в Jenkins
Для удалённого запуска можно настроить запуск в Jenkins. Пример прогона:

<img src="/images/reports/jenkins_report.png">

## :link: Allure отчет
Результат прогона тестов можно посмотреть в отчёте. Информация в тесте представлена в виде шагов с действиями.

<img src="/images/reports/allure_report.png">

## :link: Отчет в Telegram
Результат прохождения тестов отправляется в Telegram с помощью бота. Пример сообщения с отчётом:

<p align="center">
<img src="/images/reports/telegram_report.png">
</p>