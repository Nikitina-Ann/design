/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;
import  java.util.Date;
import  java.util.List;
import  java.util.ArrayList;
/**
 *
 * @author ann
 */
public class Bid {
    private Date date;
    private Boolean responseClient;
    private ResponseFinancier responseFinancier;
    private Agreement agreement;
    int sum;
    public Bid(Date date, int sum) {
        this.date = date;
        this.responseClient = false;
        this.sum = sum;
    }
     public Bid(Date date, Boolean responceClient, int sum, ResponseFinancier responseFinancier, Agreement agreement) {
        this.date = date;
        this.sum = sum;
        this.responseClient = responseClient;
        this.responseFinancier = responseFinancier;
        this.agreement = agreement;
    }
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
     public int getSum() {
        return this.sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public Boolean getResponseClient() {
        return responseClient;
    }

    public void setResponseClient(Boolean responseClient) {
        this.responseClient = responseClient;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public ResponseFinancier getResponseFinancier() {
        return this.responseFinancier;
    }

    public void setResponseFinancier(ResponseFinancier responseFinancier) {
        this.responseFinancier = responseFinancier;
    }
   
    
}

