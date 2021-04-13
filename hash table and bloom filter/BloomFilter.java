/**
 * Name: Hetsvi Navnitlal
 * PID: A13595252
 */

/**
 * Bloom filter implemented by using 3 independent hash functions.
 * @author Hetsvi
 * @since 4Nov
 */
public class BloomFilter {

    public static final int NUMBITS = 8; // number of bits
    public static final int WORD_WIDTH = Integer.SIZE* NUMBITS; // Number of bits in a machine word (int)
    public static final int CRC_HASH_SHIFT = 5; // shift hash by, per char hashed
    public static final int PJW_HASH_SHIFT = 4;  //To shift hash by, per char hashed
    public static final int PJW_HASH_RIGHT_SHIFT = 24;  // Right-shift amount
    public static final int PJW_HASH_MASK = 0xf0000000;   // Mask for extracting top 4

    byte[] table; // the hash table store in bits
    int size; // the number of slots (bits) in the hash table

    /**
     * The constructor that creates a bloom filter of given size in byte with 8 * tableSize slots.
     *
     * @param tableSize the given table size in byte
     */
    public BloomFilter(int tableSize) {
        //TODO
        table = new byte[tableSize];
        this.size = NUMBITS* tableSize;

    }

    //TODO: Add three different good hash functions, remember to make them private

    /**
     * hash function using constant and mod
     * @param str
     * @return hashVa which is index
     */
    private int hashVal(String str) {
        int hashVa = 0;
        int constant = 27;

        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            hashVa = (hashVa * constant + index) % this.size;
        }
        return Math.abs(hashVa);
    }


    /**
     * It is the modified method which rotates the hash value left by
     * 5bits. From pa notes
     * @param key
     * @return index
     */
    private int hashStringCRC(String key)		// Key to be hashed
    {
        int hashValue = 0;
        for (int i =0; i<key.length(); i++)
        {
            int leftShiftedValue = hashValue << CRC_HASH_SHIFT;
            int rightShiftedValue = hashValue >>> (WORD_WIDTH - CRC_HASH_SHIFT);
            hashValue = (leftShiftedValue | rightShiftedValue) ^ Math.abs(i);
        }
        return Math.abs(hashValue % this.size);
    }

    /**
     * Hash funxtion which shits the bits. the top bits are shifted
     * only far enough to line up with the top half. From pa notes
     * @param key
     * @return hashValue which is index
     */
    private int hashPJ(String key)
        {
            int hashValue = 0;
            for( int i =0; i< key.length(); i++)
            {
                hashValue = (hashValue << PJW_HASH_SHIFT) + Math.abs(i);
                int rotate_bits = hashValue & PJW_HASH_MASK;
                hashValue ^= rotate_bits | (rotate_bits >>> PJW_HASH_RIGHT_SHIFT);
            }
            return Math.abs(hashValue % this.size);
        }

    public static final int WEISS_HASH_MULTIPLIER = 37;

/*
    private int hashStringCRC(String key)
    {
        int hashValue = 0;
        for( int i =0; i< key.length(); i++){
            hashValue = hashValue * WEISS_HASH_MULTIPLIER + i;
        }

        return Math.abs(hashValue)% this.size;
    }
*/


    /**
     * Insert a given string to bloom filter
     * @param str the given string
     */
    public void insert(String str) {

        //TODO

            int hashIndex = hashVal(str);
            int hashIndex2 = hashStringCRC(str);
            int hashIndex3 = hashPJ(str);
            this.setBit(hashIndex/NUMBITS, hashIndex%NUMBITS);
            this.setBit(hashIndex2/NUMBITS, hashIndex2%NUMBITS);
            this.setBit(hashIndex3/NUMBITS, hashIndex3%NUMBITS);

    }

    /**
     * Helper method to set a bit in the table to 1, which is specified by the given byteIndex 
     * and bitIndex
     * @param byteIndex the index of the byte in hash table
     * @param bitIndex the index of the bit in the byte at byteIndex. Range is [0, 7]
     */
    private void setBit(int byteIndex, int bitIndex) {
        // set the bit at bitIndex of the byte at byteIndex
        table[byteIndex] = (byte) (table[byteIndex] | ((1 << (NUMBITS - 1)) >> bitIndex));

    }

    /**
     * Find if a string could exist in bloom filter
     * @param str the given string
     * @return true if given string is "believed" to be in the hash table, false otherwise
     */
    public boolean find(String str) {

        //TODO

        int hashIndex = hashVal(str);
        int hashIndex2 = hashStringCRC(str);
        int hashIndex3 = hashPJ(str);


            if( this.getSlot(hashIndex/NUMBITS, hashIndex% NUMBITS) == 1 &&
                    this.getSlot(hashIndex2/NUMBITS, hashIndex2% NUMBITS) == 1 &&
                    this.getSlot(hashIndex3/NUMBITS, hashIndex3% NUMBITS) == 1){
                return true;
            }

            return false;

    }

    /**
     * Helper method to get the bit value at the slot, which is specified by the given byteIndex 
     * and bitIndex
     * @param byteIndex the index of the byte in hash table
     * @param bitIndex the index of the bit in the byte at byteIndex. Range is [0, 7]
     */
    public int getSlot(int byteIndex, int bitIndex) {
        return (table[byteIndex] >> ((NUMBITS - 1) - bitIndex)) & 1;
    }
}
