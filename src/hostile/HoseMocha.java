package hostile;
/* HoseMocha.java by Mark D. LaDue */

/* January 21, 1997 */

/* Copyright (c) 1997 Mark D. LaDue
   You may study, use, modify, and distribute this example for any purpose.
   This example is provided WITHOUT WARRANTY either expressed or implied.  */

/* This Java application protects your class files from the Mocha decompiler.
   Its operation is simple; it just adds a dead opcode (pop, in this case)
   after each method's return.  This seems to have no effect on the viability
   of the class file, which continues to pass byte code verification, but
   it thoroughly hoses the Mocha decompiler.  If a future release of Mocha
   starts to defend iteslf, new strategies can be devised for achieving the
   desired result.  */


import java.io.*;

class HoseMocha {
    public static void main(String[] argv) {
        int fpointer = 8; // Where are we in the class file?
        int cp_entries = 1; // How big is the constant pool?
        int Code_entry = 1; // Where is the entry that denotes "Code"?
        int num_interfaces = 0; // How many interfaces does it use?
        int num_fields = 0; // How many fields are there?
        int num_f_attributes = 0; // How many attributes does a field have?
        int num_methods = 0; // How many methods are there?
        int num_m_attributes = 0; // How many attributes does a method have?
// How on earth do I use this thing?
        if (argv.length != 1) {
            System.out.println("Try \"java HoseMocha class_file.class\"");
            System.exit(1);
        }
        try {
            RandomAccessFile victim = new RandomAccessFile(argv[0], "rw");
// Skip the magic number and versions and start looking at the class file
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
                int test_int = 0;
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
                    case 6: fpointer = fpointer + 8;
                            i++;
                            break;
// This is the critical case - determine an entry in the constant pool where
// the string "Code" is found so we can later identify the code attributes
// for the class's methods
                    case 1: skipper = victim.readUnsignedShort();
                            if (skipper == 4) {
                                fpointer += 2;
                                victim.seek(fpointer);
                                test_int = victim.readInt();
                                if (test_int == 1131373669) {Code_entry = i;}
                                fpointer = fpointer + skipper;
                            }
                            else {fpointer = fpointer + skipper + 2;}
                            break;
                }
                victim.seek(fpointer); 
            }
// Skip ahead and see how many interfaces the class implements
            fpointer += 6;
            victim.seek(fpointer);
            num_interfaces = victim.readUnsignedShort();
// Bypass the interface information
            fpointer = fpointer + 2*(num_interfaces) + 2;
            victim.seek(fpointer);
// Determine the number of fields
            num_fields = victim.readUnsignedShort();
// Bypass the field information
            fpointer += 2;
            victim.seek(fpointer);
            for (int j=0; j<num_fields; j++) {
                fpointer += 6;
                victim.seek(fpointer);
                num_f_attributes = victim.readUnsignedShort();
                fpointer = fpointer + 8*(num_f_attributes) + 2;
                victim.seek(fpointer);
            }
// Determine the number of methods
            num_methods = victim.readUnsignedShort();
            fpointer += 2;
/* The main event - append a useless opcode to each method to hose Mocha */
            for (int k=0; k<num_methods; k++) {
                fpointer += 6;
                victim.seek(fpointer);
// Determine the number of attributes for the method
                num_m_attributes = victim.readUnsignedShort();
                fpointer += 2;
// Test each attribute to see if it's code
                for (int m=0; m<num_m_attributes; m++) {
                    int Code_test = victim.readUnsignedShort();
                    fpointer += 2;
// If it is, write the opcode for "pop" to the end of the method and adjust
// the stated lengths of the whole attribute array and the code array
                    if (Code_test == Code_entry){
                        int att_length = victim.readInt();
                        victim.seek(fpointer);
                        victim.writeInt(att_length + 1);
                        fpointer = fpointer + 8;
                        victim.seek(fpointer);
                        int code_length = victim.readInt();
                        victim.seek(fpointer);
                        victim.writeInt(code_length + 1);
                        fpointer = fpointer + code_length + 4;
                        victim.seek(fpointer); 
                        int diff = (int)victim.length() - fpointer;
                        byte[] tail = new byte[diff];
                        victim.read(tail, 0, diff);
                        victim.seek(fpointer);
                        victim.writeByte(87);
                        victim.write(tail);
                        fpointer = fpointer + att_length - code_length - 7;
                        victim.seek(fpointer);
                    }
// Otherwise just skip it and go on to the next method
                    else {
                        fpointer = fpointer + victim.readInt() + 4;
                        victim.seek(fpointer);
                    }
                }
            }
// All the changes are made, so close the file and move along
            victim.close();
        } catch (IOException ioe) {}
    }
}
