import java.io.Serializable;
import java.util.UUID;

public class LibraryMember implements Serializable {
    private int memberId;
    private String name;


    public LibraryMember(int memberId, String name) {
        this.memberId = Integer.parseInt(UUID.randomUUID().toString());
        //this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
