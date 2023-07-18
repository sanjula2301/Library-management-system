import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class Library implements Serializable {
    private List<Book> books;
    private List<LibraryMember> members;
    private String dataFilePath;

    public Library(String dataFilePath) {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.dataFilePath = dataFilePath;
        loadLibrary();
    }

    public void createBook(Book book) {
        books.add(book);
        saveLibrary();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO book (id, title, author) VALUES (?, ?, ?)")) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating book: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
                saveLibrary();
                try (Connection connection = DatabaseConnector.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "UPDATE book SET title = ?, author = ? WHERE id = ?")) {
                    statement.setString(1, updatedBook.getTitle());
                    statement.setString(2, updatedBook.getAuthor());
                    statement.setInt(3, updatedBook.getId());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error updating book: " + e.getMessage());
                }
                return;
            }
        }
    }

    public void deleteBook(int bookId) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getId() == bookId) {
                books.remove(i);
                saveLibrary();
                try (Connection connection = DatabaseConnector.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "DELETE FROM book WHERE id = ?")) {
                    statement.setInt(1, bookId);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error Deleting book: " + e.getMessage());
                }
                return;
            }
        }
    }

    public void addMember(LibraryMember member) {
        members.add(member);
        saveLibrary();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO members (id, name) VALUES (?, ?)")) {
            statement.setInt(1, member.getMemberId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating member: " + e.getMessage());
        }

    }

    public List<LibraryMember> getAllMembers() {
        return members;
    }

    public void reserveBook(int bookId, String memberId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                book.reserve(memberId);
                saveLibrary();
                return;
            }
        }
    }

    public void unreserveBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                book.unreserve();
                saveLibrary();
                return;
            }
        }
    }

    private void saveLibrary() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLibrary() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            Library library = (Library) ois.readObject();
            books = library.getAllBooks();
            members = library.getAllMembers();
        } catch (IOException | ClassNotFoundException e) {
            // File doesn't exist or failed to load, continue with an empty list
        }
    }
}
