/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.gui;

import com.mycompany.bank.db.ClientService;
import com.mycompany.bank.db.ManagerService;
import com.mycompany.bank.db.FinancierService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author ann
 */
public class StartFrame extends javax.swing.JFrame {

    public StartFrame() {
        super("Система кредитования");
        initComponents();
        setLocationRelativeTo(null);
        registration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame().setVisible(true);
            }
        });

        clientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(checkId.getText());
                    if (ClientService.getClientById(id) != null) {
                        new ClientFrame(id).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Клиента с таким ID нет в системе");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "ID - числовое значение");
                }
            }
        });

        managerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(checkId.getText());
                    if (ManagerService.findManagerById(id) != null) {
                        new ManagerFrame(id).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Менеджера с таким ID нет в системе");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "ID - числовое значение");
                }
            }
        });

        financierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(checkId.getText());
                    if (FinancierService.findFinancierById(id) != null) {
                        new FinancierFrame(id).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Финансиста с таким ID нет в системе");
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "ID - числовое значение");
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientButton = new javax.swing.JButton();
        managerButton = new javax.swing.JButton();
        financierButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        checkId = new javax.swing.JTextField();
        registration = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clientButton.setText("Клиент");

        managerButton.setText("Менеджер");

        financierButton.setText("Финансист");

        jLabel1.setText("Введите ID и выберите пользователя:");

        checkId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIdActionPerformed(evt);
            }
        });

        registration.setText("Регистрация");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(managerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkId, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(registration, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(registration)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(checkId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(clientButton)
                .addGap(18, 18, 18)
                .addComponent(managerButton)
                .addGap(18, 18, 18)
                .addComponent(financierButton)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkIdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField checkId;
    private javax.swing.JButton clientButton;
    private javax.swing.JButton financierButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton managerButton;
    private javax.swing.JButton registration;
    // End of variables declaration//GEN-END:variables
}
