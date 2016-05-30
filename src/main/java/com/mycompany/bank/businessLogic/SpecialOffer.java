/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
package com.mycompany.bank.businessLogic;

import static com.mycompany.bank.businessLogic.RestrBid.getArrayForRestrBidsFinancier;
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
        this.sum = sum;
        this.persent = persent;
        this.time = time;
        this.responseClient = null;
        this.id = id;
    }
    public SpecialOffer(int sum, int persent, int time, int id, Boolean responseClient,  Agreement agreement) {
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

    public static String[][] showTable(List<SpecialOffer> specialOffers) {
        if (specialOffers == null) 
            return new String[][]{{null, null, null, null}};
       List<Integer> index = getArrayForNotAgreement(specialOffers);
        String[][] array = new String[index.size()][4]; 
            for (int i = 0; i < index.size(); i++) {
                SpecialOffer specialOffer = specialOffers.get(index.get(i));
                array[i][0] = Integer.toString(specialOffer.getSum());
                array[i][1] = Integer.toString(specialOffer.getPersent());
                array[i][2] = Integer.toString(specialOffer.getTime());
                if (specialOffer.getResponseClient() == null) {
                    array[i][3] = "";
                } else if (specialOffer.getResponseClient()) {
                    array[i][3] = "согласие";
                } else {
                    array[i][3] = "отказ";
                }
            }
        return array;
    }
    
     public static String[][] showApprovedTable(List<SpecialOffer> specialOffers) {
        if (specialOffers == null) 
            return new String[][]{{null, null, null, null}};
        List<Integer> index = getArrayForApprovedSpecOffer(specialOffers);
        String[][] array = new String[index.size()][4]; 
            for (int i = 0; i < index.size(); i++) {
                SpecialOffer specialOffer = specialOffers.get(index.get(i));
                array[i][0] = Integer.toString(specialOffer.getSum());
                array[i][1] = Integer.toString(specialOffer.getPersent());
                array[i][2] = Integer.toString(specialOffer.getTime());
                array[i][3] = specialOffer.getClient().getName();
            }
        return array;
    }
    
    public static SpecialOffer getApprovedSpecOffer (List<SpecialOffer> specialOffers, int id) {
        List<Integer> index = getArrayForApprovedSpecOffer(specialOffers);
        return specialOffers.get(index.get(id));
    }
    
    public static List<Integer> getArrayForApprovedSpecOffer (List<SpecialOffer> specialOffers){
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < specialOffers.size(); i++) {
                if (specialOffers.get(i).getResponseClient() == true ) 
                    index.add(i);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }
    
     public static SpecialOffer getNotAgreement (List<SpecialOffer> specialOffers, int id) {
        List<Integer> index = getArrayForNotAgreement(specialOffers);
        return specialOffers.get(index.get(id));
    }
     
     public static List<Integer> getArrayForNotAgreement (List<SpecialOffer> specialOffers){
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < specialOffers.size(); i++) {
                if (specialOffers.get(i).getAgreement() == null) 
                    index.add(i);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }
    
    public static String[][] showAgreements(List<SpecialOffer> specialOffers, Boolean manager) {
        if (specialOffers == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForAgreements(specialOffers);
        String[][] array = new String[index.size()][5];
            for (int i = 0; i < index.size(); i++) {
                SpecialOffer specialOffer = specialOffers.get(index.get(i));
                    array[i][0] = Integer.toString(specialOffer.getSum());
                    array[i][1] = Integer.toString(specialOffer.getTime());
                    array[i][2] = Integer.toString(specialOffer.getPersent());
                    array[i][3] = Integer.toString(specialOffer.getAgreement().getResidualAmount());
                    if (manager) 
                        array[i][4] = specialOffer.getClient().getName();
            }
        return array;
    }
    
    public static SpecialOffer getBidForAgreements (List<SpecialOffer> specialOffers, int id) {
        List<Integer> index = getArrayForAgreements(specialOffers);
        return specialOffers.get(index.get(id));
    }
    
    public static List<Integer> getArrayForAgreements (List<SpecialOffer> specialOffers){
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < specialOffers.size(); i++) {
                if (specialOffers.get(i).getAgreement() != null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }
}
