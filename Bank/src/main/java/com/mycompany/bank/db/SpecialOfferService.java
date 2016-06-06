/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;

import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.SpecialOffer;
import com.mycompany.bank.businessLogic.Agreement;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 *
 * @author ann
 */
public class SpecialOfferService {

    private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String dp_user = "user";
    private static final String db_pass = "pass";

    public static SpecialOffer createNewSpecialOffer(int sum, int persent, int time) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO specialoffers (sum,persent, time)  "
                + "VALUES ( :sum, :persent, :time)";

        Connection connection = sql2o.open();
        int id = connection.createQuery(sql)
                .addParameter("sum", sum)
                .addParameter("persent", persent)
                .addParameter("time", time)
                .executeUpdate()
                .getKey(Integer.class);
        return new SpecialOffer(sum, persent, time, id);
    }

    public static List<SpecialOffer> getAllSpecialOffer() {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM specialoffers";
        Connection connection = sql2o.open();
        List<DBSpecialOffer> list = connection.createQuery(sql)
                .executeAndFetch(DBSpecialOffer.class);

        if (list.isEmpty()) {
            return null;
        }
        List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
        for (DBSpecialOffer dbspecialOffer : list) {
            SpecialOffer specialOffer = new SpecialOffer(dbspecialOffer.sum, dbspecialOffer.persent, dbspecialOffer.time, dbspecialOffer.id);
            specialOffers.add(specialOffer);
        }
        return specialOffers;
    }

    public static List<SpecialOffer> getApprovedSpecialOffer() {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM specialoffers";
        class DBClient_Response {

            public int client_id;
            public Boolean responseClient;
        }
        Connection connection = sql2o.open();
        List<DBSpecialOffer> list = connection.createQuery(sql)
                .executeAndFetch(DBSpecialOffer.class);

        if (list.isEmpty()) {
            return null;
        }
        List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
        for (DBSpecialOffer dbspecialOffer : list) {
            SpecialOffer specialOffer;
            sql = "SELECT client_id, responseClient FROM client_offer WHERE responseClient=true and offer_id= :offer_id";
            List<DBClient_Response> listClientResp = connection.createQuery(sql)
                    .addParameter("offer_id", dbspecialOffer.id)
                    .executeAndFetch(DBClient_Response.class);
            if (listClientResp.isEmpty()) {
                specialOffer = new SpecialOffer(dbspecialOffer.sum, dbspecialOffer.persent, dbspecialOffer.time, dbspecialOffer.id);
                specialOffers.add(specialOffer);
            } else {
                for (DBClient_Response client_response : listClientResp) {
                    Client client = ClientService.getClientById(client_response.client_id);
                    specialOffer = new SpecialOffer(dbspecialOffer.sum, dbspecialOffer.persent, dbspecialOffer.time, dbspecialOffer.id, client_response.responseClient, client);
                    specialOffers.add(specialOffer);
                }
            }
        }
        return specialOffers;
    }

    public static void setResponseClient(int clientId, SpecialOffer specialOffer, Boolean responseClient) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        specialOffer.setResponseClient(responseClient);
        String sql = "UPDATE client_offer set responseClient=:responseClient  "
                + "where offer_id = :offer_id and client_id =:client_id";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("responseClient", responseClient)
                .addParameter("offer_id", specialOffer.getId())
                .addParameter("client_id", clientId)
                .executeUpdate();
    }

    public static Boolean setClientId(SpecialOffer specialOffer, String clientName) {
        Client client = ClientService.findClientByName(clientName);
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        client.addSpecialOffer(specialOffer);
        String sql = "SELECT id FROM client_offer WHERE client_id= :client_id and offer_id= :offer_id";
        Connection connection = sql2o.open();
        List<Integer> listSpecOff = connection.createQuery(sql)
                .addParameter("client_id", client.getId())
                .addParameter("offer_id", specialOffer.getId())
                .executeAndFetch(Integer.class);
        if (!listSpecOff.isEmpty()) {
            return false;
        }

        sql = "INSERT INTO client_offer (client_id, offer_id)"
                + "VALUES ( :client_id, :offer_id)";

        connection.createQuery(sql)
                .addParameter("client_id", client.getId())
                .addParameter("offer_id", specialOffer.getId())
                .executeUpdate();
        return true;
    }

    public static List<SpecialOffer> getSpecialOfferByClientId(int client_id) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT offer_id, responseClient, agreement_id FROM client_offer WHERE client_id = :client_id";
        Connection connection = sql2o.open();
        List<DBClient_Offer> list = connection.createQuery(sql)
                .addParameter("client_id", client_id)
                .executeAndFetch(DBClient_Offer.class);
        if (list.isEmpty()) {
            return null;
        }
        List<SpecialOffer> specialOffers = new ArrayList<SpecialOffer>();
        for (DBClient_Offer offer_client : list) {
            sql = "SELECT * FROM specialoffers WHERE id = :id";
            connection = sql2o.open();
            List<DBSpecialOffer> listSpecOff = connection.createQuery(sql)
                    .addParameter("id", offer_client.offer_id)
                    .executeAndFetch(DBSpecialOffer.class);
            if (listSpecOff.isEmpty()) {
                return null;
            }
            DBSpecialOffer dbspecialOffer = listSpecOff.get(0);
            Agreement agreement = AgreementService.getAgreementById(offer_client.agreement_id);
            SpecialOffer specialOffer = new SpecialOffer(dbspecialOffer.sum, dbspecialOffer.persent, dbspecialOffer.time, dbspecialOffer.id, offer_client.responseClient, agreement);
            specialOffers.add(specialOffer);
        }
        return specialOffers;
    }

    public static void setAgreement(SpecialOffer specialOffer) {
        int agreement_id = AgreementService.createNewAgreement(specialOffer.getSum());
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE client_offer set agreement_id=:agreement_id  where offer_id=:offer_id";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("agreement_id", agreement_id)
                .addParameter("offer_id", specialOffer.getId())
                .executeUpdate();
    }

    class DBSpecialOffer {

        public int id;
        public int sum;
        public int persent;
        public int time;
    }

    class DBClient_Offer {

        public int offer_id;
        public Boolean responseClient;
        public int agreement_id;

    }
}
