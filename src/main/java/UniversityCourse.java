// Створення анотації
@interface CourseInfo {
    String courseName();
    int year();
}
// Використання анотації
@CourseInfo(courseName = "Java Programming", year = 2024)
public class UniversityCourse {
    // Тіло класу
}
