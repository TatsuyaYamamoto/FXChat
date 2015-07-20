import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.io.IOException;

class ChatThread extends Thread{
    private Socket socket;

//開業コード取得
public static final String  crlf = System.getProperty("line.separator");

    public ChatThread(Socket socket){
		System.out.println(crlf + "//////////////////////////////////////////////////");
		System.out.println("////////  RUN NEW CHAT THREAD! d(・８・)b  ////////");
		System.out.println("//////////////////////////////////////////////////" + crlf);

		this.socket = socket;

		System.out.println("Client Socketと接続しました。(Remote Socket Address : " + socket.getRemoteSocketAddress() + ")");
    }

    public void run(){

		try{
		    // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();

			int c = 0;
			System.out.println("bout");

			while(!socket.isClosed()){
				// String line = in.readLine();

				bout.write(input.read());

		    	System.out.println(new String(bout.toByteArray(), "UTF-8") + "!!");


				// if(line == null){
				// break;
				// }

				// System.out.println("[GET MESSAGE : "+socket.getRemoteSocketAddress() + "] " + line);
				// System.out.println("[SEND MESSAGE : "+socket.getRemoteSocketAddress() + "] " + line);
				// out.println(line);

		    }

		}catch(IOException e){
		    e.printStackTrace();
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