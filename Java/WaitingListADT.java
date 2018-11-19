//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Job Scheduler
// Files: jobs.txt
// Course: CS300, Summer, 2018
//
// Author: Sukyoung Cho
// Email: scho83@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// /////////////////// /////////////////// ///////////////////
/**
 * @author sycho Interface consisting of abstract methods
 * @param <T>
 */
public interface WaitingListADT<T> {

  /**
   * adds an item of type <T> to the waiting list according to a specific scheduling policy
   * 
   * @param newObject
   */
  public void schedule(T newObject);

  /**
   * checks if the waiting list is empty. Returns true if empty false otherwise
   * 
   * @return
   */
  public boolean isEmpty();

  /**
   * returns the number of items in the waiting list.
   * 
   * @return
   */
  public int size();

  /**
   * removes the obsolete items from the waiting list
   * 
   * @param cleaningTime
   * @return
   */
  public int clean(float cleaningTime);

  /**
   * removes all items in the waiting list
   */
  public void clear();

  /**
   * returns a new reference to a duplicate copy of the list
   * 
   * @return
   */
  public WaitingListADT<T> duplicate();

}
