import javax.swing.*;
public class ScheduleApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScheduleApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Розклад занять");
        JButton btn = new JButton("Додати новий курс");
        frame.add(btn);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
