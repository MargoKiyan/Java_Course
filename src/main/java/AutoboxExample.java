public class AutoboxExample {
    public static void main(String[] args) {
        // Автоупаковка: примітив перетворюється на об'єкт Integer
        Integer studentID = 123456;
        
        // Авторозпаковка: об'єкт перетворюється назад на примітив int
        int processedID = studentID + 100;
        
        System.out.println("Студентський номер після обробки: " + processedID); // Виведе: Студентський номер після обробки: 123556
    }
}
