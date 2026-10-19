import java.io.*;

class StudentInfoSaver {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try (FileWriter fw = new FileWriter("students.txt", true)) {
            String input;
            do {
                System.out.print("Введіть ім'я студента (stop для завершення): ");
                input = reader.readLine();
                if (!input.equalsIgnoreCase("stop")) {
                    fw.write(input + "\n");  // записати ім'я студента у файл
                }
            } while (!input.equalsIgnoreCase("stop"));
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }
}
