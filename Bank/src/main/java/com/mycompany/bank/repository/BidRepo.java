/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.repository;
import com.mycompany.bank.businessLogic.Bid;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ann
 */
public class BidRepo {
        
  private List<Bid> bids;

  public BidRepo() {
    bids = new ArrayList<Bid>();
  }

  
  
  public Bid  getBid (int index) {
      return bids.get(index);
  }
    public int getId () {
      return bids.size();
  }
    
        public int addBid (int id, Bid bid) {
     if(bids.get(id) == null){
         bids.add(bid);
     return 0;
     }
     else
     return -1;
  }
}
