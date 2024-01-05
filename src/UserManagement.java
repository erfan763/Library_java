import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserManagement {

    public List<User> usersList;


    public UserManagement() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("Users.json")) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            usersList = gson.fromJson(reader, userListType);
            if(usersList == null) {
                usersList = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUserToJSON(User userToAdd,  String fileName) {

        if (!isUsernameUnique(userToAdd.getUsername()) || !isIdUnique(userToAdd.getID())) {
            System.out.println("Error: Username or ID is not unique. Cannot add the user.");
            return;
        }
            this.usersList.add(userToAdd);
            JsonFileHandler jsonFileHandler = new JsonFileHandler();
            jsonFileHandler.writeUsersToJson(this.usersList, fileName);
            System.out.println("User added successfully.");

    }

    public void printListOfUsers() {
        if (usersList != null && !usersList.isEmpty()) {
            for (User user : usersList) {
                System.out.println("ID: " + user.getID() + ", Username: " + user.getUsername());
            }
        } else {
            System.out.println("No users found.");
        }
    }


    public User findUserByUsername(String username) {
        for (int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).getUsername() == username){
                return  usersList.get(i);
            }
        }
        return null;
    }

    public boolean isUsernameUnique(String username) {
        for (User user : usersList) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdUnique(int id) {
        for (User user : usersList) {
            if (user.getID() == id) {
                return false;
            }
        }
        return true;
    }

}
