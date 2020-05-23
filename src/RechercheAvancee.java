import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RechercheAvancee extends Stage {
	
	static private TableView<Genre> tableGenreMain;
	static private TableView<Genre> tableGenreLivre;
	static private ObservableList<Genre> genreLivre;
	static private ObservableList<Genre> genreTemp;
	
	static private Button btnValider = new Button("Valider");
	static private Button btnSwipGauche = new Button("<");
	static private Button btnSwipDroite = new Button(">");
	
	static private boolean checkNonEmpty = false;
	static private boolean isClick = false;
	
	boolean bTitre = false;
	boolean bAuteur = false;
	boolean bEditeur = false;
	boolean bType = false;
	boolean bGenre = false;
	
	public RechercheAvancee(){ 
		this.setTitle("Recherche avancée");
		this.setResizable(false);
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
		this.sizeToScene();
	}
		
	public Parent fenetrePrincipal(){
		//Label
		ObservableList<String> lTypeRep = 
			    FXCollections.observableArrayList(
			    	"",
			        "Livre",
			        "BD",
			        "Manga"
			    );
		
		Label lRechercheAvancee = new Label("Recherche avancée");
		
		Label lTitre = new Label("Par titre :");
		Label lAuteur = new Label("Par auteur :");
		Label lEditeur = new Label("Par éditeur :");
		Label lType = new Label("Par type :");
		Label lGenre = new Label("Par genre : ");
		
		TextField tTitre = new TextField();
		TextField tAuteur = new TextField();
		TextField tEditeur = new TextField();
		final ComboBox<String> tType = new ComboBox<String>(lTypeRep);
		tableGenreMain = new TableView<Genre>();
		tableGenreLivre = new TableView<Genre>();
		
		Label lRech = new Label("Recherche :");
		Label lRech2 = new Label("Recherche :");
		TextField tRechGenre = new TextField();
		TextField tRechGenre2 = new TextField();
		Button btnAnnuler = new Button("Annuler");
		
		lTitre.setPrefWidth(90);
		lAuteur.setPrefWidth(90);
		lEditeur.setPrefWidth(90);
		lType.setPrefWidth(90);
		lGenre.setPrefWidth(90);
		
		tTitre.setPrefWidth(145);
		tAuteur.setPrefWidth(145);
		tEditeur.setPrefWidth(145);
		tType.setPrefWidth(145);
		
		btnAnnuler.setStyle("-fx-background-color: #e21600;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		
		btnValider.setDisable(true);
		btnSwipDroite.setDisable(true);
		btnSwipGauche.setDisable(true);
		
		HBox hRechercheAvancee = new HBox();
		hRechercheAvancee.getChildren().add(lRechercheAvancee);
		hRechercheAvancee.setAlignment(Pos.CENTER);
		
		HBox hTitre = new HBox();
		hTitre.getChildren().addAll(lTitre,tTitre);
		hTitre.setSpacing(10);
		hTitre.setAlignment(Pos.CENTER);
		
		HBox hAuteur = new HBox();
		hAuteur.getChildren().addAll(lAuteur,tAuteur);
		hAuteur.setSpacing(10);
		hAuteur.setAlignment(Pos.CENTER);
		
		HBox hEditeur = new HBox();
		hEditeur.getChildren().addAll(lEditeur,tEditeur);
		hEditeur.setSpacing(10);
		hEditeur.setAlignment(Pos.CENTER);
		
		HBox hType = new HBox();
		hType.getChildren().addAll(lType,tType);
		hType.setSpacing(10);
		hType.setAlignment(Pos.CENTER);
		
		
		//SET ITEMS VIA MAIN (genreTemp)
		genreTemp = FXCollections.observableArrayList(Main.listgenre);
		//SET ITEMS VIA OBJECT (genreLivre)
		genreLivre = FXCollections.observableArrayList();
		
		TableColumn<Genre,String> tableColNomGenre3 = new TableColumn<Genre, String>("Genre disponible");
		
		tableGenreMain.getColumns().add(tableColNomGenre3);
		tableColNomGenre3.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tableGenreMain.setItems(genreTemp);
		tableGenreMain.setEditable(true);
		tableGenreMain.setPrefSize(320, 200);
		tableGenreMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn<Genre,String> tableColNomGenre2= new TableColumn<Genre, String>("Genre sélectionné");
		
		tableGenreLivre.getColumns().add(tableColNomGenre2);
		tableColNomGenre2.setCellValueFactory(new PropertyValueFactory<>("nom"));
		tableGenreLivre.setItems(genreLivre);
		tableGenreLivre.setEditable(true);
		tableGenreLivre.setPrefSize(320, 200);
		tableGenreLivre.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		VBox vboxSwip = new VBox();
		vboxSwip.getChildren().addAll(btnSwipDroite,btnSwipGauche);
		vboxSwip.setPadding(new Insets(30,0,0,0));
		vboxSwip.setAlignment(Pos.CENTER);
		vboxSwip.setSpacing(10);
		
		HBox hboxGenreTF = new HBox();
		hboxGenreTF.getChildren().addAll(lRech,tRechGenre);
		hboxGenreTF.setAlignment(Pos.CENTER);
		hboxGenreTF.setSpacing(5);
		
		HBox hboxGenreTF2 = new HBox();
		hboxGenreTF2.getChildren().addAll(lRech2,tRechGenre2);
		hboxGenreTF2.setAlignment(Pos.CENTER);
		hboxGenreTF2.setSpacing(30);
		
		VBox vboxGenreMain = new VBox();
		vboxGenreMain.getChildren().addAll(hboxGenreTF,RechercheAvancee.tableGenreMain);
		vboxGenreMain.setAlignment(Pos.CENTER);
		vboxGenreMain.setSpacing(5);
		
		VBox vboxGenreLivre = new VBox();
		vboxGenreLivre.getChildren().addAll(hboxGenreTF2,RechercheAvancee.tableGenreLivre);
		vboxGenreLivre.setAlignment(Pos.CENTER);
		vboxGenreLivre.setSpacing(5);
		
		HBox hboxTable = new HBox();
		hboxTable.getChildren().addAll(vboxGenreMain,vboxSwip,vboxGenreLivre);
		hboxTable.setAlignment(Pos.CENTER);
		hboxTable.setSpacing(5);
		
		HBox hboxGenre = new HBox();
		hboxGenre.getChildren().addAll(hboxTable);
		hboxGenre.setAlignment(Pos.TOP_CENTER);
		hboxGenre.setSpacing(30);
		
		
		
		//SETONACTION BUTTON
		tTitre.setOnKeyReleased(e->{
			if (tTitre.getText().isEmpty()) {
				bTitre = false;
				tEditeur.setDisable(false);
				tAuteur.setDisable(false);
				tType.setDisable(false);
				tableGenreMain.setDisable(false);
				tableGenreLivre.setDisable(false);
				tRechGenre.setDisable(false);
				tRechGenre2.setDisable(false);
				this.updateBoutons();
				
			} else {
				bTitre = true;
				tEditeur.setDisable(true);
				tAuteur.setDisable(true);
				tType.setDisable(true);
				btnSwipDroite.setDisable(true);
				btnSwipGauche.setDisable(true);
				tableGenreMain.setDisable(true);
				tableGenreLivre.setDisable(true);
				tRechGenre.setDisable(true);
				tRechGenre2.setDisable(true);
			}
			this.updateBoutons();
		});
		
		tAuteur.setOnKeyReleased(e->{
			if (tAuteur.getText().isEmpty()) {
				bAuteur = false;
				tEditeur.setDisable(false);
				tTitre.setDisable(false);
				tType.setDisable(false);
				tableGenreMain.setDisable(false);
				tableGenreLivre.setDisable(false);
				tRechGenre.setDisable(false);
				tRechGenre2.setDisable(false);
				this.updateBoutons();
			} else {
				bAuteur = true;
				tEditeur.setDisable(true);
				tTitre.setDisable(true);
				tType.setDisable(true);
				btnSwipDroite.setDisable(true);
				btnSwipGauche.setDisable(true);
				tableGenreMain.setDisable(true);
				tableGenreLivre.setDisable(true);
				tRechGenre.setDisable(true);
				tRechGenre2.setDisable(true);
			}
			this.updateBoutons();
		});
		
		tEditeur.setOnKeyReleased(e->{
			if (tEditeur.getText().isEmpty()) {
				bEditeur = false;
				tAuteur.setDisable(false);
				tTitre.setDisable(false);
				tType.setDisable(false);
				tableGenreMain.setDisable(false);
				tableGenreLivre.setDisable(false);
				tRechGenre.setDisable(false);
				tRechGenre2.setDisable(false);
				this.updateBoutons();
			} else {
				bEditeur = true;
				tAuteur.setDisable(true);
				tTitre.setDisable(true);
				tType.setDisable(true);
				btnSwipDroite.setDisable(true);
				btnSwipGauche.setDisable(true);
				tableGenreMain.setDisable(true);
				tableGenreLivre.setDisable(true);
				tRechGenre.setDisable(true);
				tRechGenre2.setDisable(true);
			}
			this.updateBoutons();
		});
		
		tType.setOnAction(e->{
			if ((tType.getValue().isEmpty())) {
				bType = false;
				tAuteur.setDisable(false);
				tTitre.setDisable(false);
				tEditeur.setDisable(false);
				tableGenreMain.setDisable(false);
				tableGenreLivre.setDisable(false);
				tRechGenre.setDisable(false);
				tRechGenre2.setDisable(false);
				this.updateBoutons();
			} else {
				bType = true;
				tAuteur.setDisable(true);
				tTitre.setDisable(true);
				tEditeur.setDisable(true);
				btnSwipDroite.setDisable(true);
				btnSwipGauche.setDisable(true);
				tableGenreMain.setDisable(true);
				tableGenreLivre.setDisable(true);
				tRechGenre.setDisable(true);
				tRechGenre2.setDisable(true);
			}
			this.updateBoutons();
		});
		
		btnSwipDroite.setOnAction(e->{
			if (RechercheAvancee.tableGenreMain.getSelectionModel().getSelectedIndex() != -1) {
				genreLivre.add(RechercheAvancee.tableGenreMain.getSelectionModel().getSelectedItem());
				genreTemp.remove(RechercheAvancee.tableGenreMain.getSelectionModel().getSelectedItem());
				RechercheAvancee.updateTableGenre();
				if (genreLivre.size() == 0) {
					bGenre = false;
					tAuteur.setDisable(false);
					tTitre.setDisable(false);
					tEditeur.setDisable(false);
					tType.setDisable(false);
				} else {
					bGenre = true;
					tAuteur.setDisable(true);
					tTitre.setDisable(true);
					tEditeur.setDisable(true);
					tType.setDisable(true);
				}
				tRechGenre.setText("");
				tRechGenre2.setText("");
				this.updateBoutons();
				
			}
		});
		
		btnSwipGauche.setOnAction(e->{
			if (RechercheAvancee.tableGenreLivre.getSelectionModel().getSelectedIndex() != -1) {
				genreTemp.add(RechercheAvancee.tableGenreLivre.getSelectionModel().getSelectedItem());
				genreLivre.remove(RechercheAvancee.tableGenreLivre.getSelectionModel().getSelectedItem());
				RechercheAvancee.updateTableGenre();
				if (genreLivre.size() == 0) {
					bGenre = false;
					tAuteur.setDisable(false);
					tTitre.setDisable(false);
					tEditeur.setDisable(false);
					tType.setDisable(false);
				} else {
					bGenre = true;
					tAuteur.setDisable(true);
					tTitre.setDisable(true);
					tEditeur.setDisable(true);
					tType.setDisable(true);
				}
				tRechGenre.setText("");
				tRechGenre2.setText("");
				this.updateBoutons();
				
			}
		});
		
		RechercheAvancee.tableGenreMain.setOnMouseClicked(e->{
			if (RechercheAvancee.tableGenreMain.getItems().size() == 0) {
				checkNonEmpty = false;
			} else {
				checkNonEmpty = true;
			}
			if (RechercheAvancee.tableGenreMain.getSelectionModel().getSelectedIndex() != -1) {
				isClick = true;
			} else {
				isClick = false;
			}
			System.out.println(Main.listgenre.get(0).getIsActive());
			this.updateBoutons();
		});
		
		RechercheAvancee.tableGenreLivre.setOnMouseClicked(e->{
			if (tableGenreLivre.getItems().size() == 0) {
				checkNonEmpty = false;
			} else {
				checkNonEmpty = true;
			}
			if (tableGenreLivre.getSelectionModel().getSelectedIndex() != -1) {
				isClick = true;
			} else {
				isClick = false;
			}
			this.updateBoutons();
		});
		
		tRechGenre.setOnKeyReleased(e->{
			ObservableList<Genre> filterGenre = FXCollections.observableArrayList();
			int i;
			for (i=0;i<genreTemp.size();i++) {
				if (genreTemp.get(i).getNom().toLowerCase().contains(tRechGenre.getText().toLowerCase())) {
					filterGenre.add(genreTemp.get(i));
				}
			}
			RechercheAvancee.tableGenreMain.setItems(filterGenre);
			btnSwipDroite.setDisable(true);
		});
		
		tRechGenre2.setOnKeyReleased(e->{
			ObservableList<Genre> filterGenre = FXCollections.observableArrayList();
			int i;
			for (i=0;i<genreLivre.size();i++) {
				if (genreLivre.get(i).getNom().toLowerCase().contains(tRechGenre2.getText().toLowerCase())) {
					filterGenre.add(genreLivre.get(i));
				}
			}
			RechercheAvancee.tableGenreLivre.setItems(filterGenre);
			btnSwipGauche.setDisable(true);
		});
		
		//Button Annuler
		btnAnnuler.setOnAction(e->{
			this.close();
		});
		//Button Valider
		btnValider.setOnAction(e->{
			if ((bTitre == true) && (bAuteur == false) && (bEditeur == false) && (bType == false) && (bGenre == false)) { //TEST TITRE
				ObservableList<Livre> filterLivre = FXCollections.observableArrayList();
				int i;
				for (i=0;i<Main.list.size();i++) {
					if (Main.list.get(i).getTitre().toLowerCase().contains(tTitre.getText().toLowerCase())) {
						filterLivre.add(Main.list.get(i));
					}
				}
				Fenetre.tableLivre.setItems(filterLivre);
			} else if ((bTitre == false) && (bAuteur == true) && (bEditeur == false) && (bType == false) && (bGenre == false)) { //TEST AUTEUR
				ObservableList<Livre> filterLivre = FXCollections.observableArrayList();
				int i;
				for (i=0;i<Main.list.size();i++) {
					if (Main.list.get(i).getAuteur().toLowerCase().contains(tAuteur.getText().toLowerCase())) {
						filterLivre.add(Main.list.get(i));
					}
				}
				Fenetre.tableLivre.setItems(filterLivre);
			} else if ((bTitre == false) && (bAuteur == false) && (bEditeur == true) && (bType == false) && (bGenre == false)) { //TEST EDITEUR
				ObservableList<Livre> filterLivre = FXCollections.observableArrayList();
				int i;
				for (i=0;i<Main.list.size();i++) {
					if (Main.list.get(i).getEditeur().toLowerCase().contains(tEditeur.getText().toLowerCase())) {
						filterLivre.add(Main.list.get(i));
					}
				}
				Fenetre.tableLivre.setItems(filterLivre);
			} else if ((bTitre == false) && (bAuteur == false) && (bEditeur == false) && (bType == true) && (bGenre == false)) { //TEST TYPE
				ObservableList<Livre> filterLivre = FXCollections.observableArrayList();
				int i;
				for (i=0;i<Main.list.size();i++) {
					if (Main.list.get(i).getType().toLowerCase().contains(tType.getValue().toLowerCase())) {
						filterLivre.add(Main.list.get(i));
					}
				}
				Fenetre.tableLivre.setItems(filterLivre);
			} else if ((bTitre == false) && (bAuteur == false) && (bEditeur == false) && (bType == false) && (bGenre == true)) { //TEST GENRE
				ObservableList<Livre> filterLivre = FXCollections.observableArrayList();
				int i;
				int j = 0;
				int k = 0;
				int l = 0;
				for (i=0;i<Main.list.size();i++) {
					while (j<Main.list.get(i).getGenre().size()) {
						while (k<genreLivre.size()) {
							if (Main.list.get(i).getGenre().get(j).getNom().toLowerCase().contains(genreLivre.get(k).getNom().toLowerCase())) {
								l = l+1;
							}
							k = k+1;
						}
						k=0;
						j = j+1;
					}
					System.out.println("Livre" + Main.list.get(i) + "\nA atteindre :" + genreLivre.size() + "\nAtteint :" + l + "\n-------------");
					if (l == genreLivre.size()) {
						filterLivre.add(Main.list.get(i));
					}
					l=0;
					j=0;
				}
				Fenetre.tableLivre.setItems(filterLivre);
			}
			
			Fenetre.btnSupprRechAvancee.setDisable(false);
			Fenetre.btnRechercheAvancee.setDisable(true);
			Fenetre.fieldRechercheLivre.setDisable(true);
			this.close();
		});
			
		HBox boutonFinal = new HBox();
		boutonFinal.getChildren().addAll(btnAnnuler,btnValider);
		boutonFinal.setAlignment(Pos.BOTTOM_RIGHT);
		boutonFinal.setPadding(new Insets(0,20,20,0));
		boutonFinal.setSpacing(10);
		
		HBox row1  = new HBox();
		row1.getChildren().addAll(hTitre,hAuteur);
		row1.setSpacing(40);
		row1.setAlignment(Pos.CENTER);
		
		HBox row2 = new HBox();
		row2.getChildren().addAll(hEditeur,hType);
		row2.setSpacing(40);
		row2.setAlignment(Pos.CENTER);
		
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(hRechercheAvancee,row1,row2,lGenre,hboxTable,hboxGenre,boutonFinal);
		vboxFinal.setSpacing(25);
		vboxFinal.setAlignment(Pos.TOP_CENTER);
		vboxFinal.setStyle("-fx-background-color: " + Main.windowcolor + ";");
		return vboxFinal;
	}
	
	public static void updateTableGenre() {
		tableGenreMain.setItems(genreTemp);
		tableGenreLivre.setItems(genreLivre);
		if (tableGenreMain.getItems().size() == 0) {
			checkNonEmpty = false;
		} else {
			checkNonEmpty = true;
		}
		Gestion.saveLivre();
	}
	
	public void updateBoutons() {
		System.out.println(bTitre + "" + bAuteur +bEditeur+bType+bGenre);
		if ((bTitre == true) || (bAuteur == true) || (bEditeur == true) || (bType == true) || (bGenre == true)) {
			btnValider.setDisable(false);
		} else {
			btnValider.setDisable(true);
		}
		
		if ((RechercheAvancee.isClick == true) && (RechercheAvancee.checkNonEmpty == true)){
			btnSwipDroite.setDisable(false);
		} else {
			btnSwipDroite.setDisable(true);
		}
		
		if (genreLivre.size() == 0) {
			btnSwipGauche.setDisable(true);
		} else {
			btnSwipGauche.setDisable(false);
		}
	}
}
