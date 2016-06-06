/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

import java.util.Date;

/**
 *
 * @author ann
 */
public class AllBid {

    private int id;
    private Date date;
    private Boolean responseClient;
    private ResponseFinancier responseFinancier;
    private Client client;
    private Manager manager;
    private Financier financier;

    public AllBid(int id, Date date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.responseFinancier = null;
    }


    public AllBid(int id, Date date, Boolean responseClient, ResponseFinancier responseFinancier, Client client) {
        this.id = id;
        this.date = date;
        this.responseClient = responseClient;
        this.responseFinancier = responseFinancier;
        this.client = client;
    }
 public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Boolean getResponseClient() {
        return responseClient;
    }

    public void setResponseClient(Boolean responseClient) {
        this.responseClient = responseClient;
    }
    
     public ResponseFinancier getResponseFinancier() {
        return this.responseFinancier;
    }

    public void setResponseFinancier(ResponseFinancier responseFinancier) {
        if(this.responseFinancier == null)
        this.responseFinancier = responseFinancier;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
         public Manager getManager() {
        return this.manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
         public Financier getFinancier() {
        return this.financier;
    }

    public void setFinancier(Financier financier) {
        this.financier = financier;
    }
}
