/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.mycompany.bank.db.FinancierService;
import com.mycompany.bank.db.ClientService;
import com.mycompany.bank.db.BidService;
import com.mycompany.bank.db.ResponseFinancierService;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.ResponseFinancier;
import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.RestrBid;
import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.db.RestrBidService;
import com.mycompany.bank.db.SpecialOfferService;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 *
 * @author ann
 */
public class FinancierFrame extends JFrame {

    private ButtonGroup buttonGroup = new ButtonGroup();
    private SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Client client;
    private List<Bid> bids;
    int id;
    List<RestrBid> restrBids;
    JTable table1, table2;
    JScrollPane scrollPane1, scrollPane2;

    public FinancierFrame(int id) {
        super("Финансист ID: " + id);
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.id = id;
        Financier financier = FinancierService.findFinancierById(id);
        nameFinancier.setText("Здравствуйте, " + financier.getName());
        showTables();
        JMenuItem eMenuItem1 = new JMenuItem("Создать специальное предложение");
        eMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                new CreateSpecialOffFrame();
            }
        });
        jMenuBar1.add(eMenuItem1);
        acceptField.setActionCommand("true");
        refuseField.setActionCommand("false");
        buttonGroup.add(acceptField);
        buttonGroup.add(refuseField);
        int answer;
        acceptField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                percentField.setVisible(true);
                timeField.setVisible(true);
            }
        });
        refuseField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                percentField.setVisible(false);
                timeField.setVisible(false);
            }
        });

        answerField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Boolean answer = Boolean.parseBoolean(buttonGroup.getSelection().getActionCommand());
                    int percent;
                    int time;
                    if (answer) {
                        percent = Integer.parseInt(percentField.getText());
                        time = Integer.parseInt(timeField.getText());
                    } else {
                        percent = time = 0;
                    }
                    int tab = jTabbedPane1.getSelectedIndex();
                    int selectIndex;
                    if (tab == 0) {
                        selectIndex = table1.getSelectedRow();
                        ResponseFinancierService.setResponseFinancier(Bid.getBidForBidsFinancier(bids, selectIndex), id, answer, percent, time);
                    }
                    if (tab == 1) {
                        selectIndex = table2.getSelectedRow();
                        RestrBidService.setResponseFinancier(RestrBid.getBidForRestrBidsFinancier(restrBids, selectIndex), answer, percent, time);
                    }
                    JOptionPane.showMessageDialog(null, "Ответ отправлен.");
                } catch (Exception nfe) {
                    JOptionPane.showMessageDialog(null, "Процент и время - числовые значения.");
                }
            }
        });
    }

    public void showTables() {
        bids = BidService.getBidByFinancierId(id);
        restrBids = RestrBidService.getBidByFinancierId(id);
        String[] columnNames1 = {"Клиент", "Рейтинг", "Доход", "Сумма", "Дата"};
        String[] columnNames2 = {"Клиент", "Рейтинг", "Доход", "Причина", "Остаток суммы", "Процент (старое)", "Срок(старое)", "Дата"};
        String[][] data1 = Bid.showBidsFinancier(bids);
        String[][] data2 = RestrBid.showRestrBidsFinancier(restrBids);
        table1 = new JTable(data1, columnNames1);
        table2 = new JTable(data2, columnNames2);
        scrollPane1 = new JScrollPane(table1);
        scrollPane2 = new JScrollPane(table2);
        jTabbedPane1.removeAll();
        jTabbedPane1.addTab("Заявки на кредит ", scrollPane1);
        jTabbedPane1.addTab("Заявки на рестр. ", scrollPane2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameFinancier = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        refuseField = new javax.swing.JCheckBox();
        acceptField = new javax.swing.JCheckBox();
        percentField = new javax.swing.JTextField();
        timeField = new javax.swing.JTextField();
        answerField = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nameFinancier.setText("jLabel2");

        jLabel1.setText("Процент: ");

        jLabel2.setText("Срок (количество месяцев): ");

        refuseField.setText("Отказать");

        acceptField.setText("Одобрить");

        answerField.setText("Ответить");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(refuseField)
                            .addComponent(acceptField))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeField, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(percentField)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(nameFinancier)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameFinancier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(acceptField)
                    .addComponent(percentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(refuseField)
                    .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(answerField)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox acceptField;
    private javax.swing.JButton answerField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nameFinancier;
    private javax.swing.JTextField percentField;
    private javax.swing.JCheckBox refuseField;
    private javax.swing.JTextField timeField;
    // End of variables declaration//GEN-END:variables
}
