package GamePackage;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame implements Runnable {

	public static int alpha = -65316;
	protected static SpriteSheet fireBall;
	private final int WIDTH = 800, HEIGHT = 600;
	private int xZoom = 3, yZoom = 3, xZoomPlayer = 3, yZoomPlayer = 3, playerSpeed = 10;
	private Canvas canvas = new Canvas();
	private RenderHandler renderer;
	private BufferedImage grassTile;
	public static ArrayList<Tile> renderTiles;
	private SpriteSheet tileSheet, personSheet, guyLad, idleGuyLad;
	private Sprite grassTile1, grassTile2;
	private Player player;


	public Game() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(0, 0, WIDTH, HEIGHT);

		setLocationRelativeTo(null);

		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(canvas);
		pack();
		setVisible(true);

		canvas.createBufferStrategy(3);
		renderer = new RenderHandler(WIDTH, HEIGHT);

		tileSheet = new SpriteSheet(loadImage("Tiles1.png"));
		personSheet = new SpriteSheet(loadImage("personKnight.png"));
		guyLad = new SpriteSheet(loadImage("guyLad.png"));
		idleGuyLad = new SpriteSheet(loadImage("idleGuy.png"));

		tileSheet.loadSprites(16, 16);
		personSheet.loadSprites(65, 64);
		guyLad.loadSprites(22, 33);
		idleGuyLad.loadSprites(30, 48);

		grassTile1 = tileSheet.getSprite(0,2);
		//grassTile2 = new Tile(tileSheet, 0, 1);

		GameKeyListener gameKeyListener = new GameKeyListener();

		canvas.addKeyListener(gameKeyListener);
		canvas.addMouseListener(new MouseClickListener(tileSheet));

		canvas.setFocusable(true);
		canvas.requestFocus();

		player = new Player(gameKeyListener, renderer, guyLad, idleGuyLad, playerSpeed, xZoomPlayer, yZoomPlayer);
		renderTiles = new ArrayList<Tile>();
		renderTiles.add(new Tile(tileSheet, 6, 4, 300, 300));
		
		/*alphaColorUSwapping("idleGuy.png");
		BufferedImage[] img = loadImageArray("i", 8);
		compoundImageY(img, "idleGuy", 30, 48);
		*/
		fireBall = new SpriteSheet(loadImage("fireBallXD2.png"));
		fireBall.loadSprites(128, 128);

		
		//alphaColorUSwapping("gaganok.png");


	}

	private BufferedImage[] loadImageArray(String folder, int x) {
		BufferedImage[] img = new BufferedImage[x];
		for(int i = 0; i < img.length; i++) {
			img[i] = loadImage( "\\" + folder + "\\"  + i + ".png");
		}
		//+ "hypnofireball"
		return img;
	}

	private void compoundImageX(BufferedImage[] img, String newImgName, int x, int y) {
		try {
			BufferedImage result = new BufferedImage(x * img.length, y, BufferedImage.TYPE_INT_RGB);
			int[] resultPix = ((DataBufferInt)result.getRaster().getDataBuffer()).getData();
			int[] imgPix;
			for(int i = 0; i < img.length; i++) {
				imgPix = ((DataBufferInt) img[i].getRaster().getDataBuffer()).getData();
				for(int b = 0; b < y; b++) 
					for(int j = 0; j < x; j++)
						resultPix[j + x * i  + (b * result.getWidth())] = imgPix[j + b * x];
			}
			renderer.renderImage(result, 100, 100, 1, 1);
			ImageIO.write(result, "png", new File (newImgName+".png"));

		} catch (IOException e) {}
	}
	private void compoundImageY(BufferedImage[] img, String newImgName, int x, int y) {
		try {
			BufferedImage result = new BufferedImage(x, img.length * y, BufferedImage.TYPE_INT_RGB);
			int[] resultPix = ((DataBufferInt)result.getRaster().getDataBuffer()).getData();
			int[] imgPix;
			for(int i = 0; i < img.length; i++) {
				imgPix = ((DataBufferInt) img[i].getRaster().getDataBuffer()).getData();
				for(int j = 0; j < imgPix.length; j++) 
					resultPix[j + i * y * x] = imgPix[j];
			}
			renderer.renderImage(result, 100, 100, 1, 1);
			ImageIO.write(result, "png", new File (newImgName+".png"));

		} catch (IOException e) {}
	}

	public static BufferedImage loadImage(String path) {
		try {
			BufferedImage loadImage = ImageIO.read(Game.class.getResource(path));
			BufferedImage formattedImage = new BufferedImage(loadImage.getWidth(), loadImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			formattedImage.getGraphics().drawImage(loadImage, 0, 0, null);

			return formattedImage;
		} catch (IOException e) {
			return null;
		}


	}

	public void alphaColorUSwapping(String path) {
		try {
			BufferedImage img = loadImage(path);
			int imgPixels[] = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
			int swapColor = imgPixels[0];

			for(int i = 0; i < imgPixels.length; i++) 
				if(imgPixels[i] == swapColor) 
					imgPixels[i] = alpha;

			ImageIO.write(img, "png", new File("fireBallXD2.png"));
			System.out.println("Tyt");
		} catch(IOException e ) {}
	}

	public void run() {

		long lastTime = System.currentTimeMillis();
		int renderDelay = 17; // 17 = 60FPS
		System.out.println("Canvas.x: "+ canvas.getWidth() + " canvs.y: " + canvas.getHeight() + "\n Frame.x: " + this.getWidth() + " Frame.y: " + this.getHeight());
		while(true) {
			if(System.currentTimeMillis() - lastTime >= renderDelay) {
				update();
				render();
				lastTime += renderDelay;
			}
		}
	}

	public void render() {

		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		super.paint(g);



		renderer.fillCamera(grassTile1, 16, 16, xZoom, yZoom);

		renderer.renderTileList(renderTiles, xZoom, yZoom);


		player.render();

		renderer.render(g);
		g.dispose();

		bs.show();

		renderer.refresh();
	}

	public void update() {
		player.update();
	}

	public static void main(String[] args) {

		Thread gameThread = new Thread(new Game());
		gameThread.start();

	}

}
