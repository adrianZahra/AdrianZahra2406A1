import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pccc on 9/27/2016.
 */
//this class contains all variables and methods that help the game run

public class Game {
    static String roundType;
    static int outCounter;
    static Integer handCardValue;
    static Integer pileCardValue;


    Game() {
        this.roundType = " ";
        this.outCounter = 0;
        this.handCardValue = null;
        this.pileCardValue = null;

    }

    static Deck deckInstance;
    static ArrayList<Player> playerArray;
    static ArrayList<Card> cardPile;
    static ArrayList<Player> winnerPile;

    /*this function is used to help compare the types and ranks between the cards.
    it also handles the trump cards and special winning conditions*/
    static String cardCompare(Player playerPlace, String cardCompareType, int playerIndex) {
        Scanner input = new Scanner(System.in);
        int cardHandIndex = -1;
        int arrayIndexNum = cardPile.size() - 1;
        do {
            System.out.println("\n---------------------------------------------------------------------------\ntype the number of the card you wish to place --> ");
            try {
                cardHandIndex = input.nextInt();
                if (cardHandIndex > playerArray.get(playerIndex).playerHand.size() || cardHandIndex < 0) {
                    System.out.println("\n---------------------------------------------------------------------------\nThat index is not acceptable");
                }
                input.nextLine();
            } catch (Exception e) {
                System.out.println("\n---------------------------------------------------------------------------\nYou must enter a number only!");
                input.nextLine();
            }
        } while (cardHandIndex > playerArray.get(playerIndex).playerHand.size() || cardHandIndex < 0);

        switch (cardCompareType) {
            case "hardness":
                try {
                    handCardValue = getHardnessVal(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getHardness());
                } catch (NullPointerException e) {
                    System.out.println("\n--------------------------------------------------------------------------\nyou have played " + playerArray.get(playerIndex).playerHand.get(cardHandIndex).title);
                    if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geologist")) {
                        cardCompareType = chooseType(cardCompareType).toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;
                    } else if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geophysicist") && playerArray.get(playerIndex).playerHand.toString().contains("Magnetite")) {
                        System.out.println("\nYou have Magnetite in you hand! ");
                        while (playerArray.get(playerIndex).playerHand.size() != 0) {
                            playerArray.get(playerIndex).playerHand.remove(0);
                        }
                        trumpPlayerReset();
                        break;
                    } else {
                        cardCompareType = playerArray.get(playerIndex).playerHand.get(cardHandIndex).getDescription().toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;
                    }
                }

                try {
                    pileCardValue = getHardnessVal(cardPile.get(arrayIndexNum).getHardness());

                } catch (Exception e) {
                    System.out.println("\n---------------------------------------------------------------------------\nthere was no value to comapre. you may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("\n---------------------------------------------------------------------------\nthe card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else if (handCardValue.equals(pileCardValue)) {
                    System.out.println("\n---------------------------------------------------------------------------\nboth cards equal try again");
                } else if (handCardValue == null) {
                    System.out.println(" ");
                } else
                    System.out.println("\n---------------------------------------------------------------------------\nthe cards value was not higher! Try again");
                break;


            case "specific gravity":

                try {
                    handCardValue = getSpecificGravityVal(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getSpecific_gravity());
                } catch (NullPointerException e) {
                    System.out.println("\n---------------------------------------------------------------------------\nyou have played" + playerArray.get(playerIndex).playerHand.get(cardHandIndex).title);
                    if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geologist")) {
                        cardCompareType = chooseType(cardCompareType).toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    } else if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geophysicist") && playerArray.get(playerIndex).playerHand.toString().contains("Magnetite")) {
                        System.out.println("\nYou have Magnetite in you hand! ");
                        while (playerArray.get(playerIndex).playerHand.size() != 0) {
                            playerArray.get(playerIndex).playerHand.remove(0);

                        }
                        trumpPlayerReset();
                        break;
                    } else {
                        cardCompareType = playerArray.get(playerIndex).playerHand.get(cardHandIndex).getDescription().toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    }
                }

                try {
                    pileCardValue = getSpecificGravityVal(cardPile.get(arrayIndexNum).getSpecific_gravity());

                } catch (Exception e) {
                    System.out.println("\n---------------------------------------------------------------------------\nthere was no value to comapre. you may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("\n---------------------------------------------------------------------------\nthe card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));

                    System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else if (handCardValue == null) {
                    System.out.println(" ");
                } else
                    System.out.println("\n---------------------------------------------------------------------------\nthe cards value was not higher! Try again");
                break;


            case "cleavage":
                try {
                    handCardValue = getCleavageVal(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                } catch (NullPointerException e) {
                    System.out.println("\n---------------------------------------------------------------------------\nyou have played" + playerArray.get(playerIndex).playerHand.get(cardHandIndex).title);
                    if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geologist")) {
                        cardCompareType = chooseType(cardCompareType).toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    } else if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geophysicist") && playerArray.get(playerIndex).playerHand.toString().contains("Magnetite")) {
                        System.out.println("\nYou have Magnetite in you hand! ");
                        while (playerArray.get(playerIndex).playerHand.size() != 0) {
                            playerArray.get(playerIndex).playerHand.remove(0);

                        }
                        trumpPlayerReset();
                        break;
                    } else {
                        cardCompareType = playerArray.get(playerIndex).playerHand.get(cardHandIndex).getDescription().toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    }
                }

                try {
                    pileCardValue = getCleavageVal(cardPile.get(arrayIndexNum).getCleavage());

                } catch (Exception e) {
                    System.out.println("\n---------------------------------------------------------------------------\nthere was no value to comapre. you may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("\n---------------------------------------------------------------------------\nthe card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));

                    System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else if (handCardValue == null) {
                    System.out.println(" ");
                } else
                    System.out.println("\n---------------------------------------------------------------------------\nthe cards value was not higher! Try again");
                break;


            case "crustal abundance":
                try {
                    handCardValue = getCrustalAbundanceVal(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCrustal_abundance());
                } catch (NullPointerException e) {
                    System.out.println("\n---------------------------------------------------------------------------\nyou have played" + playerArray.get(playerIndex).playerHand.get(cardHandIndex).title);
                    if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geologist")) {
                        cardCompareType = chooseType(cardCompareType).toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    } else if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geophysicist") && playerArray.get(playerIndex).playerHand.toString().contains("Magnetite")) {
                        System.out.println("\nYou have Magnetite in you hand! ");
                        while (playerArray.get(playerIndex).playerHand.size() != 0) {
                            playerArray.get(playerIndex).playerHand.remove(0);

                        }
                        trumpPlayerReset();
                        break;
                    } else {
                        cardCompareType = playerArray.get(playerIndex).playerHand.get(cardHandIndex).getDescription().toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    }
                }

                try {
                    pileCardValue = getCrustalAbundanceVal(cardPile.get(arrayIndexNum).getCrustal_abundance());

                } catch (Exception e) {
                    System.out.println("\n---------------------------------------------------------------------------\nthere was no value to comapre. you may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("\n---------------------------------------------------------------------------\nthe card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));

                    System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else if (handCardValue == null) {
                    System.out.println(" ");
                } else
                    System.out.println("\n---------------------------------------------------------------------------\nthe cards value was not higher! Try again");
                break;


            default:
                try {
                    handCardValue = getEconomicValueVal(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getEconomic_value());
                } catch (NullPointerException e) {
                    System.out.println("\n--------------------------------------------------------------------------\nyou have played" + playerArray.get(playerIndex).playerHand.get(cardHandIndex).title);
                    if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geologist")) {
                        cardCompareType = chooseType(cardCompareType).toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    } else if (playerArray.get(playerIndex).playerHand.get(cardHandIndex).title.equals("The Geophysicist") && playerArray.get(playerIndex).playerHand.toString().contains("Magnetite")) {
                        System.out.println("\nYou have Magnetite in you hand! ");
                        while (playerArray.get(playerIndex).playerHand.size() != 0) {
                            playerArray.get(playerIndex).playerHand.remove(0);

                        }
                        trumpPlayerReset();
                        break;
                    } else {
                        cardCompareType = playerArray.get(playerIndex).playerHand.get(cardHandIndex).getDescription().toLowerCase();
                        trumpPlayerReset();
                        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                        System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                        break;

                    }
                }

                try {
                    pileCardValue = getEconomicValueVal(cardPile.get(arrayIndexNum).getEconomic_value());

                } catch (Exception e) {
                    System.out.println("\n---------------------------------------------------------------------------\nthere was no value to comapre. you may place your card");
                    pileCardValue = -1;
                }

                if (handCardValue > pileCardValue) {
                    System.out.println("\n---------------------------------------------------------------------------\nthe card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));

                    System.out.println("\n---------------------------------------------------------------------------\n" + playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                } else if (handCardValue == null) {
                    System.out.println(" ");
                } else
                    System.out.println("\n---------------------------------------------------------------------------\nthe cards value was not higher! Try again");
                break;
        }
        return cardCompareType;

    }


    /*this method i called when a new round needs to start.
    it only actiavtes however, when Data.outCounter equals the playerAmount
    It sets all players inOut counter to True and makes the player choose a new type for the round*/
    static void newRound(int playerAmountPlace) {
        playerAmountPlace--;
        if (Game.outCounter == playerAmountPlace) {
            System.out.println("\n---------------------------------------------------------------------------\nThe current round has ended \n the new round will start now");
            for (int i = 0; i < playerArray.size(); i++) {
                playerArray.get(i).inOut = Boolean.TRUE;
            }
            Game.roundType = chooseType(Game.roundType);
            Game.outCounter = 0;
        }
    }

    /*this method has similar functionality to the new round method.
    it is called when a player uses a trump card and it resets everyones inOut counter to True*/
    static void trumpPlayerReset() {
        System.out.println("\n---------------------------------------------------------------------------\nA trump card has been played everyone is back in the round now");
        for (int i = 0; i < playerArray.size(); i++) {
            playerArray.get(i).inOut = Boolean.TRUE;
        }
        Game.outCounter = 0;
    }


    /*this method allows the player to choose a type for the rounds.
    it takes in the current type then swaps it and returns it */
    static String chooseType(String typeHolder) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("\n---------------------------------------------------------------------------\nEnter the type you wish to set --> \n hardness \n specific gravity \n cleavage \n crustal abundance \n economic value \n");
            typeHolder = input.nextLine().toLowerCase();
        }
        while (!typeHolder.equals("hardness") && !typeHolder.equals("specific gravity") && !typeHolder.equals("cleavage") && !typeHolder.equals("crustal abundance") && !typeHolder.equals("economic value"));

        return typeHolder;
    }


    /* the following set of functions takes in the string version of the
    associated data on the card. this data is put in a numerical order and returns an integer
     for comparison
     */
    static int getSpecificGravityVal(String gravValue) {
        int gravValueInt = 0;
        switch (gravValue) {
            case "2.2":
                return 1;
            case "2.3":
                return 2;
            case "2.4":
                return 3;
            case "2.5 - 2.6":
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

    static int getCleavageVal(String cleavValue) {
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

    static int getHardnessVal(String hardValue) {
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
            case "3.54.5":
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

    static int getEconomicValueVal(String ecoValue) {
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

    static int getCrustalAbundanceVal(String crustValue) {
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


    /*this function simply shows all the cards that were discarded from players hands*/
    static void showCardPile() {
        System.out.println("\n---------------------------------------------------------------------------\nBOTTOM OF THE CARD PILE\n " + cardPile.toString());
    }

    /*this function is called when the player want to draw a card
    it includes an exception to handle when the deck runs out of cards
     */
    static void drawCard(Player playerPlace) {
        try {
            playerPlace.playerHand.add(deckInstance.deckArray.get(0));
            deckInstance.deckArray.remove(0);
        } catch (IndexOutOfBoundsException c) {
            System.out.println("\n---------------------------------------------------------------------------\nThere are no more cards in the deck");
        }

    }

}
