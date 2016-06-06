/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.gui;


//import com.mycompany.bank.db.BidService;
import com.mycompany.bank.fasade.ClientFasade;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 *
 * @author ann
 */
public class ClientFrame extends javax.swing.JFrame {

    private int id;
    ClientFasade clientFasade;
    JMenuBar menubar = new JMenuBar();
    JTable table1, table2, table3, table4, table5, table6, table7;
    JScrollPane scrollPane1, scrollPane2, scrollPane3, scrollPane4, scrollPane5, scrollPane6, scrollPane7;
    CreateBidFrame createBidFrame;

    /**
     * Creates new form ClientFrame
     */
    public ClientFrame(int id) {
        super("Окно клиента ID: " + id);
        this.id = id;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        clientFasade = new ClientFasade(id);
        nameField.setText("Здравствуйте, " + clientFasade.getName());

        CreateMenu();
        ShowBid();
        yesField.setActionCommand("true");
        noField.setActionCommand("false");
        buttonGroup.add(noField);
        buttonGroup.add(yesField);
        int answer;
        answerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int tab = jTabbedPane1.getSelectedIndex();
                int selectIndex;
                try {
                    if (tab == 0) {
                        Boolean answer = Boolean.parseBoolean(buttonGroup.getSelection().getActionCommand());
                        selectIndex = table1.getSelectedRow();
                        clientFasade.setResponseClientForOffer(selectIndex, answer);
                    } else if (tab == 2) {
                        Boolean answer = Boolean.parseBoolean(buttonGroup.getSelection().getActionCommand());
                        selectIndex = table3.getSelectedRow();
                        clientFasade.setResponseClientForBid(selectIndex, answer);
                    } else if (tab == 3) {
                        selectIndex = table4.getSelectedRow();
                        String reason = getReasonField.getText();
                        clientFasade.createNewBid(selectIndex, reason);
                    } else if (tab == 6) {
                        Boolean answer = Boolean.parseBoolean(buttonGroup.getSelection().getActionCommand());
                        selectIndex = table7.getSelectedRow();
                       clientFasade.setResponseClientRestrBid( selectIndex, answer);
                    }
                    JOptionPane.showMessageDialog(null, "Ответ отправлен.");
                } catch (Exception nfe) {
                    JOptionPane.showMessageDialog(null, "Выберите параметры.");
                }
            }
        });
    }

    public void CreateMenu() {
        JMenuItem eMenuItem1 = new JMenuItem("Новая заявка");
        JMenuItem eMenuItem2 = new JMenuItem("Обновить заявки");
        eMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (createBidFrame == null) {
                    new CreateBidFrame(id).setVisible(true);
                }
            }
        });
        eMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ShowBid();
            }
        });
        jMenuBar1.add(eMenuItem1);
        jMenuBar1.add(eMenuItem2);
    }

    public void ShowBid() {
        setLocationRelativeTo(null);
        String[] columnNames1 = {"Сумма", "Процент", "Срок (мес)", "Ваш ответ"};
        String[] columnNames2 = {"Сумма", "Дата"};
        String[] columnNames3 = {"Сумма", "Дата", "Ответ финансиста", "Срок (мес)", "Процент"};
        String[] columnNames4 = {"Общая сумма", "Дата", "Срок (мес)", "Процент", "Остаток суммы"};
        String[] columnNames5 = {"Общая сумма", "Срок (мес)", "Процент", "Остаток суммы"};
        String[] columnNames6 = {"Остаток суммы", "Процент (старое)", "Срок(старое)", "Дата"};
        String[] columnNames7 = {"Остаток суммы", "Процент (старое)", "Срок(старое)", "Дата", "Ответ финансиста", "Процент (новое)", "Срок (новое)"};
        String[][] data1 = clientFasade.showTable();
        String[][] data2 = clientFasade.showBids();
        String[][] data3 = clientFasade.showApprovedBids();
        String[][] data4 =clientFasade.showAgreementsBid();
        String[][] data5 = clientFasade.showAgreementsOffer();
        String[][] data6 = clientFasade.showRestrBids();
        String[][] data7 = clientFasade.showApprovedRestrBids();
        table1 = new JTable(data1, columnNames1);
        table2 = new JTable(data2, columnNames2);
        table3 = new JTable(data3, columnNames3);
        table4 = new JTable(data4, columnNames4);
        table5 = new JTable(data5, columnNames5);
        table6 = new JTable(data6, columnNames6);
        table7 = new JTable(data7, columnNames7);
        scrollPane1 = new JScrollPane(table1);
        scrollPane2 = new JScrollPane(table2);
        scrollPane3 = new JScrollPane(table3);
        scrollPane4 = new JScrollPane(table4);
        scrollPane5 = new JScrollPane(table5);
        scrollPane6 = new JScrollPane(table6);
        scrollPane7 = new JScrollPane(table7);
        jTabbedPane1.removeAll();
        jTabbedPane1.addTab("Спец. предложения ", scrollPane1);
        jTabbedPane1.addTab("Поданные заявки ", scrollPane2);
        jTabbedPane1.addTab("Одобренные заявки ", scrollPane3);
        jTabbedPane1.addTab("Договоры ", scrollPane4);
        jTabbedPane1.addTab("Договоры по спец. предлож.", scrollPane5);
        jTabbedPane1.addTab("Заявки на рестр.", scrollPane6);
        jTabbedPane1.addTab("Одобренные заявки на рестр. ", scrollPane7);
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane1.getSelectedIndex() == 1 || jTabbedPane1.getSelectedIndex() == 5) {
                    noField.setVisible(false);
                    yesField.setVisible(false);
                    answerButton.setVisible(false);
                    jPanel2.setVisible(false);
                } else if (jTabbedPane1.getSelectedIndex() == 3) {
                    noField.setVisible(false);
                    yesField.setVisible(false);
                    answerButton.setVisible(true);
                    jPanel2.setVisible(true);
                    answerButton.setText("Заявка на реструктуризацию");
                } else {
                    noField.setVisible(true);
                    yesField.setVisible(true);
                    answerButton.setVisible(true);
                    jPanel2.setVisible(false);
                    answerButton.setText("Ответить");
                }

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

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        yesField = new javax.swing.JRadioButton();
        noField = new javax.swing.JRadioButton();
        answerButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        reasonField = new javax.swing.JLabel();
        getReasonField = new javax.swing.JTextField();
        nameField = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        yesField.setText("Принять");
        yesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesFieldActionPerformed(evt);
            }
        });

        noField.setText("Отказать");

        answerButton.setText("Ответить");

        reasonField.setText("Причина: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reasonField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getReasonField, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getReasonField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reasonField))
                .addContainerGap())
        );

        nameField.setText("jLabel1");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(answerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(yesField)
                        .addGap(30, 30, 30)
                        .addComponent(noField)))
                .addGap(0, 712, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameField)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameField)
                .addGap(11, 11, 11)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerButton)
                    .addComponent(yesField)
                    .addComponent(noField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yesFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton answerButton;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JTextField getReasonField;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nameField;
    private javax.swing.JRadioButton noField;
    private javax.swing.JLabel reasonField;
    private javax.swing.JRadioButton yesField;
    // End of variables declaration//GEN-END:variables
}
