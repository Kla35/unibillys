
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Fenetre extends Stage{
	Scene laScene;
	boolean ccLivre = false;
	boolean ccEmpr = false;
	
	
	
	static private MenuItem optionAfficherLivre = new MenuItem("Afficher livre...");
	static private MenuItem optionAjouterLivre = new MenuItem("Ajouter livre...");
	static private MenuItem optionModifierLivre = new MenuItem("Modifier livre...");
	static private MenuItem optionSupprimerLivre = new MenuItem("Supprimer");
	static private MenuItem optionCreerPret = new MenuItem("Créer prêt...");
	static private MenuItem optionSupprimerPret = new MenuItem("Supprimer prêt");
	
	static private ContextMenu menuLivre = new ContextMenu(optionAfficherLivre,optionAjouterLivre,optionModifierLivre,optionSupprimerLivre,new SeparatorMenuItem(),optionCreerPret,optionSupprimerPret);
	static private MenuItem optionAjouterEmprunteur = new MenuItem("Ajouter emprunteur...");
	static private MenuItem optionModifierEmprunteur = new MenuItem("Modifier emprunteur...");
	static private MenuItem optionSupprimerEmprunteur = new MenuItem("Supprimer emprunteur...");
	static private MenuItem optionListeEmpruntEmprunteur = new MenuItem("Emprunt(s) du lecteur...");
	
	static private ContextMenu menuEmprunteur  = new ContextMenu(optionAjouterEmprunteur,optionModifierEmprunteur,optionSupprimerEmprunteur,new SeparatorMenuItem(),optionListeEmpruntEmprunteur);
	static boolean checkClick = false;
	static boolean checkNonEmpty = false;
	static boolean checkClick2 = false;
	static boolean checkNonEmpty2 = false;
	
	static int indiceTableLivre;
	static int indiceTableEmpr;
	
	Livre l;
	Emprunteur empr;
	
	static public TableView<Livre> tableLivre = new TableView<Livre>();
	static public TableView<Emprunteur> tableEmpr = new TableView<Emprunteur>();
	
	/*RANDOM STUFF*/
	static MenuBar menuBar = new MenuBar(); 
	static Button btnLivreAfficher = new Button(Translate.WINDOW_SHOW_BOOK);
	static Button btnLivreAjouter = new Button(Translate.WINDOW_ADD_BOOK);
	static Button btnLivreModifier = new Button(Translate.WINDOW_MODIFY_BOOK);
	static Button btnLivreSupprimer = new Button(Translate.WINDOW_REMOVE_BOOK);
	static Button btnLivreCreerEmprunt = new Button(Translate.WINDOW_CREATE_BORROW);
	static Button btnLivreSupprimerEmprunt = new Button(Translate.WINDOW_DELETE_BORROW);
	static Button btnLivreListeEmprunt = new Button(Translate.WINDOW_SHOW_LIST_BORROWS);
	static Button btnEmprAjouter = new Button(Translate.WINDOW_ADD_BORROWER);
	static Button btnEmprModifier = new Button(Translate.WINDOW_MODIFY_BORROWER);
	static Button btnEmprSupprimer = new Button(Translate.WINDOW_DELETE_BORROWER);
	static Button btnEmprListeEmprunt = new Button(Translate.WINDOW_LIST_BORROW_BORROWER);
	static Button btnSupprRechAvancee = new Button(Translate.WINDOW_NORMAL_DISPLAY);
	static Button btnUploadData = new Button("Upload\nsave app");
	static Button btnRechercheAvancee = new Button(Translate.WINDOW_ADVANCED_SEARCH);
	
	static TextField fieldRechercheLivre = new TextField();
	
	
	static Label lNbLivre = new Label();
	
	public Fenetre(){ 
		this.setTitle("UniBillys");
		this.setResizable(false);
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
		this.setWidth(1100);
		this.setHeight(625);
		
	}
		
	@SuppressWarnings("unchecked")
	private Parent fenetrePrincipal(){
		this.setOnCloseRequest(e -> {
			 Main.lock = 0;
			 Gestion.saveLock();
			});
		
		//Title
		Label		title		= new Label("UniBillys (v." + Main.version_local + ")");
		HBox hboxTitle = new HBox();
		hboxTitle.getChildren().add(title);
		hboxTitle.setAlignment(Pos.CENTER);
		//Left
			//Label Recherche Livre
		Label		lRechercheLivre		= new Label(Translate.WINDOW_LABEL_SEARCH_BOOK);
		HBox hboxLabelRechLivre = new HBox();
		hboxLabelRechLivre.getChildren().add(lRechercheLivre);
		hboxLabelRechLivre.setAlignment(Pos.TOP_LEFT);
			//TextField Recherche Livre
		
		fieldRechercheLivre.setPrefWidth(190);
			//Button Recherche Avancée
		
		btnRechercheAvancee.setPrefSize(135,27);
		btnSupprRechAvancee.setPrefSize(135,27);
			//HBOX Recherche Livre+Avancée
		HBox hboxRecherche = new HBox();
		hboxRecherche.getChildren().addAll(fieldRechercheLivre,btnRechercheAvancee,btnSupprRechAvancee);
		hboxRecherche.setSpacing(20);
			//TableView Liste Livre (/!\ STRING A CHANGER /!\)
		TableColumn<Livre,String> tableColNomLivre = new TableColumn<Livre, String>("Nom du livre");
		TableColumn<Livre,String> tableColAuteur = new TableColumn<Livre, String>("Auteur");
		tableLivre.getColumns().addAll(tableColNomLivre,tableColAuteur);
		tableColNomLivre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	    tableColAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
	    
		tableLivre.setPrefSize(300, 460);
		tableLivre.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableLivre.setContextMenu(menuLivre);
		
			//VBox Left Left
		VBox vboxLeftLeft = new VBox();
		vboxLeftLeft.getChildren().addAll(hboxLabelRechLivre,hboxRecherche,tableLivre);
		vboxLeftLeft.setPadding(new Insets(0,0,0,10));
		vboxLeftLeft.setSpacing(10);
		
			//Vbox Button Left Right 1
		btnLivreAfficher.setDisable(true);
		btnLivreModifier.setDisable(true);
		btnLivreCreerEmprunt.setDisable(true);
		btnLivreSupprimerEmprunt.setDisable(true);
		btnSupprRechAvancee.setDisable(true);
		optionAfficherLivre.setDisable(true);
		optionModifierLivre.setDisable(true);
		optionSupprimerLivre.setDisable(true);
		optionCreerPret.setDisable(true);
		optionSupprimerPret.setDisable(true);
		
		if (tableLivre.getItems().size() == 0) {
			checkNonEmpty = false;
		} else {
			checkNonEmpty = true;
		}
		btnLivreSupprimer.setDisable(true);
		btnLivreAfficher.setPrefWidth(100);
		btnLivreAjouter.setPrefWidth(100);
		btnLivreModifier.setPrefWidth(100);
		btnLivreSupprimer.setPrefWidth(100);
		VBox vboxButton1 = new VBox();
		vboxButton1.getChildren().addAll(btnLivreAfficher,btnLivreAjouter,btnLivreModifier,btnLivreSupprimer, lNbLivre);
		vboxButton1.setSpacing(5);
		vboxButton1.setPadding(new Insets(80,0,0,0));
			//VBox Button Left Right 2
		
		btnLivreCreerEmprunt.setPrefSize(100,52);
		btnLivreSupprimerEmprunt.setPrefSize(100,52);
		btnLivreListeEmprunt.setPrefSize(100,73);
		VBox vboxButton2 = new VBox();
		vboxButton2.getChildren().addAll(btnLivreCreerEmprunt,btnLivreSupprimerEmprunt,btnLivreListeEmprunt);
		vboxButton2.setSpacing(5);
			//BP Left Right
		BorderPane bpLeftRight = new BorderPane();
		bpLeftRight.setTop(vboxButton1);
		bpLeftRight.setBottom(vboxButton2);
			//HBox Left Final
		HBox hboxLeftFinal = new HBox();
		hboxLeftFinal.getChildren().addAll(vboxLeftLeft ,bpLeftRight);
		hboxLeftFinal.setSpacing(5);
	/*--------------------------------------*---------------*------------*----------------------------------*/	
		//Right
		//Right Recherche Emprunteur V
	Label		lRechercheEmprunteur		= new Label(Translate.WINDOW_LABEL_SEARCH_BORROWER);
	HBox hboxLabelRechEmpr = new HBox();
	hboxLabelRechEmpr.getChildren().add(lRechercheEmprunteur);
	hboxLabelRechEmpr.setPadding(new Insets(-5,0,10,0));
	hboxLabelRechEmpr.setAlignment(Pos.TOP_LEFT);
		//TextField Recherche Livre V
	TextField fieldRechercheEmpr = new TextField();
	fieldRechercheEmpr.setPrefWidth(30);

		//VBOX 
	VBox vboxtest = new VBox();
	vboxtest.getChildren().addAll(hboxLabelRechEmpr,fieldRechercheEmpr);
	vboxtest.setPadding(new Insets(0,0,21,0));
		//TableView Liste Livre (/!\ STRING A CHANGER /!\)
	
	
	TableColumn<Emprunteur,String> tableColNom = new TableColumn<Emprunteur, String>("Nom");
	TableColumn<Emprunteur,String> tableColPrenom = new TableColumn<Emprunteur, String>("Prénom");
	tableEmpr.getColumns().addAll(tableColNom,tableColPrenom);
	tableColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    tableColPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    tableEmpr.setItems(Main.listempr);
	tableEmpr.setPrefSize(300, 460);
	tableEmpr.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	tableEmpr.setContextMenu(menuEmprunteur);
	
	//Menu Action
	final MenuItem exitItem = new MenuItem("Quitter"); 
	final Menu fileMenu = new Menu("Fichier"); 
	fileMenu.getItems().setAll(exitItem); 
	final MenuItem importItem = new MenuItem("Importer des données"); 
	final MenuItem exportItem = new MenuItem("Exporter des données");
	final MenuItem optionsItem= new MenuItem("Options...");  
	final Menu editMenu = new Menu("Édition"); 
	editMenu.getItems().setAll(importItem, exportItem, new SeparatorMenuItem(), optionsItem); 
	final MenuItem aboutItem = new MenuItem("À propos...");
	final MenuItem infoMenu = new MenuItem("Informations"); 
	final Menu helpMenu = new Menu("Aide"); 
	helpMenu.getItems().setAll(infoMenu,aboutItem);
	
	exitItem.setOnAction(actionEvent -> {
		Main.lock = 0;
		Gestion.saveLock();
		System.exit(1);
	});
	
	optionsItem.setOnAction(actionEvent -> {
		Main.ouvrirPreferences();
	});
	
	menuBar.getMenus().setAll(fileMenu, editMenu, helpMenu);
	
	
		//VBox Right Left
	VBox vboxRightLeft = new VBox();
	menuBar.setUseSystemMenuBar(true);
	vboxRightLeft.getChildren().addAll(vboxtest,tableEmpr);
	vboxRightLeft.setPadding(new Insets(6,0,0,10));
	btnEmprModifier.setDisable(true);
	btnEmprSupprimer.setDisable(true);
	btnEmprListeEmprunt.setDisable(false);
	optionSupprimerEmprunteur.setDisable(true);
	optionModifierEmprunteur.setDisable(true);
		//Vbox Button Right Right 1
	
	btnEmprAjouter.setPrefWidth(100);
	btnEmprModifier.setPrefWidth(100);
	btnEmprSupprimer.setPrefWidth(100);
	VBox vboxEmprButton1 = new VBox();
	vboxEmprButton1.getChildren().addAll(btnEmprAjouter,btnEmprModifier,btnEmprSupprimer);
	vboxEmprButton1.setSpacing(5);
	vboxEmprButton1.setPadding(new Insets(80,0,0,0));
		//VBox Button Right Right 2
	
	btnEmprListeEmprunt.setPrefSize(100,85);
	VBox vboxEmprButton2 = new VBox();
	vboxEmprButton2.getChildren().add(btnEmprListeEmprunt);
	vboxEmprButton2.setSpacing(5);
		//BP Right Right
	BorderPane bpRightRight = new BorderPane();
	bpRightRight.setTop(vboxEmprButton1);
	bpRightRight.setBottom(vboxEmprButton2);
		//HBox Right Final
	HBox hboxRightFinal = new HBox();
	hboxRightFinal.getChildren().addAll(vboxRightLeft ,bpRightRight);
	hboxRightFinal.setSpacing(5);
		
	/*-----------------*---------------------*------------------------------------------------*/
	//Codage des boutons
	
	 this.setOnCloseRequest(e -> {
		 Main.lock = 0;
		 Gestion.saveLock();
	 });
	
	
	
	tableLivre.setOnMouseClicked(e->{
		this.ccLivre = true;
		this.l = tableLivre.getSelectionModel().getSelectedItem();
		System.out.println(tableLivre.getSelectionModel().getSelectedItem());
		indiceTableLivre = tableLivre.getSelectionModel().getSelectedIndex();
		if (tableLivre.getSelectionModel().getSelectedIndex() != -1) {
			checkClick = true;
		} else {
			checkClick = false;
		}
		Fenetre.updateBoutons();
	});
	
	tableEmpr.setOnMouseClicked(e->{
		this.ccEmpr = true;
		this.empr = tableEmpr.getSelectionModel().getSelectedItem();
		System.out.println(tableEmpr.getSelectionModel().getSelectedItem());
		indiceTableEmpr = tableEmpr.getSelectionModel().getSelectedIndex();
		if (tableEmpr.getSelectionModel().getSelectedIndex() != -1) {
			checkClick2 = true;
		} else {
			checkClick2 = false;
		}
		Fenetre.updateBoutons();
	});
	
	btnRechercheAvancee.setOnAction(e->{
		Main.ouvrirRechercheAvancee();
	});
	
	Fenetre.btnSupprRechAvancee.setOnAction(e->{
		btnRechercheAvancee.setDisable(false);
		btnSupprRechAvancee.setDisable(true);
		Fenetre.fieldRechercheLivre.setDisable(false);
		Fenetre.updateTableLivre();
		
	});
	
	optionAfficherLivre.setOnAction(e->{
		Main.ouvrirAfficherLivre(l);
	});
	btnLivreAfficher.setOnAction(e->{
		Main.ouvrirAfficherLivre(l);
	});
	
	optionAjouterLivre.setOnAction(e->{
		Main.ouvrirAjouterLivre();
	});
	
	btnLivreAjouter.setOnAction(e->{
		Main.ouvrirAjouterLivre();
	});
	
	optionModifierLivre.setOnAction(e->{
		Main.ouvrirModifierLivre(l,indiceTableLivre);
	});
	
	btnLivreModifier.setOnAction(e->{
		Main.ouvrirModifierLivre(l,indiceTableLivre);
	});
	
	optionSupprimerLivre.setOnAction(e->{
		Main.ouvrirSupprimerLivre(l);
	});
	
	btnLivreSupprimer.setOnAction(e->{
		Main.ouvrirSupprimerLivre(l);
	});
	
	optionCreerPret.setOnAction(e->{
		Main.ouvrirCreerEmprunt(l);
	});
	
	btnLivreCreerEmprunt.setOnAction(e->{
		Main.ouvrirCreerEmprunt(l);
	});
	
	optionSupprimerPret.setOnAction(e->{
		Main.ouvrirSupprimerEmprunt(l);
	});
	
	btnLivreSupprimerEmprunt.setOnAction(e->{
		Main.ouvrirSupprimerEmprunt(l);
	});
	
	btnLivreListeEmprunt.setOnAction(e->{
		Main.ouvrirListeEmprunt();
	});
	
	optionAjouterEmprunteur.setOnAction(e->{
		Main.ouvrirAjouterEmprunteur();
	});
	
	btnEmprAjouter.setOnAction(e->{
		Main.ouvrirAjouterEmprunteur();
	});
	
	optionModifierEmprunteur.setOnAction(e->{
		Main.ouvrirModifierEmprunteur(empr);
	});
	
	btnEmprModifier.setOnAction(e->{
		Main.ouvrirModifierEmprunteur(empr);
	});
	
	optionSupprimerEmprunteur.setOnAction(e->{
		Main.ouvrirSupprimerEmprunteur(empr);
	});
	
	btnEmprSupprimer.setOnAction(e->{
		Main.ouvrirSupprimerEmprunteur(empr);
	});
	
	optionListeEmpruntEmprunteur.setOnAction(e->{
		Main.ouvrirListeEmpruntEmprunteur(empr);
	});
	
	btnEmprListeEmprunt.setOnAction(e->{
		Main.ouvrirListeEmpruntEmprunteur(empr);
	});
	
	fieldRechercheLivre.setOnKeyReleased(e->{
		ObservableList<Livre> filterLivre = FXCollections.observableArrayList();
		int i;
		for (i=0;i<Main.list.size();i++) {
			if (Main.list.get(i).getTitre().toLowerCase().contains(fieldRechercheLivre.getText().toLowerCase())) {
				filterLivre.add(Main.list.get(i));
			}
		}
		Fenetre.tableLivre.setItems(filterLivre);
		btnLivreAfficher.setDisable(true);
		btnLivreModifier.setDisable(true);
		btnLivreSupprimer.setDisable(true);
		btnLivreCreerEmprunt.setDisable(true);
		btnLivreSupprimerEmprunt.setDisable(true);
		optionAfficherLivre.setDisable(true);
		optionModifierLivre.setDisable(true);
		optionSupprimerLivre.setDisable(true);
		optionSupprimerPret.setDisable(true);
		optionCreerPret.setDisable(true);
	});
	
	fieldRechercheEmpr.setOnKeyReleased(e->{
		ObservableList<Emprunteur> filterEmpr = FXCollections.observableArrayList();
		int i;
		for (i=0;i<Main.listempr.size();i++) {
			if ((Main.listempr.get(i).getNom().toLowerCase().startsWith(fieldRechercheEmpr.getText().toLowerCase())) || (Main.listempr.get(i).getPrenom().toLowerCase().startsWith(fieldRechercheEmpr.getText().toLowerCase()))){
				filterEmpr.add(Main.listempr.get(i));
			}
		}
		Fenetre.tableEmpr.setItems(filterEmpr);
		btnEmprModifier.setDisable(true);
		btnEmprSupprimer.setDisable(true);
		optionAjouterEmprunteur.setDisable(true);
		optionModifierEmprunteur.setDisable(true);
		optionSupprimerEmprunteur.setDisable(true);
		optionListeEmpruntEmprunteur.setDisable(true);
	});
	
	lNbLivre.setText("Nombre\nde livres :\n"  +  Main.list.size());

	
	//Mise en forme final	
		BorderPane subracine = new BorderPane();
		subracine.setPadding(new Insets(10));
		subracine.setTop(hboxTitle);
		subracine.setLeft(hboxLeftFinal);
		subracine.setRight(hboxRightFinal);
		subracine.setStyle("-fx-background-color: " + Main.windowcolor + ";");
		BorderPane racine = new BorderPane();
		racine.setTop(menuBar);
		racine.setBottom(subracine);
		return racine;	
	}
	
	public static void updateTableLivre() {
		tableLivre.setItems(Main.list);
		if (tableLivre.getItems().size() == 0) {
			checkNonEmpty = false;
		} else {
			checkNonEmpty = true;
		}
		Gestion.saveLivre();
	}
	
	public static void updateTableEmprunteur() {
		tableEmpr.setItems(Main.listempr);
		if (tableEmpr.getItems().size() == 0) {
			checkNonEmpty2 = false;
		} else {
			checkNonEmpty2 = true;
		}
		Gestion.saveEmprunteur();
	}
	
	public static void updateBoutons() {
		if ((checkClick == true) && (checkNonEmpty == true)){
			btnLivreAfficher.setDisable(false);
			btnLivreModifier.setDisable(false);
			btnLivreSupprimer.setDisable(false);
			optionAfficherLivre.setDisable(false);
			optionModifierLivre.setDisable(false);
			optionSupprimerLivre.setDisable(false);
			if (!Fenetre.tableLivre.getSelectionModel().getSelectedItem().isEstEmprunte()) {
				btnLivreSupprimerEmprunt.setDisable(true);
				btnLivreCreerEmprunt.setDisable(false);
				optionSupprimerPret.setDisable(true);
				optionCreerPret.setDisable(false);
			}else {
				btnLivreSupprimerEmprunt.setDisable(false);
				btnLivreCreerEmprunt.setDisable(true);
				optionSupprimerPret.setDisable(false);
				optionCreerPret.setDisable(true);
			}
		} else {
			btnLivreAfficher.setDisable(true);
			btnLivreModifier.setDisable(true);
			btnLivreSupprimer.setDisable(true);
			btnLivreCreerEmprunt.setDisable(true);
			btnLivreSupprimerEmprunt.setDisable(true);
			optionAfficherLivre.setDisable(true);
			optionModifierLivre.setDisable(true);
			optionSupprimerLivre.setDisable(true);
			optionSupprimerPret.setDisable(true);
			optionCreerPret.setDisable(true);
		}
		
		System.out.println("checkClick2 : " + checkClick2  + "\ncheckNonEmpty2 : " + checkNonEmpty2);
		if ((checkClick2 ==true) && (checkNonEmpty2 == true)) {
			btnEmprModifier.setDisable(false);
			btnEmprSupprimer.setDisable(false);
			optionAjouterEmprunteur.setDisable(false);
			optionModifierEmprunteur.setDisable(false);
			optionSupprimerEmprunteur.setDisable(false);
			optionListeEmpruntEmprunteur.setDisable(false);
			
		} else {
			btnEmprModifier.setDisable(true);
			btnEmprSupprimer.setDisable(true);
			optionAjouterEmprunteur.setDisable(true);
			optionModifierEmprunteur.setDisable(true);
			optionSupprimerEmprunteur.setDisable(true);
			optionListeEmpruntEmprunteur.setDisable(true);
		}
	}
	
	public static void ajouterLivreBiblio(Livre l){
		Main.list.add(l);
		Fenetre.updateTableLivre();
	}
	
	public static void modifierLivreBiblio(Livre li,int ind){
		Main.list.set(ind, li);
		Fenetre.updateTableLivre();
		checkNonEmpty = true;
	}
	
	public static void ajouterEmprunteurBiblio(Emprunteur e){
		Main.listempr.add(e);
		Fenetre.updateTableEmprunteur();
	}
	
	public static void modifierEmprunteurBiblio(Emprunteur e){
		Main.listempr.set(indiceTableEmpr, e);
		Fenetre.updateTableEmprunteur();
		checkNonEmpty2 = true;
	}
	
}
