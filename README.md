# TestTaskPlayTox
Общее описание: Необходимо разработать приложение в соответствии с изложенными ниже требованиями.

# Общие требования
Архитектура - Java SE 8.0 (или выше), использование библиотек и фреймворков на усмотрение исполнителя.
Должна быть система логирования (на основе готового решения, например Log4j). Приложение должно логировать в файл любые действия, приводящие к изменению данных. Приложение должно корректно обрабатывать и логировать ошибки.
# Структура данных
В приложении должна быть сущность Account (счет) содержащая поля:
ID (строковое) - идентификатор счета
Money (целочисленное) - сумма средств на счете.
# Функциональные требования
При запуске приложение должно создать два экземпляра объекта Account со случайными значениями ID и значениями money равным 10000.
В приложении запускается два независимых потока. Потоки должны просыпаться каждые 1000-2000 мс. Время на которое засыпает поток выбирается случайно при каждом исполнении.
Потоки должны выполнять перевод средств с одного счета на другой. Сумма списания или зачисления определяется случайным образом. Поле money не должно становиться отрицательным.
Результаты всех транзакций должны записываться в лог. 
После 30 выполненных транзакций приложение должно завершиться.
