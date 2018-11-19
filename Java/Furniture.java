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
/**
 * Class which stores the information for every furniture.
 * 
 * @author sycho
 *
 */
public class Furniture implements DormGUI {
  private PApplet processing;
  private PImage image; // image for the furniture
  private float[] position; // position for the furniture
  private boolean isDragging; // checks if it is being dragged
  private int rotations; // how many times it is rotated
  private String type; // which furniture it is


  /**
   * initializes the fields of a new furniture object positioned in the center of the display
   * 
   * @param type
   * @param processing
   */
  public Furniture(String type, PApplet processing) {
    this.processing = processing;
    this.position = new float[] {processing.width / 2, processing.height / 2};
    this.rotations = 0;
    this.isDragging = false;
    this.type = type;
    this.image = processing.loadImage("images/" + type + ".png");
    processing.image(image, position[0], position[1]);
  }

  /**
   * @Override Constructor for loading the room
   */
  public Furniture(String type, float x, float y, int r, PApplet processing) {
    this.type = type;
    this.position = new float[] {x, y};
    this.rotations = r;
    this.processing = processing;
    this.isDragging = false;
    this.image = processing.loadImage("images/" + type + ".png");
    processing.image(image, position[0], position[1]);

  }


  /**
   * get the type of the furniture
   * 
   * @return
   */
  public String getType() {
    return type;
  }

  /**
   * get the position of the furnitures
   * 
   * @return
   */
  public float[] getPosition() {
    return position;
  }


  /**
   * get the number of rotations
   * 
   * @return
   */
  public int getRotations() {
    return rotations;
  }


  /*
   * @Override draws this bed at its current position
   */
  public void update() {
    processing.image(image, position[0], position[1], rotations * PApplet.PI / 2);
    if (isDragging) {
      position[0] = processing.mouseX;
      position[1] = processing.mouseY;
    }

  }


  /*
   * @Override used to start dragging the bed, when the mouse is over this bed when it is pressed
   */
  public void mouseDown(Furniture[] furniture) {
    if (this.isMouseOver())
      isDragging = true;
  }


  /*
   * @Override used to indicate that the bed is no longer being dragged
   */
  public void mouseUp() {
    isDragging = false;
  }


  /*
   * @Override helper method to determine whether the mouse is currently over this bed
   */
  public boolean isMouseOver() {
    float x = 0, y = 0;
    x = position[0];
    y = position[1];

    if (rotations == 0 || rotations % 2 == 0) {
      if (x - (image.width / 2) <= processing.mouseX) {
        if (processing.mouseX <= x + (image.width / 2)) {
          if (y - (image.height / 2) <= processing.mouseY) {
            if (y + (image.height / 2) >= processing.mouseY) {
              return true;
            }
          }
        }
      }
    }

    if (rotations != 0 && rotations % 2 != 0) { // when the furniture is vertical due to the
                                                // rotations
      if (x - (image.height / 2) <= processing.mouseX) {
        if (processing.mouseX <= x + (image.height / 2)) {
          if (y - (image.width / 2) <= processing.mouseY) {
            if (y + (image.width / 2) >= processing.mouseY) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  /**
   * keeps track with the number of rotations
   */
  public void rotate() {
    this.rotations = rotations + 1;

  }
}
