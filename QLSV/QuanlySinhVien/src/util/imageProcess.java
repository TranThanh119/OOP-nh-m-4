package util;

import quanlysinhvien.data.ConnectDB;
import static quanlysinhvien.data.ConnectDB.con;
import static quanlysinhvien.data.ConnectDB.stmt;
import java.awt.Image;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class imageProcess {
    
    public imageProcess() {
        new ConnectDB();
    }
    public ImageIcon scaleImage(ImageIcon icon, JLabel label) {
        Image img = icon.getImage();

        float w_img = icon.getIconWidth();
        float h_img = icon.getIconHeight();

        float w = label.getWidth();
        float h = label.getHeight();

        float tlK = w / h;
        System.err.println(tlK);
        float tl = w_img / h_img;
     
        if (tl>=tlK) {
            w_img = w;
            h_img = w_img / tl;
        } else if ( tl < tlK) {
            h_img = h;
            w_img = h_img * tl;
        }
        System.out.print("(" + w + "," + h + ") " + tl + " => ");
        int wf = Math.round(w_img);
        int hf = Math.round(h_img);
        
        System.out.println("(" + w + "," + h + ")");
        Image sImg = img.getScaledInstance(wf, hf, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sImg);
        return icon;
    }
    public byte [] getImageByID(int id){
        byte[] bin = null;
        String sq = "SELECT `hinhanh` FROM `student` WHERE `stID` = "+id+"";
        try {
            ResultSet rs = stmt.executeQuery(sq);
            while(rs.next()){
                bin = rs.getBytes("hinhanh");
            }
        }catch(NumberFormatException | SQLException e){
        }
        return bin;
    }
    public void updateImage(int id, InputStream is){
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE `student` SET `hinhanh`= ? WHERE `stID`= "+id+"");
            ps.setBlob(1, is);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
