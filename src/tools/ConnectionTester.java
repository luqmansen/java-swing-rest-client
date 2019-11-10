/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author binatangkesusahan
 */
public class ConnectionTester {
   Boolean internet;
   
   public ConnectionTester(){
       
   }
   
   public static Boolean testInet() {
      try {
         URL url = new URL("http://www.google.com");
         URLConnection connection = url.openConnection();
         connection.connect();
         System.out.println("Internet is connected");
         return true;
      } catch (MalformedURLException e) {
         System.out.println("Internet is not connected");
      } catch (IOException e) {
         System.out.println("Internet is not connected");
      }
      return false; 
    }
}
