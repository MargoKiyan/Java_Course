import javax.swing.*;
import java.awt.event.*;
public class EventApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Обробка подій");
        JButton addButton = new JButton("Додати курс");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Курс додано!");
            }
        });
        frame.add(addButton);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
