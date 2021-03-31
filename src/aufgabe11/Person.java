package aufgabe11;
import java.time.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Person {
    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;

    public Person(String v, String n, LocalDate g) {
        vorname = v;
        nachname = n;
        geburtsdatum = g;
    }

    public String getVorname() {
      return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public Person getPerson() {
        return this;
    }

    public void setVorname(String s) {
         vorname = s;
    }

    public void setNachname(String s) {
        nachname = s;
    }

    public void setGeburtsdatum(LocalDate l) {
        geburtsdatum = l;
    }

    public String toString() {
        return vorname + " " + nachname + " " + geburtsdatum.toString();
    }

    public static void main(String[] args) {
        List<Person> persList = new LinkedList<Person>();
        listeAnlegen(persList);

        //Aufgabe a)
        System.out.println("a)\n");
        Predicate<Person> Is18 = (x) -> (Period.between(x.getGeburtsdatum(),LocalDate.now()).getYears() >= (long)18);
        for (var liste : persList) {
            if(Is18.test(liste.getPerson())) {
                System.out.println(liste.getVorname());
            }
        }
        System.out.println("\n");
        System.out.println("b)\n");

        //Aufgabe b)
        persList.sort(Comparator.comparing(Person::getGeburtsdatum));
        for (var liste : persList) {
            System.out.println(liste);
        }
        System.out.println("\n");

        System.out.println("c)\n");

        //Aufgabe c)
        persList.sort(Comparator.comparing(Person::getGeburtsdatum).reversed());
        for (var liste : persList) {
            System.out.println(liste);
        }
        System.out.println("\n");

        System.out.println("d)\n");

        //Aufgabe d)
        persList.stream()
                .sorted(Comparator.comparing(Person::getGeburtsdatum))
                .filter(n -> Is18.test(n))
                .forEach(n -> System.out.println(n.getGeburtsdatum()));
        System.out.println("\n");

        System.out.println("e)\n");

        //Aufgabe e)
        persList.stream()
                .filter(n -> n.vorname.startsWith("A"))
                .sorted(Comparator.comparing(Person::getGeburtsdatum))
                .limit(3)
                .forEach(System.out::println);
    }


    public static void listeAnlegen(List<Person> persList) {
        persList.add(new Person("Kevin", "Müller", LocalDate.of(2003,12,03)));
        persList.add(new Person("Sabine", "Maier", LocalDate.of(1998,10,07)));
        persList.add(new Person("Bernhard", "Knall", LocalDate.of(2000,01,23)));
        persList.add(new Person("Ferdinand", "Pfohlen", LocalDate.of(2005,06,15)));
        persList.add(new Person("Nadine", "Rein", LocalDate.of(2002,05,18)));
        persList.add(new Person("Aevin", "Müller", LocalDate.of(2003,12,03)));
        persList.add(new Person("Aabine", "Maier", LocalDate.of(1998,10,07)));
        persList.add(new Person("Aernhard", "Knall", LocalDate.of(2000,01,23)));
        persList.add(new Person("Aerdinand", "Pfohlen", LocalDate.of(2005,06,15)));
        persList.add(new Person("Aadine", "Rein", LocalDate.of(2002,05,18)));

    }
}
