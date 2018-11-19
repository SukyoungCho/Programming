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
 * List of JobNodes
 * 
 * @author sycho
 *
 */
public class JobList implements WaitingListADT<JobNode> {
  private JobNode head; // head of list
  private int size; // Number of jobs in the list


  /**
   * Creates an empty JobList
   */
  public JobList() {
    this.head = null;
    this.size = 0;
  }

  /**
   * Creates a JobList with a given job as a head
   */
  public JobList(JobNode job) {
    this.head = job;
    this.size = 1;
  }

  /**
   * @return the head of the list
   */
  public JobNode getHead() {
    return head;
  }

  /**
   * Set the given node as a head
   * 
   * @param head
   */
  public void setHead(JobNode head) {
    this.head = head;
  }

  /*
   * Constructor for the schedule list
   * 
   * @Override
   */
  @Override
  public void schedule(JobNode newJob) {
    if (isEmpty()) { // if the array is Empty
      head = newJob; // set as the given Node as a head
      size++;
    } else if (newJob.getPriority() == 0) { // if it is not empty, and priority of the given node is
                                            // 0
      // add newObject at the end of the list
      JobNode runner = head;
      while (runner.getNext() != null) {
        // traverse the list
        runner = runner.getNext();
      }
      runner.setNext(newJob);
      size++; // increment the size
    } else if (newJob.getPriority() == 1) { // if the priority is 1
      JobNode runner = head;
      if (runner.getPriority() == 0) {
        head = newJob; // in case there is no node with priority 1
        newJob.setNext(runner); // it is a head
        size++;
      } else {
        while (runner.getNext() != null && runner.getNext().getPriority() == 1) {
          runner = runner.getNext(); // go until the end of node with priority 1
        }
        if (runner.getNext() == null) {
          runner.setNext(newJob); // at the end if there is not 0 priority
          size++;
        } else {
          newJob.setNext(runner.getNext()); // put after all the previous 1
          runner.setNext(newJob);
          size++;
        }
      }
    }
  }

  /*
   * @see WaitingListADT#size()
   */
  @Override
  public int size() {
    int size = 0;
    if (!isEmpty()) {
      JobNode runner = head; // initialize the runner to the head of the list
      size++;
      while (runner.getNext() != null) { // traverse the list
        size++;
        runner = runner.getNext();
      }
    }
    return size;
  }

  /*
   * (non-Javadoc)
   * 
   * @see WaitingListADT#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return head == null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see WaitingListADT#clean(float)
   */
  @Override
  public int clean(float cleaningTime) {
    int cleaned = 0;
    if (!isEmpty()) {
      JobNode runner = head;
      while (head != null && head.getTimeToLive() + head.getArrivalTime() < cleaningTime) {
        // delete the head if
        cleaned++; // delete the job if ttl+at is less than a cleaning time
        size--; // decrease the size if deleted
        head = head.getNext();
      }

      runner = head;

      while (runner != null) {
        while (runner.getNext() != null && runner.getNext().getArrivalTime()
            + runner.getNext().getTimeToLive() < cleaningTime) {
          // clean all the jobs with ttl+at < cleaning time
          cleaned++;
          size--;
          runner.setNext(runner.getNext().getNext());
        }
        runner = runner.getNext();
      }
    }
    return cleaned;
  }

  /*
   * (non-Javadoc)
   * 
   * @see WaitingListADT#clear()
   */
  @Override
  public void clear() {
    head = null;
    size = 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see WaitingListADT#duplicate()
   */
  @Override
  public WaitingListADT<JobNode> duplicate() {
    JobList copied = new JobList(); // create a new list for the copy
    JobNode runner = head;
    if (!isEmpty()) {
      // duplicate the whole list
      copied.setHead(runner.copy());
      JobNode runnerC = copied.getHead();
      while (runner.getNext() != null) {
        // traverse the list
        runner = runner.getNext();
        runnerC.setNext(runner.copy());
        runnerC = runnerC.getNext();
      }
    }
    copied.size = size;
    return copied;
  }

  /*
   * Convert the list to String
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    String newLine = System.getProperty("line.separator");

    JobNode runner = head;
    result.append("Job List is empty: " + isEmpty() + newLine);
    result.append("The size is: " + size + " job(s)." + newLine);

    // traverse the list
    while (runner != null) {
      result.append("job #" + runner.getJobId() + " : " + runner.getDescription() + " (UID "
          + runner.getUserId() + ") " + runner.getPriority() + newLine);

      runner = runner.getNext();
    }
    return result.toString();
  }
}
