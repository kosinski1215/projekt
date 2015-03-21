import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class granie {

	// Po co try , jak ma sie popsuc to i tak sie popsuje :D
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException,
			LineUnavailableException { // Wywalanie wyjatkow w kosmos
	
		URL url = granie.class.getResource("JAR.wav"); // Jaja z adresem pliku bo jest w paczce jar

		AudioInputStream strumien = AudioSystem.getAudioInputStream(url);
		Clip klip = AudioSystem.getClip(); // A tu ju¿ z górki ksiazkowo
		klip.open(strumien);
		klip.setFramePosition(0);
		klip.start();
		klip.loop(99999999);// I petla do konca swiata
		
		while (klip.isOpen()) { //A to ¿eby sie nie zamknal i gra³
		}
	}
}
