package aufgabe6.aufgabe6b;

import aufgabe6.aufgabe6a.Tätigkeit;

import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private List<Buch> ausgelieheneBeucher = new LinkedList<Buch>();

    public Person (String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public boolean leihtAus(Buch b) {
        if (ausgelieheneBeucher.contains(b)) {
            return false;
        }
        if(b.getEntleiher() != null && b.getEntleiher() != this) {
            return false;
        }
        ausgelieheneBeucher.add(b);
        b.wirdAusgeliehen(this);
        return true;
    }

    public boolean gibtZurueck(Buch b) {
        if(!ausgelieheneBeucher.contains(b)) {
            return false;
        }
        ausgelieheneBeucher.remove(b);
        b.wirdZurueckGegeben();
        return true;
    }

    public void print() {
        StringBuilder s = new StringBuilder("|");
        for (var p : ausgelieheneBeucher) {
            s.append(p.toString() + "|");
        }
        System.out.println("Ausgeliehen: " + name + " :: Ausgeliehen: " + s);
    }

    @Override
    public String toString() {
        return name;
    }
}
