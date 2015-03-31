package pl.kosinski;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JOptionPane;

public class szyfr {
	public static void szyfruj(FileOutputStream strumien,
			ArrayList<String> lista) throws Exception {

		String skey = User.get(Projekt.aktualnyUser).getSzyfr();
		
		DESKeySpec dks = new DESKeySpec(skey.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey key = skf.generateSecret(dks);

		Cipher c = Cipher.getInstance("DES");
		c.init(Cipher.ENCRYPT_MODE, key);
		CipherOutputStream cos = new CipherOutputStream(strumien, c);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(cos));
		for (String x : lista) {
			pw.println(x);
		}
		pw.close();

	}

	public static ArrayList<String> deszyfruj(FileInputStream strumien)
			throws Exception {

		String skey = User.get(Projekt.aktualnyUser).getSzyfr();
		while (skey.length() < 8) {
			skey = JOptionPane.showInputDialog(null,
					"Podaj has³o do pliku (min. 8 znaków)");
		}

		DESKeySpec dks = new DESKeySpec(skey.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey key = skf.generateSecret(dks);

		Cipher c = Cipher.getInstance("DES");

		c.init(Cipher.DECRYPT_MODE, key);
		CipherInputStream cis = new CipherInputStream(strumien, c);
		BufferedReader br = new BufferedReader(new InputStreamReader(cis));
		Scanner sc = new Scanner(br);

		ArrayList<String> lista = new ArrayList<String>();

		while (sc.hasNextLine()) {
			lista.add(sc.nextLine());
		}

		sc.close();
		br.close();
		cis.close();
		return lista;

	}

}