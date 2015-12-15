package hostile;
/* Beginner.java by Mark D. LaDue */

/* February 14, 1997 */

/* Copyright (c) 1997 Mark D. LaDue
   You may study, use, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java application was designed to be a simple target for Attacker.java. 
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

class Beginner {
    public static void main(String[] argv) {
        try {
            FileInputStream inner = new FileInputStream("none");
        }
        catch (IOException ioe) {System.out.println("Oops!");}
        finally {System.out.println("Whew!");}
    }
}
