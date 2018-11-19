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
 * @author Sukyoung Cho Constructs a maze and display
 */
public class Maze {
  private Position start; // Start position
  private Position finish; // Finish position
  private char[][] mazeInfo; // 2-dimensional array of characters that represents the maze layout
  private MazeRunnerStack path; // Final path from start to finish through the maze
  private Boolean solved; // indicates whether is maze path is solved or not

  /**
   * Constructor creates a new instance of Maze with a given layout. ‘L’, ‘|’, ‘_’, or ‘.’ are the
   * characters used
   * 
   * @param mazeInfo
   */
  public Maze(char[][] mazeInfo) {
    this.mazeInfo = mazeInfo;
    this.path = null;
    this.solved = null;
  }

  /**
   * sets the start position field
   * 
   * @param row row position
   * @param col col position
   */
  public void setStart(int row, int col) {
    start = new Position(row, col);
  }

  /**
   * sets the finish position field
   * 
   * @param row
   * @param col
   */
  public void setFinish(int row, int col) {
    finish = new Position(row, col);
  }

  /**
   * Solves the maze. Run the right hand rule: First, try to turn “right” (relative to where you are
   * facing) If can’t, Second, try going “straight”. Third, “left”. Lastly, turn around and go back.
   * if the new position (current) is equal to the finish. it is solved. Direction facing : 0 =
   * Right, 1 = Up, 2 = Left, 3 = Down (E, N, W, S respectively)
   * 
   */
  public void solveMaze() {
    solved = false; // tell if the maze is solved. it is null before this method is called
    int maxSteps; // given try to finish the loop
    MazeRunnerStack trial = new MazeRunnerStack(start); // starts from the starting position
    int count = 0; // number of steps taken
    Position current = new Position(start.row, start.col); // position for runner
    int orient = 0; // At first, facing the right.
    maxSteps = mazeInfo.length * mazeInfo[0].length * 4; // maxsteps depending on the size of the
                                                         // maze
    while (count < maxSteps) { // loop up to the maxSteps
      if (finish.equals(current.row, current.col)) { // solved if the runner get to the finishing
                                                     // points
        solved = true;
        path = trial; // to return solution path
        break;
      }
      if (!probe(current, (orient + 3) % 4)) { // check if there is wall on its right side
        orient = (orient + 3) % 4; // turn right
      } else if (!probe(current, orient)) { // check if there is wall in the front
        ;
      } else if (!probe(current, (orient + 1) % 4)) { // check if there is wall on the left
        orient = (orient + 1) % 4; // turn left
      } else {
        orient = (orient + 2) % 4; // otherwise, turn back
      }
      move(current, orient); // move by 1 to facing direction after turning
      if (trial.contains(current)) {
        // if it already has visited the position. Delete the past path upto the current position.
        while (!trial.peek().equals(current)) {
          trial.pop();
        }
      } else {
        // records the path
        trial.push(new Position(current));
      }
      count++;
    }
    path = trial; // even if it is not solved the path becomes non-null
  }

  /**
   * Moves by one to the according direction
   * @param current the current position of the runner
   * @param direction the direction the runner is facing
   */
  public void move(Position current, int direction) {
    if (direction == 0) { // if facing right
      current.col++;
    } else if (direction == 1) { //if facing up
      current.row--;
    } else if (direction == 2) { // if facing left
      current.col--;
    } else { // if facing down
      current.row++;
    }
  }

  /**
   * checks if there is no wall depending on the direction
   * @param current
   * @param direction the facing direction
   * @return
   */
  public boolean probe(Position current, int direction) {
    int i = current.row;
    int j = current.col;
    if (direction == 0) { // check if there is wall on the right-side
      if ((j != mazeInfo[i].length - 1)
          && ((mazeInfo[i][j + 1] != 'L') && (mazeInfo[i][j + 1] != '|'))) {
        return false;
      }
    } else if (direction == 1) { // check the wall on the North
      if ((i != 0) && ((mazeInfo[i - 1][j] != '_') && (mazeInfo[i - 1][j] != 'L'))) {
        return false;
      }
    } else if (direction == 2) { // check the wall on the East
      if ((j != 0) && ((mazeInfo[i][j] != 'L') && (mazeInfo[i][j] != '|'))) {
        return false;
      }
    } else if (direction == 3) { // check the wall downside
      if ((i != mazeInfo.length - 1) && ((mazeInfo[i][j] != 'L') && (mazeInfo[i][j] != '_'))) {
        return false;
      }
    }
    return true; // there is a wall
  }

  /**
   * Display the maze
   */
  public void displayMaze() {
    boolean[][] pathGrid = new boolean[mazeInfo.length][mazeInfo[0].length];
    String pathLine = "";
    if (path != null) {
      if (solved) {
        System.out.println("Solution is:");
        Position p;
        while (!path.peek().equals(start)) {
          p = path.pop();
          pathLine = " --> [" + p.row + "," + p.col + "]" + pathLine;
          pathGrid[p.row][p.col] = true;
        }
        p = path.pop();
        pathLine = "[" + p.row + "," + p.col + "]" + pathLine;
      } else
        System.out.println("No solution could be found.");
    }
    // Make the top wall
    for (int j = 0; j < mazeInfo[0].length; j++) {
      System.out.print("+---");
    }
    System.out.println("+");

    // Make each row
    for (int i = 0; i < mazeInfo.length; i++) {

      for (int j = 0; j < mazeInfo[i].length; j++) {
        if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|')
          System.out.print("| ");
        else
          System.out.print("  ");
        if (start.equals(i, j))
          System.out.print("S ");
        else if (finish.equals(i, j))
          System.out.print("F ");
        else if (pathGrid[i][j])
          System.out.print("* ");
        else
          System.out.print("  ");
      }
      System.out.println("|"); // Right wall always present
      // Bottom walls
      for (int j = 0; j < mazeInfo[i].length; j++) {
        if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '_')
          System.out.print("+---");
        else
          System.out.print("+   ");
      }
      System.out.println("+");
    }
    // Display the path line if solved
    if (path != null && solved)
      System.out.println("Path is: " + pathLine);

  }

  public static void main(String[] args) {
    // Testing the given example
    char[][] maze1 =
        new char[][] {{'L', '.', '|'}, {'|', '_', '.'}, {'|', '|', '|'}, {'L', '_', '_'}};
    Maze maz = new Maze(maze1);
    maz.setStart(0, 2);
    maz.setFinish(2, 1);
    maz.solveMaze();
    maz.displayMaze();
  }
}
