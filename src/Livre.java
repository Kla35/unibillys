import java.io.Serializable;
import java.util.ArrayList;

public class Livre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5776820976484327303L;
	public static int nb = -1;
	private int id;
	private String isbn;
	private String titre;
	private String auteur;
	private String editeur;
	private String resume;
	private String type;
	private ArrayList<Genre> genre =  new ArrayList<Genre>();
	private boolean EstEmprunte;
	private Emprunteur emprunteur;
	
	public Livre() {
		
	}
	
	public Livre(String isbn, String titre, String auteur,String editeur, String resume, String type, ArrayList<Genre> genre) {
		Livre.nb = Livre.nb + 1;
		this.id = Livre.nb;
		this.isbn = isbn;
		this.titre = titre;
		this.auteur = auteur;
		this.setEditeur(editeur);
		this.resume = resume;
		this.type = type;
		this.genre = genre;
		this.EstEmprunte = false;
		this.emprunteur = null;
	}
	
	//GETTER AND SETTER
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}
	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}
	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the genre
	 */
	public ArrayList<Genre> getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(ArrayList<Genre> genre) {
		this.genre = genre;
	}

	/**
	 * @return the estEmprunte
	 */
	public boolean isEstEmprunte() {
		return EstEmprunte;
	}

	/**
	 * @param estEmprunte the estEmprunte to set
	 */
	public void setEstEmprunte(boolean estEmprunte) {
		EstEmprunte = estEmprunte;
	}

	/**
	 * @return the emprunteur
	 */
	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	/**
	 * @param emprunteur the emprunteur to set
	 */
	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
	}
	
	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the resume
	 */
	public String getResume() {
		return resume;
	}

	/**
	 * @param resume the resume to set
	 */
	public void setResume(String resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", isbn=" + isbn + ", titre=" + titre + ", auteur=" + auteur + ", editeur=" + editeur
				+ ", resume=" + resume + ", type=" + type + ", genre=" + genre + ", EstEmprunte=" + EstEmprunte
				+ ", emprunteur=" + emprunteur + "]";
	}

	
}
