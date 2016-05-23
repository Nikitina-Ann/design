/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import com.mycompany.bank.repository.ClientRepo;
import com.mycompany.bank.repository.ManagerRepo;
import com.mycompany.bank.repository.FinancierRepo;
import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.Manager;
import com.mycompany.bank.businessLogic.Bid;

import java.util.ArrayList;
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
    Manager manager;
    Client client;
    Financier financier;
    Bid bid;
    
    @Before
    public void setUp() {
        this.clientRepo = new ClientRepo();
        this.managerRepo = new ManagerRepo();
        this.financierRepo = new FinancierRepo();
        client = this.clientRepo.getClient(0);
        manager = this.managerRepo.getManager(0);
        financier = this.financierRepo.getFinancier(0);
        bid=new Bid(new Date(), 10000);
        client.sendBid(bid, manager);
    }
    /**
     * Test of getName method, of class Manager.
     */
    @Test
    public void testGetName() {
        assertEquals(manager.getName(), "Иванов Петр");
        manager = this.managerRepo.getManager(1);   
        assertEquals(manager.getName(), "Карпов Владимир");
        
    }

    /**
     * Test of setName method, of class Manager.
     */
    @Test
    public void testSetName() { 
        assertEquals(manager.getName(), "Иванов Петр");
        manager.setName("Калинин Дмитрий");   
        assertEquals(manager.getName(), "Калинин Дмитрий");
    }

    /**
     * Test of enterAgreement method, of class Manager.
     */
    @Test
    public void testEnterAgreement() {
        bid=manager.getClients().get(0).getBid(0);   
        financier.generateResponseFinancier(bid, true, 10, 90);
        client.setResponseClient(bid, true);
        manager.enterAgreement(bid);
        assertFalse(bid.getAgreement().getExtinguished());
        assertEquals(bid.getAgreement().getResidualAmount(), 10000);
    }
    
    /**
     * Test of changeAgreement method, of class Manager.
     */
    @Test
    public void testChangeAgreement() {
        bid=manager.getClients().get(0).getBid(0);
        financier.generateResponseFinancier(bid, true, 10, 90);
        client.setResponseClient(bid, true);
        manager.enterAgreement(bid);
        assertFalse(bid.getAgreement().getExtinguished());
        assertEquals(bid.getAgreement().getResidualAmount(), 10000);
        manager.changeAgreement(bid, 20000, 20, 90);
        assertEquals(bid.getAgreement().getResidualAmount(), 20000);
        assertEquals(bid.getSum(), 20000);  
    }

    @Test
    public void testEnterAgreementExcept() {
        bid=manager.getClients().get(0).getBid(0);
        assertFalse(bid.getResponseClient());
        Exception exception = null;
        try {
            manager.enterAgreement(bid);
        } catch (IllegalArgumentException e) {
        exception = e;
        }
        assertNotNull(exception);
        client.setResponseClient(bid, true);
        assertTrue(bid.getResponseClient());
    }
    
    @Test
    public void testRefill() {
        bid=manager.getClients().get(0).getBid(0);
        financier.generateResponseFinancier(bid, true, 10, 90);
        client.setResponseClient(bid, true);
        manager.enterAgreement(bid);
        bid.getAgreement().refill(5000);
        assertEquals(bid.getAgreement().getResidualAmount(), 5000);
        bid.getAgreement().refill(6000);
        assertEquals(bid.getAgreement().getResidualAmount(), 0);
        assertTrue(bid.getAgreement().getExtinguished());
    }
    
    @Test
    public void testSpecialOffer() {
        financier.generateSpecialOffer(5000, 50, 30);
        SpecialOffer specialOffer = financier.getSpecialOffer().get(0);
        specialOffer.addClient(client);
        assertThat(specialOffer.getClient(), hasItem(client));
        assertFalse(client.getSpecialOffers().contains(specialOffer));
        manager.sendSpecialOffer(specialOffer);
        assertTrue(client.getSpecialOffers().contains(specialOffer));
        assertFalse(specialOffer.getResponseClient());
        Exception exception = null;
        try {
           manager.enterAgreement(specialOffer);
        } catch (IllegalArgumentException e) {
        exception = e;
        }
        assertNotNull(exception);
        client.acceptSpecialOffer(specialOffer);
        assertTrue(specialOffer.getResponseClient());
        manager.enterAgreement(specialOffer);
        assertEquals(specialOffer.getAgreement().getResidualAmount(), 5000);
        assertFalse(specialOffer.getAgreement().getExtinguished());
    }
}
