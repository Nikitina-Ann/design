/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.gui;

import javax.swing.*;
import java.awt.event.*;
import com.mycompany.bank.db.BidService;

/**
 *
 * @author ann
 */
public class CreateBidFrame extends javax.swing.JFrame {

    /**
     * Creates new form CreateBid
     */
    public CreateBidFrame(int id) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createBid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int sum = Integer.parseInt(sumField.getText());
                    if (BidService.createNewBid(id, sum))
                    JOptionPane.showMessageDialog(null, "Заявка успешно отправлено, ждите ответа финансиста.");
                    else
                    JOptionPane.showMessageDialog(null, "Сумма - положительное значение.");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Введите сумму кредита (в рублях).");
                }
                dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createBid = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sumField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createBid.setText("Отправить заявку");

        jLabel1.setText("Введите сумму кредита (в рублях):");

        sumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(sumField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(createBid, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(sumField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createBid)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sumFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createBid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField sumField;
    // End of variables declaration//GEN-END:variables
}
