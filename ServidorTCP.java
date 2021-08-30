package lista3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	public static void main(String[] args) throws IOException {
		int port1 = 3001;
		int port2 = 12345;
		
		while (true) {
			try {
				ServerSocket serverSocket1 = new ServerSocket(port1);
				ServerSocket serverSocket2 = new ServerSocket(port2);
				Socket socket1 = serverSocket1.accept();
				Socket socket2 = serverSocket1.accept();
				//criei um socket pra cada cliente
				DataInputStream entrada1 = new DataInputStream(socket1.getInputStream());
				String resposta1 = entrada1.readUTF();
				//leu a porta do cliente 1
				DataOutputStream saida2 = new DataOutputStream(socket2.getOutputStream());
				saida2.writeUTF(resposta1);
				//mandou pro cliente 2 a porta do cliente 1
				serverSocket1.close();
				serverSocket2.close();
			} catch (IOException e) {
				System.out.println("Erro "+e);
			}
		}
		
		

	}

}
