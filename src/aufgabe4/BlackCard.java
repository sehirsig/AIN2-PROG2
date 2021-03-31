package aufgabe4;

import java.util.Random;

public class BlackCard extends Card {

    public BlackCard() {
        Random zahl = new Random();
        colour = Suit.values()[zahl.nextInt(2) + 2].toString();
        value = Rank.values()[zahl.nextInt(8)].toString();
    }

    public BlackCard(Suit col, Rank val) {
        if (!(col == Suit.Karo || col == Suit.Pique))
            throw new IllegalArgumentException("Das ist kein schwarzes Symbol!");
        colour = col.toString();
        value = val.toString();
    }
}
