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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Read the saved .ddd data and load the room 
 * @author sycho
 *
 */
public class LoadButton extends Button implements DormGUI {

  /**
   * constructs the loadbutton
   * @param x
   * @param y
   * @param processing
   */
  public LoadButton(float x, float y, PApplet processing) {
    super(x, y, processing);
    this.label = "Load Room"; // sets the name
  }

  /* 
   * @Override
   * load the saved room
   */
  public void mouseDown(Furniture[] furniture) {
    if (isMouseOver()) {

      loadRoom(furniture, "RoomData.ddd");
    }
  }

  /**
   * read the .ddd file and load the saved room
   * @param furniture
   * @param filename
   */
  private void loadRoom(Furniture[] furniture, String filename) {
    for (int i = 0; i < furniture.length; i++) {
      // first, clear the room
      furniture[i] = null;
    }
    BufferedReader reader = null;

    try {
      File file = new File(filename); //read the file
      reader = new BufferedReader(new FileReader(file));
      String line;
      int i = 0;
      Pattern p = Pattern.compile( // check if the file is written in a given format
          "^([a-z]*)\\s*\\:\\s*(\\d+\\.?\\d+)\\s*\\,\\s*(\\d+\\.\\d+)\\s*\\,\\s*(\\d+)\\s*$");

      while ((line = reader.readLine()) != null && i < 6) { // if the format is wrong
        boolean matchL = p.matcher(line).matches();
        if (matchL == false) { // print an error message
          System.out.println("WARNING: Found incorrectly formatted line in file: " + line);
          continue;
        }

        String[] lineFirstSplit = line.split(":"); // separate with ':' to read the type
        String type = lineFirstSplit[0].trim().toLowerCase();
        String[] vals = lineFirstSplit[1].split(","); // separate with ',' to get positions & rotations
        float x = Float.parseFloat(vals[0]);
        float y = Float.parseFloat(vals[1]);
        int r = (int) Float.parseFloat(vals[2]);

        boolean isExist = new File("images/" + type + ".png").exists();
        if (!isExist) { // check if the image file is there
          System.out.println( // if its not there, print an error message
              "WARNING: Could not find an image for a furniture object of type: <" + type + ">");
          continue;
        } else { // creates the furniture
          furniture[i] = new Furniture(type, x, y, r, processing);
          i++;
        }
        if (i > 6) { // only upto 6 furniture
          System.out.println("WARNING: Unable to load more furniture.");
        }

      }
      reader.close(); // close the reader

    } catch (NullPointerException | NumberFormatException | IOException e) {
      // for given errors, print an error message
      System.out.println("WARNING: Could not load room contents from file RoomData.ddd.");
    }
  }
}


