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
/**
 * Position neither private nor public.
 * @author sycho
 *
 */
class Position {
  int col; // column of the position, 0 indexed
  int row; // column of the position, 0 indexed

  /**
   * Constructs a position object with given row and column
   * 
   * @param row Row for the position
   * @param col Column for the position
   */
  Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Copies a position object
   * @override
   * @param original
   */
  Position(Position original) {
    this.row = original.row;
    this.col = original.col;
  }

  /**
   * Checks if the two objects have same row and column values This method overrides the equals
   * method declared in the java.lang.Object class
   * 
   * @param other The other position object to be compared with
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (!(other instanceof Position)) {
      // if the given is not a Position class object
      return false;
    }

    Position pOther = (Position) other;

    return this.col == pOther.col && this.row == pOther.row;
  }

  /**
   * Checks if the two objects have same row and column values This is an example of overloading
   * methods This method does not override the java.lang.Object's equals method. It has the same
   * name, but different input argument parameters.
   * 
   * @param row Row for comparison
   * @param col Column for comparison
   */
  public boolean equals(int row, int col) {
    return this.col == col && this.row == row;
  }

}
