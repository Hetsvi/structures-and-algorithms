import java.util.Arrays;

/**
 * This class has a method newWords and a main to test the class.
 */
public class EnglishDictionary {

    /**
     * This method takes in a array with strings in it and creates new words by
     * concatenating elements from array letters from i till i+n and if the
     * letter starts from x or z then the new word created is %empty%.
     * Ultimately a new array is written with made up words.
     * @param letters
     * @param n
     * @return new array with words
     */

    public static String [] newWords(String[] letters, int n) {
        /** Your code is here */
        String [] new_String = new String[(letters.length)];
        String needed = " ";
        for (int i = 0, index = 0; i< letters.length; i++){

                needed = "";
            // checks if the first charcter of word is x or z
                if(letters[i].charAt(0)=='x' || letters[i].charAt(0)=='z') {

                    needed = "%empty%";
                    new_String[index] = needed;
                    index ++;
                    continue;
                }

                for (int j = i; (j<= i+n) && needed != "%empty%"; j++){
                    if (j == letters.length) {  // check if it has to break
                        break;
                    }
                    needed = needed + letters[j];
                }
            new_String[index] = needed;
            index ++;

        }
        return new_String; // returns new string
    }

    /**
     * This methods tests the newWords method.
     * @param args
     */

    public static void main(String[] args) {
        String[] test = {"xoxo", "yum" ,"lol", "ypop"};
        int n = 2;
        String[] output;
        output = newWords(test, n);

        // Should print %empty%, yumlolypop, lolypop, ypop
        System.out.println(java.util.Arrays.toString(output));

        String[] test1 = {"ma", "le" ,"zo", "sh", "tip","pa"};
        System.out.println(Arrays.toString(newWords(test1, 3)));

        String[] test2 = {"happy", "always" ,"sunny", "xmas"};
        System.out.println(Arrays.toString(newWords(test2, 2)));

    }
}
