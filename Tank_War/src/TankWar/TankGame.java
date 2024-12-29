package TankWar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class TankGame extends JFrame {

    private MyPanel panel;

    public static void main(String[] args) {

        new TankGame();
    }

    public TankGame(){
        System.out.println("Please select 1. New Game; 2. Resume");
        Scanner scanner = new Scanner(System.in);
        //将panel放入到thread中
        this.panel = new MyPanel(scanner.next());
        scanner.close();

        new Thread(panel).start();
        //设置绑定监听器
        this.addKeyListener(panel);
        this.add(panel);
        //设置窗体关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tank War");
        //获取当前屏幕分辨率
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗体大小不可变
        this.setResizable(false);
        //设置窗体位置
        width = (width - 1300) / 2;
        height = (height - 750) / 2;
        this.setBounds(width,height,1300,750);
        this.setBackground(Color.black);
        //设置窗体显示
        this.setVisible(true);

        //在JFrame 中增加相关关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
}






