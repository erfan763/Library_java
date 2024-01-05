import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DisplayAdminMenu {
    Books library = new Books();
    UserManagement userManagement = new UserManagement();
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1: Register User");
            System.out.println("2: Register Book");
            System.out.println("3: Search for Book");
            System.out.println("4: Borrow Book");
            System.out.println("5: Return Book");
            System.out.println("6: List of Users");
            System.out.println("7: List of Books");
            System.out.println("8: List of Reserve Books");
            System.out.println("9: Exit");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter username: ");
                    String username = scanner.nextLine();

                    System.out.println("Enter user Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    User newUser = new User(userId, username , age);

                    userManagement.addUserToJSON(newUser, "Users.json");
                    System.out.println("User added successfully.");
                    break;
                case 2:
                    Map<String, Book> bookProperties = new HashMap<>();
                    System.out.println("Enter ISBN: ");
                    int isbn = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Book Title: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.println("Enter Summary: ");
                    String summary = scanner.nextLine();
                    System.out.println("Enter Age Group 'CHILD' , 'TEEN' , 'ADULT': ");
                    String ageGroup = scanner.nextLine().trim().toUpperCase();

                    bookProperties.put("KEY", new Book(isbn, name, author, summary, AgeGroup.valueOf(ageGroup), BookStatus.AVAILABLE));
                    library.register(bookProperties);

                    break;
                case 3:
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
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 4:
                    // Logic for borrowing a book
                    // ...
                    break;
                case 5:
                    // Logic for returning a book
                    // ...
                    break;
                case 6:
                    System.out.println("List of Users:");
                    userManagement.printListOfUsers();
                    break;
                case 7:

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
                case 8:
                    ArrayList<HashMap<String, Book>> reverseBookList = library.getReserver();
                    if (reverseBookList != null && !reverseBookList.isEmpty()) {
                        for (HashMap<String, Book> bookMap : reverseBookList) {
                            for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
                                String key = entry.getKey();
                                Book book = entry.getValue();
                                System.out.println("Key: " + key + ", Book Details: " + book.toString());
                            }
                        }
                    } else {
                        System.out.println("ReverseBookList is empty.");
                    }
                    break;
                case 9:
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 9);

        scanner.close();
    }
}
