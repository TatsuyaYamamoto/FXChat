import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class ServerMain {

public static final int ECHO_PORT = 12121;

  public static void main(String args[]) {
    ServerSocket serverSocket = null;
    try{

      //サーバーを起動
      System.out.println("\r//////////START FXChat SYSTEM!! d(・８・)b//////////\r");
      System.out.println("socket creating...");
      serverSocket = new ServerSocket(ECHO_PORT);
      System.out.println("EchoServerが起動しました");
      System.out.println("LocalPort : " + serverSocket.getLocalPort());
      //クライアントからのコネクション待機
      System.out.println("待つわ、、、いつまでも待つわ");


      //クライアントからのTCPコネクションを待ち続ける
      while(true){
        Socket socket = serverSocket.accept();
        new ChatThread(socket).start();
        //コネンション確立後、ログインセクションに移行
        System.out.println("接続されました！");
        System.out.println("Remote Socket Address : " + socket.getRemoteSocketAddress());
      }


      // BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

      // String line;
      // while ( (line = input.readLine()) != null ) {
      //   System.out.println("get message : " + line);
      //   output.println("サーバーより : " + line);
      //   System.out.println("echo message : " + line);
      // }
    }catch(IOException e) {
      e.printStackTrace();
    }finally{
      try{
        if(serverSocket != null) {
          serverSocket.close();
        }
      }catch (IOException e) {

      }
      System.out.println("\r//////////CLOSED FXChat SYSTEM.... q(・８・)p//////////\r");
    }
  }
}