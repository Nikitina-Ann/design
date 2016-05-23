/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.repository;

import com.mycompany.bank.businessLogic.Financier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class FinancierRepo {
    private List<Financier> financiers;

  public FinancierRepo() {
    this.financiers = new ArrayList<Financier>();
    this.financiers.add(new Financier(1, "Калинин Дмитрий"));
    this.financiers.add(new Financier(2, "Решетов Владимир"));
  }
  public Financier getFinancierById(int id) {
    for (Financier financier: this.financiers ) {     
      if(financier.getId()== id)
        return financier;
    }
    return null;
  }
  
  public Financier getFinancier (int index) {
      return this.financiers.get(index);
  }
}
