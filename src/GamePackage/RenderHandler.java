package GamePackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class RenderHandler {
	private BufferedImage view;
	private int pixels[];
	public Camera camera;
	
	RenderHandler(int width, int height) {
		view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
		camera = new Camera(0, 0, width, height);
	}

	public void render(Graphics g) {

		/*for(int i = 0; i < pixels.length; i++) {
			int p = (int)(Math.random() * 0xFFFFFF);
			pixels[i] = p;
			//System.out.println(p); //WAAATTTT!!!!!!!!!!!!!!!!!!!!!!
		}*/
		g.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
	}

	public void refresh() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(Sprite renderSprite, int xPos, int yPos, int xZoom, int yZoom) {
		int[] renderPixels = renderSprite.getPixels();
		renderArray(renderPixels, renderSprite.getWidth(), renderSprite.getHeight(), xPos, yPos, xZoom, yZoom);
	}
	
	public void renderTileList(ArrayList<Tile> renderTileList, int xZoom, int yZoom) {
		for(Tile tile : renderTileList) {
			int[] renderPixels = tile.getSprite().getPixels();
			renderArray(renderPixels, tile.getWidth(), tile.getHeight(), tile.getPosX(), tile.getPosY(), xZoom, yZoom);
		}	
	}
	
	public void renderImage(BufferedImage img, int xPos, int yPos, int xZoom, int yZoom) {
		int[] renderPixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		renderArray(renderPixels, img.getWidth(), img.getHeight(), xPos, yPos, xZoom, yZoom);
	}
	
	public void fillCamera(Sprite sprite, int spriteWidth, int spriteHeight, int xZoom, int yZoom) {
		int incX = spriteWidth * xZoom;
		int incY = spriteHeight * yZoom;
		
		for(int y = camera.y - incY - (camera.y % incY); y < camera.y + camera.h; y += incY) 
			for(int x = camera.x - incX - (camera.x % incX); x < camera.x + camera.w; x += incX ) 
				renderSprite(sprite , x, y, xZoom, yZoom);
	}
	
	public void renderArray(int[] renderPixels, int width, int height, int xPos, int yPos, int xZoom, int yZoom) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				for(int yZoomPos = 0; yZoomPos < yZoom; yZoomPos++) {
					for(int xZoomPos = 0; xZoomPos < xZoom; xZoomPos++) {
						setPixel(renderPixels[x + y * width], x * xZoom + xZoomPos + xPos, y * yZoom + yZoomPos + yPos);
					} 
				}
			}
		}
	}
	
	public void setPixel(int pixel, int x, int y){
		
		if(x >= camera.x && y >= camera.y && x <= camera.x + camera.w && y <= camera.y + camera.h) {
			int pixelIndex = (x - camera.x) + (y - camera.y) * view.getWidth();
			if(pixels.length > pixelIndex && pixel != Game.alpha) {
				pixels[pixelIndex] = pixel;
			}
		}
		
	}
	
}
