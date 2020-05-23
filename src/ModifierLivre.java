import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ModifierLivre extends Stage{
	
	static private MenuItem optionAjouterGenre = new MenuItem("Ajouter genre...");
	static private MenuItem optionModifierGenre = new MenuItem("Modifier genre...");
	static private MenuItem optionSupprimerGenre = new MenuItem("Supprimer genre");
	
	static private ContextMenu menuGenre = new ContextMenu(optionAjouterGenre,optionModifierGenre,optionSupprimerGenre);
	
	static private TableView<Genre> tableGenreMain;
	static private TableView<Genre> tableGenreLivre;
	static private Button btnGenreAjouter = new Button("Ajouter");
	static private Button btnGenreModifier = new Button("Modifier");
	static private Button btnGenreSupprimer = new Button("Supprimer");
	static ObservableList<Genre> genreLivre;
	static ObservableList<Genre> genreTemp;
	static ObservableList<Genre> genrePreTemp;
	static boolean a = false;
	static private boolean checkNonEmpty = false;
	static private boolean isClick = false;
	static private boolean checkNonEmpty2 = false;
	static private boolean isClick2 = false;
	static Button btnValider = new Button("Valider");
	static Button btnSwipGauche = new Button("<");
	static Button btnSwipDroite = new Button(">");
	private Livre l;
	private int index;
	
	//BOOLEAN DE CONTROLE BOUTON OK
	boolean bTitre = true;
	boolean bAuteur = true;
	boolean bEditeur = true;
	boolean bType = true;
	boolean bGenre = true;
	
	public ModifierLivre(Livre liv, int ind){ 
		this.setTitle("Modifier un livre");
		this.setResizable(false);
		this.setWidth(800);
		this.sizeToScene();
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		this.l = liv;
		this.index = ind;
		btnGenreModifier.setDisable(true);
		btnGenreSupprimer.setDisable(true);
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
	}
		
	public Parent fenetrePrincipal(){
	//Label
		Label lAjLivre = new Label("Modifier un livre");
		Label lRech = new Label("Recherche :");
		Label lRech2 = new Label("Recherche :");
		TextField tRechGenre = new TextField();
		TextField tRechGenre2 = new TextField();
		Button btnAnnuler = new Button("Annuler");
		tableGenreMain = new TableView<Genre>();
		tableGenreLivre = new TableView<Genre>();
		
		btnGenreAjouter.setPrefWidth(80);
		btnGenreModifier.setPrefWidth(80);
		btnGenreSupprimer.setPrefWidth(80);
		btnAnnuler.setStyle("-fx-background-color: #e21600;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnSwipGauche.setDisable(true);
		btnSwipDroite.setDisable(true);
		lRech.setPrefWidth(100);
		tRechGenre.setPrefWidth(215);
		tRechGenre2.setPrefWidth(215);
		
	//HBOX TITRE
		//Label ISBN
		Font font = new Font("Verdana",14);
		Label lISBN = new Label("ISBN : ");
		TextField lISBNRep = new TextField();
		lISBN.setFont(font);
		lISBNRep.setFont(font);
		lISBN.setPrefWidth(70);
		lISBNRep.setPrefWidth(320);
		lISBNRep.setText(l.getIsbn());
		HBox hboxISBN = new HBox();
		hboxISBN.getChildren().addAll(lISBN,lISBNRep);
		hboxISBN.setAlignment(Pos.CENTER);
		hboxISBN.setSpacing(30);
		
		//Label Titre
	Label lLivre = new Label("Livre : ");
	TextField lLivreRep = new TextField();
	lLivre.setFont(font);
	lLivreRep.setFont(font);
	lLivre.setPrefWidth(70);
	lLivreRep.setPrefWidth(320);
	lLivreRep.setText(l.getTitre());
	HBox hboxTitre = new HBox();
	hboxTitre.getChildren().addAll(lLivre,lLivreRep);
	hboxTitre.setAlignment(Pos.CENTER);
	hboxTitre.setSpacing(30);
	//HBOX AUTEUR
		//Label Auteur
	Label lAuteur = new Label("Auteur : ");
	TextField lAuteurRep = new TextField();
	lAuteur.setFont(font);
	lAuteurRep.setFont(font);
	lAuteur.setPrefWidth(70);
	lAuteurRep.setPrefWidth(320);
	lAuteurRep.setText(l.getAuteur());
	HBox hboxAuteur = new HBox();
	hboxAuteur.getChildren().addAll(lAuteur,lAuteurRep);
	hboxAuteur.setAlignment(Pos.CENTER);
	hboxAuteur.setSpacing(30);
	//HBOX EDITEUR
	//Label Editeur
	Label lEditeur = new Label("Editeur : ");
	TextField lEditeurRep = new TextField();
	lEditeur.setFont(font);
	lEditeurRep.setFont(font);
	lEditeur.setPrefWidth(70);
	lEditeurRep.setPrefWidth(320);
	lEditeurRep.setText(l.getEditeur());
	HBox hboxEditeur = new HBox();
	hboxEditeur.getChildren().addAll(lEditeur,lEditeurRep);
	hboxEditeur.setAlignment(Pos.CENTER);
	hboxEditeur.setSpacing(30);
	//Label Editeur
		Label lResume = new Label("Résumé : ");
		TextArea lResumeRep = new TextArea();
		lResume.setFont(font);
		lResumeRep.setFont(font);
		lResume.setPrefWidth(70);
		lResumeRep.setPrefWidth(320);
		lResumeRep.setText(l.getResume());
		HBox hboxResume = new HBox();
		hboxResume.getChildren().addAll(lResume,lResumeRep);
		hboxResume.setAlignment(Pos.CENTER);
		hboxResume.setSpacing(30);
		//TextField Auteur
	//HBOX TYPE
		//Label Type
	Label lType = new Label("Type : ");
	
	ObservableList<String> lTypeRep = 
		    FXCollections.observableArrayList(
		        "Livre",
		        "BD",
		        "Manga"
		    );
		final ComboBox<String> comboBox = new ComboBox<String>(lTypeRep);
	lType.setFont(font);
	lType.setPrefWidth(70);
	comboBox.setPrefWidth(320);
	comboBox.setValue(l.getType());
	HBox hboxType = new HBox();
	hboxType.getChildren().addAll(lType,comboBox);
	hboxType.setAlignment(Pos.CENTER);
	hboxType.setSpacing(30);
		//??? Type
	//HBOX GENRE
		//Label Genre
	Label lGenre = new Label("Genre : ");
	Label lGenreRep = new Label("Genre à définir");
	lGenre.setFont(font);
	lGenreRep.setFont(font);
	lGenre.setPrefWidth(70);
	lGenreRep.setPrefWidth(320);
	
	//SET ITEMS VIA MAIN (genreTemp)
	genrePreTemp = FXCollections.observableArrayList(Main.listgenre);
	//SET ITEMS VIA MAIN (genreTemp)
	genreTemp = FXCollections.observableArrayList();
	//SET ITEMS VIA OBJECT (genreLivre)
	genreLivre = FXCollections.observableArrayList(this.l.getGenre());
	
	int y=0;
	int z=0;
	boolean trouve2 = false;
	int size = genrePreTemp.size();
	while (y<size) {
		while (z<genreLivre.size() && (trouve2 == false)) {
			System.out.println(genrePreTemp.size());
			System.out.println("y=" + y + "\nz=" + z + "\nTest1=" + genrePreTemp.get(y).getNom() + "\nTest2=" + genreLivre.get(z).getNom() + "\n -----");
			
			if (genrePreTemp.get(y).getNom().equals(genreLivre.get(z).getNom())) {
				trouve2 = true;
			} else {
				z=z+1;
			}
		}
		if (trouve2 == false) {
			genreTemp.add(genrePreTemp.get(y));
		}
		y=y+1;
		trouve2 = false;
		z=0;
		
	}
	
	optionModifierGenre.setDisable(true);
	optionSupprimerGenre.setDisable(true);
	
	TableColumn<Genre,String> tableColNomGenre= new TableColumn<Genre, String>("Genre disponible");
	
	tableGenreMain.getColumns().add(tableColNomGenre);
	tableColNomGenre.setCellValueFactory(new PropertyValueFactory<>("nom"));
	tableGenreMain.setItems(genreTemp);
	tableGenreMain.setEditable(true);
	tableGenreMain.setPrefSize(320, 200);
	tableGenreMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableGenreMain.setContextMenu(menuGenre);
	
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
	vboxSwip.setSpacing(10);
	
	HBox hboxGenreTF = new HBox();
	hboxGenreTF.getChildren().addAll(lRech,tRechGenre);
	hboxGenreTF.setSpacing(5);
	
	HBox hboxGenreTF2 = new HBox();
	hboxGenreTF2.getChildren().addAll(lRech2,tRechGenre2);
	hboxGenreTF2.setSpacing(5);
	
	HBox hboxGenreButton = new HBox();
	hboxGenreButton.getChildren().addAll(btnGenreAjouter,btnGenreModifier,btnGenreSupprimer);
	hboxGenreButton.setAlignment(Pos.CENTER);
	hboxGenreButton.setSpacing(30);
	
	VBox vboxGenreMain = new VBox();
	vboxGenreMain.getChildren().addAll(hboxGenreTF,ModifierLivre.tableGenreMain,hboxGenreButton);
	vboxGenreMain.setSpacing(5);
	
	VBox vboxGenreLivre = new VBox();
	vboxGenreLivre.getChildren().addAll(hboxGenreTF2,ModifierLivre.tableGenreLivre);
	vboxGenreLivre.setSpacing(5);
	
	HBox hboxTable = new HBox();
	hboxTable.getChildren().addAll(vboxGenreMain,vboxSwip,vboxGenreLivre);
	hboxTable.setSpacing(5);
	
	HBox hboxGenre = new HBox();
	hboxGenre.getChildren().addAll(lGenre,hboxTable);
	hboxGenre.setAlignment(Pos.TOP_CENTER);
	hboxGenre.setSpacing(30);
	
	//SETONACTION BUTTON
	lLivreRep.setOnKeyReleased(e->{
		if (lLivreRep.getText().isEmpty()) {
			bTitre = false;
		} else {
			bTitre = true;
		}
		this.updateBoutons();
	});
	
	lAuteurRep.setOnKeyReleased(e->{
		if (lAuteurRep.getText().isEmpty()) {
			bAuteur = false;
		} else {
			bAuteur = true;
		}
		this.updateBoutons();
	});
	
	lEditeurRep.setOnKeyReleased(e->{
		if (lEditeurRep.getText().isEmpty()) {
			bEditeur = false;
		} else {
			bEditeur = true;
		}
		this.updateBoutons();
	});
	
	comboBox.setOnAction(e->{
		if (comboBox.getValue().isEmpty()) {
			bType = false;
		} else {
			bType = true;
		}
		this.updateBoutons();
	});
	
	btnSwipDroite.setOnAction(e->{
		if (ModifierLivre.tableGenreMain.getSelectionModel().getSelectedIndex() != -1) {
			genreLivre.add(ModifierLivre.tableGenreMain.getSelectionModel().getSelectedItem());
			genreTemp.remove(ModifierLivre.tableGenreMain.getSelectionModel().getSelectedItem());
			ModifierLivre.updateTableGenre();
			if (genreLivre.size() == 0) {
				bGenre = false;
			} else {
				bGenre = true;
			}
			tRechGenre.setText("");
			tRechGenre2.setText("");
			this.updateBoutons();
		}
	});
	
	btnSwipGauche.setOnAction(e->{
		if (ModifierLivre.tableGenreLivre.getSelectionModel().getSelectedIndex() != -1) {
			genreTemp.add(ModifierLivre.tableGenreLivre.getSelectionModel().getSelectedItem());
			genreLivre.remove(ModifierLivre.tableGenreLivre.getSelectionModel().getSelectedItem());
			ModifierLivre.updateTableGenre();
			if (genreLivre.size() == 0) {
				bGenre = false;
			} else {
				bGenre = true;
			}
			tRechGenre.setText("");
			tRechGenre2.setText("");
			this.updateBoutons();
		}
	});
	
	ModifierLivre.tableGenreMain.setOnMouseClicked(e->{
		if (ModifierLivre.tableGenreMain.getItems().size() == 0) {
			checkNonEmpty = false;
		} else {
			checkNonEmpty = true;
		}
		if (ModifierLivre.tableGenreMain.getSelectionModel().getSelectedIndex() != -1) {
			isClick = true;
		} else {
			isClick = false;
		}
	
		this.updateBoutons();
	});
	
	ModifierLivre.tableGenreLivre.setOnMouseClicked(e->{
		if (tableGenreLivre.getItems().size() == 0) {
			checkNonEmpty2 = false;
		} else {
			checkNonEmpty2 = true;
		}
		if (tableGenreLivre.getSelectionModel().getSelectedIndex() != -1) {
			isClick2 = true;
		} else {
			isClick2 = false;
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
		ModifierLivre.tableGenreMain.setItems(filterGenre);
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
		ModifierLivre.tableGenreLivre.setItems(filterGenre);
		btnSwipGauche.setDisable(true);
	});
	
	btnGenreAjouter.setOnAction(e->{
		Main.ouvrirAjouterGenre2();
	});
	
	optionAjouterGenre.setOnAction(e->{
		Main.ouvrirAjouterGenre1();
	});
	
	btnGenreModifier.setOnAction(e->{
		Genre g = tableGenreMain.getSelectionModel().getSelectedItem();
		int ind = tableGenreMain.getSelectionModel().getSelectedIndex();
		Main.ouvrirModifierGenre2(g, ind);
		
	});
	
	optionModifierGenre.setOnAction(e->{
		Genre g = tableGenreMain.getSelectionModel().getSelectedItem();
		int ind = tableGenreMain.getSelectionModel().getSelectedIndex();
		Main.ouvrirModifierGenre(g, ind);
	});
	
	btnGenreSupprimer.setOnAction(e->{
		boolean trouve = false;
		int j = 0;
		int k=0;
		while((j<Main.list.size())&& (trouve == false)) {
			while ((k<Main.list.get(j).getGenre().size()) && (trouve == false)) {
				if (Main.list.get(j).getGenre().get(k).getNom().equals(tableGenreMain.getSelectionModel().getSelectedItem().getNom()) && (Main.list.get(j).getGenre().get(k).getIsActive())) {
					trouve = true;
				} else {
					k = k+1;
				}
			}
			if (trouve==false) {
				j=j+1;
			}
			k=0;
		}
		
		if (trouve == true) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Alerte");
	        alert.setHeaderText("Suppression impossible");
	        alert.setContentText("Le genre est attribué ! (" + Main.list.get(j).getTitre() + " le possède)");
	        alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Suppression du genre");
		      alert.setHeaderText("Êtes vous sûr de vouloir supprimer le genre '" + tableGenreMain.getSelectionModel().getSelectedItem().getNom() + "' ?");
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		 
		      if (option.get() == ButtonType.OK) {
		         Main.listgenre.remove(tableGenreMain.getSelectionModel().getSelectedItem());
		         genreTemp.remove(tableGenreMain.getSelectionModel().getSelectedItem());
		         tRechGenre.setText("");
		         ModifierLivre.updateTableGenre();
		         this.updateBoutons();
		         Gestion.saveGenre();
		      }
		}
		
	});
	
	optionSupprimerGenre.setOnAction(e->{
		boolean trouve = false;
		int j = 0;
		int k=0;
		while((j<Main.list.size())&& (trouve == false)) {
			while ((k<Main.list.get(j).getGenre().size()) && (trouve == false)) {
				if (Main.list.get(j).getGenre().get(k).getNom().equals(tableGenreMain.getSelectionModel().getSelectedItem().getNom()) && (Main.list.get(j).getGenre().get(k).getIsActive())) {
					trouve = true;
				} else {
					k = k+1;
				}
			}
			if (trouve==false) {
				j=j+1;
			}
			k=0;
		}
		
		if (trouve == true) {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Alerte");
	        alert.setHeaderText("Suppression impossible");
	        alert.setContentText("Le genre est attribué ! (" + Main.list.get(j).getTitre() + " le possède)");
	        alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle("Suppression du genre");
		      alert.setHeaderText("Êtes vous sûr de vouloir supprimer le genre '" + tableGenreMain.getSelectionModel().getSelectedItem().getNom() + "' ?");
		      // option != null.
		      Optional<ButtonType> option = alert.showAndWait();
		 
		      if (option.get() == ButtonType.OK) {
		         Main.listgenre.remove(tableGenreMain.getSelectionModel().getSelectedItem());
		         genreTemp.remove(tableGenreMain.getSelectionModel().getSelectedItem());
		         tRechGenre.setText("");
		         AjouterLivre.updateTableGenre();
		         this.updateBoutons();
		         Gestion.saveGenre();
		      }
		}
		
	});
	
	
		//Button Annuler
	btnAnnuler.setOnAction(e->{
		this.close();
	});
		//Button Valider
	btnValider.setOnAction(e->{
		ArrayList<Genre> al = new ArrayList<Genre>();
		al.addAll(genreLivre);
		l.setIsbn(lISBNRep.getText());
		l.setTitre(lLivreRep.getText());
		l.setAuteur(lAuteurRep.getText());
		l.setEditeur(lEditeurRep.getText());
		l.setResume(lResumeRep.getText());
		l.setType(comboBox.getValue());
		l.setGenre(al);
		Fenetre.modifierLivreBiblio(l,index);
		
		this.close();
	});
		
		//VBOX final
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(lAjLivre,hboxISBN,hboxTitre,hboxAuteur,hboxEditeur,hboxResume,hboxType,hboxGenre);
		vboxFinal.setSpacing(10);
		HBox boutonFinal = new HBox();
		boutonFinal.getChildren().addAll(btnAnnuler,btnValider);
		boutonFinal.setAlignment(Pos.BOTTOM_RIGHT);
		boutonFinal.setPadding(new Insets(0,20,20,0));
		boutonFinal.setSpacing(10);
		BorderPane bp = new BorderPane();
		bp.setTop(vboxFinal);
		bp.setBottom(boutonFinal);
		bp.setStyle("-fx-background-color: " + Main.windowcolor + ";");
		return bp;
		
		
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
		if ((bTitre == true) && (bAuteur == true) && (bEditeur == true) && (bType == true) && (bGenre == true)) {
			btnValider.setDisable(false);
		} else {
			btnValider.setDisable(true);
		}
		
		if ((ModifierLivre.isClick == true) && (ModifierLivre.checkNonEmpty == true)){
			btnGenreModifier.setDisable(false);
			btnGenreSupprimer.setDisable(false);
			optionModifierGenre.setDisable(false);
			optionSupprimerGenre.setDisable(false);
			btnSwipDroite.setDisable(false);
		} else {
			btnGenreModifier.setDisable(true);
			btnGenreSupprimer.setDisable(true);
			optionModifierGenre.setDisable(true);
			optionSupprimerGenre.setDisable(true);
			btnSwipDroite.setDisable(true);
		}
		
		if ((ModifierLivre.isClick2 == true) && (ModifierLivre.checkNonEmpty2 == true)){
			btnSwipGauche.setDisable(false);
		} else {
			btnSwipGauche.setDisable(true);
		}
		
		
		
		if (genreLivre.size() == 0) {
			btnSwipGauche.setDisable(true);
		} else {
			btnSwipGauche.setDisable(false);
		}
	}
	
}
