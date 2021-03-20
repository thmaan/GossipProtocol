package model;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Vizinho {

	private Integer digitoIp;
	private String maior;
	private String menor;

	public Vizinho() {
		this.digitoIp = getSplitIp();
		setMaior();
		setMenor();
	}

	public String getMaior() {
		return maior;
	}

	public String getMenor() {
		return menor;
	}

	private void setMaior() {
		int i = this.digitoIp + 1;

		do {
			try {
				if (InetAddress.getByName("192.168.1." + i).isReachable(10)) {
					break;
				} else if (i == 255) {
					i = 1;
				} else
					i++;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} while (i != digitoIp);

		this.maior = "192.168.1." + i;
	}

	private void setMenor() {
		int i = this.digitoIp - 1;

		do {
			try {
				if (InetAddress.getByName("192.168.1." + i).isReachable(200)) {
					break;
				} else if (i == 1) {
					i = 255;
				} else
					i--;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} while (i != digitoIp);

		this.menor = "192.168.1." + i;
	}

	private Integer getSplitIp() {
		String ip = null;

		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			ip = socket.getLocalAddress().getHostAddress();
		} catch (UnknownHostException | SocketException e) {
			System.out.println(e.getMessage());
		}

		String digito[] = ip.split("\\.");
		return Integer.parseInt(digito[3]);
	}

}
