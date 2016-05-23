/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;
import org.junit.Test;
import  com.mycompany.bank.db.ClientService;
import com.mycompany.bank.businessLogic.Client;
import java.util.List;
/**
 *
 * @author ann
 */
public class ServiceTest {
    public ServiceTest() {
    }

  @Test
  public void testGetDoctorList() {
    ClientService clientService = new ClientService();
    Client list= clientService.findClientById(1);
    assert(true);
    }
}
