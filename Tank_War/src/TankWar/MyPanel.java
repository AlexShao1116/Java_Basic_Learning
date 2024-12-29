package TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    // Define our tank (Hero)
    Hero hero = null;
    // Define enemy tanks
    Vector<EnemyTank> enemyTanks = new Vector<>();
    // Used to store bombs
    // When a bullet hits a tank, a Bomb object is added
    Vector<Bomb> bombs = new Vector<>();
    // Define three images to display explosions
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;

    // Define a Vector to store Node objects for restoring enemy tank positions and directions
    Vector<Node> nodes = null;
    // Number of enemy tanks
    int enemyTankSize = 3;

    public MyPanel(String type) {
        // Check if the record file exists
        // If it exists, proceed normally; if not, indicate that only a new game can be started
        File file = new File(Recorder.getRecordFile());
        if (!file.exists()) {
            type = "1";
            System.out.println("File not found. A new game will start.");
        } else {
            nodes = Recorder.getNodesAndTanks();
        }

        // Initialize our tank (Hero)
        hero = new Hero(150, 150);
        // Set the tank speed
        hero.setSpeed(5);
        switch (type) {
            case "1":
                // Initialize enemy tanks
                for (int i = 0; i < enemyTankSize; i++) {
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    enemyTank.setDirection(2);
                    // Start enemy tank thread
                    new Thread(enemyTank).start();
                    // Add a bullet to this enemy tank
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    enemyTank.getShots().add(shot);
                    // Assign all enemy tanks to this tank
                    enemyTank.setEnemyTanks(enemyTanks);
                    // Start the bullet thread
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2": // Continue the last game
                // Initialize enemy tanks
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    enemyTank.setDirection(node.getDirection());
                    // Start enemy tank thread
                    new Thread(enemyTank).start();
                    // Add a bullet to this enemy tank
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    enemyTank.getShots().add(shot);
                    // Assign all enemy tanks to this tank
                    enemyTank.setEnemyTanks(enemyTanks);
                    // Start the bullet thread
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("Invalid input.");
        }
        // Get project path
        // 1. Load image resources
        String parent = System.getProperty("user.dir");
        parent += "\\";
        // 2. Initialize images
        img1 = Toolkit.getDefaultToolkit().getImage(parent + "3.webp");
        img2 = Toolkit.getDefaultToolkit().getImage(parent + "2.webp");
        img3 = Toolkit.getDefaultToolkit().getImage(parent + "1.webp");
        // Store enemy tank information
        Recorder.setEnemyTanks(enemyTanks);
        // Play background music
        new PlayAudio("bg.wav").start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Fill rectangle with default black color
        g.fillRect(0, 0, 1000, 750);
        // Display game information
        showInfo(g);
        // Draw our tank
        if (hero != null && hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }
        // Draw bullets fired by our tank
        if (hero.getShot() != null && hero.getShot().isLive()) {
            g.draw3DRect(hero.getShot().getX(), hero.getShot().getY(), 2, 2, false);
        }
        // Loop through our bullets
        if (hero.getShots().size() > 0) {
            for (int i = 0; i < hero.getShots().size(); i++) {
                Shot shot = hero.getShots().get(i);
                if (shot != null && shot.isLive()) {
                    g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
                } else {
                    hero.getShots().remove(shot);
                }
            }
        }

        // Draw enemy tanks
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                // Draw bullets of enemy tanks
                Vector<Shot> shots = enemyTank.getShots();
                for (int j = 0; j < shots.size(); j++) {
                    Shot shot = shots.get(j);
                    if (shot.isLive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    } else {
                        enemyTank.getShots().remove(shot);
                    }
                }
            }
        }

        // Draw explosions if bombs exist
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.getLife() > 6) {
                g.drawImage(img1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(img2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(img3, bomb.getX(), bomb.getY(), 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
            }
        }
    }

    /**
     * Display information about destroyed enemy tanks
     *
     * @param g Graphics object
     */
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("Times New Roman", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("U have destroyed", 1020, 30);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankTotal() + "", 1080, 100);
    }

    /**
     * Draw a tank
     *
     * @param x         Top-left x-coordinate of the tank
     * @param y         Top-left y-coordinate of the tank
     * @param g         Graphics object
     * @param direction Tank's direction (up, down, left, right)
     * @param type      Tank type (0 for enemy, 1 for our tank)
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        // Set color based on tank type
        switch (type) {
            case 0: // Enemy tank
                g.setColor(Color.cyan);
                break;
            case 1: // Our tank
                g.setColor(Color.yellow);
                break;
        }
        // Draw tank based on its direction
        // Direction: 0 = up, 1 = right, 2 = down, 3 = left
        switch (direction) {
            case 0: // Up
                g.fill3DRect(x, y, 10, 60, false); // Left track
                g.fill3DRect(x + 30, y, 10, 60, false); // Right track
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // Body
                g.fillOval(x + 10, y + 20, 20, 20); // Hatch
                g.drawLine(x + 20, y + 30, x + 20, y); // Gun barrel
                break;
            case 1: // Right
                g.fill3DRect(x, y, 60, 10, false); // Top track
                g.fill3DRect(x, y + 30, 60, 10, false); // Bottom track
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // Body
                g.fillOval(x + 20, y + 10, 20, 20); // Hatch
                g.drawLine(x + 30, y + 20, x + 60, y + 20); // Gun barrel
                break;
            case 2: // Down
                g.fill3DRect(x, y, 10, 60, false); // Left track
                g.fill3DRect(x + 30, y, 10, 60, false); // Right track
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // Body
                g.fillOval(x + 10, y + 20, 20, 20); // Hatch
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // Gun barrel
                break;
            case 3: // Left
                g.fill3DRect(x, y, 60, 10, false); // Top track
                g.fill3DRect(x, y + 30, 60, 10, false); // Bottom track
                g.fill3DRect(x + 10, y + 10, 40, 20, false); // Body
                g.fillOval(x + 20, y + 10, 20, 20); // Hatch
                g.drawLine(x + 30, y + 20, x, y + 20); // Gun barrel
                break;
            default:
                System.out.println("Direction not defined.");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Check which key was pressed
        if (KeyEvent.VK_W == code) { // Move up
            hero.setDirection(0);
            hero.moveUp();
        } else if (KeyEvent.VK_D == code) { // Move right
            hero.setDirection(1);
            hero.moveRight();
        } else if (KeyEvent.VK_S == code) { // Move down
            hero.setDirection(2);
            hero.moveDown();
        } else if (KeyEvent.VK_A == code) { // Move left
            hero.setDirection(3);
            hero.moveLeft();
        }
        // Check if the fire key was pressed
        if (KeyEvent.VK_SPACE == code) {
            // Fire bullets
            hero.shotEnemyTank();
        }

        // Repaint after movement or firing
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void run() {
        // Repaint every 100 milliseconds
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Check if bullets hit enemy tanks
            hitEnemy();
            // Check if enemy bullets hit our tank
            hitHero();
            this.repaint();
        }
    }

    /**
     * Check if our bullets hit enemy tanks
     */
    public void hitEnemy() {
        if (hero.getShot() != null && hero.getShot().isLive()) {
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                // Check for multiple bullets
                hitTank(hero.getShots(), enemyTank);
            }
        }
    }

    /**
     * Check if enemy bullets hit our tank
     */
    public void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            Vector<Shot> shots = enemyTank.getShots();
            for (int j = 0; j < shots.size(); j++) {
                Shot shot = shots.get(j);
                if (hero.isLive() && shot.isLive()) {
                    hitTank(shots, hero);
                }
            }
        }
    }

    /**
     * Check if a bullet hits a tank
     *
     * @param shots List of bullets
     * @param tank  Tank object
     */
    public void hitTank(Vector<Shot> shots, Tank tank) {
        for (int i = 0; i < shots.size(); i++) {
            hitTank(shots.get(i), tank);
        }
    }

    /**
     * Check if a single bullet hits a tank
     *
     * @param s    Bullet object
     * @param tank Tank object
     */
    public void hitTank(Shot s, Tank tank) {
        switch (tank.getDirection()) {
            case 0: // Tank facing up
            case 2: // Tank facing down
                if (s.getX() > tank.getX() && s.getX() < tank.getX() + 40 &&
                        s.getY() > tank.getY() && s.getY() < tank.getY() + 60) {
                    s.setLive(false);
                    tank.setLive(false);
                    enemyTanks.remove(tank);
                    if (!(tank instanceof Hero)) {
                        Recorder.addlEnemyTankTotal();
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: // Tank facing right
            case 3: // Tank facing left
                if (s.getX() > tank.getX() && s.getX() < tank.getX() + 60 &&
                        s.getY() > tank.getY() && s.getY() < tank.getY() + 40) {
                    s.setLive(false);
                    tank.setLive(false);
                    enemyTanks.remove(tank);
                    if (!(tank instanceof Hero)) {
                        Recorder.addlEnemyTankTotal();
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }


}
