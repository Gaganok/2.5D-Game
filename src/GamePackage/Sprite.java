package GamePackage;

import java.awt.image.BufferedImage;

public class Sprite {
	private int width, height;
	private int[] pixels;
	
	Sprite(SpriteSheet sheet, int startX, int startY, int width, int height){
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		sheet.getImage().getRGB(startX, startY, width, height, pixels, 0, width);
	}
	
	Sprite(BufferedImage img) {
		width = img.getWidth();
		height = img.getHeight();
		
		pixels = new int[width * height];
		img.getRGB(0, 0, width, height, pixels, 0, width);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	
	
}
