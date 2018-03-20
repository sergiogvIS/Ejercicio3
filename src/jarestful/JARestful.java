/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarestful;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author entrar
 */
public class JARestful {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        // TODO code application logic here
        String a = "http://services.groupkt.com/country/get/iso2code/IN";
        URL url = new URL(a);
        URLConnection tc = url.openConnection();
        
        BufferedReader in = new BufferedReader (new InputStreamReader(tc.getInputStream()));
        
        String rest = new String();
        String line;
        while((line= in.readLine()) != null){
            rest+=line;
        }
        System.out.println(rest);
        in.close();
        
        JSONObject obj = new JSONObject(rest);
        JSONObject res = obj.getJSONObject("RestResponse").getJSONObject("result");
        System.out.println("res: "+ res);
        String rl = res.toString();
        
        Gson gson = new Gson();
        
        Country country = (Country) gson.fromJson(rl,Country.class);
        System.out.println("Nombre del pais: " + country.getName());
        System.out.println("Código 1: " + country.getAlpha2_code());
        System.out.println("Código 2: " + country.getAlpha3_code());
        
        
    }
    
}
