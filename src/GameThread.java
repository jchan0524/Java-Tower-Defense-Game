public class GameThread extends Thread {

	// Reference to the GamePanel where the game is displayed
	private GamePanel game;

	// Boolean flag to keep track of whether the game is running
	private boolean running;

	// Time step value to control the speed of the game 
	public double deltaT = 0.05;

	// Time variable to keep track of the time elapsed
	double time;

	// Constructor taking a reference to the GamePanel
	public GameThread(GamePanel g) {
		game = g;

	}

	// The run method contains the game loop
	public void run() {

		// Setting the running flag to true since the game is now running
		running = true;

		// Game loop 
		while (running) {

			// Increment the time variable by deltaT and round it to 2 decimal places 
			time = Math.round((time + deltaT) * 100) / 100.0;

			// Call the render method, which updates the game state and redraws the game
			render();

			// Make the thread sleep for 10 ms
			// This controls the rate at which the game loop iterates and provides a pause to allow for rendering 
			try {
				sleep(10);
			} catch (Exception e) {
			}
			;
		}

	}

	// Method to stop the game by setting the running flag to false 
	public void endGame() {
		running = false;
	}

	// The render method updates the game's state and redrawz the game 
	public void render() {
		// Call the animate method from GamePanel to update game objects
		game.animate();

		// Call repaint on game panel, which causes the panel to be redrawn
		game.repaint();

		// Update the time for each game object 
		for (GameObject object : game.gameObjects) {
			object.updateTime(time);
		}
	}
}
