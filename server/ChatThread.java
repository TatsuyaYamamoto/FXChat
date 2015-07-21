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
				byte [] buffer = new byte[BUFFERED_MESSAGE_SIZE];//受信バイト列格納用
				int messageSize = input.read(buffer); // 受信メッセージサイズ

				System.out.println("[GET MESSAGE : "+socket.getRemoteSocketAddress() + "] " + FXprotocolModuleServer.convert(buffer, messageSize));
				System.out.println("[SEND MESSAGE : "+socket.getRemoteSocketAddress() + "] " + FXprotocolModuleServer.convert(buffer, messageSize));

				//バイト列を出力
				output.write(buffer);
				output.flush();//残らずでやがれ！
			}



			// int c = 0;
			// System.out.println("bout");

			// while(!socket.isClosed()){
			// 	// String line = in.readLine();

			// 	bout.write(input.read());


		 //    	System.out.println(new String(bout.toByteArray(), "UTF-8") + "!!");


		 //  //   out.print(in.readLine());
		 //  //   while( (line = in.readLine()) != null ){
			// 	// System.out.println("[GET MESSAGE] "+socket.getRemoteSocketAddress() + " 受信 : " + line);
			// 	// System.out.println(socket.getRemoteSocketAddress() + " 送信 : " + line);
			// 	// out.println(line);
		 //  //   }

			// 	// if(line == null){
			// 	// break;
			// 	// }

			// 	// System.out.println("[GET MESSAGE : "+socket.getRemoteSocketAddress() + "] " + line);
			// 	// System.out.println("[SEND MESSAGE : "+socket.getRemoteSocketAddress() + "] " + line);
			// 	// out.println(line);

		 //    }

		}catch(IOException e){
		    e.printStackTrace();
			System.out.println("これ？");
		}finally{
		    try{
				if(socket != null){
				    socket.close();
				}
		    }catch(IOException e){
				System.out.println("切断された");
				System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
		    }
		}
    }
}