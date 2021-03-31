package aufgabe6.aufgabe6a;

/**
 *
 * @author sebastianhirsig
 * @since 26.11.2020
 */


public interface Tätigkeit{

    /**
     * Liefert die Zeit der Tätigkeit zurück.
     * @return Anzahl der Zeit der Tätigkeiten.
     */
    double getTime();

    /**
     * Fügt eine Tätigkeit hinzu.
     * @param w Tätigkeit.
     */
    void add(Tätigkeit w);

    /**
     * Löscht eine Tätigkeit.
     * @return w Tätigkeit.
     */
    void remove(Tätigkeit w);

    /**
     * Liefert die Anzal der Tätigkeiten zurück.
     * @return Anzahl der Tätigkeiten.
     */
    int getAnzahl();
}
