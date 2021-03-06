/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.gui;

import javax.swing.*;
import java.awt.event.*;

import com.mycompany.bank.db.ClientService;
import com.mycompany.bank.db.ManagerService;
import com.mycompany.bank.db.FinancierService;

/**
 *
 * @author ann
 */
public class RegistrationFrame extends javax.swing.JFrame {

    public RegistrationFrame() {
        super("Регистрация");
        initComponents();
        revenue.setVisible(false);
        revenueField.setVisible(false);
        setLocationRelativeTo(null);
        client.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revenue.setVisible(true);
                revenueField.setVisible(true);
            }
        });

        manager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revenue.setVisible(false);
                revenueField.setVisible(false);
            }
        });
        financier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                revenue.setVisible(false);
                revenueField.setVisible(false);
            }
        });
        buttonGroup.add(financier);
        buttonGroup.add(client);
        buttonGroup.add(manager);

        registration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id;
                    String name = nameField.getText();
                    if (client.isSelected()) {
                        int revenue = Integer.parseInt(revenueField.getText());
                        id = ClientService.createNewClient(name, revenue);
                        JOptionPane.showMessageDialog(null, "Вы зарегистрироваласи как клиент. Ваш ID " + id + ". Запомните его!");
                    } else if (manager.isSelected()) {
                        id = ManagerService.createNewManager(name);
                        JOptionPane.showMessageDialog(null, "Вы зарегистрироваласи как менеджер. Ваш ID " + id + ". Запомните его!");
                    } else if (financier.isSelected()) {
                        id = FinancierService.createNewFinancier(name);
                        JOptionPane.showMessageDialog(null, "Вы зарегистрироваласи как финансист. Ваш ID " + id + ". Запомните его!");
                    }
                    dispose();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Доход - числовое значение");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        client = new javax.swing.JCheckBox();
        manager = new javax.swing.JCheckBox();
        nameField = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        revenue = new javax.swing.JLabel();
        revenueField = new javax.swing.JTextField();
        financier = new javax.swing.JCheckBox();
        registration = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        client.setText("Клиент");

        manager.setText("Менеджер");

        name.setText("Имя");

        revenue.setText("Доход клиента (руб / месяц)");

        financier.setText("Финансист");

        registration.setText("Зарегистрироваться");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(client)
                                    .addComponent(financier))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(revenue)
                                    .addComponent(name)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(revenueField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(manager)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(registration)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(name)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(client))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(manager)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(revenue)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(revenueField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financier))
                .addGap(35, 35, 35)
                .addComponent(registration)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JCheckBox client;
    private javax.swing.JCheckBox financier;
    private javax.swing.JCheckBox manager;
    private javax.swing.JLabel name;
    private javax.swing.JTextField nameField;
    private javax.swing.JButton registration;
    private javax.swing.JLabel revenue;
    private javax.swing.JTextField revenueField;
    // End of variables declaration//GEN-END:variables
}
