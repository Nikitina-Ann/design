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
public class Financier {
    private int id;
    private String name;
    private ArrayList<ResponseFinancier> responseFinanciers;
    private ArrayList<SpecialOffer> specialOffers; 
    public Financier(int id, String name) {
        this.id=id;
        this.name=name;
        this.responseFinanciers = new ArrayList<ResponseFinancier>();
        this.specialOffers = new ArrayList<SpecialOffer>();
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<ResponseFinancier> getResponseFinancier() {
        return responseFinanciers;
    }

    public ArrayList<SpecialOffer> getSpecialOffer() {
        return specialOffers;
    }

    public void addResponseFinancier(ResponseFinancier responseFinancier){
        this.responseFinanciers.add(responseFinancier);
    }
    public void addSpecialOffer(SpecialOffer specialOffer){
        this.specialOffers.add(specialOffer);
    }
    public void generateResponseFinancier(Bid bid, Boolean answer, int persent, int time){
        ResponseFinancier responseFinancier;
        if(answer)
            responseFinancier = new ResponseFinancier(answer, persent, time);
        else
            responseFinancier = new ResponseFinancier(answer);
        this.responseFinanciers.add(responseFinancier);
        bid.setResponseFinancier(responseFinancier);
    }
    public void generateResponseFinancier(Bid bid, Boolean answer){
        ResponseFinancier responseFinancier;
        if(answer)
            throw new IllegalArgumentException("Exception: generateResponseFinancier"); 
        else
            responseFinancier = new ResponseFinancier(answer);
        this.responseFinanciers.add(responseFinancier);
        bid.setResponseFinancier(responseFinancier);
    }
    
    public void generateSpecialOffer(int sum, int persent, int time){
        SpecialOffer specialOffer=new SpecialOffer(sum, persent, time);
        this.specialOffers.add(specialOffer);
    }
    
        
}
