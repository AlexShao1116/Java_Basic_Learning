package TankWar;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> enemyShots = new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    @Override
    public void run(){
        while(true){
            switch(getDirect()){
                case 0:
                        setX(getX()+1);
                case 1:
                        setX(getX()-1);
                case 2:
                        setY(getY()+1);
                case 3:
                        setY(getY()-1);
            }
        }
    }
}
