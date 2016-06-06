/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.repository;

import com.mycompany.bank.businessLogic.Manager;
import java.util.ArrayList;
import java.util.List;
import  java.util.Random;

/**
 *
 * @author ann
 */
public class ManagerRepo {
    
  private List<Manager> managers;

  public ManagerRepo() {
    this.managers = new ArrayList<Manager>();
    this.managers.add(new Manager(1, "Иванов Петр"));
    this.managers.add(new Manager(2, "Карпов Владимир"));
  }
  public Manager getManagerById(int id) {
    for (Manager manager: this.managers ) {     
      if(manager.getId()== id)
        return manager;
    }
    return null;
  }
  
  public Manager getManagerByName(String name) {
    for (Manager manager: this.managers ) {     
      if(manager.getName().equals(name))
        return manager;
    }
    return null;
  }
  
  public Manager getManager (int index) {
      return this.managers.get(index);
  }
    public Manager getRandomManager (){
      Random r = new Random(System.currentTimeMillis());
      return managers.get(r.nextInt(managers.size()));
  }
}
    
  
