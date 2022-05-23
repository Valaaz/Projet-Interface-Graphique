package mvc.modele;

public class GestionOption {
	// thème du dictionnaire
	private String theme;

	// difficulté
	private String difficulte;

	// theme de l'interface (clair ou sombre)
	private boolean modeSombre;

	// taille de la police
	private int taillePolice;

	// apparence du pendu
	private int skinPendu;

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Constructeur
	///////////////////////////////////////////////////////////////////////////////////////////////

	public GestionOption() {
		super();
		this.theme = "Tous les thèmes";
		this.difficulte = "Moyen";
		this.modeSombre = false;
		this.taillePolice = 12;
		this.skinPendu = 0;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Les accesseurs/modifieurs de données membres
	///////////////////////////////////////////////////////////////////////////////////////////////

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(String difficulte) {
		this.difficulte = difficulte;
	}

	public boolean isModeSombre() {
		return modeSombre;
	}

	public void setModeSombre(boolean modeSombre) {
		this.modeSombre = modeSombre;
	}

	public int getTaillePolice() {
		return taillePolice;
	}

	public void setTaillePolice(int taillePolice) {
		this.taillePolice = taillePolice;
	}

	public int getSkinPendu() {
		return skinPendu;
	}

	public void setSkinPendu(int skinPendu) {
		this.skinPendu = skinPendu;
	}

	@Override
	public String toString() {
		return "GestionOption [theme=" + theme + ", difficulte=" + difficulte + ", modeSombre=" + modeSombre
				+ ", taillePolice=" + taillePolice + ", skinPendu=" + skinPendu + "]";
	}

}
