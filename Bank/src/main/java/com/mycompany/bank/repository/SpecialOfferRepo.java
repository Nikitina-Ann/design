/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.repository;

import com.mycompany.bank.businessLogic.SpecialOffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class SpecialOfferRepo {

    private List<SpecialOffer> specialOffers;

    public SpecialOfferRepo() {
        specialOffers = new ArrayList<SpecialOffer>();
    }

    public SpecialOffer getBid(int index) {
        return specialOffers.get(index);
    }

    public int getId() {
        return specialOffers.size();
    }

    public int addSpecialOffer(int id, SpecialOffer specialOffer) {
        if (specialOffers.get(id) == null) {
            specialOffers.add(specialOffer);
            return 0;
        } else {
            return -1;
        }
    }
    
}
