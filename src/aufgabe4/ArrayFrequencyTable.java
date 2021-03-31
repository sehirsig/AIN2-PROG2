package aufgabe4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author oliverbittel
 * @since 12.2.2020
 */
public class ArrayFrequencyTable<T> extends AbstractFrequencyTable<T> {

	public Iterator<Element<T>> iterator() {
		return new ArrayFrequencyTableIterator();
	}

	private class ArrayFrequencyTableIterator implements Iterator<Element<T>> {
		private int cursor;

		public ArrayFrequencyTableIterator() {
			this.cursor = 0;
		}

		public boolean hasNext() {
			return this.cursor < ArrayFrequencyTable.this.size;
		}

		public Element<T> next() {
			if(this.hasNext()) {
				int current = cursor;
				cursor ++;
				return get(current);
			}
			throw new NoSuchElementException();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


	private int size;
	private Element fqTable[];
	private final int DefaultSize = 100;

	public ArrayFrequencyTable() {
        clear();
    }


    private void moveToLeft(int pos) {
		Element w = fqTable[pos];
		if (size > 1 && pos > 0) {	// Damit kein Indexfehler für i und kein Fehler für die Bedingung in der while Schleife entsteht
			int i = pos - 1;
			while (fqTable[i].getFrequency() < w.getFrequency()) { //While das mit pos ausgesucht word eine höhere frequency hat als das word mit index - 1.

				fqTable[i + 1] = fqTable[i];
				if(i != 0 ) { // Damit es keinen Fehler gibt wenn der index bei 0 ist, da es nicht ins negative gehen kann.
					i--;
				} else {
					i--; // aus der schleife austreten, wenn es der niedrigste Index war.
					break;
				}
			}
			fqTable[i + 1] = w;
		}
	}

	@Override
	public int size() {
		return size; //Die size variable wiedergeben.
	}

	@Override
	public final void clear() {
		size = 0; //Größe der Liste auf 0 zurücksetzen
		fqTable = new Element[DefaultSize]; //Eine neue Liste erstellen für fqTable
	}

	@Override
	public void add(T w, int f) {
		if (fqTable.length == size) //Testen ob noch genug Platz in der Liste ist, ansonsten Liste vergrößern.
		{
			fqTable = Arrays.copyOf(fqTable, 2*size);
		}
		int count = 0;
		int flag = 0;
		if (!isEmpty()) { //Damit nicht unnötig Fehler entstehen.
		for (Element<T> s:fqTable) { //Es wird geprüft, ob das Wort schon in der Liste steht, wenn ja wird nur die frequency erhöht.
			if (s == null){ //Wenn fqTable leer ist, Schleife verlassen um Fehler zu vermeiden.
				break;
			}
			if(s.getElement().equals(w)){ //Hier findet die Prüfungs statt.
				s.addFrequency(f);
				moveToLeft(count);
				flag = 1; //flag damit ein neues Word produziert wird, wenn das Word noch nicht vorhanden war.
				break;
			}
			count++;
		}
		}

		if (flag == 0) { //Hier neues Word, falls noch nicht vorhanden.
			fqTable[size] = new Element(w, f);
			moveToLeft(size++);
		}
	}

	@Override
	public void add(T w) {
		add(w,1); //einfach mit frequency von 1 und dann add aufrufen.
	}

	@Override
	public Element<T> get(int pos) {
		if(pos >= size) { //Wenn es diesen Index nicht gibt, null zurückgeben
			return null;
		}
		return fqTable[pos]; //das komplette Wort an diesem Index zurückgeben.
	}

	@Override
	public int get(T w) {
		int found = 0; //flag
		for (int i = 0; i < size; i++) { //für jeden Indexwert, auch for each schleife möglich
			if (fqTable[i].getElement().equals(w)) //wenn es den String schon gibt, dann frequency ausgeben, ansonsten frequency von 0.
			{
				found = i;
				break;
			}
			if (i + 1 == size)
			{
				return 0;
			}
		}
		return fqTable[found].getFrequency();
	}
}
