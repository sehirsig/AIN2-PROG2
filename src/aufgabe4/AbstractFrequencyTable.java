package aufgabe4;

import java.util.Iterator;

/**
 *
 * @author oliverbittel
 * @since 22.2.2019
 */
public abstract class AbstractFrequencyTable<T> implements FrequencyTable<T> {

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(T w) {
        add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable<? extends T> fq) {
		// Ihr Code:

		//for (Element<? extends T> t: fq) {
		for (var t: fq) {
			this.add(t.getElement(), t.getFrequency());
		}

	}

	@Override
	public void collectMostFrequent(FrequencyTable<? super T> fq) {
		// Ihr Code:
		fq.clear(); //Liste leeren.
		int a = this.get(0).getFrequency(); //Frequency des häufigsten Words nehmen

		/*Iterator<Element<T>> it = this.iterator();
		while (it.hasNext()) {
			Element<T> t = it.next();
			if( a == t.getFrequency()) {
				fq.add(t.getElement(), t.getFrequency());
			}
		}*/
		for(var t: this) {
			if(a == t.getFrequency()) {
				fq.add(t.getElement(), t.getFrequency());
			} else {
				return;
			}
		}
	}

	@Override
	public void collectLeastFrequent(FrequencyTable<? super T> fq) {
		// Ihr Code:
		fq.clear();
		int a = this.get(size() - 1).getFrequency(); //die niedrigste Häufigkeit mit dem letzten Index herausfinden, -1 da Index bei 0 beginnt.

		Iterator<Element<T>> it = this.iterator();
		while (it.hasNext()) {
			Element<T> t = it.next();
			if( a == t.getFrequency()) {
				fq.add(t.getElement(), t.getFrequency());
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

		Iterator<Element<T>> it = this.iterator(); //foreach schleife besser
		while (it.hasNext()) {
			Element<T> t = it.next();
			s.append(t.toString()).append(", ");
		}
		s.append("} size = " + size());
		return s.toString();
	}
}
