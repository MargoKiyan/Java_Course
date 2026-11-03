import javax.swing.*;
import java.awt.*;
public class LayoutApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LayoutApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Університетська програма");
        frame.setLayout(new BorderLayout());
        JButton addButton = new JButton("Додати курс");
        frame.add(addButton, BorderLayout.SOUTH);
        JButton editButton = new JButton("Редагувати курс");
        frame.add(editButton, BorderLayout.NORTH);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
