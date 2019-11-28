package Game;

/*
*
*@author Pierce Miller 
*@studentid 14872510
*
*/

import static Game.Game.count;
import static Game.Game.lose;
import static Game.Game.rand;
import static Game.Game.win;
import static Game.Game.scoreLabelName;
import static Game.Game.kantoSymbol;
import static Game.Game.position;
import static Game.Game.enemy;
import static Game.Game.trainer;
import static Game.Game.moon;
import static Game.Game.moonHP;
import static Game.Game.monsterHP;
import static Game.Game.damageDealt;
import static Game.Game.hpLabelNumber;
import static Game.Game.mainTextArea;
import static Game.Game.toMuch;
import static Game.Game.moonLabelName;
import static Game.Game.choices;

public class Moves {
    
        public void kanto() {

        count++;
        scoreLabelName.setText(" " + count);

        if (kantoSymbol == 0) {
            position = "townGate";
            mainTextArea.setText("You try getting into kanto,\n whats this?\n\n A Wild " + enemy + " appears!\n\nWhat do you do?");

            choices[0].setText("TALK");
            choices[1].setText("ATTACK");
            choices[2].setText("RUN");
            choices[3].setText("");
        } else if (kantoSymbol >= 1) {
            ending();
        }
    }

    public void talkEnemy() {

        position = "talkEnemy";
        count++;
        
        System.out.println("\nTALK was clicked\nCounter : " + count); //gauge progress
        scoreLabelName.setText(" " + count);

        mainTextArea.setText(enemy + ": RAAAAAA!\n\n" + trainer + ": HA! you will never get past"
                + "\n my " + enemy + ", " + enemy + "! GO!");

        choices[0].setText(">");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");
    }

    public void attackEnemy() {

        count++;
        System.out.println("\nATTACK was clicked\nCounter : " + count);
        scoreLabelName.setText(" " + count);

        if (kantoSymbol == 0) {

            position = "attackMoon";
            mainTextArea.setText(trainer + ": You can't just attack me!"
                    + "\n\n(" + trainer + " calls the guard,\nwho kicks you out)"
                    + "\n\n" + moon + " is delt 3 damage.");

            moonHP = moonHP - damageDealt;
            hpLabelNumber.setText("" + moonHP);

            choices[0].setText(">");
            choices[1].setText("");
            choices[2].setText("");
            choices[3].setText("");
        } else if (kantoSymbol == 1) {
            ending();
        }
    }

    public void crossRoad() {

        position = "crossRoad";
        count++;

        System.out.println("\nCROSSROAD was clicked\nCounter : " + count);
        scoreLabelName.setText(" " + count);

        mainTextArea.setText("You are at a crossroad."
                + "\nIf you go south, you will go back to Kanto."
                + "\n\tObjective: \nEnter Kanto with the lowest score\n               (Each click is one point)");
        choices[0].setText("Go north");
        choices[1].setText("Go east");
        choices[2].setText("Go south");
        choices[3].setText("Go west");
    }

    public void north() {

        position = "north";
        count++;
        System.out.println("\nNORTH was clicked\nCounter : " + count);
        scoreLabelName.setText(" " + count);

        if (toMuch.equals("Heal")) {

            mainTextArea.setText("There is a river. "
                    + "\nYou drink the water and rest at the riverside. "
                    + "\n\n(Your HP is recovered by 20)");

            moonHP = moonHP + 20;
            hpLabelNumber.setText("" + moonHP);
            toMuch = "Sick";

        } else if (toMuch.equals("Sick")) {

            mainTextArea.setText(moon + " drank to much and got sick"
                    + "\n " + moon + " is dealt 20 damage!");

            moonHP = moonHP - 20;
            hpLabelNumber.setText("" + moonHP);
            toMuch = "Heal";
            
            if(moonHP <= 0){
                lose();
            }
        }

        choices[0].setText("Go south");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");
    }

    public void east() {

        position = "east";
        count++;
        System.out.println("\nEAST was clicked\nCounter : " + count);
        scoreLabelName.setText(" " + count);

        if (moon.equals("Haunter")) {

            mainTextArea.setText("You walked into a forest and found a mythical stone"
                    + "\n\nThis will evolve your " + moon + " into Genga");

            moon = "Genga";
            moonLabelName.setText(moon);

        } else if (moon.equals("Genga")) {

            mainTextArea.setText("You walked into a forest and " + moon + " got scared"
                    + "\n\n" + moon + " was delt 10 damage");

            moonHP = moonHP - 10;
            hpLabelNumber.setText("" + moonHP);

            if (moonHP < 1) {
                lose();
            }
        }

        choices[0].setText("Go west");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");

    }

    public void west() {

        position = "west";
        count++;
        System.out.println("\nWEST was clicked\nCounter : " + count);
        scoreLabelName.setText(" " + count);

        if (monsterHP > 0) {
            mainTextArea.setText("You encounter a " + enemy + "!\n From behind...\n\n What do you do?");

            choices[0].setText("Fight");
            choices[1].setText("Run");
            choices[2].setText("");
            choices[3].setText("");

        } else if (monsterHP <= 0) {

            mainTextArea.setText("Theres nothing here");

            choices[0].setText("Go east");
            choices[1].setText("");
            choices[2].setText("");
            choices[3].setText("");

        }
    }

    public void fight() {

        position = "fight";
        count++;
        System.out.println("\nFIGHT was clicked\nCounter : " + count);
        scoreLabelName.setText(" " + count);

        mainTextArea.setText(enemy + "'s HP: " + monsterHP + "\n\nWhat do you do?");
        choices[0].setText("Attack");
        choices[1].setText("Run");
        choices[2].setText("");
        choices[3].setText("");
    }

    public void playerAttack() {

        position = "playerAttack";

        int moonDamage = 0;

        if (moon.equals("Haunter")) {
            moonDamage = new java.util.Random().nextInt(10);
        } else if (moon.equals("Genga")) {
            moonDamage = new java.util.Random().nextInt(45) + 35;
        }

        mainTextArea.setText("You attacked the monster and gave...\n" + moonDamage + " damage!");

        monsterHP = monsterHP - moonDamage;

        choices[0].setText(">");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");
    }

    public void enemyAttack() {

        position = "monsterAttack";
        int enemyDamage = 0;

        //Each moon has a different damage value
        if (enemy.equals("Chaizard")) {
            enemyDamage = rand.nextInt(70) + 10;
        } else if (enemy.equalsIgnoreCase("Rayhorn")) {
            enemyDamage = rand.nextInt(45) + 40;
        } else if (enemy.equalsIgnoreCase("Treekoo")) {
            enemyDamage = rand.nextInt(60) + 30;
        } else if (enemy.equalsIgnoreCase("Torchick")) {
            enemyDamage = rand.nextInt(60) + 30;
        } else if (enemy.equalsIgnoreCase("Sulamance")) {
            enemyDamage = rand.nextInt(70) + 10;
        } else if (enemy.equalsIgnoreCase("Tiranita")) {
            enemyDamage = rand.nextInt(80) + 40;
        }

        mainTextArea.setText(enemy + " attacked " + moon + " for,\n" + enemyDamage + " damage!");

        moonHP = moonHP - enemyDamage;
        if (moonHP <= 0) {

            moonHP = 0;
            hpLabelNumber.setText("" + moonHP);

        } else {
            hpLabelNumber.setText("" + moonHP);
        }

        choices[0].setText(">");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");
    }

    public void win() {

        position = "win";

        mainTextArea.setText("You defeated the monster!"
                + "\nThe monster dropped a symbol!"
                + "\n\n(The Kanto Symbol)\n\n (This also scared " + trainer + " off)");

        kantoSymbol = 1;

        choices[0].setText("Go east");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");

    }

    public void lose() {

        position = "lose";

        mainTextArea.setText(moon + " is dead!\n\n!!GAME OVER!!");

        choices[0].setText("");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");

        choices[0].setVisible(false);
        choices[1].setVisible(false);
        choices[2].setVisible(false);
        choices[3].setVisible(false);

        lose.losers();
    }

    public void ending() {

        position = "ending";

        mainTextArea.setText("Guard: Thank God " + trainer + " is gone.....\nwait...what is that?"
                + "\n\nOMG THANK YOU!!!\n FOR FINDING OUR SYMBOL!!!\n~~~~WELCOME TO KANTO~~~~");

        choices[0].setText("");
        choices[1].setText("");
        choices[2].setText("");
        choices[3].setText("");

        choices[0].setVisible(false);
        choices[1].setVisible(false);
        choices[2].setVisible(false);
        choices[3].setVisible(false);

        win.winners();
    }
    
}
