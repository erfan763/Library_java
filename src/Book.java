import java.util.ArrayList;
import java.util.HashMap;

public class Book {
    private int ResCount;
    private ArrayList<HashMap<String, Object>> BookList;
    private ArrayList<HashMap<String, Object>>  Reserver;

    public int getResCount() {
        return ResCount;
    }

    public void setResCount(int resCount) {
        ResCount = resCount;
    }

    public ArrayList<HashMap<String, Object>> getBookList() {
        return BookList;
    }

    public void setBookList(ArrayList<HashMap<String, Object>> bookList) {
        BookList = bookList;
    }

    public ArrayList<HashMap<String, Object>> getReserver() {
        return Reserver;
    }

    public void setReserver(ArrayList<HashMap<String, Object>> reserver) {
        Reserver = reserver;
    }
}
