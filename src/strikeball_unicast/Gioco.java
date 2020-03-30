package strikeball_unicast;

import java.util.*;

public class Gioco {
	private String[] codice;
	private String[] tentativo;
	private int[] vet;
	private int vite = 5;
	private int strikes;
	private int balls;

	public Gioco() {
		vet = new int[4];
		tentativo = new String[4];
		azzera();
		int n=0;
		while(n<vet.length) {
			vet[n] = (int) (Math.random() * 10);
			n++;
		}
		n=0;
		int i=0;
		do {
			while(i<vet.length) {
				if(n!=i)
				{
					if(vet[n]!=vet[i])
					{
						i++;
					}
					else
					{
						vet[i] = (int) (Math.random() * 10);
					}
				}
				else
				{
					i++;
				}
			}
			i=0;
			n++;
		}while(n<vet.length);
		codice = new String[4];
		int x = 0;
		while (x < 4) {
			codice[x] = "" + vet[x];
			x++;
		}
	}

	public String confronto(String n0, String n1, String n2, String n3) {
		azzera();
		this.tentativo[0] = n0;
		this.tentativo[1] = n1;
		this.tentativo[2] = n2;
		this.tentativo[3] = n3;
		int n = 0;
		while (n < 4) {
			if (tentativo[n].compareTo(codice[n]) == 0) {
				this.strikes++;
			}
			n++;
		}
		n = 0;
		int i = n + 1;
		while (n < 4) {
			while (i < 4) {
				if (n == i) {

				} else {
					if (tentativo[n].compareTo(codice[i]) == 0) {
						this.balls++;
					}
				}
				i++;
			}
			n++;
			i = 0;
		}
		String messageout;
		boolean flag = true;
		if (this.strikes == 4) {
			return vittoria();
		}
		this.vite--;
		if (this.vite > 0) {
			return out();
		} else {
			return sconfitta();
		}
	}

	public String out() {
		return "Strikes: " + this.strikes + ", Balls: " + this.balls + " Vite: " + this.vite;
	}

	public void azzera() {
		this.strikes = 0;
		this.balls = 0;
	}

	public String vittoria() {
		return "Complimenti!!! Hai indovinato";
	}

	public String sconfitta() {
		return "Spiacente, hai perso... Riconnettiti per giocare ancora";
	}

	public String bara() {
		return "" + codice[0] + codice[1] + codice[2] + codice[3];
	}

	public int getVite() {
		return vite;
	}
}