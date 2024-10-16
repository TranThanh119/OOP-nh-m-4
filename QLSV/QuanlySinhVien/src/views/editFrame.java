package views;

import controllers.classController;
import controllers.studentController;
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

public class editFrame extends javax.swing.JFrame {
    classController classCTL = new classController();
    studentController stuCTL = new studentController();
    Student studentM = new Student();
    String path  = System.getProperty("user.dir");
    ImageIcon icon;
    String pathName = "";
    InputStream is = null;
    imageProcess imagePRC = new imageProcess();

    public editFrame() {
        initComponents();
        inforBonus();
    }
    public editFrame(int ID) {
        studentM = stuCTL.getStudentByID(ID);
        initComponents();
        inforBonus();
    }
//Bổ sung
    public void inforBonus(){
        messageDLable.setText("");
        classCTL.setModelCBB(editClassComboBox);
        idDField.setText(String.valueOf(studentM.getStID()));
        NameDField.setText(studentM.getStName());
        birthDField.setText(studentM.getStBirth());
        genderField.setText(studentM.getStGender());
        phoneDField.setText(String.valueOf(studentM.getStPhone()));
        classDField.setText(studentM.getStClass());
        pointDField.setText(String.valueOf(studentM.getStPoint()));
        point_RDLabel.setText(String.valueOf(studentM.getStPonit_R()));
        point_RDScorllBar.setValue(studentM.getStPonit_R());
        
        if(imagePRC.getImageByID(studentM.getStID()) != null){
            icon = new ImageIcon(imagePRC.getImageByID(studentM.getStID()));
            icon = imagePRC.scaleImage(icon, anhLabel);
            anhLabel.setIcon(icon);
        }
    } 
    public void beginStatus(){
        
    }
//Nhập Hạng
    private void setChangePoint_R() {
        int point_R = point_RDScorllBar.getValue();
        point_RDLabel.setText(String.valueOf(point_R));
    }    
//Chế độ edit
    public void setEditOption(){
        if(editDLabel.isSelected()){
            NameDField.setEditable(true);
            birthDField.setEditable(true);
            editGenderComboBox.setEnabled(true);
            phoneDField.setEditable(true);
            editClassComboBox.setEnabled(true);
            pointDField.setEditable(true);
            point_RDScorllBar.setEnabled(true);
            saveBTN.setEnabled(true);
            xoaAnhBTN.setEnabled(true);
            chonAnhBTN.setEnabled(true);
        }
        else{
            NameDField.setEditable(false);
            birthDField.setEditable(false);
            editGenderComboBox.setEnabled(false);
            phoneDField.setEditable(false);
            editClassComboBox.setEnabled(false);
            pointDField.setEditable(false);
            point_RDLabel.setEnabled(false);
            saveBTN.setEnabled(false);
            xoaAnhBTN.setEnabled(false);
            chonAnhBTN.setEnabled(false);
        }
    }
//Xóa sinh viên
    public void deleteFunc(){
        stuCTL.deleteSt(studentM);
        dispose();
    }  
    public void updateStM(){
        studentM.setStName(NameDField.getText());
        studentM.setStGender((String) editGenderComboBox.getSelectedItem());
        studentM.setStGender(phoneDField.getText());
        studentM.setStClass((String) editClassComboBox.getSelectedItem());
        studentM.setStPoint(Float.parseFloat(pointDField.getText()));
        studentM.setStPonit_R(point_RDScorllBar.getValue());
    }
//Sửa thông tin
    public void saveFunc(){
        try {
            updateStM();
            stuCTL.updateStInfor(studentM);
            imagePRC.updateImage(studentM.getStID(), is);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Kiểm tra lại thông tin!", "Lỗi", 0);
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
    //Xóa ảnh
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
        delBTN = new javax.swing.JButton();
        messageDLable = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        editClassComboBox = new javax.swing.JComboBox<>();
        point_RDScorllBar = new javax.swing.JSlider();
        editDLabel = new javax.swing.JCheckBox();
        point_RDLabel = new javax.swing.JLabel();
        classDField = new javax.swing.JTextField();
        genderField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        anhLabel = new javax.swing.JLabel();
        chonAnhBTN = new javax.swing.JButton();
        xoaAnhBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        detailLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        detailLabel.setForeground(new java.awt.Color(0, 153, 153));
        detailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        detailLabel.setText("Thông tin chi tiết");

        NameDField.setEditable(false);

        birthDField.setEditable(false);

        phoneDField.setEditable(false);

        pointDField.setEditable(false);

        editGenderComboBox.setEditable(true);
        editGenderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        editGenderComboBox.setEnabled(false);

        idDField.setEditable(false);
        idDField.setEnabled(false);

        jLabel2.setText("ID");

        jLabel3.setText("Họ tên");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Giới tính");

        jLabel6.setText("SĐT");

        jLabel7.setText("Điểm");

        saveBTN.setForeground(new java.awt.Color(0, 204, 204));
        saveBTN.setText("Lưu");
        saveBTN.setEnabled(false);
        saveBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBTNActionPerformed(evt);
            }
        });

        delBTN.setForeground(new java.awt.Color(204, 0, 0));
        delBTN.setText("Xóa");
        delBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBTNActionPerformed(evt);
            }
        });

        messageDLable.setForeground(new java.awt.Color(204, 0, 0));
        messageDLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageDLable.setText("Message");

        jLabel9.setText("Hạng");

        jLabel10.setText("Lớp");

        editClassComboBox.setEditable(true);
        editClassComboBox.setEnabled(false);

        point_RDScorllBar.setValue(0);
        point_RDScorllBar.setEnabled(false);
        point_RDScorllBar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                point_RDScorllBarStateChanged(evt);
            }
        });

        editDLabel.setText("Edit");
        editDLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDLabelActionPerformed(evt);
            }
        });

        point_RDLabel.setText("0");

        classDField.setEditable(false);

        genderField.setEditable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        anhLabel.setBackground(new java.awt.Color(153, 255, 255));
        anhLabel.setForeground(new java.awt.Color(204, 255, 255));
        anhLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
        );

        chonAnhBTN.setText("Chọn ảnh");
        chonAnhBTN.setEnabled(false);
        chonAnhBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chonAnhBTNActionPerformed(evt);
            }
        });

        xoaAnhBTN.setText("Xóa");
        xoaAnhBTN.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneDField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(editGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(birthDField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(editClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(classDField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(NameDField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pointDField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(idDField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(point_RDScorllBar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(messageDLable)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(point_RDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xoaAnhBTN)
                                .addGap(18, 18, 18)
                                .addComponent(chonAnhBTN)
                                .addGap(18, 18, 18)))))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(detailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(82, 82, 82))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(detailLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(editDLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                            .addComponent(editGenderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editClassComboBox)
                            .addComponent(jLabel10)
                            .addComponent(classDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(pointDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xoaAnhBTN)
                            .addComponent(chonAnhBTN)
                            .addComponent(point_RDScorllBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(point_RDLabel)
                            .addComponent(jLabel9))
                        .addGap(22, 22, 22)))
                .addComponent(messageDLable)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBTN)
                    .addComponent(delBTN))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editDLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDLabelActionPerformed
        setEditOption();
    }//GEN-LAST:event_editDLabelActionPerformed

    private void point_RDScorllBarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_point_RDScorllBarStateChanged
        setChangePoint_R();
    }//GEN-LAST:event_point_RDScorllBarStateChanged

    private void delBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBTNActionPerformed
        deleteFunc();
    }//GEN-LAST:event_delBTNActionPerformed

    private void saveBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBTNActionPerformed
        saveFunc();
    }//GEN-LAST:event_saveBTNActionPerformed

    private void chonAnhBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chonAnhBTNActionPerformed
        chonAnhFunc();
    }//GEN-LAST:event_chonAnhBTNActionPerformed
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NameDField;
    private javax.swing.JLabel anhLabel;
    private javax.swing.JTextField birthDField;
    private javax.swing.JButton chonAnhBTN;
    private javax.swing.JTextField classDField;
    private javax.swing.JButton delBTN;
    private javax.swing.JLabel detailLabel;
    private javax.swing.JComboBox<String> editClassComboBox;
    private javax.swing.JCheckBox editDLabel;
    private javax.swing.JComboBox<String> editGenderComboBox;
    private javax.swing.JTextField genderField;
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
