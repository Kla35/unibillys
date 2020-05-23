import java.io.Serializable;

public class Lien implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5640164964053787931L;
	private Livre l;
	private Emprunteur e;
	
	Lien(Livre li, Emprunteur empr){
		this.l = li;
		this.e = empr;
	}
	
	public Livre getLivre(){
		return l;
	}
	
	public Emprunteur getEmpr() {
		return e;
	}
}
