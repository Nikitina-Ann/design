/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;

import com.mycompany.bank.businessLogic.Client;
import com.mycompany.bank.businessLogic.SpecialOffer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class ClientService {

    private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String dp_user = "user";
    private static final String db_pass = "pass";

    public static int createNewClient(String name, int revenue) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO clients (name, rating, revenue ) VALUES ( :name, 0, :revenue )";

        Connection connection = sql2o.open();
        int id = connection.createQuery(sql)
                .addParameter("name", name)
                .addParameter("revenue", revenue)
                .executeUpdate()
                .getKey(Integer.class);
        return id;
    }

    public static Client getClientById(int id) {

        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);

        String sql = "SELECT * FROM clients WHERE id = :id";

        Connection connection = sql2o.open();
        class DBClient {

            public int id;
            public String name;
            public int rating;
            public int revenue;
        }
        List<DBClient> list = connection.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetch(DBClient.class);
        if (list.isEmpty()) {
            return null;
        }
        DBClient DBclient = list.get(0);
        List<SpecialOffer> specialOffer = SpecialOfferService.getSpecialOfferByClientId(id);
        Client client = new Client(DBclient.id, DBclient.name, DBclient.rating, DBclient.revenue, specialOffer);
        return client;
    }

    public static List<Client> getAllClients() {

        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM clients";
        Connection connection = sql2o.open();
        List<DBClient> list = connection.createQuery(sql)
                .executeAndFetch(DBClient.class);
        if (list.isEmpty()) {
            return null;
        }
        List<Client> clients = new ArrayList<Client>();
        for (DBClient DBclient : list) {
            Client client = new Client(DBclient.id, DBclient.name, DBclient.rating, DBclient.revenue);
            clients.add(client);
        }
        return clients;
    }

    public static Client findClientByName(String name) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT id FROM clients WHERE name = :name";
        Connection connection = sql2o.open();
        List<DBClient> list = connection.createQuery(sql)
                .addParameter("name", name)
                .executeAndFetch(DBClient.class);
        if (list.isEmpty()) {
            return null;
        }
        DBClient DBclient = list.get(0);
        Client client = new Client(DBclient.id, DBclient.name, DBclient.rating, DBclient.revenue);
        return client;
    }

    class DBClient {

        public int id;
        public String name;
        public int rating;
        public int revenue;
    }
}
