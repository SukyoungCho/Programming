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
 * Class JobNode: singly-linked node for a job
 * 
 * @author sycho
 *
 */
public class JobNode {

  private static int jobCount = 0; // number of jobs already created
  private int jobId; // unique job identifier
  private float arrivalTime; // arrival time in seconds
  private int priority; // job priority
  private int timeToLive; // job Time To Live in seconds
  private int userId; // identifier of the user that created the job
  private String description; // job description
  private JobNode next; // reference to the next job in the linked list


  /**
   * creates a job node
   * 
   * @param arrivalTime
   * @param userId
   * @param priority
   * @param ttl
   * @param description
   */
  public JobNode(float arrivalTime, int userId, int priority, int ttl, String description) {
    this.arrivalTime = arrivalTime;
    this.userId = userId;
    this.priority = priority;
    this.timeToLive = ttl;
    this.description = description;
    this.jobId = jobCount + 1;
    next = null;
    jobCount++;
  }

  /**
   * to duplicate the original JobNode
   * 
   * @Override constructor
   * @param original
   */
  private JobNode(JobNode original) {
    this.arrivalTime = original.arrivalTime;
    this.userId = original.userId;
    this.priority = original.priority;
    this.timeToLive = original.timeToLive;
    this.description = original.description;
    this.jobId = original.jobId;
    next = null;
  }

  /**
   * 
   * @return JobID
   */
  public int getJobId() {
    return jobId;
  }

  /**
   * @return ArrivalTime
   */
  public float getArrivalTime() {
    return arrivalTime;
  }

  /**
   * @return Priority for the job
   */
  public int getPriority() {
    return priority;
  }

  /**
   * @return TimeToLive
   */
  public int getTimeToLive() {
    return timeToLive;
  }

  /**
   * @return UserId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the next Node of this node
   */
  public JobNode getNext() {
    return next;
  }

  /**
   * Set the given Node as the next node for this node
   * 
   * @param next
   */
  public void setNext(JobNode next) {
    this.next = next;
  }

  /**
   * Set Id as given Id
   * 
   * @param Id
   */
  public void setUserId(int Id) {
    this.userId = Id;
  }

  /**
   * Set the description
   * 
   * @param des
   */
  public void setDescription(String des) {
    this.description = des;
  }

  /**
   * This method returns a new reference to a copy of the current JobNode
   * 
   * @return a new reference to a copy of this (instanceof JobNode)
   */
  public JobNode copy() {
    return new JobNode(this);
  }
}
