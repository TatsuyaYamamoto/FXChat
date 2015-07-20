import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

class ChatThread extends Thread{
    private Socket socket;

    //環境に合わせた改行コードを取得する
	public static final String sep = System.getProperty("line.separator");

    public ChatThread(Socket socket){
		System.out.println(sep + "//////////GET NEW CHAT THREAD! d(・８・)b//////////" + sep);
		this.socket = socket;

		System.out.println("接続されました！");
		System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
    }

    public void run(){
		try{

		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		    String line;

		    out.print(in.readLine());
		    while( (line = in.readLine()) != null ){
				System.out.println("[GET MESSAGE] "+socket.getRemoteSocketAddress() + " 受信 : " + line);
				System.out.println(socket.getRemoteSocketAddress() + " 送信 : " + line);
				out.println(line);
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