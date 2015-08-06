import java.io.InputStream;
import java.io.IOException;

import java.net.Socket;
import java.lang.IndexOutOfBoundsException;

class ReceptionThread extends Thread{

	private Socket socket;
	//固定値：messageのbyte列の要素数
	private static int BUFFERED_MESSAGE_SIZE = 1028;
	//開業コード取得
	public static final String  crlf = System.getProperty("line.separator");

	public ReceptionThread(Socket socket){
		this.socket = socket;
	}

	public void run(){


		try{

		    InputStream input = socket.getInputStream();

			while(!socket.isClosed()){
				byte [] buffer = new byte[BUFFERED_MESSAGE_SIZE];//受信バイト列格納用
				int messageSize = input.read(buffer);// 受信メッセージサイズ

				System.out.println("サーバーさん : " + FXprotocolModuleClient.convert(buffer, messageSize));
			}


		}catch(IOException e){
			// e.printStackTrace();
		}catch(IndexOutOfBoundsException e){
			// e.printStackTrace();
		}finally{
			try{
				if(!socket.isClosed()){
					socket.close();
					System.out.println("[reception]close socket...(ｼｮﾝﾎﾞﾘ");
				}

			}catch(IOException e){
				// e.printStackTrace();
			}finally{
			    System.out.println(crlf + "//////////////////////////////////////////////////");
			    System.out.println("// 切断されました");
			    System.out.println("// local socket address  = " + socket.getLocalSocketAddress() );
			    System.out.println("// remote socket address = " + socket.getRemoteSocketAddress() );
			    System.out.println("//////////////////////////////////////////////////" + crlf);
			}
		}

	}
}
