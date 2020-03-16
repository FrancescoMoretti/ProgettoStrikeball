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
				ServerChat chat=new ServerChat(connessione);
				chat.chat();
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
