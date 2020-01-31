package provasocket;

import java.net.*;
import java.io.*;

public class ServerConnessione {

	public static void main(String[] args) {
		ServerSocket sSocket;
		int porta = 3500;
		int millisecondi = 5000;
		boolean open = true;
		String messaggio = "";
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader riga = new BufferedReader(reader);
		while (open) {
			try {
				sSocket = new ServerSocket(porta);
				sSocket.setSoTimeout(millisecondi);
				Socket connessione;
				System.out.println("in attesa di connessioni...");
				countdown thread = new countdown(millisecondi);
				thread.start();
				connessione = sSocket.accept();
				System.out.println("Connessione stabilita");
				thread.setConnesso(true);
				BufferedReader in = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
				PrintWriter out = new PrintWriter(connessione.getOutputStream(), true);
				messaggio = in.readLine();
				System.out.println("Messaggio dal client: " + messaggio);
				out.println("Sono il Server!!!");
				/*while (messaggio.compareTo("chiudi") != 0) 
				{
					System.out.println("Chat con il Client");
					riga.readLine();
					out.println(riga);
					messaggio = in.readLine();
					System.out.println("Messaggio dal Client: " + messaggio);
				}*/
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
