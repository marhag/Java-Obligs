/*
 * 
Martin Hagen - s188098 - 2AA
Joakim Rishaug - s188080 - 2AA
Sondre Sparby Boge - s181130 - 2AA
Marius Maudal - s177654 - 3AB
Lars-Erik Kasin - s178816 - 2AA
 */

package Oblig3;

//////////////////SBinTre2 /////////////////////////////////

import java.util.*;


public class SBinTre2<T> implements Beholder<T>
{
	private static final class Node<T>   // en indre nodeklasse
	{
		private T verdi;                   // nodens verdi
		private Node<T> venstre, høyre;    // venstre og høyre barn

		private Node(T verdi, Node<T> v, Node<T> h)  // konstruktør
		{
			this.verdi = verdi;
			venstre = v; høyre = h;
		}

		private Node(T verdi)  // konstruktør
		{
			this(verdi, null, null);
		}

		public String toString(){ return "" + verdi;}

	} // class Node

	private Node<T> rot;                  // peker til rotnoden
	private int antall;                   // antall noder
	private int endringer;                // antall endringer
	private int høyde;                    // treets høyde
	private int antallIngenBarn;          // antall bladnoder
	private int antallToBarn;             // antall noder med to barn
	private int antallEttBarn;            // antall noder med kun ett barn

	private final Comparator<? super T> comp;  // komparator
	
	public SBinTre2(Comparator<? super T> c)    // konstruktør
	{
		rot = null;
		antall = 0;
		endringer = 0;
		høyde = -1;
		antallIngenBarn = 0;
		antallEttBarn = 0;
		antallToBarn = 0;
		comp = c;
	}
	
	public static <T extends Comparable<? super T>> SBinTre2<T> lagTre()
	{
		return new SBinTre2<>(Komparator.<T>naturlig());
	}
	
	public static <T> SBinTre2<T> lagTre(Comparator<? super T> c)
	{
		return new SBinTre2<>(c);
	}
	
	public int antall()        // antall verdier i treet
	{
		return antall;
	}
	
	public boolean tom()       // er treet tomt?
	{
		return antall == 0;
	}
	
	public int høyde()
	{
		return høyde;
	}
	
	public int antallIngenBarn()
	{
		return antallIngenBarn;
	}
	
	public int antallEttBarn()
	{
		return antallEttBarn;
	}
	
	public int antallToBarn()
	{
		return antallToBarn;
	}
	
	public void nullstill()
	{
		rot = null;
	    antall = 0;
	    høyde = -1;
	    antallIngenBarn = 0;
	    antallEttBarn = 0;
	    antallToBarn = 0;
	}
	
	public boolean leggInn(T verdi)
	{
		if (verdi == null) throw
		new NullPointerException("Ulovlig nullverdi!");
		
		Node<T> p = rot, q = null;               // p starter i roten
		int cmp = 0;                             // hjelpevariabel
		int niva = 0;
		
		while (p != null)       // fortsetter til p er ute av treet
		{
			q = p;                                 // q forelder til p
			cmp = comp.compare(verdi,p.verdi);      // bruker komparatoren
			p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
			niva++;
		}
		p = new Node<>(verdi);                   // oppretter en ny node
		
		if (q == null) 
		{
			rot = p;// rotnoden
			//høyde++;
		}
		else if (cmp < 0)
		{
			q.venstre = p;         // til venstre for q
			if(q.høyre!=null)
			{
				antallEttBarn--;
				antallToBarn++;
			}
			else
			{
				antallIngenBarn--;
				antallEttBarn++;
			}
		}
		else 
		{
			q.høyre = p;  // til høyre for q
			if(q.venstre!=null)
			{
				antallEttBarn--;
				antallToBarn++;
			}
			else
			{
				antallIngenBarn--;
				antallEttBarn++;
			}
		}
		
		endringer++;                             // en endring
		antall++; 								// en ny verdi i treet
		antallIngenBarn++;
		if(niva > høyde) høyde++;
		return true;
	}
	
	public boolean inneholder(T verdi)
	{
		if (verdi == null) return false;
		
		Node<T> p = rot;                            // starter i roten
		while (p != null)                           // sjekker p
		{
			int cmp = comp.compare(verdi,p.verdi);     // sammenligner
			if (cmp < 0) p = p.venstre;               // går til venstre
			else if (cmp > 0) p = p.høyre;            // går til høyre
			else return true;                         // cmp == 0, funnet
		}
		return false;                               // ikke funnet
	}
	
	public boolean fjern(T verdi)
	{
		if (verdi == null) return false;
		
		Node<T> p = rot, q = null;   // q skal være forelder til p
		
		while (p != null)            // leter etter verdi
		{
			int cmp = comp.compare(verdi,p.verdi);       // sammenligner
			if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
			else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
			else
			break;    // den søkte verdien ligger i p
		}
		
		if (p == null) return false;   // finner ikke verdi
		
		if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
		{
			Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
			if (p == rot) rot = b;
			else if (p == q.venstre) q.venstre = b;
			else q.høyre = b;
		}
		else  // Tilfelle 3)
		{
			Node<T> s = p, r = p.høyre;   // finner neste i inorden
			while (r.venstre != null)
			{
				s = r;    // s er forelder til r
				r = r.venstre;
			}
		
			p.verdi = r.verdi;   // kopierer verdien i r til p
		
			if (s != p) s.venstre = r.høyre;
			else s.høyre = r.høyre;
		}
		
		endringer++;
		antall--;   // det er nå én node mindre i treet
		return true;
	}
	
	private class InordenIterator implements Iterator<T>
	{
		private Stakk<Node<T>> s = new TabellStakk<>();  // for traversering
		private Node<T> p = null;                        // nodepeker
		private int iteratorendringer;                   // iteratorendringer
		
		private Node<T> først(Node<T> q)   // en hjelpemetode
		{
			while (q.venstre != null)        // starter i q
			{
				s.leggInn(q);                  // legger q på stakken
				q = q.venstre;                 // går videre mot venstre
			}
			return q;                        // q er lengst ned til venstre
		}
	
		public InordenIterator()  // konstruktør
		{
			if (rot == null) return;         // treet er tomt
			p = først(rot);                  // bruker hjelpemetoden
			iteratorendringer = endringer;   // setter treets endringer
		}
		
		public T next()
		{
			if (iteratorendringer != endringer)
			throw new ConcurrentModificationException();
			
			if (!hasNext()) throw new NoSuchElementException();
			
			T verdi = p.verdi;               // tar vare på verdien i noden p
			
			if (p.høyre != null) p = først(p.høyre);  // p har høyre subtre
			else if (!s.tom()) p = s.taUt();          // p har ikke høyre subtre
			else p = null;                            // stakken er tom
			
			return verdi;                    // returnerer verdien
		}
		
		public boolean hasNext()
		{
			return p != null;
		}
		
		public void remove()
		{
		throw new UnsupportedOperationException();
		}
	}
	
	public Iterator<T> iterator()
	{
		return new InordenIterator();
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();   // StringBuilder
		s.append('[');                           // starter med [
		if (!tom()) toString(rot,s);             // den rekursive metoden
		s.append(']');                           // avslutter med ]
		return s.toString();                     // returnerer
	}
	
	private static <T> void toString(Node<T> p, StringBuilder s)
	{
		if (p.venstre != null)                   // p har et venstre subtre
		{
			toString(p.venstre, s);                // komma og mellomrom etter
			s.append(',').append(' ');             // den siste i det venstre
		}                                        // subtreet til p
		
		s.append(p.verdi);                       // verdien i p
		
		if (p.høyre != null)                     // p har et høyre subtre
		{
			s.append(',').append(' ');             // komma og mellomrom etter
			toString(p.høyre, s);                  // p siden p ikke er den
		}                                        // siste noden i inorden
	}
	
	public int antall(T verdi)
	{	
		if(verdi==null) return 0;
		
		int antall=0;//antall en verdi er funnet
		
		Node<T> p = rot;
		
		while (p != null)            // leter etter verdi
		{
			int cmp = comp.compare(verdi,p.verdi);       // sammenligner
			if (cmp < 0) { p = p.venstre; }      // går til venstre
			else if (cmp > 0) { p = p.høyre; }   // går til høyre
			else{
				antall++;
				p = p.høyre;
			}
		}
		return antall;
	}
	
	public T min()
	{
		if (tom()) throw new NoSuchElementException("Treet er tomt!");
		
		Node<T> p = rot;
		while (p.venstre != null) p = p.venstre;
		return p.verdi;
	}
	
	public T nestMin()
	{
		if(rot == null) throw new NoSuchElementException("Treet er tomt!");
		if(antall==1) throw new NoSuchElementException("Kun et element!");
		
		Stakk<Node<T>> s = new TabellStakk<>();
		s.leggInn(null);
		
		Node<T> p = rot; //starter i rot
		for( ; p.venstre != null; p = p.venstre) s.leggInn(p); // beveger seg til venstre i treet
		
		if(p.høyre != null) // gjør ett ledd i inorden
		{
			for(p = p.høyre; p.venstre != null; p = p.venstre)
				s.leggInn(p);
		}
		else p = s.taUt();
		
		return p.verdi;	
	}
	
	public T minFjern()
	{	
		if (tom()) throw new NoSuchElementException("Treet er tomt!");
		
		Node<T> p = rot, q= null,r = null;
		
		if(p.høyre== null && p.venstre==null){
			nullstill();
			return p.verdi;
		}
		if(min().equals(rot.verdi)) // rot er minst
		{
			if(p.høyre!=null) 
			{
				r = p.høyre;
				antallEttBarn--;
			}
			rot = r;	
		}
		else
		{
			while(p.venstre!=null)
			{
				q = p;
				p = p.venstre;
			}
			if(p.høyre == null) // tar vare på evt resten av grenen
			{
				if(q.høyre == null)
				{
					antallEttBarn--;
					antallIngenBarn++;
				}
				else
				{
					antallToBarn--;
					antallEttBarn++;
				}
				antallIngenBarn--;
				q.venstre = null;
			}
			else
			{
				r = p.høyre;
				q.venstre = r;
				antallEttBarn--;
			}
		}
		antall--;
		endringer++;
		return p.verdi;
	}
	
	public T maks()
	{
		if (tom()) throw new NoSuchElementException("Treet er tomt!");
		
		Node<T> p = rot;
		while (p.høyre != null) p = p.høyre;
		return p.verdi;
	}
	
	public T nestMaks()
	{
		if(rot == null) throw new NoSuchElementException("Treet er tomt!");
		if(antall==1) throw new NoSuchElementException("Kun et element!");
		Stakk<Node<T>> s = new TabellStakk<>();
		s.leggInn(null);
		
		Node<T> p = rot; //starter i rot
		for( ; p.høyre != null; p = p.høyre) s.leggInn(p); // beveger seg til venstre i treet
		
		if(p.venstre != null) // gjør ett ledd i inorden, omvendt
		{
			for(p = p.venstre; p.høyre != null; p = p.høyre)
				s.leggInn(p);
		}
		else p = s.taUt();
		return p.verdi;
			
	}
	
	public int maksFjernAlle()
	{
		if(antall == 0) return 0;
		
		Node<T> p = rot, q= null,r = null;
		int antallMaks = 0;
		
		if(p.høyre== null && p.venstre==null)
		{
			nullstill();
			return 1;
			//return p.verdi;
		}
		if(maks().equals(rot.verdi)) // rot er minst
		{
			if(p.venstre!=null) 
			{
				r = p.venstre;
				antallEttBarn--;
			}
			rot = r;
			antallMaks++;
			if(p.høyre!=null) // hvis det er flere maksnoder
				antallMaks+= fjernFler(p);
		}
		else // første maks ligger ikke i rot
		{
			while(p.høyre!=null)
			{
				if(p.verdi.equals(maks())) break;
				q = p;
				p = p.høyre;
			}
			if(p.venstre == null) // har ingen gren å ta vare på
			{
				if(q.venstre == null)
				{
					antallEttBarn--;
					antallIngenBarn++;
				}
				else
				{
					antallToBarn--;
					antallEttBarn++;
				}
				antallIngenBarn--;
				q.høyre = null;
			}
			else // p.venstre finnes, og må tas vare på
			{
				r = p.venstre;
				q.høyre = r;
				antallEttBarn--;
			}
			antallMaks++;
			if(p.høyre!=null) // hvis det er flere maksnoder
				antallMaks+= fjernFler(p);
		}
		
		antall-=antallMaks;
		endringer++;
		return antallMaks;
	}
	public int fjernFler(Node<T> p) // blir kalt i tilfeller hvor maks forekommer flere ganger
	{								// utfører ikke sletting, men holder styr på variablene for de  
		int i = 0;					// nodene som kommer etter første maks
		while(p.høyre != null)
		{
			i++;
			antallEttBarn--;
			p=p.høyre;
		}
		antallEttBarn++;
		antallIngenBarn--;
		return i;
	}
	
	public String høyreGren() 
	{
		StringBuilder s = new StringBuilder();
		s.append('[');
		
		Node<T> p = rot;
		
		while(p != null)
		{
			if(p==rot) 
				s.append(p.verdi);
			else
				s.append(',').append(' ').append(p.verdi);
			
			if(p.høyre == null && p.venstre == null)
				break;
			if(p.høyre != null)
				p = p.høyre;
			else
				p = p.venstre;
		}
		s.append(']');
		return s.toString();
	}
	
	public String preOrden()
	{
		Node<T> p = rot;
		Stakk<Node<T>> s = new TabellStakk<>();
		s.leggInn(null);
		StringBuilder ss = new StringBuilder();
		ss.append('[');/*.append(p.verdi);
		if(p.venstre!=null)
		{
			if(p.høyre!=null) s.leggInn(p);
			p = p.venstre;
		}
		else
			p= p.høyre;*/
		while(p!=null)
		{
			ss.append(',').append(' ').append(p.verdi);
			if(p.venstre!=null)
			{
				if(p.høyre!=null) s.leggInn(p.høyre);
				p = p.venstre;
			}
			else if(p.høyre!=null)
				p= p.høyre;
			else 
				p = s.taUt();
		}
		ss.append(']');
		return ss.toString();
	}
	public String inOrden()
	{
		Node<T> p = rot;
		Stakk<Node<T>> s = new TabellStakk<>();
		s.leggInn(null);
		StringBuilder ss = new StringBuilder();
		ss.append('[');
		
		while(p.venstre!=null){
			s.leggInn(p);
			p = p.venstre;
		}
		while(p!=null)
		{
			ss.append(',').append(' ').append(p.verdi);
			
			if(p.høyre!= null)
			{
				p = p.høyre;
				while(p.venstre!= null){
					s.leggInn(p);
					p = p.venstre;
				}
			}
			else
				p = s.taUt();
		}
		ss.append(']');
		return ss.toString();
	}
	public String omvendtString()
	{
		//if(rot == null) throw new NullPointerException("Treet er tomt!");
		
		StringBuilder s = new StringBuilder();
		s.append('[');
		
		if(rot!=null)
		{
			Stakk<Node<T>> stakk = new TabellStakk<>();
			stakk.leggInn(null);
			
			Node<T> p = rot; //starter i rot
			for( ; p.høyre != null; p = p.høyre) stakk.leggInn(p); // beveger seg til venstre i treet
			s.append(p.verdi);
			
			while(p != null)
			{
				if(p.venstre != null)
				{
					for(p = p.venstre; p.høyre != null; p = p.høyre)
						stakk.leggInn(p);
				}
				else p = stakk.taUt();
				
				if(p!=null) s.append(',').append(' ').append(p.verdi);
			}  
		}
		s.append(']');
		return s.toString();
	}
	
	public String[] grener()
	{
		String[] grener = new String[antallIngenBarn];  
	    for(int j = 0 ; j<grener.length;j++) grener[j]="[";
	    int i = 0;
	    
		Stakk<Node<T>> s = new TabellStakk<>();
	    s.leggInn(null);    // null på bunnen av stakken

	    Node<T> p = rot;    // starter i roten

	    while (p != null)
	    {
	    	if(p!=rot) grener[i]+= ", "+p.verdi;
	    	else grener[i]+=p.verdi;
	      if (p.venstre != null)
	      {
	        if (p.høyre != null)
	        {
	        	s.leggInn(p.høyre);
	        }
	        p = p.venstre;
	      }
	      else if (p.høyre != null)  // nå er p.venstre lik null
	      {
	        p = p.høyre;
	      }
	      else  // nå er både p.venstre og p.høyre lik null
	      {
	    	  i++;
	    	  p = s.taUt(); 
	    	  if(i<grener.length) grener[i]+=finnForeldre(p);
	    	   // ferdig når null tas ut
	      }
	    }
	    for(int k = 0; k<grener.length;k++) grener[k]+="]";
	    return grener;
	}
	//Metode som skal legge til "alle foreldrenodene" til noden som blir sendt med.
	public String finnForeldre(Node<T> q)
	{
		StringBuilder s = new StringBuilder();
		Node<T> p = rot;
		
		while (p != null)            // leter etter verdi
		{
			int cmp = comp.compare(q.verdi,p.verdi);       // sammenligner
			
			if(cmp==0)
			{
				if(p.equals(q))
					break;
				if(p==rot) 
					s.append(p.verdi);
				else
					s.append(',').append(' ').append(p.verdi);
				p = p.høyre; // hvis verdier var linke, men ikke rett node
			}
			if(p==rot) 
				s.append(p.verdi);
			else
				s.append(',').append(' ').append(p.verdi);
			if (cmp > 0) 
			{ 
				p = p.høyre;
			}      // går til venstre
			else if (cmp < 0) 
			{ 
				p = p.venstre; 
			}   // går til høyre
		}
		
		return s.toString();
	}
	
	private class BladnodeIterator implements Iterator<T>
	{
		private Stakk<Node<T>> s = new TabellStakk<>();  // for traversering
		private Node<T> p = null;                        // nodepeker
		private int iteratorendringer;                   // iteratorendringer
		
		private Node<T> tilBlad(Node<T> q)   // en hjelpemetode
		{
			while(q != null)
			{
				if(q.venstre== null && q.høyre == null)
					break;
				if(q.venstre != null)
				{
					if(q.høyre != null)
						s.leggInn(q.høyre);
					q = q.venstre;
				}
				else //(q.høyre != null)
					q = q.høyre;
			}
			return q;
		}
	
		public BladnodeIterator()  // konstruktør
		{
			if (rot == null) return;         // treet er tomt
			p = tilBlad(rot);                  // bruker hjelpemetoden
			iteratorendringer = endringer;   // setter treets endringer
		}
		public boolean hasNext()
		{
			return p != null;
		}
		
		public T next()
		{
			if (iteratorendringer != endringer)
				throw new ConcurrentModificationException();
				
			if (!hasNext()) throw new NoSuchElementException();
			
			T verdi = p.verdi;               // tar vare på verdien i noden p
			
			if(!s.tom()) p = tilBlad(s.taUt()); // så lenge stakk ikke er tom finner jeg bladnoden til 
			else								// verdien jeg henter ut. Bruker postorden, men 
				p = null;						// hopper over alle noder som har "barn"
			return verdi;  
		}
		
		public void remove()
		{ 
			throw new UnsupportedOperationException();
		}
	
	}  // BladnodeIterator
	
	public Iterator<T> bladnodeiterator()
	{
		return new BladnodeIterator();
	}
	
	} // SBinTre2