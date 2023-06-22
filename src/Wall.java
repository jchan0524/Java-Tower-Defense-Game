
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// The Wall class represents a wall object in the game, and extends GameObject.
public class Wall extends GameObject {
	// Images representing different states of the wall.
	Image wallGood;
	Image wallMedium;
	Image wallBad;

	// The number of frames to wait before allowing another hit.
	public int hitTimer = 10;

	// The delay between hits.
	public int hitDelay = 10;

	// Constructor for the Wall class.
	public Wall(int x, int y, int w, int h, int health, GamePanel p) {
		// Call the constructor of the superclass, GameObject.
		super(x, y, w, h, health, p);

		// Call the constructor of the superclass, GameObject.
		wallGood = Toolkit.getDefaultToolkit().getImage("Images/realWall2.jpg");
		wallMedium = Toolkit.getDefaultToolkit().getImage("Images/Wall1.png");
		wallBad = Toolkit.getDefaultToolkit().getImage("Images/wall2.gif");

		// Set the parent GamePanel reference.
		p = game;

	}

	// The draw method draws the wall based on its health.
	public void draw(Graphics g) {
		// If the health of the wall is greater than or equal to 2666, draw the wallGood
		// image.
		if (this.returnHealth() >= 2666) {
			g.drawImage(wallGood, pos.x, pos.y, pos.width, pos.height, game);
		}
		// If the health of the wall is greater than or equal to 1333 but less than
		// 2666, draw the wallMedium image.
		else if (this.returnHealth() >= 1333) {
			g.drawImage(wallMedium, pos.x, pos.y, pos.width, pos.height, game);
		}
		// If the health of the wall is less than 1333, draw the wallBad image.
		else {
			g.drawImage(wallBad, pos.x, pos.y, pos.width, pos.height, game);
		}

	}

	
	public boolean hittingWall() {
		return false;

	}

}
