package provasocket;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientConnessione {

	public static void main(String[] args) {
		int porta = 3500;
		String messaggio = "";
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader riga = new BufferedReader(reader);
		String stringa="";
		try {
			Socket connessione;
			String server = "localhost";
			connessione = new Socket(server, porta);
			System.out.println("connessione aperta");
			BufferedReader in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
			PrintWriter out = new PrintWriter(connessione.getOutputStream(), true);
			out.println("Sono il Client!!!");
			do {
				messaggio = in.readLine();
				System.out.println("Messaggio dal server: " + messaggio);
				messaggio = in.readLine();
				System.out.println("Messaggio dal server: " + messaggio);
				System.out.println("Manda un messaggio al server(per chiudere la chat:ESCI)");
				stringa=riga.readLine();
				out.println(stringa);
			}while(stringa.compareTo("esci")!=0);
			connessione.close();
			System.out.println("connessione chiusa");
		} catch (IOException ex) {
			System.out.println("Server non trovato sulla porta: " + porta);
		}
	}
}
