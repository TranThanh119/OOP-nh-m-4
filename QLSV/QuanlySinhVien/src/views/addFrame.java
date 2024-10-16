package views;

import controllers.classController;
import controllers.studentController;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.Student;
import util.imageProcess;

public class addFrame extends javax.swing.JFrame {
    classController classCTL = new classController();
    studentController stuCTL = new studentController();
    String path  = System.getProperty("user.dir");
    ImageIcon icon;
    String pathName = "";
    InputStream is = null;
    imageProcess imagePRC = new imageProcess();
    Student studentM = new Student();
    String stID;
    String stName;
    String stBirth;
    String stPhone;
    String stPoint;
    String stPoint_R ;
    String stGender;
    String stClass;


    public addFrame() {
        initComponents();
        inforBonus();
    }
    public void inforBonus(){
        classCTL.setModelCBB(editClassComboBox);
    }
//Set defaul
    public void refresh(){
        stID =  String.valueOf(studentM.getStID());
        stName = studentM.getStName();
        stBirth = studentM.getStBirth();
        stPhone = String.valueOf(studentM.getStPhone());
        stPoint = String.valueOf(studentM.getStPoint());
        stPoint_R = String.valueOf(studentM.getStPonit_R());
        stGender = String.valueOf(studentM.getStGender());
        stClass = String.valueOf(classCTL.getClassIDInfor(studentM.getStClass()));
        
        messageDLable.setForeground(Color.red);
        idDField.setText(stID);
        birthDField.setText("2001-01-01");
        pointDField.setText("4");
    }
    public void studentMUpdate(){
        try{
            studentM.setStName(NameDField.getText());
            //Chu y cho tuoi
            studentM.setStBirth(birthDField.getText());
            studentM.setStGender(stuCTL.convertGenderToF(editGenderComboBox.getItemAt(0)));
            studentM.setStPhone(Integer.parseInt(phoneDField.getText()));
            studentM.setStClass(String.valueOf(editClassComboBox.getSelectedItem()));
            studentM.setStPoint(Float.parseFloat(pointDField.getText()));
            studentM.setStPonit_R(point_RDScorllBar.getValue());
        }catch(Exception e){
            messageDLable.setText("Thông tin không đúng");
        }
    }
    public void setValueOfPR(){
        int value = point_RDScorllBar.getValue();
        point_RDLabel.setText(String.valueOf(value));
        studentM.setStPonit_R(value);
    }
    public boolean isNullField(){
        
        if(stID.equals("0") || stName.equals("") || stBirth.equals("0")
                || stPhone.equals("0")){
            return true;
        }
        return false;
    }
 //xử lý nút Thêm
    public void addNewStudent(){
        studentMUpdate();
        refresh();
        if(isNullField()){
            messageDLable.setText("Failed! Something is NULL!");
        }
        else{
            try{
                stuCTL.insertDB(stID, stName, stBirth, stGender, stPhone, Integer.parseInt(stClass), stPoint, stPoint_R);
                if(is!=null)imagePRC.updateImage(Integer.parseInt(stID), is);
                messageDLable.setText("Sinh viên + " + idDField.getText() + " đã được thêm");
                messageDLable.setForeground(Color.BLUE);
                int idNext = Integer.parseInt(idDField.getText())+1;
                studentM.setStID(idNext);
                idDField.setText(String.valueOf(idNext));
                NameDField.setText("");
                birthDField.setText("");
                phoneDField.setText("");
                pointDField.setText("");
                anhLabel.setIcon(null);
                is = null;
            }catch(Exception e){
                messageDLable.setText("Sinh viên + " + idDField.getText() + " đã được thêm");
            }
        }
    }
    //Chọn ảnh
    public void chonAnhFunc(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(path));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            pathName = path;
            ImageIcon icon = new ImageIcon(path);
            anhLabel.setIcon(imagePRC.scaleImage(icon,anhLabel));
            try {
                is = new FileInputStream(selectedFile);
            } catch (FileNotFoundException ex) {
            }
            setTitle(selectedFile.getName());
        } else {
            System.out.println("No data");
        }
    }
    //Hàm xóa ảnh
    public void xoaAnhFunc(){
        is = null;
        anhLabel.setIcon(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detailLabel = new javax.swing.JLabel();
        NameDField = new javax.swing.JTextField();
        birthDField = new javax.swing.JTextField();
        phoneDField = new javax.swing.JTextField();
        pointDField = new javax.swing.JTextField();
        editGenderComboBox = new javax.swing.JComboBox<>();
        idDField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        saveBTN = new javax.swing.JButton();
        messageDLable = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        editClassComboBox = new javax.swing.JComboBox<>();
        point_RDScorllBar = new javax.swing.JSlider();
        point_RDLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        anhLabel = new javax.swing.JLabel();
        chonAnhBTN = new javax.swing.JButton();
        xoaAnhBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        detailLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        detailLabel.setForeground(new java.awt.Color(0, 153, 153));
        detailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailLabel.setText("Thêm thông tin");

        editGenderComboBox.setEditable(true);
        editGenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        idDField.setEnabled(false);

        jLabel2.setText("ID");

        jLabel3.setText("Họ tên");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Giới tính");
        jLabel5.setToolTipText("");

        jLabel6.setText("SĐT");

        jLabel7.setText("Điểm");

        saveBTN.setBackground(new java.awt.Color(0, 153, 153));
        saveBTN.setForeground(new java.awt.Color(255, 255, 255));
        saveBTN.setText("Thêm");
        saveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTNActionPerformed(evt);
            }
        });

        messageDLable.setForeground(new java.awt.Color(204, 0, 0));
        messageDLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setText("Hạng");

        jLabel10.setText("Lớp");

        editClassComboBox.setEditable(true);

        point_RDScorllBar.setValue(0);
        point_RDScorllBar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                point_RDScorllBarStateChanged(evt);
            }
        });

        point_RDLabel.setText("0");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        anhLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(anhLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(anhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        chonAnhBTN.setForeground(new java.awt.Color(0, 102, 102));
        chonAnhBTN.setText("Chọn");
        chonAnhBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chonAnhBTNActionPerformed(evt);
            }
        });

        xoaAnhBTN.setForeground(new java.awt.Color(204, 0, 0));
        xoaAnhBTN.setText("Xóa");
        xoaAnhBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaAnhBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(detailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addGap(82, 82, 82))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageDLable, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneDField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pointDField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(idDField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(birthDField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NameDField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(point_RDScorllBar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(point_RDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(xoaAnhBTN)
                                .addGap(18, 18, 18)
                                .addComponent(chonAnhBTN)
                                .addGap(42, 42, 42))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBTN)
                .addGap(228, 228, 228))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(detailLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(birthDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(editGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pointDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(point_RDScorllBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(point_RDLabel)
                        .addComponent(chonAnhBTN)
                        .addComponent(xoaAnhBTN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(messageDLable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveBTN)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void point_RDScorllBarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_point_RDScorllBarStateChanged
        setValueOfPR();
    }//GEN-LAST:event_point_RDScorllBarStateChanged

    private void saveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTNActionPerformed
        try {
            addNewStudent();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Kiểm tra lại thông tin!", "", 0);
        }
    }//GEN-LAST:event_saveBTNActionPerformed

    private void chonAnhBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chonAnhBTNActionPerformed
        chonAnhFunc();
    }//GEN-LAST:event_chonAnhBTNActionPerformed

    private void xoaAnhBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaAnhBTNActionPerformed
        xoaAnhFunc();
    }//GEN-LAST:event_xoaAnhBTNActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameDField;
    private javax.swing.JLabel anhLabel;
    private javax.swing.JTextField birthDField;
    private javax.swing.JButton chonAnhBTN;
    private javax.swing.JLabel detailLabel;
    private javax.swing.JComboBox<String> editClassComboBox;
    private javax.swing.JComboBox<String> editGenderComboBox;
    private javax.swing.JTextField idDField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel messageDLable;
    private javax.swing.JTextField phoneDField;
    private javax.swing.JTextField pointDField;
    private javax.swing.JLabel point_RDLabel;
    private javax.swing.JSlider point_RDScorllBar;
    private javax.swing.JButton saveBTN;
    private javax.swing.JButton xoaAnhBTN;
    // End of variables declaration//GEN-END:variables
}
