package views;

import controllers.userController;
import java.awt.Color;
import quanlysinhvien.data.ConnectDB;
import static quanlysinhvien.data.ConnectDB.stmt;
import java.sql.ResultSet;
import javax.swing.JLabel;
import util.ColorFrame;

public class signUpFrame extends javax.swing.JFrame {
    ColorFrame colorFrame = new ColorFrame();

    public signUpFrame() {
        initComponents();
        setDetail();
    }
    
    public void setDetail() {
        setTitle("Đăng kí");
        resetSatus();
        titleLabel.setForeground(colorFrame.getPrimeryColor());
    }
    
    public void resetSatus(){
        warnLabel.setText("");
        warnLabel.setForeground(Color.red);
    }
    public void createUser(){
        String name = nameField.getText();
        String pass = passField.getText();
        userController uc = new userController();
        if(name.equals("")){
            resetSatus();
            warnLabel.setText("Tài khoản trống");
        }
        else if(uc.checkUser(name)){
            resetSatus();
            warnLabel.setText("Tài khoản đã tồn tại!");
        }
        else if(pass.equals("")){
            resetSatus();
            warnLabel.setText("Mật khẩu trống!");
        }
        else{
            resetSatus();
            uc.insertUser(name, pass);
            warnLabel.setText("Thành công!");
            warnLabel.setForeground(Color.GREEN);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        passField = new javax.swing.JTextField();
        warnLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        signUpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(450, 150, 0, 0));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(51, 0, 153));
        titleLabel.setText("Đăng kí");

        warnLabel.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        warnLabel.setForeground(new java.awt.Color(255, 0, 0));
        warnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warnLabel.setText("Cảnh báo");
        warnLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Tài Khoản");

        jLabel5.setText("Mật Khẩu");

        signUpButton.setText("Đăng kí");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(warnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(titleLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(passField))))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signUpButton)
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(warnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signUpButton)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        // TODO add your handling code here:
        createUser();
    }//GEN-LAST:event_signUpButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField passField;
    private javax.swing.JButton signUpButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel warnLabel;
    // End of variables declaration//GEN-END:variables
}
