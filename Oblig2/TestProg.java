package Oblig2;


public class TestProg {
	public static void main(String[] args)
	{
		/*DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();
		
		liste.leggInn("Martin");
		liste.leggInn("Guro");
		liste.leggInn("Knut");
		liste.leggInn("Knut2");
		liste.leggInn("Knut3");
		//liste.leggInn("Knut4");
		//liste.leggInn(0, "per");
		
		System.out.println(liste.toString());
		System.out.println(liste.omvendtString());
		System.out.println(liste.antall());
		
		
		System.out.println(liste.fjern(4));
		System.out.println(liste.fjern(0));
		/*System.out.println(liste.fjern(4));
		System.out.println(liste.fjern(3));
		System.out.println(liste.fjern(2));
		System.out.println(liste.fjern(1));
		System.out.println(liste.fjern(0));*/
		

		/*System.out.println(liste.toString());
		System.out.println(liste.omvendtString());
		System.out.println(liste.antall());*/
		
		int[] a = {4,1,8,6,3,7,9,10,5,2};
		Liste<Integer> liste = new EnkeltLenketListe<>();
		for (int k : a)liste.leggInn(k);
		
		System.out.println(liste);
		int m = DobbeltLenketListe.maks(liste,Komparator.<Integer>naturlig());
		System.out.println(m);
		
	}

}
