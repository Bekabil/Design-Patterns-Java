package com.geometric.figures.java;
/**
 * Author Brahma Dathan
 * Date: January 15, 2015
 */
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
/**
 * Serves as a type for all figures in the simple
 * drawing program. Some implementation might be added 
 * at a future date.
 * This class Figure implements Serializable.
 * @author Brahma Dathan
 */
public abstract class Figure implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
 	* color is to store a particular color.	
*/	
	protected Color color;
/**
 	* initializes color
 	* @param color	
*/	
	public Figure(Color color) {
		this.color = color;
	}
/**
	 * Draws the figure using the given Graphics parameter
	 * @param graphics the Graphics object for drawing the figure
*/
	public void draw(Graphics graphics) {
		graphics.setColor(color);
	}
	
}

