import com.dd.plist.PropertyListFormatException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by pccc on 9/6/2016.
 */

public class Player {
    String playerName;
    ArrayList<Card> playerHand;
    Boolean inOut;

    Player(String in_playerName) {
        this.playerName = in_playerName;
        this.playerHand = new ArrayList<Card>();
        this.inOut = true;
    }

    public String getPlayer() {
        String hand = "";

        for (Card i : playerHand) {
            hand += i + " ";
        }

        return ("Player name: " + this.playerName/* + "\n " + hand*/);
    }
}
