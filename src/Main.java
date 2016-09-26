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
    static Deck deckInstance;
    static ArrayList<Player> playerArray;
    static ArrayList<Card> cardPile;
    static ArrayList<Player> winnerPile;

    public static void main(String[] args) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        deckInstance = new Deck();
        playerArray = new ArrayList();
        cardPile = new ArrayList();
        winnerPile = new ArrayList();

        Scanner input = new Scanner(System.in);
        int playerAmount = 0;
        String roundType = " ";


        do {
            System.out.println("Enter the number of players here: ");
            playerAmount = input.nextInt();
        } while (playerAmount > 5 || playerAmount < 3);
        input.nextLine();

        Collections.shuffle(deckInstance.deckArray);

        for (int x = 0; x < playerAmount; x = x + 1) {
            String playerName;
            System.out.println("Enter a name for player: " + x);
            playerName = input.nextLine();
            Player nextPlayer = new Player(playerName);
            while (nextPlayer.playerHand.size() < 8) {
                nextPlayer.playerHand.add(deckInstance.deckArray.remove(0));
            }
            playerArray.add(nextPlayer);
        }

        roundType = chooseType(roundType);
        String gameOption;
        System.out.println("\n" + "The type for the round is --> " + roundType + "\n");
        int outCounter = 0;
        while (playerArray.size() > 1) {
            for (int i = 0; i < playerArray.size(); i++) {
                if (playerArray.get(i).inOut) {
                    do {
                        System.out.println("\n" + "Enter an option for player: " + playerArray.get(i).playerName + " \n play \n pass");
                        gameOption = input.nextLine().toLowerCase();
                        if (gameOption.equals("play")) {
                            showCardPile();
                            System.out.println("The type for the round is: " + roundType + "\n");
                            System.out.println(playerArray.get(i).getPlayer());
                            cardCompare(playerArray.get(i), roundType, i);
                            if (playerArray.get(i).playerHand.size() == 0) {
                                System.out.println("Congratulations " + playerArray.get(i).getPlayer() + " for emptying your hand \n");
                                winnerPile.add(playerArray.get(i));
                                playerArray.remove(i);
                            }

                        } else if (gameOption.equals("pass")) {
                            drawCard(playerArray.get(i));
                            System.out.println(playerArray.get(i).getPlayer());
                            playerArray.get(i).inOut = Boolean.FALSE;
                            outCounter++;
                            outCounter = newRound(playerAmount, outCounter);
                            if (outCounter == 0) {
                                roundType = chooseType(roundType);
                            }

                        }
                    } while (!gameOption.equals("play") && !gameOption.equals("pass"));
                }
            }
        }
        System.out.println("\n" + "The game has ended Congratulations winners");
        for (Player winner : winnerPile) {
            System.out.println(winner.getPlayer());
        }

    }

    static void cardCompare(Player playerPlace, String cardCompareType, int playerIndex) {
        Integer handCardValue;
        Integer pileCardValue;
        int cardHandIndex;
        Scanner input = new Scanner(System.in);
        System.out.println("type the number of the card you wish to place --> ");
        cardHandIndex = input.nextInt();

        switch (cardCompareType) {
            case "hardness":
                handCardValue = getHardness(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getHardness(cardPile.get(0).getCleavage());

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else System.out.println("the cards value was not higher! Try again");
                break;


            case "specific gravity":

                handCardValue = getSpecificGravity(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getSpecificGravity(cardPile.get(0).getCleavage());

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else System.out.println("the cards value was not higher! Try again");
                break;


            case "cleavage":
                handCardValue = getCleavage(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getCleavage(cardPile.get(0).getCleavage());

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else {
                    System.out.println("the cards value was not higher! Try again");

                }

                break;


            case "crustal abundance":
                handCardValue = getCrustalAbundance(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getCrustalAbundance(cardPile.get(0).getCleavage());

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else System.out.println("the cards value was not higher! Try again");
                break;


            default:
                handCardValue = getEconomicValue(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getEconomicValue(cardPile.get(0).getCleavage());

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else System.out.println("the cards value was not higher! Try again");
                break;
        }


    }

    static int newRound(int playerAmountPlace, int outCounterPlace) {
        playerAmountPlace--;
        if (outCounterPlace == playerAmountPlace) {
            System.out.println("\n" + "-----------------------------------\n The current round has ended \n the new round will start now \n ----------------------------------- \n");
            for (int i = 0; i < playerArray.size(); i++) {
                playerArray.get(i).inOut = Boolean.TRUE;
            }
            outCounterPlace = 0;


        }
        return outCounterPlace;
    }

    static String chooseType(String typeHolder) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n" + "Enter the type for the round --> \n hardness \n specific gravity \n cleavage \n crustal abundance \n economic value \n");
            typeHolder = input.nextLine();
        }
        while (!typeHolder.equals("hardness") && !typeHolder.equals("specific gravity") && !typeHolder.equals("cleavage") && !typeHolder.equals("crustal abundance") && !typeHolder.equals("economic value"));

        return typeHolder;
    }

    static int getSpecificGravity(String gravValue) {
        int gravValueInt = 0;
        switch (gravValue) {
            case "2.2":
                return 1;
            case "2.3":
                return 2;
            case "2.4":
                return 3;
            case "2.5-2.6":
                return 4;
            case "2.6":
                return 5;
            case "2.65":
                return 6;
            case "2.6-2.7":
                return 7;
            case "2.7":
                return 8;
            case "2.6-2.8":
                return 9;
            case "2.6-2.9":
                return 10;
            case "2.8-2.9":
                return 11;
            case "2.9":
                return 12;
            case "3.0":
                return 13;
            case "3.15":
                return 14;
            case "3.1-3.2":
                return 15;
            case "3.0-3.2":
                return 16;
            case "3.2":
                return 17;
            case "3.25":
                return 18;
            case "2.6-3.3":
                return 19;
            case "2.7-3.3":
                return 20;
            case "3.0-3.5":
                return 21;
            case "3.5":
                return 22;
            case "3.2-3.5":
                return 23;
            case "3.2-3.6":
                return 24;
            case "3.4-3.6":
                return 25;
            case "3.5-3.6":
                return 26;
            case "3.5-3.7":
                return 27;
            case "3.7-3.8":
                return 28;
            case "3.2-3.9":
                return 29;
            case "4.0":
                return 30;
            case "3.9-4.1":
                return 31;
            case "3.5-4.3":
                return 32;
            case "4.1-4.3":
                return 33;
            case "4.3":
                return 34;
            case "3.2-4.4":
                return 35;
            case "4.5":
                return 36;
            case "4.6":
                return 37;
            case "4.6-4.7":
                return 38;
            case "4.7":
                return 39;
            case "4.7-4.8":
                return 40;
            case "5.0":
                return 41;
            case "4.5-5.1":
                return 42;
            case "5.2":
                return 43;
            case "5.0-5.3":
                return 44;
            case "5.3":
                return 45;
            case "6.9-7.1":
                return 46;
            case "7.5-7.6":
                return 47;
            case "19.3":
                return 48;

        }
        return gravValueInt;
    }

    static int getCleavage(String cleavValue) {
        int cleavValueInt = 0;
        switch (cleavValue) {
            case "none":
                return 1;
            case "poor/none":
                return 2;
            case "1 poor":
                return 3;
            case "2 poor":
                return 4;
            case "1 good":
                return 5;
            case "1 good, 1 poor":
                return 6;
            case "2 good":
                return 7;
            case "3 good":
                return 8;
            case "1 perfect":
                return 9;
            case "1 perfect, 1 good":
                return 10;
            case "1 perfect, 2 good":
                return 11;
            case "2 perfect, 1 good":
                return 12;
            case "3 perfect":
                return 13;
            case "4 perfect":
                return 14;
            case "6 perfect":
                return 15;

        }
        return cleavValueInt;
    }

    static int getHardness(String hardValue) {
        int hardValueInt = 0;
        switch (hardValue) {
            case "1":
                return 1;
            case "1-1.5":
                return 2;
            case "1-2":
                return 3;
            case "2":
                return 4;
            case "1.5-2.5":
                return 5;
            case "2.5":
                return 6;
            case "2-3":
                return 7;
            case "2.5-3":
                return 8;
            case "3":
                return 9;
            case "2.5-3.5":
                return 10;
            case "3-3.5":
                return 11;
            case "3.5-4":
                return 12;
            case "4":
                return 13;
            case "3.5-4.5":
                return 14;
            case "4-4.5":
                return 15;
            case "5":
                return 16;
            case "5-5.5":
                return 17;
            case "5.5":
                return 18;
            case "5-6":
                return 19;
            case "5.5-6":
                return 20;
            case "6":
                return 21;
            case "5.5-6.5":
                return 22;
            case "6-6.5":
                return 23;
            case "5.5-7":
                return 24;
            case "6-7":
                return 25;
            case "6.5-7":
                return 26;
            case "7":
                return 27;
            case "6-7.5":
                return 28;
            case "6.5-7.5":
                return 29;
            case "7-7.5":
                return 30;
            case "7.5":
                return 31;
            case "7.5-8":
                return 32;
            case "8":
                return 33;
            case "9":
                return 34;
            case "10":
                return 35;

        }
        return hardValueInt;
    }

    static int getEconomicValue(String ecoValue) {
        int ecoValueInt = 0;
        switch (ecoValue) {
            case "trivial":
                return 1;
            case "low":
                return 2;
            case "moderate":
                return 3;
            case "high":
                return 4;
            case "very high":
                return 5;
            case "I'm rich":
                return 6;
        }
        return ecoValueInt;
    }

    static int getCrustalAbundance(String crustValue) {
        int crustValueInt = 0;
        switch (crustValue) {
            case "ultratrace":
                return 1;
            case "trace":
                return 2;
            case "low":
                return 3;
            case "moderate":
                return 4;
            case "high":
                return 5;
            case "very high":
                return 6;
        }
        return crustValueInt;

    }

    static void showCardPile() {
        System.out.println("\n" + "--------------------\nTOP OF THE CARD PILE\n " + cardPile.toString() + "\n--------------------" + "\n");
    }

    static void drawCard(Player playerPlace) {
        playerPlace.playerHand.add(deckInstance.deckArray.get(0));
        deckInstance.deckArray.remove(0);
    }

}
