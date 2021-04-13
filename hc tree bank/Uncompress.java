/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.io.*;

/**
 * Uncompress the first given file to the second given file using Huffman coding
 *
 * @author Hetsvi
 * @since 20May
 */
public class Uncompress {
    private static final int EXP_ARG = 2; // number of expected arguments
    private static final int BYTE_BITS = 8; // number of bits in a byte
    private static final int NUM_CHAR = 256; // alphabet size of extended ASCII

    /**
     * Main method that drives the program. The first command line argument should be
     * the path of the file to be uncompressed. The second should be the path of the file
     * that's uncompressed to.
     *
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // Check if the number of arguments is correct
        if (args.length != EXP_ARG) {
            System.out.println("Invalid number of arguments.\n" +
                    "Usage: ./uncompress <infile outfile>.\n");
            return;
        }

        FileInputStream inFile = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(inFile);
        BitInputStream bitIn = new BitInputStream(in);

        FileOutputStream outFile = new FileOutputStream(args[1]);
        DataOutputStream out = new DataOutputStream(outFile);

        // TODO: EC1 empty file case

        /* Suggestion: first using in.readInt() to read the number of byte that has been
           written during compressing, so that we know when to stop. */

        // TODO: EC1: if the file has single char with non-zero freq

        // TODO: first decode the HCTree using the "header", the returned node should be the root
        int numChar = in.readInt();
        HCTree tree = new HCTree();
        tree.setRoot(tree.decodeHCTree(bitIn));
        for(int i =0; i< numChar; i++){
            out.writeByte(tree.decode(bitIn));
        }




        // TODO: then keep decoding the file

        // Hint: use out.writeByte(byte b) to write each decoded byte to DataOutputStream

        inFile.close();
        in.close();
        outFile.close();
        out.close();
        return;
    }
}
