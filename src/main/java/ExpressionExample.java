public class ExpressionExample {
    public static void main(String[] args) {
        Integer coursePoints = 90;
        int bonus = 10;
        // Автоупаковка та авторозпаковка в межах виразу
        coursePoints += bonus; 
// coursePoints автоматично розпаковується, додається 10, результат упаковується назад
        System.out.println("Загальна кількість балів курсу: " + coursePoints); 
// Виведе: Загальна кількість балів курсу: 100
    }
}
