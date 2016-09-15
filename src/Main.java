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
        Integer playerArrayIndex = 0;



        do {
            System.out.println("Enter the number of players here --> ");
            playerArrayIndex = input.nextInt();
        } while (playerArrayIndex > 5 || playerArrayIndex < 3);
        input.nextLine();


        for (int x = 0; x < playerArrayIndex; x = x + 1){
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




        for (Player j : playerArray)
            System.out.println(j.getPlayer());

        //System.out.println(playerArray.get(0).getPlayer());
        //System.out.println(playerArray.get(0));

       // drawCard(deckInstance, playerArray.get(0));
       // System.out.println(playerArray.get(0).getPlayer());

        //placeCard(playerArray.get(0), cardPile);

        //System.out.println(playerArray.get(0).getPlayer());

        //placeCard(playerArray.get(0), cardPile);

        //System.out.println(playerArray.get(0).getPlayer());

        //deckInstance.print();


    }


    static Object placeCard(Player playerPlace){
        int cardHandIndex;
        Scanner input = new Scanner(System.in);
        System.out.println("type the number of the card you wish to place --> ");
        cardHandIndex = input.nextInt();
        pileCreator(playerPlace, cardPile, cardHandIndex);
        System.out.println(playerPlace.playerHand.remove(cardHandIndex) + " was removed from hand");
        return playerPlace;
    }

    static Object pileCreator(Player playerPlace, ArrayList pilePlace, int indexPlace){
        pilePlace.add(playerPlace.playerHand.get(indexPlace));
        System.out.println("<<BOTTOM OF THE PILE>> \n " + pilePlace.toString());
        return pilePlace;
    }

    static Object drawCard(Deck deckPlace, Player playerPlace){
        playerPlace.playerHand.add(deckPlace.deckArray.get(0));
        deckPlace.deckArray.remove(0);

        return deckPlace;
    }
}

//SetPlayer(deckInstance);
//deckInstance.print();
//playerInstance.printPlayer();
//System.out.println(newPlayer.getPlayer());
//System.out.print(instance.deckArray.get(0).toString());
//System.out.print(instance.deckArray.size());
//Collections.shuffle(instance.deckArray);