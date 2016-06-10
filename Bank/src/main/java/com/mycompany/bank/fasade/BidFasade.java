/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.fasade;

import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.db.BidService;
import com.mycompany.bank.db.ResponseFinancierService;
import com.mycompany.bank.db.RestrBidService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ann
 */
public class BidFasade {
    List<Bid> bids;
    int role;
    public BidFasade(int role, int id) {
        if(role == 1)
         bids = BidService.getBidByClientId(id);
        else if (role ==2)
             bids = BidService.getBidByManagerId(id);
        else 
             bids = BidService.getBidByFinancierId(id);
        this.role =role;
    }
    
    public  Bid getBids(int id) {
        return bids.get(id);
    }
    public  Bid getBidForBids(int id) {
        List<Integer> index = getArrayForBids();
        return bids.get(index.get(id));
    }

    public List<Integer> getArrayForBids() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (role ==1 && bids.get(i).getResponseFinancier() == null) 
                    index.add(i);
                else if (role ==2 && bids.get(i).getResponseFinancier() == null || (bids.get(i).getResponseFinancier() != null && bids.get(i).getResponseFinancier().getAnswer() == null)) 
                    index.add(i);
                else  if (role ==3 && bids.get(i).getResponseFinancier().getAnswer() == null) 
                    index.add(i);
            
            }
        } catch (Exception e1) {
        }
        return index;
    }
    
    public String[][] showBids() {
        if (bids == null) {
            return new String[][]{{null, null, null}};
        }
        List<Integer> index;
        index = getArrayForBids();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][3];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            if (role ==2) {
                array[i][2] = bid.getClient().getName();
            }
        }
        return array;
    }

    

    public String[][] showApprovedBids() {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForApprovedBids();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][7];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            array[i][2] = "+";
            array[i][3] = Integer.toString(bid.getResponseFinancier().getTime());
            array[i][4] = Integer.toString(bid.getResponseFinancier().getPersent());
            if(role ==2){
            array[i][5] = bid.getClient().getName();
            array[i][6] = "+";
            }
        }
        return array;
    }

    public  String[][] showAgreements(Boolean manager) {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForAgreements();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][6];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = Integer.toString(bid.getSum());
            array[i][1] = df.format(bid.getDate());
            array[i][2] = Integer.toString(bid.getResponseFinancier().getTime());
            array[i][3] = Integer.toString(bid.getResponseFinancier().getPersent());
            array[i][4] = Integer.toString(bid.getAgreement().getResidualAmount());
            if (manager) {
                array[i][5] = bid.getClient().getName();
            }
        }
        return array;
    }
     public  Bid getBidForAgreements(int id) {
        List<Integer> index = getArrayForAgreements();
        return bids.get(index.get(id));
    }

    public List<Integer> getArrayForAgreements() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (bids.get(i).getAgreement() != null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }

    public  Bid getBidForApprovedBids(int id) {
        List<Integer> index = getArrayForApprovedBids();
        return bids.get(index.get(id));
    }

    public List<Integer> getArrayForApprovedBids() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < bids.size(); i++) {
                if (role ==1 && bids.get(i).getResponseFinancier().getAnswer() != null) {
                    index.add(i);
                }
                else if (role ==2 && bids.get(i).getResponseFinancier().getAnswer() && bids.get(i).getResponseClient() && bids.get(i).getAgreement() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }


    public String[][] showBidsFinancier() {
        if (bids == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForBids();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][5];
        for (int i = 0; i < index.size(); i++) {
            Bid bid = bids.get(index.get(i));
            array[i][0] = bid.getClient().getName();
            array[i][1] = Integer.toString(bid.getClient().getRating());
            array[i][2] = Integer.toString(bid.getClient().getRevenue());
            array[i][3] = Integer.toString(bid.getSum());
            array[i][4] = df.format(bid.getDate());
        }
        return array;
    }
  

    public void setAgreement (int selectIndex){
    BidService.setAgreement(getBidForApprovedBids(selectIndex));
    }
    
    public void setFinancierId (int selectIndex, String  financierName){
    BidService.setFinancierId(getBidForBids(selectIndex),  financierName);
    }
    public Boolean setResponseFinancier(int selectIndex, int id, Boolean answer, int percent, int time){    
    return  ResponseFinancierService.setResponseFinancier(getBidForBids(selectIndex), id, answer, percent, time);
    } 
  
}
