import com.dd.plist.NSString;
import com.dd.plist.PropertyListFormatException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
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

    public static void main(String[] args) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        deckInstance = new Deck();
        playerArray = new ArrayList();
        cardPile = new ArrayList();

        Scanner input = new Scanner(System.in);
        int playerAmount = 0;

        String roundType = " ";


        do {
            System.out.println("Enter the number of players here --> ");
            playerAmount = input.nextInt();
        } while (playerAmount > 5 || playerAmount < 3);
        input.nextLine();


        for (int x = 0; x < playerAmount; x = x + 1) {
            String playerName;
            System.out.println("Enter a players name --> ");
            playerName = input.nextLine();
            Player nextPlayer = new Player(playerName);
            //Collections.shuffle(instance.deckArray);
            while (nextPlayer.playerHand.size() < 2) {
                nextPlayer.playerHand.add(deckInstance.deckArray.remove(0));
            }
            playerArray.add(nextPlayer);
        }

        System.out.println("The type for the round is --> " + roundType);
        roundType = chooseType(roundType);
        int outCounter = 0;
        while (playerArray.size() > 1) {
            for (int i = 0; i < playerArray.size(); i++) {
                if (playerArray.get(i).inOut){
                    System.out.println("Enter an option for player: " + playerArray.get(i).playerName + " \n play \n pass");
                    String gameOption = input.nextLine();
                    if (gameOption.equals("play")) {
                        cardCompare(roundType, 0, 0, 0);
                        placeCard(playerArray.get(i));
                        System.out.println(playerArray.get(i).getPlayer());

                    } else if (gameOption.equals("pass")) {
                        drawCard(playerArray.get(i));
                        System.out.println(playerArray.get(i).getPlayer());
                        playerArray.get(i).inOut = Boolean.FALSE;
                        outCounter ++;
                        newRound(playerAmount, outCounter);

                    } else if (gameOption.equals("look")) {
                        showCardPile();
                        newRound(playerAmount, outCounter);
                    }
                }
            }
        }


    }

    static void newRound(int playerAmountPlace, int outCounterPlace){
        if (outCounterPlace == playerAmountPlace){
            for (int i = 0; i < playerArray.size(); i++) {
                playerArray.get(i).inOut = Boolean.TRUE;
            }
        }
    }

    static void cardCompare(String cardCompareType, int playerIndex, int handCardIndex, int pileCardPlace) {
        switch (cardCompareType) {
            case "hardness":
                System.out.println("hardness");
                break;
            case "specific gravity":
                System.out.println("specific gravity");
                break;
            case "cleavage":
                System.out.println("cleavage");

            /*
            cardPile.add(deckInstance.deckArray.get(0));
            if (getCleavage(playerArray.get(playerIndex).playerHand.get(handCardIndex).getCleavage()) > getCleavage(cardPile.get(0).getCleavage()))
                ;
            System.out.println("card was good");
            */

                break;
            case "crustal abundance":
                System.out.println("crustal abundance");
                break;
            default:
                System.out.println("economic value");
                break;
        }


    }

    static String chooseType(String typeHolder) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter the type for the round --> \n Hardness \n specific gravity \n Cleavage \n crustal abundance \n economic value ");
            typeHolder = input.nextLine();
        }
        while (!typeHolder.equals("hardness") && !typeHolder.equals("specific gravity") && !typeHolder.equals("cleavage") && !typeHolder.equals("crustal abundance") && !typeHolder.equals("economic value"));

        return typeHolder;
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

    static void placeCard(Player playerPlace) {
        int cardHandIndex;
        Scanner input = new Scanner(System.in);
        System.out.println("type the number of the card you wish to place --> ");
        cardHandIndex = input.nextInt();
        cardPile.add(playerPlace.playerHand.get(cardHandIndex));
        System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
    }

    static void showCardPile() {
        System.out.println("<<BOTTOM OF THE PILE>> \n " + cardPile.toString());
    }

    static void drawCard(Player playerPlace) {
        playerPlace.playerHand.add(deckInstance.deckArray.get(0));
        deckInstance.deckArray.remove(0);
    }

}

//SetPlayer(deckInstance);
//deckInstance.print();
//playerInstance.printPlayer();
//System.out.println(newPlayer.getPlayer());
//System.out.print(instance.deckArray.get(0).toString());
//System.out.print(instance.deckArray.size());
//Collections.shuffle(instance.deckArray);


//for (Player j : playerArray)
//    System.out.println(j.getPlayer());

//System.out.println(playerArray.get(0).getPlayer());
//System.out.println(playerArray.get(0));

        /*
        drawCard(playerArray.get(0));
        System.out.println(playerArray.get(0).getPlayer());
        drawCard(playerArray.get(0));
        System.out.println(playerArray.get(0).getPlayer());
        drawCard(playerArray.get(1));
        System.out.println(playerArray.get(1).getPlayer());
        drawCard(playerArray.get(1));
        System.out.println(playerArray.get(1).getPlayer());
        drawCard(playerArray.get(2));
        System.out.println(playerArray.get(2).getPlayer());
        drawCard(playerArray.get(2));
        System.out.println(playerArray.get(2).getPlayer());
        */
//placeCard(playerArray.get(0));

//System.out.println(playerArray.get(0).getPlayer());

//placeCard(playerArray.get(0));

//System.out.println(playerArray.get(0).getPlayer());

//deckInstance.print();

//pileCreator();
