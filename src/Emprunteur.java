import java.io.Serializable;
import java.util.ArrayList;

public class Emprunteur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565354091941434477L;
	public static int nb = -1;
	private int id;
	private String nom;
	private String prenom;
	private ArrayList<Livre> emprunt = new ArrayList<Livre>();
	
	public Emprunteur(String nom, String prenom) {
		Emprunteur.nb = Emprunteur.nb + 1;
		this.id = Emprunteur.nb;
		this.nom = nom;
		this.prenom = prenom;
	}

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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the emprunt
	 */
	public ArrayList<Livre> getEmprunt() {
		return emprunt;
	}

	/**
	 * @param emprunt the emprunt to set
	 */
	public void EmpruntLivre(Livre l) {
		l.setEstEmprunte(true);
		this.emprunt.add(l);
		
	}
	
	/**
	 * @param emprunt the emprunt to set
	 */
	public void RetourLivre(Livre l) {
		int i = 0;
		int a = -1;
		boolean t = false;
		l.setEstEmprunte(false);
		while ((i<this.emprunt.size()) && (t == false)) {
			if (l.getId() == this.emprunt.get(i).getId()) {
				t = true;
				a=i;
			} else {
				i++;
			}
		}
		this.emprunt.remove(a);
		System.out.println(this.emprunt);
		for (i=0;i<this.emprunt.size();i++) {
			System.out.println(this.emprunt.get(i).toString());
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Emprunteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", emprunt=" + emprunt + "]";
	}
	
}
