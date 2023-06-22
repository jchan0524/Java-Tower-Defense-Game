import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// The boss class extends Ememy and represents a boss enemy in the game
public class Boss extends Enemy {

	// Images for different states of the boss: idle, attacking and dying 
	Image idle;
	Image attack;
	Image death;

	// A flag to keep track if the boss has healed itself
	boolean healedSelf = false;

	// Constructor initializes position, size, health, and sets up the images
	public Boss(int x, int y, int w, int h, int health, GamePanel p) {
		super(x, y, w, h, health, p);
		idle = Toolkit.getDefaultToolkit().getImage("Images/Zombie boss.gif");
		attack = Toolkit.getDefaultToolkit().getImage("Images/Zombie boss attack.gif");
		death = Toolkit.getDefaultToolkit().getImage("Images/Zombie boss death.gif");
	}

	// Method to check if the boss is hitting the wall 
	public boolean hittingWall() {
		if (this.pos.x == 110)
			return true;
		else
			return false;
	}

	// Method to check if the boss is dead 
	public boolean bossDead() {
		if (this.returnHealth() <= 0)
			return true;
		else
			return false;
	}

	// Method to animate the boss. It takes care of movement and healing logic
	public void animate() {
		if (this.hittingWall()) {
			pos.x = pos.x;
		}
		if (health <= 90 && health >= 50 && !healedSelf) {
			if (!hittingWall()) {
				pos.x -= 2;
			}
		}
		if (health < 50 && !healedSelf) {
			if (pos.x < 1000) {
				pos.x += 1;
			}
		}

		// Heal logic: If the boss hasn't healed itself yet and health drops below 25, it will heal itself by 50
		if (!healedSelf && health < 25) {
			this.addHealth(50);
			healedSelf = true;

		}
		if (!hittingWall() && healedSelf) {
			if (pos.x > 120)
				pos.x -= 4;
			else
				pos.x -= 1;
		}

	}

	// Method to draw the boss on the screen based on its state
	public void draw(Graphics g) {
		if (this.hittingWall()) {
			if (health > 2) {
				g.drawImage(attack, pos.x, pos.y, pos.width, pos.height, game);
			} else if (health <= 7) {
				g.drawImage(death, pos.x, pos.y, pos.width, pos.height, game);
			}
		}
		if (health <= 90 && health >= 50 && !healedSelf) {
			if (!hittingWall()) {
				g.drawImage(attack, pos.x, pos.y, pos.width, pos.height, game);
			}
		}
		if (health < 50 && !healedSelf) {
			if (pos.x <= 1000) {
				g.drawImage(idle, pos.x, pos.y, pos.width, pos.height, game);
			}
		}
		if (!hittingWall() && healedSelf) {
			if (pos.x > 120)
				g.drawImage(attack, pos.x, pos.y, pos.width, pos.height, game);
		}
		if (health > 90) {
			g.drawImage(idle, pos.x, pos.y, pos.width, pos.height, game);
		}

	}

	// Method to check if the boss has healed itself
	public boolean hasHealedSelf() {
		return healedSelf;
	}

}
