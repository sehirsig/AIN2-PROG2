package aufgabe6.aufgabe6a;

public class ElementareTätigkeit implements Tätigkeit {
    private double time;
    private String beschr;

    public ElementareTätigkeit(String beschreibung, double zeit) {
        this.time = zeit;
        this.beschr = beschreibung;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public void add(Tätigkeit w) {
    }

    @Override
    public void remove(Tätigkeit w) {
    }

    @Override
    public int getAnzahl() {
        return 1;
    }

    @Override
    public String toString() {
        return beschr + ":" + time;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ElementareTätigkeit) {
            ElementareTätigkeit that = (ElementareTätigkeit) o;
            if (this.toString() == that.toString())
                return true;
            /* TODO: return true, wenn die Objekte this und that gleich sind */
        }
        return false;
    }
}
