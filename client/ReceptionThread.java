import java.io.InputStream;
import java.io.IOException;

import java.net.Socket;


class ReceptionThread extends Thread{

	private Socket socket;
	//固定値：messageのbyte列の要素数
	private static byte BUFFERED_MESSAGE_SIZE = 1028;

	public ReceptionThread(Socket socket){
		this.socket = socket;
	}

	public void run(){


		try{

		    InputStream input = socket.getInputStream();

			byte [] buffer = new byte[BUFFERED_MESSAGE_SIZE];//受信バイト列格納用
			int messageSize; // 受信メッセージサイズ


			while(!socket.isClosed()){

				if((messageSize = input.read(buffer)) == -1){
					break;
				}

				System.out.println("サーバーさん : " + FXprotocolModuleClient.convert(buffer, messageSize));
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
