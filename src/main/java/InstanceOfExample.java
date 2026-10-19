class Course {
    // Базовий клас
}
class OnlineCourse extends Course {
    // Підклас
}
public class InstanceOfExample {
    public static void main(String[] args) {
        Course course = new OnlineCourse();        
        // Перевіряємо, чи є course екземпляром OnlineCourse
        if(course instanceof OnlineCourse) {
            System.out.println("Це онлайн-курс.");
        } else {
            System.out.println("Це не онлайн-курс.");
        }
    }
}
