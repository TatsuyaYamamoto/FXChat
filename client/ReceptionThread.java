import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.Socket;


class ReceptionThread extends Thread{

	private Socket socket;

	public ReceptionThread(Socket socket){
		this.socket = socket;
	}

	public void run(){


		try{

			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while(!socket.isClosed()) {

				String receivedText = input.readLine();
				if(receivedText != null){
					//サーバーからの文字列を取得、出力					
					System.out.println("サーバーさん : " + receivedText);
				}else{
					//サーバーがcloseした場合、nullが返されるのでwhileを抜ける
					System.out.println("Server closed.");
					break;
				}
			}

		}catch(IOException e){
			// e.printStackTrace();
		}finally{
			try{
				if(!socket.isClosed()){
					socket.close();
					System.out.println("[reception]close socket...(ｼｮﾝﾎﾞﾘ");
				}

			}catch(IOException e){
				// e.printStackTrace();
			}
		}

	}
}
