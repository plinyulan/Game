package Src;



import java.io.File;
import javax.swing.*;


public class Main {

    public static void main(String[] args) throws Exception {

        int BoardWidth = 360; // width fame 360 px
        int BoardHeight = 640; // Heigth fame 640 px

        JFrame frame = new JFrame("Lucky Cat <3");// name game
        frame.setVisible(true); // show next button
        frame.setSize(BoardWidth, BoardHeight); // setting size fame
        frame.setLocationRelativeTo(null); // center of screen
        frame.setResizable(false); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit game
        
        //sreen index
        new Home();

        LuckyCat LuckyCat = new LuckyCat();
        frame.add(LuckyCat);
        frame.pack();
        LuckyCat.requestFocus();
        frame.setVisible(true);
        

        
        //part music
        
        Music song = new Music("Src/song/music_forLuckycat.wav", 240);
        song.play();
    }

}