package multicast;

import java.net.*;
import java.io.*;

public class MulticastClient {
	public static void main(String[] args) {
		byte[] bufferIN = new byte[1024];
		// parametri del server
		int porta = 3500;
		String gruppo = "225.4.5.6";
		try {
			MulticastSocket socket = new MulticastSocket(porta);
			socket.joinGroup(InetAddress.getByName(gruppo));
			DatagramPacket packetIN = new DatagramPacket(bufferIN, bufferIN.length);
			socket.receive(packetIN);
			// visualizzo parametri e dati raccolti
			System.out.println("Ho ricevuto i dati di lunghezza: " + packetIN.getLength() + " da: "
					+ packetIN.getAddress().toString() + " porta: " + packetIN.getPort());
			System.out.write(packetIN.getData(), 0, packetIN.getLength());
			System.out.println();
			// lascio il gruppo
			socket.leaveGroup(InetAddress.getByName(gruppo));
			// chiudo il socket
			socket.close();
		} catch (IOException e) {
			System.out.println("Errore nella crezione del client");
		}
	}
}
