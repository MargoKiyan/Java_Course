import java.io.*;

public class FileCopyDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("source.txt");
        FileOutputStream out = new FileOutputStream("destination.txt");

        int byteData;
        while ((byteData = in.read()) != -1) {
            out.write(byteData);  // Копіювання байтів із файлу
        }

        in.close();
        out.close();
    }
}
