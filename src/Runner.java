
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// Runner class extends Enemy, representing a specific type of enemy in the game.
public class Runner extends Enemy {

	// The image to represent the runner enemy.
	Image image;

	// Constructor to create a new runner enemy with position (x, y), size (w, h), health, and parent GamePanel.
	public Runner(int x, int y, int w, int h, int health, GamePanel p) {
		// Call the constructor of the superclass, Enemy.
		super(x, y, w, h, health, p);
		// Set the position of the runner enemy.
		pos.x = x;
		pos.y = y;

		// Load the image for the runner enemy from file.
		image = Toolkit.getDefaultToolkit().getImage("Images/Runner.gif"); // runner picture
	}

	// The animate method updates the position of the runner enemy.
	public void animate() {
		 // If the runner enemy is hitting a wall, do not change its x position.
		if (this.hittingWall())
			pos.x = pos.x;
		else{
			 // Otherwise, move the runner enemy to the left by 2 units
			pos.x = pos.x - 2;
		}

	}

	// The draw method is used to draw the runner enemy on the screen.
	public void draw(Graphics g) {
		 // Draw the runner enemy image at its current position, with its width and height, within the parent GamePanel.
		g.drawImage(image, pos.x, pos.y, pos.width, pos.height, game);
	}

	// Method to check if the runner enemy is hitting a wall.
	public boolean hittingWall() {
		 // If the x-coordinate of the runner enemy's position is less than or equal to 108, return true (indicating it's hitting a wall). Otherwise, return false.
		if (this.pos.x <= 108) // (this.pos.intersects(w.pos))
			return true;
		else
			return false;
	}

}
