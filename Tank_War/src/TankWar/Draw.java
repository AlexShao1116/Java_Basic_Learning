package TankWar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

class Draw extends JFrame{
    //定义画板variable。
    private MyPanel mp = null;

    public Draw(){
        mp = new MyPanel();//initialize panel
        this.add(mp);//set panel into the frame
        //set the size and visible of the frame
        this.setSize(1000,750);
        Thread thread = new Thread(mp);
        thread.start();
        //exit the program when press "x"
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);

    }

    public static void main(String[] args) {
            new Draw();
    }


}
//1.先定义panel类，继承panel，在画板上作图。
class MyPanel extends JPanel implements KeyListener,Runnable {
    public int x = 100;
    public int y = 300;
    public static int enemytanknum = 5;
    Hero hero = null;
    public static Vector<EnemyTank> gg = new Vector<>();
    public static Vector<Bomb> bombs = new Vector<>();
    BufferedImage Image1 = null;
    BufferedImage Image2 = null;
    BufferedImage Image3 = null;
    public MyPanel(){
        hero = new Hero(x,y);
        for(int i = 0;i < enemytanknum;i++){
            EnemyTank enemyTank = new EnemyTank(i*100,0);
            enemyTank.setDirect(2);
            gg.add(enemyTank);
            Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
            enemyTank.enemyShots.add(shot);
            new Thread(shot).start();
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 0;i < hero.heroShots.size();i++) {
                if (hero.heroShots != null && hero.heroShots.get(i).isLive == true){
                    for(int j = 0;j < gg.size();j++){
                        hitTank(hero.heroShots.get(i),gg.get(j));
                    }

                }
            }
            this.repaint();
        }
    }
    //说明：
    //Mypanel相当于画板，g相当于画笔。
    //graphic提供多种画图方式。
    @Override
    public void paint(Graphics g){
        /*super.paint(g);//调用父类完成初始化。
        g.drawOval(30,30,80,30);//draw oval
        g.setColor(Color.GREEN);
        g.setFont(new Font("Times New Roma",Font.BOLD,50));
        g.drawString("Hello World",500,500);
        g.fillOval(50,50,80,80);//fill oval
        //加入照片
        //1.get image resource from root menu -> /img.png
        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(this.getClass().getResource("/img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel wIcon = new JLabel(new ImageIcon(wPic));
        g.drawImage(wPic,400,400,120,67,this);*/
        g.fillRect(0,0,1000,750);
        drawTank(x,y,g,hero.getDirect(),0);
        for(int j = 0;j < hero.heroShots.size();j++){
            Shot shot = hero.heroShots.get(j);
            if(shot.isLive == true){
                g.fill3DRect(shot.x,shot.y,4,4,false);
            }
        }
        if(hero.shot != null && hero.shot.isLive == true){
            g.fill3DRect(hero.shot.x,hero.shot.y,4,4,false);
        }
        for(int i = 0;i < bombs.size();i++){
            Bomb bomb = bombs.get(i);
            if (bomb.life >= 6) {
                g.drawImage(Image1, bomb.getX(), bomb.getY(), this);
            }else if(bomb.life >= 3){
                    g.drawImage(Image2,bomb.getX(),bomb.getY(),this);
            }else {
                g.drawImage(Image3, bomb.getX(), bomb.getY(), this);
            }
            bomb.lifeDown();
        }
        for(int i = 0;i < gg.size();i++){
            EnemyTank enemyTank = gg.get(i);

            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
                for(int j = 0;j < enemyTank.enemyShots.size();j++){
                    Shot shot = enemyTank.enemyShots.get(j);
                    if(shot.isLive == true){
                        g.fill3DRect(shot.x,shot.y,4,4,false);
                    }
                }
            }
        }
        try {
            Image1 = ImageIO.read(this.getClass().getResource("/images1.jpg"));
            Image2 = ImageIO.read(this.getClass().getResource("/images2.png"));
            Image2 = ImageIO.read(this.getClass().getResource("/imagees3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawTank(int x,int y,Graphics g,int direct,int type){

        switch (type){
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch(direct){
            case 0://up direct
                g.fill3DRect(x,y,10,60,false);//左侧轮子
                g.fill3DRect(x+30,y,10,60,false);//右侧轮子
                g.fill3DRect(x+10,y+10,20,40,false);//底座
                g.fillOval(x+10,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+20,x+20,y);//炮筒
                break;
            case 1://right  direct
                g.fill3DRect(x,y,60,10,false);//左侧轮子
                g.fill3DRect(x,y+30,60,10,false);//右侧轮子
                g.fill3DRect(x+10,y+10,40,20,false);//底座
                g.fillOval(x+20,y+10,20,20);//圆盖子
                g.drawLine(x+30,y+20,x+60,y+20);//炮筒
                break;
            case 2://down direct
                g.fill3DRect(x,y,10,60,false);//左侧轮子
                g.fill3DRect(x+30,y,10,60,false);//右侧轮子
                g.fill3DRect(x+10,y+10,20,40,false);//底座
                g.fillOval(x+10,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+30,x+20,y+60);//炮筒
                break;
            case 3://left  direct
                g.fill3DRect(x,y,60,10,false);//左侧轮子
                g.fill3DRect(x,y+30,60,10,false);//右侧轮子
                g.fill3DRect(x+10,y+10,40,20,false);//底座
                g.fillOval(x+20,y+10,20,20);//圆盖子
                g.drawLine(x+30,y+20,x,y+20);//炮筒
                break;

        }

    }
    public static void hitTank(Shot s,EnemyTank enemyTank){
        switch(enemyTank.getDirect()){
            case(0):
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX()+40 && s.y > enemyTank.getY() && s.y <enemyTank.getY()+60){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    gg.remove(enemyTank);
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case(1):
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX()+60 && s.y > enemyTank.getY() && s.y <enemyTank.getY()+40){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    gg.remove(enemyTank);
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;

            case(2):
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX()+40 && s.y > enemyTank.getY() && s.y <enemyTank.getY()+60){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    gg.remove(enemyTank);
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case(3):
                if(s.x > enemyTank.getX() && s.x < enemyTank.getX()+60 && s.y > enemyTank.getY() && s.y <enemyTank.getY()+40){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    gg.remove(enemyTank);
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);

                }
                break;

        }
    }

    //when any character output,this method will work
    @Override
    public void keyTyped(KeyEvent e) {


    }
    //when any button been pressed,this method will work
    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_S){
                hero.setDirect(2);
                hero.setY(y++);
            }else if (e.getKeyCode() == KeyEvent.VK_W) {
                hero.setDirect(0);
                hero.setY(y--);
            }else if (e.getKeyCode() == KeyEvent.VK_A) {
                hero.setDirect(3);
                hero.setX(x--);
            }else if (e.getKeyCode() == KeyEvent.VK_D) {
                hero.setDirect(1);
                hero.setX(x++);
            }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                hero.heroShots.add(hero.shotEnemyTank());

            }
            this.repaint();
            System.out.println((char)e.getKeyCode()+"被摁下");
    }
    //when any button been released after pressed,this method will work
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
