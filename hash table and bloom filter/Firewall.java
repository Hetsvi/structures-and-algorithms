/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * This program takes in 3 command line arguments. The first one is the malicious URL file. The second one is the
 * mixed URL file, which contains malicious URLs from the given malicious URL file, and other safe URLs as well.
 * The third one is the name of the file to write your output to, which contains every URL from the mixed URL file
 * that are for sure known to be safe after using bloom filter (think about what that means about the
 * false positives), one URL per line.
 *
 * Note that you should only use at most 3 bytes for each 2 malicious URLs in your Bloom Filter. For example, if the 
 * malicious URL file contains 30000 URLs, then you should use at most 45000 bytes in your bloom filter.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Firewall {

    private static BloomFilter firewall;
    private static AbstractList<String> mixed ;

    /**
     * Main method that drives this program
     * @param args the command line argument
     */
    public static void main(String args[]) {

        try{

            FileReader firewallRead = new FileReader(args[0]);
            FileReader mixedRead = new FileReader(args[1]);
            LineNumberReader readFirewall = new LineNumberReader(firewallRead);
            LineNumberReader readMixed = new LineNumberReader(mixedRead);
            int numberLine =0;
            int mixedLine = 0;
            while(readFirewall.readLine() != null){
                numberLine++;
            }

            while(readMixed.readLine() != null){
                mixedLine++;
            }

            readFirewall.close();
            readMixed.close();
            double three = 3.0;
            double two = 2.0;
            double calc = (double)numberLine* (double)(three/two);
            firewall = new BloomFilter((int)calc);
            //mixed = new ArrayList<String>(mixedLine);

            File mUrl = new File(args[0]);
            File mixedUrl = new File(args[1]);
            Scanner smUrl = new Scanner(mUrl);
            Scanner smixedUrl = new Scanner(mixedUrl);


            int inputLine = 1;
            while(smUrl.hasNextLine()){   // add in filter
                String word = smUrl.nextLine();
                firewall.insert(word);
                inputLine++;

            }

          /*  for(int i =0; i< firewall.size; i++){
                System.out.println(firewall.getSlot(i/8, i%8));
            }*/

           // byte inputByte = (byte)inputLine;
            FileWriter newFile = new FileWriter("./output.txt", false);
            BufferedWriter write = new BufferedWriter(newFile);
            int counter = 0;

            while(smixedUrl.hasNextLine()){   // add in abstract list
                String word = smixedUrl.nextLine();
               // mixed.add(word);

                if(!firewall.find(word)){
                    write.write(word);
                    counter++;
                }

                //System.out.println(counter);
            }


            // Get the size of badURL in bytes
            File badURL = new File(args[0]);
            long inputSize = badURL.length();

            File mixedURL = new File(args[1]);
            double otherSize = mixedURL.length();

            double safeURL = mixedLine - numberLine;

        /*    double counter = 0;
            for(int i =0; i< mixed.size(); i++){
               if(!firewall.find(mixed.get(i))){
                   counter++;
               }
            }*/

            //System.out.println(counter);
            double falseRate = (safeURL - counter)/ safeURL;

            // print statistics
            System.out.println("False positive rate: " + falseRate );

            //  size of your bloom filter in bytes
            double savedRatio = inputLine/ numberLine*(three/two);
            System.out.println("Saved memory ratio: " + savedRatio);
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        catch (IOException ie){
            ie.printStackTrace();
        }


    }

}
