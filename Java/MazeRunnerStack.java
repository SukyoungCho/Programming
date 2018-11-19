//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Maze Explorer
// Files:
// Course: CS300, Summer, 2018
//
// Author: Sukyoung Cho
// Email: scho83@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// /////////////////// /////////////////// ///////////////////
import java.util.EmptyStackException;

/**
 * Stack of Maze information
 * 
 * @author sycho
 *
 */
public class MazeRunnerStack implements StackADT<Position> {



  private Node top; // Node for the top object


  /**
   * Node class to implement stack with single-linked list
   * 
   * @author sycho
   *
   */
  private class Node {

    private Position position; // Class position information
    private Node next; // link to the next node

    /**
     * construct a node with given position
     * 
     * @param pos
     */
    Node(Position pos) {
      this.position = pos;
      this.next = null;
    }

    /**
     * @return the next node
     */
    public Node getNext() {
      return next;
    }
  }

  /**
   * Constructs a Maze stack default constructor. creates an empty stack
   * 
   * @param top the indext for the top of the stack
   * 
   */
  public MazeRunnerStack() {

    this.top = null;
  }

  /**
   * Constructor. creates a new stack with given position as the top node
   * 
   * @Override
   * @param pos
   */
  public MazeRunnerStack(Position pos) {

    this.top = new Node(pos);
  }

  /*
   * push the new item to the top of the stack, and throws an exception if the item is null
   * 
   * @see StackADT#push(java.lang.Object)
   */
  @Override
  public void push(Position item) throws IllegalArgumentException {
    if (item == null) { // if the given object is null
      throw new IllegalArgumentException(); // throw an exception
    } else {
      Node newNode = new Node(item);
      newNode.next = top;
      top = newNode;
    }
  }

  /*
   * removes and return the position of the top item from the stack
   * 
   * @see StackADT#pop()
   */
  @Override
  public Position pop() throws EmptyStackException {
    Position item = peek();
    top = top.getNext();
    return item;
  }

  /*
   * return the position of the top item without removing it. If empty throws an exception
   * 
   * @see StackADT#peek()
   */
  @Override
  public Position peek() throws EmptyStackException {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return top.position;
  }

  /*
   * check whether or not the stack is empty
   * 
   * @see StackADT#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * Reports whether the Position p can be found within the stack
   * 
   * @param p position node
   * @return
   */
  public boolean contains(Position p) {
    if (!isEmpty()) { // if the stack is not empty
      Node runner = top;
      while (runner != null) {
        // traverse the list
        if (runner.position.equals(p)) {
          // if p is found
          return true;
        }
        runner = runner.getNext();
      }
    }
    return false;
  }

}
