/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;

import com.mycompany.bank.businessLogic.Bid;
import com.mycompany.bank.businessLogic.RestrBid;
import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.Agreement;
import com.mycompany.bank.businessLogic.ResponseFinancier;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class RestrBidService {

    private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String dp_user = "user";
    private static final String db_pass = "pass";

    public static void createNewBid(Bid bid, String reason) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO restrBids (date, bid_id, doc, agreement) "
                + "VALUES (now(), :bid_id, :reason, false)";

        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("bid_id", bid.getId())
                .addParameter("reason", reason)
                .executeUpdate();
    }

    public static List<RestrBid> getBidByRoleId(int id, int role) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        Connection connection = sql2o.open();
        List<Bid> bids;
        if (role == 1) {
            bids = BidService.getBidByClientId(id);
        } else {
            bids = BidService.getBidByManagerId(id);
        }
        List<RestrBid> restrBids = new ArrayList<RestrBid>();
        if (bids == null) {
            return null;
        }
        for (Bid bid : bids) {
            String sql = "SELECT * FROM restrBids where bid_id=:bid_id";
            List<DBRestrBid> list = connection.createQuery(sql)
                    .addParameter("bid_id", bid.getId())
                    .executeAndFetch(DBRestrBid.class);
            if (list.isEmpty()) {
                break;
            }
            for (DBRestrBid dbRestrBid : list) {
                Client client = ClientService.getClientById(bid.getClient().getId());
                RestrBid restrBid;
                if (dbRestrBid.responseFinancier == null) {
                    restrBid = new RestrBid(bid, dbRestrBid.id, dbRestrBid.doc, dbRestrBid.date, client, dbRestrBid.agreement);
                } else {
                    restrBid = new RestrBid(bid, dbRestrBid.id, dbRestrBid.doc, dbRestrBid.date, client, dbRestrBid.persent, dbRestrBid.time, dbRestrBid.responseFinancier, dbRestrBid.responseClient, dbRestrBid.agreement);
                }
                restrBids.add(restrBid);
            }
        }
        return restrBids;
    }

    public static List<RestrBid> getBidByFinancierId(int id) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        Connection connection = sql2o.open();
        String sql = "SELECT * FROM restrBids where financier_id=:financier_id";
        List<DBRestrBid> list = connection.createQuery(sql)
                .addParameter("financier_id", id)
                .executeAndFetch(DBRestrBid.class);
        if (list.isEmpty()) {
            return null;
        }
        List<RestrBid> restrBids = new ArrayList<RestrBid>();
        for (DBRestrBid dbRestrBid : list) {
            Bid bid = BidService.getBidById(dbRestrBid.bid_id);
            Client client = ClientService.getClientById(bid.getClient().getId());
            RestrBid restrBid;
            if (dbRestrBid.financier_id == 0) {
                restrBid = new RestrBid(bid, dbRestrBid.id, dbRestrBid.doc, dbRestrBid.date, client, dbRestrBid.agreement);
            } else {
                restrBid = new RestrBid(bid, dbRestrBid.id, dbRestrBid.doc, dbRestrBid.date, client, dbRestrBid.persent, dbRestrBid.time, dbRestrBid.responseFinancier, dbRestrBid.responseClient, dbRestrBid.agreement);
            }
            restrBids.add(restrBid);
        }
        return restrBids;
    }

    public static void setResponseClient(RestrBid restrBid, Boolean responseClient) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE restrBids set responseClient=:responseClient  where id=:id";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("responseClient", responseClient)
                .addParameter("id", restrBid.getId())
                .executeUpdate();
    }

    public static void setAgreement(RestrBid restrBid) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE restrBids set agreement=true  where id=:bidId";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("bidId", restrBid.getId())
                .executeUpdate();
        ResponseFinancierService.setValue(restrBid.getBid().getId(), restrBid.getResponseFinancier().getPersent(), restrBid.getResponseFinancier().getTime());
    }

    public static void setFinancierId(RestrBid restrBid, String financierName) {
        int financierId = FinancierService.findFinancierByName(financierName);
        ResponseFinancier responceFinancier = ResponseFinancierService.createNewEmptyResponseFinancier(restrBid.getId(), financierId);
        restrBid.setResponseFinancier(responceFinancier);
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE restrBids set financier_id=:financierId  where id=:bidId";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("financierId", financierId)
                .addParameter("bidId", restrBid.getId())
                .executeUpdate();
    }

    public static void setResponseFinancier(RestrBid restrBid, Boolean responseFinancier, int persent, int time) {
        ResponseFinancier responceFinancier = new ResponseFinancier(responseFinancier, persent, time);
        restrBid.setResponseFinancier(responceFinancier);
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE restrBids set time=:time, responseFinancier=:responseFinancier,persent=:persent where id=:id";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("id", restrBid.getId())
                .addParameter("time", time)
                .addParameter("responseFinancier", responseFinancier)
                .addParameter("persent", persent)
                .executeUpdate();
    }

    class DBRestrBid {

        public int id;
        public Date date;
        public int persent;
        public int time;
        public int bid_id;
        public String doc;
        public Boolean responseFinancier;
        public Boolean responseClient;
        public Boolean agreement;
        public int financier_id;
    }
}
