import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CourseSelectionDemo implements ItemListener {
    JLabel selectedLabel, changedLabel;
    JCheckBox course1, course2, course3;
    CourseSelectionDemo() {
        // Створити вікно
        JFrame frame = new JFrame("Вибір курсів");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 150);
        // Створити прапорці
        course1 = new JCheckBox("Програмування");
        course2 = new JCheckBox("Алгоритми");
        course3 = new JCheckBox("Мережі");
        // Додати слухачів подій
        course1.addItemListener(this);
        course2.addItemListener(this);
        course3.addItemListener(this);
        // Створити мітки для відображення інформації
        selectedLabel = new JLabel("Обрані курси: ");
        changedLabel = new JLabel("");
        // Додати компоненти до вікна
        frame.add(course1);
        frame.add(course2);
        frame.add(course3);
        frame.add(changedLabel);
        frame.add(selectedLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    // Обробка подій прапорців
    public void itemStateChanged(ItemEvent ie) {
        String str = "";
        JCheckBox cb = (JCheckBox) ie.getItem();
        if (cb.isSelected()) {
            changedLabel.setText(cb.getText() + " обраний.");
        } else {
            changedLabel.setText(cb.getText() + " знятий.");
        }

        if (course1.isSelected()) str += "Програмування ";
        if (course2.isSelected()) str += "Алгоритми ";
        if (course3.isSelected()) str += "Мережі ";

        selectedLabel.setText("Обрані курси: " + str);
    }

    public static void main(String[] args) {
        // Створити GUI у потоці EDT
        SwingUtilities.invokeLater(CourseSelectionDemo::new);
    }
}
