/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.businessLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ann
 */
public class RestrBid extends AllBid {

    private Bid bid;
    private String doc;
    private Boolean agreement;

    public RestrBid(Bid bid, int id, String doc, Date date, Client client, int persent, int time, Boolean answer, Boolean responseClient, Boolean agreement) {
        super(id, date, responseClient, new ResponseFinancier(answer, persent, time), client);
        this.doc = doc;
        this.bid = bid;
        this.agreement = agreement;
    }

    public RestrBid(Bid bid, int id, String doc, Date date, Client client, Boolean agreement) {
        super(id, date, client);
        this.doc = doc;
        this.bid = bid;
        this.agreement = agreement;
    }

    public String getDocumentation() {
        return doc;
    }

    public void setDocumentation(String doc) {
        this.doc = doc;
    }

    public Bid getBid() {
        return this.bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Boolean getAgreement() {
        return this.agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }

    public static String[][] showRestrBids(List<RestrBid> restrBids, Boolean manager) {
        if (restrBids == null) {
            return new String[][]{{null, null, null, null, null}};
        }
        List<Integer> index = getArrayForRestrBids(restrBids);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][5];
        try {
            for (int i = 0; i < index.size(); i++) {
                RestrBid restrBid = restrBids.get(index.get(i));
                array[i][0] = Integer.toString(restrBid.getBid().getAgreement().getResidualAmount());
                array[i][1] = Integer.toString(restrBid.getBid().getResponseFinancier().getPersent());
                array[i][2] = Integer.toString(restrBid.getBid().getResponseFinancier().getTime());
                array[i][3] = df.format(restrBid.getDate());
                if (manager) {
                    array[i][4] = restrBid.getClient().getName();
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return array;
    }

    public static String[][] showApprovedRestrBids(List<RestrBid> restrBids, Boolean manager) {
        if (restrBids == null) {
            return new String[][]{{null, null, null, null, null, null, null, null}};
        }
        List<Integer> index;
        if (manager) {
            index = getArrayForApprovedRestrBidsForManager(restrBids);
        } else {
            index = getArrayForApprovedRestrBidsForClient(restrBids);
        }
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][8];
        for (int i = 0; i < index.size(); i++) {
            RestrBid restrBid = restrBids.get(index.get(i));
            array[i][0] = Integer.toString(restrBid.getBid().getAgreement().getResidualAmount());
            array[i][1] = Integer.toString(restrBid.getBid().getResponseFinancier().getPersent());
            array[i][2] = Integer.toString(restrBid.getBid().getResponseFinancier().getTime());
            array[i][3] = df.format(restrBid.getDate());
            if (!restrBid.getResponseFinancier().getAnswer()) {
                array[i][4] = "-";
            } else {
                array[i][4] = "+";
                array[i][5] = Integer.toString(restrBid.getResponseFinancier().getTime());
                array[i][6] = Integer.toString(restrBid.getResponseFinancier().getPersent());
            }
            if (manager) {
                array[i][7] = "+";
            }
        }
        return array;
    }

    public static RestrBid getBidForApprovedRestrBidsForClient(List<RestrBid> restrBids, int id) {
        List<Integer> index = getArrayForApprovedRestrBidsForClient(restrBids);
        return restrBids.get(index.get(id));
    }

    public static RestrBid getBidForApprovedRestrBidsForManager(List<RestrBid> restrBids, int id) {
        List<Integer> index = getArrayForApprovedRestrBidsForManager(restrBids);
        return restrBids.get(index.get(id));
    }

    public static List<Integer> getArrayForApprovedRestrBidsForClient(List<RestrBid> restrBids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < restrBids.size(); i++) {
                if (restrBids.get(i).getResponseFinancier().getAnswer() != null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static List<Integer> getArrayForApprovedRestrBidsForManager(List<RestrBid> restrBids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < restrBids.size(); i++) {
                if (restrBids.get(i).getResponseFinancier().getAnswer() && restrBids.get(i).getResponseClient() && !restrBids.get(i).getAgreement()) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static RestrBid getBidForRestrBids(List<RestrBid> restrBids, int id) {
        List<Integer> index = getArrayForRestrBids(restrBids);
        return restrBids.get(index.get(id));
    }

    public static List<Integer> getArrayForRestrBids(List<RestrBid> restrBids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < restrBids.size(); i++) {
                if (restrBids.get(i).getResponseFinancier() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

    public static String[][] showRestrBidsFinancier(List<RestrBid> restrBids) {
        if (restrBids == null) {
            return new String[][]{{null, null, null, null, null, null, null, null}};
        }
        List<Integer> index = getArrayForRestrBidsFinancier(restrBids);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String[][] array = new String[index.size()][8];
        for (int i = 0; i < index.size(); i++) {
            RestrBid restrBid = restrBids.get(index.get(i));
            array[i][0] = restrBid.getBid().getClient().getName();
            array[i][1] = Integer.toString(restrBid.getBid().getClient().getRating());
            array[i][2] = Integer.toString(restrBid.getBid().getClient().getRevenue());
            array[i][3] = restrBid.getDocumentation();
            array[i][4] = Integer.toString(restrBid.getBid().getAgreement().getResidualAmount());
            array[i][5] = Integer.toString(restrBid.getBid().getResponseFinancier().getPersent());
            array[i][6] = Integer.toString(restrBid.getBid().getResponseFinancier().getTime());
            array[i][7] = df.format(restrBid.getDate());
        }
        return array;
    }

    public static RestrBid getBidForRestrBidsFinancier(List<RestrBid> restrBids, int id) {
        List<Integer> index = getArrayForRestrBidsFinancier(restrBids);
        return restrBids.get(index.get(id));
    }

    public static List<Integer> getArrayForRestrBidsFinancier(List<RestrBid> restrBids) {
        List<Integer> index = new ArrayList<Integer>();
        try {
            for (int i = 0; i < restrBids.size(); i++) {
                if (restrBids.get(i).getResponseFinancier().getAnswer() == null) {
                    index.add(i);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return index;
    }

}
