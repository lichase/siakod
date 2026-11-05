package org.example;

public class ConnectedState implements ConnectionState {

    @Override
    public void connect(NetworkClient client) {
        System.out.println("Клиент уже подключён.");
    }

    @Override
    public void sendMessage(NetworkClient client, String message) {
        client.transmit(message);
    }

    @Override
    public void disconnect(NetworkClient client) {
        System.out.println("Соединение потеряно.");

        client.setState(new ReconnectingState());
    }

    @Override
    public void onTimeout(NetworkClient client) {
        System.out.println("Тайм-аут соединения.");

        client.setState(new ReconnectingState());
    }

    @Override
    public void onConnectionEstablished(NetworkClient client) {
        System.out.println("Соединение уже установлено.");
    }

    @Override
    public String getName() {
        return "Connected";
    }
}