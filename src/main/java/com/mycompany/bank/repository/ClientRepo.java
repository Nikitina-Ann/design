/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.repository;
import com.mycompany.bank.businessLogic.Client;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ann
 */
public class ClientRepo {
    
  private List<Client> clients;

  public ClientRepo() {
    this.clients = new ArrayList<Client>();
    this.clients.add(new Client(1, "Иванов Иван",10,40000));
    this.clients.add(new Client(2, "Петров Василий",50,20000));
  }

   public Client getClientById(int id) {
    for (Client client: this.clients ) {
      if(client.getId() == id)
        return client;
    }
    return null;
  }
   
  public Client getClientByName(String name) {
    for (Client client: this.clients ) {
      if(client.getName().equals(name))
        return client;
    }
    return null;
  }
  
  public Client getClient (int index) {
      return this.clients.get(index);
  }
}
