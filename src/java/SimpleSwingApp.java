import javax.swing.*;
public class SimpleSwingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Перша програма на Swing");
            JLabel label = new JLabel("Ласкаво просимо до Java Swing!");
            frame.add(label);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
