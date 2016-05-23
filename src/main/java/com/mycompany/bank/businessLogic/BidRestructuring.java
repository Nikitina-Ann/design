/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

import java.util.Date;

/**
 *
 * @author ann
 */
public class BidRestructuring extends Bid {
    private DocRestructuring documentation;

    public BidRestructuring(DocRestructuring documentation, Date date, int sum) {
        super(date, sum);
        this.documentation = documentation;
    }

    public DocRestructuring getDocumentation() {
        return documentation;
    }

    public void setDocumentation(DocRestructuring documentation) {
        this.documentation = documentation;
    }
    
}
