import com.dd.plist.PropertyListFormatException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

/**
 * Created by pccc on 8/27/2016.
 */
public class Main {
    public static void main(String[] args) throws PropertyListFormatException, ParserConfigurationException, SAXException, ParseException, IOException {
        Deck instance = new Deck();
        Player newPlayer = new Player("buthead", instance.deckArray.get(1), Boolean.TRUE);

        System.out.println(newPlayer.getPlayer());



        //instance.print();
        //System.out.print(instance.deckArray.get(0).toString());
        //System.out.print(instance.deckArray.size());
        //Collections.shuffle(instance.deckArray);
        //instance.print();



    }
}
