package strikeball_unicast;

import java.io.*;
import java.net.*;

public class ServerChat {
	private Socket connessione;
	private BufferedReader in;
	private PrintWriter out;
	private InputStreamReader reader = new InputStreamReader(System.in);
	private BufferedReader riga = new BufferedReader(reader);
	private Gioco partita;
	private String[] vet;

	public ServerChat(Socket connessione) {
		this.connessione = connessione;
		try {
			in = new BufferedReader(new InputStreamReader(this.connessione.getInputStream()));
			out = new PrintWriter(this.connessione.getOutputStream(), true);
			partita = new Gioco();
			vet = new String[4];
			vet[0] = "";
			vet[1] = "";
			vet[2] = "";
			vet[3] = "";
		} catch (IOException e) {
			System.out.println("Errore nella creazione dell'oggetto per ricevere messaggi");
		}
	}

	public void chat() {
		String messaggio = "";
		out.println("Partita iniziata, indovina 4 numeri da 0 a 9, hai " + partita.getVite() + " vite" + " ***per test"
				+ partita.bara() + "***");
		while (messaggio.compareTo("Spiacente, hai perso... Riconnettiti per giocare ancora") != 0
				&& messaggio.compareTo("Complimenti!!! Hai indovinato") != 0) {
			riceviMessaggio();
			messaggio = inviaMessaggio();
		}
	}

	public void riceviMessaggio() {
		try {
			this.vet = in.readLine().split("");
		} catch (IOException e) {
			System.out.println("Errore nella ricezione del messaggio");
		}
	}

	public String inviaMessaggio() {
		String messaggio = "";
		messaggio = partita.confronto(vet[0], vet[1], vet[2], vet[3]);
		out.println(messaggio);
		return messaggio;
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