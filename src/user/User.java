package user;

public abstract class User {
    
    //instance variables 
    protected String userID;
    protected String password; 
    protected Role role;
    protected boolean isLoggedIn;

    //contructor
    public User(String userID, String password, Role role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    //instance method
    public abstract boolean login(String enteredPassword );
    public abstract void logout();
    public abstract boolean changePassword(String currentPassword, String newPassword);

    //Getters & Setters
    public String getUserID() {
        return userID;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String newPassword){
        password=newPassword;

    }

}
