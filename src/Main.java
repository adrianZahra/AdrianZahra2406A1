import com.dd.plist.PropertyListFormatException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by pccc on 8/27/2016.
 */
public class Main {

    public static void main(String[] args) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        Scanner startInput = new Scanner(System.in);
        String startChoice = " ";
        do {
            System.out.println("Please enter a menu choice \n Play \n Exit");
            startChoice = startInput.nextLine().toLowerCase();
            if (startChoice.equals("play")) {
                gameFlow();
            } else if (startChoice.equals("exit")) {
                System.out.println("Goodbye");
            }
        } while (!startChoice.equals("play") && !startChoice.equals("exit"));
    }

    static void gameFlow() throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        Game.deckInstance = new Deck();
        Game.playerArray = new ArrayList();
        Game.cardPile = new ArrayList();
        Game.winnerPile = new ArrayList();

        Scanner input = new Scanner(System.in);
        int playerAmount = 0;


        do {
            System.out.println("Enter the number of players here: ");
            playerAmount = input.nextInt();
        } while (playerAmount > 5 || playerAmount < 3);
        input.nextLine();

        Collections.shuffle(Game.deckInstance.deckArray);


        for (int x = 0; x < playerAmount; x = x + 1) {
            String playerName;
            System.out.println("Enter a name for player: " + x);
            playerName = input.nextLine();
            Player nextPlayer = new Player(playerName);
            while (nextPlayer.playerHand.size() < 1) {
                nextPlayer.playerHand.add(Game.deckInstance.deckArray.remove(0));
            }
            Game.playerArray.add(nextPlayer);
        }

        Game.roundType = Game.chooseType(Game.roundType);
        String gameOption;
        System.out.println("\n" + "The type for the round is --> " + Game.roundType + "\n");
        while (Game.playerArray.size() > 1) {
            for (int i = 0; i < Game.playerArray.size(); i++) {
                if (Game.playerArray.get(i).inOut) {
                    do {
                        System.out.println("\n" + "Enter an option for player: " + Game.playerArray.get(i).playerName + " \n play \n pass");
                        gameOption = input.nextLine().toLowerCase();
                        if (gameOption.equals("play")) {
                            Game.showCardPile();
                            System.out.println("The type for the round is: " + Game.roundType + "\n");
                            System.out.println(Game.playerArray.get(i).getPlayer());
                            Game.roundType = Game.cardCompare(Game.playerArray.get(i), Game.roundType, i);
                            if (Game.playerArray.get(i).playerHand.size() == 0) {
                                System.out.println("Congratulations " + Game.playerArray.get(i).getPlayer() + " for emptying your hand \n");
                                Game.winnerPile.add(Game.playerArray.get(i));
                                Game.playerArray.remove(i);
                                playerAmount--;
                            }

                        } else if (gameOption.equals("pass")) {
                            Game.drawCard(Game.playerArray.get(i));
                            System.out.println(Game.playerArray.get(i).getPlayer());
                            Game.playerArray.get(i).inOut = Boolean.FALSE;
                            Game.outCounter++;
                            Game.newRound(playerAmount);

                        }
                    } while (!gameOption.equals("play") && !gameOption.equals("pass"));
                }
            }
        }
        System.out.println("\n" + "The game has ended Congratulations winners");
        int winnerPlace = 1;
        for (Player winner : Game.winnerPile) {
            System.out.println(winnerPlace + " " + winner.getPlayer());
            winnerPlace++;
        }
    }

}

