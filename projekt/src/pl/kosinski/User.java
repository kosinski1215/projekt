package pl.kosinski;

import java.util.HashMap;

public class User {
	private static HashMap<String,User> uzytkownicy = new HashMap<String,User>();

	private String haslo;
	
	public User(String haslo){

		this.haslo=haslo;

	}
	
	public boolean verify(String haslo){
		return this.haslo.equals(haslo);
	}
	
	static void add(String login, String haslo){
		if(!uzytkownicy.containsKey(login)){
		uzytkownicy.put(login, new User(haslo));
		}else{
			System.out.println("Taki u¿ytkownik ju¿ istnieje");
		}

	}
	
static boolean istnieje(String nazwa){
		return uzytkownicy.containsKey(nazwa);
	}
	static User get(String nazwa){
		return uzytkownicy.get(nazwa);
	}
	
	
}



