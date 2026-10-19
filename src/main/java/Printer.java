class Printer {
    private boolean isBusy = false;

    synchronized void printDocument(String docName) {
        while (isBusy) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Очікування перерване");
            }
        }
        isBusy = true;
        System.out.println("Друк документу: " + docName);
        isBusy = false;
        notifyAll();
    }
}
