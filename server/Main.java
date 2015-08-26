import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Main {


	//環境に合わせた改行コードを取得する
	public static final String crlf = System.getProperty("line.separator");
	public static final int ECHO_PORT = 12121;

	public static void main(String args[]) {

	    System.out.println(crlf + "//////////////////////////////////////////////////");
	    System.out.println("////////  START Fine! SYSTEM! d(・８・)b  ////////");
	    System.out.println("//////////////////////////////////////////////////" + crlf);

	    MessageManager messageManager = new MessageManager();

		private ServerSocket serverSocket;

        try{

        	/*サーバーソケット作成*/
			System.out.println("info: socket creating...");
			serverSocket = new ServerSocket(ECHO_PORT);
			System.out.println("info: ServerSocketが起動しました!(LocalPort : " + serverSocket.getLocalPort() + ")");
			System.out.println("info: ソケット接続が可能になりました" + crlf);

			/*クライアントかのアクセス待機*/
			while(!serverSocket.isClosed()){
				Socket socket = serverSocket.accept();
				
				//コネクション後、個別チャットスレッド作成
				System.out.println("接続されました！新しいスレッド内へソケット渡します。");
				System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
				new ChatThread(socket, messageManager).start();
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
