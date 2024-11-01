# Кроссплатформенная консольная игра "Two To Ten"
### Игра была опубликована в книге Стива Норта "More BASIC Computer Games" в 1979 году. Изначально написана на языке BASIC.
![image](https://github.com/user-attachments/assets/be22fb78-7550-4b82-9d55-4ad7937f2755)
## С помощью языка Kotlin, игра была портирована на разные платформы, такие как Android, Web, а также любая ОС, поддерживающая запуск .jar-файлов (Windows, Linux, MacOS, NetBSD и др.)
![image](https://github.com/user-attachments/assets/1ff7ec43-418a-46f3-b270-3c0b95ebd945)
<center><em>Структура проекта</em></center>

## Проект состоит из четырёх модулей: app (Android-приложение), shared (Общий модуль с логикой игры), desktop (Консольное приложение в виде .jar-файла) и website (модуль web-приложения)</br></br>Установка Android-приложения возможна двумя способами: первый - загрузить приложение на устройства с помощью Android Studio, второй - создать .apk-файл также с помощью Android Studio, а затем загрузить его на устройство
![image](https://github.com/user-attachments/assets/ece6abd1-c538-4026-88b3-f64c2fe4ed6d)
 <center><em>Android-приложение, запущенное с помощью эмулятора</em></center>
 
 ## Для запуска консольного приложения, сначала необходимо сгенерировать файл .jar, выполнив в терминале в корне проекта следующую команду:
 ```./gradlew :desktop:shadowJar```
## Затем, необходимо запустить .jar-файл через терминал в месте нахождения файла следующей командой:
```java -jar desktopGame.jar```
![image](https://github.com/user-attachments/assets/f8b222e6-3c25-4143-968e-19149e3e7041)
<center><em>Работа программы в консоли</em></center>

 ## Для запуска web-приложения, необходимо запустить его на локальном хосте следующими командами:
 ```
./gradlew :website:build 
./gradlew :website:run
```
## Web-версия приложения затем запустится по адресу http://0.0.0.0:8080/:
![image](https://github.com/user-attachments/assets/7768bc75-9087-482b-9d23-217e444caffa)
<center><em>Web-версия приложения</em></center>
