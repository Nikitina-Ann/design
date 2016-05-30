/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 *
 * @author ann
 */
public class Client {
    private int id;
    private String name;
    private int rating;
    private int revenue;
    private List<SpecialOffer> specialOffers;
    public Client(int id, String name, int rating,int revenue){
        this.id =id;
        this.name = name;
        this.rating = rating;
        this.revenue = revenue;
        this.specialOffers = new ArrayList<SpecialOffer>();
    }
    
    public Client(int id, String name, int rating,int revenue, List<SpecialOffer> specialOffers){
        this.id =id;
        this.name = name;
        this.rating = rating;
        this.revenue = revenue;
        this.specialOffers = specialOffers;
    }
    
    public int getRating() {
        return this.rating;
    }
    public int getRevenue() {
        return this.revenue;
    }
    public void setRating(int rating) {
        if(rating < 0 || rating > 100) 
            throw new IllegalArgumentException("Exception: setRating"); 
        this.rating = rating;
    }
    public void setRevenue(int revenue) {
        if(revenue < 0 ) 
            throw new IllegalArgumentException("Exception: setRevenue"); 
        this.revenue = revenue;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(ArrayList<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    public void addSpecialOffer(SpecialOffer specialOffer){
        this.specialOffers.add(specialOffer);
    }
    
    public void setResponseClient(Bid bid, Boolean responseClient) {
        bid.setResponseClient(responseClient);
    }
    
    public void acceptSpecialOffer(SpecialOffer specialOffer) {
    if (!this.specialOffers.contains(specialOffer)) 
        throw new IllegalArgumentException("Exception: acceptSpecialOffer"); 
    specialOffer.setResponseClient(true);
    }
    
    public static Client getClientByName(List<Client> clients, String name) {
    for (Client client: clients ) {
      if(client.getName().equals(name))
        return client;
    }
    return null;
  }
  
}