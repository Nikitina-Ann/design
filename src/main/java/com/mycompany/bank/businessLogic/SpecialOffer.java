/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package com.mycompany.bank.businessLogic;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ann
 */
public class SpecialOffer {
    private int sum;
    private int persent;
    private int time;
    private Boolean responseClient;
    private Agreement agreement;
    private ArrayList<Client> clients;
    public SpecialOffer(int sum, int persent, int time) {
        this.sum = sum;
        this.persent = persent;
        this.time = time;
        this.responseClient = false;
        this.clients = new ArrayList<Client>();
    }

    public Boolean getResponseClient() {
        return responseClient;
    }

    public void setResponseClient(Boolean responseClient) {
        this.responseClient = responseClient;
    }
    
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPersent() {
        return persent;
    }

    public void setPersent(int persent) {
        this.persent = persent;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<Client> getClient() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
    
    public void addClient(Client client){
        this.clients.add(client);
    }  

}
