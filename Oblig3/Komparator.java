package Oblig3;

import java.util.*;    // Dette skal ligge på filen Komparator.java

  public class Komparator
  {
    private Komparator() {}     // hindrer instansiering

    private static class         // komparator for en naturlig ordning
    Naturlig<T extends Comparable<? super T>> implements Comparator<T>
    {
      public int compare(T t1, T t2) { return t1.compareTo(t2); }

    } // class Naturlig

    public static     // en konstruksjonsmetode
    <T extends Comparable<? super T>> Comparator<T> naturlig()
    {
      return new Naturlig<T>();
    }

  } // class Komparator