package GamePackage;

import java.io.File;

public class Tile {
	private String title;
	private Sprite sprite;
	private int width, height;
	private int xPos, yPos;
	
	Tile(SpriteSheet sheet, int xSprite, int ySprite, int xPos, int yPos){
		this.width = sheet.getSpriteWidth();
		this.height = sheet.getSpriteHeight();
		
		this.xPos = xPos;
		this.yPos = yPos;
		
		sprite = sheet.getSprite(xSprite, ySprite);
	}
	
	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPosX() {
		return xPos;
	}

	public int getPosY() {
		return yPos;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
