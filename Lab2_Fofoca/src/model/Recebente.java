package model;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

public class Recebente extends Thread {

	private ServerSocket portaServer;
	private Socket host;
	private int porta;
	private Vector<String> vetorFofocas;
	public Recebente(Vector<String> vetorFofocas, int porta) {
		try {
			this.portaServer = new ServerSocket(porta);
			this.vetorFofocas = vetorFofocas;
			System.out.println("\\Recebendo no IP " + "localhost" + " porta " + porta);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		while (true) {
			try {
				this.host = portaServer.accept();
			
				new ThreadRecebente(host, vetorFofocas).start();
				System.out.println("\\Nova conexao");
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}