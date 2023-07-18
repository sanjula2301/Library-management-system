import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private boolean reserved;
    private String reservedBy;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.reserved = false;
        this.reservedBy = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isReserved() {
        return reserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void reserve(String memberId) {
        reserved = true;
        reservedBy = memberId;
    }

    public void unreserve() {
        reserved = false;
        reservedBy = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
