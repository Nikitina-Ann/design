package com.mycompany.bank;
import com.mycompany.bank.gui.StartFrame;
import httpserver.HttpServer;
import javax.swing.*;

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