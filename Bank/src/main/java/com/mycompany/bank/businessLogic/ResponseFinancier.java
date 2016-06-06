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
public class ResponseFinancier {

    private Boolean answer;
    private int persent;
    private int time;

    public ResponseFinancier(Boolean answer) {    
        if (answer ==true )
            throw new IllegalArgumentException("Exception: ResponseFinancier");
        else
        this.answer = answer;
    }

    public ResponseFinancier() {
        this.answer = null;
    }

    public ResponseFinancier(Boolean answer, int persent, int time) {
        if (persent > 100 || persent < 0 || time < 0 || time > 100) 
            throw new IllegalArgumentException("Exception: ResponseFinancier");
         else {
            this.answer = answer;
            this.persent = persent;
            this.time = time;
        }
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public int getPersent() {
        return persent;
    }

    public void setPersent(int persent) {
        this.persent = persent;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void changeCreditPlan(int persent, int time) {
        this.persent = persent;
        this.time = time;
    }
}
