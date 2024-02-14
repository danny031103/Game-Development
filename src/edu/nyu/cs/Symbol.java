package edu.nyu.cs;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

public class Symbol extends PApplet {
    private Game app;
    private int xcordofsymbol; 
    private int ycordofsymbol; 
    private int r;
    private int g;
    private int b;

     /**
     * Constructor to create a symbol object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param xcordofsymbol the x coordinate of this object on the screen
     * @param ycordofsymbol the y coordinate of this object on the screen
     */
    public Symbol(Game app, int x, int y, int r, int g, int b) {
        this.app = app; 
        this.xcordofsymbol = x;
        this.ycordofsymbol = y;
        this.r=r;
        this.g=g;
        this.b=b;
    }
    /**
     * Draw this symbol's image to the screen at the coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.fill(r,g,b);
        this.ellipse(this.xcordofsymbol, this.ycordofsymbol,50,50);
    }
}
