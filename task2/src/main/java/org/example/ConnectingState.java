package org.example;

public class ConnectingState implements ConnectionState {

    @Override
    public void connect(NetworkClient client) {
        System.out.println("Подключение уже выполняется.");
    }

    @Override
    public void sendMessage(NetworkClient client, String message) {
        System.out.println("Ошибка: клиент ещё подключается.");
    }

    @Override
    public void disconnect(NetworkClient client) {
        System.out.println("Подключение прервано.");

        client.setState(new DisconnectedState());
    }

    @Override
    public void onTimeout(NetworkClient client) {
        System.out.println("Время подключения истекло.");

        client.setState(new DisconnectedState());
    }

    @Override
    public void onConnectionEstablished(NetworkClient client) {
        System.out.println("Соединение успешно установлено.");

        client.setState(new ConnectedState());
    }

    @Override
    public String getName() {
        return "Connecting";
    }
}