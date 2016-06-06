package com.mycompany.bank;
import com.mycompany.bank.gui.StartFrame;
import com.mycompany.bank.service.HttpServer;

public class Main {
  public static void main(String[] args) {
    new StartFrame().setVisible(true);
    try {
      new Thread(new HttpServer()).start();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
 
  }
}