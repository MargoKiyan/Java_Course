import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void addsNumbers() throws InvalidInputException {
        assertEquals(5.0, calculator.add(2, 3), 1e-9);
        assertEquals(-1.5, calculator.add(-2.5, 1), 1e-9);
        assertEquals(5.0, calculator.add(0, 5), 1e-9);
    }

    @Test
    void subtractsNumbers() throws InvalidInputException {
        assertEquals(6.0, calculator.subtract(10, 4), 1e-9);
        assertEquals(-3.0, calculator.subtract(2, 5), 1e-9);
    }

    @Test
    void multipliesNumbers() throws InvalidInputException {
        assertEquals(10.0, calculator.multiply(2.5, 4), 1e-9);
        assertEquals(-6.0, calculator.multiply(-2, 3), 1e-9);
    }

    @Test
    void dividesNumbers() throws InvalidInputException {
        assertEquals(2.5, calculator.divide(10, 4), 1e-9);
        assertEquals(-3.0, calculator.divide(-9, 3), 1e-9);
        assertEquals(0.0, calculator.divide(0, 5), 1e-9);
    }

    @Test
    void throwsWhenDividingByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0.0));
        assertThrows(ArithmeticException.class, () -> calculator.divide(10, -0.0));
        assertThrows(ArithmeticException.class, () -> calculator.divide(0, 0));
    }

    @Test
    void addsDecimalNumbers() throws InvalidInputException {
        assertEquals(0.3, calculator.add(0.1, 0.2), 1e-9);
    }

    @Test
    void rejectsInvalidNumbersInEveryOperation() {
        double[] invalidNumbers = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};

        for (double number : invalidNumbers) {
            assertThrows(InvalidInputException.class, () -> calculator.add(number, 2));
            assertThrows(InvalidInputException.class, () -> calculator.add(2, number));
            assertThrows(InvalidInputException.class, () -> calculator.subtract(number, 2));
            assertThrows(InvalidInputException.class, () -> calculator.subtract(2, number));
            assertThrows(InvalidInputException.class, () -> calculator.multiply(number, 2));
            assertThrows(InvalidInputException.class, () -> calculator.multiply(2, number));
            assertThrows(InvalidInputException.class, () -> calculator.divide(number, 2));
            assertThrows(InvalidInputException.class, () -> calculator.divide(2, number));
        }
    }

    @Test
    void checksInvalidNumbersBeforeDivisionByZero() {
        double[] invalidNumbers = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};

        for (double number : invalidNumbers) {
            assertThrows(InvalidInputException.class, () -> calculator.divide(number, 0.0));
            assertThrows(InvalidInputException.class, () -> calculator.divide(number, -0.0));
        }
    }
}
