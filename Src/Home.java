package Src;


import javax.swing.*;

import Src.LuckyCat.Cat;

import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {
    JButton startButton;
    JLabel titleLabel;

    public Home() {
        
        setTitle("Lucky Cat");
        setSize(360, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        
        titleLabel = new JLabel("Welcome! to game");
        titleLabel.setFont(new Font("American Typewriter", Font.PLAIN, 32));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.CENTER);

        // startButton = new JButton("Start");
        // titleLabel.setFont(new Font("American Typewriter", Font.PLAIN, 32));
        // startButton.addActionListener(this);
        // panel.add(startButton, BorderLayout.SOUTH);

        
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startGame();
        }
    }

    public void startGame() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setVisible(true);
        this.dispose();
    }

    
}

class GameWindow extends JFrame implements ActionListener {
    JButton endButton;

    public GameWindow() {
        setTitle("Game");
        setSize(360, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // endButton = new JButton("End Game");
        // endButton.addActionListener(this);
        // panel.add(endButton, BorderLayout.SOUTH);

        add(panel);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == endButton) {
            endGame();
        }
    }

    public void endGame() {
        System.exit(10);
    }
}