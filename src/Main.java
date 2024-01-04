import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Books library = new Books();

        Map<String, Book> bookProperties = new HashMap<>();
        bookProperties.put("123454jkj53", new Book("12345", "Book Title", "Author", "Summary", "Adult", BookStatus.AVAILABLE));
        library.register(bookProperties);

    }
}