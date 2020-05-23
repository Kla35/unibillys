import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javafx.collections.FXCollections;

public class Gestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8773969948991530917L;
	
	public Gestion() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public static String verifyVersionLocal() throws ClassNotFoundException{
		String str="";
		ObjectInputStream entree;
		try {
			entree = new ObjectInputStream(new FileInputStream("version.data"));
			str=(String)entree.readObject();
			System.out.println("V_Local : " + str);
			entree.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Main.lock = 0;
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		  return str;
	}
	
	public static String verifyVersionServer(){
		String serveur = "quenouillere.fr";
		int port = 21;
		String user = "";
		String password = "";
		user = "u663578832.test";
		password = "test35";
		
		FTPClient ftpClient = new FTPClient();
		String str = "";
		String a = "update/";
		File file = null;
		String chemin = null;
		boolean res = false;
		
		try {
			  ftpClient.connect(serveur, port);
			  ftpClient.login(user, password);
			  ftpClient.enterLocalPassiveMode();
			  ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		  } catch (Exception e) {
			  
		  }
		  
		  try {
		  file = new File("version.data");
		  chemin = a + "version.data";
		  OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(file));
		  System.out.println("Début du download");
		  //résultat de l'upload
		  res = ftpClient.retrieveFile(chemin, outputStream1);
		  //fermet le flut de lecture
		  outputStream1.close();
		   if (res==true) {
		     System.out.println("Le fichier " + chemin + " a été transféré avec succès");
		     ObjectInputStream entree;
				try {
					entree = new ObjectInputStream(new FileInputStream("version.data"));
					str=(String)entree.readObject();
					System.out.println("V_server : " + str);
					entree.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					Main.lock = 0;
					e.printStackTrace();
				} catch (IOException e){
					e.printStackTrace();
				}
		   }
		  }catch (Exception e) {
			  
		  }
		  return str;
	}
	
	public static void saveLivre(){
		ObjectOutputStream sortie;
		ArrayList<Livre> al = new ArrayList<Livre>(Main.list);
		try {
			sortie = new ObjectOutputStream(new FileOutputStream("livre.data"));
			sortie.writeObject(al);
			sortie.writeObject(Livre.nb);
			sortie.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static void loadLivre() throws Exception{
		ObjectInputStream entree;
		ArrayList<Livre> list;
		try {
			entree = new ObjectInputStream(new FileInputStream("livre.data"));
			list=(ArrayList<Livre>)entree.readObject();
			Main.list = FXCollections.observableArrayList(list);
			Livre.nb = (int)entree.readObject();
			entree.close();
			Fenetre.updateTableLivre();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Livre.nb = -1;
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void saveEmprunteur(){
		ObjectOutputStream sortie;
		ArrayList<Emprunteur> al = new ArrayList<Emprunteur>(Main.listempr);
		try {
			sortie = new ObjectOutputStream(new FileOutputStream("emprunteur.data"));
			sortie.writeObject(al);
			sortie.writeObject(Emprunteur.nb);
			sortie.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static void loadEmprunteur() throws Exception{
		ObjectInputStream entree;
		ArrayList<Emprunteur> listEm;
		try {
			entree = new ObjectInputStream(new FileInputStream("emprunteur.data"));
			listEm=(ArrayList<Emprunteur>)entree.readObject();
			Emprunteur.nb = (int)entree.readObject();
			Main.listempr = FXCollections.observableArrayList(listEm);
			entree.close();
			Fenetre.updateTableEmprunteur();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Emprunteur.nb = -1;
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void saveEmprunt(){
		ObjectOutputStream sortie;
		ArrayList<Lien> al = new ArrayList<Lien>(Main.listemprunt);
		try {
			sortie = new ObjectOutputStream(new FileOutputStream("emprunt.data"));
			sortie.writeObject(al);
			sortie.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static void loadEmprunt() throws Exception{
		ObjectInputStream entree;
		ArrayList<Lien> list2;
		try {
			entree = new ObjectInputStream(new FileInputStream("emprunt.data"));
			list2=(ArrayList<Lien>)entree.readObject();
			Main.listemprunt = FXCollections.observableArrayList(list2);
			entree.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void saveGenre(){
		ObjectOutputStream sortie;
		ArrayList<Genre> al = new ArrayList<Genre>(Main.listgenre);
		try {
			sortie = new ObjectOutputStream(new FileOutputStream("genre.data"));
			sortie.writeObject(al);
			sortie.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static void loadGenre() throws Exception{
		ObjectInputStream entree;
		ArrayList<Genre> list2;
		try {
			entree = new ObjectInputStream(new FileInputStream("genre.data"));
			list2=(ArrayList<Genre>)entree.readObject();
			Main.listgenre = FXCollections.observableArrayList(list2);
			entree.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void saveLock(){
		ObjectOutputStream sortie;
		try {
			sortie = new ObjectOutputStream(new FileOutputStream("lock.data"));
			sortie.writeObject(Main.lock);
			sortie.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	public static void loadLock() throws Exception{
		ObjectInputStream entree;
		try {
			entree = new ObjectInputStream(new FileInputStream("lock.data"));
			Main.lock=(int)entree.readObject();
			entree.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Main.lock = 0;
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
}