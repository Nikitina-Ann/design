/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.gui;

import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.db.ClientService;
import com.mycompany.bank.db.FinancierService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.bank.fasade.BidFasade;
import com.mycompany.bank.fasade.ManagerFasade;
import com.mycompany.bank.fasade.RestrBidFasade;
import com.mycompany.bank.fasade.SpecialOfferFasade;
import javax.swing.*;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ann
 */
public class ManagerFrame extends JFrame {

    int id;
    List<Client> clients;
    List<Financier> financiers;
    ManagerFasade managerFasade;
    BidFasade bidFasade;
    RestrBidFasade restrBidFasade;
    SpecialOfferFasade specialOfferFasade;
    JTable table1, table2, table3, table4, table5, table6, table7, table8;
    JScrollPane scrollPane1, scrollPane2, scrollPane3, scrollPane4, scrollPane5, scrollPane6, scrollPane7, scrollPane8;

    public ManagerFrame(int id) {
        super("Менеджер ID: " + id);
        initComponents();
        setLocationRelativeTo(null);
        this.id = id;
        managerFasade = new ManagerFasade(id);
        bidFasade = new BidFasade (2, id);
        restrBidFasade = new RestrBidFasade (2, id);
        specialOfferFasade = new SpecialOfferFasade ();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameField.setText("Здравствуйте, " + managerFasade.getName());
        showTables();
        jMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                showTables();
            }
        });
        roleField.removeAllItems();
        clients = ClientService.getAllClients();
        for (Client client : clients) {
            roleField.addItem(client.getName());
        }
        checkField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int tab = jTabbedPane1.getSelectedIndex();
                int selectIndex;
                try {
                    if (tab == 0) {
                        selectIndex = table1.getSelectedRow();
                        String clientName = roleField.getSelectedItem().toString();
                        if (!specialOfferFasade.setClientId(selectIndex, clientName)) {
                            JOptionPane.showMessageDialog(null, "Это специальное предложение уже было отправлено клиенту.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cпециальное предложение уcпешно отправлено.");
                        }
                    } else if (tab == 1) {
                        selectIndex = table2.getSelectedRow();
                        specialOfferFasade.setAgreement( selectIndex);
                    } else if (tab == 2) {
                        selectIndex = table3.getSelectedRow();
                        String financierName = roleField.getSelectedItem().toString();
                        bidFasade.setFinancierId( selectIndex, financierName);
                    } else if (tab == 3) {
                        selectIndex = table4.getSelectedRow();
                        bidFasade.setAgreement(selectIndex);
                    } else if (tab == 7) {
                        selectIndex = table8.getSelectedRow();
                        restrBidFasade.setAgreement( selectIndex);
                    } else if (tab == 6) {
                        selectIndex = table7.getSelectedRow();
                        String financierName = roleField.getSelectedItem().toString();
                        restrBidFasade.setFinancierId( selectIndex, financierName);
                    }
                } catch (Exception nfe) {
                    JOptionPane.showMessageDialog(null, "Выберите параметры.");
                }
            }
        });
    }

    public void showTables() {
        String[] columnNames1 = {"Сумма", "Процент", "Срок (мес)"};
        String[] columnNames2 = {"Сумма", "Процент", "Срок (мес)", "Клиент"};
        String[] columnNames3 = {"Сумма", "Дата", "Клиент"};
        String[] columnNames4 = {"Сумма", "Дата", "Ответ финансиста", "Срок (мес)", "Процент", "Клиент", "Ответ клиента"};
        String[] columnNames5 = {"Общая сумма", "Дата", "Срок (мес)", "Процент", "Остаток суммы", "Клиент"};
        String[] columnNames6 = {"Общая сумма", "Срок (мес)", "Процент", "Остаток суммы", "Клиент"};
        String[] columnNames7 = {"Остаток суммы", "Процент (старое)", "Срок(старое)", "Дата", "Клиент"};
        String[] columnNames8 = {"Остаток суммы", "Процент (старое)", "Срок(старое)", "Дата", "Ответ финансиста", "Процент (новое)", "Срок (новое)", "Клиент"};
        String[][] data1 = specialOfferFasade.showTable();
        String[][] data2 = specialOfferFasade.showApprovedTable();
        String[][] data3 = bidFasade.showBids();
        String[][] data4 = bidFasade.showApprovedBids();
        String[][] data5 = bidFasade.showAgreements(true);
        String[][] data6 = specialOfferFasade.showAgreements(true);
        String[][] data7 = restrBidFasade.showRestrBids();
        String[][] data8 = restrBidFasade.showApprovedRestrBids();
        table1 = new JTable(data1, columnNames1);
        table2 = new JTable(data2, columnNames2);
        table3 = new JTable(data3, columnNames3);
        table4 = new JTable(data4, columnNames4);
        table5 = new JTable(data5, columnNames5);
        table6 = new JTable(data6, columnNames6);
        table7 = new JTable(data7, columnNames7);
        table8 = new JTable(data8, columnNames8);
        scrollPane1 = new JScrollPane(table1);
        scrollPane2 = new JScrollPane(table2);
        scrollPane3 = new JScrollPane(table3);
        scrollPane4 = new JScrollPane(table4);
        scrollPane5 = new JScrollPane(table5);
        scrollPane6 = new JScrollPane(table6);
        scrollPane7 = new JScrollPane(table7);
        scrollPane8 = new JScrollPane(table8);
        jTabbedPane1.removeAll();
        jTabbedPane1.addTab("Спец. предложения ", scrollPane1);
        jTabbedPane1.addTab("Готовые спец. предложения ", scrollPane2);
        jTabbedPane1.addTab("Заявки ", scrollPane3);
        jTabbedPane1.addTab("Готовые заявки ", scrollPane4);
        jTabbedPane1.addTab("Договоры ", scrollPane5);
        jTabbedPane1.addTab("Договоры по спец. предл.", scrollPane6);
        jTabbedPane1.addTab("Заявки на рестр. ", scrollPane7);
        jTabbedPane1.addTab("Готовые заявки на рестр. ", scrollPane8);
        jTabbedPane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (jTabbedPane1.getSelectedIndex() == 0) {
                    roleField.setVisible(true);
                    titleRoleField.setVisible(true);
                    checkField.setVisible(true);
                    checkField.setText("Отправить");
                    titleRoleField.setText("Клиенты: ");
                    roleField.removeAllItems();
                    clients = ClientService.getAllClients();
                    for (Client client : clients) {
                        roleField.addItem(client.getName());
                    }
                } else if (jTabbedPane1.getSelectedIndex() == 2 || jTabbedPane1.getSelectedIndex() == 6) {
                    roleField.setVisible(true);
                    titleRoleField.setVisible(true);
                    checkField.setVisible(true);
                    checkField.setText("Отправить");
                    titleRoleField.setText("Финансисты: ");
                    roleField.removeAllItems();
                    financiers = FinancierService.getAllFinancier();
                    for (Financier financier : financiers) {
                        roleField.addItem(financier.getName());
                    }
                } else if (jTabbedPane1.getSelectedIndex() == 1 || jTabbedPane1.getSelectedIndex() == 3 || jTabbedPane1.getSelectedIndex() == 7) {
                    roleField.setVisible(false);
                    titleRoleField.setVisible(false);
                    checkField.setText("Оформить договор");
                    checkField.setVisible(true);
                } else {
                    roleField.setVisible(false);
                    titleRoleField.setVisible(false);
                    checkField.setVisible(false);
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

        nameField = new javax.swing.JLabel();
        roleField = new javax.swing.JComboBox<>();
        checkField = new javax.swing.JButton();
        titleRoleField = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameField.setText("jLabel1");

        roleField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleFieldActionPerformed(evt);
            }
        });

        checkField.setText("Отправить");

        titleRoleField.setText("Клиенты: ");

        jMenu1.setText("Обновить данные");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 998, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roleField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titleRoleField))
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleRoleField)
                    .addComponent(checkField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkField;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nameField;
    private javax.swing.JComboBox<String> roleField;
    private javax.swing.JLabel titleRoleField;
    // End of variables declaration//GEN-END:variables
}
