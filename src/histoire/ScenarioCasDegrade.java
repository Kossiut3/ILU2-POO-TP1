package histoire;


import personnages.Gaulois;
import villagegaulois.Etal;


public class ScenarioCasDegrade {
	
	
	public static void main(String[] args) {
		Gaulois kossi = new Gaulois("kossi",10);
		Etal etal = new Etal();
		etal.occuperEtal(kossi, "fleur", 10);

		System.out.println("Fin du test");
		}

}
