package lista3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Scanner;


public class Cliente1TCP {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int port = 28877;
		String address = "localhost";
		int serverPort = 3001;
		
		try {
			Socket socketServidor = new Socket(address, serverPort);
			DataOutputStream saida = new DataOutputStream(socketServidor.getOutputStream());
			saida.writeUTF(Integer.toString(port));
			System.out.println("Conexão aberta com servidor e número de porta enviado.");
			socketServidor.close();
			ServerSocket socketUsuario = new ServerSocket(port);
			Socket socket = socketUsuario.accept();
			System.out.println("Porta aberta com o usuário, envie sua mensagem.");
			InterfaceGrafica interfaceGrafica = new InterfaceGrafica(new Escrita1(socket), null, "Cliente 1");
			new Leitura1(socket, interfaceGrafica).start();
		} catch (IOException e) {
			System.out.println("Erro "+e);
		}

	}

}

class Escrita1 {
	private Socket socket;
	
	public Escrita1(Socket s) {
		this.socket = s;
	}
	
	public void send(String msg) {
			try {
				DataOutputStream mensagem = new DataOutputStream(socket.getOutputStream());
				mensagem.writeUTF(msg);
			} catch (IOException e) {
				System.out.println("Erro "+e);
			} 
	}
}

class Leitura1 extends Thread {
	private Socket socket;
	private InterfaceGrafica interfaceGrafica;
	
	public Leitura1(Socket s, InterfaceGrafica inter) {
		this.socket = s;
		this.interfaceGrafica = inter;
	}
	
public void run() {
		
		while (true) {
			try {
				DataInputStream mensagem = new DataInputStream(this.socket.getInputStream());
				String msg = mensagem.readUTF();
				this.interfaceGrafica.getResposta(msg);
			} catch (IOException e) {
				System.out.println("Erro "+e);
			} 
		}
	}
}
