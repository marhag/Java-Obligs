/*
 * 
Martin Hagen - s188098 - 2AA
Joakim Rishaug - s188080 - 2AA
Sondre Sparby Boge - s181130 - 2AA
Marius Maudal - s177654 - 3AB
Lars-Erik Kasin - s178816 - 2AA
 */

package Oblig2;

import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T>
{
	private static final class Node<T>       // en indre nodeklasse
	{
		private T verdi;
		private Node<T> forrige, neste;

		private Node(T verdi, Node<T> forrige, Node<T> neste)   // konstruktør
		{
			this.verdi = verdi;
			this.forrige = forrige;
			this.neste = neste;
		}
	}

	private Node<T> hode;         // peker til den første i listen
	private Node<T> hale;         // peker til den siste i listen
	private int antall;           // antall noder i listen
	private int antallEndringer;  // antall endringer i listen

	private void indeksKontroll(int indeks)
	{
		if (indeks < 0 )
			throw new IndexOutOfBoundsException("Indeks " +
					indeks + " er negativ!");
		else if (indeks >= antall)
			throw new IndexOutOfBoundsException("Indeks " +
					indeks + " >= antall(" + antall + ") noder!");
	}

	private static <T> void nullTest(T t)
	{
		if(t==null) throw new NullPointerException("Ikke tillatt med null-verdi");
	}

	private Node<T> finnNode(int indeks)
	{
		Node<T> p;
		if(indeks < antall/2)
		{
			p = hode;
			for(int i = 0; i<indeks;i++)
			{
				p = p.neste;
			}
			return p;
		}
		else
		{
			p= hale;
			for(int i = antall-1; i>indeks;i--)
			{
				p = p.forrige;
			}
			return p;
		}
	}

	public DobbeltLenketListe()  // konstruktør
	{
		hode = hale = null;
		antall = 0;
		antallEndringer = 0;
	}

	public boolean tom()
	{
		return antall==0;  
	}

	public int antall()
	{
		return antall;   
	}

	public void nullstill()
	{
		hale = hode = null;
		antall= 0;
		antallEndringer++;
	}

	public boolean inneholder(T verdi)
	{
		if(indeksTil(verdi)==-1)
			return false;
		return true;
	}

	public void leggInn(int indeks, T verdi)
	{
		nullTest(verdi);
		if(indeks < 0||indeks >antall) throw new IndexOutOfBoundsException("Ikke tillatt indeks");

		if(antall == 0)
		{
			hode = new Node<T>(verdi,null,null);
			hale = hode;
		}
		else if(indeks==0)
		{
			hode = new Node<T>(verdi,null,hode);
			hode.neste.forrige = hode;
			//if(antall==0) hale = hode;
		}
		else if(indeks==antall)
		{
			hale.neste = new Node<T>(verdi,hale,null);
			hale = hale.neste;
		}
		else
		{
			Node<T> p = finnNode(indeks-1);
			Node<T> q = finnNode(indeks);

			Node<T> ny = new Node<T>(verdi,q.forrige,p.neste);
			p.neste = ny;
			q.forrige = ny;
		}
		antall++;
		antallEndringer++;
	}

	public boolean leggInn(T verdi)
	{
		nullTest(verdi);
		if(antall == 0) // listen er tom
		{
			hale = hode = new Node<T>(verdi,null,null);
		}
		else
		{
			hale.neste = new Node<T>(verdi, hale,null);
			hale = hale.neste;
		}
		antall++;
		antallEndringer++;
		return true;
	}

	public T hent(int indeks)
	{
		indeksKontroll(indeks);
		return finnNode(indeks).verdi;
	}

	public int indeksTil(T verdi)
	{
		if(verdi==null)return -1;
		int i = 0;
		Node<T> p = hode;
		while(p!=null)
		{
			if(p.verdi.equals(verdi))
				return i;
			i++;
			p = p.neste;
		}
		return -1;
	}

	public T oppdater(int indeks, T nyverdi)
	{
		nullTest(nyverdi);
		indeksKontroll(indeks);

		Node<T> p = finnNode(indeks);

		T gammelverdi = p.verdi;
		p.verdi = nyverdi;
		antallEndringer++;
		return gammelverdi;
	}

	public T fjern(int indeks)
	{
		indeksKontroll(indeks);
		Node<T> q,p = null;

		if(indeks == 0)
		{
			q = hode;
			hode = hode.neste;
			if(antall!=1) hode.forrige = null;
			
			
		}
		else
		{
			p = finnNode(indeks-1);
			q = p.neste;
			p.neste = q.neste;
			if(q!=hale) q.neste.forrige = p;
		}
		if(q == hale)
		{
			if(antall == 1)
			{
				hale = hode = null;
			}
			else
			{
				hale = q.forrige;
				hale.neste = null;
			}
		}

		T verdi = q.verdi;
		q.verdi = null;
		q.neste = null;

		antall--;
		antallEndringer++;

		return verdi;
	}

	public boolean fjern(T verdi)
	{
		int i = indeksTil(verdi);
		if(i==-1) return false;
		else fjern(i);
		return true;
	}

	private class DobbeltLenketListeIterator implements Iterator<T>
	{
		private Node<T> p;
		private boolean fjernOK;
		private int forventetAntallEndringer;

		private DobbeltLenketListeIterator()
		{
			p = hode;         // p starter på den første i listen
			fjernOK = false;  // blir sann når next() kalles
			forventetAntallEndringer = antallEndringer;  // teller endringer
		}

		public boolean hasNext()
		{
			return p != null;  // p er ute av listen hvis den har blitt null 
		}

		public T next()
		{
			if (p == null) 
				throw new NoSuchElementException("Tomt eller ingen verdier igjen!");
			if(antallEndringer != forventetAntallEndringer)
				throw new ConcurrentModificationException("Det er gjort endringer!");

			fjernOK = true;           //kan fjernes

			T denneVerdi = p.verdi;    
			p = p.neste;               

			return denneVerdi;
		}

		public void remove()
		{
			if (!fjernOK) 
				throw new IllegalStateException("Ulovlig tilstand!");
			if(antallEndringer != forventetAntallEndringer)
				throw new ConcurrentModificationException("Det er gjort endringer!");

			fjernOK = false;
			Node<T> q = hode; // hjelpepeker
			if(antall == 1)//bare et element
			{
				hale = hode = null;
			}
			else if(p == null) // siste
			{
				q = hale;
				hale = hale.forrige;
				q.forrige = null;
				hale.neste = null;
			}
			else if(p.forrige==hode) // første
			{
				q = hode;
				hode = q.neste;
				hode.forrige = null;
			}
			else // "midt i"
			{
				Node<T> r = hode;        // hjelpenode til
				while (r.neste.neste != p)
				{
					r = r.neste;               
				}
				q = r.neste;
				r.neste = p;
				p.forrige = r;
			}
			
			q.verdi = null;                
			q.neste = null;                

			antall--; 
			antallEndringer++;
			forventetAntallEndringer++;
		}

	} // class DobbeltLenketListeIterator  

	public Iterator<T> iterator()
	{
		return new DobbeltLenketListeIterator();
	}

	public Iterator<T> iterator(int indeks)
	{
		indeksKontroll(indeks);
		DobbeltLenketListeIterator iterator = new DobbeltLenketListeIterator();
		
		for(int i = 0; i<indeks;i++)
		{
			iterator.next();
		}
		return iterator; 
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append('[');

		Node<T> p = hode;

		if(!tom())
		{
			s.append(p.verdi);
			p= p.neste;

			while(p!= null)
			{
				s.append(',').append(' ').append(p.verdi);
				p=p.neste;
			}
		}
		s.append(']');
		return s.toString();
	}

	public String omvendtString()
	{
		StringBuilder s = new StringBuilder();
		s.append('[');

		Node<T> p = hale;

		if(!tom())
		{
			s.append(p.verdi);

			p= p.forrige;

			while(p!= null)
			{
				s.append(',').append(' ').append(p.verdi);
				p=p.forrige;
			}
		}
		s.append(']');
		return s.toString();
	}
	
	/******** Max-metoden********/
	public static <T> int maks(Liste<T> liste, Comparator<? super T> c)
	{
		if(liste == null || liste.antall() == 0) throw new NoSuchElementException("Listen er tom");
		Iterator<T> it = liste.iterator();
		
		int i = 0;
		int indeks = 0;
		T max = it.next();

		while(it.hasNext())
		{
			T verdi = it.next();
			i++;
			if((max ==null || c.compare(verdi, max)>0))
			{
				max = verdi;
				indeks = i;
			}
		}
		return indeks;
	}

} // class DobbeltLenketListe