import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
public class QuadraticEquation {
    public static void main(String[] args) {
        // a, b і c представляють коефіцієнти квадратного рівняння ax^2 + bx + c = 0
        double a = 1, b = -3, c = 2;
        double discriminant = pow(b, 2) - 4 * a * c;
        // Знаходимо корені
        double x1 = (-b + sqrt(discriminant)) / (2 * a);
        double x2 = (-b - sqrt(discriminant)) / (2 * a);
        System.out.println("Корені рівняння: x1 = " + x1 + ", x2 = " + x2);
    }
}
