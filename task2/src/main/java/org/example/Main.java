package org.example;

public class Main {

    public static void main(String[] args) {
        NetworkClient client = new NetworkClient();
        // 1. Клиент изначально отключён
        System.out.println("\nНачальное состояние");
        System.out.println(client.getCurrentState());
        // 2. Начинаем подключение
        System.out.println("\nПодключение");
        client.connect();
        // 3. Подключение успешно
        System.out.println("\nУспешное соединение");
        client.connectionEstablished();
        // 4. Отправляем сообщение
        System.out.println("\nОтправка сообщения");
        client.sendMessage("Привет, сервер!");
        // 5. Теряем соединение
        System.out.println("\nПотеря соединения");
        client.disconnect();
        // 6. Происходит тайм-аут переподключения
        System.out.println("\nПовторная попытка ");
        client.onTimeout();
        // 7. Переподключение успешно
        System.out.println("\n Переподключение ");
        client.connectionEstablished();
        // 8. Снова отправляем сообщение
        System.out.println("\n Повторная отправка ");
        client.sendMessage("Соединение восстановлено");
        // 9. Закрываем клиента
        System.out.println("\n Закрытие клиента ");
        client.close();
        // 10. Пытаемся отправить сообщение
        System.out.println("\n Попытка отправки после закрытия ");
        client.sendMessage("Это сообщение не должно отправиться.");
    }
}