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
import java.util.ArrayList;

/**
 * @author sycho Run the code
 *
 */
public class Main {
  private PApplet processing;
  private PImage backgroundImage; // background image
  private ArrayList<DormGUI> guiObjects; // array list contains button and furniture class
  private final static int MAX_LOAD_FURNITURE = 100; // array list can contain upto 100 elements

  public Main(PApplet processing) {
    this.processing = processing;
    this.backgroundImage = processing.loadImage("images/background.png"); // load the background
                                                                          // image
    processing.background(95, 158, 160);
    processing.image(backgroundImage, processing.width / 2, processing.height / 2); // in the center
    this.guiObjects = new ArrayList<DormGUI>();
    guiObjects.add(new CreateFurnitureButton("Bed", 50, 24, processing)); // buttons are added to
                                                                          // the list
    guiObjects.add(new CreateFurnitureButton("Sofa", 150, 24, processing));
    guiObjects.add(new CreateFurnitureButton("Dresser", 250, 24, processing));
    guiObjects.add(new CreateFurnitureButton("Desk", 350, 24, processing));
    guiObjects.add(new CreateFurnitureButton("Sink", 450, 24, processing));
    guiObjects.add(new SaveButton(650, 24, processing));
    guiObjects.add(new LoadButton(750, 24, processing));
    guiObjects.add(new ClearButton(550, 24, processing));

  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Utility.startApplication();
  }

  /**
   * Keep the changes made
   */
  public void update() {
    processing.background(95, 158, 160);
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    for (DormGUI guiObject : guiObjects) { // update every button and furniture
      if (guiObject != null) {
        guiObject.update();
      }

    }

  }

  /**
   * When the button is clicked, create furniture or save, clear, or load the room When the
   * furniture is clicked, drag it
   */
  public void mouseDown() {

    Furniture[] furniture = extractFurnitureFromGUIObjects(); // to convert from DormGUI to
                                                              // Furniture class

    for (int j = 0; j < guiObjects.size(); j++) {
      guiObjects.get(j).mouseDown(furniture);
    }

    for (int i = 0; i < guiObjects.size(); i++)
      replaceFurnitureInGUIObjects(furniture); // Cast again from furniture class objects to DormGUI
                                               // objects

  }

  public void mouseUp() {

    for (int j = 0; j < guiObjects.size(); j++) {
      if (guiObjects.get(j).isMouseOver())
        guiObjects.get(j).mouseUp();
    }
  }

  public void keyPressed() {

    for (int i = 0; i < guiObjects.size(); i++)
      if (guiObjects.get(i) instanceof Furniture && guiObjects.get(i).isMouseOver()
          && (processing.key == 'r' || processing.key == 'R')) {
        ((Furniture) guiObjects.get(i)).rotate();
      }

    for (int i = 0; i < guiObjects.size(); i++)
      if (guiObjects.get(i) instanceof Furniture && guiObjects.get(i).isMouseOver()
          && (processing.key == 'd' || processing.key == 'D')) {
        guiObjects.remove(i);
      }

  }

  /**
   * This method creates a new Furniture[] for the old mouseDown() methods to make use of. It does
   * so by copying all Furniture references from this.guiObjects into a temporary array of size
   * MAX_LOAD_FURNITURE.
   * 
   * @return that array of Furniture references.
   */
  private Furniture[] extractFurnitureFromGUIObjects() {
    Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE]; // a new array of furniture
    int nextFreeIndex = 0;
    for (int i = 0; i < guiObjects.size() && nextFreeIndex < furniture.length; i++)
      if (guiObjects.get(i) instanceof Furniture)
        furniture[nextFreeIndex++] = (Furniture) guiObjects.get(i); // cast to furniture class
    return furniture;
  }

  /**
   * This method first removes all Furniture references from this.guiObjects, and then adds back in
   * all of the non-null references from it's parameter.
   * 
   * @param furniture contains the only furniture that will be left in this.guiObjects after this
   *        method is invoked (null references ignored).
   */
  private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
    for (int i = 0; i < guiObjects.size(); i++) // extract furniture class objects
      if (guiObjects.get(i) instanceof Furniture)
        guiObjects.remove(i--);
    for (int i = 0; i < furniture.length; i++)
      if (furniture[i] != null)
        guiObjects.add(furniture[i]);
  }
}
