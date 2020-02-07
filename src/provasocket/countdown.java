package provasocket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		while(tempo>0 && connesso==false)
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
	
	public void setConnesso(){
		this.connesso=true;
	}
}
