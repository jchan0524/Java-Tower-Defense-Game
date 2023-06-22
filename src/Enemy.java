
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// The enemy class extends GameObject and represents the enemy characters in the game
public class Enemy extends GameObject {

	// Image representing the enemy
	Image image;

	// Additional properties such as healing ability 
	int heal;

	// Wall object to check collision 
	Wall w;

	// Constructor initializes position, size, health, and sets up the image for the enemy
	public Enemy(int x, int y, int w, int h, int health, GamePanel p) {
		super(x, y, w, h, health, p);
		image = Toolkit.getDefaultToolkit().getImage("Images/basiczombie.gif");
	}

	// Method to check if the enemy is hitting a wall. Currently checks if the x position is 110
	public boolean hittingWall() {
		if (this.pos.x == 110) // (this.pos.intersects(w.pos))
			return true;
		else
			return false;
	}

	// Method to animate the enemy. If the enemy is hitting a wall, it stays in place, otherwise it moves to the left
	public void animate() {
		if (this.hittingWall())
			pos.x = pos.x;
		else
			pos.x--;

	}

	// Method to draw the enemy on the screen
	public void draw(Graphics g) {
		g.drawImage(image, pos.x, pos.y, pos.width, pos.height, game);

	}

}
