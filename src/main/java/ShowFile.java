import java.io.*;
class ShowFile {
    public static void main(String args[]) {
        int i;
        FileInputStream fin = null;

        if(args.length != 1) {
            System.out.println("Використання: ShowFile ім'я-файлу");
            return;
        }
        try {
            fin = new FileInputStream(args[0]);
            do {
                i = fin.read();
                if(i != -1) System.out.print((char) i);
            } while(i != -1);
        } catch(FileNotFoundException exc) {
            System.out.println("Файл не знайдено.");
        } catch(IOException exc) {
            System.out.println("Помилка при читанні файлу.");
        } finally {
            try {
                if(fin != null) fin.close();
            } catch(IOException exc) {
                System.out.println("Помилка при закритті файлу.");
            }
        }
    }
}
