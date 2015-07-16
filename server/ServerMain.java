import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ServerMain {

public static final int ECHO_PORT = 10007;

  public static void main(String args[]) {
    ServerSocket serverSocket = null;
    Socket socket = null;
    try{
      serverSocket = new ServerSocket(ECHO_PORT);
      System.out.println("EchoServerが起動しました(port=" + serverSocket.getLocalPort() + ")");
      socket = serverSocket.accept();
      System.out.println("接続されました " + socket.getRemoteSocketAddress() );


      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

      String line;
      while ( (line = input.readLine()) != null ) {
        System.out.println("get message : " + line);
        output.println("サーバーより : " + line);
        System.out.println("echo message : " + line);
      }
    }catch(IOException e) {
      e.printStackTrace();
    }finally{
      try{
        if(socket != null) {
          socket.close();
        }
      }catch(IOException e) {

      }
      try{
        if(serverSocket != null) {
          serverSocket.close();
        }
      }catch (IOException e) {

      }
    }
  }
}