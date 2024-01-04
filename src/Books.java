import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
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



    public Books() {
        loadBooksFromJson();
    }



    @Override
    public void register(Map<String, Book> bookProperties) {
        HashMap<String, Book> newBook = new HashMap<>(bookProperties);
        BookList.add(newBook);
        saveBooksToJson();
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


    private void loadBooksFromJson() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("BookList.json"));
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<HashMap<String, Book>>>(){}.getType();
            BookList = gson.fromJson(reader, type);
            if (BookList == null) {
                BookList = new ArrayList<>();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveBooksToJson() {
        try {
            FileWriter writer = new FileWriter("BookList.json");
            Gson gson = new Gson();
            gson.toJson(BookList, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
