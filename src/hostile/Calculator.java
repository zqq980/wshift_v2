package hostile;
/*  Calculator.java by Mark D. LaDue */

/*  March 2, 1996 */

/*  Copyright (c) 1996 Mark D. LaDue
    You may study, use, modify, and distribute this example for any purpose.
    This example is provided WITHOUT WARRANTY either expressed or implied.  */

/*  This simple class just calls the class that does all the work */

import hostile.DoMyWork;

import java.io.*;
import java.net.*;

public class Calculator extends java.applet.Applet implements Runnable {

//  The class that actually does the work
    public GetFactor doWork;

/*  As usual, we won't stop anything */

    public void stop() {}


/*  Starts the factoring by trial division */

    public void run() {
        doWork = new GetFactor();
    }
}
/*  This class takes a given long integer and tries to factor it
    by trial division.  Of course other alogorithms could be used
    instead, and you're not limited to such simple schemes. */


class GetFactor extends DoMyWork {

//  The quantities that we'll be working with
    long myNumber = DoMyWork.theNumber; 
    int myPort = DoMyWork.thePort;
    String myHome = DoMyWork.theHome;
    long factor;
    long hopeful;
    Report sendIt = null;
    Long T = null;
    Long L = null;

//  Tells whether or not factoring was successful 
    boolean success;

/*  Start factoring by trial division */

    GetFactor() {
        long maxfactor = (long) java.lang.Math.sqrt(myNumber) + 1;
        factor = 3L;
        hopeful = 0L;
        success = false;

        hopeful = myNumber % 2;
        if (hopeful == 0) {
            success = true;
            factor = 2;
        }
        else {
            success = false;
            factor = 3;
            while (success == false &&
                    factor <  maxfactor) {
                hopeful = myNumber % factor;
                if (hopeful == 0) {success = true;}
                factor += 2;
             }
        }
        if (success == false) {factor = myNumber;}
        else {
            if (factor > 2) {factor -= 2;}
        }
        T = new Long(myNumber);
        L = new Long(factor);
        String teststr = T.toString();
        String factorstr = L.toString();
        sendIt = new Report(myHome, myPort);
        sendIt.communicate(teststr, factorstr);
    }
}
