/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * This class implements the IHashTable interface.
 * It has methods to start an hashtable.
 *
 * @author Hetsvi
 * @since 4 November
 */
public class HashTable implements IHashTable {

	//You will need a HashTable of LinkedLists. 

	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	private LinkedList<String>[] hash; // linked list
	private int longest = 0;     // longest chain

	//You are allowed to add more :)

	/**
	 * Constructor to initialize
	 * @param size
	 */
	public HashTable(int size) {
		if(size <= 1){
			size = 1;
		}
		nelems = 0;
		expand = 0;
		collision = 0;
		statsFileName = null;
		printStats = false;
		hash = new LinkedList[size];
		for(int i =0; i<hash.length; i++){
			hash[i] = new LinkedList<String>();
		}

		//Initialize
	}

	/**
	 * Constructor to initialize
	 * @param size
	 */
	public HashTable(int size, String fileName) {

		// Set printStats to true and statsFileName to fileName
		if(size <= 1){
			size = 1;
		}
		nelems = 0;
		expand = 0;
		collision = 0;
		statsFileName = fileName;
		printStats = true;
		hash = new LinkedList[size];
		for(int i =0; i<hash.length; i++){
			hash[i] = new LinkedList<String>();
		}
	}

	/**
	 * Inserts element into table
	 * @param value value to insert
	 * @return true if inserted
	 */
    @Override
	public boolean insert(String value) {

		//TODO
		int divideTwo = 2;
		int divideT = 3;
		if (value == null) {
			throw new NullPointerException("Value is null");
		}

		if (lookup(value)) {
			return false;
		}
		int index = hashVal(value);

		if (hash[index] != null) {
			hash[index].add(value);
			nelems++;
			// checking load factor
			if (nelems >= (double) hash.length * (double) ((double) divideTwo / (double) divideT)) {
				rehash();
			}
			return true;
		}

		// not null then makes a connecting linked list
		else {
			hash[index] = new LinkedList<String>();
			hash[index].add(value);
			nelems++;
			if (nelems >= (double) hash.length * (double) ((double) divideTwo / (double) divideT)) {
				rehash();
			}
			return true;
		}
	}

	/**
	 * Deletes value from hashtable
	 * @param value value to delete
	 * @return boolean if deleted
	 */
	@Override
	public boolean delete(String value) {

		//TODO
		if(value == null){
			throw new NullPointerException("Value is null");
		}

		int index = hashVal(value);

		if(hash[index].size() == 0){
			//hash[index] = null;
			return false;
		}

		if(!lookup(value)){
			return false;
		}

		hash[index].remove(value); // removes
		nelems--;
		return true;
	}

	/**
	 * Checks if in the table
	 * @param value value to look up
	 * @return boolean
	 */
	@Override
	public boolean lookup(String value) {

		//TODO
		if (value == null) {
			throw new NullPointerException("Value is null");
		}

		int indexReq = hashVal(value);
		if (hash[indexReq] == null) {
			return false;
		}

		if (hash[indexReq].contains(value)) {
			collision++;
			return true;
		}

		return false;
	}

	/**
	 * Prints the table
	 */
	@Override
	public void printTable() {

		//TODO

		for (int i = 0; i < hash.length; i++) {
			System.out.println(" ");
			System.out.println(i + ": ");

			if (hash[i] != null) {
				for (int j = 0; j < hash[i].size(); j++) {
					System.out.println(hash[i].get(j));

					if (j != hash[i].size() - 1) {
						System.out.println(",");
					}

				}
			}

		}
	}

	/**
	 * Gives the size
	 * @return number of elements
	 */
	@Override
	public int getSize() {

		//TODO

		return nelems;
	}

	/**
	 * Give the index where the function is to. Hash function
	 * @param str
	 * @return index
	 */
	private int hashVal(String str) {

		//TODO
		int hashVa = 0;
		int constant = 27;

		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i);
			hashVa = (hashVa * constant + index) % hash.length; // index
		}
		return hashVa;
	}

	/**
	 * Changes the size when over loadfactor
	 */
	private void rehash() {

		//TODO

		int mutiply = 2;

		if (printStats == true) {
			printStatistics();
		}

		expand += 1;
		LinkedList<String>[] prevHash = hash;
		hash = new LinkedList[prevHash.length * mutiply]; // new list
		nelems = 0;
		//String place = null;
		for (int i = 0; i < prevHash.length; i++) {
			if (prevHash[i] != null) {
				for (String letter : prevHash[i]) {
					insert(letter);
				}
			}
		}
		collision = 0;
		longest = 0;

	}

	/**
	 * outputs a file and prints the stats in it
	 */
	private void printStatistics() {

		//TODO
		try{
			FileWriter newFile = new FileWriter(statsFileName, true);
			BufferedWriter write = new BufferedWriter(newFile);
			for(int i =0; i<hash.length; i++){
				if(hash[i] != null && hash[i].size() > longest){
					longest = hash[i].size();
				}

				if (hash[i] != null){
					collision = collision +hash[i].size() -1;
				}
			}

			double loadFactor = (double)nelems / (double)hash.length;
			DecimalFormat df = new DecimalFormat("#.##");
			String stringWant = (expand+" resize, "+"load factor "+ df.format(loadFactor).toString() +
			", " + collision +" collision, " + longest + " longest chain\n");  // format
			 write.write(stringWant);
			 write.close();
		}

		catch (IOException e){
			e.printStackTrace();
		}


	}

}
