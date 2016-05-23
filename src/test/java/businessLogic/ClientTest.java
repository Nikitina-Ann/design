/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import com.mycompany.bank.repository.ClientRepo;
import com.mycompany.bank.repository.ManagerRepo;
import com.mycompany.bank.repository.FinancierRepo;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.Manager;
import com.mycompany.bank.businessLogic.Bid;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author ann
 */
public class ClientTest {
    
    ClientRepo clientRepo;
    ManagerRepo managerRepo;
    FinancierRepo financierRepo;
    
    @Before
    public void setUp() {
        this.clientRepo = new ClientRepo();
        this.managerRepo = new ManagerRepo();
        this.financierRepo = new FinancierRepo();
    }

    /**
     * Test of getRating method, of class Client.
     */
    @Test
    public void testGetRating() {
        Client client = this.clientRepo.getClientById(1);   
        assertEquals(client.getRating(), 10);
        client = this.clientRepo.getClientById(2);
        assertEquals(client.getRating(), 50);
}

    /**
     * Test of getRevenue method, of class Client.
     */
    @Test
    public void testGetRevenue() {
        Client client = this.clientRepo.getClientById(1);   
        assertEquals(client.getRevenue(),40000);
        client = this.clientRepo.getClientById(2);
        assertEquals(client.getRevenue(), 20000);
    }

    @Test
    public void testSetRating() {
        Client client = this.clientRepo.getClientById(1);
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
        Client client = this.clientRepo.getClientById(1);
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
    public void testGetSpecialOffers() {
    }

    @Test
    public void testSetSpecialOffers() {
    }

    @Test
    public void testGetName() {
        Client client = this.clientRepo.getClientByName("Иванов Иван");
        assertEquals(client.getName(), "Иванов Иван");
    }

    @Test
    public void testSetName() {
        Client client = this.clientRepo.getClientByName("Иванов Иван");
        client.setName("Беляев Константин");
        assertEquals(client.getName(), "Беляев Константин");
    }

    @Test
    public void testGetBids() {
        Client client = this.clientRepo.getClientById(1);
        ArrayList<Client> expected = new ArrayList<Client>();
        assertThat(client.getBids(), is(expected));
        Bid bid=new Bid(new Date(), 100000);
        client.addBid(bid);
        assertThat(client.getBids(), hasItem(bid));
        assertThat(client.getBids(), not(is(expected)));
    }
    @Test
    public void testGetBid() {
        Client client = this.clientRepo.getClientById(1);
        Bid bid=new Bid(new Date(), 100000);
        client.addBid(bid);
        assertEquals(client.getBid(0), bid);
        Bid bid_new=new Bid(new Date(), 100000);
        client.addBid(bid_new);
        assertEquals(client.getBid(1), bid_new);
    }
    
    @Test
    public void testSendBid() {
        Client client = this.clientRepo.getClientById(1);
        Manager manager = this.managerRepo.getManager(1);
        Bid bid=new Bid(new Date(), 100000);
        ArrayList<Bid> expected = new ArrayList<Bid>();
        expected.add(bid);
        client.sendBid(bid, manager);
        assertThat(client.getBids(), hasItem(bid));
        assertThat(client.getManagers(), hasItem(manager));
        client.sendBid(bid, manager);
        assertThat(client.getBids(), is(expected));
        assertThat(manager.getClients(), hasItem(client));
    }
   
}
