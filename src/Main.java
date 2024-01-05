import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var isLogin = true;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Are you an admin or a simple user? Enter 'admin' or 'user' or finally 'closeProgram': ");
            String userType = scanner.nextLine();
            if(userType == "closeProgram") {
                isLogin = false;
            }
            if(!isLogin) {
                scanner.close();
            }
            if (userType.equalsIgnoreCase("admin")) {

                System.out.println("Enter admin username: ");
                String enteredAdminUsername = scanner.nextLine();
                System.out.println("Enter admin password: ");
                String enteredAdminPassword = scanner.nextLine();

                Admin adminUser = new Admin(1, "admin", "admin", true, "admin");

                if (adminUser.loginAdmin(enteredAdminUsername, enteredAdminPassword)) {

                    System.out.println("Admin login successful.");
                    DisplayAdminMenu displayAdminMenu = new DisplayAdminMenu();
                    displayAdminMenu.displayMenu();

                } else {
                    System.out.println("Admin login failed. Invalid credentials.");
                }
            } else if (userType.equalsIgnoreCase("user")) {
                System.out.println("Enter user username: ");
                String enteredUserUsername = scanner.nextLine();

                UserManagement userManagement = new UserManagement();

                boolean userFound = false;
                User userDetails = new User();
                for (User user : userManagement.usersList) {
                    if (user.getUsername().equals(enteredUserUsername)) {
                        System.out.println("User details found:");
                        System.out.println("ID: " + user.getID());
                        System.out.println("Username: " + user.getUsername());
                        userFound = true;
                        if(userFound) {
                            userDetails = user;
                        }
                        break;
                    }
                }
                if(userFound) {
                    DisplayUserMenu displayUserMenu = new DisplayUserMenu(userDetails);
                    displayUserMenu.displayMenu();
                }

                if (!userFound) {
                    System.out.println("User not found with this username.");
                }
            } else {
                System.out.println("Invalid user type entered.");
            }


        } while (isLogin);
    }
}