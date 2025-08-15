package Src;


import java.awt.event.*;
import java.sql.Time;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javax.swing.*;

public class LuckyCat extends JPanel implements ActionListener, KeyListener {

    int BoardWidth = 360; // width fame 360 px
    int BoardHeight = 640; // Height fame 640 px
    
    

    // Img
    Image CatBgImg;
    Image CatImg;
    Image BottomTubeImg;
    Image TopTubeImg;
    Image TopTube2Img;

    
    // Character
    int CatX = BoardWidth / 8;
    int CatY = BoardHeight / 2;
    int CatWidth = 70;
    int CatHeight = 40;

    class Cat {

        int x = CatX;
        int y = CatY;
        int width = CatWidth;
        int Height = CatHeight;
        static Image img;

        Cat(Image img) {
            this.img = img;
        }

    }
    // -------------------------------------------

    // Tubes
    int tubesX = BoardWidth;
    int tubesY = 1;
    int tubesWidth = 120;
    int tubesHeight =  375;

    class Tubes {
        int x = tubesX;
        int y = tubesY;
        int Width = tubesWidth;
        int Height = tubesHeight;
        Image img;

        boolean passed = false;

        Tubes(Image img) {
            this.img = img;
        }
        
    }

    //------------------------------------------------

    //Gape  between Toptube
    int gapeX = BoardHeight;
    int gapeY = tubesY - 5 ;
    int gapeWidth = 100;
    int gapeHeight = 375;

    class Gape{

        int x = gapeX;
        int y = gapeY;
        int Width = gapeWidth;
        int Height = gapeHeight;
        Image img;

        boolean passed = false;

        Gape(Image img){
            this.img = img;
        }

       
    }

    // -----------------------------------------------

    // logic game
    Cat cat;

    int valocityX = -5; // control tube
    int valocityY = 0;
    int gravity = 1;

    ArrayList<Tubes> tubes;
    ArrayList<Gape> gapes;

    Random random = new Random();

    javax.swing.Timer gameLoop;
    javax.swing.Timer PositionTubeTimer;
    boolean gameOver = false;
    double score = 0;

    // --------------------------------------------
    
    LuckyCat() {
        setPreferredSize(new Dimension(BoardWidth, BoardHeight)); // issue by subtracting 2 px for both width and height
        setFocusable(true);
        addKeyListener(this);

        // load img

        CatBgImg = new ImageIcon(getClass().getResource("./CatBg.png")).getImage(); // name file img bgflappyb.
        CatImg = new ImageIcon(getClass().getResource("./Cat.png")).getImage(); // name file img cat
        TopTubeImg = new ImageIcon(getClass().getResource("./TopTube.png")).getImage(); // name file img toppipe
        BottomTubeImg = new ImageIcon(getClass().getResource("./BottomTube.png")).getImage(); // name file img
        

        // Charecter
        cat = new Cat(CatImg);
        tubes = new ArrayList<Tubes>();
       
        

        // Position loop tube time
        PositionTubeTimer = new javax.swing.Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PositionTube();
            }
        });
        PositionTubeTimer.start();

        // game timer
        gameLoop = new javax.swing.Timer(1000 / 60, this); // 1000/60 = 16.6 sec
        gameLoop.start();
    }

    public void PositionTube() {

        int randomTubeY = (int) (tubesY - tubesHeight / 4 - Math.random() * (tubesHeight / 2));
        int OpenSpace = BoardHeight / 4;

        // top
        Tubes topTubes = new Tubes(TopTubeImg);
        topTubes.y = randomTubeY;
        tubes.add(topTubes);

        // bottom
        Tubes BottomTube = new Tubes(BottomTubeImg);
        BottomTube.y = topTubes.y + tubesHeight + OpenSpace;
        tubes.add(BottomTube);

   
        

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        // bg
        g.drawImage(CatBgImg, 0, 0, BoardWidth, BoardHeight, null);

        // charecter
        g.drawImage(cat.img, cat.x, cat.y, cat.width, cat.Height, null);

        // tube
        for (int i = 0; i < tubes.size(); i++) {
            Tubes tube = tubes.get(i);
            g.drawImage(tube.img, tube.x, tube.y, tube.Width, tube.Height, null);
        }
       
        // score

        g.setColor(Color.pink);
        g.setFont(new Font("American Typewriter",Font.PLAIN, 32));
        if(gameOver){
            
            g.drawString("   Pls wait score : " + String.valueOf((int) score), 10,320);
        }  
        else {
            
            g.drawString(String.valueOf((int) score), 170,35);
        }

    }

    public void Move() {
        // cat
        valocityY += gravity;
        cat.y += valocityY;
        cat.y = Math.max(cat.y, 0);

        // tube
        for (int i = 0; i < tubes.size(); i++) {
            Tubes tube = tubes.get(i);
            tube.x += valocityX;

            if(!tube.passed && cat.x > tube.x + tube.Width){
                tube.passed = true;
                score += 0.5;
            }


            if (Clash(cat, tube)){
                gameOver = true;
            }
        }
       
        if (cat.y > BoardHeight){
            gameOver = true;
        }
        
    }

    public boolean Clash(Cat c, Tubes t){
        return c.x < t.x + t.Width &&
               c.x + c.width > t.x && 
               c.y < t.y + t.Height &&
               c.y + c.Height > t.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Move();
        repaint();
        if(gameOver){
            PositionTubeTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            valocityY = -12;
            if (gameOver){
                // restart game by resetting
                cat.y = CatY ;
                valocityY = 0 ;
                tubes.clear();
                score = 0 ;
                gameOver = false ;
                gameLoop.start();
                PositionTubeTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {

    }

}