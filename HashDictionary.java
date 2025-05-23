import java.util.LinkedList;

//HashDictionary class must implement all the methods of the DictionaryADT interface
public class HashDictionary implements DictionaryADT {
	private LinkedList<Data>[] hashTable;
    private int size;
    private final int PRIME_BASE = 103;

    //constructor to initialize size, return empty dictionary of the specified size .
    public HashDictionary(int size) {
        this.size = size;
        hashTable = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

 // Convert string to an index
    private int hashIt(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (PRIME_BASE * hash + str.charAt(i)) % size;
        }
        return hash;
    }
    
    //Adds record to the dictionary and throws a DictionaryException if record is already in the dictionary.
    public int put(Data record) throws DictionaryException {
        int hashIndex = hashIt(record.getConfiguration());
        for (Data data : hashTable[hashIndex]) {
            if (data.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException();
            }
        }
        hashTable[hashIndex].add(record);
        if (hashTable[hashIndex].size() > 1) {
            return 1;
        } else {
            return 0;
        }
    }

    //Removes the record with given config from the dictionar and throws a DictionaryException if no record in the hash table stores config.
    public void remove(String config) throws DictionaryException {
        int hashIndex = hashIt(config);
        boolean didRemove = false;
        for (Data data : hashTable[hashIndex]) {
            if (data.getConfiguration().equals(config)) {
                hashTable[hashIndex].remove(data);
                didRemove = true;
                break;
            }
        }
        if (!didRemove) {
            throw new DictionaryException();
        }
    }

    //Returns -1 if config not in dictionary or the score stored with key config
    public int get(String config) {
        int hashIndex = hashIt(config);
        for (Data data : hashTable[hashIndex]) {
            if (data.getConfiguration().equals(config)) {
                return data.getScore();
            }
        }
        return -1;
    }

    //Returns the number of Data objects stored in the dictionary
    public int numRecords() {
        int count = 0;
        for (LinkedList<Data> list : hashTable) {
            count += list.size();
        }
        return count;
    }
}