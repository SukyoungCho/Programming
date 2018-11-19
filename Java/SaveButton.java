//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Dorm Designer 3000
// Files: DormDesigner.jar, background.png, bed.png, sofa.png, dresser.png, desk.png, sink.png
// Course: CS300, Summer, 2018
//
// Author: Sukyoung Cho
// Email: scho83@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// /////////////////// /////////////////// ///////////////////
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * save the current room in a given file format
 * 
 * @author sycho
 *
 */
public class SaveButton extends Button {

  /**
   * constructs the save button
   * 
   * @param x x-coordinate
   * @param y y-coordinate
   * @param processing
   */
  public SaveButton(float x, float y, PApplet processing) {
    super(x, y, processing);
    this.label = "Save Room"; // name for the button
  }

  /*
   * (non-Javadoc)
   * 
   * @Override Clicking on a SaveButton in your program should result in all of the furniture
   * objects from your room's Furniture[] array, already extracted from the guiObjects to being
   * saved in a file called "RoomData.ddd" in the working directory
   */

  public void mouseDown(Furniture[] furniture) {
    if (isMouseOver()) {
      saveRoom(furniture, "RoomData.ddd");
    }
  }

  /**
   * saves the room. Saves the positions of the furnitures
   * 
   * @param furniture
   * @param filename
   */
  private void saveRoom(Furniture[] furniture, String filename) {

    try { // write a new file
      PrintStream saveR = new PrintStream(new FileOutputStream("RoomData.ddd", false));
      for (Furniture f : furniture) { // loop through the file
        // with given order. 'type':'x position','y position', 'the number of rotations'
        if (f != null) {
          saveR.println(f.getType() + ":" + f.getPosition()[0] + "," + f.getPosition()[1] + ","
              + f.getRotations());

        }
      }
      saveR.flush();
      saveR.close(); // closes the file
    } catch (IOException e) { // catches IO exception and print the statement
      System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");;
    }
  }
}
