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

public class losers {
    
    private long time = 3000;
    private String input;

    public void losers() {

        JLabel iconLabel = new JLabel("Enter Name: ", JLabel.CENTER);
        
        input = JOptionPane.showInputDialog(null, iconLabel, "Unluggy Uce!", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, "Hard luck " + input + "...\nHowever, you are not a trainer!\nScore : "+ count);
        FileWriter fw = null;

        try {

            fw = new FileWriter("losers.txt", true);
            fw.write(input + " : " + count + "\n");
            fw.close();

            Scanner scoreBoard = new Scanner(new FileReader("losers.txt"));
            ArrayList<String> boardScore = new ArrayList<String>();
            Collections.sort(boardScore);

            while (scoreBoard.hasNextLine()) {
                boardScore.add(scoreBoard.nextLine());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        System.exit(0);

    }

}
