import org.example.NetworkClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NetworkClientTest {

    private NetworkClient client;

    private ByteArrayOutputStream output;

    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        client = new NetworkClient();

        originalOut = System.out;
        output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // 1. Проверка начального состояния
    @Test
    void shouldStartDisconnected() {
        assertEquals("Disconnected",
                client.getCurrentState());
    }

    // 2. Проверка перехода Disconnected -> Connecting
    @Test
    void shouldStartConnecting() {
        client.connect();

        assertEquals("Connecting",
                client.getCurrentState());
    }

    // 3. Проверка перехода Connecting -> Connected
    @Test
    void shouldBecomeConnectedAfterSuccessfulConnection() {
        client.connect();
        client.connectionEstablished();

        assertEquals("Connected",
                client.getCurrentState());
    }

    // 4. Проверка отправки сообщения в Connected
    @Test
    void shouldSendMessageWhenConnected() {
        client.connect();
        client.connectionEstablished();

        client.sendMessage("Hello!");

        assertTrue(output.toString()
                .contains("Сообщение отправлено: Hello!"));
    }

    // 5. Проверка запрета отправки в Disconnected
    @Test
    void shouldNotSendMessageWhenDisconnected() {
        client.sendMessage("Hello!");

        assertFalse(output.toString()
                .contains("Сообщение отправлено: Hello!"));

        assertTrue(output.toString()
                .contains("клиент отключён"));
    }

    // 6. Проверка перехода Connected -> Reconnecting
    @Test
    void shouldStartReconnectingAfterDisconnect() {
        client.connect();
        client.connectionEstablished();

        client.disconnect();

        assertEquals("Reconnecting",
                client.getCurrentState());
    }

    // 7. Проверка тайм-аута при подключении
    @Test
    void shouldReturnToDisconnectedAfterConnectingTimeout() {
        client.connect();

        client.onTimeout();

        assertEquals("Disconnected",
                client.getCurrentState());
    }

    // 8. Проверка увеличения количества попыток
    @Test
    void shouldIncreaseReconnectAttemptsAfterTimeout() {
        client.connect();
        client.connectionEstablished();
        client.disconnect();

        client.onTimeout();
        client.onTimeout();

        assertEquals(2,
                client.getReconnectAttempts());
    }

    // 9. Проверка успешного переподключения
    @Test
    void shouldBecomeConnectedAfterReconnecting() {
        client.connect();
        client.connectionEstablished();
        client.disconnect();

        client.connectionEstablished();

        assertEquals("Connected",
                client.getCurrentState());
    }

    // 10. Проверка окончательного закрытия
    @Test
    void shouldCloseClient() {
        client.close();

        assertEquals("Closed",
                client.getCurrentState());
    }

    // 11. Проверка запрета подключения после закрытия
    @Test
    void shouldNotConnectAfterClose() {
        client.close();

        client.connect();

        assertEquals("Closed",
                client.getCurrentState());
    }

    // 12. Проверка запрета отправки после закрытия
    @Test
    void shouldNotSendMessageAfterClose() {
        client.close();

        client.sendMessage("Hello!");

        assertFalse(output.toString()
                .contains("Сообщение отправлено: Hello!"));
    }
}