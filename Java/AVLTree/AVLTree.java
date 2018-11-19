
/**
 * Filename:   AVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Sukyoung Cho (scho83@wisc.edu)
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    003
 * 
 * Due Date:   Before 10pm on September 24, 2018
 * Version:    1.0
 * 
 * Credits:    
 * 
 * Bugs:       no known bugs
 */

import static org.junit.Assert.assertTrue;

import java.lang.IllegalArgumentException;

/**
 * AVLTree is a binary search tree which keeps its balance between left and
 * right subtree height difference to maintain its worst case as log(N).
 * 
 * @param <K>
 *            comparable variables
 */
public class AVLTree<K extends Comparable<K>> implements AVLTreeADT<K> {

	/**
	 * 
	 * It is a tree node for binary search tree (AVLTree here). It contains key and
	 * links to its left and right subtree. Height helps for the tree to keep track
	 * of its balancing.
	 * 
	 * @param <K>
	 *            comparable variables
	 */
	class BSTNode<K> {

		private K key; // it is a key to generic object the tree stores
		private int height; // The height is the length of the longest downward path to a leaf from this
							// node
		private BSTNode<K> left, right; // Link to its left and right children

		/**
		 * Constructor for a BST node.
		 * 
		 * @param key
		 */
		BSTNode(K key) {
			this.key = key; // have key as its value
			this.height = 1; // initial height is 1
			this.left = null;
			this.right = null;
		}

		/**
		 * It returns the comparable data which node contains
		 * 
		 * @return the key value (comparable K)
		 */
		public K getKey() {
			return key;
		}

		/**
		 * It sets the K value for the node
		 * 
		 * @param key
		 *            the key value (comparable K)
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * It returns the height of the tree node
		 * 
		 * @return the length of the longest downward path to a leaf from this node
		 */
		public int getHeight() {
			return height;
		}

		/**
		 * It sets the height of the node. To keep track of the height whenever it
		 * changes For example, insert or delete.
		 * 
		 * @param height
		 *            the length of the longest downward path to a leaf from this node
		 */
		public void setHeight(int height) {
			this.height = height;
		}

		/**
		 * It returns the left child of this node
		 * 
		 * @return the left child
		 */
		public BSTNode<K> getLeft() {
			return left;
		}

		/**
		 * It takes the input node as its left child
		 * 
		 * @param left
		 *            the left child (node with smaller value than the parent)
		 */
		public void setLeft(BSTNode<K> left) {
			this.left = left;
		}

		/**
		 * It returns the right child of the node
		 * 
		 * @return the right child
		 */
		public BSTNode<K> getRight() {
			return right;
		}

		/**
		 * It takes the input node as its right child
		 * 
		 * @param right
		 *            the right child (node with greater value than the parent)
		 */
		public void setRight(BSTNode<K> right) {
			this.right = right;
		}

	}

	private BSTNode<K> root; // The root of AVL Tree
	private String inorder = ""; // For print(), store the keys of tree nodes
	private BSTNode<K> prev; // To keep track of traversal for Checking BST


	/**
	 * getter for the root
	 * @return the root of the tree
	 */
	private BSTNode<K> getRoot() {
		return this.root;
	}

	/**
	 * setter for the root
	 * @param node BSTNode<K> tree node
	 */
	private void setRoot(BSTNode<K> node) {
		this.root = node;
	}

	/**
	 * getter for the keys of the tree nodes
	 * @return the string casted key values in in-order.
	 */
	private String getInorder() {
		return this.inorder;
	}

	/**
	 * setter for the keys
	 * @param values keys of the tree nodes
	 */
	private void setInorder(String values) {
		this.inorder = values;
	}

	/**
	 * Helper method for insert() and delete(). It is to right rotate subtree to maintain the
	 * balance of the tree
	 * 
	 * @param node
	 *            BSTNode on which the rotate occurs (pivot)
	 * @return the right-rotated node
	 */
	private BSTNode<K> rotateRight(BSTNode<K> node) {
		BSTNode<K> previous = node; // parent / root
		BSTNode<K> next = node.getLeft(); // a left child
		previous.setLeft(next.getRight()); // left child's right child becomes root's left child
		next.setRight(previous); // root becomes a right child of its left child
		// Update the height
		previous.setHeight(Math.max(getHeight(previous.getLeft()), getHeight(previous.getRight())) + 1);
		next.setHeight(Math.max(getHeight(next.getLeft()), getHeight(next.getRight())) + 1);
		// return the rotated node
		return next;
	}

	/**
	 * Helper method for insert() and delete(). It is to left rotate subtree to maintain the
	 * balance of the tree
	 * 
	 * @param node
	 *            BSTNode on which the rotate occurs (pivot)
	 * @return the left-rotated node
	 */
	private BSTNode<K> rotateLeft(BSTNode<K> node) {
		BSTNode<K> previous = node; // parent
		BSTNode<K> next = node.getRight(); // right child
		previous.setRight(next.getLeft()); // right child's left child becomes a right child of the root
		next.setLeft(previous); // the root becomes a left child of its right child
		// update the height
		previous.setHeight(Math.max(getHeight(previous.getLeft()), getHeight(previous.getRight())) + 1);
		next.setHeight(Math.max(getHeight(next.getLeft()), getHeight(next.getRight())) + 1);
		return next; // return the rotated node
	}

	/**
	 * private helper method to keep the tree balanced
	 * @param node
	 * @return the node after double rotation
	 */
	private BSTNode<K> rotateLeftRight(BSTNode<K> node) {
		BSTNode<K> previous = node;
		BSTNode<K> left = node.getLeft();
		// rotate Left with left child
		previous.setLeft(rotateLeft(left));
		// rotate Right with the root
		return rotateRight(previous);
	}

	/**
	 * private helper method to keep the tree balanced
	 * @param node
	 * @return the node after double rotation
	 */
	private BSTNode<K> rotateRightLeft(BSTNode<K> node) {
		BSTNode<K> previous = node;
		BSTNode<K> right = node.getRight();
		// rotate Left with left child
		previous.setRight(rotateRight(right));
		// rotate Right with the root
		return rotateLeft(node);
	}

	/**
	 * @param node
	 * @return the int value of the height of the tree
	 */
	private int getHeight(BSTNode<K> node) {
		if (node == null) // if the tree is empty
			return 0;
		else
			return node.getHeight();
	}

	/**
	 * Check if the AVLTree is empty or not.
	 */
	@Override
	public boolean isEmpty() {
		return this.getRoot() == null;
	}

	/**
	 * It inserts a new node with given key into AVL Tree while maintaining the
	 * balance of the tree. insert() should throw a DuplicateKeyException (if there
	 * is an attempt to insert a duplicate key), and should throw an
	 * IllegalArgumentException if null is passed as a parameter.
	 * 
	 */
	@Override
	public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
		// call helper method with the root node
		setRoot(insert(this.getRoot(), key));
	}

	/**
	 * @param node
	 * @param key
	 * @return the new tree after insertion
	 * @throws DuplicateKeyException if it tries to insert duplicated key
	 * @throws IllegalArgumentException if it tries to insert null
	 */
	private BSTNode<K> insert(BSTNode<K> node, K key) throws DuplicateKeyException, IllegalArgumentException {
		// if the key is null, throw exception
		if (key == null) {
			throw new IllegalArgumentException();
		}
		if (node == null) { // if the tree is empty
			node = new BSTNode<K>(key); // create a new node for root
			return node;
		}
		if ((key.compareTo(node.getKey()) < 0)) { // if the key is smaller than the root
			node.setLeft(insert(node.getLeft(), key)); // insert into the root's left subtree
		} else if (key.compareTo(node.getKey()) > 0) { // if the key is greater
			node.setRight(insert(node.getRight(), key)); // insert into the root's right subtree
		} else if (node.getKey().equals(key)) { // if the key is already there, throw an exception
			throw new DuplicateKeyException();
		}
		// Rotate to maintain the tree balanced, if necessary.
		if (Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) == 2) { // if the tree is unbalanced
			if (getHeight(node.getLeft()) > getHeight(node.getRight())) { // and if the left subtree is higher
				if (key.compareTo(node.getLeft().getKey()) < 0) { // if the key should be inserted into the left subtree
					node = rotateRight(node); // rotate right
				} else { // if needs to be inserted in the right subtree
					node = rotateLeftRight(node); // rotateLeftRight (double rotation)
				}
			} else if (getHeight(node.getLeft()) < getHeight(node.getRight())) { // and if the right subtree is higher
				if (key.compareTo(node.getRight().getKey()) > 0) { // if the key is greater
					node = rotateLeft(node); // rotate left
				} else { // if smaller
					node = rotateRightLeft(node); // rotate right left (double rotation)
				}
			}
			node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1); // update the height
		}
		node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1); // update the height after
																								// every insertion
		return node;
	}

	/**
	 * delete() traverses the tree and delete the one with the same value in the
	 * tree. Delete should throw an IllegalArgumentException if null is passed as a
	 * parameter.
	 */
	@Override
	public void delete(K key) throws IllegalArgumentException {
		setRoot(delete(this.getRoot(), key)); // call delete with the root node
	}

	/**
	 * private helper method for deletion.
	 * @param node
	 * @param key
	 * @return the tree after removal of the given key
	 * @throws IllegalArgumentException if try to delete null
	 */
	private BSTNode<K> delete(BSTNode<K> node, K key) throws IllegalArgumentException {
		if (key == null) { // if it tries to delete null
			throw new IllegalArgumentException();
		}
		if (node == null) { // if the tree is empty nothing happens
			return node;
		}
		if (key.equals(node.getKey())) { // if it finds the node with the same key
			if (node.getLeft() == null) { // and if the node has no left child
				node = node.getRight(); // the right child becomes the root
				return node;
				// if the root does not have a right child either, then it just deletes the root
				// the tree is always maintained balanced. if it does not have a left child, it
				// is a root
			} else { // if it has both left and right child
				BSTNode<K> maxLeft = node.getLeft(); // Will use the biggest left child method
				while (maxLeft.getRight() != null) { // loop through the left subtree
					maxLeft = maxLeft.getRight(); // to find the largest left child
				}
				node.setKey(maxLeft.getKey()); // replace the node's value with its greatest left child
				node.setLeft(delete(node.getLeft(), maxLeft.getKey())); // delete the node
				if (Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) == 2) { // if the tree becomes
																								// unbalanced
					if (getHeight(node.getLeft()) == 0) { // if there's no left child
						node = rotateLeft(node); // rotate left (balancing)
					} else if (getHeight(node.getRight().getLeft()) < getHeight(node.getRight().getRight())) {
						// if there are more right children
						node = rotateLeft(node); // rotate left
					} else {// vice versa
						node = rotateRightLeft(node); // rotate right left (double rotation)
					}
				}
			}
		} else if (key.compareTo(node.getKey()) < 0) { // if the key is smaller
			node.setLeft(delete(node.getLeft(), key)); // go to left subtree
			if (Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) == 2) { // if the tree becomes
																							// unbalanced
				if (getHeight(node.getLeft()) == 0) { // if no left child
					node = rotateLeft(node); // rotate left (balancing)
				} else if (getHeight(node.getRight().getLeft()) < getHeight(node.getRight().getRight())) {
					// if right children exceed,
					node = rotateLeft(node); // rotate left
				} else { // vice versa
					node = rotateRightLeft(node); // double rotation
				}
			}
		} else if (key.compareTo(node.getKey()) > 0) { // if the key is greater
			node.setRight(delete(node.getRight(), key)); // go to right subtree
			if (Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) == 2) {
				if (getHeight(node.getRight()) == 0) { // if no right child
					node = rotateRight(node); // balancing
				} else if (getHeight(node.getRight().getLeft()) < getHeight(node.getRight().getRight())) {
					node = rotateRight(node);
				} else {
					node = rotateLeftRight(node);
				}
			}
		}
		// update the height
		node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
		return node;
	}

	/**
	 * Search for a key in AVL tree and returns true if the tree contains the key
	 * search should throw an IllegalArgumentException if null is passed as a
	 * parameter
	 */
	@Override
	public boolean search(K key) throws IllegalArgumentException {
		BSTNode<K> current = this.getRoot(); // starts from the root
		if (key == null) // if the key is null, throws an exception
			throw new IllegalArgumentException();
		if (current == null) // empty tree
			return false;
		if (current.getKey().equals(key)) { // if the root has the same key,
			return true;
		}
		while (!current.getKey().equals(key)) { // traverse the tree
			if (key.compareTo(current.getKey()) < 0) // if smaller, go left
				current = current.getLeft();
			else if (key.compareTo(current.getKey()) > 0) // if greater, go right
				current = current.getRight();
			if (current == null) // if the node is null, there is no such node
				return false;
		}
		return true;
	}

	/**
	 * Private helper method to print in-order traversal using parent pointer.
	 * It traverses the tree in-order and then set the private field to store the keys in string
	 * @param node
	 */
	private void inorder(BSTNode<K> node) {
		if (node == null) // if the tree is empty
			return;
		// 1. Traverse left subtree
		inorder(node.getLeft());
		// 2. Store the keys in values field as string
		String values = this.getInorder() + node.getKey().toString() + " "; // convert to string and add a space at the end of each key
		setInorder(values); // store the values
		// 3. Traverse right subtree
		inorder(node.getRight());
	}

	/**
	 * print() should perform an in-order traversal of the AVL Tree and print each
	 * node's key followed by a single space. It is okay to have an extra white
	 * space after the last node's key. Example: "1 2 3 4 5 "
	 */
	@Override
	public String print() {
		// String inorder = print(this.getRoot());
		inorder(this.getRoot()); // call in-order traversal function 
		return this.getInorder(); // return the string
	}

	/**
	 * Check if the tree is balanced, which means that the difference of its left subtree
	 * and right subtree is not greater than 1.
	 */
	@Override
	public boolean checkForBalancedTree() {
		// recursively check
		return checkForBalancedTree(this.getRoot());
	}
	
	/**
	 * private helper method for recursion to check if the tree is balanced.
	 * @param node
	 * @return true if the tree is balanced
	 */
	private boolean checkForBalancedTree(BSTNode<K> node) {
		int heightL; // the height of its left subtree
		int heightR; // the height of its right subtree
		// if tree is empty, it is balanced
		if (node == null)
			return true;
		// Get the height of its left and right sub trees
		heightL = getHeight(node.getLeft());
		heightR = getHeight(node.getRight());
		// calculate the balance, and check if its subtrees are balanced too
		if (Math.abs(heightL - heightR) <= 1 && checkForBalancedTree(node.getLeft()) && checkForBalancedTree(node.getRight()))
			return true;
		// If the tree is not height-balanced
		return false;
	}

	/**
	 * Check if it is a binary tree using in-order traversal
	 * 1.Node(s) on the left subtree of a node must be smaller than the node's key.
	 * 2. Node(s) on the right subtree of a node must be larger than the node's key
	 * 3. All keys must be unique (no duplication) 4. Subtrees must also be binary search tree.
	 */
	@Override
	public boolean checkForBinarySearchTree() {
		prev = null; // first set the previous filed to null
		return checkForBinarySearchTree(this.getRoot()); // recursive function
	}

	/**
	 * private helper method to check BST.
	 * Implemented for recursion
	 * @param node a BST tree node
	 * @return true if the tree is a binary search tree.
	 */
	private boolean checkForBinarySearchTree(BSTNode<K> node) {
		// traverse the tree in in-order fashion and 
        // keep a track of previous node 
        if (node != null) 
        { 
            if (!checkForBinarySearchTree(node.getLeft())) 
                return false; 
            // allows only distinct and larger value than its previous 
            if (prev != null && node.getKey().compareTo(prev.getKey()) <= 0 ) 
                return false; 
            prev = node;
            return checkForBinarySearchTree(node.getRight()); 
        } 
        return true; 
	}

}
