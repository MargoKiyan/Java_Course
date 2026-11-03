import javax.swing.*;
import javax.swing.event.*;
public class EventListenerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventListenerApp::showWindow);
    }
    private static void showWindow() {
        JFrame frame = new JFrame("Вибір елементів");
        String[] courses = {"Курс 1", "Курс 2", "Курс 3"};
        JList<String> courseList = new JList<>(courses);
        courseList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    JOptionPane.showMessageDialog(null, "Обрано: " + courseList.getSelectedValue());
                }
            }
        });
        frame.add(new JScrollPane(courseList));
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
