package pl.kosinski;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Projekt {

	private ArrayList<String> lista = new ArrayList<String>();
	static boolean wyjscie = false;
	public static String aktualnyUser = null;
	public static void main(String[] args) {
		Projekt p = new Projekt();
		//Hasla ponad 8 znaków !!
			User.add("admin", "12345678");
			User.add("marian", "qwertyui");
			
			while(!wyjscie){
			aktualnyUser = JOptionPane.showInputDialog(null,"Podaj nazwê U¿ytkownika");
			if(aktualnyUser == null){
				wyjscie = true;
			}
			
			if(User.istnieje(aktualnyUser)){
			p.loguj(User.get(aktualnyUser));
			}
			}
			JOptionPane.showMessageDialog(null, "Dziêki :)");


	}

	
	
	
	
	
	private void loguj(User u) {
		if(u.verify(JOptionPane.showInputDialog(null,"Podaj has³o"))){
			menu();
		}
		}
		
	

	void wyswietl() {
		for (String s : lista) {
			System.out.println(s);

		}
	}

	private void add(String s) {
		lista.add(s);
	}

	private void menu() {
		String tekst = "";
		while (!wyjscie) {
			tekst = JOptionPane.showInputDialog(null,
					"menu: \n 1.Nowy Wpis \n 2. Wyœwietl \n 3. Zmiana Wpisu \n 4. Kasowanie Wpisu \n 5. Zapis do pliku \n 6. Odczyt z pliku \n 0. Wyjœcie");
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

		try {
			plik = new File("plik uzytkownika " + aktualnyUser +".txt");
			lista = szyfr.deszyfruj(new FileInputStream(plik));
		} catch (Exception e) {
			System.out.println("B³¹d odczytu pliku");
			e.printStackTrace();
			return;
		}
		
		
		System.out.println("Plik wczytany");
	}

	private void zapisz() {
		if(lista.isEmpty()){
			System.out.println("Nie ma czego zapisaæ");
			return;
		}
		File plik = new File("plik uzytkownika " + aktualnyUser +".txt");
		try{
			

		
		}catch(Exception e){
			System.out.println("B³¹d szyfrowania");
			e.printStackTrace();
		}
		FileOutputStream strumien = null;

		try {
			strumien = new FileOutputStream(plik);

		} catch (Exception e) {
			System.out.println("NIe mozna zapisaæ pliku");
		}
		try{
		szyfr.szyfruj(strumien,lista);
			strumien.close();
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
			System.out.println("Podaj liczbê!");
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
			System.out.println("Podaj liczbê!");
		}
		
		
		if( x >= 0 && x < lista.size()){
			tekst = JOptionPane.showInputDialog(null,"Podaj now¹ wartoœæ elementu");
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
