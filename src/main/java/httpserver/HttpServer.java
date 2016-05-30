/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import com.mycompany.bank.businessLogic.Manager;
import com.mycompany.bank.db.ManagerService;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author ann
 */
public class HttpServer implements Runnable {

    private ServerSocket ss;
    private boolean result;

    public HttpServer() throws Throwable {
        result = false;
        try {
            ss = new ServerSocket(8080);
            result = true;
        } catch (SocketException e) {
            result = false;
        }
    }

    public void run() {
        while (result) {
            try {
                Socket s = ss.accept();
                System.err.println("Client accepted");
                new Thread(new SocketProcessor(s)).start();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    private static class SocketProcessor implements Runnable {

        private Socket s;
        private InputStream is;
        private OutputStream os;

        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
        }

        public void run() {
            try {
                readInputHeaders();
                List<Manager> managers = ManagerService.getAllManagers();
                String resp = convertObjectToJson(managers);
                byte[] bytes = resp.getBytes("cp1251");
                String s = new String(bytes, "cp1251");
                writeResponse(s);
            } catch (Throwable t) {
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse(String s) throws Throwable {
            String response = "HTTP/1.1 200 OK\r\n"
                    + "Server: HospitalServer/2009-09-09\r\n"
                    + "Content-Type: text/json\r\n; charset=UTF-8"
                    + "Content-Length: " + s.length() + "\r\n"
                    + "Connection: close\r\n\r\n";
            String result = response + s;
            byte[] b = result.getBytes("cp1251");
            os.write(b);
            os.flush();
        }

        private void readInputHeaders() throws Throwable {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (true) {
                String s = br.readLine();
                if (s == null || s.trim().length() == 0) {
                    break;
                }
            }
        }

        String convertObjectToJson(Object object) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
