import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void addsNumbers() {
        checkOutput("8\n2\n+\n", "Результат: 10.0");
    }

    @Test
    void subtractsNumbers() {
        checkOutput("8\n2\n-\n", "Результат: 6.0");
    }

    @Test
    void multipliesNumbers() {
        checkOutput("8\n2\n*\n", "Результат: 16.0");
    }

    @Test
    void dividesNumbers() {
        checkOutput("8\n2\n/\n", "Результат: 4.0");
    }

    @Test
    void handlesDivisionByZero() {
        checkOutput("8\n0\n/\n", "Ділення на нуль");
    }

    @Test
    void handlesDivisionByNegativeZero() {
        checkOutput("8\n-0.0\n/\n", "Ділення на нуль");
    }

    @Test
    void handlesTextInsteadOfFirstNumber() {
        checkOutput("abc\n2\n+\n", "введіть коректні числа");
    }

    @Test
    void handlesTextInsteadOfSecondNumber() {
        checkOutput("8\nxyz\n+\n", "введіть коректні числа");
    }

    @Test
    void handlesNaN() {
        checkOutput("NaN\n2\n+\n", "Некоректні вхідні дані");
    }

    @Test
    void handlesInfinity() {
        checkOutput("8\nInfinity\n*\n", "Некоректні вхідні дані");
    }

    @Test
    void handlesUnknownOperation() {
        checkOutput("8\n2\n?\n", "Невідома операція");
    }

    @Test
    void acceptsNegativeNumbers() {
        checkOutput("-2.5\n1.5\n+\n", "Результат: -1.0");
    }

    @Test
    void acceptsSpacesAroundNumbers() {
        checkOutput(" 8 \n 2 \n/\n", "Результат: 4.0");
    }

    @Test
    void handlesEmptyInput() {
        checkOutput("", "недостатньо введених даних");
    }

    @Test
    void handlesMissingSecondNumber() {
        checkOutput("8\n", "недостатньо введених даних");
    }

    @Test
    void handlesMissingOperation() {
        checkOutput("8\n2\n", "недостатньо введених даних");
    }

    private void checkOutput(String input, String expectedMessage) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayOutputStream errors = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(output, true, StandardCharsets.UTF_8);
        PrintStream testErr = new PrintStream(errors, true, StandardCharsets.UTF_8);

        try {
            // передаємо готові рядки замість введення з клавіатури
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(testOut);
            System.setErr(testErr);
            Main.main(new String[0]);
        } finally {
            // відновлюємо потоки навіть тоді, коли запуск завершився помилкою
            System.setIn(originalIn);
            System.setOut(originalOut);
            System.setErr(originalErr);
            testOut.close();
            testErr.close();
        }

        String text = output.toString(StandardCharsets.UTF_8);
        assertEquals("", errors.toString(StandardCharsets.UTF_8));

        if (expectedMessage.startsWith("Результат:")) {
            int resultStart = text.indexOf("Результат:");
            assertTrue(resultStart >= 0, text);
            int resultEnd = text.indexOf('\n', resultStart);
            assertTrue(resultEnd > resultStart, text);
            double actual = Double.parseDouble(
                    text.substring(resultStart + "Результат:".length(), resultEnd).trim());
            double expected = Double.parseDouble(
                    expectedMessage.substring("Результат:".length()).trim());
            assertEquals(expected, actual, 1e-9);
            assertEquals(resultStart, text.lastIndexOf("Результат:"));
            assertFalse(text.contains("Помилка"));
            assertFalse(text.contains("Некоректні"));
            int first = text.indexOf("Введіть перше число");
            int second = text.indexOf("Введіть друге число");
            int operation = text.indexOf("Оберіть операцію (+, -, *, /)");
            assertTrue(first >= 0 && second > first && operation > second);
        } else {
            assertTrue(text.contains(expectedMessage), text);
            assertFalse(text.contains("Результат:"));
        }

        String finished = "Обробку запиту завершено.";
        assertTrue(text.trim().endsWith(finished));
        assertEquals(text.indexOf(finished), text.lastIndexOf(finished),
                "Повідомлення про завершення має бути тільки одне");
    }
}
