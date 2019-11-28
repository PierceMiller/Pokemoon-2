package Game;

/*
*
*@author Pierce Miller 
*@studentid 14872510
*
*/

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game {
    
    private TitleScreenHandler tsHandler = new TitleScreenHandler();
    private ChoiceHandler choiceHandler = new ChoiceHandler();

    public static winners win = new winners();
    public static losers lose = new losers();
    public static musicPlayer play = new musicPlayer();
    public static JDBC database = new JDBC();
    public static Random rand = new Random();
    public static Moves moves = new Moves();
    public static int count = 0;

    public static int damageDealt = 3;
    public static int moonAttackDmg = 50;
    public static int moonHP, monsterHP, kantoSymbol;
    public static int maxEnemyHealth = 100;
    public static int enemyAttackDamage = 25;
    public static String moon, position, toMuch;
    public static String[] enemies = {"Chaizard", "Rayhorn", "Treekoo", "Torchick", "Sulamance", "Tiranita"};
    public static String enemy = enemies[rand.nextInt(enemies.length)]; //random enemy generator
    public static String[] lowEnemies = {"Pinsir", "Scytha"}; //secondary enemies
    public static String lowEnemey = lowEnemies[rand.nextInt(lowEnemies.length)];
    public static String[] trainers = {"Calvin", "Ben", "Wonde", "Sean"};
    public static String trainer = trainers[rand.nextInt(trainers.length)];
    
    public static JFrame window;
    public static Container con;
    public static JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel, scorePanel;
    public static JLabel titleNameLabel, hpLabel, hpLabelNumber, moonLabel, moonLabelName, scoreLabel, scoreLabelName;
    public static Font titleFont = new Font("Showcard Gothic", Font.PLAIN, 90);
    public static Font normalFont = new Font("Showcard Gothic", Font.PLAIN, 30);
    public static JButton[] choices = new JButton[4];
    public static JButton startButton;
    public static JTextArea mainTextArea;

    public Game() {

        //FORMATED FOR WINDOWS
        window = new JFrame("Pokemoon");
        window.setSize(1250, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {

            window.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("image_genga_background.jpg")))));

        } catch (IOException event) {
            System.out.println("Image doesn't exist " + event);
        }

        window.setIconImage(Toolkit.getDefaultToolkit().getImage("image_genga_icon.jpg"));
        window.setResizable(false);
        //window.pack();      
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Pokemoon");
        titleNameLabel.setForeground(Color.MAGENTA);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("BEGIN");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.lightGray);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);
        con.validate();
    }

    public void createGameScreen() {

        //tracks movement 
        System.out.println("\nBEGIN was clicked\nCounter : " + count);

        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea("Pokemoon");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        //PANELS
        playerPanel = new JPanel();
        playerPanel.setBounds(50, 15, 1000, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);

        scorePanel = new JPanel();
        scorePanel.setBounds(600, 400, 230, 150);
        scorePanel.setBackground(Color.black);
        scorePanel.setLayout(new GridLayout(1, 2));
        con.add(scorePanel);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);

        //BUTTONS
        for (int i = 0; i < 4; i++) {

            choices[i] = new JButton();
            choices[i].setBackground(Color.black);
            choices[i].setForeground(Color.MAGENTA);
            choices[i].setFont(normalFont);
            choices[i].setFocusPainted(false);
            choices[i].addActionListener(choiceHandler);

            choiceButtonPanel.add(choices[i]);
        }

        choices[0].setActionCommand("c1");
        choices[1].setActionCommand("c2");
        choices[2].setActionCommand("c3");
        choices[3].setActionCommand("c4");

        for (int i = 0; i < 4; i++) {

            choiceButtonPanel.add(choices[i]);
        }

        //LABELS      
        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.MAGENTA);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.LIGHT_GRAY);
        playerPanel.add(hpLabelNumber);

        moonLabel = new JLabel("Moon:");
        moonLabel.setFont(normalFont);
        moonLabel.setForeground(Color.MAGENTA);
        playerPanel.add(moonLabel);

        moonLabelName = new JLabel();
        moonLabelName.setFont(normalFont);
        moonLabelName.setForeground(Color.LIGHT_GRAY);
        playerPanel.add(moonLabelName);

        scoreLabel = new JLabel("Score:");
        scoreLabel.setFont(normalFont);
        scoreLabel.setForeground(Color.MAGENTA);
        scorePanel.add(scoreLabel);

        scoreLabelName = new JLabel();
        scoreLabelName.setFont(normalFont);
        scoreLabelName.setForeground(Color.LIGHT_GRAY);
        scorePanel.add(scoreLabelName);

        playerSetup();

    }

    public void playerSetup() {

        moonHP = 100;
        monsterHP = rand.nextInt(maxEnemyHealth) + 70;
        moon = "Haunter";
        toMuch = "Heal";
        moonLabelName.setText(moon);
        hpLabelNumber.setText("" + moonHP);
        scoreLabelName.setText(" " + count);

        moves.kanto();
    }

    public class TitleScreenHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            createGameScreen();
        }
    }

    public class ChoiceHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String yourChoice = event.getActionCommand();

            switch (position) {
                case "townGate":
                    switch (yourChoice) {
                        case "c1":
                            if (kantoSymbol == 1) {
                                //ending();
                            } else {
                                moves.talkEnemy();
                            }
                            break;
                        case "c2":
                            moves.attackEnemy();
                            break;
                        case "c3":
                            moves.crossRoad();
                            break;
                    }
                    break;
                case "talkEnemy":
                    switch (yourChoice) {
                        case "c1":
                            moves.kanto();
                            break;
                    }
                    break;
                case "attackMoon":
                    switch (yourChoice) {
                        case "c1":
                            moves.kanto();
                            break;
                    }
                    break;
                case "crossRoad":
                    switch (yourChoice) {
                        case "c1":
                            moves.north();
                            break;
                        case "c2":
                            moves.east();
                            break;
                        case "c3":
                            moves.kanto();
                            break;
                        case "c4":
                            moves.west();
                            break;
                    }
                    break;
                case "north":
                    switch (yourChoice) {
                        case "c1":
                            moves.crossRoad();
                            break;
                    }
                    break;
                case "east":
                    switch (yourChoice) {
                        case "c1":
                            moves.crossRoad();
                            break;
                    }
                    break;
                case "west":
                    switch (yourChoice) {
                        case "c1":
                            if (monsterHP > 0) {
                                moves.fight();
                            } else {
                                moves.crossRoad();
                            }
                            break;
                        case "c2":
                            moves.crossRoad();
                            break;
                    }
                    break;
                case "fight":
                    switch (yourChoice) {
                        case "c1":
                            moves.playerAttack();
                            break;
                        case "c2":
                            moves.crossRoad();
                            break;
                    }
                    break;
                case "playerAttack":
                    switch (yourChoice) {
                        case "c1":
                            if (monsterHP <= 0) {
                                moves.win();
                            } else {
                                moves.enemyAttack();
                            }
                            break;
                    }
                    break;
                case "monsterAttack":
                    switch (yourChoice) {
                        case "c1":
                            if (moonHP < 1) {
                                moves.lose();
                            } else {
                                moves.fight();
                            }
                            break;
                    }
                    break;
                case "win":
                    switch (yourChoice) {
                        case "c1":
                            moves.crossRoad();
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {

        database.enemyDB();
        database.trainerDB();

        //BE CAREFUL OF VOLUME
        play.playMusic("music_genga.wav");

        new Game();
    }

}
