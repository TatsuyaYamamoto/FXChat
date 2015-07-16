import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ClientMain {

  public static int ECHO_PORT = 12121;

  public static void main(String args[]) {


    //要実装！！！！！！
    //標準入力のチェック

    System.out.println();
    System.out.println("////////This is Client-Side!//////////");
    System.out.println();

    Socket socket = null;
    try {
      socket = new Socket(args[0], ECHO_PORT);

      System.out.println("サーバーへ接続しました。");
      System.out.println("local socket address  = " + socket.getLocalSocketAddress() );
      System.out.println("remote socket address = " + socket.getRemoteSocketAddress() );
      System.out.println();

      BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);



      String keyIn_text;
      while((keyIn_text = keyIn.readLine()) != null ) {// !!!!ユーザ一入力を待つ【なんでできる？】

        //exitで終わります
        if("exit".equals(keyIn_text)){
          break;          
        }

        System.out.println("あなた : " + keyIn_text);
        output.println(keyIn_text);
        String line = input.readLine();

        if(line != null) {
          System.out.println("サーバーさん : " + line);

        }else{
          break;
        }


      }
    }catch(IOException e){
      e.printStackTrace();
    }finally{

      //ソケットを閉じる
      try{
        if(socket != null){
          System.out.println("close socket...");
          socket.close();
        }
      }catch(IOException e){
        e.printStackTrace();
      }
      System.out.println("切断されました。");
      System.out.println("local socket address  = " + socket.getLocalSocketAddress() );
      System.out.println("remote socket address = " + socket.getRemoteSocketAddress() );
    }
  }
}