/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package com.mycompany.bank.businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ann
 */
public class SpecialOffer {

    private int id;
    private int sum;
    private int persent;
    private int time;
    private Client client;
    private Boolean responseClient;
    private Agreement agreement;

    public SpecialOffer(int sum, int persent, int time, int id) {
        if (sum< 0 || persent<0 || persent> 100 || time<0 || time> 100 || id<0)
            throw new IllegalArgumentException("Exception: ResponseFinancier");
        else{
        this.sum = sum;
        this.persent = persent;
        this.time = time;
        this.responseClient = null;
        this.id = id;
        }
    }
    public SpecialOffer(int sum, int persent, int time, int id, Boolean responseClient, Agreement agreement) {
        this.sum = sum;
        this.persent = persent;
        this.time = time;
        this.responseClient =responseClient;
        this.id = id;
        this.agreement=agreement;
    }   
    public SpecialOffer(int sum, int persent, int time, int id, Boolean responseClient, Client client) {
        this.sum = sum;
        this.persent = persent;
        this.time = time;
        this.responseClient =responseClient;
        this.id = id;
        this.client=client;
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
       public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

}
