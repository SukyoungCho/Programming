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
 * A generic interface using the type variable “E”.
 * 
 * @author sycho
 *
 * @param <E>
 */
public interface StackADT<E> {
  /**
   * adds a new item to the top of the stack
   * 
   * @param item
   */
  public void push(E item);

  /**
   * removes the top item from the stack and returns it
   * 
   * @return
   * @throws EmptyStackException
   */
  public E pop() throws EmptyStackException;

  /**
   * 
   * @return the top item from the stack without removing it
   * @throws EmptyStackException
   */
  public E peek() throws EmptyStackException;

  /**
   * 
   * @return true if the stack is empty, otherwise returns false
   */
  public boolean isEmpty();
}
