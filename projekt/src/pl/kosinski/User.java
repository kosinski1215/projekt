package pl.kosinski;

public class User {

	private String haslo;
	public User(String login, String haslo){

		this.haslo=haslo;
	}
	public boolean verify(String haslo){
		return this.haslo.equals(haslo);
	}
	
	
	
	
}



