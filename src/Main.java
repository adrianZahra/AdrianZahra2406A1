import com.dd.plist.PropertyListFormatException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by pccc on 8/27/2016.
 */
//this class is where the flow and execution of the game take place this will be replaced by a gui in the future
public class Main {

    //this is where the player can choose to either start the game or exit
    public static void main(String[] args) throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {
        Scanner startInput = new Scanner(System.in);
        String startChoice = " ";
        do {
            System.out.println("\n---------------------------------------------------------------------------\nPlease enter a menu choice \n Play \n Exit");
            startChoice = startInput.nextLine().toLowerCase();
            if (startChoice.equals("play")) {
                gameFlow();
            } else if (startChoice.equals("exit")) {
                System.out.println("\n---------------------------------------------------------------------------\nGoodbye");
            }
        } while (!startChoice.equals("play") && !startChoice.equals("exit"));
    }

    //this method is where the game both sets itself up and follows its loops to play
    static void gameFlow() throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        Game.deckInstance = new Deck();
        Game.playerArray = new ArrayList();
        Game.cardPile = new ArrayList();
        Game.winnerPile = new ArrayList();

        Scanner input = new Scanner(System.in);
        int playerAmount = 0;

        do {
            System.out.println("\n---------------------------------------------------------------------------\nEnter the number of players here: ");
            try {
                playerAmount = input.nextInt();
                if (playerAmount > 5 || playerAmount < 3) {
                    System.out.println("\n---------------------------------------------------------------------------\nThat player amount is not acceptable it must be between 3 & 5 inclusive");
                }
                input.nextLine();
            } catch (Exception e) {
                System.out.println("\n---------------------------------------------------------------------------\nYou must enter a number only!");
                input.nextLine();
            }
        } while (playerAmount > 5 || playerAmount < 3);

        System.out.println("\n---------------------------------------------------------------------------\nThere will be " + playerAmount + " players in the game");

        Collections.shuffle(Game.deckInstance.deckArray);

        //this part calls the constructor form the Player class
        //its objects are then stored in the player array
        for (int x = 0; x < playerAmount; x = x + 1) {
            String playerName;
            System.out.println("\n---------------------------------------------------------------------------\nEnter a name for player: " + x);
            playerName = input.nextLine();
            Player nextPlayer = new Player(playerName);
            while (nextPlayer.playerHand.size() < 8) {
                nextPlayer.playerHand.add(Game.deckInstance.deckArray.remove(0));
            }
            Game.playerArray.add(nextPlayer);
        }

        //this is the large set of loops that controlls the state of the game
        Game.roundType = Game.chooseType(Game.roundType);
        String gameOption = null;
        System.out.println("\n---------------------------------------------------------------------------\nThe type for the round is --> " + Game.roundType);
        while (Game.playerArray.size() > 1) {
            for (int icounter = 0; icounter < Game.playerArray.size(); icounter++) {
                if (Game.playerArray.get(icounter).inOut) {
                    do {
                        try {
                            do {
                                System.out.println("\n---------------------------------------------------------------------------\nEnter an option for player: " + Game.playerArray.get(icounter).playerName + " \n play \n pass");
                                gameOption = input.nextLine().toLowerCase();
                                if (gameOption.equals("play")) {
                                    Game.showCardPile();
                                    System.out.println("\n---------------------------------------------------------------------------\nThe type for the round is: " + Game.roundType + "\n");
                                    System.out.println(Game.playerArray.get(icounter).getPlayer());
                                    int cardPlaceNum = 0;
                                    for (Card cardSpace : Game.playerArray.get(icounter).playerHand) {
                                        System.out.println("Card Index: " + cardPlaceNum + " " + cardSpace);
                                        cardPlaceNum++;
                                    }
                                    Game.roundType = Game.cardCompare(Game.playerArray.get(icounter), Game.roundType, icounter);
                                    //if a player has 0 cards in their hand they will then be added to a winners array
                                    //when there is all but 1 player left in the playerArray then the games winning condition will be met
                                    if (Game.playerArray.get(icounter).playerHand.size() == 0) {
                                        System.out.println("\n---------------------------------------------------------------------------\nCongratulations " + Game.playerArray.get(icounter).getPlayer() + " for emptying your hand \n");
                                        Game.winnerPile.add(Game.playerArray.get(icounter));
                                        Game.playerArray.remove(icounter);
                                        playerAmount--;
                                        Game.newRound(playerAmount);
                                        icounter--;
                                    }

                                } else if (gameOption.equals("pass")) {
                                    Game.drawCard(Game.playerArray.get(icounter));
                                    System.out.println(Game.playerArray.get(icounter).getPlayer());
                                    Game.playerArray.get(icounter).inOut = Boolean.FALSE;
                                    Game.outCounter++;
                                    Game.newRound(playerAmount);
                                    break;

                                }
                            } while (Game.handCardValue < Game.pileCardValue/* || Game.handCardValue != null*/);

                        } catch (Exception e) {
                            System.out.println(" ");
                        }
                    } while (!gameOption.equals("play") && !gameOption.equals("pass"));
                }
            }
        }
        //when the game is over all the winning players will be shown in order
        System.out.println("\n---------------------------------------------------------------------------\nThe game has ended Congratulations winners");
        int winnerPlace = 1;
        for (Player winner : Game.winnerPile) {
            System.out.println(winnerPlace + " " + winner.getPlayer());
            winnerPlace++;
        }
    }

}

