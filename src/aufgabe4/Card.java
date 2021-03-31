package aufgabe4;

import java.util.Random;

public abstract class Card {

    enum Suit { Karo, Herz, Pique, Kreuz};
    enum Rank { sieben, acht, neun, zehn, Bube, Dame, König, Ass}

    protected String colour;
    protected String value;

    public String getColour() {
        return colour;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return colour + "." + value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Card) {
            Card that = (Card) o;
            if(that.toString().equals(this.toString())){
                return true;
            }
            /* TODO: return true, wenn die Objekte this und that gleich sind */
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
