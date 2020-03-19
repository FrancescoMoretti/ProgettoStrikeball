package provasocket;

import java.util.*;

/*
 * @author Francesco Moretti
 */
public class countdown extends Thread{
	private int tempo;
	private boolean connesso;

	public countdown(int tempo) {
		this.tempo=tempo;
		this.connesso=false;
	}
	
	public void run(){
		while(tempo>0 && connesso==false)//finchè c'è tempo o qualcuno si connette
		{
			try {
				System.out.println(tempo/1000);
				this.sleep(1000);
				tempo=tempo-1000;
			} catch (InterruptedException ex) {
				System.out.println("ERRORE Interruzione del conto alla rovescia");
			}
		}
	}
	
	//metodo per l'interruzione del conto alla rovescia
	public void setConnesso(){
		this.connesso=true;
	}
}
