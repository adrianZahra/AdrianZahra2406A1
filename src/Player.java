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
    ArrayList playerHand;
    Boolean inOut;

    Player(String in_playerName, ArrayList in_playerHand, Boolean in_inOut){
        this.playerName = in_playerName;
        this.playerHand = in_playerHand;
        this.inOut = in_inOut;
    }

    public String getPlayer(){
        return (this.playerName + " " + this.playerHand + " " + this.inOut);
    }
}
