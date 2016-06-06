/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ann
 */
public class RestrBid extends AllBid {

    private Bid bid;
    private String doc;
    private Boolean agreement;

    public RestrBid(Bid bid, int id, String doc, Date date, Client client, int persent, int time, Boolean answer, Boolean responseClient, Boolean agreement) {
        super(id, date, responseClient, new ResponseFinancier(answer, persent, time), client);
        this.doc = doc;
        this.bid = bid;
        this.agreement = agreement;
    }

    public RestrBid(Bid bid, int id, String doc, Date date, Client client, Boolean agreement) {
        super(id, date, client);
        this.doc = doc;
        this.bid = bid;
        this.agreement = agreement;
    }

    public String getDocumentation() {
        return doc;
    }

    public void setDocumentation(String doc) {
        this.doc = doc;
    }

    public Bid getBid() {
        return this.bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Boolean getAgreement() {
        return this.agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }

}
