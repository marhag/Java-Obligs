package Oblig2;

import java.util.*;

public class EnkeltLenketListe<T> implements Liste<T>
{
  private static final class Node<T>       // en indre nodeklasse
  {
    private T verdi;
    private Node<T> neste;

    private Node(T verdi, Node<T> neste)   // konstruktør
    {
      this.verdi = verdi;
      this.neste = neste;
    }
  }  // Node

  private Node<T> hode, hale;   // pekere til første og siste node
  private int antall;           // antall verdier/noder i listen
  private int antallEndringer;  // endringer i listen

  private void indeksKontroll(int indeks)
  {
    if (indeks < 0)
    {
      throw new IndexOutOfBoundsException("Indeks("
        + indeks + ") er negativ!");
    }
    else if (indeks >= antall)
    {
      throw new IndexOutOfBoundsException("Indeks("
        + indeks + ") >= antall(" + antall + ")!");
    }
  }

  private Node<T> finnNode(int indeks)
  {
    Node<T> p = hode;

    for (int i = 0; i < indeks; i++)
    {
      p = p.neste;
    }

    return p;
  }

  public EnkeltLenketListe()   // standardkonstruktør
  {
    hode = hale = null;        // hode og hale til null
    antall = 0;                // ingen verdier - tom liste
    antallEndringer = 0;       // ingen endringer når vi starter
  }

  public EnkeltLenketListe(Iterable<T> itererbar)    // konstruktør
  {
    this();                    // bruker konstruktøren over

    for (T t : itererbar)
    {
      leggInn(t);              // en forAlle-løkke
    }
  }

  @Override
  public boolean leggInn(T t)  // parameterverdi t legges bakerst
  {
    if (t == null)
    {
      throw new NullPointerException("Ikke tillatt med null-verdier!");
    }

    if (antall == 0)           // sjekker om listen er tom
    {
      hode = hale = new Node<>(t, null);
    }
    else
    {
      hale = hale.neste = new Node<>(t, null);  // legges bakerst
    }

    antallEndringer++;         // en ny endring
    antall++;                  // en mer i listen

    return true;               // vellykket innlegging
  }

  @Override
  public void leggInn(int indeks, T t)    // verdi t til posisjon indeks
  {
    if (t == null)
    {
      throw new IllegalArgumentException
        ("Ikke tillatt med null-verdier!");
    }

    if (indeks < 0 || indeks > antall)
    {
      throw new IndexOutOfBoundsException
        ("Indeks(" + indeks + ") er ulovlig!");
    }

    if (indeks == 0)                     // ny verdi skal ligge først
    {
      hode = new Node<>(t, hode);        // legges først
      if (antall == 0)
      {
        hale = hode;                     // listen har kun én node
      }
    }
    else if (indeks == antall)           // ny verdi skal ligge bakerst
    {
      hale = hale.neste = new Node<>(t, null);  // legges bakerst
    }
    else
    {
      Node<T> p = hode;    // må telle fra hode og til indeks - 1
      for (int i = 1; i < indeks; i++)
      {
        p = p.neste;
      }
      p.neste = new Node<>(t, p.neste);  // ny node settes på rett plass
    }

    antallEndringer++;                   // en ny endring
    antall++;                            // listen har fått en ny verdi
  }

  @Override
  public boolean inneholder(T t)
  {
    return indeksTil(t) != -1;
  }

  @Override
  public T hent(int indeks)
  {
    indeksKontroll(indeks);

    return finnNode(indeks).verdi;       // bruker indeksTil 
  }

  @Override
  public int indeksTil(T t)
  {
    if (t == null)
    {
      return -1;
    }

    Node<T> p = hode;

    for (int indeks = 0; indeks < antall; indeks++)
    {
      if (p.verdi.equals(t))
      {
        return indeks;
      }

      p = p.neste;
    }

    return -1;
  }

  @Override
  public T oppdater(int indeks, T t)
  {
    indeksKontroll(indeks);

    Node<T> p = finnNode(indeks);        // bruker finnNode

    T gammelVerdi = p.verdi;
    p.verdi = t;

    antallEndringer++;                   // en ny endring

    return gammelVerdi;
  }

  @Override
  public T fjern(int indeks)
  {
    indeksKontroll(indeks);              // sjekker indeksen

    T temp;                              // hjelpevariabel

    if (indeks == 0)
    {
      temp = hode.verdi;                 // tar vare på verdien som skal fjernes
      hode = hode.neste;                 // hode flyttes til neste node

      if (antall == 1) hale = null;      // det var kun en verdi i listen
    }
    else
    {
      Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
      Node<T> q = p.neste;               // q skal fjernes

      temp = q.verdi;                    // tar vare på verdien som skal fjernes

      if (q == hale) hale = p;           // q er siste node

      p.neste = q.neste;                 // "hopper over" q
    }

    antallEndringer++;                   // en ny endring
    antall--;                            // reduserer antallet

    return temp;                         // returner fjernet verdi
  }

  @Override
  public boolean fjern(T t)              // t skal fjernes
  {
    if (t == null)
    {
      return false;                      // ingen nullverdier i listen
    }

    Node<T> q = hode, p = null;          // hjelpepekere

    while (q != null)                    // q skal finne verdien t
    {
      if (q.verdi.equals(t))
      {
        break;                           // verdien funnet
      }
      p = q;
      q = q.neste;                       // p er forgjengeren til q
    }

    if (q == null)
    {
      return false;                      // fant ikke t
    }
    else if (q == hode)
    {
      hode = hode.neste;                 // hopper over q
    }
    else
    {
      p.neste = q.neste;                 // hopper over q
    }

    if (q == hale)
    {
      hale = p;                          // oppdaterer hale
    }

    q.verdi = null;                      // nuller verdien til q
    q.neste = null;                      // nuller nestepeker

    antallEndringer++;                   // en ny endring
    antall--;                            // en node mindre i listen

    return true;                         // vellykket fjerning
  }

  @Override
  public int antall()
  {
    return antall;
  }

  @Override
  public boolean tom()
  {
    return antall == 0;
  }

  @Override
  public void nullstill()
  {
    Node<T> p = hode, q;

    while (p != null)
    {
      q = p.neste;
      p.neste = null;
      p.verdi = null;
      p = q;
    }

    hode = hale = null;

    antallEndringer++;
    antall = 0;
  }

  @Override
  public String toString()
  {
    StringBuilder s = new StringBuilder();

    s.append('[');

    if (!tom())
    {
      Node<T> p = hode;
      s.append(p.verdi);

      p = p.neste;

      while (p != null)  // tar med resten hvis det er noe mer
      {
        s.append(',').append(' ').append(p.verdi);
        p = p.neste;
      }
    }

    s.append(']');

    return s.toString();
  }

  private class EnkeltLenketListeIterator implements Iterator<T>
  {
    private Node<T> p = hode;                    // den første i listen
    private boolean fjernOK = false;             // blir sann når next() kalles
    private int
    forventetAntallEndringer = antallEndringer;  // får startverdi

    public boolean hasNext()
    {
      return p != null;  // p er ute av listen hvis den har blitt null
    }

    public T next()
    {
      if (antallEndringer != forventetAntallEndringer)
      {
        throw new ConcurrentModificationException("Listen er endret!");
      }

      if (!hasNext())
      {
        throw new NoSuchElementException("Tomt eller ingen flere verdier!");
      }

      fjernOK = true;            // nå kan remove() kalles

      T denneVerdi = p.verdi;    // tar vare på verdien i p

      p = p.neste;               // flytter p til den nestenoden

      return denneVerdi;         // returnerer verdien
    }

    @Override
    public void remove()
    {
      if (antallEndringer != forventetAntallEndringer)
      {
        throw new ConcurrentModificationException("Listen er endret!");
      }

      if (!fjernOK)
      {
        throw new IllegalStateException("Ulovlig tilstand!");
      }

      fjernOK = false;               // remove() kan ikke kalles på nytt
      Node<T> q = hode;              // hjelpepeker

      if (hode.neste == p)           // skal den første fjernes?
      {
        hode = hode.neste;           // den første fjernes
      }
      else
      {
        // må finne forgjengeren til forgjengeren til p

        Node<T> r = hode;
        while (r.neste.neste != p)
        {
          r = r.neste;               // flytter r
        }

        q = r.neste;                 // det er q som skal fjernes
        r.neste = p;                 // "hopper" over q
      }

      q.verdi = null;                // nuller verdien i noden
      q.neste = null;                // nuller nestepeker

      antallEndringer++;             // en ny endring i treet
      forventetAntallEndringer++;    // en lokal endring
      antall--;                      // en node mindre i listen
    }

  } // class EnkeltLenketListeIterator

  @Override
  public Iterator<T> iterator()
  {
    return new EnkeltLenketListeIterator();
  }

} // class EnkeltLenketListe