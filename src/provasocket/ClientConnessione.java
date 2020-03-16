package provasocket;

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientConnessione {

	public static void main(String[] args) {
		int porta = 3500;
		String messaggio = "";
		try {
			Socket connessione;
			String server = "localhost";
			connessione = new Socket(server, porta);
			System.out.println("connessione aperta");
			ClientChat chat=new ClientChat(connessione);
			chat.chat();
			connessione.close();
			System.out.println("connessione chiusa");
		} catch (IOException ex) {
			System.out.println("Server non trovato sulla porta: " + porta);
		}
	}
}
