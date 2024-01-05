import java.time.LocalDateTime;

public class Users implements java.io.Serializable {
    private int ID;
    private String Username;
    private String  RegTime;

    public Users(int ID, String Username) {
        this.ID = ID;
        this.Username = Username;
        this.RegTime = LocalDateTime.now().toString();
    }

    public Users() {

    }

    public void displayUserInfo() {
        System.out.println("ID: " + ID);
        System.out.println("Username: " + Username);
        System.out.println("Registration Time: " + RegTime);
    }

    public LocalDateTime getRegTime() {
        return LocalDateTime.parse(RegTime);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return this.Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
}
