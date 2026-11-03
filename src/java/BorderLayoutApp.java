import javax.swing.*;
import java.awt.*;
public class BorderLayoutApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            BorderLayoutApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("BorderLayout Програма");
        frame.setLayout(new BorderLayout());
        JButton northButton = new JButton("Північ");
        JButton southButton = new JButton("Південь");
        JButton eastButton = new JButton("Схід");
        JButton westButton = new JButton("Захід");
        JButton centerButton = new JButton("Центр");
        frame.add(northButton, BorderLayout.NORTH);
        frame.add(southButton, BorderLayout.SOUTH);
        frame.add(eastButton, BorderLayout.EAST);
        frame.add(westButton, BorderLayout.WEST);
        frame.add(centerButton, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
