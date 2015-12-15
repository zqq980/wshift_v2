package hostile;
/* Mutator1.java by Mark D. LaDue */

/* February 24, 1997 */

/* Copyright (c) 1997 Mark D. LaDue
   You may study, use, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java application demonstrates how a class can induce mutations in
   itself, remain viable, and take actions based upon its history.  This
   particular class keeps track of the number of times that it's been run,
   reports and updates that number each time it's run, and then deletes
   itself on the sixth attempted run.  Thus a Java trojan could easily
   appear benign, even useful, for the first several runs and then
   suddenly spring a nasty surprise on the hapless user.  Don't count
   on being able to decompile it to source code to see what it's going to
   do - defending it with the hostile application HoseMocha.java could
   eliminiate that possibility.

   Since the original Mutator.class was specific to Version 1.02 of Java,
   Mutator.java had to be updated to work with Version 1.1.  While the
   class file format remains the same, individual class files may change
   from one release to the next.  Thus any class file hacking programs
   which rely only on the class file format should require no changes,
   unless the class file format changes. */

import java.io.*;

class Mutator1 {
    public static void main(String[] argv) {
        String whoami = "Mutator1"; // What's the name of this application?
        int num_runs10 = 10; // The number of previous runs plus 10
        int fpointer = 0; // Where are we in the class file?
// How on earth do I use this thing?
        if (argv.length != 0) {
            System.out.println("Try \"java " + whoami + "\".");
            System.exit(1);
        }
// If Mutator1.class isn't writeable, then forget the whole deal.
        File testit = new File(whoami + ".class");
        if (!(testit.canWrite())) {
            System.out.println(whoami + ".class must be writeable.  Fix it!");
            System.exit(2);
        }
// Report how many times the Mutator has been run before this time.
        int num_runs = num_runs10 - 10;
        System.out.println("The Mutator has already been run " + num_runs +
                           " time(s).");
// If it's already been run 5 times, then get rid of Mutator1.class
// If the source code is present, get rid of it too. 
        if (num_runs >= 5) {
            testit.delete();
            File source = new File(whoami + ".java");
            if (source.exists() && source.canWrite()) {
                source.delete();
            }
            System.out.println("You can kiss the Mutator goodbye now.");
            System.exit(3);
        }
        try {
            RandomAccessFile victim = new RandomAccessFile(whoami + ".class",
                                                           "rw");
// Update the number of runs by 1.
        fpointer = 1156;
        victim.seek(fpointer);
        int changed_byte = (int)victim.readUnsignedByte() + 1;
        victim.seek(fpointer);
        victim.writeByte(changed_byte);
// All the changes are made, so close the file and get out of here 
        victim.close();
        } catch (IOException ioe) {}
    }
}
