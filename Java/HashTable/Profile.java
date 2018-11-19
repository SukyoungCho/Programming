
/**
 * Filename:   Profile.java
 * Project:    p3
 * Authors:    Sukyoung Cho Lec003 (scho83@wisc.edu)
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10:00 pm on Oct 29, 2018
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author sycho The Profile class contains methods for inserting and retrieving
 *         data from hash table and tree map Instantiate Profile class in the
 *         main method with the appropriate data type The profile class takes
 *         one argument <num_elements>, to insert and retrieve these many number
 *         of elements from both hash table and tree map
 * @param <K>
 *            key
 * @param <V>
 *            value
 */
public class Profile<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable; // HashTable
	TreeMap<K, V> treemap; // Built-in Treemap

	/**
	 * Constructor for the profile which instantiates the hashtable and treemap
	 */
	public Profile() {
		this.hashtable = new HashTable<K, V>();
		this.treemap = new TreeMap<K, V>();
	}

	/**
	 * Insert into both the hash table and tree map as many times as the num_element
	 * Insert K, V into both hashtable and treemap
	 * 
	 * @param key
	 *            key for the input
	 * @param value
	 *            value to store
	 */
	public void insert(K key, V value) {
		this.hashtable.put(key, value);
		this.treemap.put(key, value);
	}

	/**
	 * Get from both the hash table and the tree map as many times as the
	 * num_element
	 * 
	 * @param key
	 *            key to find the value
	 */
	public void retrieve(K key) {
		this.hashtable.get(key);
		this.treemap.get(key);
	}

	/**
	 * Create a profile object and execute the insert and retrieve method of profile as many times as numElements
	 * @param args num_element - the number of elements and times to insert and get
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Expected 1 argument: <num_elements>");
			System.exit(1);
		}

		int numElements = Integer.parseInt(args[0]); // take the argument
		// instantiate the treemap and hashtable
		Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
		for (int i = 0; i < numElements; i++) {
			//insert and retrieve as many times as the num_element
			profile.insert(i, i);
			profile.retrieve(i);
		}

		String msg = String.format("Successfully inserted and retreived %d elements into the hash table and treemap",
				numElements);
		System.out.println(msg);
	}
}
