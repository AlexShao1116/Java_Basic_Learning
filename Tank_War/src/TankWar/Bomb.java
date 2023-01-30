package TankWar;

public class Bomb {
    private int x;
    private int y;
    boolean isLive = true;
    int life = 6;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if(life > 0){
            life--;
        }else if(life == 0){
            isLive = false;
        }
    }
}
