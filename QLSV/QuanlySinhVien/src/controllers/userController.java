package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import quanlysinhvien.data.ConnectDB;
import static quanlysinhvien.data.ConnectDB.stmt;

public class userController {
    
    private User user1;

    public User getUser1() {
        return user1;
    }
//Update MK
    public void updatePass(String name,String pass){
        try {
            String sq0 = "UPDATE `user` SET `passWord`='" + pass + "' WHERE `userName`='" + name + "'";
            stmt.executeUpdate(sq0);
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Kiểm tra người dùng tồn tại
    public boolean checkUser(String userName){
        boolean i = true;
        try {
            String sq0 = "SELECT * FROM `user` WHERE `userName`= '" + userName + "'";
            ResultSet rs = stmt.executeQuery(sq0);
            i = rs.next();
        } catch (SQLException ex) {
        }
        return i;
    }
//Lấy thông tin người dùng
    public void getUserInfor(String userName){
        int id;
        String name,pass;
        String sq0 = "SELECT * FROM `user` WHERE `userName`= '" + userName + "'";
        try {
            ResultSet rs = stmt.executeQuery(sq0);
            while(rs.next()){
                id = Integer.parseInt(rs.getString("userID"));
                name =  rs.getString("userName");
                pass =  rs.getString("passWord");
                user1 = new User(id,name,pass);
            }
        } catch (SQLException ex) {
        }
    }
    public User getUser(String userName){
        User user = new User();
        int id;
        String name,pass;
        String sq0 = "SELECT * FROM `user` WHERE `userName`= '" + userName + "'";
        try {
            ResultSet rs = stmt.executeQuery(sq0);
            while(rs.next()){
                id = Integer.parseInt(rs.getString("userID"));
                name =  rs.getString("userName");
                pass =  rs.getString("passWord");
                user = new User(id,name,pass);
            }
        } catch (SQLException ex) {
        }
        return user;
    }
//Update Thông tin người dùng
    public void updateUser(User user){
        String sq = "UPDATE `user` SET `userName`='"+user.getUserName() +"',`passWord`='"+user.getPassWord()+"' WHERE `userID`= "+user.getUserID()+";";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }
//Thêm người dùng  
    public void insertUser(String name, String pass){
        try {
            String sq1 = "INSERT INTO `user`(`userName`, `passWord`) VALUES ('" + name +"','" + pass + "')";
            stmt.executeUpdate(sq1);
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public userController() {
        new ConnectDB();
    }
}