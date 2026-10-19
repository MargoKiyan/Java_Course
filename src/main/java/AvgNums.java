import java.io.*;
class AvgNums {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int n;
        double sum = 0.0, avg, t;
        System.out.print("Скільки чисел ви будете вводити: ");
        str = br.readLine();
        try {
            n = Integer.parseInt(str);  // перетворити рядок у ціле число
        } catch (NumberFormatException exc) {
            System.out.println("Недопустимий формат.");
            return;
        }
        System.out.println("Введіть " + n + " чисел.");
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            try {
                t = Double.parseDouble(str);  // перетворити рядок у число з плаваючою комою
            } catch (NumberFormatException exc) {
                System.out.println("Недопустимий формат.");
                t = 0.0;
            }
            sum += t;
        }
        avg = sum / n;
        System.out.println("Середнє значення: " + avg);
    }
}
