import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

class ChatThread extends Thread{
    private Socket socket;

    public ChatThread(Socket socket){
		System.out.println("//////////GET NEW CHAT THREAD! d(・８・)b//////////");
		this.socket = socket;

		System.out.println("接続された");
		System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
    }

    public void run(){
		try{
			System.out.println("s1");
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("s2");

			String start = "//////////WELCOME TO 秘密のCHATの園(ﾃﾞﾃﾞｰﾝ//////////";
			out.print(start);

		    String line;

		    out.print(in.readLine());
		  //   while( (line = in.readLine()) != null ){
				// System.out.println("[GET MESSAGE] "+socket.getRemoteSocketAddress() + " 受信 : " + line);
				// System.out.println(socket.getRemoteSocketAddress() + " 送信 : " + line);
				// out.println(line);
		  //   }
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