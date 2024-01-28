import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirstFrameTest {

    @Test
    public void testGetNewGame() {
        FirstFrameMock firstFrame = new FirstFrameMock();
        assertFalse(firstFrame.getNewGame());

        firstFrame.setNewGame(true);
        assertTrue(firstFrame.getNewGame());
    }

    @Test
    public void testGetLoadGame() {
        FirstFrameMock firstFrame = new FirstFrameMock();
        assertFalse(firstFrame.getLoadGame());

        firstFrame.setLoadGame(true);
        assertTrue(firstFrame.getLoadGame());
    }
}
