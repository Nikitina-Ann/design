/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.db;
import com.mycompany.bank.businessLogic.Manager;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author ann
 */
public class ManagerService {
  private static final String db_url = "jdbc:mysql://localhost:3306/bank?useSSL=false&serverTimezone=Europe/Moscow";
  private static final String dp_user ="user";
  private static final String db_pass = "pass";

  
  public static int createNewManager(String name) {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "INSERT INTO managers (name) VALUES ( :name)";

    Connection connection = sql2o.open();
    int id = connection.createQuery(sql)
        .addParameter("name", name)
        .executeUpdate()
        .getKey(Integer.class);
    return id;
  }
  
  public static int getRandomManager() {
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String sql = "SELECT id FROM managers ";

    Connection connection = sql2o.open();
     List<Integer> list = connection.createQuery(sql)
        .executeAndFetch(Integer.class);
      int index = new Random().nextInt(list.size());
      return list.get(index);
  }
   public static Manager findManagerById(int id) {
       
    Sql2o sql2o = new Sql2o(db_url, dp_user, db_pass);
    String  sql = "SELECT name FROM managers WHERE id = :id";

    Connection connection = sql2o.open();
    List<String> list = connection.createQuery(sql)
        .addParameter("id",id)
        .executeAndFetch(String.class);
    if(list.isEmpty()) 
      return null;
    String name = list.get(0);
    
    Manager manager = new Manager(id, name);
    return manager;
  }
}