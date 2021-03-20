package application;

import java.util.Scanner;
import java.util.Vector;

import model.Enviante;
import model.Recebente;
import model.Vizinho;

public class Main {

	public static void main(String[] args) {
	
		Vector<String> vetorFofocas = new Vector<>();
		
		Recebente receptor = new Recebente(vetorFofocas,9000);
		receptor.start();	
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("9000 fofoque: ");

		String fofoca = keyboard.nextLine();
		vetorFofocas.add(fofoca);
		new Enviante(fofoca, 9001).start();
	}
}