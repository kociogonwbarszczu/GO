import go.game.frames.SecondFrame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecondFrameTest {

    @Test
    public void testSetStartGame() {
        SecondFrameMock.setStartGame(false);
        assertFalse(SecondFrameMock.getStartGame());

        SecondFrameMock.setStartGame(true);
        assertTrue(SecondFrameMock.getStartGame());
    }

    @Test
    public void testSetGameMode() {
        SecondFrameMock secondFrameMock = new SecondFrameMock();

        // Test 2-player game mode
        secondFrameMock.setGameMode(-1);
        assertFalse(secondFrameMock.setGameMode);
        secondFrameMock.setGameMode(0);
        assertTrue(secondFrameMock.setGameMode);
        assertEquals(0, secondFrameMock.getGameMode());

        // Test game with bot mode
        secondFrameMock.setGameMode(-1);
        assertFalse(secondFrameMock.setGameMode);
        secondFrameMock.setGameMode(1);
        assertTrue(secondFrameMock.setGameMode);
        assertEquals(1, secondFrameMock.getGameMode());
    }
}
