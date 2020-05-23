import java.io.Serializable;

public class Genre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6473509151408313083L;
	private static int nb = 0;
	private int id;
	private String nom;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genre [nom=" + nom + ", isActive=" + isActive + "]";
	}

	private boolean isActive;
	
	Genre(String n, boolean is){
		this.id = nb;
		this.nom = n;
		this.isActive = is;
		Genre.nb = Genre.nb +1;
	}
	
	public void setNom(String n) {
		this.nom = n;
	}
	
	public void setIsActive(boolean n) {
		this.isActive = n;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}
}
