/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;
import com.mycompany.bank.db.BidService;
import com.mycompany.bank.db.ClientService;
import java.util.List;

/**
 *
 * @author ann
 */
public class BankCredit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BidService bidService = new BidService();
        List<Bid> list= bidService.getBidByClientId(1);
        System.out.println(list);
        System.out.println("Hello World!");
    }
    
}
