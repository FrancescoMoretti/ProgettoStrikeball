package provasocket;

import java.io.*;
import java.net.*;

public class ClientChat {
	Socket connessione;
	BufferedReader in;
	PrintWriter out;
	InputStreamReader reader = new InputStreamReader(System.in);
	BufferedReader riga = new BufferedReader(reader);
	
	public ClientChat(Socket connessione) {
		this.connessione = connessione;
		try {
			in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			out = new PrintWriter(connessione.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Errore nella creazione dell'oggetto per ricevere messaggi");
		}
	}
	public void chat() {
		String stringa = "";
		out.println("Sono il Client!!!");
		riceviMessaggio();
		do {
			riceviMessaggio();
			stringa=inviaMessaggio();
		} while (stringa.compareTo("ESCI") != 0);
	}

	public void riceviMessaggio() {
		String messaggio = "";
		try {
			messaggio = in.readLine();
			System.out.println("Messaggio dal server: " + messaggio);
		} catch (IOException e) {
			System.out.println("Errore nella ricezione del messaggio");
		}
	}

	public String inviaMessaggio() {
		String stringa = "";
		try {
			System.out.println("Manda un messaggio al server (per terminare la chat scrivi:'ESCI')");
			stringa = riga.readLine();
			out.println(stringa);
			return stringa;
		} catch (IOException e) {
			System.out.println("Errore nell'invio di un messaggio");
			return "ESCI";
		}
	}
}
