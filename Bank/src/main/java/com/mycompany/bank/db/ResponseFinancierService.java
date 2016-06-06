/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;

import com.mycompany.bank.businessLogic.ResponseFinancier;
import com.mycompany.bank.businessLogic.Bid;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ann
 */
public class ResponseFinancierService {

    private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String dp_user = "user";
    private static final String db_pass = "pass";

    public static ResponseFinancier createNewEmptyResponseFinancier(int bid_id, int financier_id) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO responseFinancier (bid_id, financier_id) "
                + "VALUES ( :bid_id, :financier_id)";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("bid_id", bid_id)
                .addParameter("financier_id", financier_id)
                .executeUpdate();
        return new ResponseFinancier();
    }

    public static ResponseFinancier createNewResponseFinancier(int bid_id, int financier_id, Boolean answer, int persent, int time) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "INSERT INTO responseFinancier (bid_id, financier_id, answer, persent, time) "
                + "VALUES ( :bid_id, :financier_id, :answer,  :persent, :time)";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("bid_id", bid_id)
                .addParameter("financier_id", financier_id)
                .addParameter("answer", answer)
                .addParameter("persent", persent)
                .addParameter("time", time)
                .executeUpdate();
        return new ResponseFinancier(answer, persent, time);
    }

    public static void setResponseFinancier(Bid bid, int financier_id, Boolean answer, int persent, int time) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE responseFinancier set answer=:answer, persent=:persent, time=:time"
                + " where bid_id=:bid_id and financier_id=:financier_id";
        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("bid_id", bid.getId())
                .addParameter("financier_id", financier_id)
                .addParameter("answer", answer)
                .addParameter("persent", persent)
                .addParameter("time", time)
                .executeUpdate();
        bid.setResponseFinancier(new ResponseFinancier(answer, persent, time));
    }

    public static ResponseFinancier getResponseFinancierByBidId(int bid_id) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "SELECT * FROM responseFinancier WHERE bid_id = :bid_id";

        Connection connection = sql2o.open();
        List<DBResponseFinancier> list = connection.createQuery(sql)
                .addParameter("bid_id", bid_id)
                .executeAndFetch(DBResponseFinancier.class);

        if (list.isEmpty()) {
            return null;
        }
        DBResponseFinancier dbResponseFinancier = list.get(0);
        ResponseFinancier responseFinancier = new ResponseFinancier(dbResponseFinancier.answer, dbResponseFinancier.persent, dbResponseFinancier.time);
        return responseFinancier;
    }

    public static void setValue(int bid_id, int persent, int time) {
        Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
        String sql = "UPDATE responseFinancier set time=:time, persent=:persent WHERE bid_id = :bid_id";

        Connection connection = sql2o.open();
        connection.createQuery(sql)
                .addParameter("time", time)
                .addParameter("persent", persent)
                .addParameter("bid_id", bid_id)
                .executeUpdate();
    }

    class DBResponseFinancier {
        public int id;
        public int bid_id;
        public int financier_id;
        public Boolean answer;
        public int persent;
        public int time;
    }
}
