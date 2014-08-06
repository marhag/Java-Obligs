package Oblig1;

import java.io.*;
import java.util.Arrays;

public class Oblig1Test
{
  public static void main(String[] args) throws IOException
  {
    int antallFeil = 0;

    antallFeil += oppgave1();
    antallFeil += oppgave2();
    // antallFeil += oppgave3();
    // antallFeil += oppgave4();
    antallFeil += oppgave5();
    antallFeil += oppgave6();
    antallFeil += oppgave7();
    //antallFeil += oppgave8();
    antallFeil += oppgave9();
    antallFeil += oppgave10();

    if (antallFeil == 0)
    {
      System.out.println("Gratulerer!! Du passerte testen!");
    }
    else
    {
      System.out.println("Dette mŒ forbedres. Du har minst " + antallFeil + " feil!");
    }
  }

  ///// Oppgave 1 //////////////////////////////////////

  public static int oppgave1()
  {
    int antallFeil = 0;

    boolean unntak = false;
    int[] tom = {};
    try
    {
      Oblig1.maks(tom);  // kaller maks-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof java.util.NoSuchElementException))
      {
        System.out.println("Opgave 1: Feil unntak for en tom tabell!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println("Opgave 1: Kast unntak for en tom tabell!");
      antallFeil++;
    }

    int[] a = {3};
    int[] b = {5,2,8,4,7,6};
    int[] c = {5,4,3,2,1};
    int[] d = {1,2,3,4,5};
    if (Oblig1.maks(a) != 3 ||
        Oblig1.maks(b) != 8 ||
        Oblig1.maks(c) != 5 ||
        Oblig1.maks(d) != 5)
    {
      System.out.println("Oppgave 1: Metoden gir feil resultat!");
      antallFeil++;
    }

    int[] e = {1,4,3,7,6,5,10,2,9,8};
    int[] f = {1,3,4,6,5,7,2,9,8,10};

    Oblig1.maks(e);
    if(!Arrays.equals(e,f))
    {
      System.out.println("Oppgave 1: Feil i ombyttingene!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 2 //////////////////////////////////////

  public static int oppgave2()
  {
    int antallFeil = 0;

    int[] a = {};
    int[] b = {5};
    int[] c = {7,2,8,3,10,5,9,1,6,4};
    int[] d = {1,2,3,4,5,6,7,8,9,10};
    int[] e = {10,9,8,7,6,5,4,3,2,1};
    int[] f = {1,2,2,3,3,3,4,4,4,4,5,5,5,6,6,7};
    int[] g = {4,6,2,4,7,5,6,4,3,5,4,2,5,3,1,3};

    try
    {
      Oblig1.sortering(a);
    }
    catch (Exception ex)
    {
      System.out.println("Oppgave 2: Skal ikke kaste unntak for tom tabell!");
      antallFeil++;
    }

    Oblig1.sortering(b);  // skal ikke kastes unntak her!
    Oblig1.sortering(c);
    Oblig1.sortering(e);
    Oblig1.sortering(g);
    if (!Arrays.equals(c,d) ||
        !Arrays.equals(e,d) ||
        !Arrays.equals(f,g))
    {
      System.out.println("Oppgave 2: Metoden gir feil resultat!");
      antallFeil++;
    }

    return antallFeil;
  }
/*
  ///// Oppgave 3 //////////////////////////////////////

  public static int oppgave3()
  {
    int antallFeil = 0;

    int[] a = {};
    int[] b = {1};
    int[] c = {1,2,3,4,5,4};

    try
    {
      Oblig1.antallUlikeSortert(a);  // kaller metoden
      Oblig1.antallUlikeSortert(b);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 3: Ikke unntak for tabell med 0 eller 1 verdi!");
      antallFeil++;
    }

    boolean unntak = false;

    try
    {
      Oblig1.antallUlikeSortert(c);  // kaller metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalStateException))
      {
        System.out.println
          ("Oppgave 3: Feil unntak for en usortert tabell!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println
        ("Oppgave 3: Kast et unntak for en usortert tabell!");
      antallFeil++;
    }

    int[] d = {1,1};
    int[] e = {1,2,3,4,5,6,7};
    int[] f = {1,1,2,2,2,3,4,4,5,6,6,6,6,7};

    if (Oblig1.antallUlikeSortert(a) != 0
        || Oblig1.antallUlikeSortert(b) != 1
        || Oblig1.antallUlikeSortert(d) != 1
        || Oblig1.antallUlikeSortert(e) != 7
        || Oblig1.antallUlikeSortert(f) != 7)
     {
       System.out.println("Oppgave 3: Metoden gir feil resultat!");
       antallFeil++;
     }
    return antallFeil;
  }

  ///// Oppgave 4 //////////////////////////////////////

  public static int oppgave4()
  {
    int antallFeil = 0;

    int[] a = {};   // skal ikke kastes unntak her!
    int[] b = {1};  // skal ikke kastes unntak her!
    int[] c = {1,1};
    int[] d = {6,2,4,6,9,1,4,9,10};
    int[] dkopi = {6,2,4,6,9,1,4,9,10};
    int[] e = {5,4,3,2,1};
    int[] f = {1,2,2,2,2,2,3};

    try
    {
      Oblig1.antallUlikeSortert(a);  // kaller metoden
      Oblig1.antallUlikeSortert(b);  // kaller metoden
    }
    catch (Exception ex)
    {
      System.out.println
        ("Oppgave 4: Ikke unntak for tabell med 0 eller 1 verdi!");
      antallFeil++;
    }

    if (Oblig1.antallUlikeUsortert(a) != 0
        || Oblig1.antallUlikeUsortert(b) != 1
        || Oblig1.antallUlikeUsortert(c) != 1
        || Oblig1.antallUlikeUsortert(d) != 6
        || Oblig1.antallUlikeUsortert(e) != 5
        || Oblig1.antallUlikeUsortert(f) != 3)
    {
      System.out.println("Oppgave 4: Metoden gir feil resultat!");
      antallFeil++;
    }

    if (!Arrays.equals(d,dkopi))
    {
      System.out.println("Oppgave 4: Metoden endrer tabellen!");
      antallFeil++;
    }



    return antallFeil;
  }
*/
  ///// Oppgave 5 //////////////////////////////////////  

  public static int oppgave5()
  {
    int antallFeil = 0;

    char[] a = {};

    try
    {
      Oblig1.rotasjon(a);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 5: Skal ikke kaste unntak for en tom tabell!!");
        antallFeil++;
    }

    char[] b = {'A'};
    char[] b0 = {'A'};
    Oblig1.rotasjon(b);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 5: Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    char[] c = {'A','B'};
    char[] c0 = {'B','A'};
    Oblig1.rotasjon(c);
    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 5: Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
    char[] d0 = {'J','A','B','C','D','E','F','G','H','I'};

    Oblig1.rotasjon(d);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 5: Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    return antallFeil;

  }

  ///// Oppgave 6 //////////////////////////////////////  

  public static int oppgave6()
  {
    int antallFeil = 0;

    char[] a = {};

    try
    {
      Oblig1.rotasjon(a,1);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 6: Skal ikke kaste unntak for en tom tabell!!");
        antallFeil++;
    }

    char[] b = {'A'};
    char[] b0 = {'A'};
    Oblig1.rotasjon(b,0);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    Oblig1.rotasjon(b,1);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    Oblig1.rotasjon(b,-1);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    char[] c = {'A','B'};
    char[] c0 = {'B','A'};
    Oblig1.rotasjon(c,1);

    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    c = new char[] {'A','B'};

    Oblig1.rotasjon(c,-1);
    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
    char[] d0 = {'G','H','I','J','A','B','C','D','E','F'};

    Oblig1.rotasjon(d,4);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    d = new char[]{'A','B','C','D','E','F','G','H','I','J'};
    Oblig1.rotasjon(d,-6);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 6: Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    char[] x = new char[100_000];
    long tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,99_999);
    tid = System.currentTimeMillis() - tid;

    if (tid > 40)
    {
      System.out.println("Oppgave 6: Metoden "
        + "er for ineffektiv. MŒ forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,199_999);
    tid = System.currentTimeMillis() - tid;
    if (tid > 40)
    {
      System.out.println("Oppgave 6: Metoden "
        + "er for ineffektiv. MŒ forbedres!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 7 //////////////////////////////////////  

  public static int oppgave7()
  {
    int antallFeil = 0;

    int[] a = {1,2,3,4,5,6};
    String s = Oblig1.toString(a,'[', '>',"-");
    String t = "[1-2-3-4-5-6>";
    if (!s.equals(t))
    {
      System.out.println("Oppgave 7: Feil resultat!");
      antallFeil++;
    }

    int[] c = {100,200,300};
    s = Oblig1.toString(c,'[', ']',"----");
    t = "[100----200----300]";
    if (!s.equals(t))
    {
      System.out.println("Oppgave 7: Feil resultat!");
      antallFeil++;
    }

    int[] d = {2};
    s = Oblig1.toString(d,'[',']',"");
    if (!s.equals("[2]"))
    {
      System.out.println("Oppgave 7: Feil resultat for tabell med ett element!");
      antallFeil++;
    }

    int[] e = {};
    s = Oblig1.toString(e,'[',']',"");
    if (!s.equals("[]"))
    {
      System.out.println("Oppgave 7: Feil resultat for tom tabell!");
      antallFeil++;
    }

    int[] b = new int[20000];
    long tid = System.currentTimeMillis();
    Oblig1.toString(b,' ',' '," ");
    tid = System.currentTimeMillis() - tid;

    if (tid > 40)
    {
      System.out.println("Oppgave 7: Metoden "
        + "er for ineffektiv. MŒ forbedres!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 8 //////////////////////////////////////  

  public static int oppgave8()
  {
    System.out.println("Denne testen skal dere lage selv!");

    return 0;
  }

  ///// Oppgave 9 //////////////////////////////////////  

  public static int oppgave9()
  {
    int antallFeil = 0;
    boolean unntak = false;

    int[] a = {3,2,8,5,6,10,4,9,1,7};

    try
    {
      Oblig1.kMinst(a,0);  // kaller kMinst-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalArgumentException))
      {
        System.out.println("Oppgave 9: Feil unntak for k < 1!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println("Opgave 9: Skal kaste unntak for k < 1!");
      antallFeil++;
    }

    unntak = false;

    try
    {
      Oblig1.kMinst(a,a.length + 1);  // kaller kMinst-metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalArgumentException))
      {
        System.out.println("Opgave 9: Feil unntak for k > a.length!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println("Opgave 9: Skal kaste unntak for k > a.length!");
      antallFeil++;
    }

    int[] b = {1};
    int[] c = {1,2};
    int[] d = {1,2,3};
    int[] e = {1,2,3,4};
    int[] f = {1,2,3,4,5,6,7,8,9,10};

    int[] ac = a.clone();

    if (!Arrays.equals(b,Oblig1.kMinst(a,1)) ||
        !Arrays.equals(c,Oblig1.kMinst(a,2)) ||
        !Arrays.equals(d,Oblig1.kMinst(a,3)) ||
        !Arrays.equals(e,Oblig1.kMinst(a,4))||
        !Arrays.equals(f,Oblig1.kMinst(a,a.length)))
    {
      System.out.println("Oppgave 9: Metoden gir feil resultat!");
      antallFeil++;
    }

    if (!Arrays.equals(a,ac))
    {
      System.out.println
        ("Oppgave 9: Metoden gj¿r endringer i koden!");
      antallFeil++;
    }

    int[] g = {1};
    if (!Arrays.equals(g,Oblig1.kMinst(b,1)))
    {
      System.out.println
        ("Oppgave 9: Feil hvis tabellen har kun en verdi!!");
      antallFeil++;
    }

    return antallFeil;
  }

  ///// Oppgave 10 //////////////////////////////////////  

  public static int oppgave10() throws IOException
  {
    int antallFeil = 0;

    long tid = System.currentTimeMillis();
    int[] antall1 =
      Oblig1.bokstavfrekvens("http://www.iu.hio.no/~ulfu/appolonius/kap1/1/kap11.html");
    tid = System.currentTimeMillis() - tid;

    int[] antall2 =
    {4768, 1468, 935, 3057, 7922, 1063, 1747, 800, 5046, 442, 1713, 3615, 1932,
      4748, 2760, 1996, 128, 4431, 4191, 5305, 816, 1334, 253, 212, 335, 13,
      67, 353, 405};

    if (!Arrays.equals(antall1,antall2))
    {
      System.out.println("Oppgave 10: Feil i metoden!");
       antallFeil++;
    }

    if (tid > 150)
    {
      System.out.println
        ("Oppgave 10: For ineffektiv! MŒ forbedres! Har du treg nettforbindelse?");
      antallFeil++;
    }
    return antallFeil;
  }

} // class Oblig1Test
