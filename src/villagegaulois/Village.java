package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
				if (!etals[i].isEtalOccupe()) nbEtalsLibre += 1;
			}
			if (nbEtalsLibre == 0) {
				etalLibre = new Etal[0];
				return etalLibre;
			}
			else {
				etalLibre = new Etal[nbEtalsLibre];
				for (int j=0;j<etals.length;j++) {
					if (!etals[j].isEtalOccupe()) {
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
				if (etals[i].isEtalOccupe() && etals[i].getVendeur().equals(gaulois)) {
					etalDuVendeur = etals[i];
					return etalDuVendeur;
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			int nbEtalLibre = 0 ; 
			String affichage= "";
			for (int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe()) {
					affichage += etals[i].afficherEtal();
				}
				else {
					nbEtalLibre++;
				}
			}
			if( nbEtalLibre == 0) {
				return affichage;
			}
			else {
				affichage = affichage + "Il reste " + nbEtalLibre + " �tals non utilis�s dans le march�.\n";
				return affichage;
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
