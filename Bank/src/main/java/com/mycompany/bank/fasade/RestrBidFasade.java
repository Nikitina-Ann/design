/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.fasade;

import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.businessLogic.RestrBid;
import com.mycompany.bank.db.BidService;
import com.mycompany.bank.db.RestrBidService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class RestrBidFasade {

    List<RestrBid> restrBids;
    int role;

    public RestrBidFasade(int role, int id) {
        if (role == 3) {
            restrBids = RestrBidService.getBidByFinancierId(id);
        } else {
            restrBids = RestrBidService.getBidByRoleId(id, 1);
        }
        this.role = role;
    }

    public String[][] showRestrBids() {
        if (restrBids == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForRestrBids();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][5];
        try {
            for (int i = 0; i < index.size(); i++) {
                RestrBid restrBid = restrBids.get(index.get(i));
                array[i][0] = Integer.toString(restrBid.getBid().getAgreement().getResidualAmount());
                array[i][1] = Integer.toString(restrBid.getBid().getResponseFinancier().getPersent());
                array[i][2] = Integer.toString(restrBid.getBid().getResponseFinancier().getTime());
                array[i][3] = df.format(restrBid.getDate());
                if (role ==2) {
                    array[i][4] = restrBid.getClient().getName();
                }
            }
        } catch (Exception e1) {
        }
        return array;
    }

    public  String[][] showApprovedRestrBids() {
        if (restrBids == null) {
            return new String[][]{{null, null, null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForApprovedRestrBids(); 
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][8];
        for (int i = 0; i < index.size(); i++) {
            RestrBid restrBid = restrBids.get(index.get(i));
            array[i][0] = Integer.toString(restrBid.getBid().getAgreement().getResidualAmount());
            array[i][1] = Integer.toString(restrBid.getBid().getResponseFinancier().getPersent());
            array[i][2] = Integer.toString(restrBid.getBid().getResponseFinancier().getTime());
            array[i][3] = df.format(restrBid.getDate());
            if (!restrBid.getResponseFinancier().getAnswer()) {
                array[i][4] = "-";
            } else {
                array[i][4] = "+";
                array[i][5] = Integer.toString(restrBid.getResponseFinancier().getTime());
                array[i][6] = Integer.toString(restrBid.getResponseFinancier().getPersent());
            }
            if (role ==2 ) {
                array[i][7] = "+";
            }
        }
        return array;
    }

    public RestrBid getBidForApprovedRestrBids( int id) {
        List<Integer> index = getArrayForApprovedRestrBids();
        return restrBids.get(index.get(id));
    }

    public  List<Integer> getArrayForApprovedRestrBids() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < restrBids.size(); i++) {
                if (role ==1 && restrBids.get(i).getResponseFinancier().getAnswer() != null) {
                    index.add(i);
                }
                else if (role ==2 && restrBids.get(i).getResponseFinancier().getAnswer() && restrBids.get(i).getResponseClient() && !restrBids.get(i).getAgreement()) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }

    
    public  RestrBid getBidForRestrBids( int id) {
        List<Integer> index = getArrayForRestrBids();
        return restrBids.get(index.get(id));
    }

    public  List<Integer> getArrayForRestrBids() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < restrBids.size(); i++) {
                 if (role ==3 && restrBids.get(i).getResponseFinancier().getAnswer() == null) {
                    index.add(i);
                }
                 else if (restrBids.get(i).getResponseFinancier() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }
    
    public  String[][] showRestrBidsFinancier() {
        if (restrBids == null) {
            return new String[][]{{null, null, null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForRestrBids();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][8];
        for (int i = 0; i < index.size(); i++) {
            RestrBid restrBid = restrBids.get(index.get(i));
            array[i][0] = restrBid.getBid().getClient().getName();
            array[i][1] = Integer.toString(restrBid.getBid().getClient().getRating());
            array[i][2] = Integer.toString(restrBid.getBid().getClient().getRevenue());
            array[i][3] = restrBid.getDocumentation();
            array[i][4] = Integer.toString(restrBid.getBid().getAgreement().getResidualAmount());
            array[i][5] = Integer.toString(restrBid.getBid().getResponseFinancier().getPersent());
            array[i][6] = Integer.toString(restrBid.getBid().getResponseFinancier().getTime());
            array[i][7] = df.format(restrBid.getDate());
        }
        return array;
    }

    public void setAgreement (int selectIndex){
    RestrBidService.setAgreement(getBidForApprovedRestrBids(selectIndex));
    }
    
    public void setFinancierId(int selectIndex, String financierName){    
    RestrBidService.setFinancierId(getBidForRestrBids( selectIndex), financierName);
    } 
    
     public void setResponseFinancier(int selectIndex, Boolean answer, int percent, int time){     
    RestrBidService.setResponseFinancier(getBidForRestrBids( selectIndex), answer, percent, time);
}
}
