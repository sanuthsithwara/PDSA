package librarymanagement;

public class Book {
    private String id;
    private String title;
    private String author;
    private String subject;
    private boolean borrowed;

    public Book(String id, String title, String author, String subject) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.borrowed = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void borrowBook() {
        if (!borrowed) {
            borrowed = true;
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        if (borrowed) {
            borrowed = false;
        } else {
            System.out.println("Book was not borrowed.");
        }
    }
}
