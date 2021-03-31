package aufgabe7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class aufgabe7 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //testprogram();

        //test();

        //kafka();

        //cardtest();

        //laufzeit();
    }

    public static void testprogram() {
        Integer a[] = {1, 2, 3, 4, 5, 6 ,7 ,8 ,9 ,10};
        hybridquicksort3median.quickSort(a);
    }

    public static void laufzeit() {
        System.out.println("100000 Zufällige Spielkarten:\n");
        Card[] k1 = cardherstellung(100000);
        Card[] k2 = Arrays.copyOf(k1, k1.length);
        Card[] k3 = Arrays.copyOf(k1, k1.length);
        ;


        System.out.println("Hybrides Quicksort:");
        long start = System.nanoTime();
        hybridquicksort.quickSort(k1);
        long end = System.nanoTime();
        double elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Hybrides Quicksort it 3-Median:");
        start = System.nanoTime();
        hybridquicksort3median.quickSort(k2);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Arrays.sort");
        start = System.nanoTime();
        Arrays.sort(k3);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime + "\n");

        System.out.println("200000 Zufällige Spielkarten:\n");
        Card[] b1 = cardherstellung(200000);
        Card[] b2 = Arrays.copyOf(b1, b1.length);
        Card[] b3 = Arrays.copyOf(b1, b1.length);

        System.out.println("Hybrides Quicksort:");
        start = System.nanoTime();
        hybridquicksort.quickSort(b1);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Hybrides Quicksort it 3-Median:");
        start = System.nanoTime();
        hybridquicksort3median.quickSort(b2);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Arrays.sort");
        start = System.nanoTime();
        Arrays.sort(b3);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime + "\n");

        System.out.println("100000 sortierte Spielkarten:\n");
        Card[] temp1 = cardherstellung(100000);
        hybridquicksort3median.quickSort(temp1);
        Card[] e1 = Arrays.copyOf(temp1, temp1.length);
        Card[] e2 = Arrays.copyOf(temp1, temp1.length);
        Card[] e3 = Arrays.copyOf(temp1, temp1.length);

        System.out.println("Hybrides Quicksort:");
        start = System.nanoTime();
        hybridquicksort.quickSort(e1);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Hybrides Quicksort it 3-Median:");
        start = System.nanoTime();
        hybridquicksort3median.quickSort(e2);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Arrays.sort");
        start = System.nanoTime();
        Arrays.sort(e3);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime + "\n");


        System.out.println("200000 sortierte Spielkarten:\n");
        Card[] temp2 = cardherstellung(200000);
        hybridquicksort3median.quickSort(temp2);
        Card[] u1 = Arrays.copyOf(temp2, temp2.length);
        Card[] u2 = Arrays.copyOf(temp2, temp2.length);
        Card[] u3 = Arrays.copyOf(temp2, temp2.length);

        System.out.println("Hybrides Quicksort:");
        start = System.nanoTime();
        hybridquicksort.quickSort(u1);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Hybrides Quicksort it 3-Median:");
        start = System.nanoTime();
        hybridquicksort3median.quickSort(u2);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);

        System.out.println("Arrays.sort");
        start = System.nanoTime();
        Arrays.sort(u3);
        end = System.nanoTime();
        elapsedTime = (double) (end - start) / 1.0e06;
        System.out.println("Benötigte Zeit in msec: " + elapsedTime);


    }

    public static Card[] cardherstellung(int anzahlspielkarten) {
        List<Card> cards = new ArrayList<Card>();
        Random rand = new Random();
        for (int i = 0; i < anzahlspielkarten; i++) {
            if (rand.nextInt() % 2 == 0) {
                RedCard c = new RedCard();
                cards.add(c);
            } else {
                BlackCard c = new BlackCard();
                cards.add(c);
            }
        }

        Card[] cardarray = new Card[cards.size()];
        int g = 0;
        for (var w : cards) {
            cardarray[g++] = w;
        }

        return cardarray;
    }

    public static void cardtest() {
        List<Card> cardtab = new ArrayList<Card>();
        List<RedCard> redcardtab = new ArrayList<RedCard>();
        List<BlackCard> blackcardtab = new ArrayList<BlackCard>();

        Random rand = new Random();
        for (int i = 0; i < 400; i++) {
            if (rand.nextInt() % 2 == 0) {
                RedCard c = new RedCard();
                cardtab.add(c);
                redcardtab.add(c);
            } else {
                BlackCard c = new BlackCard();
                cardtab.add(c);
                blackcardtab.add(c);
            }
        }

        Card[] cardarray = new Card[cardtab.size()];
        int g = 0;
        for (var w : cardtab) {
            cardarray[g++] = w;
        }

        Card[] redcardarray = new Card[redcardtab.size()];
        g = 0;
        for (var w : redcardtab) {
            redcardarray[g++] = w;
        }

        Card[] blackcardarray = new Card[blackcardtab.size()];
        g = 0;
        for (var w : blackcardtab) {
            blackcardarray[g++] = w;
        }

        hybridquicksort3median.quickSort(cardarray);
        hybridquicksort3median.quickSort(redcardarray);
        hybridquicksort3median.quickSort(blackcardarray);

        for (var i : cardarray) {
            System.out.println(i);
        }

    }

    public static void kafka() throws FileNotFoundException, IOException {
        List<String> list = new ArrayList<String>();
        LineNumberReader in;
        in = new LineNumberReader(new FileReader("Kafka_Der_Prozess.txt"));
        String line;

        while ((line = in.readLine()) != null) {
            String[] wf = line.split("[^a-z^A-Z^ß^ä^ö^ü^Ä^Ö^Ü]+");
            for (String w : wf) {
                if (w.length() == 0 || w.length() == 1)
                    continue;
                list.add(w);
            }
        }
        String[] stringarray = new String[list.size()];
        int g = 0;
        for (String w : list) {
            stringarray[g++] = w;
        }

        hybridquicksort.quickSort(stringarray);
        for (var i : stringarray) {
            System.out.println(i);
        }

        System.out.println("\nAnzahl Wörter: " + stringarray.length);
    }

    public static void test() {
        int M = 5000;
        Integer e[] = new Integer[5000];
        for (int i = 0; i < e.length; i++) {
            e[i] = (int) (Math.random() * M); // Zufälliges Integer Feld
        }

        //Integer k[] = {2, 5, 7, 3, 1, 8, 6, 4};
        //Integer j[] = {2, 5, 7, 3, 1, 8, 6, 4};
        Integer k[] = e;
        Integer j[] = e;
        long start1 = System.nanoTime();
        hybridquicksort.quickSort(k);
        long end1 = System.nanoTime();
        double elapsedTime1 = (double) (end1 - start1) / 1.0e06;
        System.out.println("Hybrid Normal - Benötigte Zeit in msec: " + elapsedTime1);
        //for (var i : k) {
        //    System.out.println(i);
        //}
        System.out.println();

        long start2 = System.nanoTime();
        hybridquicksort3median.quickSort(j);
        long end2 = System.nanoTime();
        double elapsedTime2 = (double) (end2 - start2) / 1.0e06;
        System.out.println("Hybrid 3-Median - Benötigte Zeit in msec: " + elapsedTime2);
        //for (var i : j) {
        //    System.out.println(i);
        //}
    }
}
