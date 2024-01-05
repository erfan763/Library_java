public class User extends Users {
    private int Age;
    private int Reserved;

    public User() {
        this.Age = Age;
        this.Reserved = -1;
    }

    public User(int ID, String Username, int Age) {
        super(ID, Username);
        this.Age = Age;
        this.Reserved = -1; // Default value when no book is reserved
    }

    public int getAge() {
        return Age;
    }

    public int getReserved() {
        return Reserved;
    }

    public void setReserved(int reserved) {
        Reserved = reserved;
    }

    public boolean isReserved(int ISBN) {
        return this.Reserved == ISBN;
    }

    public boolean loginUser(String enteredUsername) {
        return this.getUsername().equals(enteredUsername);
    }
}
