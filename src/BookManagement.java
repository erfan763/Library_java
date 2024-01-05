import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BookManagement {
    public abstract void register(Map<String, Book> bookProperties);
    public abstract ArrayList<HashMap<String, Book>> list();
    public abstract boolean ageOK(int age);
    public abstract void writeStatus(Map<String, Book> bookProperties);
    public abstract String lastStatus();


    public abstract void saveBooksToJson(String fileName, ArrayList<HashMap<String, Book>> BookList);

//    public abstract void loadBooksFromJson(String fileName, ArrayList<HashMap<String, Book>> BookList);
}
