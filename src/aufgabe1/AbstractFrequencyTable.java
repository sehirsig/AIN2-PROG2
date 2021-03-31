package aufgabe1;

/**
 *
 * @author oliverbittel
 * @since 22.2.2019
 */
public abstract class AbstractFrequencyTable implements FrequencyTable {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(String w) {
        add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable fq) {
		// Ihr Code:
		int s = fq.size();// Jedes Wort des Tables fq Stück für Stück mit dem eingebauten add der Liste hinzufügen.
		for (int i = 0; i < s; i++) {
			add(fq.get(i).getWord(), fq.get(i).getFrequency());
		}
	}

	@Override
	public void collectMostFrequent(FrequencyTable fq) {
		// Ihr Code:
		fq.clear(); //Liste leeren.
		int a = this.get(0).getFrequency(); //Frequency des häufigsten Words nehmen
		int i = 0;
		while (a == this.get(i).getFrequency()) { //Alle Words mit der selben Häufigkeit in die neue Liste hinzufügen.
			fq.add(this.get(i).getWord(), this.get(i).getFrequency());
			i++;
		}
	}

	@Override
	public void collectLeastFrequent(FrequencyTable fq) {
		// Ihr Code:
		fq.clear();
		int a = this.get(size() - 1).getFrequency(); //die niedrigste Häufigkeit mit dem letzten Index herausfinden, -1 da Index bei 0 beginnt.
		int i = size() - 1;
		for (int j = 0; j < size(); j++) { //Alles Words mit der selben niedrigsten Häufigkeit der neuen Liste hinzufügen
			if ( a == this.get(j).getFrequency()) {
				fq.add(this.get(j).getWord(), this.get(j).getFrequency());
			}
		}
	}

	/**
	 * Liefert eine String-Darstellung zur&uuml;ck.
	 * @return String-Darstellung.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");
		// Ihr Code:
		for (int i = 0; i < size(); i++) { //Den String hier bauen mit jedem Wort von Höchster Häufigkeit bis runter zur niedrigsten.
			s.append(get(i).toString()).append(", ");
		}
		s.append("} size = " + size());
		return s.toString();
	}
}
