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

        roundType = chooseType(roundType);
        System.out.println("The type for the round is --> " + roundType);
        int outCounter = 0;
        while (playerArray.size() > 1) {
            for (int i = 0; i < playerArray.size(); i++) {
                if (playerArray.get(i).inOut) {
                    System.out.println("Enter an option for player: " + playerArray.get(i).playerName + " \n 1.play \n 2.pass");
                    int gameOption = input.nextInt();
                    if (gameOption == 1) {
                        showCardPile();
                        System.out.println(playerArray.get(i).getPlayer());
                        cardCompare(playerArray.get(i),roundType, i);

                    } else if (gameOption == 2) {
                        drawCard(playerArray.get(i));
                        System.out.println(playerArray.get(i).getPlayer());
                        playerArray.get(i).inOut = Boolean.FALSE;
                        outCounter++;
                        outCounter = newRound(playerAmount, outCounter);

                    }
                }
            }
        }
    }

    static int newRound(int playerAmountPlace, int outCounterPlace) {
        playerAmountPlace--;
        if (outCounterPlace == playerAmountPlace) {
            System.out.println("-----------------------------------\n The current round has ended \n the new round will start now \n -----------------------------------");
            for (int i = 0; i < playerArray.size(); i++) {
                playerArray.get(i).inOut = Boolean.TRUE;
            }
            outCounterPlace = 0;
        }
        return outCounterPlace;
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

                }catch (IndexOutOfBoundsException e){
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = 0;
                }

                if (handCardValue > pileCardValue){
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                }else System.out.println("the cards value was not higher! Try again");
                break;

            case "specific gravity":
                handCardValue = getSpecificGravity(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getSpecificGravity(cardPile.get(0).getCleavage());

                }catch (IndexOutOfBoundsException e){
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = 0;
                }

                if (handCardValue > pileCardValue){
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                }else System.out.println("the cards value was not higher! Try again");
                break;

            case "cleavage":
                handCardValue = getCleavage(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getCleavage(cardPile.get(0).getCleavage());

                }catch (IndexOutOfBoundsException e){
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = 0;
                }

                if (handCardValue > pileCardValue){
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                }else System.out.println("the cards value was not higher! Try again");

                break;
            case "crustal abundance":
                handCardValue = getCrustalAbundance(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getCrustalAbundance(cardPile.get(0).getCleavage());

                }catch (IndexOutOfBoundsException e){
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = 0;
                }

                if (handCardValue > pileCardValue){
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                }else System.out.println("the cards value was not higher! Try again");
                break;

            default:
                handCardValue = getEconomicValue(playerArray.get(playerIndex).playerHand.get(cardHandIndex).getCleavage());
                try {
                    pileCardValue = getEconomicValue(cardPile.get(0).getCleavage());

                }catch (IndexOutOfBoundsException e){
                    System.out.println("there was no card on the pile you. may place your card");
                    pileCardValue = 0;
                }

                if (handCardValue > pileCardValue){
                    System.out.println("the card has a higher value");
                    cardPile.add(playerPlace.playerHand.get(cardHandIndex));
                    System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
                }else System.out.println("the cards value was not higher! Try again");
                break;
        }


    }

    static int getSpecificGravity(String cleavage) {
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

    static String chooseType(String typeHolder) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Enter the type for the round --> \n hardness \n specific gravity \n cleavage \n crustal abundance \n economic value ");
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
