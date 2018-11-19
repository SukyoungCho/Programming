
/**
 * Filename:   TestAVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Sukyoung Cho (scho83@wisc.edu)
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    003
 * 
 * Due Date:   Before 10pm on Oct 8, 2018
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       no known bugs, but not complete either
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.IllegalArgumentException;
import org.junit.Test;

/** TODO: add class header comments here */
public class TestAVLTree {

	/**
	 * Tests that an AVLTree is empty upon initialization.
	 */
	@Test
	public void test01isEmpty() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		assertTrue(tree.isEmpty());
	}

	/**
	 * Tests that an AVLTree is not empty after adding a node.
	 */
	@Test
	public void test02isNotEmpty() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			tree.insert(1);
			assertFalse(tree.isEmpty());
		} catch (DuplicateKeyException e) {
			// should not throw Exception
			fail("Unexpected Exception: DuplicateKeyException");
		} catch (IllegalArgumentException e) {
			// should not throw Exception
			fail("Unexpected Exception: IllegalArgumentException");
		} catch (Exception e) {
			// should not throw Exception
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	/**
	 * Tests functionality of a single delete following several inserts.
	 */
	@Test
	public void test03insertManyDeleteOne() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		if (tree.isEmpty()) {
			try {
				int n = 1;
				while (n < 1000001) {
					// insert a million inputs
					tree.insert(n);
					n++;
				}
				assertFalse(tree.isEmpty()); // check if keys are inserted
				assertTrue(tree.checkForBalancedTree()); // make sure the tree is balanced after insertion
				assertTrue(tree.checkForBinarySearchTree()); // make sure the tree BST after insertion
				assertTrue(tree.search(100)); // make sure 100 is there before the deletion
				tree.delete(100);
				assertFalse(tree.search(100)); // check if 100 is deleted
				assertTrue(tree.checkForBalancedTree()); // check if the tree is balanced and BST even after deletion
				assertTrue(tree.checkForBinarySearchTree());
			} catch (DuplicateKeyException e) {
				// should not throw Exception
				fail("Unexpected Exception: DuplicateKeyException");
			} catch (IllegalArgumentException e) {
				// should not throw Exception
				fail("Unexpected Exception: IllegalArgumentException");
			} catch (Exception e) {
				// should not throw Exception
				fail("Unexpected Exception: " + e.getMessage());
			}
		}
	}

	/**
	 * Tests functionality of many deletes following several inserts.
	 */
	@Test
	public void test04insertManyDeleteMany() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		if (tree.isEmpty()) {
			try {
				int n = 1;
				while (n < 1000001) {
					// insert a million inputs
					tree.insert(n);
					n++;
				}
				assertFalse(tree.isEmpty()); // check if keys are inserted
				assertTrue(tree.checkForBalancedTree()); // make sure the tree is balanced after insertion
				assertTrue(tree.checkForBinarySearchTree()); // make sure the tree BST after insertion
				int d = 67; // assinged any random number to delete
				while (d < 1000001) {
					assertTrue(tree.search(d)); // make sure the key is there before the deletion
					tree.delete(d);
					assertFalse(tree.search(d)); // check if it is deleted
					d = d + 91; // delete numbers randomly (here by adding 91)
				}
				assertTrue(tree.checkForBalancedTree()); // check after the deletions
				assertTrue(tree.checkForBinarySearchTree());
			} catch (DuplicateKeyException e) {
				// should not throw Exception
				fail("Unexpected Exception: DuplicateKeyException");
			} catch (IllegalArgumentException e) {
				// should not throw Exception
				fail("Unexpected Exception: IllegalArgumentException");
			} catch (Exception e) {
				// should not throw Exception
				fail("Unexpected Exception: " + e.getMessage());
			}
		}
	}

	/**
	 * Tests functionality of print following many inserts
	 */
	@Test
	public void test05printMany() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			// insert integers from 1 to 11 randomly
			// print should return in ascending order regardless of the order of insertion
			assertTrue(tree.isEmpty());
			tree.insert(2);
			tree.insert(6);
			tree.insert(3);
			tree.insert(1);
			tree.insert(4);
			tree.insert(5);
			tree.insert(7);
			tree.insert(10);
			tree.insert(9);
			tree.insert(8);
			tree.insert(11);
			assertFalse(tree.isEmpty()); // check if the tree is not empty
			assertTrue(tree.checkForBalancedTree()); // check if the tree is balanced and BST
			assertTrue(tree.checkForBinarySearchTree());
			String keys = tree.print(); // to compare with the expected value
			assertTrue("1 2 3 4 5 6 7 8 9 10 11 ".equals(keys)); // a blank space at the end
		} catch (DuplicateKeyException e) {
			// should not throw Exception
			fail("Unexpected Exception: DuplicateKeyException");
		} catch (IllegalArgumentException e) {
			// should not throw Exception
			fail("Unexpected Exception: IllegalArgumentException");
		} catch (Exception e) {
			// should not throw Exception
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	/**
	 * Tests functionality of delete when it tries to delete an empty tree
	 */
	@Test
	public void test06deleteEmptyTree() {
		// nothing happens if the key does not exist in the tree.
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			tree.delete(1);
			assertTrue(tree.isEmpty());
		} catch (Exception e) {
			// should not throw any exception, if so it failed.
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	/**
	 * Tests functionality of inserting a duplicated value
	 * (expected=DuplicateKeyException.class)
	 */
	@Test(expected = DuplicateKeyException.class)
	public void test07insertDuplicated() throws DuplicateKeyException {
		// if try to insert the duplicated value, it should throw Duplicate key
		// exception
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			tree.insert(1);
			tree.insert(2);
			tree.insert(6);
			tree.insert(124);
			tree.insert(19823);
			assertFalse(tree.isEmpty()); // make sure they are inserted
			assertTrue(tree.checkForBalancedTree()); // check if the tree is balanced and BST
			assertTrue(tree.checkForBinarySearchTree());
			tree.insert(1);
			fail("insert() did not throw a DuplicateKeyException");
		} catch (IllegalArgumentException e) {
			fail("Unexpected Exception: IllegalArgumentException");
		}
	}

	/**
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void test08insertNull() throws IllegalArgumentException {
		// insert null should throw Illegal argument exception
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			tree.insert(null);
			fail("insert() did not throw a IllegalArgumentException");
		} catch (DuplicateKeyException e) {
			fail("Unexpected Exception: DuplicateKeyException");
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test09deleteNullEmpty() throws IllegalArgumentException {
		// delete null should throw Illegal argument exception
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			tree.insert(null);
			fail("delete() did not throw a IllegalArgumentException");
		} catch (DuplicateKeyException e) {
			fail("Unexpected Exception: DuplicateKeyException");
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void test10searchNull() throws IllegalArgumentException {
		// search null should throw IAE
		AVLTree<Integer> tree = new AVLTree<Integer>();
		assertTrue(tree.isEmpty());
		tree.search(null);
		fail("search() did not throw a IllegalArgumentException");
	}

	@Test
	public void test11checkBalancedEmpty() {
		// Emptty tree is balanced.
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			assertTrue(tree.checkForBalancedTree());
		} catch (Exception e) { // should not throw any exception
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test12checkBSTEmpty() {
		// Empty tree is binary search tree
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			assertTrue(tree.checkForBinarySearchTree());
		} catch (Exception e) {
			// should not throw any Exception
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test13printEmpty() {
		// Print() empty tree should return empty string not null.
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			String keys = tree.print(); // to compare with the expected value
			assertTrue("".equals(keys)); // a blank space at the end
		} catch (Exception e) {
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test14checkNegative() {
		// tree should be BST and balanced even with negative integer
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			tree.insert(2);
			tree.insert(-6);
			tree.insert(3);
			tree.insert(-1);
			tree.insert(4);
			tree.insert(-5);
			tree.insert(7);
			tree.insert(-10);
			tree.insert(9);
			tree.insert(-8);
			tree.insert(11);
			assertFalse(tree.isEmpty()); // check if they are inserted
			assertTrue(tree.checkForBalancedTree()); // check if the tree is still BST and balanced
			assertTrue(tree.checkForBinarySearchTree());
		} catch (Exception e) {
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test15checkString() {
		// tree should be BST and balanced even with String (comparable)
		AVLTree<String> tree = new AVLTree<String>(); // tree with string
		try {
			assertTrue(tree.isEmpty());
			tree.insert("apple"); // insert strings
			tree.insert("banana");
			tree.insert("cat");
			tree.insert("CS400");
			tree.insert("Madison");
			tree.insert("badger");
			tree.insert("bucky");
			tree.insert("wisconsin");
			assertFalse(tree.isEmpty()); // check if strings are inserted. balanced, and BST
			assertTrue(tree.checkForBalancedTree());
			assertTrue(tree.checkForBinarySearchTree());
		} catch (Exception e) {
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test16deleteString() {
		// check if delete works with string
		AVLTree<String> tree = new AVLTree<String>(); // tree with string
		try {
			assertTrue(tree.isEmpty());
			tree.insert("apple"); // insert strings
			tree.insert("banana");
			tree.insert("cat");
			tree.insert("CS400");
			tree.insert("Madison");
			tree.insert("badger");
			tree.insert("bucky");
			tree.insert("wisconsin");
			assertTrue(tree.search("CS400"));
			tree.delete("CS400");
			assertFalse(tree.search("CS400"));
			assertTrue(tree.checkForBalancedTree());
			assertTrue(tree.checkForBinarySearchTree());
		} catch (Exception e) {
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test17searchEmpty() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			assertFalse(tree.search(5));
		} catch (Exception e) {
			// should not throw any exception, if so it failed.
			fail("Unexpected Exception: " + e.getMessage());
		}
	}

	@Test
	public void test18searchAbsence() {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		try {
			assertTrue(tree.isEmpty());
			tree.insert(1);
			assertFalse(tree.isEmpty());
			assertFalse(tree.search(5));
		} catch (Exception e) {
			// should not throw any exception, if so it failed.
			fail("Unexpected Exception: " + e.getMessage());
		}
	}
}
