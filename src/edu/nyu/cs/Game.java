package edu.nyu.cs;

import org.apache.commons.lang3.SystemUtils;
 
import processing.core.*; 

/*
 * A visually displayed, playable game of tic-tac-toe, where each player takes turns placing their- 
 * respective colored mark until one gets 3 in a row or a tie occurs.
 * 
 * @author https://github.com/danny031103
 * @version 0.1
 */

public class Game extends PApplet {
  //window size
	private static final int widthofwindow=600;
	private static final int heightofwindow=600;

  //grid
  int[][] grid = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

  //points
  //{x,y,r,g,b,p1taken?,p2taken?}
  int [][]points={{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,0,-1,-1},{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,-1,-1},{-50,0,0,255,239,-1,-1}};

  //images
  private PImage img1;
  private PImage img2;
  int yaxisstartimg1=50;
  int yaxisendimg1=475;
  int initialyimg1=yaxisstartimg1;
  int direction=5;

  int yaxisstartimg2=475;
  int yaxisendimg2=50;
  int initialyimg2=yaxisstartimg2;
  int directionimg2=-5;

  //turn
  int turntracker=1;
  int location=0;
  
  //keep playing
  int gameover=0;
  public int getGameOver(){
    return this.gameover;
  }
	/**
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {
    // set the cursor to crosshairs
    this.cursor(PApplet.CROSS);
    this.img1=loadImage("/Users/daniel.brito/Desktop/CS101/game-development-exercise-danny031103/images/me.png");
    img1.resize(110,110);
    this.img2=loadImage("/Users/daniel.brito/Desktop/CS101/game-development-exercise-danny031103/images/star.png");
    img2.resize(110,110);
	}
	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`, `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
	 */
	public void draw() {
    // fill the window
    this.background(0,255,239);
    
    //pictures
    //squirrel 
    image(img1, 35, initialyimg1); // draw the image at the current y-coordinate
    initialyimg1 += direction; // update the y-coordinate based on the speed
    if (initialyimg1 > yaxisendimg1 || initialyimg1 < yaxisstartimg1) { // if the image reaches the top or bottom, reverse direction
      direction = -direction;
    }
    //cat
    image(img2, 465, initialyimg2); 
    initialyimg2 += directionimg2; 
    if (initialyimg2 < yaxisendimg2 || initialyimg2 > yaxisstartimg2) { 
      directionimg2 = -directionimg2;
    }
  
    //draw lines for containing rock/paper/scissors
    this.line(225,150,225,450);
    this.line(375,150,375,450);
    this.line(150,225,450,225);
    this.line(150,375,450,375);

    //title
    this.textSize(15);
    String texttowrite="TIC-TAC-TOE (or Knots and Crosses)";
    this.fill(0,0,0);
    this.text(texttowrite, 170, 100);
    gameEnded();
    //drawing player moves
    if (this.gameover==0){
      for (int i=0;i<9;i++){
        this.fill(points[i][2], points[i][3], points[i][4]);
        this.ellipse(points[i][0], points[i][1], 50, 50);
      }
    }
    
    
    //announcing winner
    //player one wins
    else if (this.gameover==1){
      for (int i=0;i<9;i++){
        this.fill(points[i][2], points[i][3], points[i][4]);
        this.ellipse(points[i][0], points[i][1], 50, 50);
      }
      this.textSize(15);
      String texttowriteforwinner="PLAYER ONE WINS!";
      this.fill(0,0,0);
      this.text(texttowriteforwinner, 234, 500);
    }
    //player two wins
    else if (this.gameover==2){
      for (int i=0;i<9;i++){
        this.fill(points[i][2], points[i][3], points[i][4]);
        this.ellipse(points[i][0], points[i][1], 50, 50);
      }
      this.textSize(15);
      String texttowriteforwinnerr="PLAYER TWO WINS!";
      this.fill(0,0,0);
      this.text(texttowriteforwinnerr, 234, 500);
    }
    //tie
    else if (this.gameover==3){
      for (int i=0;i<9;i++){
        this.fill(points[i][2], points[i][3], points[i][4]);
        this.ellipse(points[i][0], points[i][1], 50, 50);
      }
      this.textSize(15);
      String texttowritefortie="TIE GAME!";
      this.fill(0,0,0);
      this.text(texttowritefortie, 256, 500);
    }
  }
	/**
	 * This method is automatically called by Processing every time the user clicks a mouse button.
	 * - The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   * - The `mouseButton` variable is automatically assigned the value of either the PApplet.LEFT or PApplet.RIGHT constants, depending upon which button was pressed.
   */
	public void mouseClicked() {
    //Divided board into zones from top left to bottom: A,B,C,D,E,F,G,H,I
		if (this.gameover==0){
      System.out.println(String.format("Mouse clicked at: %d:%d.", this.mouseX, this.mouseY));
      if ((this.mouseX>450||this.mouseX<150)||(this.mouseY>450||this.mouseY<150));
      
      else {
        ////////////////////////ODD PLAYER////////////////////
        //Zone A ODD
        if ((this.mouseX>150&this.mouseX<225)&&(this.mouseY>150&this.mouseY<225)&&turntracker%2!=0&&(points[0][0]!=187)){
          points[0][0]=187;
          points[0][1]=187;
          points[0][2]=255;
          points[0][3]=0;
          points[0][4]=0;
          points[0][5]=1;
          turntracker++;
        }
        //Zone B ODD
        else if ((this.mouseX>225&this.mouseX<375)&&(this.mouseY>150&this.mouseY<225)&&turntracker%2!=0&&(points[1][0]!=300)){
          points[1][0]=300;
          points[1][1]=187;
          points[1][2]=255;
          points[1][3]=0;
          points[1][4]=0;
          points[1][5]=1;
          turntracker++;
        }
        //Zone C ODD
        else if ((this.mouseX>375&this.mouseX<450)&&(this.mouseY>150&this.mouseY<225)&&turntracker%2!=0&&(points[2][0]!=412)){
          points[2][0]=412;
          points[2][1]=187;
          points[2][2]=255;
          points[2][3]=0;
          points[2][4]=0;
          points[2][5]=1;
          turntracker++;
        }
        //Zone D ODD
        else if ((this.mouseX>150&this.mouseX<225)&&(this.mouseY>225&this.mouseY<375)&&turntracker%2!=0&&(points[3][0]!=187)){
          points[3][0]=187;
          points[3][1]=300;
          points[3][2]=255;
          points[3][3]=0;
          points[3][4]=0;
          points[3][5]=1;
          turntracker++;
        }
        //Zone E ODD
        else if ((this.mouseX>225&this.mouseX<375)&&(this.mouseY>225&this.mouseY<375)&&turntracker%2!=0&&(points[4][0]!=300)){
          points[4][0]=300;
          points[4][1]=300;
          points[4][2]=255;
          points[4][3]=0;
          points[4][4]=0;
          points[4][5]=1;
          turntracker++;
        }
        //Zone F ODD
        else if ((this.mouseX>375&this.mouseX<450)&&(this.mouseY>225&this.mouseY<375)&&turntracker%2!=0&&(points[5][0]!=412)){
          points[5][0]=412;
          points[5][1]=300;
          points[5][2]=255;
          points[5][3]=0;
          points[5][4]=0;
          points[5][5]=1;
          turntracker++;
        }
        //Zone G ODD
        else if ((this.mouseX>150&this.mouseX<225)&&(this.mouseY>375&this.mouseY<450)&&turntracker%2!=0&&(points[6][0]!=187)){
          points[6][0]=187;
          points[6][1]=412;
          points[6][2]=255;
          points[6][3]=0;
          points[6][4]=0;
          points[6][5]=1;
          turntracker++;
        }
        //Zone H ODD
        else if ((this.mouseX>225&this.mouseX<375)&&(this.mouseY>375&this.mouseY<450)&&turntracker%2!=0&&(points[7][0]!=300)){
          points[7][0]=300;
          points[7][1]=412;
          points[7][2]=255;
          points[7][3]=0;
          points[7][4]=0;
          points[7][5]=1;
          turntracker++;
        }
        //Zone I ODD
        else if ((this.mouseX>375&this.mouseX<450)&&(this.mouseY>375&this.mouseY<450)&&turntracker%2!=0&&(points[8][0]!=412)){
          points[8][0]=412;
          points[8][1]=412;
          points[8][2]=255;
          points[8][3]=0;
          points[8][4]=0;
          points[8][5]=1;
          turntracker++;
        }
        ///////////////////////////////////EVEN PLAYER/////////////////////////////////////////
        //Zone A EVEN
        else if ((this.mouseX>150&this.mouseX<225)&&(this.mouseY>150&this.mouseY<225)&&turntracker%2==0&&(points[0][0]!=187)){
          points[0][0]=187;
          points[0][1]=187;
          points[0][2]=0;
          points[0][3]=255;
          points[0][4]=0;
          points[0][6]=2;
          turntracker++;
        }
        //Zone B EVEN
        else if ((this.mouseX>225&this.mouseX<375)&&(this.mouseY>150&this.mouseY<225)&&turntracker%2==0&&(points[1][0]!=300)){
          points[1][0]=300;
          points[1][1]=187;
          points[1][2]=0;
          points[1][3]=255;
          points[1][4]=0;
          points[1][6]=2;
          turntracker++;
        }
        //Zone C EVEN
        else if ((this.mouseX>375&this.mouseX<450)&&(this.mouseY>150&this.mouseY<225)&&turntracker%2==0&&(points[2][0]!=412)){
          points[2][0]=412;
          points[2][1]=187;
          points[2][2]=0;
          points[2][3]=255;
          points[2][4]=0;
          points[2][6]=2;
          turntracker++;
        }
        //Zone D EVEN
        else if ((this.mouseX>150&this.mouseX<225)&&(this.mouseY>225&this.mouseY<375)&&turntracker%2==0&&(points[3][0]!=187)){
          points[3][0]=187;
          points[3][1]=300;
          points[3][2]=0;
          points[3][3]=255;
          points[3][4]=0;
          points[3][6]=2;
          turntracker++;
        }
        //Zone E EVEN
        else if ((this.mouseX>225&this.mouseX<375)&&(this.mouseY>225&this.mouseY<375)&&turntracker%2==0&&(points[4][0]!=300)){
          points[4][0]=300;
          points[4][1]=300;
          points[4][2]=0;
          points[4][3]=255;
          points[4][4]=0;
          points[4][6]=2;
          turntracker++;
        }
        //Zone F EVEN
        else if ((this.mouseX>375&this.mouseX<450)&&(this.mouseY>225&this.mouseY<375)&&turntracker%2==0&&(points[5][0]!=412)){
          points[5][0]=412;
          points[5][1]=300;
          points[5][2]=0;
          points[5][3]=255;
          points[5][4]=0;
          points[5][6]=2;
          turntracker++;
        }
        //Zone G EVEN
        else if ((this.mouseX>150&this.mouseX<225)&&(this.mouseY>375&this.mouseY<450)&&turntracker%2==0&&(points[6][0]!=187)){
          points[6][0]=187;
          points[6][1]=412;
          points[6][2]=0;
          points[6][3]=255;
          points[6][4]=0;
          points[6][6]=2;
          turntracker++;
        }
        //Zone H EVEN
        else if ((this.mouseX>225&this.mouseX<375)&&(this.mouseY>375&this.mouseY<450)&&turntracker%2==0&&(points[7][0]!=300)){
          points[7][0]=300;
          points[7][1]=412;
          points[7][2]=0;
          points[7][3]=255;
          points[7][4]=0;
          points[7][6]=2;
          turntracker++;
        }
        //Zone I EVEN
        else if ((this.mouseX>375&this.mouseX<450)&&(this.mouseY>375&this.mouseY<450)&&turntracker%2==0&&(points[8][0]!=412)){
          points[8][0]=412;
          points[8][1]=412;
          points[8][2]=0;
          points[8][3]=255;
          points[8][4]=0;
          points[8][6]=2;
          turntracker++;
        }
      }
    }
  }
  /**
   * A method that can be used to check if a player has won.
   */
  public void gameEnded() {
    //top row p1 win
    if (points[0][5]+points[1][5]+points[2][5]==3) {
      this.gameover=1;
    }
    //top row p2 win
    else if (points[0][6]+points[1][6]+points[2][6]==6) {
      this.gameover=2;
    }
    //player one middle row
    else if(points[3][5]+points[4][5]+points[5][5]==3) {
      this.gameover=1;
    }
    //player two middle row
    else if(points[3][6]+points[4][6]+points[5][6]==6) {
      this.gameover=2;
    }
    //player one bottom row
    else if(points[6][5]+points[7][5]+points[8][5]==3) {
      this.gameover=1;
    }
    //player two bottom row
    else if(points[6][6]+points[7][6]+points[8][6]==6) {
      this.gameover=2;
    }
    //diagonal player one wins
    else if(points[0][5]+points[4][5]+points[8][5]==3) {
      this.gameover=1;
    }
    //diagonal player two wins
    else if(points[0][6]+points[4][6]+points[8][6]==6) {
      this.gameover=2;
    }
    //other diagonal player one wins
    else if(points[2][5]+points[4][5]+points[6][5]==3) {
      this.gameover=1;
    }
    //other diagonal player two wins
    else if(points[2][6]+points[4][6]+points[6][6]==6) {
      this.gameover=2;
    }
    //vertical left player one wins
    else if(points[0][5]+points[3][5]+points[6][5]==3) {
      this.gameover=1;
    }
    //vertical left player two wins
    else if(points[0][6]+points[3][6]+points[6][6]==6) {
      this.gameover=2;
    }
    //vertical middle player one wins
    else if(points[1][5]+points[4][5]+points[7][5]==3) {
      this.gameover=1;
    }
    //vertical middle player two wins
    else if(points[1][6]+points[4][6]+points[7][6]==6) {
      this.gameover=2;
    }
    //vertical right player one wins
    else if((points[2][5]+points[5][5]+points[8][5])==3){
      this.gameover=1;
    }
    //vertical right player one wins
    else if(points[2][6]+points[5][6]+points[8][6]==6) {
      this.gameover=2;
    }
    else if (turntracker==10){
      this.gameover=3;
    }
  }
  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		this.size(Game.widthofwindow, Game.heightofwindow);    
  }
  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }
}


