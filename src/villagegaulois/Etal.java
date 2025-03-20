package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}
	
	public int getquantite() {
		return quantite;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		
		etalOccupe = false;
		
		String nomGaulois = "";
		try {
		nomGaulois = vendeur.getNom(); 
		}catch(NullPointerException e) {e.printStackTrace();
		System.out.println("L'etal n as pas été occupé précédemment\n");
		return "";}
		
		StringBuilder chaine = new StringBuilder(
				"Le vendeur " + nomGaulois + " quitte son étal, ");
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append(
					"il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		String nomGaulois = vendeur.getNom();
		
		StringBuilder chaine = new StringBuilder();
		chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
				+ " " + produit + " à " + nomGaulois);
		if (quantite == 0) {
			chaine.append(", malheureusement il n'y en a plus !");
			quantiteAcheter = 0;
		}
		if (quantiteAcheter > quantite) {
			chaine.append(", comme il n'y en a plus que " + quantite + ", "
					+ acheteur.getNom() + " vide l'étal de "
					+ nomGaulois + ".\n");
			quantiteAcheter = quantite;
			quantite = 0;
		}
		if (quantite != 0) {
			quantite -= quantiteAcheter;
			chaine.append(". " + acheteur.getNom()
					+ ", est ravi de tout trouver sur l'étal de "
					+ nomGaulois + "\n");
		}
		return chaine.toString();
		
	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}
	
	
		
	
	

	
	
	
	
	

}
