package com.mycompany.bank.businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Bid extends AllBid {

    private Agreement agreement;
    int sum;

    public Bid(int id, Date date, int sum, Client client) {
        super(id, date, client);
        this.sum = sum;
        this.agreement = null;
    }


    public Bid(int id, Date date, Boolean responseClient, int sum, ResponseFinancier responseFinancier, Agreement agreement, Client client) {
        super(id, date, responseClient, responseFinancier, client);
        this.sum = sum;
        this.agreement = agreement;
    }

    public int getSum() {
        return this.sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }

    public static Bid getBid(List<Bid> bids, int id) {
        for (Bid bid : bids) {
            if (bid.getId() == id) {
                return bid;
            }
        }
        return null;
    }
    

}
