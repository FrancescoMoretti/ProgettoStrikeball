package strikeball_unicast;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientChat {
	private Socket connessione;
	private BufferedReader in;
	private PrintWriter out;
	private InputStreamReader reader = new InputStreamReader(System.in);
	private BufferedReader riga = new BufferedReader(reader);
	
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
		String stringa="";
		riceviMessaggio();
		do
		{
			inviaMessaggio();
			stringa=riceviMessaggio();
		}while(stringa.compareTo("QUIT")!=0);
		chiudi();
	}

	public String riceviMessaggio() {
		String messaggio = "";
		try {
			messaggio = in.readLine();
			System.out.println("Messaggio dal server: " + messaggio);
			if(messaggio.compareTo("Spiacente, hai perso... Riconnettiti per giocare ancora")==0 || messaggio.compareTo("Complimenti!!! Hai indovinato")==0) {
			return"QUIT";	
			}
			return messaggio;
		} catch (IOException e) {
			System.out.println("Errore nella ricezione del messaggio");
			return "QUIT";
		}
	}

	public void inviaMessaggio() {
		boolean validita;
		int n;
		String input;
		String[] stringa;
		try {
			do {
				System.out.println("Manda un messaggio al server (4 numeri da 0 a 9 Es: 1111)");
				n=0;
				validita=true;
				input = riga.readLine();
				stringa = input.split("");
				if(stringa.length!=4) {
					validita=false;
				}
				while(n<4)
				{
					if(stringa[n].equals("0")||stringa[n].equals("1")||stringa[n].equals("2")||stringa[n].equals("3")||stringa[n].equals("4")||stringa[n].equals("5")||stringa[n].equals("6")||stringa[n].equals("7")||stringa[n].equals("8")||stringa[n].equals("9")) {
					}else {
						validita=false;
					}
					n++;
				}
			}while(validita!=true);
			out.println(stringa[0]+stringa[1]+stringa[2]+stringa[3]);
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
			System.out.println("Errore nella chiusura degli stream lato client");
		}
	}
}
