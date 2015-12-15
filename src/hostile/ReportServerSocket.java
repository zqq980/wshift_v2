package hostile;

/* ReportServerSocket.java by Mark D. LaDue */

/* March 2, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/*  This Java Application sets up a simple ServerSocket to receive
     data from the Java applet DoMyWork.java */

import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;

class ReportServerSocket{

    public static void main(String args[]) {

        ServerSocket server;
        Socket socker;
        InputStream innerStream;
//      OutputStream outerStream;
        String home = new String("www.math.gatech.edu");
        int port = 9000;
        byte by[] = new byte[4096];
        int numberbytes;
        String reply;

        if (args.length != 1) {
          System.out.println("Command: java ReportServerSocket <port number>");
            return;
        }

        System.out.println("ReportServerSocket Session Starting");
        System.out.println("*Factor is the smallest prime factor of Integer*");
        port = Integer.parseInt(args[0]);

//    Create the ServerSocket
        try {
            server = new ServerSocket(port);
            }
        catch (IOException ioe) {
            System.out.println("Unable to open port " + port);
            return;
        }

//  Listen for anyone sending reults back to the applet
        while (true) {
            try {
                socker = server.accept();
                innerStream = socker.getInputStream();
            }
            catch (IOException ioe) {
                System.out.println("Accept failed at port " + port);
                return;
            }
            try {
                numberbytes = innerStream.read(by, 0, 4096);
            }
            catch (IOException ioe) {
                System.out.println("Read failed at port " + port);
                return;
            }
            reply = new String(by, 0, 0, numberbytes);
            System.out.println("Host Name / IP Address \t" + "Date" + 
                               "\t\t\t\t" + "Integer  \t" + "Factor");
            System.out.println(reply);

//  We could send a message back, but we won't right now
            try {
                socker.close();
            }
            catch (IOException ioe) {
                 System.out.println("Unable to close port " + port);
            }
        }
    }
}


