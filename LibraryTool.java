package librarymanagement;

import java.util.Scanner;

public class LibraryTool {
    private static final int MAX_CAPACITY = 1000;
    private Book[] bookStack;
    private Book[] bookQueue;
    private Member[] memberStack;
    private int bookStackTop;
    private int bookQueueFront;
    private int bookQueueRear;
    private int bookQueueSize;
    private int memberStackTop;
    private Scanner scanner;

    public LibraryTool() {
        bookStack = new Book[MAX_CAPACITY];
        bookQueue = new Book[MAX_CAPACITY];
        memberStack = new Member[MAX_CAPACITY];
        bookStackTop = -1;
        bookQueueFront = 0;
        bookQueueRear = -1;
        bookQueueSize = 0;
        memberStackTop = -1;
        scanner = new Scanner(System.in);
    }

    // Stack Methods
    public void pushBook(Book book) {
        if (isBookStackFull()) {
            System.out.println("Book stack is full.");
            return;
        }
        bookStack[++bookStackTop] = book;
    }

    public Book popBook() {
        if (isBookStackEmpty()) {
            System.out.println("Book stack is empty.");
            return null;
        }
        return bookStack[bookStackTop--];
    }

    public Book peekBook() {
        if (isBookStackEmpty()) {
            System.out.println("Book stack is empty.");
            return null;
        }
        return bookStack[bookStackTop];
    }

    public boolean isBookStackEmpty() {
        return bookStackTop == -1;
    }

    public boolean isBookStackFull() {
        return bookStackTop == MAX_CAPACITY - 1;
    }

    // Queue Methods
    public void enqueueBook(Book book) {
        if (isBookQueueFull()) {
            System.out.println("Book queue is full.");
            return;
        }
        bookQueueRear = (bookQueueRear + 1) % MAX_CAPACITY;
        bookQueue[bookQueueRear] = book;
        bookQueueSize++;
    }

    public Book dequeueBook() {
        if (isBookQueueEmpty()) {
            System.out.println("Book queue is empty.");
            return null;
        }
        Book book = bookQueue[bookQueueFront];
        bookQueueFront = (bookQueueFront + 1) % MAX_CAPACITY;
        bookQueueSize--;
        return book;
    }

    public Book peekBookQueue() {
        if (isBookQueueEmpty()) {
            System.out.println("Book queue is empty.");
            return null;
        }
        return bookQueue[bookQueueFront];
    }

    public boolean isBookQueueEmpty() {
        return bookQueueSize == 0;
    }

    public boolean isBookQueueFull() {
        return bookQueueSize == MAX_CAPACITY;
    }

    public void printBookQueue() {
        if (isBookQueueEmpty()) {
            System.out.println("Book queue is empty.");
            return;
        }
        int current = bookQueueFront;
        for (int i = 0; i < bookQueueSize; i++) {
            System.out.println(bookQueue[current].getTitle());
            current = (current + 1) % MAX_CAPACITY;
        }
    }

    // Member Stack Methods
    public void pushMember(Member member) {
        if (isMemberStackFull()) {
            System.out.println("Member stack is full.");
            return;
        }
        memberStack[++memberStackTop] = member;
    }

    public Member popMember() {
        if (isMemberStackEmpty()) {
            System.out.println("Member stack is empty.");
            return null;
        }
        return memberStack[memberStackTop--];
    }

    public Member peekMember() {
        if (isMemberStackEmpty()) {
            System.out.println("Member stack is empty.");
            return null;
        }
        return memberStack[memberStackTop];
    }

    public boolean isMemberStackEmpty() {
        return memberStackTop == -1;
    }

    public boolean isMemberStackFull() {
        return memberStackTop == MAX_CAPACITY - 1;
    }

    // Library Operations
    public void addBook(String id, String title, String author, String subject) {
        Book book = new Book(id, title, author, subject);
        pushBook(book);
        enqueueBook(book);
        System.out.println("Book added successfully.");
    }

    public void removeBook() {
        Book book = popBook();
        if (book != null) {
            System.out.println("Book removed successfully: " + book.getTitle());
        }
    }

    public void addMember(String name, String id) {
        Member member = new Member(name, id);
        pushMember(member);
        System.out.println("Member added successfully.");
    }

    public void removeMember() {
        Member member = popMember();
        if (member != null) {
            System.out.println("Member removed successfully: " + member.getName());
        }
    }

    public void borrowBook(String memberId, String bookId) {
        Member member = getMemberById(memberId);
        Book book = getBookById(bookId);

        if (member != null && book != null && !book.isBorrowed()) {
            book.borrowBook();
            member.addTransaction("Borrowed: " + book.getTitle());
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Cannot borrow book.");
        }
    }

    public void returnBook(String memberId, String bookId) {
        Member member = getMemberById(memberId);
        Book book = getBookById(bookId);

        if (member != null && book != null && book.isBorrowed()) {
            book.returnBook();
            member.addTransaction("Returned: " + book.getTitle());
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Cannot return book.");
        }
    }

    public void showTransactionHistory(String memberId) {
        Member member = getMemberById(memberId);

        if (member != null) {
            System.out.println("Transaction history for member " + member.getName() + ":");
            member.showTransactionHistory();
        } else {
            System.out.println("Member not found.");
        }
    }

    public void listAllBooks() {
        if (isBookStackEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("List of all books:");
            for (int i = 0; i <= bookStackTop; i++) {
                Book book = bookStack[i];
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Subject: " + book.getSubject() + ", Borrowed: " + (book.isBorrowed() ? "Yes" : "No"));
            }
        }
    }

    public void listAllMembers() {
        if (isMemberStackEmpty()) {
            System.out.println("No members available.");
        } else {
            System.out.println("List of all members:");
            for (int i = 0; i <= memberStackTop; i++) {
                Member member = memberStack[i];
                System.out.println("ID: " + member.getId() + ", Name: " + member.getName());
            }
        }
    }

    public void searchBooksByAuthor(String author) {
        boolean found = false;

        for (int i = 0; i <= bookQueueRear; i++) {
            if (bookQueue[i].getAuthor().equalsIgnoreCase(author)) {
                System.out.println("ID: " + bookQueue[i].getId() + ", Title: " + bookQueue[i].getTitle() + ", Subject: " + bookQueue[i].getSubject() + ", Borrowed: " + (bookQueue[i].isBorrowed() ? "Yes" : "No"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by author " + author);
        }
    }

    public void searchBooksBySubject(String subject) {
        boolean found = false;

        for (int i = 0; i <= bookQueueRear; i++) {
            if (bookQueue[i].getSubject().equalsIgnoreCase(subject)) {
                System.out.println("ID: " + bookQueue[i].getId() + ", Title: " + bookQueue[i].getTitle() + ", Author: " + bookQueue[i].getAuthor() + ", Borrowed: " + (bookQueue[i].isBorrowed() ? "Yes" : "No"));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with subject " + subject);
        }
    }

    private Member getMemberById(String id) {
        for (int i = 0; i <= memberStackTop; i++) {
            if (memberStack[i].getId().equals(id)) {
                return memberStack[i];
            }
        }
        return null;
    }

    private Book getBookById(String id) {
        for (int i = 0; i <= bookQueueRear; i++) {
            if (bookQueue[i].getId().equals(id)) {
                return bookQueue[i];
            }
        }
        return null;
    }
}
