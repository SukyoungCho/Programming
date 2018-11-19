
/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    Sukyoung Cho Lec003 (scho83@wisc.edu)
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   10:00 pm on Oct 29, 2018 (partially before 10pm on 10/22)
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */

import java.util.NoSuchElementException;

//import HashTable.ListNode;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * @author sycho
 * 
 * This HashTable utilizes a bucket algorithm to resolve collisions. The table is an ArrayList, which is composed of
 * a Linkedlist of Listnodes with a key and value. When, the given key has the same hash index (if collision occurs),
 * the given key and value are added to the last of linked list as a form of node. If the given key is already in the
 * table, the older value is replaced with the new. 
 * First, I use the built-in method called hashCode() in order to create hash code with given key, then
 * calculate the modulo (key.hashCode() % table size) and use it as an hash index for the table
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

	/**
	 * A node for linked list containing a key and value. It allows the linked list
	 * to store key and value at the same time
	 * 
	 * @author sycho
	 *
	 */
	class ListNode {
		private ListNode next;
		private V value; // value to be stored
		private K key; // key for value. Hash index

		/**
		 * Constructs a node for a LinkedList with given key and value
		 * 
		 * @param Key
		 * @param value
		 */
		private ListNode(K Key, V value) {
			this.setNext(null);
			this.setKey(Key);
			this.setValue(value);
		}

		/**
		 * getter for the next node
		 * 
		 * @return next node
		 */
		private ListNode getNext() {
			return next;
		}

		/**
		 * setter for the next node
		 * 
		 * @param next
		 */
		private void setNext(ListNode next) {
			this.next = next;
		}

		/**
		 * getter for the value
		 * 
		 * @return value of this node
		 */
		private V getValue() {
			return value;
		}

		/**
		 * setter for the value
		 * 
		 * @param value
		 */
		private void setValue(V value) {
			this.value = value;
		}

		/**
		 * getter for the key
		 * 
		 * @return this node's key
		 */
		private K getKey() {
			return key;
		}

		/**
		 * setter for the key
		 * 
		 * @param key
		 */
		private void setKey(K key) {
			this.key = key;
		}

	}

	private int size; // record the number of keys
	private ArrayList<LinkedList<ListNode>> table; // the hash table.
	private double loadFactor; // to determine how many keys the table can store. if exceeds, resize
	private int capacity; // table size

	/**
	 * default constructor. Size of 11 and a load factor of 0.75 are widely used
	 * default values, as discussed in the lecture.
	 */
	public HashTable() {
		this.setTable(new ArrayList<LinkedList<ListNode>>(11));
		for (int i = 0; i < 11; i++) {
			this.getTable().add(new LinkedList<ListNode>());
		}
		this.setCapacity(11);
		this.setLoadFactor(0.75);

	}

	/**
	 * A constructor that accepts initial capacity and load factor.
	 * 
	 * @param initialCapacity
	 *            the table size
	 * @param loadFactor
	 *            the factor of the number of nodes which the table can load
	 */
	public HashTable(int initialCapacity, double loadFactor) {
		this.setTable(new ArrayList<LinkedList<ListNode>>(initialCapacity));
		for (int i = 0; i < initialCapacity; i++) {
			this.getTable().add(new LinkedList<ListNode>());
		}
		this.setCapacity(initialCapacity);
		this.setLoadFactor(loadFactor);
	}

	/**
	 * Hash function to convert a given key into hash index for the table. Utilize
	 * the built-in hashCode() method.
	 * 
	 * @param key
	 * @return the calculated hash index
	 */
	private int hash(K key) {
		int code = Math.abs(key.hashCode());
		int index = code % this.getCapacity();
		return index;
	}

	// insert a <key,value> pair entry into the hash table if the key already exists
	// in the table,
	// replace existing value for that key with the value specified in this call to
	// put.
	// permits null values but not null keys and permits the same value to be paired
	// with different key
	// throw IllegalArgumentException when key is null
	@Override
	public void put(K key, V value) throws IllegalArgumentException {
		// 1. throw IllegalArgumentException when key is null
		if (key == null)
			throw new IllegalArgumentException();
		int index = hash(key); // 2. hashCode the key
		ListNode n = new ListNode(key, value); // create a new node with given key and value
		LinkedList<ListNode> l = this.getTable().get(index); // go to the hash index of array list
		boolean found = false;
		if (!l.isEmpty()) { // 3. if not empty, ListNode and link
			ListNode node = l.getFirst(); // iterator
			while (node != null) { // traverse the Linked list to check if the key already exists
				if (node.getKey().equals(key)) { // if the key is there
					node.setValue(value); // replace the value
					found = true;
					node = null;
				}
				node = node.getNext();
			}
		}
		if (!found) { // if empty index, just put. since there will be no duplicate key.
			// hash function always returns the same hash index with the same key.
			this.getTable().get(index).add(n); // add to the LinkedList in that index}
			this.setSize(this.getSize() + 1); // the size increases
		}

		// 4. Resize. If it exceeds the loadfactor (# of keys > size*loadFactor), double
		// the size + 1 (odd)
		// then, recalculate all hashIndex and assign new place
		if (this.getCapacity() * this.getLoadFactor() < this.getSize()) { // if the table is almost full,
			// create a new hash table and copy all the key and values from the older one.
			HashTable<K, V> newHash = new HashTable<K, V>(this.getCapacity() * 2 + 1, this.getLoadFactor());
			for (int i = 0; i < this.getCapacity(); i++) {
				LinkedList<ListNode> list = this.getTable().get(i);
				if (!list.isEmpty()) {
					ListNode node = list.getFirst(); // iterator
					while (node != null) {
						newHash.put(node.getKey(), node.getValue());
						node = node.getNext();
					}
				}
			}
			this.setTable(newHash.getTable()); // set the new Hashtable
			this.setCapacity(newHash.getCapacity());
		}
	}

	// return the value associated with the given key.
	// throw IllegalArgumentException if key is null
	// throw NoSuchElementException if key does not exist
	@Override
	public V get(K key) throws IllegalArgumentException, NoSuchElementException {
		if (key == null) // if key is null
			throw new IllegalArgumentException();
		int index = hash(key);
		LinkedList<ListNode> list = this.getTable().get(index);
		if (!list.isEmpty()) { // traverse the Linked list to check if the key exists,
			ListNode node = list.getFirst(); // from the first node
			while (node != null) {
				if (node.getKey().equals(key)) // if the key is found
					return node.getValue();
				node = node.getNext();
			}
		}
		throw new NoSuchElementException(); // if the key is not found
	}

	// remove the (key,value) entry for the specified key
	// throw IllegalArgumentException if key is null
	// throw NoSuchElementException if key does not exist in the tree
	@Override
	public void remove(K key) throws IllegalArgumentException, NoSuchElementException {
		if (key == null) // if key is null
			throw new IllegalArgumentException();
		int index = hash(key);
		boolean found = false; // boolean to check if the key exists.
		LinkedList<ListNode> list = this.getTable().get(index);
		if (!list.isEmpty()) { // if the Linked list is not empty
			ListNode node = list.getFirst(); // traverses from the first
			while (node != null) {
				if (node.getKey().equals(key)) { // if the key is found,
					list.remove(node); // delete the node
					node = null;
					found = true; // to prevent NoSuchElement Exception
					this.setSize(this.getSize()-1); // update the size
				} else
					node = node.getNext();
			}
		}
		if (!found) { // if the key is not there
			throw new NoSuchElementException();
		}
	}

	// return the number of keys in the hash table
	@Override
	public int size() {
		return this.size();
	}

	/**
	 * getter for the size
	 * @return size the number of keys
	 */
	private int getSize() { // helper method for the size
		return size;
	}

	/**
	 * setter for the size
	 * @param size the number of keys
	 */
	private void setSize(int size) {
		this.size = size;
	}

	/**
	 * getter for the loadFactor
	 * @return loadFactor a value to decide to resize the table
	 */
	private double getLoadFactor() {
		return loadFactor;
	}

	/**
	 * Setter for the loadFacotr
	 * @param loadFactor a value to decide to resize the table
	 */
	private void setLoadFactor(double loadFactor) {
		this.loadFactor = loadFactor;
	}

	/**
	 * the size of the table
	 * @return capacity 
	 */
	private int getCapacity() {
		return capacity;
	}

	/**
	 * setter for the capacity
	 * @param capacity
	 */
	private void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * getter for the table
	 * @return table the ArrayList, which is the hashtable
	 */
	private ArrayList<LinkedList<ListNode>> getTable() {
		return table;
	}

	/**
	 * setter for the table
	 * @param table the ArrayList, which is the hashtable
	 */
	private void setTable(ArrayList<LinkedList<ListNode>> table) {
		this.table = table;
	}


}
