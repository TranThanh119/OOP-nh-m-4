package controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import quanlysinhvien.data.ConnectDB;
import static quanlysinhvien.data.ConnectDB.stmt;

public class classController {

    //Lấy mã lớp
    public int getClassIDInfor(String className){
        int index = 0;
        String sq = "SELECT `classID` FROM `class` WHERE `className` = '" + className +"'";
        try{
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                index =Integer.parseInt(rs.getString("classID"));
            }
        }catch(Exception e){
             System.out.println("ERR in classController.java: " + e.getMessage());
        }
        return index;
    }

    public String getClassNameInfor(String classID){
        String name = "";
        String sq = "SELECT `className` FROM `class` WHERE `classID` = '" + classID +"'";
        try{
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                name =rs.getString("className");
            }
        }catch(Exception e){
             System.out.println("ERR in classController.java: " + e.getMessage());
        }
        return name;
    }
    public String getIdClass(String i){
        String kq = null;
        try {
            String sq = "SELECT `classID` FROM `class` WHERE `className` = '"+i+"'";
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                kq = rs.getString("classID");
            }
        } catch (SQLException ex) {
            System.err.println("Loi lay ma lop!");
        }
        return kq;
    }
    //Thêm lớp
    public void addNewClass(String className){
        String sq = "INSERT INTO `class`(`className`) VALUES ('"+className+"');";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }
    //Xóa lớp
    public void deleteClass(String className){
        String id = this.getIdClass(className);
        String sq0 = "DELETE FROM `student` WHERE `classID` = "+ id +";" ;
        String sq01 = "ALTER TABLE `student` DROP FOREIGN KEY `fk_classID`;";
        String sq1 = "DELETE FROM `class` WHERE `classID` = "+id+";";
        String sq11 = "ALTER TABLE `student` ADD CONSTRAINT `fk_classID` FOREIGN KEY(`classID`) REFERENCES `class`(`classID`);";
        
        try {
            stmt.executeUpdate(sq0);
            stmt.executeUpdate(sq01);
            stmt.executeUpdate(sq1);
            stmt.executeUpdate(sq11);
        } catch (Exception e) {
        }
    }
    public void setModelCBB(JComboBox CB1){
        
        String sq = "SELECT `className` FROM `class`";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                String className = rs.getString("className");
                CB1.addItem(className);
            }
        } catch (SQLException ex) {
            System.out.println("ERR in classController.java: " + ex.getMessage());
        }
    }
    public classController() {
        new ConnectDB();
    }

}
