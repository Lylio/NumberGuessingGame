import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumberGuessingGameLogicTest {
    private NumberGuessingGameLogic game;

    @BeforeEach
    public void setUp() {
        game = new NumberGuessingGameLogic();
        game.setNumberToGuess(50); // predictable value
    }

    @Test
    public void testCorrectGuess() {
        String result = game.checkGuess(50);
        assertEquals("CORRECT", result);
        assertEquals(1, game.getTries());
    }

    @Test
    public void testTooLowGuess() {
        String result = game.checkGuess(30);
        assertEquals("TOO_LOW", result);
    }

    @Test
    public void testTooHighGuess() {
        String result = game.checkGuess(70);
        assertEquals("TOO_HIGH", result);
    }

    @Test
    public void testOutOfRangeLow() {
        String result = game.checkGuess(0);
        assertEquals("OUT_OF_RANGE", result);
    }

    @Test
    public void testOutOfRangeHigh() {
        String result = game.checkGuess(101);
        assertEquals("OUT_OF_RANGE", result);
    }

    @Test
    public void testTriesIncrement() {
        game.checkGuess(30);
        game.checkGuess(40);
        assertEquals(2, game.getTries());
    }
}

