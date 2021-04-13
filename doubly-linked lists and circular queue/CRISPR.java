/*
 * NAME: Hetsvi Navnitlal
 * PID: A13595252
 */


/**
 * Gene Splicing CRISPR Simulator
 *
 * @author Hetsvi
 * @since 21st October 2018
 */
public class CRISPR {

    /*Sequences to use/test your CRISPR functions on. Please add more as you test*/
    private static String simpleGenome = "ACATATA";

    private static String sequencedGenome = "AAATTCAAGCGAGGTGATTACAACAAATTTTGCTGATGGTTTAGGCGTA"
            + "CAATCCCCTAAAGAATATAATTAAGAAAATAGCATTCCTTGTCGCCTAGAATTACCTACCGGCGTCCACCATACCTTCG"
            + "ATATTCGCGCCCACTCTCCCATTAGTCGGCACAAGTGGATGTGTTGCGATTGCCCGCTAAGATATTCTAAGGCGTAACG"
            + "CAGATGAATATTCTACAGAGTTGCCGTACGCGTTGAACACTTCACGGATGATAGGAATTTGCGTATAGAGCGGGTCATT"
            + "GAAGGAGATTACACTCGTAGTTAACAACGGGCCCGGCTCTATCAGAACACGAGTGCCTTGAATAACATACTCATCACTA";

    private static String overlappingGuide = "UAU";
    private static String guideRNA = "CUAAUGU";
    private static String splicedGene = "TAGACAT";

    private static String genomeSeq = "ACATA";
    private static String guideSeq = "UGU";
    private static String spliceSeq = "TAG";

    private static String genomeSeq1 = "ATATA";
    private static String guideSeq1 = "UA";
    private static String splicedSeq1 = "CAT";

    private static String genomeSeq2 = "ATATA";
    private static String guideSeq2 = "UAU";
    private static String splicedSeq2 = "CAT";

    private static String genomeSeq3 = "ATATA";
    private static String guideSeq3 = "CG";
    private static String splicedSeq3 = "CAT";

    private static String genomeSeq4 = "ACATATA";
    private static String guideSeq4 = "UGU";
    private static String splicedSeq4 = "TAGACAT";

    private static String genomeSeq5 = "ACATATA";
    private static String guideSeq5 = "UA";
    private static String spliceSeq5 = "CAT";

    /*private static String genomeSeq6 = "G";
            //"TCGATAATCATCG";
    private static String guideSeq6 = "C";
                    //"AGC";
    private static String spliceSeq6 = "B";
                            //"BALL";
*/
    private static String genomeSeq7 = "AGAGAGA";
    private static String guideSeq7 = "U";
    private static String spliceSeq7 = "BA";

    /*private static String genomeSeq8 = "ACATACATACCATATA";
    private static String guideSeq8 = "UAU";
    private static String spliceSeq8 = "CAT";

    private static String genomeSeq9 = "ACATACATACCATACATATA";
    private static String guideSeq9 = "UAU";
    private static String spliceSeq9 = "BB";*/


    /**
     * Program Entry, this simply runs and gives
     * the output for the above strings
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        /*Should print out ACATATA (unchanged)*/
        System.out.println(spliceDNA(simpleGenome, overlappingGuide, splicedGene));
        System.out.println(spliceDNA(genomeSeq, guideSeq, spliceSeq));
        System.out.println(spliceDNA(genomeSeq1, guideSeq1, splicedSeq1));
        System.out.println(spliceDNA(genomeSeq2, guideSeq2, splicedSeq2));
        System.out.println(spliceDNA(genomeSeq3, guideSeq3, splicedSeq3));
        System.out.println(spliceDNA(genomeSeq4, guideSeq4, splicedSeq4));
        System.out.println(spliceDNA(genomeSeq5, guideSeq5, spliceSeq5));
       // System.out.println(spliceDNA(genomeSeq6, guideSeq6, spliceSeq6));
        System.out.println(spliceDNA(genomeSeq7, guideSeq7, spliceSeq7));
       // System.out.println(spliceDNA(genomeSeq8, guideSeq8, spliceSeq8));
       // System.out.println(spliceDNA(genomeSeq9, guideSeq9, spliceSeq9));
    }

    /**
     *  Simulate gene splicing on a genome using CRISPR
     *
     * @param genomeSequence initial DNA encoding
     * @param guideSequence guideRNA encoding
     * @param splicedSequence target insertion gene encoding
     * @return modified genome
     */
    public static String spliceDNA(String genomeSequence, String guideSequence,
                                   String splicedSequence) {
        DoublyLinkedList<Character> genome = new DoublyLinkedList<>();
        DoublyLinkedList<Character> guideRNA = new DoublyLinkedList<>();

        populateFromDNA(genome, genomeSequence);
        populateDNAFromRNA(guideRNA, guideSequence);

        /*TODO: Implement a splicing algorithm with will add the splicedSequence
                where appropriate to genome
         */

        int [] wanted = genome.match(guideRNA);
        boolean select = false;

        DoublyLinkedList<Integer> currWant = new DoublyLinkedList<>();
        // Checks for overlapping
        for (int i = 0; i < wanted.length - 1; i++) {
            if (wanted[i + 1] - wanted[i] >= guideRNA.size()) {
                currWant.add(wanted[i]);
                select = true;
            }
        }

        if (select == true || wanted.length == 1){
                currWant.add(wanted[wanted.length - 1]);

            //System.out.println(currWant);
        }


        // splicing is done
        for (int i = 0; i < currWant.size(); i++){
            DoublyLinkedList<Character> spliceWant = new DoublyLinkedList<>();
            populateFromDNA(spliceWant, splicedSequence);
            genome.splice(currWant.get(i) + guideRNA.size()+
                    (spliceWant.size()*i), spliceWant);

        }
        return transcribeGeneticCode(genome);
    }

    /**
     * This is a direct encoding of the genetic code from the String to a LinkedList
     * @param dnaList to populate
     * @param dnaString DNA string encoding
     */
    public static void populateFromDNA(DoublyLinkedList<Character> dnaList,
                                       String dnaString) {
        //TODO: Populate dnaList with the characters in s

        for(int i =0; i<dnaString.length(); i++){
            dnaList.add(dnaString.charAt(i));
        }
    }

    /**
     * This is an encoding of of the DNA that binds with the RNA
     * Remember that DNA pairs up A-T C-G, and RNA pairs up A-U C-G
     * Thus the guide RNA AUCG would match with the DNA TAGC
     * @param dnaList to populate
     * @param rnaString RNA string encoding
     */
    public static void populateDNAFromRNA(DoublyLinkedList<Character> dnaList,
                                          String rnaString) {
        //TODO: Populate dnaList with the DNA representation of the RNA Sequence

        populateFromDNA(dnaList, rnaString);
        //replaces the RNA ones to DNA
        for(int i =0; i<dnaList.size(); i++){
            if(dnaList.get(i) == 'A'){
                dnaList.set(i, 'T');
            }

            if(dnaList.get(i) == 'U'){
                dnaList.set(i, 'A');
            }

            if(dnaList.get(i) == 'C'){
                dnaList.set(i, 'G');
            }

            if(dnaList.get(i) == 'G'){
                dnaList.set(i, 'C');
            }
        }
    }

    /**
     * Recreate the original base sequence that was loaded into the list
     * @param geneticSequence list representation of the
     * @return base sequence of the genetic material
     */
    public static String transcribeGeneticCode(DoublyLinkedList<Character>
                                                       geneticSequence) {
        String s = "";
        for (char c : geneticSequence) {
            s += c;
        }
        return s;
    }

}
