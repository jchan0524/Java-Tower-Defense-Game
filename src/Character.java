import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// The Character class extends GameObject and represents the main player in the game
public class Character extends GameObject {

	// Images for the character: standing still and moving
	Image soldier;
	Image soldierMoving;

	// Various properties related to firing bullets 
	public int reloadTimer = 20;
	public int bulletDelay = 20;
	public int bulletsFired = 0;
	public int bulletLimit;
	public boolean tooManyBullets = false;
	public boolean hasReloaded = false; // use to teleport ammoDepot

	// Constructor initializes position, size, health, and sets up the images 
	public Character(int x, int y, int w, int h, int health, GamePanel p) {
		super(x, y, w, h, health, p);
		soldier = Toolkit.getDefaultToolkit().getImage("Images/soldiertrans.png");
		soldierMoving = Toolkit.getDefaultToolkit().getImage("Images/Move Right.png");

	}
	// Method to draw the character on the screen based on whether it is moving or not
	public void draw(Graphics g) {
		if (game.upPressed || game.downPressed)
			g.drawImage(soldierMoving, pos.x, pos.y, pos.width, pos.height, game);
		else
			g.drawImage(soldier, pos.x, pos.y, pos.width, pos.height, game);
	}

	// Method to fire bullets
	public void fire() {
		int x = 15; // speed change Vy
		double y = 0.1;
		game.gameObjects.add(0, new Projectile(pos.x, pos.y, 50, 25, 1, game, x, y));
		bulletsFired++;

		// Set the bullet limit based on the current level
		if (game.levelNum == 1) {
			bulletLimit = 10;
		} else if (game.levelNum == 3) {
			bulletLimit = 20;
		} else if (game.levelNum == 4) {
			bulletLimit = 30;
		} else if (game.levelNum == 5) {
			bulletLimit = 10;
		}
		if (bulletsFired >= bulletLimit)
			tooManyBullets = true;
	}

	// Method to move the character
	public void move(int x, int y) {
		pos.y += y;

		// Set limits for the character's position
		if (pos.y > 560)
			pos.y = 560;
		if (pos.y < -10)
			pos.y = -10;

		pos.x = 10;
	}

	// Method to animate the character
	public void animate() {
		if (pos.y < 400)
			pos.y += 4;
	}

	// Method to check if the character is hitting a wall. Currently always returns false
	@Override
	public boolean hittingWall() {
		return false;
	}
}
