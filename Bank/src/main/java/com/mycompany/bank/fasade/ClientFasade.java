/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.fasade;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.db.BidService;
import com.mycompany.bank.db.ClientService;
import com.mycompany.bank.db.RestrBidService;

/**
 *
 * @author ann
 */
public class ClientFasade {
    BidFasade bidFasade;
    RestrBidFasade restrBidFasade;
    SpecialOfferFasade specialOfferFasade;
    Client client;
    public ClientFasade (int id){
        client = ClientService.getClientById(id);
        bidFasade = new BidFasade (1, id);
        restrBidFasade = new RestrBidFasade (1, id);
        specialOfferFasade = new SpecialOfferFasade (client);
    }
    
    public String getName(){
        return client.getName();
    }
    
    
   public void setResponseClientForBid(int selectIndex, Boolean answer){  
       client.setResponseClientBid(bidFasade.getBidForApprovedBids(selectIndex), answer);
  }
    public void setResponseClientForOffer(int selectIndex, Boolean answer){  
   client.setResponseClientSpecialOffer(selectIndex, answer);
  }
    public void createNewBid(int selectIndex,String reason){
    RestrBidService.createNewBid(bidFasade.getBids(selectIndex), reason);
  }
    
    public void setResponseClientRestrBid(int selectIndex, Boolean answer){
    client.setResponseClientRestrBid(restrBidFasade.getBidForApprovedRestrBids(selectIndex), answer);
}
    
    public String[][] showTable(){    
     return specialOfferFasade.showTable();
    }
    
    public String[][] showBids(){
        
     return bidFasade.showBids();
    }
    public String[][]  showApprovedBids(){
        
     return bidFasade.showApprovedBids();
    }
    public String[][] showAgreementsBid(){
        
     return bidFasade.showAgreements(false);
    }
        public String[][] showAgreementsOffer(){
        
     return specialOfferFasade.showAgreements(false);
    }
    public String[][]  showRestrBids(){
        
     return restrBidFasade.showRestrBids();
    }
        public String[][]  showApprovedRestrBids(){
        
     return restrBidFasade.showApprovedRestrBids();
    }
    
   
}
