/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import com.mycompany.bank.repository.ClientRepo;
import com.mycompany.bank.repository.ManagerRepo;
import com.mycompany.bank.repository.FinancierRepo;
import com.mycompany.bank.repository.BidRepo;
import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.Manager;
import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.repository.SpecialOfferRepo;

import java.util.Date;
import static org.hamcrest.CoreMatchers.hasItem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ann
 */
public class ManagerTest {
    ClientRepo clientRepo;
    ManagerRepo managerRepo;
    FinancierRepo financierRepo;
    SpecialOfferRepo specialOfferRepo;
    BidRepo bidRepo;
    Manager manager;
    Client client;
    Financier financier;
    
    @Before
    public void setUp() {
        this.clientRepo = new ClientRepo();
        this.managerRepo = new ManagerRepo();
        this.financierRepo = new FinancierRepo();  
        this.specialOfferRepo = new SpecialOfferRepo();
        this.bidRepo = new BidRepo();
        client = this.clientRepo.getClient(0);
        financier = this.financierRepo.getFinancier(0);
    }

    @Test
    public void testSendManager() {
        Bid bid =client.newBid(bidRepo.getId(), new Date(), 10000);
        manager = managerRepo.getRandomManager();
        bid.setManager(manager);
        assertEquals(bid.getManager().getId(), manager.getId());
        manager.sendBid(bid,financier);
        assertEquals(bid.getFinancier().getId(), financier.getId());
        Financier financier1 = this.financierRepo.getFinancier(1);
        manager.sendBid(bid,financier1);
        assertEquals(bid.getFinancier().getId(), financier.getId());
        assertNotEquals(bid.getFinancier().getId(), financier1.getId());
    }
    
    @Test
    public void testEnterAgreement() {
        Bid bid =client.newBid(bidRepo.getId(), new Date(), 10000);
        manager = managerRepo.getRandomManager();
        bid.setManager(manager);
        manager.sendBid(bid,financier);
        financier.generateResponseFinancier(bid, true, 12, 50);
        client.setResponseClientBid(bid, true);
        assertEquals(bid.getResponseFinancier().getPersent(), 12);
        manager.enterAgreementBid(bid);
        assertFalse(bid.getAgreement().getExtinguished());
        assertEquals(bid.getAgreement().getResidualAmount(), 10000);
    }
    
     @Test
    public void testSpecialOffer() {
        SpecialOffer specialOffer = financier.generateSpecialOffer(50000, 50, 30, specialOfferRepo.getId());
        assertEquals(specialOffer.getSum(), 50000);
        assertFalse(client.getSpecialOffers().contains(specialOffer));
        client.addSpecialOffer(specialOffer);
        assertThat(client.getSpecialOffers(), hasItem(specialOffer));
        assertTrue(client.getSpecialOffers().contains(specialOffer));
        Exception exception = null;
        try {
           manager.enterAgreementOffer(client.getSpecialOffers().get(0));     
           assertFalse(specialOffer.getResponseClient());
        } catch (Exception e) {
        exception = e;
        }
        assertNotNull(exception);
        assertTrue(client.setResponseClientSpecialOffer(0, true));
        try {
           manager.enterAgreementOffer(client.getSpecialOffers().get(0));     
           assertTrue(specialOffer.getResponseClient());
           assertEquals(specialOffer.getAgreement().getResidualAmount(), 50000);
           assertFalse(specialOffer.getAgreement().getExtinguished());
        } catch (Exception e) {
        exception = e;
        }
    }
    
     @Test
    public void testSpecialOfferFalse() {
        SpecialOffer specialOffer = financier.generateSpecialOffer(50000, 50, 30, specialOfferRepo.getId());
        client.addSpecialOffer(specialOffer);
        assertTrue(client.setResponseClientSpecialOffer(0, false));
        Exception exception = null;
        try {
           manager.enterAgreementOffer(client.getSpecialOffers().get(0));     
           assertFalse(specialOffer.getResponseClient());
        } catch (Exception e) {
        exception = e;
        }
    }
}
