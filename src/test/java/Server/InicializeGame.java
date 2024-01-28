package Server;

import go.game.ClientServer.GO;
import go.game.frames.SecondFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InicializeGame {
    private GO server;
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
    public void testInicializeGame() {
        SecondFrame secondFrame = new SecondFrame();
        secondFrame.setGameMode(0);

        // Sprawdź, czy gra została poprawnie zainicjalizowana
        assertTrue(server.currentGame != null);
        assertTrue(server.player2Joined);
        assertTrue(server.startGame);
    }
}
