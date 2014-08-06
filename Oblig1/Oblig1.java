/****************
Martin Hagen - s188098 - 2AA
Joakim Rishaug - s188080 - 2AA
Sondre Sparby Boge - s181130 - 2AA
Marius Maudal - s177654 - 3AB
Lars-Erik Kasin - s178816 - 2AA
******************/
package Oblig1;

import java.util.NoSuchElementException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class Oblig1 
{
	public static void bytt(int[] a, int value1, int value2) {
		int holder = a[value1];
		a[value1] = a[value2];
		a[value2] = holder;
	}
	//oppgave 1
	// hvis tabellen har lengde n, ender vi opp med n-1 sammenligninger pluss
	// bytteoperasjoner. Metoden er derfor av orden n.
	public static int maks(int[] a) {
		if (a.length <= 0) {
			throw new NoSuchElementException(
					"The Array doesn't have enough values for a comparison.");
		}

		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				bytt(a, i, i + 1);
			}
		}
		// returnerer h�yeste verdi i arrayen som vil v�re p� siste plass.
		return a[a.length - 1];
	}
	//oppgave 2
	// for n tall, vil metoden ha summen av n til 0: n-1 sammenligninger
	public static void sortering(int[] a) {
		int counter = a.length;

		// arrayen har 0-1 elementer og er sortert fra f�r.
		if (counter <= 1)
			return;
		do {
			for (int i = 0; i < counter - 1; i++) {
				if (a[i] > a[i + 1]) {
					bytt(a, i, i + 1);
				}
			}
			counter--;
		} while (counter > 1);
	}
	
	//oppgave 5
	public static void rotasjon(char[] a)
	{
		if(a.length==0)return; 
		int siste = a.length-1; // index for siste posisjon
		char verdi = a[siste]; //  lagrer siste verdi
		
		for(int i = siste; i > 0; i--)// trekker verdiene en bakover
		{
			a[i] = a[i-1];
		}
		a[0] = verdi; // legger siste f�rst
	}
	//oppgave 6
	public static void rotasjon(char[] a, int k)
	{
		if(a.length==0)return; 
		k = k%a.length;
		if(k==0)return;
		
		k = (k>0) ? k : (k+a.length);
		
		char[] temp = new char[k];
		int p = 0;
		for(int i = a.length-k;i < a.length; i++)
		{
			temp[p++] = a[i]; 
		}
		for(int i =a.length-1;i > k-1; i--)
		{
			a[i] = a[i-k];
		}
		p=0;
		for(int i = 0;i < k; i++)
		{
			a[i] = temp[p++]; 
		}
	}
	// Oppgave 7
	public static String toString(int[] a, char v, char h, String mellomrom) {
	        
	        StringBuilder result = new StringBuilder();
	        int length = a.length;
	        
	        result.append(v);
	        for (int i = 0; i < length; i++) {
	            
	            result.append(a[i]);
	            
	            if (i == length - 1)
	                break;
	            
	            result.append(mellomrom);
	        }
	        result.append(h);
	        return result.toString();
	    }


		// Oppgave 8a
		public static int[] tredjeMinst(int[] a)
		{
			int n = a.length;
			if (a.length < 3)
				throw new IllegalArgumentException("a.lenght(" + n + ") < 3!");

			int m = 0;  //    minst
			int nm = 1; // 2. minst
			int tm = 2; // 3. minst

			// sorterer de 3 f�rste verdiene
			if (a[m] > a[nm])
			{
				m = 1;
				nm = 0;
			}
			if (a[nm] > a[tm])
			{
				int h = nm;
				nm = tm;
				tm = h;
				if (a[m] > a[nm])
				{
					int h2 = m;
					m = nm;
					nm = h2;
				}
			}
			
			int minst = a[m];
			int nestminst = a[nm];
			int tredjeminst = a[tm];

			for (int i = 3; i < n; i++)
			{
				if (a[i] < tredjeminst)
				{
					if (a[i] < nestminst)
					{
						tm = nm;
						tredjeminst = nestminst;

						if (a[i] < minst) // ny 1., 2. og 3. minst
						{
							nm = m;
							nestminst = minst;

							m = i;
							minst = a[m];
						} 
						else // ny 2. og 3. minst
						{
							nm = i;
							nestminst = a[nm];
						}
					} 
					else // bare ny 3. minst
					{
						tm = i;
						tredjeminst = a[tm];
					}
				}
			}

			return new int[] { m, nm, tm };
		}

		// Oppgave 8b
		public static void tredjeMinstTest()
		{
			int[] a = {1,2,3}; // 0,1,2 posisjon fra minst til tredjeminst
			int[] b = {1,3,2}; // 0,2,1
			int[] c = {2,1,3}; // 1,0,2
			int[] d = {2,3,1}; // 2,0,1
			int[] e = {3,1,2}; // 1,2,0
			int[] f = {3,2,1}; // 2,1,0

			if(!sammenlignTabell(tredjeMinst(a),new int[]{0,1,2})) // 0,1,2
				System.out.println("Feil posisjon for de tre minste-verdiene i tilfelle a!");
			if(!sammenlignTabell(tredjeMinst(b),new int[]{0,2,1})) // 0,2,1
				System.out.println("Feil posisjon for de tre minste-verdiene i tilfelle b!");
			if(!sammenlignTabell(tredjeMinst(c),new int[]{1,0,2})) // 1,0,2
				System.out.println("Feil posisjon for de tre minste-verdiene i tilfelle c!");
			if(!sammenlignTabell(tredjeMinst(d),new int[]{2,0,1})) // 2,0,1
				System.out.println("Feil posisjon for de tre minste-verdiene i tilfelle d!");
			if(!sammenlignTabell(tredjeMinst(e),new int[]{1,2,0})) // 1,2,0
				System.out.println("Feil posisjon for de tre minste-verdiene i tilfelle e!");
			if(!sammenlignTabell(tredjeMinst(f),new int[]{2,1,0})) // 2,1,0
				System.out.println("Feil posisjon for de tre minste-verdiene i tilfelle f!");
			
			a = new int[0]; // en tom tabell, lengde lik 0
			boolean unntak = false;

			try
			{
				tredjeMinst(a);
			} 
			catch (Exception exception)
			{
				unntak = true;
				if (!(exception instanceof java.util.NoSuchElementException))
					System.out.println("Feil-unntak for tom tabell!");
			}

			if (!unntak)
				System.out.println("Unntak skal kastes for tom tabell!");
		}
		
		// brukes av tredjeMinstTest() - oppg8b
		public static boolean sammenlignTabell(int[] a, int[] b)
		{
			if(a.length != b.length)
				return false;
			
			for(int i = 0; i < a.length; i++)
				if(a[i] != b[i]) return false;
			
			return true;
		}
		
		// Oppgave 9
		public static int[] kMinst(int[] a, int k)
		{
			int n = a.length;
			if(n < k) 
				throw new IllegalArgumentException("k > a.length(" + n + ")");
			if(k < 1) 
				throw new IllegalArgumentException("k < 1!");
			
			int[] verdier = new int[k];
			
			for(int i = 0; i < k; i++)
				verdier[i] = a[i];
			
			sortering(verdier); // fra oppg 2
			
			int storsteK = verdier[k-1];
			for(int i = k; i < n; i++) // s�ker for � finne nye k-verdier
			{
				if(a[i] < storsteK) // en ny k-verdi
				{
					int ny = a[i];
					for(int j = 0; j < k; j++) // finner ny plassering
					{
						if(verdier[j] > ny) // fant plassering
						{
							int forrige = verdier[j];
							verdier[j] = ny;
							for(int f = j; f < k - 1; f++) // forskyver tabellen utover
							{
								int hjelp = forrige;
								forrige = verdier[f+1];
								verdier[f+1] = hjelp;
							}
							storsteK = verdier[k-1];
							break;
						}
					}
				}
			}
			
			return verdier;
		}

	// Oppgave 10
	public static int[] bokstavfrekvens(String url) throws IOException
	    {
	        String encoding = "iso-8859-1";
	        int[] letterFrequency = new int[29];
	        
	        InputStream inn =
	            new BufferedInputStream(
	                (new URL(url)).openStream());
	     
	        java.util.Scanner s = new java.util.Scanner(inn,encoding).useDelimiter("\\A"); // "\A" matches beginning of input
	        String input = s.hasNext() ? s.next() : "";
	        inn.close();
	        input = input.toLowerCase();
	        
	        // Parse and add to array
	        for(int i = 0; i < input.length(); i++)
	        {
	            char c = input.charAt(i); // Get character
	            
	            // a-z = 97 - 122
	            // �-� = 230,248,229
	            if(c >= 97 && c <= 122)
	            {
	                letterFrequency[c - 97]++; // Increment at alphabetized index
	            }
	            else
	            {
	                switch (c){
	                    case 230:           // �
	                        letterFrequency[26]++;
	                        break;
	                    case 248:           // �
	                        letterFrequency[27]++;
	                        break;
	                    case 229:           // �
	                        letterFrequency[28]++;
	                        break;
	                    default:
	                        break;
	                }
	            } 
	        }
	        return letterFrequency;
	    }

}
