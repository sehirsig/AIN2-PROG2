package aufgabe4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListFrequencyTable<T> extends AbstractFrequencyTable<T> {

    private int current = 0;

    public Iterator<Element<T>> iterator() {
        return new LinkedListFrequencyTable.LinkedListFrequencyTableIterator();
    }

    private class LinkedListFrequencyTableIterator implements Iterator<Element<T>> {
        private int cursor;

        public LinkedListFrequencyTableIterator() {
            this.cursor = LinkedListFrequencyTable.this.current;
        }

        public boolean hasNext() {
            return this.cursor < LinkedListFrequencyTable.this.size;
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
    private Node<T> begin;
    private Node<T> end;


    public LinkedListFrequencyTable() {
        clear();
    }

    private static class Node<T> {
        Node<T> next;
        Node<T> prev;
        Element<T> data;
        Node(Element<T> x, Node<T> n, Node<T> p) {
            data = x;
            next = n;
            prev = p;
        }
    }


    @Override
    public int size() {
        return size; //Die size variable wiedergeben.
    }

    @Override
    public final void clear() {
        begin = new Node<T>(new Element<T>((T)"",0),null,null);
        end = new Node<T>(new Element<T>((T)"",0), null, begin);
        begin.next = end;
        size = 0; //Größe der Liste auf 0 zurücksetzen
    }

    private void add(int idx, Element<T> s){
        if(idx < 0 || idx > size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> p = begin;
        for (int i = 0; i < idx; i++) {
            p = p.next;
        }
        Node<T> r = new Node<T>(s, p.next, p); // 1)
        r.next.prev = r; // 2)
        p.next = r;

        size++;
    }


    @Override
    public void add(T w, int f) {
        Node<T> p = begin;
        while (p.next != null){
            if(p != null && p.data.getElement().equals(w)){
                p.data.addFrequency(f);
                Node s = begin;
                int c = p.data.getFrequency();

                Node<T> d = p.next; //Node Löschen und danach neu hinzufügen mit Reihenfolge.
                p = p.prev;
                p.next = p.next.next;
                d.prev = p;
                size--; //Size kleiner da node gelöscht.
                int idx = 0;
                for (int i = 0; i < this.size; i++) {
                    if(c > s.next.data.getFrequency()){
                        add(i, new Element<T>(w,c));
                        return;
                    }
                    s = s.next;
                    idx++;
                }
                return;
            }
            p = p.next;
        }

        p = begin;
        if (p.next.data.getFrequency() == 0) {
            add(0, new Element<T>(w, f));
            return;
        }
        int idx = 0;
        for (int i = 0; i < this.size; i++) {
            if(f > p.next.data.getFrequency()){
                add(i, new Element<T>(w,f));
                return;
            }
            p = p.next;
            idx++;
        }

        add(idx, new Element<T>(w,f));
    }

    @Override
    public void add(T w) {
        add(w,1);
    }


    @Override
    public Element<T> get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        Node<T> p = begin.next;
        for (int i = 0; i < pos; i++) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public int get(T w) {
        Node<T> p = begin;
        while (p.next != null){
            if(p != null && p.data.getElement().equals(w)){
                return p.data.getFrequency();
            }
            p = p.next;
        }
        return 0;
    }
}
