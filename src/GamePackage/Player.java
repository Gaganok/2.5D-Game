package GamePackage;

import java.util.ArrayList;

// EXTENDS AnimatedSprite
public class Player implements GameObject {

	private int missleCoolDown = 15, missleCounter = 0;
	private int xSize, ySize;
	private int movementMode;
	//0 - Down, 1 - Up, 2 - Right, 3 - Left, 4 - DownRight, 5 - DownLeft, 6 - UpRight, 7 - UpLeft
	private int direction, newDirection, oldDirection;
	private AnimatedSprite moveSprite;
	private SpriteSheet idleSheet;
	private int xPlayerPos, yPlayerPos;
	private Sprite player;
	private int speed;
	private int idleCounter;
	private int xZoom, yZoom;
	private RenderHandler renderer;
	private boolean moved = false;
	private GameKeyListener listener;
	private ArrayList<Missle> missleList;

	Player(GameKeyListener listener, RenderHandler renderer, SpriteSheet move, SpriteSheet idle, int speed, int xZoom, int yZoom){

		this.xZoom = xZoom;
		this.yZoom = yZoom;
		this.speed = speed;

		this.listener = listener;
		this.renderer = renderer;

		idleSheet = idle;

		//Default Down
		direction = 1;
		idleCounter = 0;
		movementMode = move.getHeight() / move.getSpriteHeight();

		listener.setMode(movementMode);

		moveSprite = new AnimatedSprite(move, speed, direction);

		missleList = new ArrayList<Missle>();

	}

	private void updateDirection() {

		int offset = 3;
		int xOffset = 0, yOffset = 0;


		if(listener.isRight()) {
			xOffset -= offset;
			updateMoved(2);
		}
		if(listener.isLeft()) {
			xOffset += offset;
			updateMoved(3);
		}
		if(listener.isDown()) {
			yOffset -= offset;
			updateMoved(0);
		}
		if(listener.isUp()) {
			yOffset += offset;
			updateMoved(1);
		}
		if(movementMode == 8) {
			if(listener.isDownRight()) 
				newDirection = 4;
			if(listener.isDownLeft()) 
				newDirection = 5;
			if(listener.isUpRight()) 
				newDirection = 6;	
			if(listener.isUpLeft()) 
				newDirection = 7;
		}
	
		if(moved) 
			renderer.camera.offset(xOffset, yOffset);
	}

	private void updateMoved(int newDirection) {
		this.newDirection = newDirection;
		moved = true;
	}

	private void updateMissle() {
		if(missleCoolDown < missleCounter) {
			if(listener.isMissle()) {
				missleList.add(new Missle(xPlayerPos, yPlayerPos, direction, 5, "fireBall.png"));
				missleCounter = 0;
			}
		}
		missleCounter++;

		if(missleList.size() > 0) { 

			for(Missle m : missleList) 
				m.update();

			int iterator = missleList.size();
			for(int i = 0; i < iterator; i++) {
				Missle missle = missleList.get(i);
				if(!missle.isAlive()) {
					missleList.remove(missle);
					missle = null;	
					iterator--;
				}
			}
		}
	}

	private void renderMissle() {
		for(Missle m : missleList) {
			renderer.renderSprite(m.getSprite(), m.getPositionX(), m.getPositionY(), 1, 1);
		}
	}

	@Override
	public void update() {
		updateDirection();
		updatePlayerPosition();	
		if(moved)
			updateSpriteState();	
		else 
			updateIdleState();
		updateMissle();
	}

	private void updateIdleState() {
		if(idleCounter < 2) {
			player = idleSheet.getSprite(oldDirection);
			direction = oldDirection;	
		}else 
			player = idleSheet.getSprite(direction);
		
	}

	private void updateSpriteState() {
		moveSprite.updateState(true);
		if(direction != newDirection) {
			oldDirection = direction;
			direction = newDirection;
			idleCounter = 0;
			moveSprite.updateSpriteSet(direction);
		}
		moveSprite.update();
		player = moveSprite.getSprite();
		idleCounter++;

		moved = false;

	}

	private void updatePlayerPosition() {
		Camera camera = renderer.camera;
		xPlayerPos = camera.w / 2 + camera.x;
		yPlayerPos = camera.h / 2 + camera.y;

	}

	@Override
	public void render() {

		renderer.renderSprite(player, xPlayerPos, yPlayerPos, xZoom, xZoom);
		if(missleList.size() > 0)
			renderMissle();
	}

	public Sprite getSprite() {
		return player;
	}

}
