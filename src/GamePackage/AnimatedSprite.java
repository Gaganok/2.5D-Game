package GamePackage;

public class AnimatedSprite  implements GameObject{

	private Sprite[] spriteSet;
	private int width, height;
	private int currentSprite, counter;
	private int speed;
	private SpriteSheet sheet;
	private boolean animate = false;
	//1 - Down, 2 - Up, 3 - Right, 4 - Left
	

	AnimatedSprite(SpriteSheet sheet, int speed, int layer){
		this.width = sheet.getSpriteWidth();
		this.height = sheet.getSpriteHeight();
		this.speed = speed;
		this.sheet = sheet;
		
		spriteSet = new Sprite[sheet.getWidth()/ width];
		
		updateSpriteSet(layer);
		
		reset();
	}
	AnimatedSprite(SpriteSheet sheet, int width, int height, int speed){
		this(sheet, speed, 0);
	}

	public Sprite getSprite() {
		return spriteSet[currentSprite];
	}
	
	private void reset() {
		currentSprite = 0;
		counter = 0;
	}
	
	public void updateSpriteSet(int layer) {
		int arrayLayer = layer * spriteSet.length;
		for(int i = 0; i < spriteSet.length; i++) 
			spriteSet[i] = sheet.getSprite(i + arrayLayer);
	}
	
	public void updateState(boolean animate) {
		this.animate = animate;
	}
	
	@Override
	public void update() {
		if(animate) {
			counter++;
			if(counter == speed) {
				incrementSprite();
				counter = 0;
			}
		} else 
			reset();
	}

	private void incrementSprite() {
		currentSprite++;
		if(currentSprite == spriteSet.length) 
			currentSprite = 0;	
	}

	@Override
	public void render() {}


}
