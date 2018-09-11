package GamePackage;

public class Missle implements GameObject{
	
	private final int lifeTime = 100;
	private int lifeTimeCounter;
	private int xPos, yPos;
	private int xStart, yStart;
	private int xZoom, yZoom;
	//private Sprite sprite;
	private AnimatedSprite sprite;
	private int xDirection, yDirection, speed;
	private boolean live;
	
	Missle(int xPos, int yPos, int direction, int speed, String path){
		sprite = new AnimatedSprite(Game.fireBall, 3, (int) Math.random() * 1);
		sprite.updateState(true);
		
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.speed = speed;
		
		xStart = xPos;
		yStart = yPos;
		
		live = true;
		
		lifeTimeCounter = 1;
		
		defineDirection(direction);
	}
	
	private void defineDirection(int dir) {
		switch(dir) {
		case 0: 
			yDirection = 1;
			break;
		case 1: 
			yDirection = -1;
			break;
		case 2:
			xDirection = 1;
			break;
		case 3:
			xDirection = -1;
			break;
		case 4:
			xDirection = 1;
			yDirection = 1;
			break;
		case 5:
			xDirection = -1;
			yDirection = 1;
			break;
		case 6:
			xDirection = 1;
			yDirection = -1;
			break;
		case 7:
			xDirection = -1;
			yDirection = -1;
			break;
		} 
			
		
	}

	@Override
	public void update() {
		updatePosition();
		sprite.update();
	}

	@Override
	public void render() {}
	
	private void updatePosition() {
		live = lifeTimeCounter < lifeTime;
 		xPos += speed * xDirection;
 		yPos += speed * yDirection;
 		lifeTimeCounter++;	
 		
	}
	

	public Sprite getSprite() {
		return sprite.getSprite();
	}
	public int getPositionX() {
		return xPos;
	}
	public int getPositionY() {
		return yPos;
	}
	public boolean isAlive() {
		return live;
	}
	
	

}
