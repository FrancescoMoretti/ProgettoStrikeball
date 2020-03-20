package strikeball_unicast;

import java.util.*;

public class Gioco {
	private String[] codice;
	private String[] tentativo;
	private int[] vet;
	private int vite=5;
	private int strikes;
	private int balls;

	public Gioco() {
		vet = new int[4];
		tentativo = new String[4];
		azzera();
		vet[0] = (int) (Math.random() * 10);
		do {
			vet[1] = (int) (Math.random() * 10);
		} while (vet[0] == vet[1]);
		do {
			vet[2] = (int) (Math.random() * 10);
		} while (vet[2] == vet[0] || vet[2] == vet[1]);
		do {
			vet[3] = (int) (Math.random() * 10);
		} while (vet[3] == vet[0] || vet[3] == vet[1] || vet[3] == vet[2]);
		codice = new String[4];
		int n=0;
		while(n<4) 
		{
			codice[n]=""+vet[n];
			n++;
		}
	}
	
	public String confronto(String n0, String n1, String n2, String n3) {
		azzera();
		this.tentativo[0]=n0;
		this.tentativo[1]=n1;
		this.tentativo[2]=n2;
		this.tentativo[3]=n3;
		int n=0;
		while(n<4)
		{
			if(tentativo[n].compareTo(codice[n])==0) {
				this.strikes++;
			}
			n++;
		}
		n=0;
		int i=n+1;
		while(n<4)
		{
			while(i<4) 
			{
				if(n==i) {
					
				}else {
					if(tentativo[n].compareTo(codice[i])==0) {
						this.balls++;
					}
				}
				i++;
			}
			n++;
			i=0;
		}
		String messageout;
		boolean flag=true;
		if(this.strikes==4) {
			return vittoria();
		}
		this.vite--;
		if(this.vite>0){
			return out();
		}else {
			return sconfitta();
		}
	}
	
	public String out() {
		return"Strikes: "+this.strikes+", Balls: "+this.balls+" Vite: "+this.vite;
	}
	
	public void azzera() {
		this.strikes=0;
		this.balls=0;
	}
	
	public String vittoria() {
		return"Complimenti!!! Hai indovinato";
	}
	
	public String sconfitta() {
		return"Spiacente, hai perso... Riconnettiti per giocare ancora";
	}
	
	public String bara() {
		return ""+codice[0]+codice[1]+codice[2]+codice[3];
	}
	
	public int getVite() {
		return vite;
	}
}