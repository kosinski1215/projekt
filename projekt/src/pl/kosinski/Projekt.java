package pl.kosinski;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Projekt {
	private ArrayList<String> lista = new ArrayList<String>();
	static boolean wyjscie = false;
	private static String aktualnyUser = null;
	public static void main(String[] args) {
		Projekt p = new Projekt();
		HashMap<String,User> uzytkownicy = new HashMap<String,User>();
			User marian = new User("marian","4321");
			User admin = new User("admin","1234");
			
			uzytkownicy.put("admin", admin);
			uzytkownicy.put("marian", marian);
			
			while(!wyjscie){
			aktualnyUser = JOptionPane.showInputDialog(null,"Podaj nazw� U�ytkownika");
			if(aktualnyUser == null){
				wyjscie = true;
			}
			if(uzytkownicy.containsKey(aktualnyUser)){
			p.loguj(uzytkownicy.get(aktualnyUser));
			}
			}
			JOptionPane.showMessageDialog(null, "Dzi�ki :)");


	}

	
	
	
	
	
	private void loguj(User u) {
		if(u.verify(JOptionPane.showInputDialog(null,"Podaj has�o"))){
			menu();
		}
		}
		
	

	void wyswietl() {
		for (String s : lista) {
			System.out.println(s);

		}
	}

	void add(String s) {
		lista.add(s);
	}

	void menu() {
		String tekst = "";
		while (!wyjscie) {
			tekst = JOptionPane.showInputDialog(null,
					"menu: \n 1.Nowy Wpis \n 2. Wy�wietl \n 3. Zmiana Wpisu \n 4. Kasowanie Wpisu \n 5. Zapis do pliku \n 6. Odczyt z pliku \n 0. Wyj�cie");
			if(tekst == null){
				wyjscie = true;
				break;
			}
			switch (tekst) {
			case "1":
				Wpisz();
				break;
			case "2":
				wyswietl();
				break;
			case "3":
				zamien();
				break;
			case "4":
				usun();
				break;
			case "5":
				zapisz();
				break;
			case "6":
				odczytaj();
				break;
			case "0":
				wyjscie = true;
				break;
			}
		}
	}

	private void odczytaj() {
		while(!lista.isEmpty()){
			lista.remove(0);
		}
		File plik = null;
		Scanner odczyt = null;
		try {
			plik = new File("plik uzytkownika " + aktualnyUser +".txt");
			odczyt = new Scanner(plik);
		} catch (FileNotFoundException e) {
			System.out.println("B��d odczytu pliku");
			return;
		}
		while(odczyt.hasNext()){
			lista.add(odczyt.nextLine());
		}
		odczyt.close();
		System.out.println("Plik wczytany");
	}

	private void zapisz() {
		if(lista.isEmpty()){
			System.out.println("Nie ma czego zapisa�");
			return;
		}
		File plik = new File("plik uzytkownika " + aktualnyUser +".txt");
		FileOutputStream strumien = null;
		PrintWriter writer = null;
		
		try {
			strumien = new FileOutputStream(plik);
			writer = new PrintWriter(plik);
		} catch (Exception e) {
			System.out.println("NIe mozna zapisa� pliku");
		
		}
		for(String x:lista){
			writer.println(x);
		}
		try{
			strumien.close();
			writer.close();
		}catch(Exception e){}
		
		System.out.println("Zapisano do pliku");
	}

	private void usun() {
		String tekst;
		while(true){
		tekst = JOptionPane.showInputDialog(null,"Podaj Numer Elementu (od 0 do " + (lista.size() - 1) +")");
		if(tekst == null)break;
		int x = -1;
		try{
		x = Integer.parseInt(tekst);
		}catch(Exception e){
			System.out.println("Podaj liczb�!");
		}
		if( x >= 0 && x < lista.size()){
			lista.remove(x);
			System.out.println("Usunieto element " + x);
			
		}
		
		}
	}

	private void zamien() {
		String tekst = "";
		while(true){
		tekst = JOptionPane.showInputDialog(null,"Podaj Numer Elementu (od 0 do " + (lista.size() - 1) +")");
		if(tekst == null)break;
		int x = -1;
		try{
		x = Integer.parseInt(tekst);
		}catch(Exception e){
			System.out.println("Podaj liczb�!");
		}
		
		
		if( x >= 0 && x < lista.size()){
			tekst = JOptionPane.showInputDialog(null,"Podaj now� warto�� elementu");
			lista.set(x, tekst);
		}
		}
	}

	private void Wpisz() {
		String tekst = "";
		
		while(true){
			tekst = JOptionPane.showInputDialog(null,
					"Podaj dane.");
			if(tekst == null)break;
		add(tekst);
		
		}
	}


}
