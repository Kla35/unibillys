import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AjouterEmprunteur extends Stage {
	boolean bNom = false;
	boolean bPrenom = false;
	static Button btnAnnuler = new Button("Annuler");
	static Button btnValider = new Button("Valider");
	
	public AjouterEmprunteur(){ 
		this.setTitle("Ajouter un emprunteur");
		this.setResizable(false);
		this.sizeToScene();	
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
	}
		
	public Parent fenetrePrincipal(){
		//Label
		Label lAjouterEmpr = new Label("Ajouter un emprunteur");
		
		btnAnnuler.setStyle("-fx-background-color: #e21600;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setDisable(true);
		//HBOX TITRE
			//Nom
		Font font = new Font("Verdana",14);
		Label lNom = new Label("Nom : ");
		TextField lNomRep = new TextField();
		lNom.setFont(font);
		lNomRep.setFont(font);
		lNom.setPrefWidth(70);
		lNomRep.setPrefWidth(320);
		HBox hboxNom = new HBox();
		hboxNom.getChildren().addAll(lNom,lNomRep);
		hboxNom.setAlignment(Pos.CENTER);
		hboxNom.setSpacing(30);
			//TextField Titre
		//HBOX AUTEUR
			//Pr�nom
		Label lPrenom = new Label("Pr�nom : ");
		TextField lPrenomRep = new TextField();
		lPrenom.setFont(font);
		lPrenomRep.setFont(font);
		lPrenom.setPrefWidth(70);
		lPrenomRep.setPrefWidth(320);
		HBox hboxPrenom = new HBox();
		hboxPrenom.getChildren().addAll(lPrenom,lPrenomRep);
		hboxPrenom.setAlignment(Pos.CENTER);
		hboxPrenom.setSpacing(30);
		
			//Button Annuler
		btnAnnuler.setOnAction(e->{
			this.close();
		});
			//Button Valider
		btnValider.setOnAction(e->{
			Emprunteur empr = new Emprunteur(lNomRep.getText(), lPrenomRep.getText());
			Fenetre.ajouterEmprunteurBiblio(empr);
			this.close();
		});
		
		lNomRep.setOnKeyReleased(e->{
			if (lNomRep.getText().isEmpty()) {
				bNom = false;
			} else {
				bNom = true;
			}
			this.updateBouton();
		});
		
		lPrenomRep.setOnKeyReleased(e->{
			if (lPrenomRep.getText().isEmpty()) {
				bPrenom = false;
			} else {
				bPrenom = true;
			}
			this.updateBouton();
		});
		
		HBox boutonFinal = new HBox();
		boutonFinal.getChildren().addAll(btnAnnuler,btnValider);
		boutonFinal.setAlignment(Pos.BOTTOM_RIGHT);
		boutonFinal.setPadding(new Insets(0,20,20,0));
		boutonFinal.setSpacing(10);
		
		//VBOX final
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(lAjouterEmpr,hboxNom,hboxPrenom,boutonFinal);
		vboxFinal.setSpacing(11);
		vboxFinal.setStyle("-fx-background-color: " + Main.windowcolor + ";");
		return vboxFinal;
	}
	
	public void updateBouton() {
		if ((bPrenom == true) && (bNom == true)) {
			btnValider.setDisable(false);
		} else {
			btnValider.setDisable(true);
		}
	}
}
