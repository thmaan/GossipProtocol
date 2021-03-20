package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Vector;

public class ThreadRecebente extends Thread {

	private Socket host;
	private Vector<String> vetorFofocas;

	public ThreadRecebente(Socket host, Vector<String> vetorFofocas) {
		this.host = host;
		this.vetorFofocas = vetorFofocas;
	}

	public void run() {
		try {
			this.host.setSoTimeout(2000);
			BufferedReader leitor = new BufferedReader(new InputStreamReader(host.getInputStream()));

			String fofoca;
			fofoca = leitor.readLine();
		
			if (fofoca != null) {
				if (!vetorFofocas.contains(fofoca)) {
					vetorFofocas.add(fofoca);
							
				}
				else {
					System.out.println("Encontrei a minha fofoca");
					currentThread().sleep(50000000);
				}
				new Enviante(fofoca, 9001).start();
			}
			
			System.out.println(vetorFofocas);
			System.out.println("\\\\Recebida fofoca: '" + fofoca + "' da porta "
						+ host.getLocalPort());

				 
						
			
			host.close();
		} catch (IOException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}