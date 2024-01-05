import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public ArrayList<HashMap<String, Book>> getReserver() {
        return Reserver;
    }

    public void setReserver(ArrayList<HashMap<String, Book>> reserver) {
        Reserver = reserver;
    }



    public Books() {
        loadBooksFromJson();
        loadReserverBooksFromJson();

        setResCount(Reserver != null ? Reserver.size() : 0);
        System.out.println(ResCount);
    }

    @Override
    public void register(Map<String, Book> bookProperties) {
        HashMap<String, Book> newBook = new HashMap<>(bookProperties);
        Book myBook = newBook.get("KEY");

        boolean isISBNUnique = checkUniqueISBN(myBook.ISBN);
        if (!isISBNUnique) {
            System.out.println("Error: ISBN is not unique. Cannot register the book.");
            return;
        }

        newBook.remove("KEY");
        newBook.put(String.valueOf(myBook.ISBN),myBook);
        BookList.add(newBook);
        saveBooksToJson("BookList.json", BookList);
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
            Type type = new TypeToken<LinkedList<HashMap<String, Book>>>(){}.getType();
            LinkedList<HashMap<String, Book>> linkedList = gson.fromJson(reader, type);
            if (linkedList == null || linkedList.isEmpty()) {
                BookList = new ArrayList<>();
            }else {
                BookList = new ArrayList<>(linkedList);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadReserverBooksFromJson() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ReserverList.json"));
            Gson gson = new Gson();
            Type type = new TypeToken<LinkedList<HashMap<String, Book>>>(){}.getType();
            LinkedList<HashMap<String, Book>> linkedList = gson.fromJson(reader, type);
            if (Reserver == null) {
                Reserver = new ArrayList<>();
            } else {
                Reserver = new ArrayList<>(linkedList);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   @Override
    public void saveBooksToJson(String fileName, ArrayList<HashMap<String, Book>> BookList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            Gson gson = new Gson();
            gson.toJson(BookList, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkUniqueISBN(int ISBN) {
        for (int i = 0; i < BookList.size(); i++) {
            HashMap<String, Book> bookMap = BookList.get(i);
            Book book = bookMap.get(String.valueOf(i + 1));
            if (book.ISBN == ISBN) {
                return false;
            }
        }
        return true;
    }





    public Book searchBook(int type, String keyword) {
        ArrayList<HashMap<String, Book>> searchList = new ArrayList<>();
        Book foundBook = null;

        switch (type) {
            case 1: // Search by ISBN
                for (HashMap<String, Book> bookMap : BookList) {
                    for (Book book : bookMap.values()) {
                        if (book.ISBN == Integer.parseInt(keyword)) {
                            foundBook = book;
                            break;
                        }
                    }
                }
                break;

            case 2: // Search by Name
                for (HashMap<String, Book> bookMap : BookList) {
                    for (Book book : bookMap.values()) {
                        if (book.Name.equals(keyword)) {
                            foundBook = book;
                            break;
                        }
                    }
                }
                break;

            case 3: // Search by Author
                for (HashMap<String, Book> bookMap : BookList) {
                    for (Book book : bookMap.values()) {
                        if (book.Author.equals(keyword)) {
                            foundBook = book;
                            break;
                        }
                    }
                }
                break;

            case 4: // Search by Abstract using RegExp
                Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
                for (HashMap<String, Book> bookMap : BookList) {
                    for (Book book : bookMap.values()) {
                        Matcher matcher = pattern.matcher(book.Abstract);
                        if (matcher.find()) {
                            foundBook = book;
                            break;
                        }
                    }
                }
                break;

            default:
                System.out.println("Invalid search type.");
        }

        return foundBook;
    }

    private int parseAgeGroup(AgeGroup ageGroup) {
        int age = 0;
        switch (AgeGroup.valueOf(String.valueOf(ageGroup))) {
            case CHILD:
                age = 8;
                break;
            case TEEN:
                age = 13;
                break;
            case ADULT:
                age = 18;
                break;
            default:
                break;
        }
        return age;
    }


    private Book findBookByISBN(int ISBN) {
        if (BookList != null) {
            for (HashMap<String, Book> bookMap : BookList) {
                for (Book book : bookMap.values()) {
                    if (book.ISBN == ISBN) {

                        return book;
                    }
                }
            }
        }
        return null;
    }


    public void borrowBookUser(int ISBN, User user) {
        Book bookToBorrow = findBookByISBN(ISBN);
        System.out.println(bookToBorrow);
        if (bookToBorrow != null) {
            if (bookToBorrow.bookStatus == BookStatus.AVAILABLE) {

                if (user.getAge() >= parseAgeGroup(bookToBorrow.ageGroup)) {

                    if (user.getReserved() == -1) {
                        if (BookList != null) {
                            for (HashMap<String, Book> bookMap : BookList) {
                                for (Book book : bookMap.values()) {
                                    if (book.ISBN == ISBN) {
                                        book.bookStatus = BookStatus.BORROWED;
                                    }
                                }
                            }
                        }

                        // have bug. i should update reservd in user (update isbn)
                        bookToBorrow.bookStatus = BookStatus.BORROWED;
                        user.setReserved(ISBN);
                        HashMap<String, Book> revBook = new HashMap<String, Book>();
                        revBook.put(String.valueOf(user.getID()), bookToBorrow);
                        Reserver.add(revBook);
                        setResCount(Reserver != null ? Reserver.size() : 0);
                        saveBooksToJson("ReserverList.json", Reserver);
                        saveBooksToJson("BookList.json", BookList);

                        System.out.println("Book reserved successfully.");
                    } else {
                        System.out.println("Error: User already has a reserved book.");
                    }
                } else {
                    System.out.println("Error: User's age does not match the book's age group.");
                }
            } else {
                System.out.println("Error: Book is not available for borrowing.");
            }
        } else {
            System.out.println("Error: Book with ISBN " + ISBN + " not found.");
        }
    }

    public void returnBookUser(User user) {
        Book bookToBorrow = findBookByISBN(user.getReserved());
        if (bookToBorrow != null) {
            if (bookToBorrow.bookStatus == BookStatus.BORROWED) {
                    if (user.getReserved() != -1) {

                        if (BookList != null) {
                            for (HashMap<String, Book> bookMap : BookList) {
                                for (Book book : bookMap.values()) {
                                    if (book.ISBN == user.getReserved()) {
                                        book.bookStatus = BookStatus.AVAILABLE;
                                    }
                                }
                            }
                        }
                        bookToBorrow.bookStatus = BookStatus.AVAILABLE;
                        user.setReserved(-1);

                        Reserver.remove(user.getID());
                        setResCount(Reserver != null ? Reserver.size() : 0);
                        saveBooksToJson("ReserverList.json", Reserver);
                        saveBooksToJson("BookList.json", BookList);
                        System.out.println("Book return successfully.");

                    } else {
                        System.out.println("Error: User already has a reserved book.");
                    }

            } else {
                System.out.println("Error: Book is available for borrowing.");
            }
        } else {
            System.out.println("Error: Book with ISBN " + user.getReserved() + " not found.");
        }
    }
}
