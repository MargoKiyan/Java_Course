import java.io.*;

class ReadChars {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, System.console().charset()));
        char c;
        System.out.println("Вводите символи; для виходу введіть крапку.");
        do {
            c = (char) br.read();
            System.out.println(c);
        } while (c != '.');
    }
}
