/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

/**
 *
 * @author ann
 */
public class Agreement {
    private Boolean extinguished;
    private int residualAmount;    

    public Agreement(Boolean extinguished, int residualAmount) {
        this.extinguished = extinguished;
        this.residualAmount = residualAmount;
    }
    public Agreement(int residualAmount) {
        this.extinguished = false;
        this.residualAmount = residualAmount;
    }

    public Boolean getExtinguished() {
        return extinguished;
    }

    public void setExtinguished(Boolean extinguished) {
        this.extinguished = extinguished;
    }

    public int getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(int residualAmount) {
        this.residualAmount = residualAmount;
    }
    public void refill (int sum){
        this.residualAmount = residualAmount - sum;
        if (this.residualAmount <= 0) {
            this.residualAmount = 0;
            this.extinguished = true;
        }
    }
}
