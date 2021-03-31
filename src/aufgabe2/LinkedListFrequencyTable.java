package aufgabe2;

public class LinkedListFrequencyTable extends AbstractFrequencyTable {
    private int size;
    private Node begin;
    private Node end;


    public LinkedListFrequencyTable() {
        clear();
    }

    private class Node {
        Node next;
        Node prev;
        Word data;
        Node(Word x, Node n, Node p) {
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
        begin = new Node(new Word("",0),null,null);
        end = new Node(new Word("",0), null, begin);
        begin.next = end;
        size = 0; //Größe der Liste auf 0 zurücksetzen
    }

    public void add(int idx, Word s){
        if(idx < 0 || idx > size){
            throw new IndexOutOfBoundsException();
        }

        Node p = begin;
        for (int i = 0; i < idx; i++) {
            p = p.next;
        }
        Node r = new Node(s, p.next, p); // 1)
        r.next.prev = r; // 2)
        p.next = r;

        size++;
    }


    @Override
    public void add(String w, int f) {
        Node p = begin;
        while (p.next != null){
            if(p != null && p.data.getWord().equals(w)){
                p.data.addFrequency(f);
                // Hier Einfügen nach Links verschieben
                Node s = begin;
                int c = p.data.getFrequency();

                Node d = p.next;
                p = p.prev;
                p.next = p.next.next;
                d.prev = p;
                size--;
                int idx = 0;
                for (int i = 0; i < this.size; i++) {
                    if(c > s.next.data.getFrequency()){
                        add(i, new Word(w,c));
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
            add(0, new Word(w, f));
            return;
        }
        int idx = 0;
        for (int i = 0; i < this.size; i++) {
            if(f > p.next.data.getFrequency()){
                add(i, new Word(w,f));
                return;
            }
            p = p.next;
            idx++;
        }

        add(idx, new Word(w,f));
    }

    @Override
    public void add(String w) {
        add(w,1);
    }


    @Override
    public Word get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        Node p = begin.next;
        for (int i = 0; i < pos; i++) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public int get(String w) {
        Node p = begin;
        while (p.next != null){
            if(p != null && p.data.getWord().equals(w)){
                return p.data.getFrequency();
            }
            p = p.next;
        }
        return 0;
    }
}
