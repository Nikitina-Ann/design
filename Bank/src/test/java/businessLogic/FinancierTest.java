/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.repository.ClientRepo;
import com.mycompany.bank.repository.BidRepo;
import com.mycompany.bank.repository.SpecialOfferRepo;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ann
 */
public class FinancierTest {
    Bid bid;
    Financier financier;
    Client client;
    ClientRepo clientRepo;
    BidRepo bidRepo;
    SpecialOfferRepo specialOfferRepo;
    
    @Before
    public void setUp() {
        clientRepo = new ClientRepo();
        bidRepo = new BidRepo();
        specialOfferRepo = new SpecialOfferRepo();
        client = this.clientRepo.getClientById(1);   
        bid =client.newBid(bidRepo.getId(), new Date(), 10000);
        financier = new Financier(1, "Петр Иванов");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGenerateResponseFinancierTrue() {
        assertFalse(financier.generateResponseFinancier(bid, true,-1,10));
        assertFalse(financier.generateResponseFinancier(bid, true,101,10));
        assertFalse(financier.generateResponseFinancier(bid, true,-1,-14));
        financier.generateResponseFinancier(bid, true,10,30);
        assertEquals(bid.getResponseFinancier().getPersent(), 10);
        assertEquals(bid.getResponseFinancier().getTime(), 30);
        assertTrue(bid.getResponseFinancier().getAnswer());
        financier.generateResponseFinancier(bid, false,0,0);
        assertTrue(bid.getResponseFinancier().getAnswer());
    }
    @Test
    public void testGenerateResponseFinancierFalse() {
        financier.generateResponseFinancier(bid, false,0,0);
        assertFalse(bid.getResponseFinancier().getAnswer());
    }
    
    @Test
    public void testGenerateSpecialOffer() {
        SpecialOffer specialOffer =financier.generateSpecialOffer(5000, 110, 30, specialOfferRepo.getId());
        assertEquals(specialOffer, null);
        specialOffer =financier.generateSpecialOffer(5000, 10, -20, specialOfferRepo.getId());
        assertEquals(specialOffer, null);
        specialOffer =financier.generateSpecialOffer(-90, 10, 30, specialOfferRepo.getId());
        assertEquals(specialOffer, null);
        specialOffer =financier.generateSpecialOffer(90, 10, 110, specialOfferRepo.getId());
        assertEquals(specialOffer, null);
        specialOffer =financier.generateSpecialOffer(50000, 10, 30, specialOfferRepo.getId());
        assertEquals(specialOffer.getSum(), 50000);
        assertEquals(specialOffer.getPersent(), 10);
        assertEquals(specialOffer.getTime(), 30);
    }
    
        
}
