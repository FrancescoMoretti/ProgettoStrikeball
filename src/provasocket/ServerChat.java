package provasocket;

import java.io.*;
import java.net.*;

public class ServerChat {
	Socket connessione;
	BufferedReader in;
	PrintWriter out;
	InputStreamReader reader = new InputStreamReader(System.in);
	BufferedReader riga = new BufferedReader(reader);

	public ServerChat(Socket connessione) {
		this.connessione = connessione;
		try {
			in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			out = new PrintWriter(connessione.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Errore nella creazione dell'oggetto per ricevere messaggi");
		}
	}

	public void chat() {
		String messaggio = "";
		messaggio = riceviMessaggio();
		out.println("Sono il server!!!");
		do {
			inviaMessaggio();
			messaggio = riceviMessaggio();
		} while (messaggio.compareTo("ESCI") != 0);
	}

	public String riceviMessaggio() {
		String messaggio = "";
		try {
			messaggio = in.readLine();
			System.out.println("Messaggio dal client: " + messaggio);
		} catch (IOException e) {
			System.out.println("Errore nella ricezione del messaggio");
			return "ESCI";
		}
		return messaggio;
	}

	public void inviaMessaggio() {
		String stringa = "";
		try {
			System.out.println("Manda un messaggio al client");
			stringa = riga.readLine();
			out.println(stringa);
		} catch (IOException e) {
			System.out.println("Errore nell'invio di un messaggio");
		}
	}

	public void chiudi() {
		try {
			in.close();
			out.close();
			reader.close();
			riga.close();
		} catch (IOException e) {
			System.out.println("Errore nella chiusura degli stream lato server");
		}
	}
}
