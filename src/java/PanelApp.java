import javax.swing.*;
public class PanelApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PanelApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Панелі контейнерів");
        JPanel contentPanel = new JPanel();
        JButton addButton = new JButton("Додати курс");
        contentPanel.add(addButton);
        frame.setContentPane(contentPanel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
