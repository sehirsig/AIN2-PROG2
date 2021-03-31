package aufgabe4;

import java.util.Random;

public class RedCard extends Card {


    public RedCard() {
        Random zahl = new Random();
        colour = Suit.values()[zahl.nextInt(2)].toString();
        value = Rank.values()[zahl.nextInt(8)].toString();
    }

    public RedCard(Suit col, Rank val) {
        if (!(col == Suit.Herz || col == Suit.Karo))
            throw new IllegalArgumentException("Das ist kein rotes Symbol!");
        colour = col.toString();
        value = val.toString();
    }
}
