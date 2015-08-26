 ]_
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

class Transmission extends Thread{

	private Socket socket;
	private byte [] message;


	public Transmission(Socket socket, byte [] message){
		this.socket = socket;
		this.message = message;
	}
	
	public void run(){

		try{

			OutputStream output = socket.getOutputStream();

			output.write(responce);

		}catch(IOException e){
			// e.printStackTrace();
		}
	}
}