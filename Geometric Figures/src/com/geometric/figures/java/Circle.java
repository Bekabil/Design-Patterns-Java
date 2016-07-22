package com.geometric.figures.java;
import java.awt.Color;
import java.awt.Graphics;

/**
 * @author	Bekabil Tolassa
 * Date:	May 28, 2015
 * Class:	ICS 372
 * Program:	Assignment 2
 * Purpose:	This class Circle extends abstract class Figure.	
 * 			It invokes super classe's constructor.
 * 			It draws filled circle (oval) of a particular color		
*/
public class Circle extends Figure{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y, h, w;
/**
 	* invokes super classe's constructor 
 	* @param color 	
*/
	public Circle(Color color) {
		super(color);
		
	}
/**
 	* Draws the figure using the given Graphics parameter
 	* @param graphics the Graphics object for drawing the figure
 	* @param x, y, w, and h
*/
	public void draw(Graphics graphics, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		graphics.setColor(color);
		graphics.fillOval(x, y, w, w);
		//graphics.dr
	}
	public String toString() {
		return "Circle [x=" + x + ", y=" + y 
				+ ", radius=" + (w / 2) 
				+ ", Color=" + color + " ]\n";
	}
	
}
