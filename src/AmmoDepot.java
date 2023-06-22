import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// The AmmoDepot class extends GameObject and represents a depot that players can interact with in the game.
public class AmmoDepot extends GameObject {

	// Two images are created: one for when the ammo depot is not highlighted and another for when it is.
	Image AmmoDepot;
	Image AmmoDepotHighlighted;

	// The constructor takes in the position, size, health, and game panel 
	// It sets up the images and game panel
	public AmmoDepot(int x, int y, int w, int h, int health, GamePanel p) {
		super(x, y, w, h, health, p);
		// Gets the images from the given file paths.
		AmmoDepot = Toolkit.getDefaultToolkit().getImage("Images/AmmoBox.png");
		AmmoDepotHighlighted = Toolkit.getDefaultToolkit().getImage("Images/AmmoBoxH.png");
		p = game;
	}

	// The draw method takes a Graphics object and draws the image depending on whether or not the player is at the ammo depot. 
	public void draw(Graphics g) {
		if (game.atAmmoDepot == false)
			g.drawImage(AmmoDepot, pos.x, pos.y, pos.width, pos.height, game);
		else
			g.drawImage(AmmoDepotHighlighted, pos.x, pos.y, pos.width, pos.height, game);
	}

	// The teleport method is used to move the ammo depot to a new location if needed
	public void teleport(Boolean h) {
		if (h) {
			while (h) {
				// Updates the y position to a new value
				this.pos.y = this.pickNewLocation();
				h = false;
			}
		}
	}

	// This method randomly picks a new location for the ammo depot
	public int pickNewLocation() {
		return (int) (Math.random() * 570) - 10;
	}

	// Overridden method from the GameObject class. Currently always returns false, meaning this object cannot hit walls.
	@Override
	public boolean hittingWall() {
		return false;
	}

}
