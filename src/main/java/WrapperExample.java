public class WrapperExample {
    public static void main(String[] args) {
        // Автоупаковка: перетворення int на об'єкт Integer
        Integer studentScore = 85;
        
        // Авторозпаковка: перетворення об'єкта Integer на примітив int
        int finalScore = studentScore + 5;
        
        System.out.println("Остаточний бал студента: " + finalScore); 
// Виведе: Остаточний бал студента: 90
    }
}
