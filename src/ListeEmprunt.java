import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListeEmprunt extends Stage {
	
	static private MenuItem optionSupprimerPret = new MenuItem("Supprimer le prêt");
	
	static private ContextMenu menuPret = new ContextMenu(optionSupprimerPret);
	
	public ListeEmprunt(){ 
		this.setTitle("Liste des emprunts");
		this.setResizable(false);
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
		this.setWidth(900);
	}
		
	@SuppressWarnings("unchecked")
	public Parent fenetrePrincipal(){
		//Label
		Label lListeEmprunt = new Label("Liste des emprunts");
		Label lAff = new Label("Liste des emprunts");
		Button btnOK = new Button("Fermer");
		TableView<Lien> tableLivre3 = new TableView<Lien>();
		
		btnOK.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		
		//HBox Label Aff
		HBox hText = new HBox();
		hText.getChildren().add(lAff);
		hText.setAlignment(Pos.CENTER);
		
		//ListView
		TableColumn<Lien,String> tableColPret = new TableColumn<Lien, String>("Prêt");
		TableColumn<Lien,String> tableColNomLivre = new TableColumn<Lien, String>("Nom du livre");
		TableColumn<Lien,String> tableColAuteur = new TableColumn<Lien, String>("Auteur");
		TableColumn<Lien,String> tableColEmprunteur = new TableColumn<Lien, String>("Emprunteur");
		TableColumn<Lien,String> tableColNom = new TableColumn<Lien, String>("Nom");
		TableColumn<Lien,String> tableColPrenom = new TableColumn<Lien, String>("Prénom");
		tableColEmprunteur.getColumns().addAll(tableColNom,tableColPrenom);
		tableColPret.getColumns().addAll(tableColNomLivre, tableColAuteur);
		
		tableLivre3.getColumns().addAll(tableColPret,tableColEmprunteur);
		//FAIRE UNE NOUVELLE CLASSE LIEN POUR LIER LIVRE ET EMPRUNTEUR
		tableColNomLivre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Lien , String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Lien , String> param) {
			return new SimpleObjectProperty<>(param.getValue().getLivre().getTitre());
			}
		});
		tableColAuteur.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Lien , String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Lien , String> param) {
			return new SimpleObjectProperty<>(param.getValue().getLivre().getAuteur());
			}
		});
		tableColNom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Lien , String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Lien , String> param) {
			return new SimpleObjectProperty<>(param.getValue().getEmpr().getNom());
			}
		});
		tableColPrenom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Lien , String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Lien , String> param) {
			return new SimpleObjectProperty<>(param.getValue().getEmpr().getPrenom());
			}
		});
	
	    tableLivre3.setItems(Main.listemprunt);
	    tableLivre3.setPrefSize(300, 300);
	    tableLivre3.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableLivre3.setContextMenu(menuPret);
		btnOK.setOnAction(e->{
			this.close();
		});
		
		optionSupprimerPret.setOnAction(e->{
			Main.ouvrirSupprimerEmprunt(tableLivre3.getSelectionModel().getSelectedItem().getLivre());
			ObservableList<Lien> listEmprunt2 = FXCollections.observableArrayList(Main.listemprunt);
			tableLivre3.setItems(listEmprunt2);
			this.updateBouton();
		});
		
		if (Main.listemprunt.size() == 0) {
			optionSupprimerPret.setDisable(true);
		} else {
			optionSupprimerPret.setDisable(false);
		}
		//
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(lListeEmprunt,hText,tableLivre3);
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
		if (Main.listemprunt.size() == 0) {
			optionSupprimerPret.setDisable(true);
		} else {
			optionSupprimerPret.setDisable(false);
		}
	}
}
