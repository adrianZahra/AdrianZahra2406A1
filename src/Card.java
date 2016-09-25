import com.dd.plist.NSArray;
import com.dd.plist.NSString;

/**
 * Created by pccc on 8/27/2016.
 */


public abstract class Card {
    public String title;

    Card() {

    }

    Card(NSString in_name) {
        this.title = in_name.toString();
    }

    abstract String getChemistry();

    abstract String getClassification();

    abstract String getCrystal_system();

    abstract NSArray getOccurrence();

    abstract String getHardness();

    abstract String getSpecific_gravity(); //might change to float

    abstract String getCleavage();

    abstract String getCrustal_abundance();

    abstract String getEconomic_value();

    abstract String getDescription();

    public String toString() {
        return ("Card name: " + this.title + "\n Hardness: " + getHardness() + "\n Specific Gravity: " + getSpecific_gravity() + "\n Cleavage: "  + getCleavage() + "\n Crustal Abundance: "  + getCrustal_abundance() + "\n Economic Value: "  + getEconomic_value());
    }

}

abstract class MineralCard extends Card {
    public String chemistry;
    public String classification;
    public String crystal_system;
    public NSArray occurrence;
    public String hardness;
    public String specific_gravity; //might change to float
    public String cleavage;
    public String crustal_abundance;
    public String economic_value;

    MineralCard(NSString in_name, NSString in_class1, NSString in_class2, NSString in_class3, NSArray in_class4, NSString in_class5, NSString in_class6, NSString in_class7, NSString in_class8, NSString in_class9) {
        super(in_name);
        this.chemistry = in_class1.toString();
        this.classification = in_class2.toString();
        this.crystal_system = in_class3.toString();
        this.occurrence = in_class4;
        this.hardness = in_class5.toString();
        this.specific_gravity = in_class6.toString();
        this.cleavage = in_class7.toString();
        this.crustal_abundance = in_class8.toString();
        this.economic_value = in_class9.toString();
    }

    /*public String toString() { return (this.specific_gravity + " "); }*/

    @Override
    public String getChemistry() {
        return chemistry;
    }

    public String getClassification() {
        return classification;
    }

    public String getCrystal_system() {
        return crystal_system;
    }

    public NSArray getOccurrence() {
        return occurrence;
    }

    public String getHardness() {
        return hardness;
    }

    public String getSpecific_gravity() {
        return specific_gravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getCrustal_abundance() {
        return crustal_abundance;
    }

    public String getEconomic_value() {
        return economic_value;
    }

}

abstract class TrumpCard extends Card {
    public String subtitle;

    TrumpCard(NSString in_name, NSString in_class10) {
        super(in_name);
        this.subtitle = in_class10.toString();
    }

    @Override
    public String getDescription() {
        return subtitle;
    }
    /*
    public String toString() {
        return ("This Trump Card is " + this.title + "\n its description is " + this.subtitle + "\n");
    }
    */

}