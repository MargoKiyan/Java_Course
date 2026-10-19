public class MoreThreads {
    public static void main(String[] args) {
        System.out.println("Запуск потоків.");
        MyThread mt1 = MyThread.createAndStart("Child #1");
        MyThread mt2 = MyThread.createAndStart("Child #2");
        MyThread mt3 = MyThread.createAndStart("Child #3");

        try {
            mt1.t.join();
            mt2.t.join();
            mt3.t.join();
        } catch (InterruptedException exc) {
            System.out.println("Головний потік перервано.");
        }
        System.out.println("Головний потік завершено.");
    }
}

