package GamePackage;

public class Camera {
	public int x, y, w, h;
	
	Camera(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	Camera(){
		this(0, 0, 0, 0);
	}
	
	public void offset(int x, int y) {
		this.x -= x;
		this.y -= y;
	}
}
