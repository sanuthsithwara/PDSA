package librarymanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books;
    private HashMap<String, Member> members;
    private Scanner scanner;

    public Library() {
        books = new ArrayList<>();
        members = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addBook() {
        System.out.println("Enter book ID:");
        String id = scanner.nextLine();
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book author:");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void removeBook() {
        System.out.println("Enter book ID to remove:");
        String id = scanner.nextLine();
        books.removeIf(book -> book.getId().equals(id));
        System.out.println("Book removed successfully.");
    }

    public void addMember() {
        System.out.println("Enter member name:");
        String name = scanner.nextLine();
        System.out.println("Enter member ID:");
        String memberId = scanner.nextLine();

        Member member = new Member(name, memberId);
        members.put(memberId, member);
        System.out.println("Member added successfully.");
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public void borrowBook() {
        System.out.println("Enter member ID:");
        String memberId = scanner.nextLine();
        System.out.println("Enter book ID to borrow:");
        String id = scanner.nextLine();

        Member member = getMember(memberId);
        Book book = searchBookById(id);

        if (member != null && book != null && !book.isBorrowed()) {
            book.borrow();
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
        String id = scanner.nextLine();

        Member member = getMember(memberId);
        Book book = searchBookById(id);

        if (member != null && book != null && book.isBorrowed()) {
            book.returnBook();
            member.addTransaction("Returned: " + book.getTitle());
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Cannot return book.");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Enter member ID to view transaction history:");
        String memberId = scanner.nextLine();
        Member member = getMember(memberId);

        if (member != null) {
            System.out.println("Transaction history for member " + member.getName() + ":");
            member.printTransactionHistory();
        } else {
            System.out.println("Member not found.");
        }
    }

    public void listAllBooks() {
        System.out.println("\nList of all books:");
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Borrowed: " + (book.isBorrowed() ? "Yes" : "No"));
        }
    }

    public void listAllMembers() {
        System.out.println("\nList of all members:");
        for (Member member : members.values()) {
            System.out.println("ID: " + member.getMemberId() + ", Name: " + member.getName());
        }
    }

    public Book searchBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void start() {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add Member");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. View Transaction History");
            System.out.println("7. List All Books");
            System.out.println("8. List All Members");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    addMember();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    listAllBooks();
                    break;
                case 8:
                    listAllMembers();
                    break;
                case 9:
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.start();
    }
}

