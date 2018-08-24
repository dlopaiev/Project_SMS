/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.EmailTLS;
import Helpers.ComponentResizer;
import Models.EmailAccount;
import Models.EmailContact;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author Denys
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private int pX, pY;
    
    public Main() {
        initComponents();
        formSetupAccounts = new SetupAccounts();
        formEmailsFromFile = new EmailsFromFile();
        formEmailsFromDB = new EmailsFromDB();
        this.setLocationRelativeTo(null);         
        //this.makeResizable();     
        addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    // Get x,y and store them
                    pX = me.getX();
                    pY = me.getY();

                }
                

                 public void mouseDragged(MouseEvent me) {

                    setLocation(getLocation().x + me.getX() - pX,
                            getLocation().y + me.getY() - pY);
                }
                
            });
        
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent me) {
                    
                    setLocation(getLocation().x + me.getX() - pX,
                            getLocation().y + me.getY() - pY);
                }
            });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        buttonEmailsFromDB = new javax.swing.JButton();
        buttonSetupAccounts = new javax.swing.JButton();
        buttonEmailsFromFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaEmail = new javax.swing.JTextArea();
        buttonClear = new javax.swing.JButton();
        buttonAttachFile = new javax.swing.JButton();
        buttonSend = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonPause = new javax.swing.JButton();
        buttonStop = new javax.swing.JButton();
        buttonExportToFile = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        buttonCloseApplication = new javax.swing.JButton();
        fieldSubject = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMS");
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1336, 917));
        setMinimumSize(new java.awt.Dimension(1336, 917));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1336, 917));
        setSize(new java.awt.Dimension(1336, 917));

        mainPanel.setBackground(new java.awt.Color(254, 255, 255));
        mainPanel.setMinimumSize(new java.awt.Dimension(780, 460));
        mainPanel.setName(""); // NOI18N

        sidePanel.setBackground(new java.awt.Color(18, 60, 105));

        buttonEmailsFromDB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonEmailsFromDB.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmailsFromDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/database_35px.png"))); // NOI18N
        buttonEmailsFromDB.setText("E-mails from DB");
        buttonEmailsFromDB.setBorder(null);
        buttonEmailsFromDB.setBorderPainted(false);
        buttonEmailsFromDB.setContentAreaFilled(false);
        buttonEmailsFromDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmailsFromDBActionPerformed(evt);
            }
        });

        buttonSetupAccounts.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonSetupAccounts.setForeground(new java.awt.Color(255, 255, 255));
        buttonSetupAccounts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/user_accounts_35px.png"))); // NOI18N
        buttonSetupAccounts.setText("Setup Accounts");
        buttonSetupAccounts.setBorder(null);
        buttonSetupAccounts.setBorderPainted(false);
        buttonSetupAccounts.setContentAreaFilled(false);
        buttonSetupAccounts.setName("buttonSetupAccounts"); // NOI18N
        buttonSetupAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetupAccountsActionPerformed(evt);
            }
        });

        buttonEmailsFromFile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        buttonEmailsFromFile.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmailsFromFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/file_35px.png"))); // NOI18N
        buttonEmailsFromFile.setText("E-mails from File");
        buttonEmailsFromFile.setBorder(null);
        buttonEmailsFromFile.setBorderPainted(false);
        buttonEmailsFromFile.setContentAreaFilled(false);
        buttonEmailsFromFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmailsFromFileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/brain_50px.png"))); // NOI18N
        jLabel1.setText("SMS");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setIconTextGap(10);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Smart Marketing System");

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSetupAccounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEmailsFromDB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEmailsFromFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGap(115, 115, 115))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(28, 28, 28))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addGap(214, 214, 214)
                .addComponent(buttonSetupAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEmailsFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEmailsFromDB, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(419, Short.MAX_VALUE))
        );

        buttonEmailsFromFile.getAccessibleContext().setAccessibleName("Emails from File");

        jCheckBox1.setBackground(mainPanel.getBackground());
        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jCheckBox1.setText("Emails from File");

        jCheckBox2.setBackground(mainPanel.getBackground());
        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jCheckBox2.setText("Emails from Database");

        textAreaEmail.setColumns(20);
        textAreaEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textAreaEmail.setRows(5);
        jScrollPane1.setViewportView(textAreaEmail);

        buttonClear.setBackground(new java.awt.Color(44, 53, 49));
        buttonClear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonClear.setForeground(new java.awt.Color(255, 255, 255));
        buttonClear.setText("Clear");
        buttonClear.setBorder(null);
        buttonClear.setBorderPainted(false);
        buttonClear.setFocusPainted(false);
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });

        buttonAttachFile.setBackground(new java.awt.Color(44, 53, 49));
        buttonAttachFile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAttachFile.setForeground(new java.awt.Color(255, 255, 255));
        buttonAttachFile.setText("Attach file");
        buttonAttachFile.setBorder(null);
        buttonAttachFile.setBorderPainted(false);

        buttonSend.setBackground(new java.awt.Color(44, 53, 49));
        buttonSend.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSend.setForeground(new java.awt.Color(255, 255, 255));
        buttonSend.setText("Send");
        buttonSend.setBorder(null);
        buttonSend.setBorderPainted(false);
        buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSendActionPerformed(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"mail@mail.com", "Sent", null, null}
            },
            new String [] {
                "Sent to", "Status", "Time", "Sent from"
            }
        ));
        jTable2.setGridColor(new java.awt.Color(255, 255, 255));
        jTable2.setOpaque(false);
        jTable2.setRowHeight(24);
        jTable2.setSelectionBackground(new java.awt.Color(18, 60, 105));
        jScrollPane3.setViewportView(jTable2);

        buttonPause.setBackground(new java.awt.Color(44, 53, 49));
        buttonPause.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonPause.setForeground(new java.awt.Color(255, 255, 255));
        buttonPause.setText("Pause");
        buttonPause.setBorder(null);
        buttonPause.setBorderPainted(false);

        buttonStop.setBackground(new java.awt.Color(44, 53, 49));
        buttonStop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonStop.setForeground(new java.awt.Color(255, 255, 255));
        buttonStop.setText("Stop");
        buttonStop.setBorder(null);
        buttonStop.setBorderPainted(false);

        buttonExportToFile.setBackground(new java.awt.Color(44, 53, 49));
        buttonExportToFile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonExportToFile.setForeground(new java.awt.Color(255, 255, 255));
        buttonExportToFile.setText("Export to file");
        buttonExportToFile.setBorder(null);
        buttonExportToFile.setBorderPainted(false);
        buttonExportToFile.setOpaque(false);

        topPanel.setBackground(new java.awt.Color(18, 60, 105));

        buttonCloseApplication.setBackground(topPanel.getBackground());
        buttonCloseApplication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Views/Icons/close_20px.png"))); // NOI18N
        buttonCloseApplication.setBorder(null);
        buttonCloseApplication.setBorderPainted(false);
        buttonCloseApplication.setContentAreaFilled(false);
        buttonCloseApplication.setFocusPainted(false);
        buttonCloseApplication.setPreferredSize(new java.awt.Dimension(25, 25));
        buttonCloseApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseApplicationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonCloseApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonCloseApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        fieldSubject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fieldSubject.setToolTipText("Enter email subject");

        jLabel2.setText("Email subject");

        jLabel3.setText("Email text");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox2))
                                .addComponent(jScrollPane1)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonAttachFile, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(buttonPause, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(buttonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                                .addComponent(buttonExportToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldSubject))
                            .addComponent(jLabel3))
                        .addContainerGap(288, Short.MAX_VALUE))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAttachFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPause, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonExportToFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSetupAccountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetupAccountsActionPerformed
        // TODO add your handling code here:
        formSetupAccounts.setVisible(true);        
    }//GEN-LAST:event_buttonSetupAccountsActionPerformed

    private void buttonCloseApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseApplicationActionPerformed
        // TODO add your handling code here:
        System.exit(0);
        
    }//GEN-LAST:event_buttonCloseApplicationActionPerformed

    private void buttonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSendActionPerformed
        // TODO add your handling code here:
        try {
            List<EmailAccount> accounts = formSetupAccounts.getAccounts();
            List<EmailContact> contacts = formEmailsFromFile.getContacts();
            
            System.out.println("Accounts used: " + accounts.size());
            System.out.println("Contacts to process: " + contacts.size());
            
            Runtime rt = Runtime.getRuntime();
            int cpus = rt.availableProcessors();
            System.out.println("Available processors " + cpus);
            ExecutorService es = Executors.newFixedThreadPool(cpus);
            
            for(EmailAccount acc : accounts) {
                EmailTLS emailTLS = new EmailTLS(acc.getAccEmail(), acc.getAccPassword(), acc.getAccSMTP());
                
                EmailSending eSending = new EmailSending(emailTLS, contacts, fieldSubject.getText(), textAreaEmail.getText());
                
                es.execute(eSending);
            }
        } catch(NullPointerException npe) {
            System.out.print("No accounts selected.");
        }
    }//GEN-LAST:event_buttonSendActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClearActionPerformed
        // TODO add your handling code here:
        fieldSubject.setText("");
        textAreaEmail.setText("");     
        
    }//GEN-LAST:event_buttonClearActionPerformed

    private void buttonEmailsFromFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmailsFromFileActionPerformed
        // TODO add your handling code here:
        formEmailsFromFile.setVisible(true);
        
    }//GEN-LAST:event_buttonEmailsFromFileActionPerformed

    private void buttonEmailsFromDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmailsFromDBActionPerformed
        // TODO add your handling code here:
        formEmailsFromDB.setVisible(true);
    }//GEN-LAST:event_buttonEmailsFromDBActionPerformed

    private void makeResizable() {
        ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(780, 460));
        cr.setMaximumSize(new Dimension(1336, 917));
        cr.registerComponent(this);
        cr.setSnapSize(new Dimension(4, 10));
    }
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAttachFile;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonCloseApplication;
    private javax.swing.JButton buttonEmailsFromDB;
    private javax.swing.JButton buttonEmailsFromFile;
    private javax.swing.JButton buttonExportToFile;
    private javax.swing.JButton buttonPause;
    private javax.swing.JButton buttonSend;
    private javax.swing.JButton buttonSetupAccounts;
    private javax.swing.JButton buttonStop;
    private javax.swing.JTextField fieldSubject;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JTextArea textAreaEmail;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
    private SetupAccounts formSetupAccounts;   
    private EmailsFromFile formEmailsFromFile;
    private EmailsFromDB formEmailsFromDB;
    
    class EmailSending implements Runnable {
        
        private EmailTLS eTLS;
        private List<EmailContact> eContact;
        private String eSubject;
        private String eMessage;
        
        EmailSending(EmailTLS emailTLS, List<EmailContact> contactList, String subject, String message){
            this.eTLS = emailTLS;
            this.eContact = contactList;
            this.eSubject = subject;
            this.eMessage = message;
        }
        @Override
        public void run() {
            sendEmail();
        }   
        
        private void sendEmail() {
            eTLS.initiateSession();
            for(EmailContact emailContact : eContact) {
                eTLS.sendEmailTLS(emailContact.getEmail(), eSubject, eMessage);
            }
            
        }
    }
}
