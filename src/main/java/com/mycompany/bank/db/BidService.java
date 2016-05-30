/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;

import com.mycompany.bank.businessLogic.Bid;
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
public class BidService {

    private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String dp_user = "user";
    private static final String db_pass = "pass";

    public static void createNewBid(int client_id, int sum) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO bids (client_id, manager_id, financier_id, date, responseClient, sum, agreement_id) "
                + "VALUES ( :client_id,  :manager_id, NULL, now(), false, :sum, NULL)";

        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("client_id", client_id)
                .addParameter("manager_id", ManagerService.getRandomManager())
                .addParameter("sum", sum)
                .executeUpdate();
    }

    public static Bid getBidById(int id) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM bids WHERE id = :id";

        Connection connection = sql2o.open();
        List<DBBid> list = connection.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetch(DBBid.class);

        if (list.isEmpty()) {
            return null;
        }

        List<Bid> bids = new ArrayList<Bid>();
        for (DBBid dbBid : list) {
            Agreement agreement = AgreementService.getAgreementById(dbBid.agreement_id);
            ResponseFinancier responseFinancier = ResponseFinancierService.getResponseFinancierByBidId(dbBid.id);
            Client client = ClientService.getClientById(dbBid.client_id);
            Bid bid = new Bid(dbBid.id, dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement, client);
            bids.add(bid);
        }
        return bids.get(0);
    }

    public static List<Bid> getBidByFinancierId(int financierId) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM bids WHERE financier_id = :financierId";
        Connection connection = sql2o.open();
        List<DBBid> list = connection.createQuery(sql)
                .addParameter("financierId", financierId)
                .executeAndFetch(DBBid.class);

        if (list.isEmpty()) {
            return null;
        }
        List<Bid> bids = new ArrayList<Bid>();
        for (DBBid dbBid : list) {
            Agreement agreement = AgreementService.getAgreementById(dbBid.agreement_id);
            ResponseFinancier responseFinancier = ResponseFinancierService.getResponseFinancierByBidId(dbBid.id);
            Client client = ClientService.getClientById(dbBid.client_id);
            Bid bid = new Bid(dbBid.id, dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement, client);
            bids.add(bid);
        }
        return bids;
    }

    public static List<Bid> getBidByClientId(int clientId) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM bids WHERE client_id = :clientId";

        Connection connection = sql2o.open();
        List<DBBid> list = connection.createQuery(sql)
                .addParameter("clientId", clientId)
                .executeAndFetch(DBBid.class);

        if (list.isEmpty()) {
            return null;
        }

        List<Bid> bids = new ArrayList<Bid>();
        for (DBBid dbBid : list) {
            Agreement agreement = AgreementService.getAgreementById(dbBid.agreement_id);
            ResponseFinancier responseFinancier = ResponseFinancierService.getResponseFinancierByBidId(dbBid.id);
            Client client = ClientService.getClientById(dbBid.client_id);
            Bid bid = new Bid(dbBid.id, dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement, client);
            bids.add(bid);
        }
        return bids;
    }

    public static List<Bid> getBidByManagerId(int manager_id) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM bids WHERE manager_id = :manager_id";

        Connection connection = sql2o.open();
        List<DBBid> list = connection.createQuery(sql)
                .addParameter("manager_id", manager_id)
                .executeAndFetch(DBBid.class);

        if (list.isEmpty()) {
            return null;
        }

        List<Bid> bids = new ArrayList<Bid>();
        for (DBBid dbBid : list) {
            Agreement agreement = AgreementService.getAgreementById(dbBid.agreement_id);
            ResponseFinancier responseFinancier = ResponseFinancierService.getResponseFinancierByBidId(dbBid.id);
            Client client = ClientService.getClientById(dbBid.client_id);
            Bid bid = new Bid(dbBid.id, dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement, client);
            bids.add(bid);
        }
        return bids;
    }

    public static void setFinancierId(Bid bid, String financierName) {
        int financierId = FinancierService.findFinancierByName(financierName);
        ResponseFinancier responceFinancier = ResponseFinancierService.createNewEmptyResponseFinancier(bid.getId(), financierId);
        bid.setResponseFinancier(responceFinancier);
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE bids set financier_id=:financierId  where id=:bidId";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("financierId", financierId)
                .addParameter("bidId", bid.getId())
                .executeUpdate();
    }

    public static void setResponseClient(Bid bid, Boolean responseClient) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE bids set responseClient=:responseClient  where id=:bidId";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("responseClient", responseClient)
                .addParameter("bidId", bid.getId())
                .executeUpdate();
    }

    public static void setAgreement(Bid bid) {
        int agreement_id = AgreementService.createNewAgreement(bid.getSum());
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE bids set agreement_id=:agreement_id  where id=:bidId";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("agreement_id", agreement_id)
                .addParameter("bidId", bid.getId())
                .executeUpdate();
    }

    class DBBid {
        public int id;
        public int client_id;
        public int manager_id;
        public int financier_id;
        public Date date;
        public Boolean responseClient;
        public int sum;
        public int agreement_id;
    }
}
