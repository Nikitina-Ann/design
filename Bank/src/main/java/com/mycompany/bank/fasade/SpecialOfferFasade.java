/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.fasade;

import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.db.SpecialOfferService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class SpecialOfferFasade {

    List<SpecialOffer> specialOffers;
    List<SpecialOffer> approvedSpecialOffers;

    public SpecialOfferFasade() {
        specialOffers = SpecialOfferService.getAllSpecialOffer();
        approvedSpecialOffers = SpecialOfferService.getApprovedSpecialOffer();
    }

    public SpecialOfferFasade(Client client) {

        specialOffers = client.getSpecialOffers();
    }

    public String[][] showTable() {
        if (specialOffers == null) {
            return new String[][]{{null, null, null, null}};
        }
        List<Integer> index = getArrayForNotAgreement();
        String[][] array = new String[index.size()][4];
        for (int i = 0; i < index.size(); i++) {
            SpecialOffer specialOffer = specialOffers.get(index.get(i));
            array[i][0] = Integer.toString(specialOffer.getSum());
            array[i][1] = Integer.toString(specialOffer.getPersent());
            array[i][2] = Integer.toString(specialOffer.getTime());
            if (specialOffer.getResponseClient() == null) {
                array[i][3] = "";
            } else if (specialOffer.getResponseClient()) {
                array[i][3] = "согласие";
            } else {
                array[i][3] = "отказ";
            }
        }
        return array;
    }

    public String[][] showApprovedTable() {
        if (approvedSpecialOffers == null) {
            return new String[][]{{null, null, null, null}};
        }
        List<Integer> index = getArrayForApprovedSpecOffer();
        String[][] array = new String[index.size()][4];
        for (int i = 0; i < index.size(); i++) {
            SpecialOffer specialOffer = approvedSpecialOffers.get(index.get(i));
            array[i][0] = Integer.toString(specialOffer.getSum());
            array[i][1] = Integer.toString(specialOffer.getPersent());
            array[i][2] = Integer.toString(specialOffer.getTime());
            array[i][3] = specialOffer.getClient().getName();
        }
        return array;
    }

    public SpecialOffer getApprovedSpecOffer(int id) {
        List<Integer> index = getArrayForApprovedSpecOffer();
        return approvedSpecialOffers.get(index.get(id));
    }

    public List<Integer> getArrayForApprovedSpecOffer() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < approvedSpecialOffers.size(); i++) {
                if (approvedSpecialOffers.get(i).getResponseClient() == true) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }

    public SpecialOffer getNotAgreement(int id) {
        List<Integer> index = getArrayForNotAgreement();
        return specialOffers.get(index.get(id));
    }

    public List<Integer> getArrayForNotAgreement() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < specialOffers.size(); i++) {
                if (specialOffers.get(i).getAgreement() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }

    public String[][] showAgreements(Boolean manager) {
        if (specialOffers == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForAgreements();
        String[][] array = new String[index.size()][5];
        for (int i = 0; i < index.size(); i++) {
            SpecialOffer specialOffer = specialOffers.get(index.get(i));
            array[i][0] = Integer.toString(specialOffer.getSum());
            array[i][1] = Integer.toString(specialOffer.getTime());
            array[i][2] = Integer.toString(specialOffer.getPersent());
            array[i][3] = Integer.toString(specialOffer.getAgreement().getResidualAmount());
            if (manager) {
                array[i][4] = specialOffer.getClient().getName();
            }
        }
        return array;
    }

    public SpecialOffer getBidForAgreements(int id) {
        List<Integer> index = getArrayForAgreements();
        return specialOffers.get(index.get(id));
    }

    public List<Integer> getArrayForAgreements() {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < specialOffers.size(); i++) {
                if (specialOffers.get(i).getAgreement() != null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
        }
        return index;
    }

    public void setResponseClient(Client client, int selectIndex, Boolean answer) {
        SpecialOfferService.setResponseClient(client.getId(), getNotAgreement(selectIndex), answer);
    }

    public Boolean setClientId(int selectIndex, String clientName) {
        return SpecialOfferService.setClientId(specialOffers.get(selectIndex), clientName);
    }

    public void setAgreement(int selectIndex) {

        SpecialOfferService.setAgreement(getApprovedSpecOffer(selectIndex));
    }
}
