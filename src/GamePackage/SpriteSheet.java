package GamePackage;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private int[] pixels;
	private BufferedImage image;
	private int sheetWidth, sheetHeight;
	private int spriteWidth, spriteHeight;
	private Sprite[] loadedSprites;
	private boolean loaded = false;

	
	SpriteSheet(BufferedImage img){
		image = img;
		sheetWidth = img.getWidth();
		sheetHeight = img.getHeight();
		
		pixels = new int[sheetWidth * sheetHeight];
		img.getRGB(0, 0, sheetWidth, sheetHeight, pixels, 0, sheetWidth);
	}
	
	public void loadSprites(int spriteWidth, int spriteHeight) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		
		loadedSprites = new Sprite[sheetWidth / spriteWidth * sheetHeight / spriteHeight];
		
		int spriteIndex = 0;
		this.spriteWidth = spriteWidth;
		for(int y = 0; y < sheetHeight; y += spriteHeight) {
			for(int x = 0; x < sheetWidth; x += spriteWidth) {	
				loadedSprites[spriteIndex] = new Sprite(this, x, y, spriteWidth, spriteHeight);
				spriteIndex++;
			}
		}
		loaded = true;
		
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public Sprite getSprite(int ID) {
		if(loaded) 
			return loadedSprites[ID];
		else 
			return null;
	}
	
	public Sprite getSprite(int x, int y) {
		return loadedSprites[x + y * (sheetWidth / spriteWidth)];
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getWidth() {
		return sheetWidth;
	}
	public int getLoadedLength() {
		return loadedSprites.length;
	}

	public int getHeight() {
		return sheetHeight;
	}
}
