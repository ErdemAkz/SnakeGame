package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class SnakeParts implements Runnable{
    public int x;
    public int y;
    public int index;
    public final static int uzunluk=10;
    public int yon;
    public final static int beklemeSuresi=70;
    public final static int atlamaMesafesi=10;


    public SnakeParts(int x, int y, int yon, int index){
        this.x=x;
        this.y=y;
        this.yon=yon;
        this.index=index;
    }

    public void moveUP(){
        if (yon!=Yon.DOWN)
            this.yon=Yon.UP;
    }
    public void moveDOWN(){
        if (yon!=Yon.UP)
            this.yon=Yon.DOWN;
    }
    public void moveRIGHT(){
        if (yon!=Yon.LEFT)
            this.yon=Yon.RIGHT;
    }
    public void moveLEFT(){
        if (yon!=Yon.RIGHT)
            this.yon=Yon.LEFT;
    }




    @Override
    public void run() {

        while (true){

            if (yon==Yon.UP){
                if (y==0)
                    y=Screen.heigth-atlamaMesafesi;
                else
                    y=y-atlamaMesafesi;
            }
            else if (yon==Yon.DOWN){
                if (y==Screen.heigth-atlamaMesafesi)
                    y=0;
                else
                    y=y+atlamaMesafesi;
            }
            else if (yon==Yon.RIGHT){
                if (x==Screen.width-atlamaMesafesi)
                    x=0;
                else
                    x=x+atlamaMesafesi;
            }
            else if (yon==Yon.LEFT){
                if (x==0)
                    x=Screen.width-atlamaMesafesi;
                else
                    x=x-atlamaMesafesi;
            }

            SnakePanel.Move();
            /*
            for (int i=0;i<SnakePanel.parts.size();i++)
            System.out.println(i+" x: "+SnakePanel.parts.get(i).x+" y: "+SnakePanel.parts.get(i).y);
            */

            try {
                Thread.sleep(beklemeSuresi);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
