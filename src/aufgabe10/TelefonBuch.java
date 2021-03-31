// O. Bittel
// 10.03.2017

package aufgabe10;

import javax.swing.*;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TelefonBuch {

    private TreeMap<String,String> telBuch = new TreeMap<String,String>();

    public boolean insert(String name, String zusatz, String telNr) {
        String neu = name + " " + zusatz;
        if (!telBuch.containsKey(neu)) {
            telBuch.put(neu, telNr);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(String name, String zusatz) {
        String neu = name + " " + zusatz;
        if (telBuch.containsKey(neu)) {
            telBuch.remove(neu);
            return true;
        } else {
            return false;
        }
    }

    public String exactSearch(String name, String zusatz) {
        String neu = name + zusatz;

        /*for (var k : telBuch.entrySet()) {
            String search = k.getKey() +  k.getValue();
            if (search.equals(neu)) {
                return k.getKey() + " " + k.getValue();
            }
        }*/
        return telBuch.get(neu);
    }

    public List<String> prefixSearch(String s) {
        List<String> newList = new LinkedList<>();
/*
        for (var k : telBuch.entrySet()) {
            String r = k.getKey();
            if (r.startsWith(s)){
                String fin = r + " " + k.getValue(); //r + " " + k.getValue();
                newList.add(fin);
            }
        }*/

        String value = s;
        if (s.length() != 0) {
            char[] temp = value.toCharArray();

            char lastLetter = temp[temp.length - 1];
            lastLetter++;
            temp[temp.length - 1] = lastLetter;

            String end = new String(temp);
            for (Entry<String, String> eintrag
                    : telBuch.subMap(value, true, end, false).entrySet()) {
                newList.add(eintrag.getKey() + " " + eintrag.getValue());
            }
        } else {
            for (Entry<String, String> eintrag
                    : telBuch.subMap("AAA", true, "ZZZ", false).entrySet()) {
                newList.add(eintrag.getKey() + " " + eintrag.getValue());
            }
        }

        return newList; // damit IDE kein Systaxfehler anzeigt
    }

    public void read(File f) {
        LineNumberReader in = null;
        try {
            telBuch.clear();
            in = new LineNumberReader(new FileReader(f));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                if (sf.length == 2) {
                    insert(sf[0], "", sf[1]); // leerer Zusatz
                } else if (sf.length == 3) {
                    insert(sf[0], sf[1], sf[2]);
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(TelefonBuch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save(File f) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(f);
            for (Entry<String, String> eintrag : telBuch.entrySet()) {
                String s = eintrag.getKey().replace('#', ' ') + " " + eintrag.getValue();
                out.println(s);
            }
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(TelefonBuch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void print(List<String> strList) {
        for (String s : strList)
            System.out.println(s);
    }

    public static void main(String[] args)
            throws FileNotFoundException, IOException {

        TelefonBuch telBuch = new TelefonBuch();
        telBuch.read(new File("TelBuchMit420Namen.txt"));

        System.out.println(telBuch.exactSearch("Oliver",""));
        System.out.println();

        print(telBuch.prefixSearch("H"));
        System.out.println();

        print(telBuch.prefixSearch(""));
        System.out.println();

        telBuch.insert("Oliver","1","33245");
        telBuch.insert("Oliver","2","23423");
        telBuch.insert("Oliver","3","87655");
        telBuch.remove("Oliver","2");

        print(telBuch.prefixSearch("Ol"));
        System.out.println();

        telBuch.save(new File("test.txt"));
    }
}