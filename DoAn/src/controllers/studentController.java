package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Student;
import quanlysinhvien.data.ConnectDB;
import static quanlysinhvien.data.ConnectDB.stmt;

public class studentController {
    classController clCTL = new classController();
    static Student[] st;
//Hàm set và get   
    public static void setLength(String sq) throws SQLException{
        int Sl = 0;
        ResultSet rs1 = stmt.executeQuery(sq);
        while (rs1.next()) {            
            Sl++;
        }
        st = new Student[Sl];
    }
//Giới tính
    public String convertGender(String i){
        if(i.equals("0"))
            return "Male";
        else
            return "Female";
    }
    public String convertGenderToF(String i){
        if(i.equals("Female"))
            return "true";
        else
            return "false";
    }
    //Xóa dữ liệu của bảng
    public void deleteTableData(){
        String sq = "DELETE FROM `student` WHERE `classID` != -1;";
        try {
            stmt.executeUpdate(sq);
            System.out.println("Đã xóa");
        } catch (SQLException ex) {
            System.out.println("Loi ham deleteTableData() trong studentController: " + ex.getMessage());
        }
    }
    //Thêm dữ liệu vào bảng
    public void insertDB(String stID,String stName, String stBirth, String stGender,String stPhone,int classID, String stPoint, String stPoint_R){
        String sq = "INSERT INTO `student`(`stID`,`stName`, `stBirth`, `stGender`, `stPhone`, `classID`, `stPoint`, `stPoint_R`) "
                + "VALUES (" + stID + ",'" +stName+ "','" +stBirth+ "'," +stGender + "," + stPhone + "," +classID + "," + stPoint+ "," + stPoint_R+ ")";
        try {
            stmt.executeUpdate(sq);
        } catch (SQLException ex) {
            System.err.println("Loi ham insertDB() trong studentController: " + ex.getMessage());
        }
    }
    public void loadDB(JTable table, int classID, boolean all){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        int i = 0;
        
        String sq = "SELECT `stID`,`stName`, `stBirth`, `stGender`, `stPhone`,`class`.`className`, `stPoint`, `stPoint_R` \n" +
                    "FROM `student`,`class` \n" +
                    "WHERE `student`.`classID`=`class`.`classID`\n"; 
        
        if(!all){
            sq = sq + "AND `student`.`classID`= " + classID + ";";
        }
        try {
            setLength(sq);
            
            ResultSet rs = stmt.executeQuery(sq);
            
            while(rs.next()){
                int stID = Integer.parseInt(rs.getString("stID"));
                String stName = rs.getString("stName");
                String stBirth = rs.getString("stBirth");
                String stGender = convertGender(rs.getString("stGender"));
                int stPhone = Integer.parseInt(rs.getString("stPhone"));
                String stClass = rs.getString("className");
                float stPoint = Float.parseFloat(rs.getString("stPoint"));
                int stPoint_R = Integer.parseInt(rs.getString("stPoint_R"));
                
                st[i] = new Student(stID, stName, stBirth, stGender, stPhone, stClass, stPoint, stPoint_R);
                i++;
                
                String[] data = {String.valueOf(stID),stName,stBirth,stGender,String.valueOf(stPhone),
                                stClass,String.valueOf(stPoint),String.valueOf(stPoint_R)};
                model.addRow(data);
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
    }
    public void loadDB(JTable table, float minP, float maxP, int minPR, int maxPR){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        int i = 0;
        
        String sq = "SELECT `stID`,`stName`, `stBirth`, `stGender`, `stPhone`,`class`.`className`, `stPoint`, `stPoint_R` \n" +
                    "FROM `student`,`class` \n" +
                    "WHERE `student`.`classID` = `class`.`classID` AND `stPoint`<="+maxP+" AND "+minP+"<=`stPoint` AND `stPoint_R`<="+maxPR+" AND "+minPR+"<=`stPoint_R`;"; 

        try {
            setLength(sq);
            
            ResultSet rs = stmt.executeQuery(sq);
            
            while(rs.next()){
                int stID = Integer.parseInt(rs.getString("stID"));
                String stName = rs.getString("stName");
                String stBirth = rs.getString("stBirth");
                String stGender = convertGender(rs.getString("stGender"));
                int stPhone = Integer.parseInt(rs.getString("stPhone"));
                String stClass = rs.getString("className");
                float stPoint = Float.parseFloat(rs.getString("stPoint"));
                int stPoint_R = Integer.parseInt(rs.getString("stPoint_R"));
                
                st[i] = new Student(stID, stName, stBirth, stGender, stPhone, stClass, stPoint, stPoint_R);
                i++;
                
                String[] data = {String.valueOf(stID),stName,String.valueOf(stBirth),stGender,String.valueOf(stPhone),
                                stClass,String.valueOf(stPoint),String.valueOf(stPoint_R)};
                model.addRow(data);
            }
            
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
    }
//GetStudentByID
    public Student getStudentByID(int IDkey){
        Student st = new Student();
        
        int ID = 0;
        String name = "";
        String birth = "";
        String gender = "";
        int phone = 0;
        String classID = "";
        String className = "";
        float point = 0;
        int point_R = 0;
        
        String sq ="Select * from `student` where stID = "+IDkey+";";
        
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                ID = Integer.parseInt(rs.getString("stID"));
                name = rs.getString("stName");
                birth = rs.getString("stBirth");
                gender = convertGender(rs.getString("stGender"));
                phone = Integer.parseInt(rs.getString("stPhone"));
                classID = rs.getString("classID");
                point = Float.parseFloat(rs.getString("stPoint"));
                point_R = Integer.parseInt(rs.getString("stPoint_R"));
            }
        } catch (SQLException ex) {
        }
        
        className = clCTL.getClassNameInfor(classID);
        st = new Student(ID, name, birth, gender, phone, className, point, point_R);
        
        return st;
    }
//UpdateStInfor()
    public void updateStInfor(Student st){
        int ID = st.getStID();
        String name = st.getStName();
        String birth = "2001-12-12";
        String gender = convertGenderToF(st.getStGender());
        int phone = st.getStPhone();
        
        String className = st.getStClass();
        int classID = clCTL.getClassIDInfor(className);
        float point = st.getStPoint();
        int point_R = st.getStPonit_R();
        String sq = "UPDATE `student` SET `stName`='"+name+"',`stBirth`='"+birth+"',"
                + "`stGender`="+gender+",`stPhone`="+phone+",`classID`="+classID+",`stPoint`="+point+",`stPoint_R`="+point_R+" WHERE `stID`= "+ID+";";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
        System.out.println("Updated!");
    }
//Xóa sinh viên
    public  void deleteSt(Student st){
        String sq = "DELETE FROM `student` WHERE `stID`= " + st.getStID()+ ";";
        try {
            stmt.executeUpdate(sq);
        } catch (Exception e) {
        }
    }
    public studentController() {
        new ConnectDB();
    }
    
    public static void main(String[] args) {
        studentController studentCTL = new studentController();
        JTable table = new JTable();
        Student st = studentCTL.getStudentByID(4);
        System.err.println("Diem cu: "+st.getStName());
        st.setStName("Bui Trung Duc");
        studentCTL.updateStInfor(st);
        System.out.println("Diem moi: "+st.getStName());
    }
}
