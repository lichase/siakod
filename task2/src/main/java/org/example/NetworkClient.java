package org.example;

public class NetworkClient {
    private ConnectionState state;
    private int reconnectAttempts;
    public NetworkClient() {
        this.state = new DisconnectedState();
        this.reconnectAttempts = 0;
    }
    // Изменение текущего состояния
    public void setState(ConnectionState state) {
        this.state = state;
        System.out.println("Новое состояние: " + state.getName());
    }
    // Начать подключение
    public void connect() {
        state.connect(this);
    }
    // Отправить сообщение
    public void sendMessage(String message) {
        state.sendMessage(this, message);
    }
    // Потеря соединения
    public void disconnect() {
        state.disconnect(this);
    }
    // Истечение времени ожидания
    public void onTimeout() {
        state.onTimeout(this);
    }
    // Соединение успешно установлено
    public void connectionEstablished() {
        state.onConnectionEstablished(this);
    }
    // Окончательно закрыть клиент
    public void close() {
        setState(new ClosedState());
    }
    public String getCurrentState() {
        return state.getName();
    }
    public int getReconnectAttempts() {
        return reconnectAttempts;
    }
    public void incrementReconnectAttempts() {
        reconnectAttempts++;
    }
    // Имитация отправки сообщения
    public void transmit(String message) {
        System.out.println("Сообщение отправлено: " + message);
    }
}