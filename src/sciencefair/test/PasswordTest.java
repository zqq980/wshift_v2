/**
 * version 1.00 1999-08-27
 * author Cay Horstmann
 */

package sciencefair.test;

import java.io.*;
import java.net.*;
import java.util.*;

import sciencefair.util.Base64OutputStream;



public class PasswordTest
{

   private String urlName;
   private String username;
   private String password;
   private URL url;

   public PasswordTest(String urlName, String username, String password) {

      this.urlName = urlName;
      this.username = username;
      this.password = password;

      urlTest(500);
      //urlTest_2(100);

     //urlConTest();

      //print();

   }

   public void urlTest(int  nclick) {

                    try {

      url = new URL(urlName);

      for(int iaa=0; iaa < nclick; iaa++) {

         System.out.println("==================");
         System.out.println("iaa= " + iaa);
         System.out.println("==================");

         InputStream uin = url.openStream();
         BufferedReader in = new BufferedReader(new InputStreamReader(uin));

         String line;
         int n = 1;

         //while ((line = in.readLine()) != null)  {
         while ((line = in.readLine()) != null && n <= 1) {
            System.out.println(line);
            n++;
         }

         if (line != null) System.out.println(". . .");

      }         //for(int iaa=0; iaa<10; iaa++) {

                  }
                  catch (IOException exception) {
                     System.out.println("Error: " + exception);
                  }

   }

   public void urlTest_2(int  nclick) {

                    try {

      url = new URL(urlName);

      for(int iaa=0; iaa < nclick; iaa++) {

         System.out.println("==================");
         System.out.println("iaa= " + iaa);
         System.out.println("==================");

         URLConnection connection = url.openConnection();

         connection.connect();

         BufferedReader in = new BufferedReader(new
                  InputStreamReader(connection.getInputStream()));

         // print contents

         String line;
         int n = 1;

         //while ((line = in.readLine()) != null)  {
         while ((line = in.readLine()) != null && n <= 1) {
            System.out.println(line);
            n++;
         }

         if (line != null) System.out.println(". . .");

       }         //for(int iaa=0; iaa<10; iaa++) {

                  }
                  catch (IOException exception) {
                     System.out.println("Error: " + exception);
                  }

   }

   public void urlConTest() {

         try {

      url = new URL(urlName);

      URLConnection connection = url.openConnection();

      if(username != null && password != null) {
         String input = username + ":" + password;
         String encoding = base64Encode(input);

         connection.setRequestProperty("Authorization", "Basic " + encoding);
      }

      connection.connect();

      // print header fields

      int n = 1;
      String key;

      System.out.println();
      System.out.println("---------- key ----------");

      while ((key = connection.getHeaderFieldKey(n)) != null) {
         String value = connection.getHeaderField(n);
         System.out.println(key + ": " + value);
         n++;
      }

         // print convenience functions

         System.out.println("----------");

         System.out.println("getContentType: " + connection.getContentType());
         System.out.println("getContentLength: " + connection.getContentLength());
         System.out.println("getContentEncoding: " + connection.getContentEncoding());
         System.out.println("getDate: " + connection.getDate());
         System.out.println("getExpiration: " + connection.getExpiration());
         System.out.println("getLastModifed: " + connection.getLastModified());

         System.out.println("----------");


         BufferedReader in = new BufferedReader(new
            InputStreamReader(connection.getInputStream()));

         // print first ten lines of contents

         String line;
         n = 1;

         while ((line = in.readLine()) != null)
         //while ((line = in.readLine()) != null && n <= 1)
         {  System.out.println(line);
            n++;
         }

         if (line != null) System.out.println(". . .");

            } catch (IOException exception) {
               System.out.println("Error: " + exception);
            }


   }

   //* +++++++++++++++++++++ *//
   //*
   //* +++++++++++++++++++++ *//

   public void print() {


   }

   //* +++++++++++++++++++++ *//
   //*
   //* +++++++++++++++++++++ *//

   public static String base64Encode(String s) {

      ByteArrayOutputStream bOut
         = new ByteArrayOutputStream();
      Base64OutputStream out = new Base64OutputStream(bOut);

      try
      {  out.write(s.getBytes());
         out.flush();

      } catch (IOException exception) {
      }

      return bOut.toString();
   }


   //* +++++++++++++++++++++++++++++++++++++++++++++++++++ *//
   //*
   //* +++++++++++++++++++++++++++++++++++++++++++++++++++ *//

   public static void main(String[] args) {

      String urlName = new String();
      String username = new String();
      String password = new String();

      if (args.length > 0)  {
         urlName = args[0];
      } else {
         //urlName = "http://java.sun.com";
         //urlName = "http://www.webjb.org/webjb/sviewtopic_sanxian.php?topic=210461&select=&forum=1";
         //urlName = "http://www.webjb.org/webjb/sviewtopic_sanxian.php?topic=211344&select=&forum=1";
         //urlName = "http://www.webjb.org/webjb/sviewtopic_sanxian.php?topic=214213&select=&forum=1";
         //urlName = "http://www.webjb.org/webjb/sviewtopic_sanxian.php?topic=257513&select=&forum=1";
         urlName = "http://www.webjb.org/webjb/sviewtopic_sanxian.php?topic=308206&select=&forum=1";
      }

      // set username, password if specified on command line

      if (args.length > 2) {
         username = args[1];
         password = args[2];
      }

      PasswordTest urlConnectionTest =  new PasswordTest(urlName, username, password);
   }


}


