// Киян М. ПД-31, практична робота 1

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> allProducts = List.of(product1, product2, product3);

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Пошук товарів");
            System.out.println("7 - Історія замовлень");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    allProducts.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    if (id == 1) cart.addProduct(product1);
                    else if (id == 2) cart.addProduct(product2);
                    else if (id == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено.");
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId = scanner.nextInt();
                    boolean isRemoved = cart.removeProductById(removeId);
                    if (isRemoved) {
                        System.out.println("Товар успішно видалено з кошика.");
                    } else {
                        System.out.println("Товар з таким ID в кошику не знайдено!");
                    }
                    break;
                case 6:
                    System.out.println("Введіть назву товару або категорії для пошуку:");
                    String query = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    for (Product p : allProducts) {
                        if (p.getName().toLowerCase().contains(query) || p.getCategory().getName().toLowerCase().contains(query)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) System.out.println("Нічого не знайдено.");
                    break;
                case 7:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("\n--- Замовлення #" + (i + 1) + " ---");
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що скористались нашим магазином!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}