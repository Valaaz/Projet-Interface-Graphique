package mvc.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GestionJeu {

	// mot à deviner
	private String motMystere;

	// ensemble des mots à deviner
	private ArrayList<String> dico;

	// ensemble des lettres déjà données par le joueur
	private String lettresDejaDonnees;

	// nombre maximum d'erreurs autorisées
	private int nbMaxErreurs;

	// nombre de lettres déjà trouvées par le joueur
	private int nbLettresTrouvees;

	// nombre d'erreurs déjà commises par le joueur
	private int nbErreurs;

	// générateur de nombre aléatoire
	private Random alea;

	// Nom du joueur
	private String nomJoueur;

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Constructeur
	///////////////////////////////////////////////////////////////////////////////////////////////
	public GestionJeu(String nomDico) throws IOException {
		this.nomJoueur = "";
		this.nbMaxErreurs = 5;
		this.nbErreurs = 0;
		this.nbLettresTrouvees = 0;
		lettresDejaDonnees = new String();
		this.alea = new Random();
		this.motMystere = new String();
		this.dico = new ArrayList<String>();
		ConstruireDico(nomDico);

	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Les accesseurs/modifieurs de données membres
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @return the nomJoueur
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}

	/**
	 * @param nomJoueur the nomJoueur to set
	 */
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	/**
	 * @return the motMystere
	 */
	public String getMotMystere() {
		return motMystere;
	}

	/**
	 * @param motMystere the motMystere to set
	 */
	public void setMotMystere(String motMystere) {
		this.motMystere = motMystere;
	}

	/**
	 * @return the lettresDejaDonnees
	 */
	public String getLettresDejaDonnees() {
		return lettresDejaDonnees;
	}

	/**
	 * @param lettresDejaDonnees the lettresDejaDonnees to set
	 */
	public void setLettresDejaDonnees(String lettresDejaDonnees) {
		this.lettresDejaDonnees = lettresDejaDonnees;
	}

	/**
	 * @return the nbMaxErreurs
	 */
	public int getNbMaxErreurs() {
		return nbMaxErreurs;
	}

	/**
	 * @param nbMaxErreurs the nbMaxErreurs to set
	 */
	public void setNbMaxErreurs(int nbMaxErreurs) {
		this.nbMaxErreurs = nbMaxErreurs;
	}

	/**
	 * @return the nbLettresTrouvees
	 */
	public int getNbLettresTrouvees() {
		return nbLettresTrouvees;
	}

	/**
	 * @param nbLettresTrouvees the nbLettresTrouvees to set
	 */
	public void setNbLettresTrouvees(int nbLettresTrouvees) {
		this.nbLettresTrouvees = nbLettresTrouvees;
	}

	/**
	 * @return the nbErreurs
	 */
	public int getNbErreurs() {
		return nbErreurs;
	}

	/**
	 * @param nbErreurs the nbErreurs to set
	 */
	public void setNbErreurs(int nbErreurs) {
		this.nbErreurs = nbErreurs;
	}

	/**
	 * @return the dico
	 */
	public ArrayList<String> getDico() {
		return dico;
	}

	/**
	 * @param dico the dico to set
	 */
	public void setDico(ArrayList<String> dico) {
		this.dico = dico;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// modification de données du type gestion
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @param c : nouvelle lettre choisie par le joueur. Elle sera stockée avec les
	 *          lettres déja données
	 */
	public void MemoriserLettreChoisie(char c) {
		if (this.lettresDejaDonnees.indexOf(c) == -1)
			this.lettresDejaDonnees += c;
	}

	/**
	 * Une erreur a été faite. Cette fonction met à jour le nombre d'erreurs
	 * commises
	 */
	public void MAJNbErreurs() {
		this.nbErreurs++;
	}

	/**
	 * Cette méthode crée l'ensemble des mots à deviner en consultant un fichier
	 * contenant une liste de mots
	 * 
	 * @param nomFichierDico : nom du fichier stockant les mots du dictionnaire à
	 *                       utiliser pour le jeu
	 * @throws IOException
	 */
	public void ConstruireDico(String nomFichierDico) throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(nomFichierDico));
		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture");
		}
		while ((ligne = lecteurAvecBuffer.readLine()) != null)
			this.dico.add(ligne);
		lecteurAvecBuffer.close();
	}

	/**
	 * remplace le dictionnaire actuel avec les mots qui se trouvent dans le fichier
	 * donné
	 * 
	 * @param nomFichierDico : nom du noueu dictionnaire
	 * @throws IOException
	 */
	public void ChangerDico(String nomFichierDico) throws IOException {
		this.dico.clear();
		this.ConstruireDico(nomFichierDico);
	}

	/**
	 * choisit le mot à deviner dans l'ensemble des mot à deviner
	 */
	public void ChoixMotMystere() {
		this.motMystere = this.dico.get(alea.nextInt(this.dico.size()));

	}

	/**
	 * initialise les données pour une partie du jeu
	 */
	public void InitialiserPartie() {
		ChoixMotMystere();
		this.nbLettresTrouvees = 0;
		this.nbErreurs = 0;
		this.lettresDejaDonnees = "";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// tests et recherches sur des données du type gestion
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @return indique si le joueur a deviné toutes les lettres du mot à deviner
	 */
	public boolean ToutTrouve() {
		return (this.nbLettresTrouvees == this.motMystere.length());
	}

	/**
	 * 
	 * @return indique si le joueur a commis une erreur de trop
	 */
	public boolean MaxErreursDepasse() {
		return (this.nbErreurs > this.nbMaxErreurs);
	}

	/**
	 * @param c : caractère à tester
	 * @return indique si c est bien une lettre de l'alphabet
	 */
	public boolean CaractereAutorise(String c) {
		return ((c.length() == 1) && Character.isLetter(c.charAt(0)));
	}

	/**
	 * @param mot : mot à tester
	 * @return indique si mot est le mot à deviner
	 */
	public boolean EstMotMystere(String mot) {
		return (mot.compareToIgnoreCase(this.motMystere) == 0);
	}

	/**
	 * @param car : lettre à chercher dans le mot
	 * @param pos : ensemble des positions où se trouve la lettre
	 * @return donne le nombre de positions de la lettre dans le mot
	 */
	public int ChercherLettreDansMot(char car, Vector<Integer> pos) {

		int index = this.motMystere.indexOf(car);
		int nbpos = 0;

		while (index >= 0) {
			nbpos++;
			this.nbLettresTrouvees++;
			pos.add(index);
			index = this.motMystere.indexOf(car, index + 1);
		}
		return nbpos;
	}

	public void AffErreurs() {
		System.out.println("Nb erreurs max=" + getNbMaxErreurs());
	}

}
