import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Books extends BookManagement {
    private int ResCount;
    private ArrayList<HashMap<String, Book>> BookList;
    private ArrayList<HashMap<String, Book>>  Reserver;

    public int getResCount() {
        return ResCount;
    }

    public void setResCount(int resCount) {
        ResCount = resCount;
    }

    public ArrayList<HashMap<String, Book>> getBookList() {
        return BookList;
    }

    public void setBookList(ArrayList<HashMap<String, Book>> bookList) {
        BookList = bookList;
    }

    public ArrayList<HashMap<String, Book>> getReserver() {
        return Reserver;
    }

    public void setReserver(ArrayList<HashMap<String, Book>> reserver) {
        Reserver = reserver;
    }

    @Override
    public void register(Map<String, Book> bookProperties) {
      return ;
    }

    @Override
    public ArrayList<HashMap<String, Book>> list() {
      return BookList;
    }

    @Override
    public boolean ageOK(int age) {
        return false;
    }

    @Override
    public void writeStatus(Map<String, Book> bookProperties) {

    }

    @Override
    public String lastStatus() {
        return null;
    }
}
