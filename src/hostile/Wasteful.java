package hostile;
/* Wasteful.java by Mark D. LaDue */

/* February 17, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This  Java Applet is intended to bring your Java-aware
   browser to its knees by hogging the CPU.  Note that you can
   suspend its effects because it has a mouseDown() method.  */

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Wasteful extends java.applet.Applet implements Runnable {
    Font wordFont = new Font("TimesRoman", Font.PLAIN, 12);
    Thread wasteResources = null;
    Image offscreenImage;
//    Graphics offscreenGraphics;
    boolean threadStopped = false;
    StringBuffer holdResults = new StringBuffer(0);
    long n = 0;
    int delay;

    public void init() {
    setBackground(Color.blue);
//    offscreenImage = createImage(this.size().width, this.size().height);
//    offscreenGraphics = offscreenImage.getGraphics();
    String str = getParameter("wait");
    if (str == null)
        delay = 0;
    else delay = (1000)*(Integer.parseInt(str));
    }

    public void start() {
        if (wasteResources == null) {
        wasteResources = new Thread(this);
        wasteResources.setPriority(Thread.MAX_PRIORITY);
        wasteResources.start();
        }
    }

    public void stop() {} //doesn't stop anything


    public void run() {
        try {Thread.sleep(delay);}
        catch(InterruptedException e) {}
        while (n >= 0) {
        holdResults.append(fibonacci(n));
        repaint();
        n++;
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {

//     offscreenGraphics.drawRect(0, 0, this.size().width, this.size().height);
//     offscreenGraphics.setColor(Color.blue);
//     offscreenGraphics.drawString(holdResults.toString(), 10, 10);
//     g.drawImage(offscreenImage, 0, 0, this);
    }

    public long fibonacci(long k) {
        if (k == 0 || k == 1)
            return k;
        else
            return fibonacci(k - 1) + fibonacci(k - 2);
    }
}
