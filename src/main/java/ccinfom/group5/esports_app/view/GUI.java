/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ccinfom.group5.esports_app.view;

/**
 *
 * @author Sean Kyle
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainMainPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        bannerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lowerPanel = new javax.swing.JPanel();
        lowerLeftPanel = new javax.swing.JPanel();
        menuLbl = new javax.swing.JLabel();
        mainViewBtn = new javax.swing.JButton();
        makeTransacBtn = new javax.swing.JButton();
        genReportsBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lowerRightPanel = new javax.swing.JPanel();
        mainViewPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainViewTable = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        queryTxtField = new javax.swing.JTextField();
        mainViewMainMenuBtn = new javax.swing.JButton();
        mainViewMakeTransacBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ESports Tracker - Valorant");
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        mainMainPanel.setLayout(new java.awt.CardLayout());

        mainPanel.setBackground(new java.awt.Color(255, 204, 51));
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        bannerPanel.setBackground(new java.awt.Color(51, 102, 0));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ESports Tracker - Valorant");
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        javax.swing.GroupLayout bannerPanelLayout = new javax.swing.GroupLayout(bannerPanel);
        bannerPanel.setLayout(bannerPanelLayout);
        bannerPanelLayout.setHorizontalGroup(
            bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                .addContainerGap())
        );
        bannerPanelLayout.setVerticalGroup(
            bannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        mainPanel.add(bannerPanel, gridBagConstraints);

        lowerPanel.setMinimumSize(new java.awt.Dimension(400, 600));
        lowerPanel.setLayout(new java.awt.GridBagLayout());

        lowerLeftPanel.setBackground(new java.awt.Color(204, 204, 204));
        lowerLeftPanel.setLayout(new java.awt.GridBagLayout());

        menuLbl.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        menuLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuLbl.setText("MENU");
        menuLbl.setPreferredSize(new java.awt.Dimension(70, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        lowerLeftPanel.add(menuLbl, gridBagConstraints);

        mainViewBtn.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        mainViewBtn.setText("Main View");
        mainViewBtn.setPreferredSize(new java.awt.Dimension(135, 60));
        mainViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainViewBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 7, 10);
        lowerLeftPanel.add(mainViewBtn, gridBagConstraints);

        makeTransacBtn.setFont(new java.awt.Font("sansserif", 0, 15)); // NOI18N
        makeTransacBtn.setText("Make Transactions");
        makeTransacBtn.setPreferredSize(mainViewBtn.getPreferredSize());
        makeTransacBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeTransacBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 7, 10);
        lowerLeftPanel.add(makeTransacBtn, gridBagConstraints);

        genReportsBtn.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        genReportsBtn.setText("Generate Reports");
        genReportsBtn.setPreferredSize(mainViewBtn.getPreferredSize());
        genReportsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genReportsBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 21;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 7, 10);
        lowerLeftPanel.add(genReportsBtn, gridBagConstraints);

        exitBtn.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        exitBtn.setText("Exit");
        exitBtn.setPreferredSize(new java.awt.Dimension(72, 40));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(75, 10, 75, 10);
        lowerLeftPanel.add(exitBtn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        lowerPanel.add(lowerLeftPanel, gridBagConstraints);

        lowerRightPanel.setBackground(new java.awt.Color(0, 255, 204));

        javax.swing.GroupLayout lowerRightPanelLayout = new javax.swing.GroupLayout(lowerRightPanel);
        lowerRightPanel.setLayout(lowerRightPanelLayout);
        lowerRightPanelLayout.setHorizontalGroup(
            lowerRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lowerRightPanelLayout.setVerticalGroup(
            lowerRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        lowerPanel.add(lowerRightPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.9;
        mainPanel.add(lowerPanel, gridBagConstraints);

        mainMainPanel.add(mainPanel, "card2");
        mainPanel.getAccessibleContext().setAccessibleParent(mainMainPanel);

        mainViewPanel.setPreferredSize(getPreferredSize());
        mainViewPanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 380));

        mainViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        mainViewTable.setMaximumSize(new java.awt.Dimension(225, 100));
        mainViewTable.setPreferredSize(new java.awt.Dimension(400, 80));
        mainViewTable.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(mainViewTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        mainViewPanel.add(jScrollPane1, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(200, 35));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 165, 5, 165);
        mainViewPanel.add(jComboBox1, gridBagConstraints);

        queryTxtField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        queryTxtField.setMinimumSize(new java.awt.Dimension(64, 28));
        queryTxtField.setPreferredSize(new java.awt.Dimension(500, 100));
        queryTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryTxtFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(15, 5, 15, 5);
        mainViewPanel.add(queryTxtField, gridBagConstraints);

        mainViewMainMenuBtn.setText("Main Menu");
        mainViewMainMenuBtn.setPreferredSize(new java.awt.Dimension(89, 30));
        mainViewMainMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainViewMainMenuBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        mainViewPanel.add(mainViewMainMenuBtn, gridBagConstraints);

        mainViewMakeTransacBtn.setText("<html><div style='text-align: center;'>Make<br>Transactions</div></html>");
        mainViewMakeTransacBtn.setToolTipText("");
        mainViewMakeTransacBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainViewMakeTransacBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        mainViewPanel.add(mainViewMakeTransacBtn, gridBagConstraints);

        mainMainPanel.add(mainViewPanel, "card3");

        getContentPane().add(mainMainPanel);
        mainMainPanel.setBounds(0, 0, 1280, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainViewBtnActionPerformed
        java.awt.CardLayout cardLayout = (java.awt.CardLayout) mainMainPanel.getLayout();
        cardLayout.show(mainMainPanel, "card3");
    }//GEN-LAST:event_mainViewBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitBtnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void genReportsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genReportsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genReportsBtnActionPerformed

    private void makeTransacBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeTransacBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_makeTransacBtnActionPerformed

    private void queryTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_queryTxtFieldActionPerformed

    private void mainViewMainMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainViewMainMenuBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainViewMainMenuBtnActionPerformed

    private void mainViewMakeTransacBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainViewMakeTransacBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainViewMakeTransacBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bannerPanel;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton genReportsBtn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lowerLeftPanel;
    private javax.swing.JPanel lowerPanel;
    private javax.swing.JPanel lowerRightPanel;
    private javax.swing.JPanel mainMainPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton mainViewBtn;
    private javax.swing.JButton mainViewMainMenuBtn;
    private javax.swing.JButton mainViewMakeTransacBtn;
    private javax.swing.JPanel mainViewPanel;
    private javax.swing.JTable mainViewTable;
    private javax.swing.JButton makeTransacBtn;
    private javax.swing.JLabel menuLbl;
    private javax.swing.JTextField queryTxtField;
    // End of variables declaration//GEN-END:variables
}
