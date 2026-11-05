package org.example;
public interface ConnectionState {
    // Начать подключение
    void connect(NetworkClient client);
    // Отправить сообщение
    void sendMessage(NetworkClient client, String message);
    // Потеря соединения
    void disconnect(NetworkClient client);
    // Истечение времени ожидания
    void onTimeout(NetworkClient client);
    // Успешное установление соединения
    void onConnectionEstablished(NetworkClient client);
    // Получить название состояния
    String getName();
}