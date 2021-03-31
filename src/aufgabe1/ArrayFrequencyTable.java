package aufgabe1;

import java.util.Arrays;

/**
 *
 * @author oliverbittel
 * @since 12.2.2020
 */
public class ArrayFrequencyTable extends AbstractFrequencyTable {
	private int size;
	private Word fqTable[];
	private final int DefaultSize = 100;

	public ArrayFrequencyTable() {
        clear();
    }

    private void moveToLeft(int pos) {
		Word w = fqTable[pos];
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
		fqTable = new Word[DefaultSize]; //Eine neue Liste erstellen für fqTable
	}

	@Override
	public void add(String w, int f) {
		if (fqTable.length == size) //Testen ob noch genug Platz in der Liste ist, ansonsten Liste vergrößern.
		{
			fqTable = Arrays.copyOf(fqTable, 2*size);
		}
		int count = 0;
		int flag = 0;
		if (size != 0) { //Damit nicht unnötig Fehler entstehen.
		for (Word s:fqTable) { //Es wird geprüft, ob das Wort schon in der Liste steht, wenn ja wird nur die frequency erhöht.
			if (s == null){ //Wenn fqTable leer ist, Schleife verlassen um Fehler zu vermeiden.
				break;
			}
			if(s.getWord().equals(w)){ //Hier findet die Prüfungs statt.
				s.addFrequency(f);
				moveToLeft(count);
				flag = 1; //flag damit ein neues Word produziert wird, wenn das Word noch nicht vorhanden war.
				break;
			}
			count++;
		}
		}

		if (flag == 0) { //Hier neues Word, falls noch nicht vorhanden.
			fqTable[size] = new Word(w, f);
			moveToLeft(size++);
		}
	}

	@Override
	public void add(String w) {
		add(w,1); //einfach mit frequency von 1 und dann add aufrufen.
	}

	@Override
	public Word get(int pos) {
		if(pos >= size) { //Wenn es diesen Index nicht gibt, null zurückgeben
			return null;
		}
		return fqTable[pos]; //das komplette Wort an diesem Index zurückgeben.
	}

	@Override
	public int get(String w) {
		int found = 0; //flag
		for (int i = 0; i < size; i++) { //für jeden Indexwert, auch for each schleife möglich
			if (fqTable[i].getWord().equals(w)) //wenn es den String schon gibt, dann frequency ausgeben, ansonsten frequency von 0.
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
