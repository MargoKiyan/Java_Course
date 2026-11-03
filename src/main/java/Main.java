import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Оберіть операцію (+, -, *, /): ");
            String operation = scanner.nextLine().trim();

            double result;
            switch (operation) {
                case "+":
                    result = calculator.add(a, b);
                    break;
                case "-":
                    result = calculator.subtract(a, b);
                    break;
                case "*":
                    result = calculator.multiply(a, b);
                    break;
                case "/":
                    result = calculator.divide(a, b);
                    break;
                default:
                    throw new InvalidInputException("Невідома операція: " + operation);
            }

            System.out.println("Результат: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректні числа, а не текст.");
        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Некоректні вхідні дані: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Помилка: недостатньо введених даних.");
        } finally {
            System.out.println("Обробку запиту завершено.");
        }
    }
}
