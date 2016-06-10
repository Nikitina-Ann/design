/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;

import com.mycompany.bank.businessLogic.Financier;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

/**
 *
 * @author ann
 */
public class FinancierService {

    private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String dp_user = "user";
    private static final String db_pass = "pass";

    public static int createNewFinancier(String name) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO financiers (name) VALUES ( :name)";

        Connection connection = sql2o.open();
        int id = connection.createQuery(sql)
                .addParameter("name", name)
                .executeUpdate()
                .getKey(Integer.class);
        return id;
    }

    public static Financier findFinancierById(int id) {

        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);

        String sql = "SELECT name FROM financiers WHERE id = :id";

        Connection connection = sql2o.open();
        List<String> list = connection.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetch(String.class);
        if (list.isEmpty()) {
            return null;
        }
        String name = list.get(0);
        Financier financier = new Financier(id, name);
        return financier;
    }

    public static int findFinancierByName(String name) {

        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);

        String sql = "SELECT id FROM financiers WHERE name = :name";

        Connection connection = sql2o.open();
        List<Integer> list = connection.createQuery(sql)
                .addParameter("name", name)
                .executeAndFetch(Integer.class);
        if (list.isEmpty()) {
            return -1;
        }
        return list.get(0);
    }

    public static List<Financier> getAllFinancier() {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM financiers";

        Connection connection = sql2o.open();
        List<Financier> list = connection.createQuery(sql)
                .executeAndFetch(Financier.class);
        return list;
    }
}
