import java.util.Random;

public class NumberGuessingGameLogic {
    private int numberToGuess;
    private int numberOfTries;

    public NumberGuessingGameLogic() {
        reset();
    }

    public void reset() {
        numberToGuess = new Random().nextInt(100) + 1;
        numberOfTries = 0;
    }

    public String checkGuess(int guess) {
        numberOfTries++;
        if (guess < 1 || guess > 100) {
            return "OUT_OF_RANGE";
        } else if (guess < numberToGuess) {
            return "TOO_LOW";
        } else if (guess > numberToGuess) {
            return "TOO_HIGH";
        } else {
            return "CORRECT";
        }
    }

    public int getTries() {
        return numberOfTries;
    }

    public void setNumberToGuess(int number) {
        this.numberToGuess = number;
    }
}

