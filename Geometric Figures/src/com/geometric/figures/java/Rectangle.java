package com.geometric.figures.java;
import java.awt.Color;
import java.awt.Graphics;
/**
 * @author	Bekabil Tolassa
 * Date:	May 28, 2015
 * Class:	ICS 372
 * Program:	Assignment 2
 * Purpose:	This class Rectangle extends abstract class Figure.	
 * 			It invokes super classe's constructor.
 * 			It draws filled rectangle of a particular color		
 */

public class Rectangle extends Figure {
	private int x, y, h, w;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
 	* invokes super classe's constructor 
 	* @param color 	
*/
	public Rectangle(Color color) {
		super(color);
	
	}
	
/**
 	* Draws the figure using the given Graphics parameter
 	* @param graphics the Graphics object for drawing the figure
 	* @param x, y, w, and h
*/	
	public void draw(Graphics graphics, int x, int y, int w, int h) {
		
		graphics.setColor(color);
		graphics.fillRect(x, y, w, h);
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		
	}
	public String toString() {
		return "Rectangle [x=" + x + ", y=" + y 
				+ ", Width=" + (w) 
				+ ", Height=" + (h) 
				+ ", Color=" + color + " ]\n";
	}

}


