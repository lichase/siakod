package org.example;

public class ClosedState implements ConnectionState {

    @Override
    public void connect(NetworkClient client) {
        System.out.println("Ошибка: клиент окончательно закрыт.");
    }

    @Override
    public void sendMessage(NetworkClient client, String message) {
        System.out.println("Ошибка: отправка невозможна. "
                + "Клиент закрыт.");
    }

    @Override
    public void disconnect(NetworkClient client) {
        System.out.println("Клиент уже закрыт.");
    }

    @Override
    public void onTimeout(NetworkClient client) {
        System.out.println("Тайм-аут проигнорирован: "
                + "клиент закрыт.");
    }

    @Override
    public void onConnectionEstablished(NetworkClient client) {
        System.out.println("Невозможно подключиться: "
                + "клиент закрыт.");
    }

    @Override
    public String getName() {
        return "Closed";
    }
}