
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// Projectile class extends GameObject, representing a projectile in the game.
public class Projectile extends GameObject {

	// The image to represent the projectile when it is moving.
	Image shoot;

	// Horizontal speed of the projectile.
	int Vx;

	// Vertical speed of the projectile.
	double Vy;

	// Constructor to create a new projectile with position (x, y), size (w, h),
	// health, parent GamePanel, and speeds (Vx, Vy).
	public Projectile(int x, int y, int w, int h, int health, GamePanel p, int Vx, double Vy) {
		// Call the constructor of the superclass, GameObject.
		super(x, y, w, h, health, p);

		// Load the image for the projectile from file.
		shoot = Toolkit.getDefaultToolkit().getImage("Images/Bullet1.png");

		// facingLeft=Toolkit.getDefaultToolkit().getImage("bulletleft.gif");

		// Set horizontal speed.
		this.Vx = Vx;
		// Set vertical speed.
		this.Vy = Vy;
		// Set health of the projectile to 1.
		this.health = 1;
		// isFacingRight=facing;
	}

	 // The draw method is used to draw the projectile on the screen.
	@Override
	public void draw(Graphics g) {
		// Draw the projectile image at its current position, with its width and height, within the parent GamePanel.
		g.drawImage(shoot, pos.x, pos.y, pos.width, pos.height, game);

	}

	// The animate method updates the position of the projectile.
	public void animate() {
		 // Update the x-coordinate of the position by adding the horizontal speed.
		pos.x += Vx;
		 // Update the y-coordinate of the position by adding the vertical speed.
		pos.y += Vy;
	}

	 // Method to check if the projectile is hitting a wall.
	@Override
	public boolean hittingWall() {
		return false;
	}

}
