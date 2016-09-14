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

    public static void main(String[] args) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        Deck deckInstance = new Deck();
        //SetPlayers playerInstance = new SetPlayers();
        Scanner input = new Scanner(System.in);
        ArrayList<Player> playerArray = new ArrayList();
        //ArrayList<Card> cardPile = new ArrayList();

        String playerName;
        System.out.println("Enter a players name --> ");
        playerName = input.nextLine();
        //Collections.shuffle(instance.deckArray);
        ArrayList playersHand = new ArrayList();
        while (playersHand.size() < 2) {
            playersHand.add(deckInstance.deckArray.get(0));
            deckInstance.deckArray.remove(0);
        }
        playerArray.add(new Player(playerName, playersHand, Boolean.TRUE));


        //for (Player j : playerArray)
        //    System.out.println(j.getPlayer());

        //System.out.println(playerArray.get(0).getPlayer());

        drawCard(deckInstance, playerArray.get(0));

        System.out.println(playerArray.get(0).getPlayer());

        placeCard(playerArray.get(0));

        System.out.println(playerArray.get(0).getPlayer());

        //deckInstance.print();


    }


    static Object placeCard(Player playerPlace){
        int cardHandIndex;
        Scanner input = new Scanner(System.in);
        System.out.println("type the number of the card you wish to place --> ");
        cardHandIndex = input.nextInt();
        System.out.println(playerPlace.playerHand.get(cardHandIndex) + " was removed from hand");
        playerPlace.playerHand.remove(cardHandIndex);
        return playerPlace;
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