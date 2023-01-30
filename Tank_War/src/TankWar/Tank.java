package TankWar;

public class Tank {
    private int x;
    private int y;
    private int Direct;
    boolean isLive = true;

    public int getDirect() {
        return Direct;
    }

    public void setDirect(int direct) {
        Direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tank(int x, int y){
        this.x = x;
        this.y = y;
    }
}
