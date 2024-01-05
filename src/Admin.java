public class Admin extends Users {
    private String Password;
    private boolean Active;
    private String Type;

    public Admin(int ID, String Username, String Password, boolean Active, String Type) {
        super(ID, Username);
        this.Password = Password;
        this.Active = Active;
        this.Type = Type;
    }


    public boolean loginAdmin(String enteredUsername, String enteredPassword) {
        return this.getUsername().equals(enteredUsername) && this.Password.equals(enteredPassword);
    }

}
