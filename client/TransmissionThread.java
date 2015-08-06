import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

class TransmissionThread extends Thread{


	//開業コード取得
	public static final String  crlf = System.getProperty("line.separator");

	private Socket socket;

	public TransmissionThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){

		try{

			BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
			// PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


			while(!socket.isClosed()){
				OutputStream output = socket.getOutputStream();
				System.out.println("[transmission is in readiness]");
				String keyIn_text = keyIn.readLine();

				//入力した文字列が「exit」で終わります
				if("exit".equals(keyIn_text)){
					System.out.println("帰っちゃうの...？!（・８・）ｻﾐｼｲｯ‼︎");
					System.out.println("Please enter \"no\"!");
					String exit_answer = keyIn.readLine();

					if("no".equals(exit_answer)){
						System.out.println("YATTA!YATTA!");
						//noならループが続く
					}else{
						//それ以外は終了
						break;
					}
				}else{
					//メッセージの型を指定(とりあえずブロードキャスト固定)
					// String type = "broadcast";
					//チャットサーバー用メッセージフォーマットに変換して送信する
					output.write(FXprotocolModuleClient.convert(keyIn_text));
					output.flush();//残らずでやがれ！
				}
			}

		}catch(IOException e){
			// e.printStackTrace();
		}finally{
			try{
				if(!socket.isClosed()){
					socket.close();
					System.out.println("[transmission]close socket...(ｼｮﾝﾎﾞﾘ");
				}
			}catch(IOException e){
				// e.printStackTrace();
			}finally{
			    System.out.println(crlf + "//////////////////////////////////////////////////");
			    System.out.println("// 切断されました");
			    System.out.println("// local socket address  = " + socket.getLocalSocketAddress() );
			    System.out.println("// remote socket address = " + socket.getRemoteSocketAddress() );
			    System.out.println("//////////////////////////////////////////////////" + crlf);
			}
		}
	}
}