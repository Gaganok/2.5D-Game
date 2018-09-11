package GamePackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener{
	private boolean up, down, right, left, downRight, downLeft, upRight, upLeft, space;
	private int mode;
	
	GameKeyListener(){
		up = false;
		down = false;
		right = false;
		left = false;
		downRight = false;
		downLeft = false;
		upRight = false;
		upLeft = false;
		space = false;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_Q)
			space = true;
		/*if(e.getKeyCode() == KeyEvent.VK_SPACE)
			space = true;*/
		if(e.getKeyCode() == KeyEvent.VK_UP)
			up = true;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			down = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
			right = true;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			left = true;
			
		if(mode == 8) {
			downRight = down && right;
			downLeft = down && left;
			upRight = up && right;
			upLeft = up && left;
		}
	}

	public boolean isUp() {
		return up;
	}
	public boolean isDown() {
		return down;
	}
	public boolean isRight() {
		return right;
	}
	public boolean isLeft() {
		return left;
	}
	public boolean isDownRight() {
		return downRight;
	}
	public boolean isDownLeft() {
		return downLeft;
	}
	public boolean isUpRight() {
		return upRight;
	}
	public boolean isUpLeft() {
		return upLeft;
	}
	public int getMode() {
		return mode;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) 
			up = false;
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			down = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
			right = false;
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			left = false;
		/*if(e.getKeyCode() == KeyEvent.VK_SPACE) 
			space = false;*/
		if(e.getKeyCode() == KeyEvent.VK_Q)
			space = false;
		if(mode == 8) {
			downRight = down && right;
			downLeft = down && left;
			upRight = up && right;
			upLeft = up && left;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public void setMode(int movementMode) {
		mode = movementMode;
	}
	public boolean isMissle() {
		return space;
	}
}
