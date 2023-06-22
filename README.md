# Operation Brimstone

This project is a simple 2D tower defense zombie shooter game built using Java and its AWT (Abstract Window Toolkit) library for creating the graphical user interface.

## Game Description

In this game, the player controls a character that has to defend against an onslaught of zombies. The character can shoot projectiles and has to avoid being overwhelmed by the zombies. The game features different levels with increasing difficulty and complexity.

## Classes

The project consists of several classes that work together to create the game:

### 1. GameObject.java

This is an abstract base class for all game objects. It includes basic properties such as position, size, and health. It defines some methods like `draw`, `move`, `animate`, and `hittingWall` that should be implemented by subclasses.

### 2. GamePanel.java

This class is responsible for the main game loop, rendering, and handling user inputs. It keeps track of game objects, updates their positions, checks for collisions, and repaints the graphics on the panel. It also has methods for starting a new game, advancing to the next level, and checking if the game is over.

### 3. GameFrame.java

This class is responsible for creating the main window of the game. It sets up the JFrame, including its size, layout, and creating a menu with options to start a new game or quit. It adds the `GamePanel` to the center of the frame.

### 4. Character.java

This class extends `GameObject` and represents the main player in the game. It has properties for the character's images, reloading bullets, etc. It has methods to draw the character on the screen, fire bullets, move the character, and animate the character.

### 5. Enemy.java

This class extends `GameObject` and represents enemy characters in the game. It has an image representing the enemy, and methods to check if the enemy is hitting a wall, animate the enemy's movement, and draw the enemy on the screen.

### 6. Wall.java

This class extends `GameObject` and represents a wall in the game. It holds images for different states of the wall depending on its health. It has a draw method which draws the wall based on its health. It has a `hittingWall` method which currently always returns false.

### 7. Runner.java

This class extends `Enemy` and represents a specific type of enemy called a runner in the game. It has an image to represent the runner enemy and overrides the `animate` method from the `Enemy` class to update the position of the runner enemy. It also has a `draw` method used to draw the runner enemy on the screen, and a `hittingWall` method to check if the runner enemy is hitting a wall.

### 8. Projectile.java

This class extends `GameObject` and represents a projectile in the game. It has an image representing the projectile when it is moving. It also has properties for the horizontal and vertical speed of the projectile. The `draw` method is used to draw the projectile on the screen. The `animate` method updates the position of the projectile. The `hittingWall` method currently always returns false.

### 9. Boss.java

This class extends `Enemy` and represents a boss enemy in the game. It holds images for different states of the boss such as idle, attacking, and dying. It includes a boolean flag `healedSelf` to keep track of whether the boss has healed itself. The `animate` method controls the boss’s movement and healing logic. The `draw` method is used to draw the boss on the screen based on its state. The class also has methods to check if the boss is hitting a wall and if it is dead, as well as a method to check if the boss has healed itself.

### 10. AmmoDepot.java

This class extends `GameObject` and represents an ammo depot that players can interact with in the game. It has two images: one for when the ammo depot is not highlighted and another for when it is. The `draw` method draws the image depending on whether or not the player is at the ammo depot. There's also a `teleport` method used to move the ammo depot to a new location if needed.

### 11. GameThread.java

This class extends `Thread` and is responsible for the game loop. It keeps a reference to the `GamePanel` where the game is displayed and a boolean flag to track if the game is running. It also holds a time step value to control the speed of the game and a time variable to keep track of the time elapsed. The `run` method contains the game loop that updates the game state and redraws the game at a consistent interval. The `endGame` method stops the game loop, and the `render` method calls the `animate` method from `GamePanel` to update game objects, repaints the game panel, and updates the time for each game object.

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your machine.
2. Clone this repository to your local machine.
3. Navigate to the directory where the files are located.
4. Compile the Java files using the command `javac GameFrame.java`.
5. Run the program using the command `java GameFrame`.

## Requirements

- Java Development Kit (JDK) to compile and run the Java files

## Assets

You will need to provide image assets and place them in an "Images" folder. Image file paths are specified in `Character`, `Enemy`, `Wall`, `Projectile` and `Runner` classes.

## Contribute

Contributions, issues, and feature requests are welcome. Feel free to check [issues page](#) if you want to contribute.
