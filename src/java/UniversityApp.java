import javax.swing.*;
public class UniversityApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Університетська програма");
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Додати курс");
        panel.add(addButton);
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
