package views;

import controllers.userController;
import java.awt.Color;
import models.User;
import util.ColorFrame;

public class loginFrame extends javax.swing.JFrame {
    ColorFrame colorFrame = new ColorFrame();
    int statue = 0;
    userController userC1 = new userController();
    User user = new User();
    
    public loginFrame() {
        initComponents();
        setDetail();
    }
    public void setDetail() {
        
        setTitle("Login");
        resetStatus();
        titleLabel.setForeground(colorFrame.getPrimeryColor());
    }
    
    public void resetStatus(){
        nameWarningLabel.setText("");
        passWarningLabel.setText("");
        statue = 0;
    }
    
//Ẩn hiện pass
    public void hideOrShow(){
        if(hideSelection.isSelected()){
            hideSelection.setText("Hiện");
            passWordField1.setEchoChar((char)0);
        }
        else{
            hideSelection.setText("Ẩn");
            passWordField1.setEchoChar('*');
        }
    }

//Check tài khoản và mật khẩu - Hàm này được gọi khi click đăng nhập
    public void checkLogin() {
        String name = userNameField.getText();
        String pass = passWordField1.getText();
        userC1 = new userController();
// Kiểm tra tài khoản mật khẩu
        if (name.equals("")) {
            resetStatus();
            nameWarningLabel.setText("* Hãy nhập tài khoản!");
        } 
        else if (pass.equals("")) {
            resetStatus();
            passWarningLabel.setText("* Hãy nhập mật khẩu!");
        } 
//Truy vấn CSDL
        else {
            userC1.getUserInfor(name);
            if (userC1.getUser1() != null) {
                if (pass.equals(userC1.getUser1().getPassWord())) {
                    resetStatus();
                    passWordField1.setText("");
                    //Đăng nhập thành công
                    user = userC1.getUser(name);
                    homeFrame homeFrame1 = new homeFrame(user);
                    homeFrame1.setVisible(true);
                    dispose();
                    
                    System.out.println(name + " thành công!");
                } else {
                    resetStatus();
                    passWarningLabel.setText("* Sai mật khẩu!");
                    statue = 1;
                }
            } else {
                resetStatus();
                nameWarningLabel.setText("* Không tồn tại!");
            }
        }
    }
//Xử lý khi quên mật khẩu
    public void changePass(){
        if(statue == 1){
            String newPass = "12345678";
            userC1.updatePass(userC1.getUser1().getUserName(), newPass);
            passWordField1.setText(newPass);
            passWarningLabel.setText("Mật khẩu mới là " + newPass);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userNameField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        forgetLabel = new javax.swing.JLabel();
        loginBTN = new javax.swing.JButton();
        sigUpLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        nameWarningLabel = new javax.swing.JLabel();
        passWarningLabel = new javax.swing.JLabel();
        passWordField1 = new javax.swing.JPasswordField();
        hideSelection = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 204, 255));
        setBounds(new java.awt.Rectangle(400, 100, 0, 0));

        nameLabel.setBackground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Tài Khoản");

        passLabel.setBackground(new java.awt.Color(255, 255, 255));
        passLabel.setText("Mật Khẩu");

        forgetLabel.setBackground(new java.awt.Color(255, 255, 255));
        forgetLabel.setForeground(new java.awt.Color(0, 0, 153));
        forgetLabel.setText("Quên mật khẩu?");
        forgetLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        forgetLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgetLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgetLabelMouseExited(evt);
            }
        });

        loginBTN.setText("Đăng nhập");
        loginBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBTNActionPerformed(evt);
            }
        });

        sigUpLabel.setBackground(new java.awt.Color(255, 255, 255));
        sigUpLabel.setForeground(new java.awt.Color(0, 0, 153));
        sigUpLabel.setText("Đăng ký");
        sigUpLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        sigUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sigUpLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sigUpLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sigUpLabelMouseExited(evt);
            }
        });

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(0, 153, 153));
        titleLabel.setText("Quản lý sinh viên");

        nameWarningLabel.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        nameWarningLabel.setForeground(new java.awt.Color(204, 0, 0));
        nameWarningLabel.setText("Lỗi");

        passWarningLabel.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        passWarningLabel.setForeground(new java.awt.Color(204, 0, 0));
        passWarningLabel.setText("Lỗi");

        passWordField1.setEchoChar('*');

        hideSelection.setText("Ẩn");
        hideSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideSelectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(passLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(forgetLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sigUpLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNameField)
                            .addComponent(passWordField1)
                            .addComponent(nameWarningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passWarningLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hideSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(156, 156, 156))
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(nameWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(passWordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hideSelection))
                .addGap(3, 3, 3)
                .addComponent(passWarningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(forgetLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sigUpLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }
    private void loginBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBTNActionPerformed

        checkLogin();
    }
    private void forgetLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetLabelMouseClicked

        changePass();
    }
    private void forgetLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetLabelMouseEntered

        forgetLabel.setForeground(Color.GRAY);
    }
    private void forgetLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetLabelMouseExited

        forgetLabel.setForeground(Color.BLUE);
    }
    private void sigUpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sigUpLabelMouseClicked

         new signUpFrame().setVisible(true);
    }
    private void sigUpLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sigUpLabelMouseEntered

        sigUpLabel.setForeground(Color.GRAY);
    }
    private void sigUpLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sigUpLabelMouseExited

        sigUpLabel.setForeground(Color.BLUE);
    }
    private void hideSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideSelectionActionPerformed

        hideOrShow();
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginFrame().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel forgetLabel;
    private javax.swing.JCheckBox hideSelection;
    private javax.swing.JButton loginBTN;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameWarningLabel;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel passWarningLabel;
    private javax.swing.JPasswordField passWordField1;
    private javax.swing.JLabel sigUpLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField userNameField;
}
