/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

import java.util.ArrayList;

/**
 *
 * @author ann
 */
public class Manager {
    private int id;
    private String name;
    
    public Manager(int id, String name) {
        this.id =id;
        this.name = name;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void enterAgreement(Bid bid) {
        if(!bid.getResponseClient() || !bid.getResponseFinancier().getAnswer())
            throw new IllegalArgumentException("Exception: enterAgreement"); 
        Agreement agreement = new Agreement(bid.getSum());
        bid.setAgreement(agreement);
    }
      
    public void enterAgreement(SpecialOffer specialOffer) {
        if(!specialOffer.getResponseClient())
            throw new IllegalArgumentException("Exception: enterAgreement"); 
        Agreement agreement = new Agreement(specialOffer.getSum());
        specialOffer.setAgreement(agreement);
    }
    
    public void changeAgreement(Bid bid, int newSum, int persent, int time) {
        bid.setSum(newSum);
        bid.getResponseFinancier().changeCreditPlan(persent, time);
        bid.getAgreement().setResidualAmount(newSum);
    }
    
    public void sendSpecialOffer(SpecialOffer specialOffer, Client client){
        client.addSpecialOffer(specialOffer);
    }
      
}
