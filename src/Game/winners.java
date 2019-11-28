package Game;

/*
*
*@author Pierce Miller 
*@studentid 14872510
*
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Collections;
import javax.swing.JLabel;
import static Game.Game.count;

public class winners {

    private long time = 6000;
    private String input;

    public void winners() {

        JLabel iconLabel = new JLabel("Enter Name: ", JLabel.CENTER);
        input = JOptionPane.showInputDialog(null, iconLabel, "Winner Winner Chicken Dinner", JOptionPane.PLAIN_MESSAGE);

        JOptionPane.showMessageDialog(null, "Well Done " + input + "...\nYou are a true Pokemoon Trainer!\nScore : " + count);

        FileWriter fw = null;

        try {

            fw = new FileWriter("winners.txt", true);
            fw.write(input + " : " + count + "\n");
            fw.close();

            Scanner scoreBoard = new Scanner(new FileReader("winners.txt"));
            ArrayList<String> boardScore = new ArrayList<String>();
            Collections.sort(boardScore);

            while (scoreBoard.hasNextLine()) {
                boardScore.add(scoreBoard.nextLine());
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        System.exit(
                0);

    }

}
