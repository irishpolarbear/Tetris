import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

//import java.awt.Graphics;


public class Board extends JPanel implements ActionListener {
	
	int[][] xy ;
		
	int maxHeight;
	int halfWidth;
	
	Block block;
	Timer timer;
	
	int curPiece = 1 ;
 
	int currX, curry;
	
	int firstX; 
	int firstY; 
	int secX;
	int secY;
	int thiX;
	int thiY;
	int fouX;
	int fouY; 
	
	int isEmpty;
	
	boolean fallingFin;
	
	
	//Board object created, instantiated. Timer created and starts. 
public Board(){
	 	setFocusable(true);
	 	requestFocusInWindow();
		xy = new int[20][10];
		maxHeight = 0;
		halfWidth = xy[0].length / 2 ;
		
		Thread t = new Thread(){
			public void run(){
				
			}
		};
		
		timer = new Timer(400, this);
		timer.start();
		
		addKeyListener(new TAdapter());
		
		
		
		System.out.println(maxHeight + "." + halfWidth);
	}

	//fills the array with 0s, thus initializing the game.
	public void fill(){
		for(int i = 0; i < xy.length; i++){
			for(int j = 0 ; j < xy[i].length ; j++){
				if(i == xy.length-1 || i == xy.length-2 || i == xy.length-3){
					xy[i][j] = 2;
				} else {
					xy[i][j] = 0 ;
				}
			}
		}
	}
	
	//calls fill() then selects a piece.
	public void start(){
		fill();
		fallingFin = false;
		selectPiece();
		timer.start();
	}
	
	public void pause(){
		timer.stop();
	}
	
	//randomly select a piece
	public void selectPiece(){
		int rand = (int)Math.ceil(Math.random()*7);
		if(rand == 1){	
			block = new boxBlock();
		} else if( rand == 2){
			block = new leftSquiggly();
		} else if( rand == 3){
			block = new rightSquiggly();
		} else if( rand == 4){
			block = new lineBlock();
		} else if( rand == 5){
			block = new tBlock();
		} else if( rand == 6){
			block = new LShape();
		} else if( rand == 7){
			block = new RevLShape();
		} else {
			System.out.println("System error, random number not specified" + rand);
		}
		spawnPiece();
	}
	
	//'Spawn' a block into the game world and array.
	public void spawnPiece(){
		int ex, ey, fx, fy, gx, gy, hx, hy;
		
		ex = block.getEx();
		ey = block.getEy();
		fx = block.getFx();
		fy = block.getFy();
		gx = block.getGx();
		gy = block.getGy();
		hx = block.getHx();
		hy = block.getHy();
		
		boolean setFi = false, setTw = false, setTh =  false, setFo =  false ;
		
		firstX = halfWidth + ex;
		firstY = maxHeight + ey + 1;
		secX = halfWidth + fx;
		secY = maxHeight + fy + 1;
		thiX = halfWidth + gx;
		thiY = maxHeight + gy + 1;
		fouX = halfWidth + hx;
		fouY = maxHeight + hy + 1;
		
		int i, j ;
		for(i = 0 ; i < xy.length ; i++){
			for(j = 0; j < xy[i].length ; j++){
				if(firstX == i && firstY == j){
					ex = i;
					ey = j;
					if(xy[j][i] == 0){
						xy[j][i] = curPiece;
					} else {
						System.out.println("Game over.");
						timer.stop();
						System.exit(0);
					}
					setFi = true;
				}
				if(secX == i && secY == j){
					fx = i;
					fy = j;
					xy[j][i] = curPiece;
					setTw = true;
				}
				if(thiX == i && thiY == j){
					gx = i;
					gy = j;
					xy[j][i] = curPiece;
					setTh = true;
				}
				if(fouX == i && fouY == j){
					hx = i;
					hy = j;
					xy[j][i] = curPiece;
					setFo = true ;
				}
			}
		}
		
		if(setFi && setTw && setTh && setFo){
			curPiece++;
			moveDown();
		}
	}
	
	//check below the block to see if it's clear to move down //string
	public boolean checkBelow(int x, int y, String Dir){

		boolean returnB = false ;
		if(Dir.equals("L")){
			if(x-1 >= 0){
				if(xy[y][x-1] == 0 || xy[y][x-1] == (curPiece-1)){
					returnB = true;
				} else {
					System.out.println(x-1 + "," + (y) + "=" + xy[y][x-1] + ",," + curPiece);
					returnB = false;
					
				}
			}
		} else if(Dir.equals("R")){
			if(x+1 < xy[0].length){
				if(xy[y][x+1] == 0 || xy[y][x+1] == (curPiece-1)){
					returnB = true;
				} else {
					System.out.println(x+1 + "," + (y) + "=" + xy[y][x+1] + ",," + curPiece);
					returnB = false;
					
				}
			}
		} else if(Dir.equals("D")){
			if(y+1 < xy.length){
				if(xy[y+1][x] == 0 || xy[y+1][x] == (curPiece-1)){
					returnB = true;
				} else {
					returnB = false;
					
				}
			} else {
				System.out.println("Reached Bottom");
				returnB = false;
			}
		} else {
			returnB = false;
		}
		
		return returnB;
	}
	
	public void actionPerformed(ActionEvent e){
		if(fallingFin){
			fallingFin = false;
			selectPiece();
		} else {
			moveDown();
		}
	}
	
	//Checks if the piece can be moved down a level, to the left or to the right and if so moves the whole piece down / across.
	public void moveDown(){
		
		boolean first, second, third, fourth;
		int temp1, temp2, temp3, temp4;
		
			first = checkBelow(firstX, firstY, "D");
			second = checkBelow(secX, secY, "D");
			third = checkBelow(thiX, thiY, "D");
			fourth = checkBelow(fouX, fouY, "D");
			
			
				while(first && second && third && fourth){
					temp1 = xy[firstY][firstX];
					temp2 = xy[secY][secX];
					temp3 = xy[thiY][thiX];
					temp4 = xy[fouY][fouX];
					
					xy[firstY][firstX] = 0;
					firstY++;	
					xy[secY][secX] = 0;
					secY++;		
					xy[thiY][thiX] = 0;
					thiY++;
					xy[fouY][fouX] = 0;
					fouY++;
					
					xy[firstY][firstX] = temp1;
					xy[secY][secX] = temp2;
					xy[thiY][thiX] = temp3;
					xy[fouY][fouX] = temp4;
			
					first = checkBelow(firstX, firstY, "D");
					second = checkBelow(secX, secY, "D");
					third = checkBelow(thiX, thiY, "D");
					fourth = checkBelow(fouX, fouY, "D");
					draw();
				}
			fallingFin = true;
			checkLine();		
	}
	
	public void moveLeft(){
		
		boolean first, second, third, fourth;
		int temp1, temp2, temp3, temp4; 
		
		first = checkBelow(firstX, firstY, "L");
		second = checkBelow(secX, secY, "L");
		third = checkBelow(thiX, thiY, "L");
		fourth = checkBelow(fouX, fouY, "L");
		
		System.out.println(first + " " + second + " " + third + " " + fourth);
		
			while(first && second && third && fourth){
				temp1 = xy[firstY][firstX];
				temp2 = xy[secY][secX];
				temp3 = xy[thiY][thiX];
				temp4 = xy[fouY][fouX];
				
				xy[firstY][firstX] = 0;
				firstX--;	
				xy[secY][secX] = 0;
				secX--;		
				xy[thiY][thiX] = 0;
				thiX--;
				xy[fouY][fouX] = 0;
				fouX--;
				
				xy[firstY][firstX] = temp1;
				xy[secY][secX] = temp2;
				xy[thiY][thiX] = temp3;
				xy[fouY][fouX] = temp4;
		
				first = checkBelow(firstX, firstY, "L");
				second = checkBelow(secX, secY, "L");
				third = checkBelow(thiX, thiY, "L");
				fourth = checkBelow(fouX, fouY, "L");
				System.out.println(first + " " + second + " " + third + " " + fourth);
				draw();
			}
			
			
	}
	
	public void moveRight(){
		boolean first, second, third, fourth;
		int temp1, temp2, temp3, temp4; 
		
		first = checkBelow(firstX, firstY, "R");
		second = checkBelow(secX, secY, "R");
		third = checkBelow(thiX, thiY, "R");
		fourth = checkBelow(fouX, fouY, "R");
		
		System.out.println(first + " " + second + " " + third + " " + fourth);
		
			while(first && second && third && fourth){
				temp1 = xy[firstY][firstX];
				temp2 = xy[secY][secX];
				temp3 = xy[thiY][thiX];
				temp4 = xy[fouY][fouX];
				
				xy[firstY][firstX] = 0;
				firstX++;	
				xy[secY][secX] = 0;
				secX++;		
				xy[thiY][thiX] = 0;
				thiX++;
				xy[fouY][fouX] = 0;
				fouX++;
				
				xy[firstY][firstX] = temp1;
				xy[secY][secX] = temp2;
				xy[thiY][thiX] = temp3;
				xy[fouY][fouX] = temp4;
		
				first = checkBelow(firstX, firstY, "R");
				second = checkBelow(secX, secY, "R");
				third = checkBelow(thiX, thiY, "R");
				fourth = checkBelow(fouX, fouY, "R");
				System.out.println(first + " " + second + " " + third + " " + fourth);
				draw();
			}
			
			
	}
	
	public void rotation(){
		
	}
	
	public void checkLine(){
		int i = xy.length - 1,j,k, l;
		boolean lineComplete = true;
		
		while(i > 0){	
			for(j = 0 ; j <= xy[i].length-1 ; j++){
				if(xy[i][j] == 0){
					lineComplete = false ;
					
				}
			}
			if(lineComplete){
				for(k = i; k >0; k-- ){
					for(l = 0 ; l <= xy[i].length-1 ; l++){
						xy[k][l] = xy[k-1][l];
					}
				}
				
			} else {
				i--;
			}
			lineComplete = true;
		}
		draw();
		
	}
	
class TAdapter extends KeyAdapter{	
	public void keyboard(KeyEvent e){
		int keycode = e.getKeyCode();
		
		/*if(keycode == KeyEvent.VK_LEFT){
			System.out.println("PRESSED LEFT");
			moveLeft();
		} else if(keycode == KeyEvent.VK_RIGHT){
			moveRight();
		} else if(keycode == KeyEvent.VK_UP){
			
		} else if(keycode == 'h'){
			System.out.println("PRESSED H");
		} else if(keycode == 'p'){
			pause();
		}*/
		
		 if (keycode == 'p' || keycode == 'P') {
             pause();
             return;
         }
		
		switch (keycode){
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case 'h':
			System.out.println("H was pressed");
			break;
		
		}
	}
}
	
	/*public void paint(Graphics g){
		super.paint(g);
		
		drawSquare(g, 0,0);
	}*/
	
	public void draw(){
		for(int i = 0; i < xy.length; i++){
			for(int j = 0 ; j < xy[i].length ; j++){
				System.out.print(xy[i][j]);
			}
			System.out.println("");
		}
	}
}
