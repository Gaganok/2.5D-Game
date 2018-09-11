package GamePackage;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickListener implements MouseListener {
	private SpriteSheet sheet;
	private int spriteWidth, spriteHeight;
	MouseClickListener(SpriteSheet sheet){
		this.sheet = sheet;
		spriteWidth = sheet.getSpriteWidth();
		spriteHeight = sheet.getSpriteHeight();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == e.BUTTON1) {
			Point point = e.getPoint();
			Game.renderTiles.add(new Tile(sheet, 5, 4, (int) point.getX(), (int) point.getY()));
			System.out.println("tyt");
		}
		System.out.println(e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
