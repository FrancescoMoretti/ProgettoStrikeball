package provasocket;

import java.net.*;
import java.io.*;

public class ClientThread extends Thread{
	private Socket connessione;
	
	public ClientThread(Socket soc){
		connessione=soc;
	}
	
	public void run(){
		System.out.println("Connessione stabilita");
	}
}