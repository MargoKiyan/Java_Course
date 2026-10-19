import java.io.*;

class ReadLines {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, System.console().charset()));
        String str;
        System.out.println("Вводите строки текста.");
        System.out.println("Для завершення введіть 'stop'.");
        do {
            str = br.readLine();
            System.out.println(str);
        } while (!str.equals("stop"));
    }
}
