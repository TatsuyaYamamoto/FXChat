import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Console;

public class ClientMain {

  public static int SERVER_PORT = 12121;

//開業コード取得
public static final String  crlf = System.getProperty("line.separator");


  public static void main(String args[]) {


    //要実装！！！！！！
    //標準入力のチェック

    System.out.println(crlf + "///////////////////////////////////////////////////");
    System.out.println("////////  This is Client-Side! d(・８・)b  ////////");
    System.out.println("///////////////////////////////////////////////////" + crlf);


    Socket socket = null;
    Console console = System.console();

    /*コネクションセクション----------------*/

    while(true){

      //接続先サーバーを入力
      System.out.println("Please enter host name of connection destination");
      String host = console.readLine("host > ");

      try{
        socket = new Socket(host, SERVER_PORT);
      }
      catch(UnknownHostException e){
        System.out.println("つなげるサーバーがないぞ！（・８・）9m");
      }
      catch(IOException e){}

      //接続成功:break
      //接続失敗:socket == null
      if(socket != null){
        break;
      }
    }

    System.out.println(crlf + "//////////////////////////////////////////////////");
    System.out.println("// サーバーへ接続しました");
    System.out.println("// local socket address  = " + socket.getLocalSocketAddress() );
    System.out.println("// remote socket address = " + socket.getRemoteSocketAddress() );
    System.out.println("//////////////////////////////////////////////////" + crlf);


    /*ログインセクション----------------*/

    while(true) {

      //サーバへuser_nameを送信
      String user_name = console.readLine("user_name > ");
      //サーバへpasswordを送信
      char [] password = console.readPassword("password > ");

      if(verifyLogin(user_name, password)){
        //ログインセクションから出ます。
        break;
      }else{
        //ログインエラー
        System.out.println("ERROR. Sorry, please enter again.");
      }
    }


    System.out.println(crlf + "///////////////////////////////////////////////////////");
    System.out.println("////////  WELCOME! THIS IS FXChat! d(・８・)b  ////////");
    System.out.println("///////////////////////////////////////////////////////" + crlf);


    /**チャットセクション----------------*/

    new TransmissionThread(socket).start();
    new ReceptionThread(socket).start();

    while(!socket.isClosed()){
      //これむだやな、、、直したいんですけど。
    }



    //ソケットを閉じる
    try{

      if(!socket.isClosed()){
        socket.close();
        System.out.println("close socket...(ｼｮﾝﾎﾞﾘ");
      }
    }catch(IOException e){
      e.printStackTrace();
    }

    System.out.println(crlf + "//////////////////////////////////////////////////");
    System.out.println("// 切断されました");
    System.out.println("// local socket address  = " + socket.getLocalSocketAddress() );
    System.out.println("// remote socket address = " + socket.getRemoteSocketAddress() );
    System.out.println("//////////////////////////////////////////////////" + crlf);

  }//main()終了

  //ログイン処理
  public static boolean verifyLogin(String user_name, char [] password){
    return true;
  }

}