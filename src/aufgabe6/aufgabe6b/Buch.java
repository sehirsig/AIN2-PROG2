package aufgabe6.aufgabe6b;

public class Buch {
    private String name;
    private Person entleiher;

    public Buch (String n) {
        name = n;
        entleiher = null;
    }
    public String getName() {
        return name;
    }

    public Person getEntleiher() {
        return entleiher;
    }

    public boolean wirdAusgeliehen(Person p) {
        if (entleiher != null) {
            return false;
        }
        entleiher = p;
        p.leihtAus(this);
        return true;
    }

    public boolean wirdZurueckGegeben() {
        if(entleiher == null) {
            return false;
        }
        Person d = entleiher;
        entleiher = null;
        d.gibtZurueck(this);
        return true;
    }

    public void print() {
        if (getEntleiher() == null) {
            System.out.println("Ausgeliehen: --- :: Buch: " + name);
        } else {
            System.out.println("Ausgeliehen: " + this.getEntleiher() + " :: Buch: " + name);
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
