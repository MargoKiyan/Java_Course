import javax.swing.*;
public class LambdaButtonDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Лямбда приклад");
            frame.setSize(300, 100);
            frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
            JButton button = new JButton("Натисніть мене");
            button.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                    "Ви натиснули кнопку!"));
            frame.add(button);
            frame.setVisible(true);
        });
    }
}
