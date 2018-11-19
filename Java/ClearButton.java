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
 * clears the room when the button is clicked
 * 
 * @author sycho
 *
 */
public class ClearButton extends Button {
  public ClearButton(float x, float y, PApplet processing) {
    super(x, y, processing);
    this.label = "Clear Room";
  }

  /* (non-Javadoc)
   * @ override
   * deletes every furniture in the room
   */
  public void mouseDown(Furniture[] furniture) {
    if (isMouseOver()) { // check if the mouse is over the button
      for (int i = 0; i < furniture.length; i++) { // loop through the array
        furniture[i] = null; // make every element null
      }
    }
  }
}
