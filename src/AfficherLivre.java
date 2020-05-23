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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AfficherLivre extends Stage {
	
	Livre livre = null;
	private TableView<Genre> tableGenreAff = new TableView<Genre>();
	
	public AfficherLivre(Livre l){
		Scene laScene;
		this.livre = l;
		System.out.println(l);
		this.setResizable(false);
		this.setTitle("Afficher livre");
		this.sizeToScene();
		Class<?> clazz = this.getClass();
		Image icon = new Image(clazz.getResourceAsStream("icon.jpg"));
		this.getIcons().add(icon);
		
		laScene = new Scene(fenetrePrincipal());
		this.setScene(laScene);
	}
		
	public Parent fenetrePrincipal(){
		//Label
		Button btnOK = new Button("OK");
		btnOK.setOnAction(e->{
			this.close();
		});
		//HBOX TITRE
		//Label ISBN
				Font font = new Font("Verdana",14);
				Label lISBN = new Label("ISBN : ");
				Label lISBNRep = new Label("" + this.livre.getIsbn());
				lISBN.setFont(font);
				lISBNRep.setFont(font);
				lISBN.setPrefWidth(110);
				lISBNRep.setPrefWidth(320);
				HBox hboxISBN = new HBox();
				hboxISBN.getChildren().addAll(lISBN,lISBNRep);
				hboxISBN.setAlignment(Pos.CENTER);
				hboxISBN.setSpacing(30);
			//Label Titre
		Label lLivre = new Label("Livre : ");
		Label lLivreRep = new Label("" + this.livre.getTitre());
		lLivre.setFont(font);
		lLivreRep.setFont(font);
		lLivre.setPrefWidth(110);
		lLivreRep.setPrefWidth(320);
		HBox hboxTitre = new HBox();
		hboxTitre.getChildren().addAll(lLivre,lLivreRep);
		hboxTitre.setAlignment(Pos.CENTER);
		hboxTitre.setSpacing(30);
		//HBOX AUTEUR
			//Label Auteur
		Label lAuteur = new Label("Auteur : ");
		Label lAuteurRep = new Label("" + this.livre.getAuteur());
		lAuteur.setFont(font);
		lAuteurRep.setFont(font);
		lAuteur.setPrefWidth(110);
		lAuteurRep.setPrefWidth(320);
		HBox hboxAuteur = new HBox();
		hboxAuteur.getChildren().addAll(lAuteur,lAuteurRep);
		hboxAuteur.setAlignment(Pos.CENTER);
		hboxAuteur.setSpacing(30);
	
		//HBOX EDITEUR
			//Label Editeur
		Label lEditeur = new Label("Editeur : ");
		Label lEditeurRep = new Label("" + this.livre.getEditeur());
		lEditeur.setFont(font);
		lEditeurRep.setFont(font);
		lEditeur.setPrefWidth(110);
		lEditeurRep.setPrefWidth(320);
		HBox hboxEditeur = new HBox();
		hboxEditeur.getChildren().addAll(lEditeur,lEditeurRep);
		hboxEditeur.setAlignment(Pos.CENTER);
		hboxEditeur.setSpacing(30);
		//HBOX TYPE
		//Label Editeur
				Label lResume = new Label("Résumé : ");
				Label lResumeRep = new Label("" + this.livre.getResume());
				lResume.setFont(font);
				lResumeRep.setFont(font);
				lResume.setPrefWidth(110);
				lResumeRep.setPrefWidth(320);
				HBox hboxResume = new HBox();
				hboxResume.getChildren().addAll(lResume,lResumeRep);
				hboxResume.setAlignment(Pos.CENTER);
				hboxResume.setSpacing(30);
				//HBOX TYPE
			//Label Type
		Label lType = new Label("Type : ");
		Label lTypeRep = new Label("" + this.livre.getType());
		lType.setFont(font);
		lTypeRep.setFont(font);
		lType.setPrefWidth(110);
		lTypeRep.setPrefWidth(320);
		HBox hboxType = new HBox();
		hboxType.getChildren().addAll(lType,lTypeRep);
		hboxType.setAlignment(Pos.CENTER);
		hboxType.setSpacing(30);
			//??? Type
		//HBOX GENRE
			//Label Genre
		Label lGenre = new Label("Genre : ");
		Label lGenreRep = new Label("Genre à définir");
		lGenre.setFont(font);
		lGenreRep.setFont(font);
		lGenre.setPrefWidth(110);
		lGenreRep.setPrefWidth(320);
		
			//??? Genre
		
		
		
		TableColumn<Genre,String> tableColNomGenre= new TableColumn<Genre, String>("Genre");
		tableGenreAff.getColumns().add(tableColNomGenre);
		tableColNomGenre.setCellValueFactory(new PropertyValueFactory<>("nom"));
		ObservableList<Genre> listG = FXCollections.observableArrayList(this.livre.getGenre());
		tableGenreAff.setItems(listG);
		tableGenreAff.setPrefSize(320, 200);
		tableGenreAff.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		HBox hboxGenre = new HBox();
		hboxGenre.getChildren().addAll(lGenre,tableGenreAff);
		hboxGenre.setAlignment(Pos.CENTER);
		hboxGenre.setSpacing(30);
		
		
		
		
		//Label EstEmprunte
		Label lEmprunt = new Label("Emprunté ?");
		Label lEmpruntRep = new Label();
		if (this.livre.isEstEmprunte()) {
			lEmpruntRep.setText("Oui");
		} else {
			lEmpruntRep.setText("Non");
		}
		lEmprunt.setFont(font);
		lEmpruntRep.setFont(font);
		lEmprunt.setPrefWidth(110);
		lEmpruntRep.setPrefWidth(320);
		HBox hboxEmprunt = new HBox();
		hboxEmprunt.getChildren().addAll(lEmprunt,lEmpruntRep);
		hboxEmprunt.setAlignment(Pos.CENTER);
		hboxEmprunt.setSpacing(30);
		
		//EMPRUNTEUR NOM
		Label lNEmprunteur = new Label("Nom");
		Label lNEmprunteurRep = new Label();
		if (this.livre.isEstEmprunte()) {
			lNEmprunteurRep.setText(this.livre.getEmprunteur().getNom());
		} else {
			lNEmprunteurRep.setText("null");
		}
		lNEmprunteur.setFont(font);
		lNEmprunteurRep.setFont(font);
		lNEmprunteur.setPrefWidth(110);
		lNEmprunteurRep.setPrefWidth(320);
		HBox hboxNEmprunteur = new HBox();
		hboxNEmprunteur.getChildren().addAll(lNEmprunteur,lNEmprunteurRep);
		hboxNEmprunteur.setAlignment(Pos.CENTER);
		hboxNEmprunteur.setSpacing(30);
		
		//EMPRUNTEUR PRENOM
		Label lPEmprunteur = new Label("Prénom");
		Label lPEmprunteurRep = new Label();
		if (this.livre.isEstEmprunte()) {
			lPEmprunteurRep.setText(this.livre.getEmprunteur().getPrenom());
		} else {
			lPEmprunteurRep.setText("null");
		}
		lPEmprunteur.setFont(font);
		lPEmprunteurRep.setFont(font);
		lPEmprunteur.setPrefWidth(110);
		lPEmprunteurRep.setPrefWidth(320);
		HBox hboxPEmprunteur = new HBox();
		hboxPEmprunteur.getChildren().addAll(lPEmprunteur,lPEmprunteurRep);
		hboxPEmprunteur.setAlignment(Pos.CENTER);
		hboxPEmprunteur.setSpacing(30);
		
		
		
		//VBOX final
		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(hboxISBN,hboxTitre,hboxAuteur,hboxEditeur,hboxResume,hboxType,hboxGenre);
		vbox1.setSpacing(10);
		vbox1.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(hboxEmprunt,hboxNEmprunteur,hboxPEmprunteur);
		vbox2.setSpacing(10);
		vbox2.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");
		
		
		VBox vboxFinal = new VBox();
		if (!this.livre.isEstEmprunte()) {
			vboxFinal.getChildren().addAll(vbox1,hboxEmprunt);
		} else {
			vboxFinal.getChildren().addAll(vbox1,vbox2);
		}
		
		vboxFinal.setSpacing(40);
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
}
