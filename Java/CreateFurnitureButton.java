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
 * @author sycho Create a new furniture
 *
 */
public class CreateFurnitureButton extends Button {
  private String type;

  /**
   * constructor for the button
   * 
   * @param type type of furniture
   * @param x x position of the button
   * @param y y position of the button
   * @param processing
   */
  public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
    super(x, y, processing);
    this.label = "Create " + type; // name depends on the type of furniture
    this.type = type.toLowerCase(); // make it lowercase
  }

  /*
   * @Override Creates a new furniture depending on the type
   */
  public void mouseDown(Furniture[] furniture) {
    if (isMouseOver())
      for (int i = 0; i < furniture.length; i++) {
        if (furniture[i] == null) {
          furniture[i] = new Furniture(type, processing);
          break;
        }
      }
  }
}
