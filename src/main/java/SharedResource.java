class SharedResource {
    private int value;

    synchronized int updateResource(int[] nums) {
        value = 0; // Скидання значення
        for (int num : nums) {
            value += num;
            System.out.println("Проміжне значення в потоці " + 
                Thread.currentThread().getName() + " = " + value);
            try {
                Thread.sleep(50); // Імітація довгої обробки
            } catch (InterruptedException e) {
                System.out.println("Потік перерваний.");
            }
        }
        return value;
    }
}
