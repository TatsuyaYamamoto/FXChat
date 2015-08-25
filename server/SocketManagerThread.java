import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

class SocketManagerThread extends Thread{

	private ServerSocket serverSocket;
	private ChatManager chatManager;

	public static final String crlf = System.getProperty("line.separator");
	public static final int ECHO_PORT = 12121;

    public SocketManagerThread(){
		System.out.println(crlf + "//////////////////////////////////////////////////");
		System.out.println("////////  RUN SOCKET MANGAER! d(・８・)b  ////////");
		System.out.println("//////////////////////////////////////////////////" + crlf);

		chatManager = new ChatManager();
    }

    public void run(){

        try{

        	/*サーバーソケット作成*/
			System.out.println("socket creating...");
			serverSocket = new ServerSocket(ECHO_PORT);
			System.out.println("ServerSocketが起動しました!(LocalPort : " + serverSocket.getLocalPort() + ")");
			System.out.println("ソケット接続が可能になりました" + crlf);

			/*クライアントかのアクセス待機*/
			while(!serverSocket.isClosed()){
				Socket socket = serverSocket.accept();
				//コネクション後、個別チャットスレッド作成
				System.out.println("接続されました！新しいスレッド内へソケット渡します。");
				System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
				new ChatThread(socket, chatManager).start();
			}
		}catch(IOException e) {
			e.printStackTrace();
	    }finally{
			try{
				if(serverSocket != null) {
					serverSocket.close();
				}
			}catch (IOException e) {
			}finally{
				System.out.println(crlf + "//////////////////////////////////////////////////");
				System.out.println("////////// CLOSED FXChat SYSTEM.... q(・８・)p //////////");
				System.out.println("//////////////////////////////////////////////////" + crlf);
			}
		}

    }
}