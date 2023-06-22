import java.awt.Graphics;
import java.awt.Rectangle;

// GameObject is an abstract class that represents a general game object within the game
// It includes common properties likr position, health, and reference to the game panel. 

public abstract class GameObject {

	// Position and size of the GameObject represented as a Rectangle. 
	public Rectangle pos;

	// Reference to the GamePanel
	public GamePanel game;

	// Variable that stores the current time of the object
	double currentTime = 0;

	// Health of the GameObject
	public int health;

	// Constructor initializing position, size, health, and reference to the game panel
	public GameObject(int x, int y, int w, int h, int health, GamePanel p) {
		// Initialize the position and size using a Rectangle object
		pos = new Rectangle(x, y, w, h);
		// Reference to the game panel 
		game = p;
		// Set the health
		this.health = health;
	}

	// Method to update the currentTime variable and return the updated time
	public double updateTime(double time) {
		this.currentTime = time;
		return time;

	}

	// Abstract method to be implemented by the subclasses, defining how the GameObject should be drawn
	public abstract void draw(Graphics g);

	// Animate method currently empty, can be overridden by subclasses to define object specific animations
	public void animate() {

	}

	// Method to decrement the health of the GameObject by 1
	public void removeHealth() {
		this.health -= 1;
	}

	// Method to return the health of the GameObject
	public int returnHealth() {
		return this.health;
	}

	// Method to increment the health of the Gameobject by a specified amount 
	public void addHealth(int hE) {
		this.health += hE;
	}

	// Abstract method to be implemented by subclasses to check if GameObject is hitting a wall
	public abstract boolean hittingWall();
}
