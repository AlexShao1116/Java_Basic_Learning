# TankWar Game

## Overview
**TankWar** is a classic 2D tank shooter game developed in Java. The game involves controlling a tank (hero) to destroy enemy tanks while avoiding their attacks. It features:

- Dynamic gameplay with multiple enemy tanks.
- Explosions and animations for engaging effects.
- Scoring system and ability to save and load games.
- Multi-threading for smooth movement and shooting mechanics.

## Features
- **Hero Tank Control:** Use keyboard controls to move and shoot.
- **Enemy Tanks:** AI-controlled enemy tanks with movement and shooting capabilities.
- **Explosions:** Visual effects for destroyed tanks.
- **Save/Load Functionality:** Resume from where you left off.
- **Audio Effects:** Background music and explosion sounds.

## Installation
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```

2. Navigate to the project directory:
   ```bash
   cd TankWar
   ```

3. Compile the project:
   ```bash
   javac -d bin -sourcepath src src/**/*.java
   ```

4. Run the game:
   ```bash
   java -cp bin TankWar.Main
   ```

## Gameplay
### Controls
- **W:** Move Up
- **A:** Move Left
- **S:** Move Down
- **D:** Move Right
- **J:** Shoot

### Objective
- Destroy all enemy tanks while avoiding being hit.
- Collect points for each tank destroyed.
- Survive as long as possible.

## Project Structure
- **src/**: Contains all source code files.
- **bin/**: Compiled Java classes.
- **resources/**: Images, audio files, and other assets.
- **save/**: Save files for game progress.

## Key Classes
- **Hero:** Represents the player-controlled tank.
- **EnemyTank:** Represents AI-controlled enemy tanks.
- **MyPanel:** Handles game rendering and user input.
- **Recorder:** Tracks scores and manages game state saving/loading.
- **Bomb:** Handles explosion effects.

## Contributions
Feel free to contribute to the project! Here's how you can help:
1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m 'Add feature X'
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments
- Inspiration from classic arcade tank games.
- Java documentation and tutorials.

---
Enjoy playing TankWar! Feel free to report any issues or suggest new features!



Inspiration from classic arcade tank games.

Java documentation and tutorials.

Enjoy playing TankWar! Feel free to report any issues or suggest new features!
