package Oblig3;

import java.util.Comparator;
import java.util.Iterator;

import Oblig2.Komparator;

public class Main {
	public static void main(String[] args)
	{
			Comparator<Integer> c = Komparator.naturlig();
		  SBinTre2<Integer> tre = new SBinTre2(c);

		  int[] a = {8,2,10,1,4,9,12,3,6,11,5,7};
		  for (int k : a) tre.leggInn(k);
		  
		  System.out.println(tre.postOrden());

		  /*Iterator<Integer> i = tre.bladnodeiterator();
		  while(i.hasNext())
		  {
		    System.out.print(i.next() + " ");
		  }*/

		  // Utskrift: 1 3 5 7 9 11
		/*SBinTre2<Character> tre = SBinTre2.lagTre();//= new SBinTre2<>(Komparator.<Integer>naturlig());
		char[] verdier = "GSAHQNRBFCJDOEPKIML".toCharArray();
		for (char c : verdier) tre.leggInn(c);*/

		//String[] s = tre.grener();
		//System.out.println(tre.høyreGren());
		//for (String x : s) System.out.println(x);
		 // Utskrift:
		  // [G, A, B, F, C, D, E]
		  // [G, S, H, Q, N, J, I]
		  // [G, S, H, Q, N, J, K, M, L]
		  // [G, S, H, Q, N, O, P]
		  // [G, S, H, Q, R]
		/*tre.leggInn(6);
		tre.leggInn(1);
		tre.leggInn(8);
		//tre.leggInn(2);
		//tre.leggInn(3);
		tre.leggInn(10);
		tre.leggInn(19);
		tre.leggInn(7);
		tre.leggInn(19);
		//tre.leggInn(1);
		tre.leggInn(19);
		tre.leggInn(19);
		tre.leggInn(18);
		tre.leggInn(17);*/
		
		
		/*System.out.println(tre.antall(1));
		System.out.println(tre.høyde());
		System.out.println(tre.antall());
		System.out.println(tre.antallEttBarn());
		System.out.println(tre.antallIngenBarn());
		System.out.println(tre.antallToBarn());*/
		
		/*System.out.println(tre.nestMin());
		System.out.println(tre.maks());
		System.out.println(tre.nestMaks());*/
		
		//System.out.println(tre.toString());
		//System.out.println(tre.omvendtString());
		//System.out.println(tre.høyreGren());
		//System.out.println("\n" + tre.maksFjernAlle() + "\n");
		//System.out.println(tre.minFjern());
		//System.out.println(tre.toString());
		//System.out.println(tre.omvendtString());
		
//		System.out.println(tre.antallEttBarn());
//		System.out.println(tre.antallIngenBarn());
//		System.out.println(tre.antallToBarn());
		
		//System.out.println(tre.antall(1));
		
		/*SBinTre2<Integer> tre = SBinTre2.lagTre();
		tre.leggInn(10);
		tre.leggInn(5);
		tre.leggInn(15);
		
		System.out.println(tre.høyde());
		//System.out.println(tre.antall());
		System.out.println(tre.antallEttBarn());
		System.out.println(tre.antallIngenBarn());
		System.out.println(tre.antallToBarn());*/
	}

}
