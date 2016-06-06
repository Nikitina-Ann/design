/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.fasade;

import com.mycompany.bank.businessLogic.Manager;
import com.mycompany.bank.db.ManagerService;

/**
 *
 * @author ann
 */
public class ManagerFasade {
    Manager manager;
    public ManagerFasade (int id){
        manager = ManagerService.findManagerById(id);
    }
    
    public String getName(){
        return manager.getName();
    }
    
}
