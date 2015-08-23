import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

class ChatThread extends Thread{
    private Socket socket;

    //環境に合わせた改行コードを取得する
	public static final String crlf = System.getProperty("line.separator");
	//固定値：messageのbyte列の要素数
	private static int BUFFERED_MESSAGE_SIZE = 1028;


    public ChatThread(Socket socket){
		System.out.println(crlf + "//////////////////////////////////////////////////");
		System.out.println("////////  RUN NEW CHAT THREAD! d(・８・)b  ////////");
		System.out.println("//////////////////////////////////////////////////" + crlf);

		this.socket = socket;

		System.out.println("Client Socketと接続しました。(Remote Socket Address : " + socket.getRemoteSocketAddress() + ")");
    }

    public void run(){

		try{
		    InputStream input = socket.getInputStream();
		    OutputStream output = socket.getOutputStream();


			while(!socket.isClosed()){

				byte [] message = new byte[BUFFERED_MESSAGE_SIZE];//受信バイト列格納用
				input.read(message); // メッセージ受信
				System.out.println(message[0] + "+" + message[1] + "+" + message[2]+ "+" + message[100]);


				//受信したmessageのheaderを確認する
				// String messageType = FXprotocolModuleServer.checkHeader(message);

				System.out.println("[GET MESSAGE] : " + FXprotocolModuleServer.getBody(message));

				// switch(messageType){
				// 	case "login":
				// 		System.out.println("[LOGIN REQUEST RECEPTION : "+socket.getRemoteSocketAddress() + " ]" );

				// 		if(FXprotocolModuleServer.isLoginPermitted()){
				// 			message = "true".getBytes();
				// 			System.out.println("[LOGIN おk！ : "+socket.getRemoteSocketAddress() + " ]" );
				// 		}else{
				// 			message = "false".getBytes();
				// 			System.out.println("[LOGIN だめ！ : "+socket.getRemoteSocketAddress() + " ]" );
				// 		}
				// 		break;

				// 	case "broadcast":

				// 		System.out.println("[GET MESSAGE : "+socket.getRemoteSocketAddress() + "] " + FXprotocolModuleServer.convert(message, messageSize));
				// 		System.out.println("[SEND MESSAGE : "+socket.getRemoteSocketAddress() + "] " + FXprotocolModuleServer.convert(message, messageSize));
				// 		break;
				// }

				//バイト列を出力
				output.write(message);
				output.flush();//残らずでやがれ！
			}

		}catch(IOException e){
		    // e.printStackTrace();
		}finally{
		    try{
				if(socket != null){
				    socket.close();
				}
		    }catch(IOException e){

		    }
			System.out.println("切断されました");
			System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
		}
    }
}