/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bank.service;
import com.mycompany.bank.businessLogic.Client;
import org.codehaus.jackson.*;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ann
 */
public class ClientService {

    private static String fileName = "src/main/resources/clients.json";

    public static String getJobClient(String clientName) {
        String retString = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonParser parser = new JsonFactory().createJsonParser(new File(fileName));
            TypeReference<List<JsonClient>>ref= new TypeReference<List<JsonClient>>() {};
            List<JsonClient> clients = mapper.readValue(parser, ref);
            for (JsonClient jsonClient : clients) {
                if (clientName.equals(jsonClient.name)) {
                    retString = jsonClient.job;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retString;
    }

   public static class JsonClient {
  public String name;
  public String job;
}

}
