import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class StudentNameDemo implements ActionListener {
    JTextField nameField;
    JLabel label;
    StudentNameDemo() {
        // Створити вікно
        JFrame frame = new JFrame("Введіть ім'я студента");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 120);
        // Створити текстове поле
        nameField = new JTextField(10);
        nameField.addActionListener(this);
        // Створити кнопку Reverse
        JButton reverseButton = new JButton("Перевернути");
        reverseButton.addActionListener(this);
        // Створити мітку для виведення результату
        label = new JLabel("Введіть ім'я студента: ");
        // Додати компоненти до вікна
        frame.add(label);
        frame.add(nameField);
        frame.add(reverseButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    // Метод обробки подій
    public void actionPerformed(ActionEvent ae) {
        String studentName = nameField.getText();
        String reversedName = new StringBuilder(studentName).reverse().toString();
        nameField.setText(reversedName);
    }
    public static void main(String[] args) {
        // Створити GUI у потоці EDT
        SwingUtilities.invokeLater(StudentNameDemo::new);
    }
}
