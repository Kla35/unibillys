import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	static private Fenetre fenetre;
	
	static int lock = 0;
	
	static private RechercheAvancee frecherche;
	static private AfficherLivre fafflivre;
	static private AjouterLivre fajoutlivre;
	static private ModifierLivre fmodlivre;
	static private ListeEmprunt flisteempr;
	static private ListeEmpruntEmprunteur flisteempr2;
	static private AjouterEmprunteur femprunteur;
	static private ModifierEmprunteur fmodemprunteur;
	static private CreerEmprunt fajemprunt;
	static private AjouterGenre fajgenre;
	static private AjouterGenre2 fajgenre2;
	static private ModifierGenre fmodgenre;
	static private ModifierGenre2 fmodgenre2;
	
	static public String windowcolor = "#048b9a";
	static public String version_local = "";
	
	static ObservableList<Livre> list = FXCollections.observableArrayList();
	static ObservableList<Livre> list2 = FXCollections.observableArrayList();
	static ObservableList<Emprunteur> listempr = FXCollections.observableArrayList();
	static ObservableList<Lien> listemprunt = FXCollections.observableArrayList();
	static ObservableList<Genre> listgenre = FXCollections.observableArrayList();
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*ArrayList<String> array = new ArrayList<String>();
		Livre a = new Livre("Ahahah", "Barn", "Michel Lafon", "Livre", array);
		Emprunteur emprunteur = new Emprunteur("Bono","Michel");
		System.out.println(a.toString());
		emprunteur.EmpruntLivre(a);
		System.out.println(emprunteur.toString());
		emprunteur.RetourLivre(a);
		System.out.println(emprunteur.toString());
		System.out.println(a.toString());*/
		//9782290017739
		/*JsonReader test = new JsonReader("9781730927829");
		System.out.println("Titre : " + test.getTitle());
		System.out.println("Auteur : " + test.getAuthor());
		System.out.println("Editeur : " + test.getEditor());
		System.out.println("Description : " + test.getDescription());*/		
		
		Application.launch(args);
		
		/*Gestion.loadLivre();
		for(int i=0;i<Main.list.size();i++) {
			System.out.println(Main.list.get(i));
			Livre l = new Livre("000",Main.list.get(i).getTitre(),Main.list.get(i).getAuteur(), Main.list.get(i).getEditeur(), "X" ,Main.list.get(i).getType(), Main.list.get(i).getGenre());
			Main.list2.add(l);
		}
		Gestion.saveLivre();
		System.exit(1);*/
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Gestion.loadLock();
		if (Main.lock == 0) {
			Main.lock = 1;
		} else {
			System.exit(1);
		}
		/*Main.version_local = Gestion.verifyVersionLocal();
		String version_serveur = Gestion.verifyVersionServer();
		if(!version_serveur.equals("") && !Main.version_local.equals("")) {
			if(Main.version_local.equals(version_serveur)) {
				Gestion.saveLock();
				Gestion.loadLivre();
				Gestion.loadEmprunteur();
				Gestion.loadEmprunt();
				Gestion.loadGenre();
				fenetre = new Fenetre();
				Main.fenetre.show();
				System.out.println("Version identique");
			} else {
				System.out.println("MAJ A LANCER");
				String[] cmd = {"javaw", "-jar", "UpdateBilly.jar"};
				Runtime r = Runtime.getRuntime();
				@SuppressWarnings("unused")
				Process p = r.exec(cmd);
				System.exit(1);
				
			}
		} else {
			Gestion.saveLock();
			Gestion.loadLivre();
			Gestion.loadEmprunteur();
			Gestion.loadEmprunt();
			Gestion.loadGenre();
			fenetre = new Fenetre();
			Main.fenetre.show();
			System.out.println("Imposible à vérifier");
		}*/
		
		Gestion.saveLock();
		Gestion.loadLivre();
		Gestion.loadEmprunteur();
		Gestion.loadEmprunt();
		Gestion.loadGenre();
		fenetre = new Fenetre();
		Main.fenetre.show();
		
		 
		// TODO Auto-generated method stub
		
	}
	
	static public void ouvrirRechercheAvancee(){
		frecherche = new RechercheAvancee();
		frecherche.initOwner(Main.fenetre);
		frecherche.initModality(Modality.WINDOW_MODAL);
		frecherche.show();
	}
	
	static public void ouvrirAfficherLivre(Livre l){
		fafflivre = new AfficherLivre(l);
		fafflivre.initOwner(Main.fenetre);
		fafflivre.initModality(Modality.WINDOW_MODAL);
		fafflivre.show();
	}
	
	static public void ouvrirAjouterLivre(){
		fajoutlivre	= new AjouterLivre();
		fajoutlivre.initOwner(Main.fenetre);
		fajoutlivre.initModality(Modality.WINDOW_MODAL);
		fajoutlivre.show();
	}
	
	static public void ouvrirModifierLivre(Livre l,int ind){
		fmodlivre = new ModifierLivre(l,ind);
		fmodlivre.initOwner(Main.fenetre);
		fmodlivre.initModality(Modality.WINDOW_MODAL);
		fmodlivre.show();
	}
	
	static public void ouvrirSupprimerLivre(Livre l){
		if (l.isEstEmprunte() == false) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Suppression du livre");
		      alert.setHeaderText("Êtes vous sûr de vouloir supprimer le livre ?");
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		 
		      if (option.get() == ButtonType.OK) {
		         list.remove(l);
		         Fenetre.updateTableLivre();
		         Fenetre.updateBoutons();
		         Gestion.saveLivre();
		         Fenetre.lNbLivre.setText("Nombre\nde livres :\n" + Main.list.size());
		      }
		} else {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Alerte");
	        alert.setHeaderText("Suppression impossible");
	        alert.setContentText("Le livre est en emprunt");
	        alert.showAndWait();
		}
	}	
	
	static public void ouvrirListeEmprunt(){
		flisteempr = new ListeEmprunt();
		flisteempr.initOwner(Main.fenetre);
		flisteempr.initModality(Modality.WINDOW_MODAL);
		flisteempr.show();
	}
	
	static public void ouvrirListeEmpruntEmprunteur(Emprunteur e){
		flisteempr2 = new ListeEmpruntEmprunteur(e);
		flisteempr2.initOwner(Main.fenetre);
		flisteempr2.initModality(Modality.WINDOW_MODAL);
		flisteempr2.show();
	}
	
	static public void ouvrirAjouterEmprunteur(){
		femprunteur = new AjouterEmprunteur();
		femprunteur.initOwner(Main.fenetre);
		femprunteur.initModality(Modality.WINDOW_MODAL);
		femprunteur.show();
	}
	
	static public void ouvrirModifierEmprunteur(Emprunteur e){
		fmodemprunteur = new ModifierEmprunteur(e);
		fmodemprunteur.initOwner(Main.fenetre);
		fmodemprunteur.initModality(Modality.WINDOW_MODAL);
		fmodemprunteur.show();
	}
	
	static public void ouvrirSupprimerEmprunteur(Emprunteur e){
		if (e.getEmprunt().isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Suppression de l'emprunteur");
		      alert.setHeaderText("Êtes vous sûr de vouloir supprimer l'emprunteur ?");
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		      if (option.get() == ButtonType.OK) {
		    	 listempr.remove(e);
		         Fenetre.updateTableEmprunteur();
		         Fenetre.updateBoutons();
		         Gestion.saveEmprunteur();
		      }
		} else {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Alerte");
	        alert.setHeaderText("Suppression impossible");
	        alert.setContentText("L'emprunteur a au moins un livre en emprunt !");
	        alert.showAndWait();
		}
	}
	
	static public void ouvrirCreerEmprunt(Livre l){
		fajemprunt = new CreerEmprunt(l);
		fajemprunt.initOwner(Main.fenetre);
		fajemprunt.initModality(Modality.WINDOW_MODAL);
		fajemprunt.show();
	}
	
	static public void ouvrirSupprimerEmprunt(Livre l){
		Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Suppression d'un emprunt");
	      alert.setHeaderText("Êtes vous sûr de vouloir supprimer l'emprunt de '" +  l.getTitre() + "' ?");
	      alert.setContentText("Ce livre est emprunté par " + l.getEmprunteur().getNom() + " " + l.getEmprunteur().getPrenom());
	      // option != null.
	      Optional<ButtonType> option = alert.showAndWait();
	 
	      if (option.get() == ButtonType.OK) {
	    	 
	    	 int j=0;
	    	 boolean trouve2 = false;
	    	 while ((j<listempr.size()) && (trouve2 == false)) {
	    		 if (l.getEmprunteur().getId() == listempr.get(j).getId()) {
	    			 trouve2 = true;
	    		 } else {
	    			 j = j+1;
	    		 }
	    	 }
	    	 listempr.get(j).RetourLivre(l);
	    	 for (j=0;j<listempr.size();j++) {
	    		 System.out.println(listempr.get(j).toString());
	    	 }
	    	 int i = 0;
	    	 boolean trouve = false;
	    	 while ((i<list.size()) && (trouve == false)) {
	    		 if (list.get(i).equals(l)) {
	    			 trouve = true;
	    		 } else {
	    			 i = i+1;
	    		 }
	    	 }
	    	 
	    	 boolean trouve3 = false;
	    	 int k=0;
	    	 
	    	 while ((k<listemprunt.size()) && (trouve3 == false)) {
	    		 if(listemprunt.get(k).getLivre().getId() == l.getId()) {
	    			 trouve3 = true;
	    		 } else {
	    			k = k+1;
	    		 }
	    	 }
	    	 
	    	 Main.listemprunt.remove(k);
	    	 
	    	 list.get(i).setEmprunteur(null);
	    	 list.get(i).setEstEmprunte(false);
	    	 Fenetre.updateTableEmprunteur();
	    	 Fenetre.btnLivreCreerEmprunt.setDisable(false);
	    	 Fenetre.btnLivreSupprimerEmprunt.setDisable(true);
	    	 Gestion.saveEmprunteur();
	         Gestion.saveLivre();
	         Gestion.saveEmprunt();	
	      }
	}
	
	static void ouvrirAjouterGenre1() {
		fajgenre = new AjouterGenre();
		fajgenre.initOwner(Main.fajoutlivre);
		fajgenre.initModality(Modality.WINDOW_MODAL);
		fajgenre.show();
	}
	
	static void ouvrirAjouterGenre2() {
		fajgenre2 = new AjouterGenre2();
		fajgenre2.initOwner(Main.fmodlivre);
		fajgenre2.initModality(Modality.WINDOW_MODAL);
		fajgenre2.show();
	}
	
	static void ouvrirModifierGenre(Genre g,int ind) {
		fmodgenre = new ModifierGenre(g, ind);
		fmodgenre.initOwner(Main.fajoutlivre);
		fmodgenre.initModality(Modality.WINDOW_MODAL);
		fmodgenre.show();
	}
	
	static void ouvrirModifierGenre2(Genre g,int ind) {
		fmodgenre2 = new ModifierGenre2(g, ind);
		fmodgenre2.initOwner(Main.fajoutlivre);
		fmodgenre2.initModality(Modality.WINDOW_MODAL);
		fmodgenre2.show();
		
	}


}
