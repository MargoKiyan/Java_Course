import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CourseRegistration implements ActionListener {
    JLabel label;
    CourseRegistration() {
        // Створити вікно
        JFrame frame = new JFrame("Реєстрація на курс");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 100);
        // Створити кнопку
        JButton registerButton = new JButton("Зареєструватися");
        registerButton.addActionListener(this);
        // Створити мітку для виведення результату
        label = new JLabel("Натисніть кнопку для реєстрації.");
        // Додати кнопку та мітку до вікна
        frame.add(registerButton);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    // Метод обробки події натискання кнопки
    public void actionPerformed(ActionEvent ae) {
        label.setText("Ви успішно зареєстровані на курс!");
    }
    public static void main(String[] args) {
        // Створити GUI у потоці EDT
        SwingUtilities.invokeLater(CourseRegistration::new);
    }
}
