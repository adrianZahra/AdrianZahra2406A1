import com.dd.plist.*;
import com.dd.plist.PropertyListParser;

import javax.xml.parsers.*;

import org.xml.sax.*;

import java.text.ParseException;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by pccc on 8/27/2016.
 */
//this class is where the DDPlist reader searchs the Plist file for the data so it can be stored as an object in an array
public class Deck {
    public static ArrayList<Card> deckArray = new ArrayList();

    Deck() throws ParserConfigurationException, ParseException, SAXException, PropertyListFormatException, IOException {

        try {
            Integer cardCounter = -1;
            Integer trumpCounter = 53;

            File file = new File("MstCards_151021.plist");
            NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(file);
            NSArray cardsArray = (NSArray) rootDict.objectForKey("cards");


            while (cardCounter < 53) {
                cardCounter = cardCounter + 1;

                NSDictionary card0 = (NSDictionary) cardsArray.objectAtIndex(cardCounter); // index of the cards

                //each of these searches for the key and assigns its value to a place
                // in the object that is then stored in the arraylist deck
                NSString value0 = (NSString) card0.objectForKey("title");
                NSString value1 = (NSString) card0.objectForKey("chemistry");
                NSString value2 = (NSString) card0.objectForKey("classification");
                NSString value3 = (NSString) card0.objectForKey("crystal_system");
                //NSString value4 = (NSString) card0.objectForKey("occurrence"); /*value4 +*/
                NSArray value4 = (NSArray) card0.objectForKey("occurrence");
                //NSString value41 = (NSString) card0.objectForKey("string");
                NSString value5 = (NSString) card0.objectForKey("hardness");
                NSString value6 = (NSString) card0.objectForKey("specific_gravity");
                NSString value7 = (NSString) card0.objectForKey("cleavage");
                NSString value8 = (NSString) card0.objectForKey("crustal_abundance");
                NSString value9 = (NSString) card0.objectForKey("economic_value");

                deckArray.add(new MineralCard(value0, value1, value2, value3, value4, value5, value6, value7, value8, value9) {
                    @Override
                    String getDescription() {
                        return null;
                    }
                });

            }

            while (trumpCounter < 59) {
                trumpCounter = trumpCounter + 1;

                NSDictionary card1 = (NSDictionary) cardsArray.objectAtIndex(trumpCounter); // index of the cards

                NSString value0 = (NSString) card1.objectForKey("title");
                NSString value1 = (NSString) card1.objectForKey("subtitle");

                deckArray.add(new TrumpCard(value0, value1) {
                    @Override
                    String getChemistry() {
                        return null;
                    }

                    @Override
                    String getClassification() {
                        return null;
                    }

                    @Override
                    String getCrystal_system() {
                        return null;
                    }

                    @Override
                    NSArray getOccurrence() {
                        return null;
                    }

                    @Override
                    String getHardness() {
                        return null;
                    }

                    @Override
                    String getSpecific_gravity() {
                        return null;
                    }

                    @Override
                    String getCleavage() {
                        return null;
                    }

                    @Override
                    String getCrustal_abundance() {
                        return null;
                    }

                    @Override
                    String getEconomic_value() {
                        return null;
                    }
                });

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void print() {
        for (Card i : deckArray) {
            System.out.println(i.toString());
        }
    }
}
