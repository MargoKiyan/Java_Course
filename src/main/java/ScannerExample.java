import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле число: ");
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();  // зчитати ціле число
            System.out.println("Ви ввели: " + number);
        } else {
            System.out.println("Введене значення не є цілим числом.");
        }
    }
}
