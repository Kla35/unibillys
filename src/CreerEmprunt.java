import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreerEmprunt extends Stage {
	Livre l;
	
	public CreerEmprunt(Livre livre){
		this.setResizable(false);
		this.setTitle("Créer un emprunt");
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		this.l = livre;
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
		this.setWidth(500);
	}
		
	@SuppressWarnings("unchecked")
	public Parent fenetrePrincipal(){
		//Label
		Label lCreerEmprunt = new Label("Créer un emprunt");
		Label lAff = new Label("Qui emprunte le livre '" + this.l.getTitre() +"' ?");
		TextField fieldEmpr = new TextField();
		Button btnAnnuler = new Button("Annuler");
		Button btnValider = new Button("Valider");
		TableView<Emprunteur> tableEmpr2 = new TableView<Emprunteur>();
		
		btnAnnuler.setStyle("-fx-background-color: #e21600;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setDisable(true);
		
		//HBox Label Aff
		HBox hText = new HBox();
		hText.getChildren().add(lAff);
		hText.setAlignment(Pos.CENTER);
		
		//ListView
		TableColumn<Emprunteur,String> tableColNom = new TableColumn<Emprunteur, String>("Nom");
		TableColumn<Emprunteur,String> tableColPrenom = new TableColumn<Emprunteur, String>("Prénom");
		tableEmpr2.getColumns().addAll(tableColNom,tableColPrenom);
		tableColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    tableColPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	    tableEmpr2.setItems(Main.listempr);
		tableEmpr2.setPrefSize(300, 300);
		tableEmpr2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		//
		fieldEmpr.setOnKeyReleased(e->{
			ObservableList<Emprunteur> filterEmpr = FXCollections.observableArrayList();
			int i;
			for (i=0;i<Main.listempr.size();i++) {
				if ((Main.listempr.get(i).getNom().toLowerCase().startsWith(fieldEmpr.getText().toLowerCase())) || (Main.listempr.get(i).getPrenom().toLowerCase().startsWith(fieldEmpr.getText().toLowerCase()))) {
					filterEmpr.add(Main.listempr.get(i));
				}
			}
			tableEmpr2.setItems(filterEmpr);
		});
		
		btnAnnuler.setOnAction(e->{
			this.close();
		});
		
		btnValider.setOnAction(e->{
			Emprunteur empr = tableEmpr2.getSelectionModel().getSelectedItem();
			empr.EmpruntLivre(this.l);
			this.l.setEmprunteur(empr);
			Main.listemprunt.add(new Lien(this.l,empr));
			Fenetre.btnLivreCreerEmprunt.setDisable(true);
			Fenetre.btnLivreSupprimerEmprunt.setDisable(false);
			Gestion.saveLivre();
			Gestion.saveEmprunteur();
			Gestion.saveEmprunt();
			this.close();
		});
		
		tableEmpr2.setOnMouseClicked(e->{
			if (tableEmpr2.getSelectionModel().getSelectedIndex() != -1) {
				btnValider.setDisable(false);
			} else {
				btnValider.setDisable(true);
			}
		});
		
		//
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(lCreerEmprunt,hText,fieldEmpr,tableEmpr2);
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

}
