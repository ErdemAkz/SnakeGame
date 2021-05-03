package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {
    class ConfirmWindow extends JFrame implements ActionListener {
        public ConfirmWindow(String text){
            setSize(300,150);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());
            JLabel label = new JLabel(text);
            JButton ok = new JButton("OK");
            ok.addActionListener(this);
            add(label);
            add(ok);
            this.setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("OK")){
                System.exit(0);
            }else{
                dispose();
            }
        }
    }

    public SnakeFrame(){
        setSize(Screen.width,Screen.heigth);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        SnakePanel p1 = new SnakePanel();
        p1.setLayout(null);
        p1.setBackground(Color.BLACK);
        add(p1);
        this.setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        getContentPane().setPreferredSize( new Dimension(Screen.width,Screen.heigth));
        pack();

        while (true){
            p1.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (p1.Kontrol()){
                p1.gameStop();
                new ConfirmWindow("Oyun Bitti! Puaniniz: "+p1.puan);
                break;
            }



        }

    }
}
