import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModifierGenre2 extends Stage{
	Genre g;
	int ind;
	
	public ModifierGenre2(Genre genre, int index){ 
		this.setTitle("Modifier un genre");
		this.g = genre;
		this.ind = index;
		this.setResizable(false);
		this.sizeToScene();
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		Scene laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
	}
	
	Parent fenetrePrincipal() {
		Label lPres = new Label ("Modifier un genre");
		Label lGenre = new Label("Saisir le nom du genre :");
		TextField tGenre = new TextField();
		tGenre.setText(g.getNom());
		Button btnAnnuler = new Button("Annuler");
		Button btnValider = new Button("Valider");
		btnAnnuler.setPrefWidth(80);
		btnValider.setPrefWidth(80);
		btnAnnuler.setStyle("-fx-background-color: #e21600;-fx-border-color: #000000; -fx-border-width: 2px;");
		btnValider.setStyle("-fx-background-color: #00e2b6;-fx-border-color: #000000; -fx-border-width: 2px;");
		
		HBox hboxPres = new HBox();
		hboxPres.getChildren().add(lPres);
		hboxPres.setAlignment(Pos.CENTER);
		
		HBox hboxGenre = new HBox();
		hboxGenre.getChildren().addAll(lGenre,tGenre);
		hboxGenre.setSpacing(10);
		hboxGenre.setAlignment(Pos.CENTER);
		
		HBox boutonFinal = new HBox();
		boutonFinal.getChildren().addAll(btnAnnuler,btnValider);
		boutonFinal.setAlignment(Pos.BOTTOM_RIGHT);
		boutonFinal.setPadding(new Insets(0,20,20,0));
		boutonFinal.setSpacing(10);
		
		//BUTTON ACTION
		
		btnAnnuler.setOnAction(e->{
			this.close();
		});
		
		btnValider.setOnAction(e->{
			boolean trouve = false;
			int j = 0;
			
			while((j<Main.listgenre.size())&& (trouve == false)) {
				System.out.println(trouve);
				System.out.println(Main.listgenre.get(j).getNom() + " " + tGenre.getText());
				if(Main.listgenre.get(j).getNom().equals(tGenre.getText())) {
					System.out.println(Main.listgenre.get(j).getNom() + " " + tGenre.getText());
					trouve = true;
					System.out.println(trouve);
				}
				j=j+1;
			}
			
			System.out.println("stop" + trouve);
			if (trouve) {
				Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Alerte");
		        alert.setHeaderText("Modification impossible");
		        alert.setContentText("Le genre existe déjà ! ");
		        alert.showAndWait();
			} else {
				this.g.setNom(tGenre.getText());
				Main.listgenre.set(this.ind, this.g);
				ModifierLivre.genreTemp.set(this.ind, this.g);
				Gestion.saveGenre();
				Gestion.saveLivre();
				Gestion.saveEmprunt();
				Gestion.saveEmprunteur();
				ModifierLivre.updateTableGenre();
				this.close();
			}
		});
		
		
		VBox vboxFinal = new VBox();
		vboxFinal.getChildren().addAll(hboxPres,hboxGenre,boutonFinal);
		vboxFinal.setSpacing(20);
		vboxFinal.setAlignment(Pos.CENTER);
		vboxFinal.setStyle("-fx-background-color: " + Main.windowcolor + ";");
		return vboxFinal;
		
	}
}
