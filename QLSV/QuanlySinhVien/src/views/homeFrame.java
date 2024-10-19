package views;

import controllers.classController;
import controllers.studentController;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.User;
import util.ColorFrame;

public class homeFrame extends javax.swing.JFrame {
    
    ColorFrame colorFrame = new ColorFrame();
    static String message ="Welcome";
    studentController stCTL = new studentController();
    classController clCTL = new classController();
    User currentUser = new User();
    int rowSelectedIndex = 0;

   
    public homeFrame() {
        try {
            initComponents();
            setDetail();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối!", "", 0);
        }
    }
    public homeFrame(User user) {
        try {
            currentUser  = user;
            initComponents();
            setDetail();    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối!", "", 0);
        }
    }

    public void setDetail(){
        setTitle("Quản lý sinh viên");
        setBounds(150, 50, 1000, 400);
        clCTL.setModelCBB(classSelectBox);
        allClassCheckBox.setSelected(false);
        reloadBTN.setEnabled(true);
        searchBTN.setEnabled(false);
        loadTable(false);
        tbEditCheckBox.setEnabled(false);
        tbEditCheckBox.setSelected(false);
        updateBTN.setEnabled(false);
        homeTable.setEnabled(false);
        messageLabel.setText(message);
        messageLabel.setForeground(colorFrame.getPrimeryColor());
        userLabel.setText(currentUser.getUserName());
        idBTN.setText(String.valueOf(currentUser.getUserID()));
    }
 //Update
    public void UpdateInfor(){
        try {
            messageLabel.setText("");
            editBTN.setEnabled(false);
            updateBTN.setEnabled(false);
            setNumberOfSt();
             setMidScore();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi kết nối!", "", 0);
        }
    }
 
 //Load table
    public void loadTable(boolean all){
        DefaultTableModel model = (DefaultTableModel)homeTable.getModel();
        model.setRowCount(0);
        String itemSelected = (String) classSelectBox.getSelectedItem();
        int index = clCTL.getClassIDInfor(itemSelected);
        stCTL.loadDB(homeTable, index,all);
        UpdateInfor();
    }
//allOrOnew()
    public void allOrOnew(int type){
        classSelectBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        clCTL.setModelCBB(classSelectBox);
        if(allClassCheckBox.isSelected()){
            tbEditCheckBox.setEnabled(true);
            reloadBTN.setEnabled(false);
            searchBTN.setEnabled(true);
            if(type == 0){
                loadTable(true);
            }
        }
        else{
            tbEditCheckBox.setSelected(false);
            homeTable.setEnabled(false);
            tbEditCheckBox.setEnabled(false);
            reloadBTN.setEnabled(true);
            loadTable(false);
            searchBTN.setEnabled(false);
        }
    }
//SetTableEdit()
    public void setTableEdit(){
        if(tbEditCheckBox.isSelected()){
            homeTable.setEnabled(true);
            updateBTN.setEnabled(true);
        }else{
            homeTable.setEnabled(false);
            updateBTN.setEnabled(false);
            messageLabel.setText("");
        }
    }

//Tính tổng người
    public void setNumberOfSt(){
        int count = homeTable.getRowCount();
        numberLabel.setText("Tổng: " + count);
    }
//Tính điểm trung bình
    public void setMidScore(){
        int count = 0;
        float sum = 0;
        float min = 0;
        float max = 0;
        int indexMax = 0;
        int indexMin = 0;
        if(homeTable.getRowCount()>0){
            for(int i = 0; i< homeTable.getRowCount(); i++){
                count++;
                float score = Float.parseFloat((String) homeTable.getValueAt(i, 6));
                sum += score;
                if(score > max){
                    max = score;
                    indexMax = i;
                }
            }
            min = max;
            for(int i = 0; i< homeTable.getRowCount(); i++){
                float score = Float.parseFloat((String) homeTable.getValueAt(i, 6));
                if(score < min){
                    min = score;
                    indexMin = i;
                }
            }
            String nameOfChairMen = (String) homeTable.getValueAt(indexMax, 1);
            
            float av = 0;
            try {
                av = (float) ((double) Math.ceil(100*(sum/count)) / 100);
            } catch (Exception e) {
                av = 0;
            }
            
            MidScoreLabel.setText("ĐTB: " + av);
            bossLabel.setText(" " + nameOfChairMen);
            maxScoreLabel.setText("Max: " + max);
            minScoreLabel.setText("Min: " + min);
        }
    }

//Enable các nút bấm
    public void setEnabledBTN(){
        rowSelectedIndex = homeTable.getSelectedRow();
        if(rowSelectedIndex>=0){
            addBTN.setEnabled(true);
            editBTN.setEnabled(true);
        }
        else
            UpdateInfor();
    }
//Hiển thị khung detail
    public void getDetailInfor(){
        loadTable(true);
        
        int lastID = 0;
        
        for (int row = 0; row < homeTable.getRowCount(); row++) {
            int value = Integer.parseInt((String) homeTable.getValueAt(row, 0));
            if (value > lastID) lastID = value;
        }
        lastID++;
        
        addFrame addFrame = new addFrame();
        addFrame.studentM.setStID(lastID);
        addFrame.setVisible(true);
        addFrame.refresh();
        
    }
//Edit trực tiếp trên bảng
    public void editInfor(){
        int stIDSelected = Integer.parseInt((String) homeTable.getValueAt(rowSelectedIndex, 0));
        
        editFrame editFrame = new editFrame(stIDSelected);
        editFrame.setVisible(true);
    }

    public void uploadDBInfor(){
        stCTL.deleteTableData();
        for (int row = 0; row < homeTable.getRowCount(); row++) {
            String stID = (String) homeTable.getValueAt(row, 0);
            String stName = (String) homeTable.getValueAt(row, 1);
            String stBirth = (String) homeTable.getValueAt(row, 2);
            String stGender = stCTL.convertGenderToF((String) homeTable.getValueAt(row, 3));
            String stPhone = (String) homeTable.getValueAt(row, 4);
            int classID = clCTL.getClassIDInfor((String) homeTable.getValueAt(row, 5));
            String stPoint = (String) homeTable.getValueAt(row, 6);
            String stPoint_R = (String) homeTable.getValueAt(row, 7);

            stCTL.insertDB(stID,stName, stBirth, stGender, stPhone, classID, stPoint, stPoint_R);
        }
    }
//Search
    public void searchFunc(){
        if(keySearch.getText() != null){
            String key = keySearch.getText();
            if(key.equals("who?")){
                messageLabel.setText("This app was created by Trong Doan!");
                messageLabel.setForeground(Color.RED);
            }else{
                for (int row = 0; row < homeTable.getRowCount(); row++) {
                        String TbValue = (String) homeTable.getValueAt(row, 0);
                        if(TbValue.equals(key)){
                            int stIDSelected = Integer.parseInt((String) homeTable.getValueAt(row, 0));
                            editFrame editFrame = new editFrame(stIDSelected);
                            editFrame.setVisible(true);    
                        }
                }
            }            
        }
    }
    public void homeTableNote(int type){
        //Type = 0 tức là trỏ chuột ở trong bảng
        if(!allClassCheckBox.isSelected()){
            messageLabel.setForeground(Color.RED);
            if(type == 0){
                messageLabel.setText("Chỉ sửa bảng khi nút All được ấn !!!");
            }
            else{
                messageLabel.setText("");
            }
        }
    }
//Cài đặt
    public void settingFunc(){
        userProfileFrame uPF = new userProfileFrame(currentUser);
        uPF.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        idBTN = new javax.swing.JButton();
        addBTN = new javax.swing.JButton();
        editBTN = new javax.swing.JButton();
        updateBTN = new javax.swing.JButton();
        settingBTN = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        numberLabel = new javax.swing.JLabel();
        MidScoreLabel = new javax.swing.JLabel();
        searchBTN = new javax.swing.JButton();
        reloadBTN = new javax.swing.JButton();
        classSelectBox = new javax.swing.JComboBox<>();
        allClassCheckBox = new javax.swing.JCheckBox();
        tbEditCheckBox = new javax.swing.JCheckBox();
        keySearch = new javax.swing.JTextField();
        maxScoreLabel = new javax.swing.JLabel();
        minScoreLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        homeTable = new javax.swing.JTable();
        bossLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(2147483647, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        userLabel.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        userLabel.setForeground(new java.awt.Color(255, 0, 0));
        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userLabel.setText("Account");

        idBTN.setText("Image");
        idBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idBTNActionPerformed(evt);
            }
        });

        addBTN.setBackground(new java.awt.Color(0, 153, 153));
        addBTN.setForeground(new java.awt.Color(255, 255, 255));
        addBTN.setText("Thêm");
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });

        editBTN.setBackground(new java.awt.Color(0, 153, 153));
        editBTN.setForeground(new java.awt.Color(255, 255, 255));
        editBTN.setText("Sửa");
        editBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTNActionPerformed(evt);
            }
        });

        updateBTN.setBackground(new java.awt.Color(0, 153, 153));
        updateBTN.setForeground(new java.awt.Color(255, 255, 255));
        updateBTN.setText("Update DB");
        updateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBTNActionPerformed(evt);
            }
        });

        settingBTN.setBackground(new java.awt.Color(0, 153, 153));
        settingBTN.setForeground(new java.awt.Color(255, 255, 255));
        settingBTN.setText("Cài đặt");
        settingBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(settingBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(idBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(addBTN)
                .addGap(18, 18, 18)
                .addComponent(editBTN)
                .addGap(18, 18, 18)
                .addComponent(updateBTN)
                .addGap(18, 18, 18)
                .addComponent(settingBTN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        numberLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        numberLabel.setForeground(new java.awt.Color(0, 51, 51));
        numberLabel.setText("Count: 0");

        MidScoreLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MidScoreLabel.setForeground(new java.awt.Color(0, 51, 51));
        MidScoreLabel.setText("ĐTB: 0");

        searchBTN.setBackground(new java.awt.Color(0, 153, 153));
        searchBTN.setForeground(new java.awt.Color(255, 255, 255));
        searchBTN.setText("Search ID");
        searchBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBTNActionPerformed(evt);
            }
        });

        reloadBTN.setBackground(new java.awt.Color(0, 153, 153));
        reloadBTN.setForeground(new java.awt.Color(255, 255, 255));
        reloadBTN.setText("Reload DB");
        reloadBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadBTNActionPerformed(evt);
            }
        });

        allClassCheckBox.setForeground(new java.awt.Color(0, 51, 51));
        allClassCheckBox.setText("All");
        allClassCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allClassCheckBoxActionPerformed(evt);
            }
        });

        tbEditCheckBox.setText("Sửa bảng");
        tbEditCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbEditCheckBoxActionPerformed(evt);
            }
        });

        maxScoreLabel.setForeground(new java.awt.Color(0, 204, 204));
        maxScoreLabel.setText("Max: 0");

        minScoreLabel.setForeground(new java.awt.Color(255, 0, 0));
        minScoreLabel.setText("Min: 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classSelectBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reloadBTN)
                .addGap(17, 17, 17)
                .addComponent(allClassCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MidScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(maxScoreLabel)
                .addGap(39, 39, 39)
                .addComponent(minScoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(tbEditCheckBox)
                .addGap(28, 28, 28)
                .addComponent(keySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBTN)
                .addGap(11, 11, 11))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numberLabel)
                        .addComponent(MidScoreLabel)
                        .addComponent(searchBTN)
                        .addComponent(reloadBTN)
                        .addComponent(allClassCheckBox)
                        .addComponent(tbEditCheckBox)
                        .addComponent(keySearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maxScoreLabel)
                        .addComponent(minScoreLabel))
                    .addComponent(classSelectBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        homeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birth", "Gender", "Phone", "Class", "Score", "Rank"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        homeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        homeTable.setGridColor(new java.awt.Color(255, 255, 255));
        homeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeTableMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeTableMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(homeTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        bossLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bossLabel.setForeground(new java.awt.Color(0, 51, 51));
        bossLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        messageLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        messageLabel.setForeground(new java.awt.Color(255, 0, 0));
        messageLabel.setText("Message!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bossLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bossLabel)
                    .addComponent(messageLabel))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reloadBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadBTNActionPerformed
        loadTable(false);
    }//GEN-LAST:event_reloadBTNActionPerformed

    private void homeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTableMouseClicked
        setEnabledBTN();
    }//GEN-LAST:event_homeTableMouseClicked

    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTNActionPerformed
        getDetailInfor();
    }//GEN-LAST:event_addBTNActionPerformed

    private void editBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTNActionPerformed
        editInfor();
    }//GEN-LAST:event_editBTNActionPerformed

    private void updateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBTNActionPerformed
        uploadDBInfor();
    }//GEN-LAST:event_updateBTNActionPerformed

    private void allClassCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allClassCheckBoxActionPerformed
        allOrOnew(0);
    }//GEN-LAST:event_allClassCheckBoxActionPerformed

    private void tbEditCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbEditCheckBoxActionPerformed
        setTableEdit();
    }//GEN-LAST:event_tbEditCheckBoxActionPerformed

    private void searchBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBTNActionPerformed
        searchFunc();
    }//GEN-LAST:event_searchBTNActionPerformed

    private void homeTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTableMouseEntered
        homeTableNote(0);
    }//GEN-LAST:event_homeTableMouseEntered

    private void homeTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTableMouseExited
        homeTableNote(1);
    }//GEN-LAST:event_homeTableMouseExited

    private void settingBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBTNActionPerformed
        settingFunc();
    }//GEN-LAST:event_settingBTNActionPerformed

    private void idBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idBTNActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(homeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new homeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MidScoreLabel;
    private javax.swing.JButton addBTN;
    private javax.swing.JCheckBox allClassCheckBox;
    private javax.swing.JLabel bossLabel;
    private javax.swing.JComboBox<String> classSelectBox;
    private javax.swing.JButton editBTN;
    private javax.swing.JTable homeTable;
    private javax.swing.JButton idBTN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keySearch;
    private javax.swing.JLabel maxScoreLabel;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel minScoreLabel;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JButton reloadBTN;
    private javax.swing.JButton searchBTN;
    private javax.swing.JButton settingBTN;
    private javax.swing.JCheckBox tbEditCheckBox;
    private javax.swing.JButton updateBTN;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
