
package models;

public class User {
    
//Khai báo thuộc tính user
    private int userID;
    private String userName;
    private String passWord;
  
    public User() {
    }
//Khởi tạo hàm có tham số
    public User(int userID, String userName, String passWord) {
        this.userID = userID;
        this.userName = userName;
        this.passWord = passWord;
    }
//Phương thức get và set
    public int getUserID() {
        return userID;
    }

    public void setUseID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    
}
