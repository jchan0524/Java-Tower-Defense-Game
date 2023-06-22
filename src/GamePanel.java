import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.*;

import java.util.ArrayList;
import javax.swing.JPanel;

// Creating a custom GamePanel class that extends JPanel and implements KeyListener
public class GamePanel extends JPanel implements KeyListener {

	// Class Variables
	public ArrayList<GameObject> gameObjects; // Array List to store different game objects

	// Instances of game characters and elements
	private Character mainCharacter;
	private AmmoDepot ammoBox;
	private Wall w;
	private Boss b;

	// Flags and other variables to keep track of the game state
	boolean gameRunning;
	public GameThread thread;
	public double tick = 1;
	public boolean lost = false;
	public boolean won = false;
	public int numEnemiesKilled = 0;
	public int tNEK = 0;
	public int numEnemies;
	public int levelNum;
	public int numEnemiesHittingWall;

	// Flags for keys pressed
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean spacePressed;
	public boolean rPressed;
	public boolean atAmmoDepot;

	// Images for backgrounds and cutscenes
	public Image background;
	public Image background2;
	public Image background3;
	public Image background4;
	public Image background5;
	public Image cutScene;
	public Image winCutScene;
	public Image loseCutScene;

	private BufferedImage image;

	public GameFrame gameFrame;

	// Constructor
	public GamePanel(GameFrame gameApplet) {

		// Initializing variables and setting up the game
		this.gameFrame = gameApplet;
		this.setFocusable(true);
		gameObjects = new ArrayList<GameObject>();
		this.addKeyListener(this);
		w = new Wall(90, -10, 50, 900, 4000, this);
		mainCharacter = new Character(20, 400, 100, 100, 100, this);
		ammoBox = new AmmoDepot(20, 100, 30, 30, 100, this);
		b = new Boss(1000, 350, 150, 150, 100, this);
		gameObjects.add(w);
		gameObjects.add(mainCharacter);
		gameObjects.add(ammoBox);
		this.setBackground(Color.black);

		// Load images
		background = Toolkit.getDefaultToolkit().getImage("Images/background.gif");
		background2 = Toolkit.getDefaultToolkit().getImage("Images/Hell2.jpg");
		background3 = Toolkit.getDefaultToolkit().getImage("Images/Hell1.jpg");
		background4 = Toolkit.getDefaultToolkit().getImage("Images/Hell3.jpg");
		background5 = Toolkit.getDefaultToolkit().getImage("Images/Hell4.jpg");
		cutScene = Toolkit.getDefaultToolkit().getImage("Images/CS Final Open.gif");
		winCutScene = Toolkit.getDefaultToolkit().getImage("Images/win2.gif");
		loseCutScene = Toolkit.getDefaultToolkit().getImage("Images/lose2.gif");

	}

	// Method to check if the game has finished
	public void hasFinishedGame(boolean d, int h) {
		// Logic for ending the game
		if (h <= 0 || d == true) {
			thread.endGame();
			gameRunning = false;
			if (h <= 0) {
				lost = true;
			} else if (d == true) {
				won = true;
			}
		}
	}

	// Overriding paintComponent method to draw on the JPanel 
	@Override
	public void paintComponent(Graphics g) {

		// Switch case to load the right background based on level 
		switch (levelNum) {
			case 0:
				super.paintComponent(g);
				g.drawImage(cutScene, 0, 0, 1400, 700, this);
				break;
			case 1:
				super.paintComponent(g);
				g.drawImage(background, 0, 0, 1400, 700, this);
				break;
			case 2:
				super.paintComponent(g);
				g.drawImage(background2, 0, 0, 1400, 700, this);
				break;
			case 3:
				super.paintComponent(g);
				g.drawImage(background3, 0, 0, 1400, 700, this);
				break;
			case 4:
				super.paintComponent(g);
				g.drawImage(background4, 0, 0, 1400, 700, this);
				break;
			case 5:
				super.paintComponent(g);
				g.drawImage(background5, 0, 0, 1400, 700, this);
				break;
		}

		// Draws Game objects
		if (gameRunning) {
			try {
				for (GameObject object : gameObjects) {
					object.draw(g);
				}
			} catch (Exception e) {
			}
			;
			mainCharacter.draw(g);
			ammoBox.draw(g);
			w.draw(g);

			// Displays the game state and character's information
			Font bold = new Font("System", Font.BOLD, 16);
			Font boldER = new Font("System", Font.BOLD, 18);
			g.setFont(bold);
			g.setColor(Color.WHITE);
			g.drawString("Number of enemies killed:" + tNEK, 40, 50);
			g.drawString("Level:" + levelNum, 40, 10);
			if (levelNum == 5) {
				g.setColor(Color.BLUE);
				g.drawString("Boss Health: " + b.returnHealth(), b.pos.x, b.pos.y + 25);
			}
			g.setColor(Color.red);
			g.drawString("Time Survived:" + mainCharacter.updateTime(thread.time), 40, 70);
			if (w.health >= 2666) {
				g.setColor(Color.GREEN);
				g.drawString("Wall Health:" + w.returnHealth(), 40, 90);
			} else if (w.health >= 1333) {
				g.setColor(Color.YELLOW);
				g.drawString("Wall Health:" + w.returnHealth(), 40, 90);
			} else {
				g.setColor(Color.RED);
				g.drawString("Wall Health:" + w.returnHealth(), 40, 90);
			}

			if (!mainCharacter.tooManyBullets) {
				g.setFont(bold);
				g.setColor(Color.WHITE);
				g.drawString("Shots remaning:" + (mainCharacter.bulletLimit - mainCharacter.bulletsFired), 40, 30);
			} else {
				g.setFont(boldER);
				g.setColor(Color.RED);
				g.drawString("RELOAD", 40, 30);
			}

		} else {

			if (won) {
				g.drawImage(winCutScene, 0, 0, 1400, 700, this);
			} else if (lost) {
				g.drawImage(loseCutScene, 0, 0, 1400, 700, this);
			}

		}

	}

	// Method to animate game objects.
	public void animate() {
		// This method contains the game logic for animation, movements, collisions, reloading, etc.
		mainCharacter.reloadTimer -= tick;
		if (mainCharacter.pos.y <= ammoBox.pos.y + 10 && mainCharacter.pos.y >= ammoBox.pos.y - 50)
			atAmmoDepot = true;
		else
			atAmmoDepot = false;

		if (upPressed)
			mainCharacter.move(0, -10);

		if (downPressed)
			mainCharacter.move(0, 10);

		if ((numEnemiesKilled == numEnemies) && levelNum <= 5 && levelNum >= 1) {
			levelNum++;
			loadLevel(levelNum);
		}

		if (spacePressed && !mainCharacter.tooManyBullets && mainCharacter.reloadTimer < 0) {
			mainCharacter.fire();
			mainCharacter.reloadTimer = mainCharacter.bulletDelay;
		} else if (mainCharacter.tooManyBullets) {
			if (rPressed && atAmmoDepot) {
				mainCharacter.tooManyBullets = false;
				mainCharacter.bulletsFired = 0;
				mainCharacter.hasReloaded = true;
				ammoBox.teleport(mainCharacter.hasReloaded);
			}

		}
		for (GameObject object : gameObjects) {
			object.animate();
		}
		removeObjectsifColliding();
		hasFinishedGame(b.bossDead(), w.health);

	}

	// Method to remve game objects if they collide
	public void removeObjectsifColliding() {

		// Handles collision logic
		try {
			for (GameObject object : gameObjects) {
				if (object instanceof Projectile & object != mainCharacter) {
					for (GameObject object2 : gameObjects) {
						if (object2 != object & object2 != mainCharacter) {
							if (object.pos.intersects(object2.pos)) {
								if (object.health == 0 && object2.health == 0) {
									gameObjects.remove(object);
									gameObjects.remove(object2);
									numEnemiesKilled++;
									tNEK++;
									return;
								} else if (object2.health == 0) {
									gameObjects.remove(object2);
									numEnemiesKilled++;
									tNEK++;
									return;
								} else {
									gameObjects.remove(object);
									object2.removeHealth();
									return;
								}
							} else if (object2.hittingWall()) {
								w.hitTimer -= tick;
								if (w.hitTimer < 0 && w.returnHealth() >= 0) {
									w.removeHealth();
									w.hitTimer = w.hitDelay;
									return;
								}
							} else if ((object2.hittingWall()) && (object.pos.intersects(object2.pos))) {
								if (object.health == 0 && object2.health <= 0) {
									gameObjects.remove(object);
									gameObjects.remove(object2);
									numEnemiesKilled++;
									tNEK++;
									return;
								} else if (object2.health == 0) {
									gameObjects.remove(object2);
									numEnemiesKilled++;
									tNEK++;
									return;
								} else {
									w.hitTimer -= tick;
									if (w.hitTimer < 0 && w.returnHealth() >= 0) {
										w.removeHealth();
										w.hitTimer = w.hitDelay;
										return;
									}
									gameObjects.remove(object);
									object2.removeHealth();
									return;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		;
	}

	// Method to start a new game
	public void newGame() {
		// Logic to start a new game - re-initializing variables, loading the first level, etc.
		gameObjects = new ArrayList<GameObject>();
		levelNum = 1;
		loadLevel(levelNum);
		gameRunning = true;
		won = false;
		lost = false;
		thread = new GameThread(this);
		thread.start();
		this.requestFocus();
	}

	public void loadLevel(int level) {
		switch (level) {
			case 1:
				numEnemies = 15;
				numEnemiesKilled = 0;
				for (int i = 0; i < numEnemies; i++) {
					gameObjects.add(0, new Enemy((int) (Math.random() * 600) + 1500, (int) (Math.random() * 500 + 10),
							150, 150, 1, this));
				}
				break;
			case 2:
				numEnemies = 20;
				numEnemiesKilled = 0;
				for (int i = 0; i < numEnemies; i++) {
					if (i <= 15)
						gameObjects.add(0, new Enemy((int) (Math.random() * 600) + 1500,
								(int) (Math.random() * 300 + 100), 150, 150, 2, this));
					else
						gameObjects.add(0, new Runner((int) (Math.random() * 600) + 1500,
								(int) (Math.random() * 500 + 10), 150, 150, 1, this));
				}
				break;
			case 3:
				numEnemies = 40;
				numEnemiesKilled = 0;
				for (int i = 0; i < numEnemies; i++) {
					if (i < 30)
						gameObjects.add(0, new Enemy((int) (Math.random() * 1500) + 1000,
								(int) (Math.random() * 200 + 300), 150, 150, 2, this));
					else
						gameObjects.add(0, new Runner((int) (Math.random() * 600) + 1500,
								(int) (Math.random() * 500 + 10), 150, 150, 2, this));
				}
				break;
			case 4:
				numEnemies = 50;
				numEnemiesKilled = 0;
				for (int i = 0; i < numEnemies; i++) {
					if (i < 25)
						gameObjects.add(0, new Enemy((int) (Math.random() * 1500) + 1000,
								(int) (Math.random() * 200 + 300), 150, 150, 2, this));
					else
						gameObjects.add(0, new Runner((int) (Math.random() * 600) + 1500,
								(int) (Math.random() * 500 + 10), 150, 150, 1, this));
				}

				break;
			case 5:
				numEnemies = 13;
				numEnemiesKilled = 0;
				gameObjects.add(b);
				int j = 12;
				for (int i = 0; i < j; i++) {
					if (i < 8)
						gameObjects.add(0, new Enemy((int) (Math.random() * 1500) + 1000,
								(int) (Math.random() * 200 + 300), 150, 150, 3, this));
					else
						gameObjects.add(0, new Runner((int) (Math.random() * 600) + 1500,
								(int) (Math.random() * 500 + 10), 150, 150, 2, this));
				}
				break;
		}
	}


	// KeyListener interface methods to handle keyboard input.

	
	public void keyPressed(KeyEvent e) {
		// Handling what happens when a kye is pressed - moving the character, firing, etc
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacePressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			rPressed = true;
		}

	}


	public void keyTyped(KeyEvent e) {

		// This method is not used but is required for implementing KeyListener.

	}

	public void keyReleased(KeyEvent e) {
		// Handling what happens when a key is released - stopping the character's movement, etc
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			rPressed = false;
		}
	}

}
