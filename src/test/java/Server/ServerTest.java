package Server;

import go.game.ClientServer.Client;
import go.game.ClientServer.GO;
import go.game.frames.SecondFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ServerTest {
    private GO server;
    private Client clientOne;
    private Client clientTwo;

    @Before
    public void setUp() {
        server = new GO();
    }

    @After
    public void tearDown() {
        // Sprawdzanie, czy serwer działa i zamykanie
        if (server != null) {
            assertTrue(server.isServerRunning());
            server.setServerRunning(false);
        }
    }

    @Test
    public void testInitializeGameWithTwoPlayers() {
        clientOne = new Client();
        clientTwo = new Client();
        assertNotNull(clientOne);
        assertNotNull(clientTwo);
        clientOne.connectToServer();
        clientTwo.connectToServer();
    }

}

