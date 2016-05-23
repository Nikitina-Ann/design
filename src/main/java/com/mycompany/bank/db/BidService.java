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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ann
 */
public class BidService {
  private static AgreementService agreementService;
  private static ResponseFinancierService responseFinancierService;
  
  private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
  private static final String dp_user ="user";
  private static final String db_pass = "pass";
  public BidService() {
        this.agreementService = new AgreementService();
        this.responseFinancierService = new ResponseFinancierService();
  }

  public static void createNewBid(int  client_id,  int sum) {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "INSERT INTO bids (client_id, manager_id, financier_id, date, responseClient, sum, agreement_id) "
            + "VALUES ( :client_id,  :manager_id, :financier_id, now(), false, :sum, NULL)";

    Connection connection = sql2o.open();
    connection.createQuery(sql)
        .addParameter("client_id", client_id)
        .addParameter("manager_id", ManagerService.getRandomManager())
        .addParameter("financier_id", FinancierService.getRandomFinancier())
        .addParameter("sum", sum)
        .executeUpdate();
  }
    
  public static List<Bid> getNewBidsByFinancierId(int financierId) {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "SELECT * FROM bids WHERE financier_id = :financierId";
       class DBBid{
      public int id;
      public int client_id;
      public int manager_id;
      public int financier_id;
      public Date date;
      public Boolean responseClient;
      public int sum;
      public int agreement_id;
    }
    Connection connection = sql2o.open();
    List<DBBid> list = connection.createQuery(sql)
        .addParameter("financierId", financierId)
        .executeAndFetch(DBBid.class);

    if (list.isEmpty())
      return null;
    
    List<Bid> bids = new ArrayList<Bid>();
    for (DBBid dbBid : list) {
        Agreement agreement=agreementService.getAgreementById(dbBid.agreement_id);
        ResponseFinancier responseFinancier = responseFinancierService.getResponseFinancierByBidId(dbBid.id);
        Bid bid = new Bid(dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement);
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

    if (list.isEmpty())
      return null;
    
    List<Bid> bids = new ArrayList<Bid>();
    for (DBBid dbBid : list) {
        Agreement agreement=agreementService.getAgreementById(dbBid.agreement_id);
        ResponseFinancier responseFinancier = responseFinancierService.getResponseFinancierByBidId(dbBid.id);
        Bid bid = new Bid(dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement);
        bids.add(bid);
      }
    return bids;
  }
   
   public static void setBidForClient(List<Client> clients) {
    for (Client client : clients) {
    int id = client.getId();
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "SELECT * FROM bids WHERE client_id = :clientId";
    
    
    Connection connection = sql2o.open();
    List<DBBid> list = connection.createQuery(sql)
        .addParameter("clientId", id)
        .executeAndFetch(DBBid.class);

    if (list.isEmpty())
      return ;
    
    List<Bid> bids = new ArrayList<Bid>();
    for (DBBid dbBid : list) {
        Agreement agreement=agreementService.getAgreementById(dbBid.agreement_id);
        ResponseFinancier responseFinancier = responseFinancierService.getResponseFinancierByBidId(dbBid.id);
        Bid bid = new Bid(dbBid.date, dbBid.responseClient, dbBid.sum, responseFinancier, agreement);
        bids.add(bid);
      }
    client.addBidArray(bids);
    }
   }
    
   public static int getId(int clientId, int financierId, int sum) {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "SELECT id FROM bids WHERE client_id = :clientId and financier_id=:financierId and sum =:sum ";
    Connection connection = sql2o.open();
    List<Integer> list = connection.createQuery(sql)
        .addParameter("clientId", clientId)
        .addParameter("financierId", financierId)
        .addParameter("sum", sum)
        .executeAndFetch(Integer.class);
    
    int id= list.get(0);
    return id;
}
   class DBBid{
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