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
 * Abstract interface for buttons and furnitures 
 * @author sycho
 *
 */
public interface DormGUI {
	public void update(); // update
	public void mouseDown(Furniture[] furniture); // when mouse is clicked
	public void mouseUp(); // when the button is released
	public boolean isMouseOver(); // checks if the mouse is over
}
