import javax.swing.*;
public class StudentApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Інформація про студента");
        JTextField nameField = new JTextField("Введіть ім'я");
        JButton submitButton = new JButton("Підтвердити");
        frame.add(nameField, "North");
        frame.add(submitButton, "South");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
