/**
コネクション、ログインセクションを介してチャットセクションに移行します。
チャットセクションはReceptionThreadとTransmissionThreadで構成され、
移行後はmain()は終了します。
*/

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Console;

public class ClientMain {

  public static final int SERVER_PORT = 12121;

  //開業コード取得
  public static final String  crlf = System.getProperty("line.separator");

  public static Socket socket = null;

  public static void main(String args[]) {

    System.out.println(crlf + "///////////////////////////////////////////////////");
    System.out.println("////////  This is Client-Side! d(・８・)b  ////////");
    System.out.println("///////////////////////////////////////////////////" + crlf);

    Console console = System.console();

    /*コネクションセクション----------------*/

    while(true){

      //接続先サーバーを入力
      System.out.println("Please enter host name of connection destination");
      String host = console.readLine("host > ");

      //hostに入力があればソケットを作成
      if("".equals(host)){
          System.out.println("見入力です。");
      }else{
        try{
          socket = new Socket(host, SERVER_PORT);
        }
        catch(UnknownHostException e){
          System.out.println("つなげるサーバーがないぞ！（・８・）9m");
        }
        catch(IOException e){}
      }


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

      String user_name = console.readLine("user_name > ");
      char [] password = console.readPassword("password > ");

      if(verifyLogin(user_name, password)){
        //ログインセクションから出ます。
        break;
      }else{
        //ログインエラー
        System.out.println("ERROR. Sorry, please enter again.");
      }
    }

    /**チャットセクション----------------*/

    System.out.println(crlf + "///////////////////////////////////////////////////////");
    System.out.println("////////  WELCOME! THIS IS FXChat! d(・８・)b  ////////");
    System.out.println("///////////////////////////////////////////////////////" + crlf);

    new TransmissionThread(socket).start();
    new ReceptionThread(socket).start();

  }//main()終了

  //ログイン処理
  public static Boolean verifyLogin(String user_name, char [] password){

    // InputStream input = null;
    // String isLoginPermitted = "false";
    // try{
    //   OutputStream output = socket.getOutputStream();
    //   output.write(FXprotocolModuleClient.login(user_name, password));

    //   input = socket.getInputStream();

    //   byte [] buffer = new byte[10];//受信バイト列格納用、とりあえず10
    //   int messageSize = input.read(buffer);// 受信メッセージサイズ
    //   isLoginPermitted = FXprotocolModuleClient.convert(buffer, messageSize);

    // }catch(IOException e){
    //   e.printStackTrace();
    // }
    // System.out.println(isLoginPermitted);
    return true;
  }

}