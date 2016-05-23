/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;
import com.mycompany.bank.businessLogic.Agreement;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ann
 */
public class AgreementService {
  private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
  private static final String dp_user ="user";
  private static final String db_pass = "pass";

    public static void createNewAgreement( Boolean extinguished, int residualAmount ) {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "INSERT INTO agreements (extinguished, residualAmount) "
            + "VALUES ( :extinguished, :residualAmount)";

    Connection connection = sql2o.open();
    connection.createQuery(sql)
        .addParameter("extinguished", extinguished)
        .addParameter("residualAmount", residualAmount)
        .executeUpdate();
  }
    
  public static Agreement getAgreementById(int id) {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "SELECT * FROM agreements WHERE id = :id";
    
    class DBAgreement{
      public int id;
      public Boolean extinguished;
      public int residualAmount;
    }
    Connection connection = sql2o.open();
    List<DBAgreement> list = connection.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(DBAgreement.class);

    if (list.isEmpty())
      return null;
    DBAgreement dbAgreement = list.get(0);
    Agreement agreement = new Agreement(dbAgreement.extinguished, dbAgreement.residualAmount);
    return agreement;
  }
}