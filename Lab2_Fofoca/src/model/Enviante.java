package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Enviante extends Thread {

	private Socket host;
	private String fofoca;
	private String hostIp;
	private int porta;
	
	public Enviante(String fofoca, int porta) {
		this.fofoca = fofoca;
		this.porta = porta;
	}

	public void run() {
		try {
			
			this.host = new Socket("localhost", porta);
			System.out.println("\\\\\\Conexao estabelecida com " + host.getInetAddress().getHostName());
			PrintWriter escritor = new PrintWriter(host.getOutputStream(), true);

			escritor.println(fofoca);
			System.out.println("\\\\\\Enviando '" + fofoca + "' para " + porta);
			host.close();
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
	}
}
