/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

import com.mycompany.bank.service.ClientService;
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
        this.id = id;
        this.name = name;
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

    public void addResponseFinancier(ResponseFinancier responseFinancier) {
        this.responseFinanciers.add(responseFinancier);
    }

    public void addSpecialOffer(SpecialOffer specialOffer) {
        this.specialOffers.add(specialOffer);
    }

    public Boolean generateResponseFinancier(Bid bid, Boolean answer, int persent, int time) {
        ResponseFinancier responseFinancier;
        if (answer) {
            try {
                responseFinancier = new ResponseFinancier(answer, persent, time);
                this.responseFinanciers.add(responseFinancier);
                bid.setResponseFinancier(responseFinancier);
            } catch (Exception ex) {
                return false;
            }
        } else {
            responseFinancier = new ResponseFinancier(answer);
            this.responseFinanciers.add(responseFinancier);
            bid.setResponseFinancier(responseFinancier);
        }
        return true;
    }

    public SpecialOffer generateSpecialOffer(int sum, int persent, int time, int id){
        try{
        SpecialOffer specialOffer=new SpecialOffer(sum, persent, time,  id);
        this.specialOffers.add(specialOffer); 
        return specialOffer;
        }catch (Exception ex){
            return null;
        }
    }
    
        
    public String getJobClient(String clientName) throws Exception {
    if (clientName.equals(""))
      return "";
    return ClientService.getJobClient(clientName);
  }
}
