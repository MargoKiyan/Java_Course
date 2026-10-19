class Library {
    private int books;

    Library(int totalBooks) {
        books = totalBooks;
    }

    void borrowBook(String student) {
        synchronized (this) {
            if (books > 0) {
                System.out.println(student + " взяв книгу.");
                books--;
            } else {
                System.out.println("Немає книг для " + student);
            }
        }
    }
}
