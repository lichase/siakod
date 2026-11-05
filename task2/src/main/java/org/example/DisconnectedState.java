package org.example;

public class DisconnectedState implements ConnectionState {

    @Override
    public void connect(NetworkClient client) {
        System.out.println("Начинаем подключение...");

        client.setState(new ConnectingState());
    }

    @Override
    public void sendMessage(NetworkClient client, String message) {
        System.out.println("Ошибка: клиент отключён. "
                + "Сообщение не отправлено.");
    }

    @Override
    public void disconnect(NetworkClient client) {
        System.out.println("Клиент уже отключён.");
    }

    @Override
    public void onTimeout(NetworkClient client) {
        System.out.println("Тайм-аут проигнорирован: "
                + "клиент отключён.");
    }

    @Override
    public void onConnectionEstablished(NetworkClient client) {
        System.out.println("Невозможно установить соединение: "
                + "подключение не начиналось.");
    }

    @Override
    public String getName() {
        return "Disconnected";
    }
}