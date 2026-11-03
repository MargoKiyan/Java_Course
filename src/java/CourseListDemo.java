import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class CourseListDemo implements ListSelectionListener {
    JList<String> courseList;
    JLabel selectedLabel;
    JScrollPane scrollPane;
    CourseListDemo() {
        // Створити вікно
        JFrame frame = new JFrame("Вибір курсів");
        frame.setLayout(new FlowLayout());
        frame.setSize(250, 150);
        // Створити масив курсів
        String[] courses = { "Програмування", "Алгоритми", "Мережі", "Бази даних", "Штучний інтелект" };
        // Створити JList з курсами
        courseList = new JList<>(courses);        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Додати прокрутку
        scrollPane = new JScrollPane(courseList);
        scrollPane.setPreferredSize(new Dimension(120, 90));
        // Створити мітку для відображення вибору
        selectedLabel = new JLabel("Виберіть курс");
        // Додати слухача подій для списку
        courseList.addListSelectionListener(this);
        // Додати компоненти до вікна
        frame.add(scrollPane);
        frame.add(selectedLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Обробка подій вибору в списку
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedCourse = courseList.getSelectedValue();
            selectedLabel.setText("Вибрано курс: " + selectedCourse);
        }
    }

    public static void main(String[] args) {
        // Створити GUI у потоці EDT
        SwingUtilities.invokeLater(CourseListDemo::new);
    }
}
