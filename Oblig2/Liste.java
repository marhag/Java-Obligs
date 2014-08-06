package Oblig2;

import java.util.Iterator;

public interface Liste<T> extends Beholder<T>
{
	public boolean leggInn(T t);              // Nytt element bakerst
	public void leggInn(int indeks, T t);     // Nytt element p� plass indeks
	public boolean inneholder(T t);           // Er t i listen?
	public T hent(int indeks);                // Hent element p� plass indeks
	public int indeksTil(T t);                // Hvor ligger t?
	public T oppdater(int indeks, T t);       // Oppdater p� plass indeks
	public boolean fjern(T t);                // Fjern objektet t
	public T fjern(int indeks);               // Fjern element p� plass indeks
	public int antall();                      // Antallet i listen
	public boolean tom();                     // Er listen tom?
	public void nullstill();                  // Listen nullstilles (og t�mmes)
	public Iterator<T> iterator();            // En iterator
}