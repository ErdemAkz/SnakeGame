

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakePanel extends JPanel implements KeyListener {
    public static ArrayList<SnakeParts> parts = new ArrayList<>(1);
    public static Yem yem;
    public Thread snake;
    public static int puan=0;

    public static Yem YemOlustur(){
        Yem yem = new Yem();
        Random rand = new Random();
        int x,y;
        while (true){
            x = rand.nextInt(Screen.width/10)*10;
            y = rand.nextInt(Screen.heigth/10)*10;
            for (int i=0;i<parts.size();i++){
                if (x==parts.get(i).x || y==parts.get(i).y)
                    continue;
            }
            break;
        }
        yem.x=x;
        yem.y=y;
        System.out.println(x+"  "+y);
        return yem;
    }

    public static void Move(){
        boolean eklenecekVarmi = YemKontrol();

        int x=parts.get(parts.size()-1).x;
        int y=parts.get(parts.size()-1).y;

        for (int i=parts.size(); i>=2; i--){
            parts.get(i - 1).x = parts.get(i-2).x;
            parts.get(i - 1).y = parts.get(i-2).y;;
        }
        if (eklenecekVarmi) {
            puan++;
            if (parts.size() < 20) {
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));

            }
            else {
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));
                parts.add(new SnakeParts(x, y, 0, 0));

            }
            if (parts.size()==2) {
                parts.add(new SnakeParts(x, y, 0, 0));
            }
            yem = YemOlustur();
        }

    }
    public boolean Kontrol(){
        if (parts.size()>3) {
            for (int i = 2; i < parts.size(); i++) {
                if (parts.get(0).x == parts.get(i).x && parts.get(0).y == parts.get(i).y)
                    return true;
            }
        }
        return false;
    }

    public void gameStop(){
        snake.stop();
    }


    public static boolean YemKontrol(){
        if (parts.get(0).x==yem.x && parts.get(0).y==yem.y)
            return true;
        return false;
    }


    public SnakePanel() {
        setSize(Screen.width, Screen.heigth);
        yem = YemOlustur();
        //parts.set(0,new SnakeParts(Screen.width/2-SnakeParts.uzunluk/2,Screen.heigth/2-SnakeParts.uzunluk/2, Yon.UP, 0));
        parts.add(new SnakeParts(Screen.width/2-SnakeParts.uzunluk/2,Screen.heigth/2-SnakeParts.uzunluk/2, Yon.UP, 0));
        snake = new Thread(parts.get(0));
        snake.start();

        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(yem.x,yem.y,Yem.uzunluk,Yem.uzunluk);

        for (int i=0;i<parts.size();i++){
            g.setColor(Color.GREEN);
            g.fillRect(parts.get(i).x,parts.get(i).y,SnakeParts.uzunluk,SnakeParts.uzunluk);
            g.setColor(Color.RED);
            g.drawLine(parts.get(i).x,parts.get(i).y,parts.get(i).x+SnakeParts.uzunluk,parts.get(i).y);
            g.drawLine(parts.get(i).x,parts.get(i).y,parts.get(i).x,parts.get(i).y+SnakeParts.uzunluk);
            g.drawLine(parts.get(i).x+SnakeParts.uzunluk,parts.get(i).y,parts.get(i).x+SnakeParts.uzunluk,parts.get(i).y+SnakeParts.uzunluk);
            g.drawLine(parts.get(i).x,parts.get(i).y+SnakeParts.uzunluk,parts.get(i).x+SnakeParts.uzunluk,parts.get(i).y+SnakeParts.uzunluk);
        }


    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){
            parts.get(0).moveUP();
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            parts.get(0).moveDOWN();
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            parts.get(0).moveRIGHT();
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            parts.get(0).moveLEFT();
        }
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
