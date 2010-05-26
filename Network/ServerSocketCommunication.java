package Network;

import control.CActioner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mats Larsen, søren Jørgensen, stefan larsen, Dan vi.
 */
public class ServerSocketCommunication {

    private ServerSocket server;
    private final int port = 7777;
    CActioner cactioner;

    public ServerSocketCommunication() throws Exception {
	try {
	    cactioner = new CActioner();
	    // Kommunikaitonen forløber gennem port 7777
	    server = new ServerSocket(port);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
// sas starter op

    public static void main(String[] args) throws Exception {
	ServerSocketCommunication ssc = new ServerSocketCommunication();
	ssc.handleConnection();
    }

    public void handleConnection() throws Exception {
	System.out.println("Waiting for client message...");

	// sas løber i et loop, hvor der ventes på accept for klienten

	while (true) {
	    try {
		System.out.println("\nTCPServer waiting for connection on port "
			+ Integer.toString(port));
		Socket socket = server.accept();
		System.out.println("\nTCPServer got a connection");
		new ConnectionHandler(socket);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    /*
     * Håndterer forbindelsen til klienten. Der oprettes et nyt objekt ved hver
     * forbindelse, som forløber som en tråd.
     */

    class ConnectionHandler implements Runnable {

	private Socket socket;

	public ConnectionHandler(Socket socket) throws Exception {
	    this.socket = socket;
	    Thread t = new Thread(this);
	    t.start();
	}

	public void run() {
	    try {
		// Læser fra objektet som er modtaget fra klienten
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		SendObject sObject = (SendObject) ois.readObject();

		try {
		    // Kalder over i kontrolklassen med sObject som parameter
		    sObject = cactioner.chooseMetode(sObject);
		} catch (Exception e) {
		    e.getMessage();
		}
		// Sender response til klienten, som er gemt i sObject.
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sObject);

		// Steam og socket sluttes
		ois.close();
		oos.close();
		socket.close();

	    } catch (IOException e) {
		e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    }
	}
    }
}
