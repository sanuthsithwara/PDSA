package librarymanagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Library {
    private static final int MAX_CAPACITY = 1000; // Example capacity
    private Stack<Book> bookStack;
    private Queue<Book> bookQueue;
    private Stack<Member> memberStack;
    private Scanner scanner;

    public Library() {
        bookStack = new Stack<>();
        bookQueue = new LinkedList<>();
        memberStack = new Stack<>();
        scanner = new Scanner(System.in);
    }

    public boolean isBookStackEmpty() {
        return bookStack.isEmpty();
    }

    public boolean isBookStackFull() {
        return bookStack.size() >= MAX_CAPACITY;
    }

    public boolean isMemberStackEmpty() {
        return memberStack.isEmpty();
    }

    public void pushBook() {
        if (isBookStackFull()) {
            System.out.println("Library is full. Cannot add more books.");
            return;
        }

        System.out.println("Enter book ID:");
        String id = scanner.nextLine();
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book author:");
        String author = scanner.nextLine();
        System.out.println("Enter book subject:");
        String subject = scanner.nextLine();

        Book book = new Book(id, title, author, subject);
        bookStack.push(book);
        bookQueue.offer(book);
        System.out.println("Book added successfully.");
    }

    public void popBook() {
        if (isBookStackEmpty()) {
            System.out.println("Library is empty. Cannot remove any books.");
            return;
        }

        Book book = bookStack.pop();
        bookQueue.remove(book);
        System.out.println("Book removed successfully: " + book.getTitle());
    }

    public void pushMember() {
        System.out.println("Enter member name:");
        String name = scanner.nextLine();
        System.out.println("Enter member ID:");
        String id = scanner.nextLine();

        Member member = new Member(name, id);
        memberStack.push(member);
        System.out.println("Member added successfully.");
    }

    public Member popMember() {
        if (isMemberStackEmpty()) {
            System.out.println("No members to remove.");
            return null;
        }

        return memberStack.pop();
    }

    public Member getMember(String id) {
        for (Member member : memberStack) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
    }

    public void borrowBook() {
        System.out.println("Enter member ID:");
        String memberId = scanner.nextLine();
        System.out.println("Enter book ID to borrow:");
        String bookId = scanner.nextLine();

        Member member = getMember(memberId);
        Book book = searchBookById(bookId);

        if (member != null && book != null && !book.isBorrowed()) {
            book.borrowBook();
            member.addTransaction("Borrowed: " + book.getTitle());
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Cannot borrow book.");
        }
    }

    public void returnBook() {
        System.out.println("Enter member ID:");
        String memberId = scanner.nextLine();
        System.out.println("Enter book ID to return:");
        String bookId = scanner.nextLine();

        Member member = getMember(memberId);
        Book book = searchBookById(bookId);

        if (member != null && book != null && book.isBorrowed()) {
            book.returnBook();
            member.addTransaction("Returned: " + book.getTitle());
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Cannot return book.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("Enter member ID to view transaction history:");
        String memberId = scanner.nextLine();
        Member member = getMember(memberId);

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
            return;
        }

        System.out.println("\nList of all books:");
        for (Book book : bookStack) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Subject: " + book.getSubject() + ", Borrowed: " + (book.isBorrowed() ? "Yes" : "No"));
        }
    }

    public void listAllMembers() {
        if (isMemberStackEmpty()) {
            System.out.println("No members available.");
            return;
        }

        System.out.println("\nList of all members:");
        for (Member member : memberStack) {
            System.out.println("ID: " + member.getId() + ", Name: " + member.getName());
        }
    }

    public Book searchBookById(String id) {
        for (Book book : bookStack) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void searchBooksByAuthor() {
        System.out.println("Enter author name to search books:");
        String author = scanner.nextLine();
        boolean found = false;

        for (Book book : bookStack) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Subject: " + book.getSubject() + ", Borrowed: " + (book.isBorrowed() ? "Yes" : "No"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found for author: " + author);
        }
    }

    public void searchBooksBySubject() {
        System.out.println("Enter subject to search books:");
        String subject = scanner.nextLine();
        boolean found = false;

        for (Book book : bookQueue) {
            if (book.getSubject().equalsIgnoreCase(subject)) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Subject: " + book.getSubject() + ", Borrowed: " + (book.isBorrowed() ? "Yes" : "No"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found for subject: " + subject);
        }
    }

    public void start() {
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Book (Push)");
            System.out.println("2. Remove Book (Pop)");
            System.out.println("3. Add Member (Push)");
            System.out.println("4. Remove Member (Pop)");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Show Transaction History");
            System.out.println("8. List All Books");
            System.out.println("9. List All Members");
            System.out.println("10. Search Books by Author");
            System.out.println("11. Search Books by Subject");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    pushBook();
                    break;
                case 2:
                    popBook();
                    break;
                case 3:
                    pushMember();
                    break;
                case 4:
                    popMember();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    showTransactionHistory();
                    break;
                case 8:
                    listAllBooks();
                    break;
                case 9:
                    listAllMembers();
                    break;
                case 10:
                    searchBooksByAuthor();
                    break;
                case 11:
                    searchBooksBySubject();
                    break;
                case 12:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.start();
    }
}
