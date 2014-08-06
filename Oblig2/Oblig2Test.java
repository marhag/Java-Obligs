package Oblig2;

import java.util.*;

public class Oblig2Test
{
  public static void main(String[] args)
  {
    int antallFeil = 0;

    antallFeil += oppgave2();
    antallFeil += oppgave3();
    antallFeil += oppgave4();
    antallFeil += oppgave5();
    antallFeil += oppgave6();
    antallFeil += oppgave7();
    antallFeil += oppgave8();
    antallFeil += oppgave9();
    antallFeil += oppgave10();
    antallFeil += oppgave11();

    if (antallFeil == 0)
    {
      System.out.println("Gratulerer!! Du passerte testen!");
    }
    else
    {
      System.out.println
        ("Dette m� forbedres. Du har minst " + antallFeil + " feil!");
    }
  }

  public static int oppgave2()
  {
    int antallFeil = 0;
    Liste<Integer> liste = new DobbeltLenketListe<>();

    if (liste.antall() != 0)
    {
      antallFeil++;
      System.out.println("Oppgave 2a: Feil i metoden antall()!");
    }

    if (liste.tom() != true)
    {
      antallFeil++;
      System.out.println("Oppgave 2b: Feil i metoden tom()!");
    }

    if (liste.leggInn(1) != true)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 2c: leggInn-metoden skal returnere true!");
    }

    liste.leggInn(2);

    if (liste.antall() != 2)
    {
      antallFeil++;
      System.out.println("Oppgave 2d: Feil i metoden antall()!");
    }

    if (liste.tom() == true)
    {
      antallFeil++;
      System.out.println("Oppgave 2e: Feil i metoden tom()!");
    }

    try
    {
      liste.leggInn(null);
      antallFeil++;
      System.out.println
        ("Oppgave 2f: Ikke tillatt � legge inn null-verdier!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println
          ("Oppgave 2g: Kaster feil type unntak for null-verdier!");
        antallFeil++;
      }
    }

    return antallFeil;
  }

  public static int oppgave3()
  {
    int antallFeil = 0;
    DobbeltLenketListe<Integer> liste =
                                new DobbeltLenketListe<>();

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 3a: Utskriften skal v�re lik []!");
    }

    if (!liste.omvendtString().equals("[]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3b: Omvendt-utskriften skal v�re lik []!");
    }

    liste.leggInn(1);

    if (!liste.toString().equals("[1]"))
    {
      antallFeil++;
      System.out.println("Oppgave 3c: Utskriften skal v�re lik [1]!");
    }

    if (!liste.omvendtString().equals("[1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3d: Omvendt-utskriften skal v�re lik [1]!");
    }

    liste.leggInn(2);

    if (!liste.toString().equals("[1, 2]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3e: Utskriften skal v�re lik [1, 2]!");
    }

    if (!liste.omvendtString().equals("[2, 1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3f: Omvendt-utskriften skal v�re lik [2, 1]!");
    }

    liste.leggInn(3);
    liste.leggInn(4);

    if (!liste.toString().equals("[1, 2, 3, 4]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3g: Utskriften skal v�re lik [1, 2, 3, 4]!");
    }

    if (!liste.omvendtString().equals("[4, 3, 2, 1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 3h: Omvendt-utskriften skal v�re lik [4, 3, 2, 1]!");
    }

    return antallFeil;
  }

  public static int oppgave4()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    try
    {
      liste.hent(0);
      antallFeil++;
      System.out.println("Oppgave 4a: En tom liste har ikke indeks lik 0!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 4b: Metoden hent() kaster feil type unntak!");
        antallFeil++;
      }
    }

    liste.leggInn(1);

    try
    {
      liste.hent(-1);
      antallFeil++;
      System.out.println("Oppgave 4c: En liste har ikke indeks lik -!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 4d: Metoden hent() kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.hent(1);
      antallFeil++;
      System.out.println("Oppgave 4e: En liste med en verdie har ikke indeks lik 1");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 4f: Metoden hent() kaster feil type unntak!");
        antallFeil++;
      }
    }

    if (liste.hent(0) != 1)
    {
      antallFeil++;
      System.out.println("Oppgave 4g: Metoden hent() gir feil svar!");
    }

    liste.leggInn(2);
    liste.leggInn(3);
    liste.leggInn(4);

    if (liste.hent(3) != 4 || liste.hent(2) != 3
        || liste.hent(1) != 2 || liste.hent(0) != 1)
    {
      antallFeil++;
      System.out.println("Oppgave 4h: Metoden hent() gir feil svar!");
    }

    try
    {
      liste.oppdater(3, null);
      antallFeil++;
      System.out.println("Oppgave 4i: Ikke tillatt med nullverdier i oppdater!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println(
          "Oppgave 4j: Feil unntak for nullverdier i oppdater()!");
        antallFeil++;
      }
    }

    try
    {
      liste.oppdater(4, 5);
      antallFeil++;
      System.out.println
        ("Oppgave 4k: En liste med fire verdier har ikke indeks lik 4");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 4l: Metoden kaster feil type unntak for indeksfeil!");
        antallFeil++;
      }
    }

    if (liste.oppdater(3, 5) != 4)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4m: Metoden oppdater() returnerer feil verdi!");
    }

    if (liste.antall() != 4)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4n: Antall skal ikke endres i oppdater()!");
    }

    if (liste.hent(3) != 5)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4o: Metoden oppdater() setter feil verdi!");
    }

    if (liste.oppdater(0, -1) != 1)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4p: Metoden oppdater() returnerer feil verdi!");
    }

    if (liste.hent(0) != -1)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4q: Metoden oppdater() setter feil verdi!");
    }

    if (!liste.toString().equals("[-1, 2, 3, 5]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4r: Utskriften skal v�re lik [-1, 2, 3, 5]!");
    }

    if (!liste.omvendtString().equals("[5, 3, 2, -1]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 4s: Omvendt-utskriften skal v�re lik [5, 3, 2, -1]!");
    }

    return antallFeil;
  }

  public static int oppgave5()
  {
    int antallFeil = 0;

    class A
    {
      char tegn;

      public A(char tegn) { this.tegn = tegn; }

      public boolean equals(Object o)
      {
        if (o == this) return true;
        if (!(o instanceof A)) return false;  // feil datatype

        return tegn == ((A)o).tegn;
      }
    }

    Liste<A> sliste = new DobbeltLenketListe<>();

    A a = new A('X');
    A b = new A('X');

    sliste.leggInn(a);

    if (sliste.indeksTil(b) != 0)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 5a: M� bruke equals og ikke == i sammenligningen i indeksTil()!");
    }

    Liste<Integer> liste = new DobbeltLenketListe<>();

    if (liste.indeksTil(2) != -1)
    {
      antallFeil++;
      System.out.println("Oppgave 5b: En tom liste inneholder ikke tallet 2!");
    }

    liste.leggInn(1);

    if (liste.indeksTil(1) != 0)
    {
      antallFeil++;
      System.out.println("Oppgave 5c: Tallet 1 har indeks 0!");
    }

    liste.leggInn(3);
    liste.leggInn(5);
    liste.leggInn(7);

    if (liste.indeksTil(10) != -1 || liste.indeksTil(4) != -1)
    {
      antallFeil++;
      System.out.println("Oppgave 5d: Listen inneholder hverken 4 eller 10!");
    }

    if (liste.indeksTil(1) != 0 || liste.indeksTil(3) != 1
        || liste.indeksTil(5) != 2 || liste.indeksTil(7) != 3)
    {
      antallFeil++;
      System.out.println("Oppgave 5e: Feil i metoden indeksTil()!");
    }

    try
    {
      if (liste.indeksTil(null) != -1)
      {
        antallFeil++;
        System.out.println
          ("Oppgave 5f: Skal returnere -1 for null-verdier!");
      }
    }
    catch (Exception e)
    {
      System.out.println(
      "Oppgave 5g: Skal ikke kaste unntak, men returnere -1 for null-verdier!");
        antallFeil++;
    }

    if (liste.inneholder(1) != true || liste.inneholder(7) != true
      || liste.inneholder(0) != false || liste.inneholder(6) != false)
    {
      antallFeil++;
      System.out.println("Oppgave 5h: Feil i metoden inneholder()!");
    }

    return antallFeil;
  }

  public static int oppgave6()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    try
    {
      liste.leggInn(-1, 1);
      antallFeil++;
      System.out.println(
        "Oppgave 6a: Kan ikke legge inn p� indeks -1!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 6b: Metoden leggInn() kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.leggInn(1, 1);
      antallFeil++;
      System.out.println("Oppgave 6c: Kan ikke bruke indeks 1 i en tom liste!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 6d: Metoden leggInn() kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.leggInn(0, null);
      antallFeil++;
      System.out.println("Oppgave 6e: Ikke tillatt � legge inn null-verdier!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NullPointerException))
      {
        System.out.println
          ("Oppgave 6f: Det kastes feil type unntak fra leggInn()!");
        antallFeil++;
      }
    }

    try
    {
      liste.leggInn(0, 4);
    }
    catch (Exception e)
    {
      System.out.println("Oppgave 6g: Indeks 0 er tillatt i en tom liste!");
      antallFeil++;
    }

    liste.leggInn(0, 2);  // ny verdi legges forrest
    liste.leggInn(2, 6);  // ny verdi legges bakerst
    liste.leggInn(1, 3);  // ny verdi nest forrest
    liste.leggInn(3, 5);  // ny verdi nest bakerst
    liste.leggInn(0, 1);  // ny verdi forrest
    liste.leggInn(6, 7);  // ny verdi legges bakerst

    if (liste.antall() != 7)
    {
      antallFeil++;
      System.out.println(
        "Oppgave 6h: Feil i antall-oppdateringen i metoden leggInn()!");
    }

    if (!liste.toString().equals("[1, 2, 3, 4, 5, 6, 7]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6i: Feil i metoden leggInn()!");
    }

    if (!liste.omvendtString().equals("[7, 6, 5, 4, 3, 2, 1]"))
    {
      antallFeil++;
      System.out.println("Oppgave 6j: Feil i metoden leggInn()!");
    }
    return antallFeil;
  }

  public static int oppgave7()
  {
    int antallFeil = 0;

    DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();

    try
    {
      liste.fjern(0);
      antallFeil++;
      System.out.println("Oppgave 7a: Indeks 0 finnes ikke i en tom liste!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 7b: Metoden fjern(indeks) kaster feil type unntak!");
        antallFeil++;
      }
    }

    String[] s = {"A","B","C","D","E","F","G"};

    for (int i = 0; i < s.length; i++)
    {
      liste.leggInn(s[i]);
    }

    try
    {
      liste.fjern(7);
      antallFeil++;
      System.out.println("Oppgave 7c: Indeks 7 finnes ikke!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 7d: Metoden fjern(indeks) kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.fjern(-1);
      antallFeil++;
      System.out.println("Oppgave 7e: Indeks -1 finnes ikke!");
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println("Oppgave 7f: Metoden fjern(indeks) kaster feil type unntak!");
        antallFeil++;
      }
    }

    if (!liste.fjern(3).equals("D"))
    {
      antallFeil++;
      System.out.println("Oppgave 7g: Feil returverdi i metoden fjern(indeks)!");
    }

    liste.fjern(0);  // fjerner A
    liste.fjern(4);  // fjerner G

    if (liste.antall() != 4)
    {
      antallFeil++;
      System.out.println("Oppgave 7h: Feil i antall-oppdateringen!");
    }

    if (!liste.toString().equals("[B, C, E, F]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7i: Feil i fjern(indeks)-metoden!");
    }

    if (!liste.omvendtString().equals("[F, E, C, B]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7j: Feil i fjern(indeks)-metoden!");
    }

    liste.leggInn("H");
    liste.leggInn("I");

    if (liste.fjern(" ") == true
        || liste.fjern("G") == true
        || liste.fjern("J") == true)
    {
      antallFeil++;
      System.out.println("Oppgave 7k: Feil returverdi i metoden fjern(T)!");
    }


    if (liste.fjern("B") != true
        || liste.fjern("F") != true
        || liste.fjern("I") != true)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 7l: Feil returverdi i metoden fjern(T)!");
    }

    if (!liste.toString().equals("[C, E, H]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7m: Feil i metoden fjern(T)!");
    }

    if (!liste.omvendtString().equals("[H, E, C]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7n: Feil i metoden fjern(T)!");
    }

    liste.fjern("H");
    liste.fjern("C");
    liste.fjern("E");

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7o: Feil i metoden fjern(T)!");
    }

    if (!liste.omvendtString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 7p: Feil i metoden fjern(T)!");
    }

    if (liste.antall() != 0)
    {
      antallFeil++;
      System.out.println(
        "Oppgave 7q: Feil i antall-oppdateringen metoden fjern(T)!");
    }

    return antallFeil;
  }

  public static int oppgave8()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    for (int i = 1; i <= 7; i++)
    {
      liste.leggInn(i);
    }

    liste.nullstill();

    if (liste.antall() != 0)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 8a: Feil i antalloppdateringen i nullstill!");
    }

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 8b: Feil i metoden nullstill()!");
    }

    if (!liste.omvendtString().equals("[]"))
    {
      antallFeil++;
      System.out.println("Oppgave 8c: Feil i metoden nullstill()!");
    }

    return antallFeil;
  }

  public static int oppgave9()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    try
    {
      Iterator<Integer> i = liste.iterator();
      i.next();  // kaller next() i en tom liste
      System.out.println(
        "Oppgave 9a: Skal kastes unntak for next() i en tom liste!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 9b: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    liste.leggInn(1);

    Iterator<Integer> i = liste.iterator();

    if (i.next() != 1)
    {
      System.out.println
        ("Oppgave 9c: Metoden next() gir feil verdi!");
      antallFeil++;
    }

    try
    {
      i.next();  // det er ikke flere i listen

      System.out.println(
        "Oppgave 9d: Skal kastes unntak for next() her!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 9e: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    for (int k = 2; k <= 7; k++)
    {
      liste.leggInn(k);
    }

    int k = 1;
    for (Iterator<Integer> j = liste.iterator(); j.hasNext();)
    {
      if (j.next() != k)
      {
        System.out.println
          ("Oppgave 9f: Metoden next() gir feil verdier!");
        antallFeil++;
      }
      k++;
    }

    i = liste.iterator();
    liste.fjern(0); // fjerner etter at itertaoren er opprettet

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9g: Her skal det kastes et unntak!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 9h: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.leggInn(8);

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9i: Her skal det kastes et unntak!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 9j: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.fjern(new Integer(8));

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9k: Her skal det kastes et unntak!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 9l: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.leggInn(0,1);

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9m: Her skal det kastes et unntak!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 9n: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.oppdater(3, 9);

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9o: Her skal det kastes et unntak!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 9p: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    i = liste.iterator();
    liste.nullstill();

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9q: Her skal det kastes et unntak!");
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 9r: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

   for (int j = 1; j <= 7; j++) liste.leggInn(j);

    try
    {
      liste.iterator(7);
      System.out.println("Oppgave 9s: Indeks 7 finnes ikke!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 9t: Metoden kaster feil type unntak!");
        antallFeil++;
      }
    }

    try
    {
      liste.iterator(-1);
      System.out.println("Oppgave 9u: Indeks -1 finnes ikke!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof IndexOutOfBoundsException))
      {
        System.out.println
          ("Oppgave 9v: Metoden kaster feil type unntak!");
        antallFeil++;
      }
    }

    int m = 4;
    i = liste.iterator(3);
    for (; i.hasNext();)
    {
      if (i.next() != m)
      {
        antallFeil++;
        System.out.println("Oppgave 9w: Feil i metoden next()!");
      }
      m++;
    }

    try
    {
      i.next();
      antallFeil++;
      System.out.println("Oppgave 9x: Skal kastes for next() her!");
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 9y: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    return antallFeil;
  }


  public static int oppgave10()
  {
    int antallFeil = 0;

    DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

    for (int k = 1; k <= 13; k++)
    {
      liste.leggInn(k);
    }

    for (Iterator<Integer> i = liste.iterator(); i.hasNext();)
    {
      int verdi = i.next();
      if (verdi % 2 == 1)
      {
        i.remove(); // fjerner oddetallene
      }
    }

    if (liste.antall() != 6)
    {
      antallFeil++;
      System.out.println
        ("Oppgave 10a: Feil i antall-oppdatering i remove()!");
    }

    if (!liste.toString().equals("[2, 4, 6, 8, 10, 12]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10b: Feil i remove()!");
    }

    if (!liste.omvendtString().equals("[12, 10, 8, 6, 4, 2]"))
    {
      antallFeil++;
      System.out.println("Oppgave 10c: Feil i remove()!");
    }

    // fjerner alle i listen
    Iterator<Integer> j = liste.iterator();
    for (; j.hasNext();)
    {
      j.next();
      j.remove();
    }

    if (!liste.toString().equals("[]"))
    {
      antallFeil++;
      System.out.println
        ("Oppgave 10d: Feil n�r remove() har slettet alle!");
    }

    try
    {
      Iterator<Integer> i = liste.iterator();
      i.remove();  // kaller remove() i tom liste
      antallFeil++;
      System.out.println("Oppgave 10e: Her skal det kastes unntak");
    }
    catch (Exception e)
    {
      if (!(e instanceof IllegalStateException))
      {
        System.out.println("Oppgave 10f: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    for (int k = 1; k <= 5; k++)
    {
      liste.leggInn(k);
    }
    Iterator<Integer> i1 = liste.iterator();
    Iterator<Integer> i2 = liste.iterator();
    i1.next();
    i1.remove();
    try
    {
      i2.next();
      System.out.println("Oppgave 10g: Her skal det kastes et unntak!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof ConcurrentModificationException))
      {
        System.out.println("Oppgave 10h: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    j = liste.iterator();

    try
    {
      j.next();
      j.remove();
      j.remove();
      System.out.println("Oppgave 10i: Her skal det kastes et unntak!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof IllegalStateException))
      {
        System.out.println("Oppgave 10j: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    return antallFeil++;
  }

  public static int oppgave11()
  {
    class StringKomparator implements Comparator<String>
    {
      public int compare(String s, String t)
      {
        return s.compareTo(t);
      }
    }

    int antallFeil = 0;
    Liste<String> liste = new DobbeltLenketListe<>();

    try
    {
      DobbeltLenketListe.maks(liste, new StringKomparator());
      System.out.println("Oppgave 11a: Her skal det kastes et unntak!");
      antallFeil++;
    }
    catch (Exception e)
    {
      if (!(e instanceof NoSuchElementException))
      {
        System.out.println("Oppgave 11b: Det kastes feil type unntak!");
        antallFeil++;
      }
    }

    liste.leggInn("A");

    int m = DobbeltLenketListe.maks(liste, new StringKomparator());

    if (m != 0)
    {
      System.out.println("Oppgave 11c: Feil verdi!");
      antallFeil++;
    }

    liste.leggInn("B");
    liste.leggInn("C");
    liste.leggInn("C");

    m = DobbeltLenketListe.maks(liste, new StringKomparator());

    if (m != 2)
    {
      System.out.println("Oppgave 11d: Feil verdi!");
      antallFeil++;
    }
    liste.leggInn("D");
    liste.leggInn("E");

    m = DobbeltLenketListe.maks(liste, new StringKomparator());

    if (m != 5)
    {
      System.out.println("Oppgave 11e: Feil verdi!");
      antallFeil++;
    }

    liste.leggInn(0,"X");
    m = DobbeltLenketListe.maks(liste, new StringKomparator());

    if (m != 0)
    {
      System.out.println("Oppgave 11f: Feil verdi!");
      antallFeil++;
    }

    return antallFeil;
  }

} // Oblig2Test
