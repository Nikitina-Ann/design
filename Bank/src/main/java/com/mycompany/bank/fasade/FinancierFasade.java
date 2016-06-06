/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.fasade;

import com.mycompany.bank.businessLogic.Financier;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.db.ClientService;
import com.mycompany.bank.db.FinancierService;
import java.util.List;
/**
 *
 * @author ann
 */
public class FinancierFasade {
    Financier financier;
    public FinancierFasade (int id){
        financier = FinancierService.findFinancierById(id);
    }
    
    public String getName(){
        return financier.getName();
    }
    
    
     public String getJobClient(String clientName) throws Exception {
    if (financier == null)
      throw new Exception("Финансит не задан");
    return financier.getJobClient(clientName);
 }
     
      public String[][] showAllClients() {
        List<Client> clients = ClientService.getAllClients();
         String[][] array = new String[clients.size()][2];
            try{
            for (int i = 0; i < clients.size(); i++) {
                array[i][0] = clients.get(i).getName();
                array[i][1] = getJobClient( clients.get(i).getName());
            }
        } catch (Exception ex){
            return new String[][]{{null, null}};
        }
        return array;
    }

}
