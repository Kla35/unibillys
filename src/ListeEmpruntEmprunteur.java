import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListeEmpruntEmprunteur extends Stage {
	Emprunteur em;
	Livre l;
	
	static private MenuItem optionSupprimerPret = new MenuItem("Supprimer le prêt");
	
	static private ContextMenu menuPret = new ContextMenu(optionSupprimerPret);
	
	
	public ListeEmpruntEmprunteur(Emprunteur emprunteur){ 
		this.setTitle("Liste emprunt emprunteur");
		this.setResizable(false);
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		this.em = emprunteur;
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
		this.setWidth(500);
	}
		
	@SuppressWarnings("unchecked")
	public Parent fenetrePrincipal(){
		//Label
		Label lListeEmprEmprunteur = new Label("Liste emprunt emprunteur");
		Label lAff = new Label("Liste des emprunts de " + em.getNom() + " " + em.getPrenom());
		Button btnOK = new Button("Fermer");
		TableView<Livre> tableLivre2 = new TableView<Livre>();
		
		btnOK.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		
		//HBox Label Aff
		HBox hText = new HBox();
		hText.getChildren().add(lAff);
		hText.setAlignment(Pos.CENTER);
		
		//ListView
		TableColumn<Livre,String> tableColNomLivre = new TableColumn<Livre, String>("Nom du livre");
		TableColumn<Livre,String> tableColAuteur = new TableColumn<Livre, String>("Auteur");
		tableLivre2.getColumns().addAll(tableColNomLivre,tableColAuteur);
		tableColNomLivre.setCellValueFactory(new PropertyValueFactory<>("titre"));
	    tableColAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
	    ObservableList<Livre> listEmprunt = FXCollections.observableArrayList(em.getEmprunt());
	    tableLivre2.setItems(listEmprunt);
	    tableLivre2.setPrefSize(300, 300);
	    tableLivre2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableLivre2.setContextMenu(menuPret);
		btnOK.setOnAction(e->{
			this.close();
		});
		
		tableLivre2.setOnMouseClicked(e->{
			this.l = tableLivre2.getSelectionModel().getSelectedItem();
		});
		
		optionSupprimerPret.setOnAction(e->{
			Main.ouvrirSupprimerEmprunt(l);
			ObservableList<Livre> listEmprunt2 = FXCollections.observableArrayList(em.getEmprunt());
			tableLivre2.setItems(listEmprunt2);
			this.updateBouton();
		});
		
		if (em.getEmprunt().size() == 0) {
			optionSupprimerPret.setDisable(true);
		} else {
			optionSupprimerPret.setDisable(false);
		}
		
		//
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(lListeEmprEmprunteur,hText,tableLivre2);
		vboxFinal.setSpacing(10);
		HBox boutonFinal = new HBox();
		boutonFinal.getChildren().add(btnOK);
		boutonFinal.setAlignment(Pos.BOTTOM_RIGHT);
		boutonFinal.setPadding(new Insets(0,20,20,0));
		boutonFinal.setSpacing(10);
		BorderPane bp = new BorderPane();
		bp.setTop(vboxFinal);
		bp.setBottom(boutonFinal);
		bp.setStyle("-fx-background-color: " + Main.windowcolor + ";");
		return bp;
	}
	
	public void updateBouton() {
		if (em.getEmprunt().size() == 0) {
			optionSupprimerPret.setDisable(true);
		} else {
			optionSupprimerPret.setDisable(false);
		}
	}
}
