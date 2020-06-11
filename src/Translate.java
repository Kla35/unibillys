
public class Translate {
	//WINDOW TEXT
	static public String WINDOW_ADVANCED_SEARCH;
	static public String WINDOW_NORMAL_DISPLAY;
	static public String WINDOW_SHOW_BOOK;
	static public String WINDOW_ADD_BOOK;
	static public String WINDOW_REMOVE_BOOK;
	static public String WINDOW_MODIFY_BOOK;
	static public String WINDOW_CREATE_BORROW;
	static public String WINDOW_DELETE_BORROW;
	static public String WINDOW_SHOW_LIST_BORROWS;
	static public String WINDOW_ADD_BORROWER;
	static public String WINDOW_MODIFY_BORROWER;
	static public String WINDOW_DELETE_BORROWER;
	static public String WINDOW_LIST_BORROW_BORROWER;
	static public String WINDOW_LABEL_SEARCH_BOOK;
	static public String WINDOW_LABEL_SEARCH_BORROWER;
	
	//MENU BAR TEXT
	static public String MENUBAR_FILE;
	static public String MENUBAR_FILE_EXIT;
	static public String MENUBAR_EDIT;
	static public String MENUBAR_EDIT_IMPORT;
	static public String MENUBAR_EDIT_EXPORT;
	static public String MENUBAR_EDIT_OPTIONS;
	static public String MENUBAR_HELP;
	static public String MENUBAR_HELP_DOCUMENTATION;
	static public String MENUBAR_HELP_ABOUT;
	
	public Translate(String language) {
		switch(language) {
		case "Français":
			Translate.translateFrench();
			break;
		case "English" :
			Translate.translateEnglish();
			break;
		}
	}
	
	public static void translateEmpty() {
		Translate.WINDOW_ADVANCED_SEARCH = "";
		Translate.WINDOW_NORMAL_DISPLAY = "";
		Translate.WINDOW_SHOW_BOOK = "";
		Translate.WINDOW_ADD_BOOK = "";
		Translate.WINDOW_REMOVE_BOOK = "";
		Translate.WINDOW_MODIFY_BOOK = "";
		Translate.WINDOW_CREATE_BORROW= "";
		Translate.WINDOW_DELETE_BORROW = "";
		Translate.WINDOW_SHOW_LIST_BORROWS = "";
		Translate.WINDOW_ADD_BORROWER = "";
		Translate.WINDOW_MODIFY_BORROWER = "";
		Translate.WINDOW_DELETE_BORROWER = "";
		Translate.WINDOW_LIST_BORROW_BORROWER = "";
		Translate.WINDOW_LABEL_SEARCH_BOOK = "";
		Translate.WINDOW_LABEL_SEARCH_BORROWER = "";
		
		//MENU BAR TEXT
		Translate.MENUBAR_FILE = "";
		Translate.MENUBAR_FILE_EXIT = "";
		Translate.MENUBAR_EDIT = "";
		Translate.MENUBAR_EDIT_IMPORT = "";
		Translate.MENUBAR_EDIT_EXPORT = "";
		Translate.MENUBAR_EDIT_OPTIONS = "";
		Translate.MENUBAR_HELP = "";
		Translate.MENUBAR_HELP_DOCUMENTATION = "";
		Translate.MENUBAR_HELP_ABOUT = "";
	}
	
	public static void translateFrench() {
		Translate.WINDOW_ADVANCED_SEARCH = "Recherche Avancée";
		Translate.WINDOW_NORMAL_DISPLAY = "Affichage Normal";
		Translate.WINDOW_SHOW_BOOK = "Afficher";
		Translate.WINDOW_ADD_BOOK = "Ajouter";
		Translate.WINDOW_REMOVE_BOOK = "Supprimer";
		Translate.WINDOW_MODIFY_BOOK = "Modifier";
		Translate.WINDOW_CREATE_BORROW= "Créer\nEmprunt";
		Translate.WINDOW_DELETE_BORROW = "Supprimer\nEmprunt";
		Translate.WINDOW_SHOW_LIST_BORROWS = "Liste des\nlivres\nempruntés";
		Translate.WINDOW_ADD_BORROWER = "Ajouter";
		Translate.WINDOW_MODIFY_BORROWER = "Modifier";
		Translate.WINDOW_DELETE_BORROWER = "Supprimer";
		Translate.WINDOW_LIST_BORROW_BORROWER = "Emprunt\ndu lecteur";
		Translate.WINDOW_LABEL_SEARCH_BOOK = "Rechercher un livre :";
		Translate.WINDOW_LABEL_SEARCH_BORROWER = "Rechercher un emprunteur :";
		
		//MENU BAR TEXT
		Translate.MENUBAR_FILE = "";
		Translate.MENUBAR_FILE_EXIT = "";
		Translate.MENUBAR_EDIT = "";
		Translate.MENUBAR_EDIT_IMPORT = "";
		Translate.MENUBAR_EDIT_EXPORT = "";
		Translate.MENUBAR_EDIT_OPTIONS = "";
		Translate.MENUBAR_HELP = "";
		Translate.MENUBAR_HELP_DOCUMENTATION = "";
		Translate.MENUBAR_HELP_ABOUT = "";
	}
	
	public static void translateEnglish() {
		Translate.WINDOW_ADVANCED_SEARCH = "";
		Translate.WINDOW_NORMAL_DISPLAY = "";
		Translate.WINDOW_SHOW_BOOK = "";
		Translate.WINDOW_ADD_BOOK = "";
		Translate.WINDOW_REMOVE_BOOK = "";
		Translate.WINDOW_MODIFY_BOOK = "";
		Translate.WINDOW_CREATE_BORROW= "";
		Translate.WINDOW_DELETE_BORROW = "";
		Translate.WINDOW_SHOW_LIST_BORROWS = "";
		Translate.WINDOW_ADD_BORROWER = "";
		Translate.WINDOW_MODIFY_BORROWER = "";
		Translate.WINDOW_DELETE_BORROWER = "";
		Translate.WINDOW_LIST_BORROW_BORROWER = "";
		Translate.WINDOW_LABEL_SEARCH_BOOK = "";
		Translate.WINDOW_LABEL_SEARCH_BORROWER = "";
		
		//MENU BAR TEXT
		Translate.MENUBAR_FILE = "";
		Translate.MENUBAR_FILE_EXIT = "";
		Translate.MENUBAR_EDIT = "";
		Translate.MENUBAR_EDIT_IMPORT = "";
		Translate.MENUBAR_EDIT_EXPORT = "";
		Translate.MENUBAR_EDIT_OPTIONS = "";
		Translate.MENUBAR_HELP = "";
		Translate.MENUBAR_HELP_DOCUMENTATION = "";
		Translate.MENUBAR_HELP_ABOUT = "";
	}
}
