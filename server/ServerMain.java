import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class ServerMain {

public static final int ECHO_PORT = 12121;
//開業コード取得
public static final String  crlf = System.getProperty("line.separator");

  public static void main(String args[]) {
    ServerSocket serverSocket = null;
    try{

      //サーバーを起動
      System.out.println(crlf + "//////////////////////////////////////////////////");
      System.out.println("////////  START FXChat SYSTEM! d(・８・)b  ////////");
      System.out.println("//////////////////////////////////////////////////" + crlf);

      System.out.println("socket creating...");
      serverSocket = new ServerSocket(ECHO_PORT);
      System.out.println("ServerSocketが起動しました!(LocalPort : " + serverSocket.getLocalPort() + ")");
      System.out.println("ソケット接続が可能になりました" + crlf);

      //クライアントからのTCPコネクションを待ち続ける
      while(!serverSocket.isClosed()){
        //クライアントからのコネクション待機
        Socket socket = serverSocket.accept();
        new ChatThread(socket).start();
      }

    }catch(IOException e) {
      e.printStackTrace();
    }finally{
      try{
        if(serverSocket != null) {
          serverSocket.close();
        }
      }catch (IOException e) {

      }
      System.out.println(crlf + "//////////////////////////////////////////////////");
      System.out.println("////////// CLOSED FXChat SYSTEM.... q(・８・)p //////////");
      System.out.println("//////////////////////////////////////////////////" + crlf);

    }
  }
}