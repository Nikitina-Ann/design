/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;
import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.Bid;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ann
 */
public class FinancierTest {
    Bid bid;
    Financier financier;
    
    @Before
    public void setUp() {
        bid =new Bid(new Date(), 100000);
        financier = new Financier(1, "Петр Иванов");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generateResponseFinancier method, of class Financier.
     */
    @Test
    public void testGenerateResponseFinancier_2args() {
        Bid bid =new Bid(new Date(), 100000);
        Exception exception = null;
        try {
            financier.generateResponseFinancier(bid, true);
        } catch (IllegalArgumentException e) {
        exception = e;
        }
        assertNotNull(exception);
        financier.generateResponseFinancier(bid, false);
        assertFalse(bid.getResponseFinancier().getAnswer());
    }

    /**
     * Test of generateResponseFinancier method, of class Financier.
     */
    @Test
    public void testGenerateResponseFinancier_5args() {
        financier.generateResponseFinancier(bid, false);
        assertFalse(bid.getResponseFinancier().getAnswer());
        financier.generateResponseFinancier(bid, false, 10, 90);
        assertFalse(bid.getResponseFinancier().getAnswer());
        financier.generateResponseFinancier(bid, true, 10, 90);
        assertTrue(bid.getResponseFinancier().getAnswer());
        assertEquals(bid.getResponseFinancier().getPersent(), 10);
        assertEquals(bid.getResponseFinancier().getTime(), 90);        
        
    }
    
}
