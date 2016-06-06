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
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.Manager;
import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.repository.SpecialOfferRepo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

/**
 *
 * @author ann
 */
public class ClientTest {

    ClientRepo clientRepo;
    ManagerRepo managerRepo;
    FinancierRepo financierRepo;
    BidRepo bidRepo;
    Client client;
    Financier financier;
    SpecialOfferRepo specialOfferRepo;

    @Before
    public void setUp() {
        clientRepo = new ClientRepo();
        managerRepo = new ManagerRepo();
        financierRepo = new FinancierRepo();
        specialOfferRepo = new SpecialOfferRepo();
        bidRepo = new BidRepo();
        client = this.clientRepo.getClientById(1);
        financier = this.financierRepo.getFinancier(0);
    }

    @Test
    public void testGetRating() {
        assertEquals(client.getRating(), 10);
        client = this.clientRepo.getClientById(2);
        assertEquals(client.getRating(), 50);
    }

    @Test
    public void testGetRevenue() {
        assertEquals(client.getRevenue(), 40000);
        client = this.clientRepo.getClientById(2);
        assertEquals(client.getRevenue(), 20000);
    }

    @Test
    public void testSetRating() {
        assertEquals(client.getRating(), 10);
        client.setRating(70);
        assertEquals(client.getRating(), 70);
        Exception exception = null;
        try {
            client.setRating(-10);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(client.getRating(), 70);
        exception = null;
        try {
            client.setRating(150);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(client.getRating(), 70);
    }

    @Test
    public void testSetRevenue() {
        assertEquals(client.getRevenue(), 40000);
        client.setRevenue(70000);
        assertEquals(client.getRevenue(), 70000);
        Exception exception = null;
        try {
            client.setRevenue(-10000);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
        assertEquals(client.getRevenue(), 70000);
    }

    @Test
    public void newBid() {
        Bid bid = client.newBid(bidRepo.getId(), new Date(), 10000);
        assertEquals(bid.getClient().getId(), client.getId());
        assertEquals(bid.getSum(), 10000);
    }

    @Test
    public void newBidException() {
        Exception exception = null;
        try {
            Bid bid = client.newBid(bidRepo.getId(), new Date(), -12);
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);

    }
    
    @Test
    public void testSetResponseClientBid() {
        Bid bid =client.newBid(bidRepo.getId(), new Date(), 10000);
        Manager manager = managerRepo.getRandomManager();
        bid.setManager(manager);
        manager.sendBid(bid,financier);
        client.setResponseClientBid(bid, true);
        assertEquals(bid.getResponseClient(), null);
        financier.generateResponseFinancier(bid, true, 12, 50);
        client.setResponseClientBid(bid, true);
        assertTrue(bid.getResponseClient());
    }
    
     @Test
    public void testSetResponseClientOffer() {
        SpecialOffer specialOffer = financier.generateSpecialOffer(50000, 50, 30, specialOfferRepo.getId());
        client.addSpecialOffer(specialOffer);    
        assertTrue(client.setResponseClientSpecialOffer(0, true));
        assertTrue(client.getSpecialOffers().get(0).getResponseClient());
        assertFalse(client.setResponseClientSpecialOffer(2, true));
    }

}
