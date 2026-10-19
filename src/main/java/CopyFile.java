import java.io.*;

class CopyFile {
    public static void main(String args[]) throws IOException {
        FileInputStream fin = null;
        FileOutputStream fout = null;

        if (args.length != 2) {
            System.out.println("Використання: CopyFile ім'я-джерело ім'я-ціль");
            return;
        }

        try {
            fin = new FileInputStream(args[0]);
            fout = new FileOutputStream(args[1]);
            int i;
            while ((i = fin.read()) != -1) {
                fout.write(i);
            }
        } catch (IOException exc) {
            System.out.println("Помилка введення-виведення: " + exc);
        } finally {
            if (fin != null) fin.close();
            if (fout != null) fout.close();
        }
    }
}
