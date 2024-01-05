import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DisplayUserMenu {


    User user = new User();
    public DisplayUserMenu(User user) {
        this.user  = user;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Books library = new Books();
            System.out.println("Menu:");
            System.out.println("1: Search for Book");
            System.out.println("2: Borrow Book");
            System.out.println("3: Return Book");
            System.out.println("4: List of Books");
            System.out.println("5: Exit");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter search type (1:ISBN, 2: Name, 3:Author, 4: Abstract): ");
                    int searchType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter keyword for search: ");
                    String keyword = scanner.nextLine();

                    Book foundBook = library.searchBook(searchType, keyword);
                    if (foundBook != null) {
                        System.out.println("Book found!");
                        System.out.println("ISBN: " + foundBook.ISBN);
                        System.out.println("Name: " + foundBook.Name);
                        System.out.println("Author: " + foundBook.Author);
                        System.out.println("Abstract: " + foundBook.Abstract);
                        System.out.println("Status: " + String.valueOf(foundBook.bookStatus));
                        System.out.println("ageGroup: " + String.valueOf(foundBook.ageGroup));

                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter book ISBN: ");
                    int bookIsbn = scanner.nextInt();
                    library.borrowBookUser(bookIsbn, user);

                    break;
                case 3:
                    library.returnBookUser(user);
                    break;
                case 4:
                    ArrayList<HashMap<String, Book>> bookList = library.getBookList();
                    if (bookList != null && !bookList.isEmpty()) {
                        for (HashMap<String, Book> bookMap : bookList) {
                            for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
                                String key = entry.getKey();
                                Book book = entry.getValue();
                                System.out.println("Key: " + key + ", Book Details: " + book.toString());
                            }
                        }
                    } else {
                        System.out.println("BookList is empty.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);
    }
}
