package aufgabe6.aufgabe6a;

import java.util.LinkedList;
import java.util.List;

public abstract class  ZusammengesetzteTätigkeit implements Tätigkeit {

    protected List<Tätigkeit> meineTätigkeiten = new LinkedList<Tätigkeit>();

    @Override
    public void add(Tätigkeit tk) {
        meineTätigkeiten.add(tk);
    }

    @Override
    public void remove(Tätigkeit tk) {
        meineTätigkeiten.remove(tk);
    }

    @Override
    public int getAnzahl() {
        int anzahl = 0;
        for (var d : meineTätigkeiten)
            anzahl += d.getAnzahl();
        return anzahl;
    }

    @Override
    public String toString() {
        if (meineTätigkeiten == null)
            return "";
        StringBuilder s = new StringBuilder("");
        for (var d : meineTätigkeiten)
            s.append(d.toString() + " ");

        return s.toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ZusammengesetzteTätigkeit) {
            ZusammengesetzteTätigkeit that = (ZusammengesetzteTätigkeit) o;
            if (this.toString() == that.toString())
                return true;
            /* TODO: return true, wenn die Objekte this und that gleich sind */
        }
        return false;
    }
}
