package Logic;

import go.game.logic.DefaultLogicStrategy;
import go.game.logic.Logic;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLogicStrategyTest {

    @Test
    void countBreath() {
        Logic logic = new Logic(new DefaultLogicStrategy());
        logic.updateBoard(1, 1,Color.WHITE);
        logic.updateBoard(2, 2, Color.WHITE);
        logic.updateBoard( 1, 2, Color.BLACK);

        int breath = logic.countBreath(1, 2);
        assertEquals(2, breath);
    }

    @Test
    void countBreathHypothetical() {
        Logic logic = new Logic(new DefaultLogicStrategy());
        logic.updateBoard(1, 1,Color.WHITE);
        logic.updateBoard(2, 2, Color.WHITE);

        int breath = logic.countBreathHypothetical(1, 2, Color.BLACK);
        assertEquals(2, breath);
    }

    @Test
    void countBreathChain() {
        Logic logic = new Logic(new DefaultLogicStrategy());
        logic.updateBoard(1, 1,Color.WHITE);
        logic.updateBoard(2, 2, Color.WHITE);
        logic.updateBoard( 1, 2, Color.WHITE);

        int breath = logic.countBreath(1, 2);
        assertEquals(7, breath);
    }

    @Test
    void getUpdateTest() {
        Logic logic = new Logic(new DefaultLogicStrategy());
        logic.updateBoard(1, 1,Color.WHITE);
        logic.updateBoard(2, 2, Color.WHITE);
        logic.updateBoard( 1, 2, Color.WHITE);

        assertEquals('W', logic.getElement(1, 1));
    }

    @Test
    void ifAlreadyOccupiedTest() {
        Logic logic = new Logic(new DefaultLogicStrategy());
        logic.updateBoard(1, 1,Color.WHITE);
        logic.updateBoard(2, 2, Color.WHITE);
        logic.updateBoard( 1, 2, Color.WHITE);

        assertFalse(logic.ifAlreadyOccupied(1, 2));
        assertTrue(logic.ifAlreadyOccupied(2, 3));
    }
}

