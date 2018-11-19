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
 * @author sycho Button class for every button
 *
 */
public class Button implements DormGUI {
  private static final int WIDTH = 96; // width of the button
  private static final int HEIGHT = 32; // height of the button
  protected PApplet processing;
  private float[] position; // position of the button
  protected String label; // name for the button

  /**
   * constructor for the button
   * 
   * @param x x positon
   * @param y y position
   * @param processing
   */
  public Button(float x, float y, PApplet processing) {
    this.processing = processing;
    this.position = new float[] {x, y};
    this.label = "Button";
  }

  /*
   * @Override changes color of the button when the mouse is over
   */
  public void update() {
    if (this.isMouseOver()) { // color change
      processing.fill(100);
      processing.rect(position[0] - (WIDTH / 2), 40, position[0] + (WIDTH / 2), 8);
    }

    else {
      processing.fill(200);
      processing.rect(position[0] - (WIDTH / 2), 40, position[0] + (WIDTH / 2), 8);
    }
    processing.rect(position[0] - (WIDTH / 2), 40, position[0] + (WIDTH / 2), 8);
    processing.fill(0);
    processing.text(label, position[0], position[1]);
  }

  /*
   * @Override base for every button
   */
  public void mouseDown(Furniture[] furniture) {
    if (isMouseOver())
      System.out.println("A button was pressed.");
  }


  /*
   * @Override base for every button
   */
  public void mouseUp() {

  }

  /* 
   * @Override
   * check if the mouse is over the button
   */
  public boolean isMouseOver() {
    if (position[0] - (WIDTH / 2) <= processing.mouseX) {
      if (processing.mouseX <= position[0] + (WIDTH / 2)) {
        if (position[1] - (HEIGHT / 2) <= processing.mouseY) {
          if (position[1] + (HEIGHT / 2) >= processing.mouseY) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
