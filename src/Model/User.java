package Model;

public class User {

    private int userId;
    private String userName;
    private String passWord;

    public User(int userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    //Get Methods
    public int getUserId() { return this.userId; }

    public String getUserName() { return this.userName; }

    public String getPassWord() { return this.passWord; }
}
