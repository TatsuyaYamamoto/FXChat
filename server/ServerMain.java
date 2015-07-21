import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class ServerMain {


  public static final int ECHO_PORT = 12121;

  //環境に合わせた改行コードを取得する
  public static final String crlf = System.getProperty("line.separator");

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
        //コネンション確立後、ログインセクションに移行
        System.out.println("接続されました！新しいスレッド内へソケット渡します。");
        System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());


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