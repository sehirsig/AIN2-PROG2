package aufgabe7;

import java.util.Random;

public abstract class Card implements Comparable<Card> {

    @Override
    public int compareTo(Card o) {
        if (this.value.equals(o.value)) {
            return 0;
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < Rank.values().length; i++) {
            if (Rank.values()[i].toString().equals(this.value)) {
                a = i;
            }
        }
        for (int i = 0; i < Rank.values().length; i++) {
            if (Rank.values()[i].toString().equals(o.value)) {
                b = i;
            }
        }
        return a > b ? 1 : -1;
    }

    enum Suit {Karo, Herz, Pique, Kreuz}

    ;

    enum Rank {sieben, acht, neun, zehn, Bube, Dame, König, Ass}

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
            if (that.toString().equals(this.toString())) {
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
