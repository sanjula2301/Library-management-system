import java.util.List;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library;

    public static void main(String[] args) {
        library = new Library("library.csv");

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    addMember();
                    break;
                case 6:
                    viewAllMembers();
                    break;
                case 7:
                    reserveBook();
                    break;
                case 8:
                    unreserveBook();
                    break;
                case 9:
                    loadFileData();
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);
    }

    public static void displayMenu() {
        System.out.println("------ Library Management System ------");
        System.out.println("1. Add a new book");
        System.out.println("2. View all books");
        System.out.println("3. Update a book");
        System.out.println("4. Delete a book");
        System.out.println("5. Add a new member");
        System.out.println("6. View all members");
        System.out.println("7. Reserve a book");
        System.out.println("8. Unreserve a book");
        System.out.println("9. Load the file");
        System.out.println("10.Exit");
        System.out.print("Enter your choice: ");
    }

    public static void createBook() {
        System.out.println("------ Add a New Book ------");
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);
        library.createBook(book);

        System.out.println("Book added successfully.");
        System.out.println();
    }

    public static void viewAllBooks() {
        System.out.println("------ All Books ------");
        List<Book> allBooks = library.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : allBooks) {
                System.out.println(book);
            }
        }
        System.out.println();
    }

    public static void updateBook() {
        System.out.println("------ Update a Book ------");
        System.out.print("Enter book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Book> allBooks = library.getAllBooks();
        for (Book book : allBooks) {
            if (book.getId() == bookId) {
                System.out.print("Enter new book title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new book author: ");
                String newAuthor = scanner.nextLine();

                book.setTitle(newTitle);
                book.setAuthor(newAuthor);

                library.updateBook(book);

                System.out.println("Book updated successfully.");
                System.out.println();
                return;
            }
        }

        System.out.println("Book with ID " + bookId + " not found.");
        System.out.println();
    }

    public static void deleteBook() {
        System.out.println("------ Delete a Book ------");
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        library.deleteBook(bookId);

        System.out.println("Book deleted successfully.");
        System.out.println();
    }

    public static void addMember() {
        System.out.println("------ Add a New Member ------");
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();

        LibraryMember member = new LibraryMember(memberId, memberName);
        library.addMember(member);

        System.out.println("Member added successfully.");
        System.out.println();
    }

    public static void viewAllMembers() {
        System.out.println("------ All Members ------");
        List<LibraryMember> allMembers = library.getAllMembers();
        if (allMembers.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (LibraryMember member : allMembers) {
                System.out.println("Member ID: " + member.getMemberId() + ", Name: " + member.getName());
            }
        }
        System.out.println();
    }

    public static void reserveBook() {
        System.out.println("------ Reserve a Book ------");
        System.out.print("Enter book ID to reserve: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter member ID: ");
        String memberId = scanner.nextLine();

        library.reserveBook(bookId, memberId);

        System.out.println("Book reserved successfully.");
        System.out.println();
    }

    public static void unreserveBook() {
        System.out.println("------ Unreserve a Book ------");
        System.out.print("Enter book ID to unreserve: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        library.unreserveBook(bookId);

        System.out.println("Book unreserved successfully.");
        System.out.println();
    }
    public static void loadFileData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("library.csv"))) {
            Library serializedLibrary = (Library) ois.readObject();

            // Access and display the data from the serializedLibrary object
            List<Book> books = serializedLibrary.getAllBooks();
            List<LibraryMember> members = serializedLibrary.getAllMembers();

            System.out.println("------ Loaded File Data ------");
            System.out.println("Books:");
            for (Book book : books) {
                System.out.println(book);
            }

            System.out.println("Members:");
            for (LibraryMember member : members) {
                System.out.println(member);
                //System.out.println("Member ID: " + member.getMemberId() + ", Name: " + member.getName());
            }
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {// File doesn't exist or failed to load, continue with an empty list
        }
    }

}