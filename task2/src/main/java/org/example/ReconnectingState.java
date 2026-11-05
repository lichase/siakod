package org.example;

public class ReconnectingState implements ConnectionState {

    @Override
    public void connect(NetworkClient client) {
        System.out.println("Переподключение уже выполняется.");
    }

    @Override
    public void sendMessage(NetworkClient client, String message) {
        System.out.println("Ошибка: соединение отсутствует. "
                + "Сообщение не отправлено.");
    }

    @Override
    public void disconnect(NetworkClient client) {
        System.out.println("Клиент уже переподключается.");
    }

    @Override
    public void onTimeout(NetworkClient client) {
        client.incrementReconnectAttempts();

        System.out.println("Попытка переподключения №"
                + client.getReconnectAttempts());
    }

    @Override
    public void onConnectionEstablished(NetworkClient client) {
        System.out.println("Переподключение успешно.");

        client.setState(new ConnectedState());
    }

    @Override
    public String getName() {
        return "Reconnecting";
    }
}