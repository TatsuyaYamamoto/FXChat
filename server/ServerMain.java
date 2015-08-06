public class ServerMain {

  //環境に合わせた改行コードを取得する
  public static final String crlf = System.getProperty("line.separator");

  public static void main(String args[]) {

    System.out.println(crlf + "//////////////////////////////////////////////////");
    System.out.println("////////  START FXChat SYSTEM! d(・８・)b  ////////");
    System.out.println("//////////////////////////////////////////////////" + crlf);

    new SocketManagerThread().start();
    new ChatManager();
  }
}