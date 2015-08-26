class SocketManager extends Thread{

    private Socket socket;
    //環境に合わせた改行コードを取得する
	public static final String crlf = System.getProperty("line.separator");
	//固定値：messageのbyte列の要素数
	private static int BUFFERED_MESSAGE_SIZE = 1028;



	private Map<String, Socket> userlist;

    public SocketManager(){
		System.out.println(crlf + "//////////////////////////////////////////////////");
		System.out.println("////////  RUN SOCKET MANAGER! d(・８・)b  ////////");
		System.out.println("//////////////////////////////////////////////////" + crlf);
    }

	public void setScoket(Socket socket){

	}
	public void broadcast(){

	}
}