package librarymanagement;
import java.util.Scanner;

public class LibraryMain {

public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while(true){
             System.out.println("\nLibrary Management System Menu:");
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

            switch (choice){
                case 1:
                    System.out.println("Enter Book ID, Title, Author, and Subject:");
                    String bookId = scanner.nextLine();
                    String title = scanner.nextLine();
                    String author = scanner.nextLine();
                    String subject = scanner.nextLine();
                    library.addBook(bookId, title, author, subject);
                    break;
                case 2:
                    library.removeBook();
                    break;
                case 3:
                    System.out.println("Enter Member Name and ID:");
                    String memberName = scanner.nextLine();
                    String memberId = scanner.nextLine();
                    library.addMember(memberName, memberId);
                    break;
                case 4:
                    library.removeMember();
                    break;
                case 5:
                    System.out.println("Enter Member ID and Book ID:");
                    String borrowMemberId = scanner.nextLine();
                    String borrowBookId = scanner.nextLine();
                    library.borrowBook(borrowMemberId, borrowBookId);
                    break;
                case 6:
                    System.out.println("Enter Member ID and Book ID:");
                    String returnMemberId = scanner.nextLine();
                    String returnBookId = scanner.nextLine();
                    library.returnBook(returnMemberId, returnBookId);
                    break;
                case 7:
                    System.out.println("Enter Member ID:");
                    String historyMemberId = scanner.nextLine();
                    library.showTransactionHistory(historyMemberId);
                    break;
                case 8:
                    library.listAllBooks();
                    break;
                case 9:
                    library.listAllMembers();
                    break;
                case 10:
                    System.out.println("Enter Author Name:");
                    String searchAuthor = scanner.nextLine();
                    library.searchBooksByAuthor(searchAuthor);
                    break;
                case 11:
                    System.out.println("Enter Subject:");
                    String searchSubject = scanner.nextLine();
                    library.searchBooksBySubject(searchSubject);
                    break;
                case 12:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
