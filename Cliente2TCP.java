package lista3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente2TCP {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int port = 28886;
		String address = "localhost";
		int serverPort = 3001;
		
		try {
			Socket socketServidor = new Socket(address, serverPort);
			DataOutputStream saida = new DataOutputStream(socketServidor.getOutputStream());
			saida.writeUTF(Integer.toString(port));
			System.out.println("Conexão aberta com servidor.");
			DataInputStream entrada = new DataInputStream(socketServidor.getInputStream());
			String respostaServidor = entrada.readUTF();
			System.out.println("Porta do outro usuário: "+respostaServidor);
			socketServidor.close();
			Socket socketUsuario = new Socket(address, Integer.parseInt(respostaServidor));
			System.out.println("Porta aberta com o usuário, envie sua mensagem.");
			InterfaceGrafica interfaceGrafica = new InterfaceGrafica(null, new Escrita2(socketUsuario), "Cliente 2");
			new Leitura2(socketUsuario, interfaceGrafica).start();
		} catch (IOException e) {
			System.out.println("Erro "+e);
		}

	}

}

class Escrita2 {
	private Socket socket;
	
	public Escrita2(Socket s) {
		this.socket = s;
	}
	
	public void send(String msg) {
		Scanner in = new Scanner(System.in);
			try {
				DataOutputStream mensagem = new DataOutputStream(socket.getOutputStream());
				mensagem.writeUTF(msg);
			} catch (IOException e) {
				System.out.println("Erro "+e);
			} 
	}
}

class Leitura2 extends Thread {
	private Socket socket;
	private InterfaceGrafica interfaceGrafica;
	
	public Leitura2(Socket s, InterfaceGrafica inter) {
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


