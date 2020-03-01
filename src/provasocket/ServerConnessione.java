package provasocket;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerConnessione {

	public static void main(String[] args) {
		ServerSocket sSocket;
		int porta = 3500;
		int millisecondi = 10000;
		boolean open = true;
		String messaggio = "";
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader riga = new BufferedReader(reader);
		String stringa="";
		while (open) {
			try {
				sSocket = new ServerSocket(porta);
				sSocket.setSoTimeout(millisecondi);
				Socket connessione;
				System.out.println("in attesa di connessioni...");
				countdown count = new countdown(millisecondi);
				count.start();
				connessione = sSocket.accept();
				count.setConnesso();
				System.out.println("Connessione stabilita");
				BufferedReader in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
				PrintWriter out = new PrintWriter(connessione.getOutputStream(), true);
				messaggio = in.readLine();
				System.out.println("Messaggio dal client: " + messaggio);
				out.println("Sono il Server!!!");
				do {
					System.out.println("Manda un messaggio al client");
					stringa=riga.readLine();
					out.println(stringa);
					messaggio = in.readLine();
					System.out.println("Messaggio dal client: " + messaggio);
				}while(messaggio.compareTo("ESCI")!=0);
				sSocket.close();
				System.out.println("connessione chiusa");
			} catch (SocketTimeoutException ex) {
				System.out.println("Tempo scaduto sulla porta " + porta);
				open = false;
			} catch (IOException ex) {
				System.out.println("Client non trovati sulla porta: " + porta);
			}
		}
	}
}
