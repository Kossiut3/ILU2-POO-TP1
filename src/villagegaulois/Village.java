package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marcheVillage;
	private int nombreEtals; 
	

	public Village(String nom, int nbVillageoisMaximum, int nbetal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marcheVillage = new Marche(nbetal);
		this.nombreEtals = nbetal;
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		
		return chaine.toString();
	}

	private static class Marche {
		private Etal[] etals;

		public Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for (int i=0; i<etals.length;i++) {
				if (!etals[i].isEtalOccupe()) return i;
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			Etal[] etalLibre;
			int indice = 0;
			int nbEtalsLibre = 0;
			for(int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) nbEtalsLibre += 1;
			}
			if (nbEtalsLibre == 0) {
				etalLibre = null;
				return etalLibre;
			}
			else {
				etalLibre = new Etal[nbEtalsLibre];
				for (int j=0;j<etals.length;j++) {
					if (etals[j].isEtalOccupe() && etals[j].contientProduit(produit)) {
						etalLibre[indice] = etals[j];
						indice++;
						}
				}
				return etalLibre;
			}
		} 
		
		private Etal trouverVendeur(Gaulois gaulois) {
			
			Etal etalDuVendeur;
			for(int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe() ) {
					Gaulois vendeur = etals[i].getVendeur();
					if(vendeur != null && vendeur.equals(gaulois)) {
						etalDuVendeur = etals[i];
						return etalDuVendeur;
					}
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			int nbEtalLibre = 0 ; 
			StringBuilder affichage = new StringBuilder();
			for (int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe()) {
					affichage.append(etals[i].afficherEtal());
				}
				else {
					nbEtalLibre++;
				}
			}
			if( nbEtalLibre == 0) {
				return affichage.toString();
			}
			else {
				affichage.append ("Il reste " + nbEtalLibre + " �tals non utilis�s dans le march�.\n");
				return affichage.toString();
				
			}
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()  + " cherche un endroit pour vendre "+ nbProduit + " "+ produit);
		int numEtalLibre = marcheVillage.trouverEtalLibre();
		if (numEtalLibre == -1) {return "Pas d'etal vide dans le marché\n";}
		Etal etalLibre = marcheVillage.etals[numEtalLibre];
		etalLibre.occuperEtal(vendeur, produit, nbProduit);
		
		
		int numEtalAff = numEtalLibre+1;
		chaine.append("\nle vendeur " + vendeur.getNom() + " vend des " + produit + " a l etal " + numEtalAff +"\n" );
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
	    
	    Etal[] etalProduit = marcheVillage.trouverEtals(produit);
	    if (etalProduit != null) {
	    	chaine.append("Recherche des vendeurs de " + produit + "\n");
	    	for (int i=0;i<etalProduit.length;i++) {
	    		Gaulois nomVendeur = etalProduit[i].getVendeur();
	    		chaine.append("- "+nomVendeur.getNom() + "\n");
	    	}
	    }
	    else {
	    	chaine.append("Auncun vendeur ne propose des " + produit +" au marché\n");
	    }

	    return chaine.toString();
		
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		Etal etalGaulois = new Etal() ;
		boolean arretBoucle = true;
		for (int i=0; i<nombreEtals && arretBoucle;i++) {
			 Etal etalCourant = marcheVillage.etals[i];
			 if (etalCourant.isEtalOccupe() && etalCourant.getVendeur() == vendeur) {
				 etalGaulois = etalCourant;
				 arretBoucle = false;
			 }
		 } 
		return etalGaulois;
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		boolean arretBoucle = true;
		for (int i=0; i<nombreEtals && arretBoucle;i++) {
			 Etal etalCourant = marcheVillage.etals[i];
			 if (etalCourant.isEtalOccupe() && etalCourant.getVendeur() == vendeur) {
				 chaine.append(etalCourant.libererEtal());
				 arretBoucle = false;
			}
		 }
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		int nbetalLibre = 0;
		chaine.append("Le marche du  village des ' " + nom + " ' contient plusieurs etals:\n");
		for (int i=0;i<nombreEtals;i++) {
			 Etal etalCourant = marcheVillage.etals[i];
			 if (etalCourant.isEtalOccupe()) {
				 Gaulois nomVendeur = etalCourant.getVendeur();
				 chaine.append(nomVendeur.getNom() + " vend " + etalCourant.getquantite() +" produits\n");
			 }
			 else nbetalLibre++;
		 }
		chaine.append("Il reste " + nbetalLibre+ " etal(s) libre dans le marche\n");
		return chaine.toString();
	}
	
	 
	
	
	
	
	
}
