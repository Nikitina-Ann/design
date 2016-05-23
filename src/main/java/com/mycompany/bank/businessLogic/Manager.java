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
    private ArrayList<Client> clients;
    
    public Manager(int id, String name) {
        this.id =id;
        this.name = name;
        this.clients = new ArrayList<Client>();
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

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
    
    public void addClient(Client client){
        if (!this.clients.contains(client)) 
            this.clients.add(client);
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
    
    public void sendSpecialOffer(SpecialOffer specialOffer){
        for (int i = 0; i < specialOffer.getClient().size(); i++) {
            Client client = specialOffer.getClient().get(i);
            client.addSpecialOffer(specialOffer);
        }
    }
    
    
}
